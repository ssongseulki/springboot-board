package ssong.boardspring.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "member")
    private List<Board> boardList;

    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberPw;

    public Member(String memberEmail, String memberPw, String memberPhone, String memberName) {
        this.memberEmail = memberEmail;
        this.memberPw = memberPw;
        this.memberPhone = memberPhone;
        this.memberName = memberName;
    }

    public Member() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public String getMemberPw() {
        return memberPw;
    }

}
