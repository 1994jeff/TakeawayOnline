package module.base.com.takeawayonline.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.base.BaseActivity;
import module.base.com.takeawayonline.fragment.CommentFragment;
import module.base.com.takeawayonline.fragment.MenuFragment;
import module.base.com.takeawayonline.fragment.OrderFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

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
        switchFragment(new MenuFragment());

        mMenu.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        mComment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu:
                switchFragment(new MenuFragment());
                break;
            case R.id.order:
                switchFragment(new OrderFragment());
                break;
            case R.id.comment:
                switchFragment(new CommentFragment());
                break;
        }
    }
}
