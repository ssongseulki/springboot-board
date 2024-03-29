package ssong.boardspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ssong.boardspring.domain.Member;
import ssong.boardspring.dto.MemberCreateDto;
import ssong.boardspring.service.MemberService;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "member/createMemberForm";
    }

    //      회원 가입
    @PostMapping("/signUp")
    public String createMember(@Validated @ModelAttribute("memberCreateDto") MemberCreateDto memberCreateDto, Model model) {
        boolean checkSave = memberService.join(memberCreateDto);
        if (checkSave) {
            model.addAttribute("msg", "가입되었습니다.");
            return "/login";
        } else {
            model.addAttribute("msg", "이미 등록된 이메일입니다.");
            return "member/createMemberForm";
        }

    }

    //    전체 회원 조회
    @GetMapping("")
    public String getMembers(Model model) {
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("memberList", memberList);
        return "member/memberList";
    }

    //    전체 회원 조회 For Paging
    @GetMapping("/paging")
    public String membersForPaging(Model model, @RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;

        Page<Member> memberList = memberService.findMembersForPaging(page, pageSize);
        model.addAttribute("memberList", memberList);
        return "member/memberList";
    }

}
