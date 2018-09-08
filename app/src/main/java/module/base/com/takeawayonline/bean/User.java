package module.base.com.takeawayonline.bean;

/**
 * Created by jeff on 18-9-8.
 */

public class User {

    private String userNo;
    private String userName;
    private String userPsd;
    private String userEmail;

    public User(String userName, String userPsd, String userEmail) {
        this.userName = userName;
        this.userPsd = userPsd;
        this.userEmail = userEmail;
    }

    public User(String userNo, String userName, String userPsd, String userEmail) {
        this.userNo = userNo;
        this.userName = userName;
        this.userPsd = userPsd;
        this.userEmail = userEmail;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
