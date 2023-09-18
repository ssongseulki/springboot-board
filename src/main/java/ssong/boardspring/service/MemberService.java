package ssong.boardspring.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssong.boardspring.domain.Member;
import ssong.boardspring.dto.MemberCreateDto;
import ssong.boardspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {

    private MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //회원가입
    public long join(MemberCreateDto memberCreateDto) {
        memberCreateDto.setMemberPw(bCryptPasswordEncoder.encode(memberCreateDto.getMemberPw()));
        checkDuplicateMember(memberCreateDto.toEntity());
        memberRepository.save(memberCreateDto.toEntity());
        return memberCreateDto.toEntity().getId();
    }

    //중복회원 검증
    public void checkDuplicateMember(Member member) {
        memberRepository.findBymemberEmail(member.getMemberEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //ID로 단일 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //Email로 회원 정보 조회
    public Member findByMemberEmail(String memberEmail) {
        Optional<Member> optionalMember = memberRepository.findBymemberEmail(memberEmail);
        if(optionalMember.isPresent()){
            return optionalMember.get();
        }
        return null;
    }
}
