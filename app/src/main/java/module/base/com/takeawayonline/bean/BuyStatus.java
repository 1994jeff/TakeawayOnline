package module.base.com.takeawayonline.bean;

/**
 * Created by jeff on 18-9-8.
 */

public class BuyStatus {

    public boolean showFlag[];
    public int nums[];

    public BuyStatus(int size){
        showFlag = new boolean[size];
        nums = new int[size];
    }
}
