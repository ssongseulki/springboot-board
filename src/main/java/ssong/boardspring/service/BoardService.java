package ssong.boardspring.service;

import org.springframework.data.domain.Page;
import ssong.boardspring.domain.Board;
import ssong.boardspring.dto.BoardDto;

public interface BoardService {
    //게시글 작성
    long createBoard(BoardDto boardDto);

    //게시글 수정
    Long updateBoard(BoardDto boardDto);

    //게시글 목록 FOR PAGINATION
    Page<Board> findBoardList(int page, int pageSize);

    //게시글 조회
    Board findOne(Long boardId);

    //게시글 삭제
    boolean deleteBoard(Long boardId);

    //게시글 조회 수 증가
    boolean incrementHitCnt(Long boardId);
}
