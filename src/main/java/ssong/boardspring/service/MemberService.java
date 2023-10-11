package ssong.boardspring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public boolean join(MemberCreateDto memberCreateDto) {
        memberCreateDto.setMemberPw(bCryptPasswordEncoder.encode(memberCreateDto.getMemberPw()));
        boolean isDuplicate = checkDuplicateMember(memberCreateDto.toEntity());
        if (isDuplicate) {
            return false;
        } else {
            memberRepository.save(memberCreateDto.toEntity());
            return true;
        }

    }

    //중복회원 검증
    public boolean checkDuplicateMember(Member member) {
        return memberRepository.findBymemberEmail(member.getMemberEmail()).isPresent();
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //전체 회원 조회 For Paging
    public Page<Member> findMembersForPaging(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id").descending());
        return memberRepository.findAll(pageable);
    }

    //ID로 단일 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //Email로 회원 정보 조회
    public Member findByMemberEmail(String memberEmail) {
        Optional<Member> optionalMember = memberRepository.findBymemberEmail(memberEmail);
        if (optionalMember.isPresent()) {
            return optionalMember.get();
        }
        return null;
    }
}
