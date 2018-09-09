package module.base.com.takeawayonline.base;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import module.base.com.takeawayonline.R;

/**
 * activity基类
 * 所有的activity均继承自BaseActivity
 */
public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showToastShort(int res){
        Toast.makeText(this,getString(res),Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(int res){
        Toast.makeText(this,getString(res),Toast.LENGTH_LONG).show();
    }

    public void showToastShort(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    /**
     * fragment切换
     * @param fragment 要切换到的fragment
     */
    public void switchFragment(BaseFragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
