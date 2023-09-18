package ssong.boardspring.repository;

import ssong.boardspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //회원가입
    Member save(Member member);

    //아이디 회원 조회
    Optional<Member> findById(long id);

    //email 회원 조회
    Optional<Member> findBymemberEmail(String memberEmail);

    //모두 조회
    List<Member> findAll();

}
