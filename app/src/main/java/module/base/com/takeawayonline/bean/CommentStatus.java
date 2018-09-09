package module.base.com.takeawayonline.bean;

/**
 * Created by jeff on 18-9-9.
 */

public class CommentStatus {

    public String des[];//描述
    public String buyNo[];

    public CommentStatus(int size){
        des = new String[size];
        buyNo = new String[size];
    }
}
