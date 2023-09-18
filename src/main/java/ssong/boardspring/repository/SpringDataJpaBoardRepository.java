package ssong.boardspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssong.boardspring.domain.Board;

public interface SpringDataJpaBoardRepository extends JpaRepository<Board,Long>, BoardRepository {


}
