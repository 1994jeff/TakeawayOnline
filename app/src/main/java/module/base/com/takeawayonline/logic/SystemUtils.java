package module.base.com.takeawayonline.logic;

import android.content.Context;

import module.base.com.takeawayonline.activity.RegisterActivity;

/**
 * Created by jeff on 18-9-8.
 */

public class SystemUtils {

    private static boolean isAdminLogin;

    public static boolean isIsAdminLogin() {
        return isAdminLogin;
    }

    public static boolean login(Context context, String userNameString, String userPsdString) {
        return true;
    }

    public static boolean register(Context context, String userNameString, String userPsdString, String userMailString) {
        return true;
    }

    public static boolean forgetPsd(Context context, String psdString, String userMailString,String userNameString) {
        return true;
    }


}