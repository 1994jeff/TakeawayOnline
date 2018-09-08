package module.base.com.takeawayonline.bean;

/**
 * Created by jeff on 18-9-8.
 */

public class Order {

    private String orderNo;
    private String userNo;
    private String buyNo;
    private String createTime;

    public Order(String orderNo, String userNo, String buyNo, String createTime) {
        this.orderNo = orderNo;
        this.userNo = userNo;
        this.buyNo = buyNo;
        this.createTime = createTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getBuyNo() {
        return buyNo;
    }

    public void setBuyNo(String buyNo) {
        this.buyNo = buyNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
