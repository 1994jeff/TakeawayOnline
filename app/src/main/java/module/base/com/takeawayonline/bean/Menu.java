package module.base.com.takeawayonline.bean;

/**
 * Created by jeff on 18-9-8.
 */

public class Menu {

    private String vegetTableNo;
    private String vegetTableName;
    private String vegetTableDes;
    private String vegetTablePic;
    private int vegetTablePirce;
    private String createTime;

    public Menu(String vegetTableNo, String vegetTableName, String vegetTableDes, String vegetTablePic, int vegetTablePirce, String createTime) {
        this.vegetTableNo = vegetTableNo;
        this.vegetTableName = vegetTableName;
        this.vegetTableDes = vegetTableDes;
        this.vegetTablePic = vegetTablePic;
        this.vegetTablePirce = vegetTablePirce;
        this.createTime = createTime;
    }

    public String getVegetTableDes() {
        return vegetTableDes;
    }

    public void setVegetTableDes(String vegetTableDes) {
        this.vegetTableDes = vegetTableDes;
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
