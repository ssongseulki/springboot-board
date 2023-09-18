package ssong.boardspring.dto;

import com.sun.istack.NotNull;

public class BoardUpdateDto {
    @NotNull
    private long id;
    @NotNull
    private String boardTitle;
    @NotNull
    private String boardContent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

}
