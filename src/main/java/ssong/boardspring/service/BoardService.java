package ssong.boardspring.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssong.boardspring.domain.Board;
import ssong.boardspring.domain.Member;
import ssong.boardspring.dto.BoardCreateDto;
import ssong.boardspring.dto.BoardUpdateDto;
import ssong.boardspring.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BoardService {
    private BoardRepository boardRepository;
    private MemberService memberService;

    public BoardService(BoardRepository boardRepository, MemberService memberService) {
        this.boardRepository = boardRepository;
        this.memberService = memberService;
    }

    //게시글 작성
    public long createBoard(BoardCreateDto boardCreateDto) {
        Board board = new Board();
        board.setTitle(boardCreateDto.getBoardTitle());
        board.setContent(boardCreateDto.getBoardContent());
        Member member = memberService.findOne(boardCreateDto.getMemberId()).get();
        board.setMember(member);
        boardRepository.save(board);
        return board.getId();
    }

    //게시글 수정
    public Long updateBoard(BoardUpdateDto boardUpdateDto) {
        Board board = boardRepository.findById(boardUpdateDto.getId()).orElse(null);
        if (board != null) {
            board.setContent(boardUpdateDto.getBoardContent());
            board.setTitle(boardUpdateDto.getBoardTitle());
            boardRepository.save(board);
            return board.getId();
        } else {
            return null;
        }

    }

    //게시글 리스트
//    public List<Board> findBoardList() {
//        List<Board> boardList = boardRepository.findAll();
//        return boardList;
//    }

    //게시글 목록 FOR PAGINATION
    public Page<Board> findBoardList(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by("regDate").descending());
        return (Page<Board>) boardRepository.findAll(pageable);
    }

    //게시글 조회
    public Board findOne(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            return optionalBoard.get();
        }
        return null;
    }

    //게시글 삭제
    public int deleteBoard(Long boardId){
        int cnt = 0;
        try{
            boardRepository.deleteById(boardId);
            cnt++;
        } catch (EmptyResultDataAccessException e){
        }
        return cnt;
    }
}
