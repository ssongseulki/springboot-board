package ssong.boardspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ssong.boardspring.repository.MemberRepository;

//수동으로 스프링 빈 등록
@Configuration
public class springConfig {

    //Spring data Jpa
    private final MemberRepository memberRepository;

    @Autowired
    public springConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
