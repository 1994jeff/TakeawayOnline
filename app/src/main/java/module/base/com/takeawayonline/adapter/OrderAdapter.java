package module.base.com.takeawayonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.bean.OrderDetails;

/**
 * Created by jeff on 18-9-8.
 */

public class OrderAdapter extends BaseAdapter {

    List<List<OrderDetails>> list;
    Context mContext;

    public OrderAdapter(Context context, List<List<OrderDetails>> list) {
        this.mContext = context;
        this.list = list;
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
//        OrderDetails orderDetails = list.get(position);

//        holder.mOrderDetails.setText();
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mOrderDetails;
        public TextView mOrderTime;
        public TextView mOrderPrice;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mOrderDetails = (TextView) rootView.findViewById(R.id.order_details);
            this.mOrderTime = (TextView) rootView.findViewById(R.id.order_time);
            this.mOrderPrice = (TextView) rootView.findViewById(R.id.order_price);
        }

    }
}
