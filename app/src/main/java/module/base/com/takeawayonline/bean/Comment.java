package module.base.com.takeawayonline.bean;

/**
 * Created by jeff on 18-9-8.
 */

public class Comment {

    private String commentNo;
    private String userNo;
    private String menuNo;
    private String commentContent;
    private String createTime;

    public Comment(String commentNo, String userNo, String menuNo, String commentContent, String createTime) {
        this.commentNo = commentNo;
        this.userNo = userNo;
        this.menuNo = menuNo;
        this.commentContent = commentContent;
        this.createTime = createTime;
    }

    public String getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(String commentNo) {
        this.commentNo = commentNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
