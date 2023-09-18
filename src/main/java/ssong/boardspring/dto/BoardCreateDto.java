package ssong.boardspring.dto;

import com.sun.istack.NotNull;

public class BoardCreateDto {

    @NotNull
    private String boardTitle;
    @NotNull
    private String boardContent;
    @NotNull
    private long memberId;

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public void setMemberId(long memberIdx) {
        this.memberId = memberIdx;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public long getMemberId() {
        return memberId;
    }
}
