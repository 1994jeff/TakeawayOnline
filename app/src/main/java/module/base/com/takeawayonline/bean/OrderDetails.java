package module.base.com.takeawayonline.bean;

/**
 * Created by jeff on 18-9-8.
 */

public class OrderDetails {

    private String orderDetailsNo;
    private String buyNo;
    private String menuNo;
    private String menuNum;
    private String menuPrice;
    private String menuName;
    private String menuPic;
    private String createTime;
    private String userNo;
    private String userName;
    private String comment;

    public OrderDetails(String orderDetailsNo, String buyNo, String menuNo, String menuNum, String menuPrice, String menuName, String menuPic) {
        this.orderDetailsNo = orderDetailsNo;
        this.buyNo = buyNo;
        this.menuNo = menuNo;
        this.menuNum = menuNum;
        this.menuPrice = menuPrice;
        this.menuName = menuName;
        this.menuPic = menuPic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OrderDetails() {
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

    public String getOrderDetailsNo() {
        return orderDetailsNo;
    }

    public void setOrderDetailsNo(String orderDetailsNo) {
        this.orderDetailsNo = orderDetailsNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBuyNo() {
        return buyNo;
    }

    public void setBuyNo(String buyNo) {
        this.buyNo = buyNo;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public String getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(String menuNum) {
        this.menuNum = menuNum;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPic() {
        return menuPic;
    }

    public void setMenuPic(String menuPic) {
        this.menuPic = menuPic;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsNo='" + orderDetailsNo + '\'' +
                ", buyNo='" + buyNo + '\'' +
                ", menuNo='" + menuNo + '\'' +
                ", menuNum='" + menuNum + '\'' +
                ", menuPrice='" + menuPrice + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuPic='" + menuPic + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userNo='" + userNo + '\'' +
                ", userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
