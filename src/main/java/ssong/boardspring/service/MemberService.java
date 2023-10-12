package ssong.boardspring.service;

import org.springframework.data.domain.Page;
import ssong.boardspring.domain.Member;
import ssong.boardspring.dto.MemberCreateDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    //회원가입
    boolean join(MemberCreateDto memberCreateDto);

    //전체 회원 조회
    List<Member> findMembers();

    //전체 회원 조회 For Paging
    Page<Member> findMembersForPaging(int page, int pageSize);

    //회원 조회 By ID
    Optional<Member> findOne(Long memberId);

    //회원 정보 조회 By Email
    Member findByMemberEmail(String memberEmail);

}
