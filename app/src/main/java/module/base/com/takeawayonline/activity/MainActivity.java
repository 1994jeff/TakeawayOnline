package module.base.com.takeawayonline.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView mMenu;
    private TextView mOrder;
    private TextView mComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMenu = (TextView) findViewById(R.id.menu);
        mOrder = (TextView) findViewById(R.id.order);
        mComment = (TextView) findViewById(R.id.comment);

    }
}
