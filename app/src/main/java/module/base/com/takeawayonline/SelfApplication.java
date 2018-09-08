package module.base.com.takeawayonline;
import android.app.Application;

import module.base.com.takeawayonline.logic.DatabaseUtil;

public class SelfApplication extends Application   {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseUtil.getInstance().createDB(this);
    }
}
