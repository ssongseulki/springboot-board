package ssong.boardspring.controller;

import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ssong.boardspring.domain.Board;
import ssong.boardspring.domain.Member;
import ssong.boardspring.dto.BoardDto;
import ssong.boardspring.service.BoardService;
import ssong.boardspring.service.MemberService;
import ssong.boardspring.service.S3UploadService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final S3UploadService s3UploadService;

    public BoardController(BoardService boardService, MemberService memberService, S3UploadService s3UploadService) {
        this.boardService = boardService;
        this.memberService = memberService;
        this.s3UploadService = s3UploadService;
    }


    //게시판 목록
    @GetMapping("")
    public ModelAndView getBoards(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 10;

        Page<Board> boardList = boardService.findBoardList(page, pageSize);
        ModelAndView mv = new ModelAndView("board/boardList");
        mv.addObject("boardList", boardList);
        return mv;
    }

    //게시글 조회
    @GetMapping("/{boardId}")
    public ModelAndView getBoard(@PathVariable Long boardId) {
        ModelAndView mv = new ModelAndView("board/viewPage");
        //게시글 조회수 증가
        boolean resultView = boardService.incrementHitCnt(boardId);
        if (resultView == true) {
            //게시글 정보 조회
            mv.addObject("board", boardService.findOne(boardId));
        }
        return mv;
    }

    //게시글 작성 화면
    @GetMapping("/writePage")
    public String writePage(Model model, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        Member member = memberService.findByMemberEmail(remoteUser);
        model.addAttribute("member", member);
        return "board/writePage";
    }

    //게시글 수정 화면
    @GetMapping("/modifyPage/{boardId}")
    public ModelAndView modifyPage(@Validated @PathVariable Long boardId) {
        ModelAndView mv = new ModelAndView("board/writePage");
        mv.addObject("board", boardService.findOne(boardId));
        return mv;
    }

    //게시글 작성
    @PostMapping("")
    public ResponseEntity<String> createBoard(@Validated @ModelAttribute("boardRequest") BoardDto boardDto,
                                              @RequestPart(value = "attachedFile", required = false) MultipartFile multipartFile,
                                              BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("유효성 검사에 실패하였습니다.");
        }
        //첨부파일 AWS S3 저장
        if (multipartFile != null) {
            uploadFile(multipartFile, boardDto);
        }

        //board 저장
        Long result = boardService.createBoard(boardDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result.toString());
    }

    //게시글 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<String> updateBoard(@Validated @ModelAttribute("boardRequest") BoardDto boardDto,
                                              @RequestPart(value = "attachedFile", required = false) MultipartFile multipartFile,
                                              BindingResult bindingResult, @PathVariable String boardId) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("유효성 검사에 실패하였습니다.");
        }
        //첨부파일 AWS S3 저장
        if (multipartFile != null) {
            String originalFileName = boardDto.getS3fileName();
            //기존 파일 삭제
            s3UploadService.deleteImage(originalFileName);
            uploadFile(multipartFile, boardDto);
        }

        Long result = boardService.updateBoard(boardDto);
        if (result != null) {
            return ResponseEntity.ok(result.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("게시글을 찾을 수 없습니다.");
        }

    }

    //게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@Validated @PathVariable Long boardId) {
        Board board = boardService.findOne(boardId);
        String originalFileName = board.getS3fileName();
        boolean result = boardService.deleteBoard(boardId);
        if (result) {
            if (originalFileName != null) {
                s3UploadService.deleteImage(originalFileName);
            }
            return ResponseEntity.ok("삭제되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("게시글을 찾을 수 없습니다.");
    }

    //게시글 첨부파일 업로드
    public void uploadFile(@RequestPart(value = "attachedFile", required = false) MultipartFile multipartFile, BoardDto boardDto) throws IOException {
        Map<String, Object> attachedFile = s3UploadService.saveFile(multipartFile);
        boardDto.setFileName((String) attachedFile.get("fileName"));
        boardDto.setFilePath((String) attachedFile.get("filePath"));
        boardDto.setS3fileName((String) attachedFile.get("s3fileName"));
    }

    //게시글 첨부파일 다운로드
    @GetMapping("/downloadFile")
    public ResponseEntity<UrlResource> downloadFile(String fileName) {
        return s3UploadService.downloadFile(fileName);
    }


}
