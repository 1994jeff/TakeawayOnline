package module.base.com.takeawayonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.bean.BuyStatus;
import module.base.com.takeawayonline.bean.Menu;

/**
 * Created by jeff on 18-9-8.
 */

public class MenuAdapter extends BaseAdapter{

    List<Menu> list;
    Context mContext;
    private int priceAll;

    BuyStatus buyStatus;

    public List<Menu> getData() {
        return list;
    }

    public interface PriceChangeListener{
        void onPriceChange(int price,BuyStatus buyStatus);
    }

    PriceChangeListener priceChangeListener;

    public PriceChangeListener getPriceChangeListener() {
        return priceChangeListener;
    }

    public void setPriceChangeListener(PriceChangeListener priceChangeListener) {
        this.priceChangeListener = priceChangeListener;
    }

    public int getPriceAll() {
        return priceAll;
    }

    public void setPriceAll(int priceAll) {
        this.priceAll = priceAll;
        if(priceChangeListener!=null){
            priceChangeListener.onPriceChange(priceAll,buyStatus);
        }
    }

    public MenuAdapter(Context context, List<Menu> list) {
        this.list = list;
        this.mContext = context;
        buyStatus = new BuyStatus(list.size());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_menu, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Menu menu = list.get(position);
        final ViewHolder currentHolder = holder;
        final Menu currentMenu = menu;
        holder.mMenuName.setText(menu.getVegetTableName());
        holder.mMenuDes.setText(menu.getVegetTableDes());
        holder.mPrice.setText("¥"+menu.getVegetTablePirce()+"");
        holder.mIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.valueOf(currentHolder.mNum.getText().toString()) +1;
                if(num>0){
                    currentHolder.mDecrease.setVisibility(View.VISIBLE);
                    currentHolder.mNum.setVisibility(View.VISIBLE);
                    currentHolder.mNum.setText(num+"");
                    buyStatus.showFlag[position] = true;//记录显示状态
                }else {
                    buyStatus.showFlag[position] = false;//记录显示状态
                }
                buyStatus.nums[position] = num;
                setPriceAll(getPriceAll()+currentMenu.getVegetTablePirce());
            }
        });
        holder.mDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.valueOf(currentHolder.mNum.getText().toString()) -1;
                if(num<=0){
                    currentHolder.mDecrease.setVisibility(View.GONE);
                    currentHolder.mNum.setVisibility(View.GONE);
                    currentHolder.mNum.setText("0");
                    buyStatus.showFlag[position] = false;//记录显示状态
                    num = 0;
                }else{
                    buyStatus.showFlag[position] = true;//记录显示状态
                    currentHolder.mDecrease.setVisibility(View.VISIBLE);
                    currentHolder.mNum.setVisibility(View.VISIBLE);
                    currentHolder.mNum.setText(num+"");
                }
                buyStatus.nums[position] = num;
                setPriceAll(getPriceAll()-currentMenu.getVegetTablePirce());
            }
        });

        if (buyStatus.showFlag[position]){
            currentHolder.mDecrease.setVisibility(View.VISIBLE);
            currentHolder.mNum.setVisibility(View.VISIBLE);
            currentHolder.mNum.setText(buyStatus.nums[position]+"");
        }else {
            currentHolder.mDecrease.setVisibility(View.GONE);
            currentHolder.mNum.setVisibility(View.GONE);
            currentHolder.mNum.setText("0");
        }
        return convertView;
    }

    private void update(ViewHolder currentHolder,Menu currentMenu) {

    }

    public static class ViewHolder {
        public View rootView;
        public ImageView mImg;
        public TextView mMenuName;
        public TextView mMenuDes;
        public TextView mPrice;
        public ImageView mDecrease;
        public TextView mNum;
        public ImageView mIncrease;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mImg = (ImageView) rootView.findViewById(R.id.img);
            this.mMenuName = (TextView) rootView.findViewById(R.id.menuName);
            this.mMenuDes = (TextView) rootView.findViewById(R.id.menuDes);
            this.mPrice = (TextView) rootView.findViewById(R.id.price);
            this.mDecrease = (ImageView) rootView.findViewById(R.id.decrease);
            this.mNum = (TextView) rootView.findViewById(R.id.num);
            this.mIncrease = (ImageView) rootView.findViewById(R.id.increase);
        }

    }
}
