package ssong.boardspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ssong.boardspring.domain.Member;
import ssong.boardspring.dto.MemberCreateDto;
import ssong.boardspring.repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        MemberCreateDto memberDto = new MemberCreateDto();
        memberDto.setMemberEmail("dddgg22@naver.com");
        memberDto.setMemberName("뚜기님");
        memberDto.setMemberPhone("01011110000");
        memberDto.setMemberPw("1234");

        Long saveId = memberService.join(memberDto);
        Optional<Member> findMemberId = memberService.findOne(saveId);

        assertThat(findMemberId).isNotNull();
    }

    @Test
    public void checkDuplicateMember() {
        MemberCreateDto member1 = new MemberCreateDto();
        member1.setMemberEmail("songtest@naver.com");
        member1.setMemberName("뚜기님");
        member1.setMemberPhone("01011110000");
        member1.setMemberPw("1234");


        MemberCreateDto member2 = new MemberCreateDto();
        member2.setMemberEmail("songtest@naver.com");
        member2.setMemberName("뚜기님");
        member2.setMemberPhone("01011110000");
        member2.setMemberPw("1234");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
