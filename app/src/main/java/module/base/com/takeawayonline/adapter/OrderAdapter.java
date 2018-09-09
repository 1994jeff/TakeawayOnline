package module.base.com.takeawayonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.bean.CommentStatus;
import module.base.com.takeawayonline.bean.OrderDetails;
import module.base.com.takeawayonline.logic.SystemUtils;

/**
 * Created by jeff on 18-9-8.
 */

public class OrderAdapter extends BaseAdapter {

    List<List<OrderDetails>> list;
    Context mContext;
    CommentStatus commentStatus;

    public OrderAdapter(Context context, List<List<OrderDetails>> list) {
        this.mContext = context;
        this.list = list;
        commentStatus = new CommentStatus(list.size());
    }

    public CommentStatus getCommentStatus(){
        return commentStatus;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_order_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        List<OrderDetails> orderDetails = list.get(position);
        StringBuilder stringBuilder = new StringBuilder();
        int price = 0;
        for (OrderDetails orderDetail: orderDetails){
            stringBuilder.append(orderDetail.getMenuName()).append(orderDetail.getMenuNum())
                    .append("份,");
            price+=Integer.valueOf(orderDetail.getMenuPrice())*Integer.valueOf(orderDetail.getMenuNum());
        }
        commentStatus.des[position] = stringBuilder.toString().substring(0,stringBuilder.length()-1);
        commentStatus.buyNo[position] = orderDetails.get(0).getBuyNo();
        holder.mOrderDetails.setText(commentStatus.des[position]);
        holder.mOrderPrice.setText(price+"");
        holder.mOrderTime.setText(orderDetails.get(0).getCreateTime());
        if(SystemUtils.isIsAdminLogin()){
            holder.mUserName.setVisibility(View.VISIBLE);
            holder.mUserName.setText(orderDetails.get(0).getUserName());
        }else {
            holder.mUserName.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void swapData(List<List<OrderDetails>> listCom) {
        this.list = listCom;
        commentStatus = new CommentStatus(list.size());
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mOrderDetails;
        public TextView mOrderTime;
        public TextView mOrderPrice;
        public TextView mUserName;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mOrderDetails = (TextView) rootView.findViewById(R.id.order_details);
            this.mOrderTime = (TextView) rootView.findViewById(R.id.order_time);
            this.mOrderPrice = (TextView) rootView.findViewById(R.id.order_price);
            this.mUserName = (TextView) rootView.findViewById(R.id.userName);
        }

    }
}
