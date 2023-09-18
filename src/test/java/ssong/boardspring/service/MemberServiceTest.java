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

@SpringBootTest
//@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join(){
        MemberCreateDto memberDto = new MemberCreateDto();
        memberDto.setMemberEmail("dddgg@naver.com");
        memberDto.setMemberName("뚜기님");
        memberDto.setMemberPhone("01011110000");
        memberDto.setMemberPw("1234");

        Long saveId = memberService.join(memberDto);
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember).isNotNull();
        assertThat(memberDto.getMemberEmail()).isEqualTo(findMember.getMemberEmail());
        assertThat(memberDto.getMemberName()).isEqualTo(findMember.getMemberName());
        assertThat(memberDto.getMemberPhone()).isEqualTo(findMember.getMemberPhone());
        assertThat(memberDto.getMemberPw()).isEqualTo(findMember.getMemberPw());
    }

   @Test
   public void checkDuplicateMember(){
        MemberCreateDto member1 = new MemberCreateDto();
        member1.setMemberEmail("ssong@naver.com");

        MemberCreateDto member2 = new MemberCreateDto();
        member2.setMemberEmail("ssong@naver.com");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

   }
}
