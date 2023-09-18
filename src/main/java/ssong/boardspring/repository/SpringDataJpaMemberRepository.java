package ssong.boardspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssong.boardspring.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findBymemberEmail(String memberEmail);
}
