package ssong.boardspring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ssong.boardspring.domain.Board;

import java.util.Optional;


public interface BoardRepository extends PagingAndSortingRepository<Board,Long> {

    //게시글 작성, 수정
    Board save(Board board);

    //게시글 삭제
    Board deleteById(long id);

    //게시글 리스트 ForPaging
    Page<Board> findAll(Pageable pageable);

    //단일 게시글 조회
    Optional<Board>findById(long id);

    //게시글 댓글 등록

    //게시글 댓글 수정

    //게시글 댓글 리스트 조회

    //게시글 댓글 삭제
}
