package ssong.boardspring.dto;

import com.sun.istack.NotNull;

public class BoardDto {
    @NotNull
    private long id;
    @NotNull
    private String boardTitle;
    @NotNull
    private String boardContent;
    @NotNull
    private long memberId;
    private String fileName;
    private String s3fileName;
    private String filePath;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getS3fileName() {
        return s3fileName;
    }

    public void setS3fileName(String s3fileName) {
        this.s3fileName = s3fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
