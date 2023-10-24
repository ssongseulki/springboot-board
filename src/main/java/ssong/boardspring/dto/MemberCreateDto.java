package ssong.boardspring.dto;

import com.sun.istack.NotNull;
import ssong.boardspring.domain.Member;

import javax.validation.constraints.Email;

public class MemberCreateDto {

    @NotNull
    private String memberName;
    @NotNull
    @Email(message = "형식이 맞는 이메일 주소를 입력하세요.")
    private String memberEmail;
    private String memberPhone;
    @NotNull
    private String memberPw;

    public MemberCreateDto() {
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public Member toEntity() {
        return new Member(memberEmail, memberPw, memberPhone, memberName);
    }
}
