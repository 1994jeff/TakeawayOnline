package module.base.com.takeawayonline.bean;

/**
 * Created by jeff on 18-9-8.
 */

public class Order {

    private String orderNo;
    private String userNo;
    private String menuNO;
    private int orderNum;
    private int orderPrce;
    private String createTime;

    public Order(String orderNo, String userNo, String menuNO, int orderNum, int orderPrce) {
        this.orderNo = orderNo;
        this.userNo = userNo;
        this.menuNO = menuNO;
        this.orderNum = orderNum;
        this.orderPrce = orderPrce;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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

    public String getMenuNO() {
        return menuNO;
    }

    public void setMenuNO(String menuNO) {
        this.menuNO = menuNO;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderPrce() {
        return orderPrce;
    }

    public void setOrderPrce(int orderPrce) {
        this.orderPrce = orderPrce;
    }
}
