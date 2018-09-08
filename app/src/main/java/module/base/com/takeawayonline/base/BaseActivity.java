package module.base.com.takeawayonline.base;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by jfdeng on 18-9-4.
 * email: jfdeng@grandstream.cn.
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
}
