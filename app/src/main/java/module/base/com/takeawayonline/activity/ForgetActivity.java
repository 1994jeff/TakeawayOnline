package module.base.com.takeawayonline.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.base.BaseActivity;
import module.base.com.takeawayonline.logic.SystemUtils;

public class ForgetActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUserEmail;
    private EditText mUserPsd;
    private EditText mUserName;
    private Button mReset;
    private Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
    }

    private void initView() {
        mUserEmail = (EditText) findViewById(R.id.userEmail);
        mUserPsd = (EditText) findViewById(R.id.userPsd);
        mReset = (Button) findViewById(R.id.reset);
        mBack = (Button) findViewById(R.id.back);
        mUserName = (EditText) findViewById(R.id.userName);

        mReset.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:

                break;
            case R.id.back:

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

        String userEmailString = mUserEmail.getText().toString().trim();
        if (TextUtils.isEmpty(userEmailString)) {
            Toast.makeText(this, "请输入邮箱号", Toast.LENGTH_SHORT).show();
            return;
        }

        String userPsdString = mUserPsd.getText().toString().trim();
        if (TextUtils.isEmpty(userPsdString)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        boolean flag = SystemUtils.forgetPsd(this,userPsdString,userEmailString,userNameString);
        if(flag){
            showToastShort("重置密码成功，快去登录吧");
        }else {
            showToastShort("用户名邮箱号匹配错误，重置密码失败");
        }

    }
}
