package ssong.boardspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ssong.boardspring.domain.Board;
import ssong.boardspring.domain.Member;
import ssong.boardspring.dto.BoardCreateDto;
import ssong.boardspring.dto.BoardUpdateDto;
import ssong.boardspring.service.BoardService;
import ssong.boardspring.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    public BoardController(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
    }


    //게시판 목록
    @GetMapping("")
    public ModelAndView getBoards(@RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
        int pageSize = 5;

        Page<Board> boardList = boardService.findBoardList(page, pageSize);
        ModelAndView mv = new ModelAndView("board/boardList");

        String remoteUser = request.getRemoteUser();
        Member member = memberService.findByMemberEmail(remoteUser);

        mv.addObject("member", member);
        mv.addObject("boardList", boardList);
        return mv;
    }

    //게시글 단일 조회
    @GetMapping("/{boardId}")
    public ModelAndView getBoard(@PathVariable Long boardId) {
        ModelAndView mv = new ModelAndView("board/viewPage");
        mv.addObject("board", boardService.findOne(boardId));
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
    public ResponseEntity<String> createBoard(@Validated @RequestBody BoardCreateDto boardCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("유효성 검사에 실패하였습니다.");
        }
        Long result = boardService.createBoard(boardCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result.toString());
    }

    //게시글 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<String> updateBoard(@Validated @RequestBody BoardUpdateDto boardUpdateDto, BindingResult bindingResult, @PathVariable Long boardId) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("유효성 검사에 실패하였습니다.");
        }
        Long result = boardService.updateBoard(boardUpdateDto);
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
        int cnt = boardService.deleteBoard(boardId);
        if (cnt > 0) {
            return ResponseEntity.ok("삭제되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("게시글을 찾을 수 없습니다.");
    }

}
