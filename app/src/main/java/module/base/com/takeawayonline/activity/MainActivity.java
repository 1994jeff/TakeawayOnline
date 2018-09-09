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
import module.base.com.takeawayonline.logic.SystemUtils;

/**
 * 主页面，承载fragment页面内容
 */
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

        //管理员登录则隐藏菜单页面，只显示订单和评论
        if(!SystemUtils.isIsAdminLogin()){
            //默认第一个页面切换到菜单
            switchFragment(new MenuFragment());
        }else {
            //默认第一个页面切换到订单
            switchFragment(new OrderFragment());
            mMenu.setVisibility(View.GONE);
            //管理员登录，修改底部菜单栏目显示的文字如下
            mOrder.setText("所有订单");
            mComment.setText("所有评论");
        }

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
