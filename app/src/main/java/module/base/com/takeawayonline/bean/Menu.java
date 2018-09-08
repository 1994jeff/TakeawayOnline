package module.base.com.takeawayonline.bean;

/**
 * Created by jeff on 18-9-8.
 */

public class Menu {

    private String vegetTableNo;
    private String vegetTableName;
    private String vegetTablePic;
    private int vegetTablePirce;
    private String createTime;

    public Menu(String vegetTableNo, String vegetTableName, String vegetTablePic, int vegetTablePirce) {
        this.vegetTableNo = vegetTableNo;
        this.vegetTableName = vegetTableName;
        this.vegetTablePic = vegetTablePic;
        this.vegetTablePirce = vegetTablePirce;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getVegetTableNo() {
        return vegetTableNo;
    }

    public void setVegetTableNo(String vegetTableNo) {
        this.vegetTableNo = vegetTableNo;
    }

    public String getVegetTableName() {
        return vegetTableName;
    }

    public void setVegetTableName(String vegetTableName) {
        this.vegetTableName = vegetTableName;
    }

    public String getVegetTablePic() {
        return vegetTablePic;
    }

    public void setVegetTablePic(String vegetTablePic) {
        this.vegetTablePic = vegetTablePic;
    }

    public int getVegetTablePirce() {
        return vegetTablePirce;
    }

    public void setVegetTablePirce(int vegetTablePirce) {
        this.vegetTablePirce = vegetTablePirce;
    }
}
