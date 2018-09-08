package module.base.com.takeawayonline.activity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.base.BaseActivity;
import module.base.com.takeawayonline.data.MyDataBase;
import module.base.com.takeawayonline.logic.DatabaseUtil;
import module.base.com.takeawayonline.logic.SystemUtils;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUserName;
    private EditText mUserPsd;
    private EditText mUserMail;
    private Button mBack;
    private Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mUserName = (EditText) findViewById(R.id.userName);
        mUserPsd = (EditText) findViewById(R.id.userPsd);
        mUserMail = (EditText) findViewById(R.id.userMail);
        mBack = (Button) findViewById(R.id.back);
        mRegister = (Button) findViewById(R.id.register);

        mBack.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.register:
                submit();
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

        String userMailString = mUserMail.getText().toString().trim();
        if (TextUtils.isEmpty(userMailString)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        boolean flag = SystemUtils.register(this,userNameString,userPsdString,userMailString);
        if(flag){
            showToastShort("注册成功");
        }else {
            showToastShort("注册失败，用户已存在");
        }
    }

}
