package module.base.com.takeawayonline.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.activity.MainActivity;
import module.base.com.takeawayonline.adapter.MenuAdapter;
import module.base.com.takeawayonline.base.BaseFragment;
import module.base.com.takeawayonline.bean.BuyStatus;
import module.base.com.takeawayonline.bean.Menu;
import module.base.com.takeawayonline.logic.SystemUtils;

/**
 * Created by jeff on 18-9-8.
 */

public class MenuFragment extends BaseFragment implements MenuAdapter.PriceChangeListener {

    private ListView mMenuListView;
    private TextView mPrice;
    private TextView mBuy;
    private MenuAdapter menuAdapter;
    LinearLayout containerBuy;
    BuyStatus buyStatus;

    @Override
    protected int getFragmentLayout() {
        return R.layout.menu_layout;
    }

    @Override
    protected void initFragment(View view) {
        mMenuListView = view.findViewById(R.id.menuListView);
        mPrice = view.findViewById(R.id.price);
        mBuy = view.findViewById(R.id.buy);
        containerBuy = view.findViewById(R.id.container_buy);

        menuAdapter = new MenuAdapter(getActivity(),getMenuData());
        menuAdapter.setPriceChangeListener(this);
        mMenuListView.setAdapter(menuAdapter);

        mBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buyStatus!=null){
                    boolean flag = SystemUtils.createOrderDetails(getActivity(),buyStatus,menuAdapter.getData());
                    if(flag){
                        ((MainActivity)getActivity()).showToastShort("结算成功");
                        menuAdapter.clearStatus();
                        containerBuy.setVisibility(View.GONE);
                    }else {
                        ((MainActivity)getActivity()).showToastShort("结算失败");
                    }
                }else {
                    ((MainActivity)getActivity()).showToastShort("请先下单再结算");
                }
            }
        });
    }

    private List<Menu> getMenuData() {
        List<Menu> list = new ArrayList<>();
        list.add(new Menu("M100001","凤爪","美味的红烧肉","www.baidu.com",22,"20"));
        list.add(new Menu("M100001","红烧肉","美味的红烧肉","www.baidu.com",15,"20"));
        list.add(new Menu("M100001","红烧排骨","美味的红烧肉","www.baidu.com",25,"20"));
        list.add(new Menu("M100001","炖土豆","美味的红烧肉","www.baidu.com",12,"20"));
        list.add(new Menu("M100001","辣椒炒肉","美味的红烧肉","www.baidu.com",18,"20"));
        list.add(new Menu("M100001","白斩鸡","美味的红烧肉","www.baidu.com",26,"20"));
        list.add(new Menu("M100001","北京烤鸭","美味的红烧肉","www.baidu.com",30,"20"));
        list.add(new Menu("M100001","冬瓜浓汤","美味的红烧肉","www.baidu.com",10,"20"));
        list.add(new Menu("M100001","鸡胸肉","美味的红烧肉","www.baidu.com",16,"20"));
        list.add(new Menu("M100001","烧饼","美味的红烧肉","www.baidu.com",5,"20"));
        list.add(new Menu("M100001","米饭","美味的红烧肉","www.baidu.com",1,"20"));
        return list;
    }

    @Override
    public void onPriceChange(int price, BuyStatus buyStatus) {
        mPrice.setText("总价:¥"+price);
        if(price==0){
            containerBuy.setVisibility(View.GONE);
        }else {
            containerBuy.setVisibility(View.VISIBLE);
        }
        this.buyStatus = buyStatus;
    }
}
