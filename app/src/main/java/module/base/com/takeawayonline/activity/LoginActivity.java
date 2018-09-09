package module.base.com.takeawayonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.base.BaseActivity;
import module.base.com.takeawayonline.logic.CacheUtil;
import module.base.com.takeawayonline.logic.SystemUtils;
/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUserName;
    private EditText mUserPsd;
    private Button mLogin;
    private Button mRegister;
    private TextView mForgetPsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mUserName = (EditText) findViewById(R.id.userName);
        mUserPsd = (EditText) findViewById(R.id.userPsd);
        mLogin = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.register);
        mForgetPsd = (TextView) findViewById(R.id.forgetPsd);

        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mForgetPsd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                submit();
                break;
            case R.id.register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.forgetPsd:
                startActivity(new Intent(this,ForgetActivity.class));
                break;
        }
    }

    private void submit() {
        // validate
        String userNameString = mUserName.getText().toString().trim();
        if (TextUtils.isEmpty(userNameString)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String userPsdString = mUserPsd.getText().toString().trim();
        if (TextUtils.isEmpty(userPsdString)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // 调用登录逻辑
        boolean flag = SystemUtils.login(this,userNameString,userPsdString);

        if(flag){
            if(SystemUtils.isIsAdminLogin()){
                startActivity(new Intent(this,MainActivity.class));
            }else {
                startActivity(new Intent(this,MainActivity.class));
            }
            finish();
        }else {
            showToastShort("用户名或密码错误");
        }
    }
}
