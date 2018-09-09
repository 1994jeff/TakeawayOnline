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
 * 菜单页面
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
        list.add(new Menu("M100001","酸辣凤爪","酸辣爽快，美味佳肴","www.baidu.com",22,"20"));
        list.add(new Menu("M100002","红烧肉","美味的红烧肉","www.baidu.com",15,"20"));
        list.add(new Menu("M100003","红烧排骨","香嫩可口的排骨","www.baidu.com",25,"20"));
        list.add(new Menu("M100004","炖土豆","素食主义者的最爱","www.baidu.com",12,"20"));
        list.add(new Menu("M100005","辣椒炒肉","经典美味，南方烧菜","www.baidu.com",18,"20"));
        list.add(new Menu("M100006","白斩鸡","美味的地方特色菜","www.baidu.com",26,"20"));
        list.add(new Menu("M100007","北京烤鸭","著名的美食北京烤鸭","www.baidu.com",30,"20"));
        list.add(new Menu("M100008","冬瓜浓汤","清热去火的冬瓜汤","www.baidu.com",10,"20"));
        list.add(new Menu("M100009","鸡胸肉","美味的鸡胸肉","www.baidu.com",16,"20"));
        list.add(new Menu("M100001","米饭","香甜可口的东北大米","www.baidu.com",1,"20"));
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
