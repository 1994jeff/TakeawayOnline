package module.base.com.takeawayonline.activity;

import android.os.Bundle;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
