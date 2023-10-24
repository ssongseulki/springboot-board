package ssong.boardspring.serviceImpl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssong.boardspring.domain.Member;
import ssong.boardspring.repository.MemberRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String id) {
        Optional<Member> member = memberRepository.findBymemberEmail(id);

        if (member.isPresent()) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("USER")); //USER 라는 역할 부여

            Member foundMember = member.get();
            return new org.springframework.security.core.userdetails.User(
                    foundMember.getMemberEmail(),
                    foundMember.getMemberPw(),
                    grantedAuthorities
            );
        } else {
            return null;
        }
    }

}
