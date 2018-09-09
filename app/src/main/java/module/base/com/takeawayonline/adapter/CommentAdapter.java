package module.base.com.takeawayonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.bean.Comment;

/**
 * Created by jeff on 18-9-9.
 */

public class CommentAdapter extends BaseAdapter {

    Context mContext;
    List<Comment> list;

    public CommentAdapter(Context mContext, List<Comment> list) {
        this.mContext = mContext;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Comment comment = list.get(position);
        holder.mCommentContent.setText(comment.getCommentContent());
        holder.mOrderTime.setText(comment.getCreateTime());
        holder.mOrderDetails.setText(comment.getMenuDes());
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mCommentContent;
        public TextView mOrderTime;
        public TextView mOrderDetails;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mCommentContent = (TextView) rootView.findViewById(R.id.commentContent);
            this.mOrderTime = (TextView) rootView.findViewById(R.id.order_time);
            this.mOrderDetails = (TextView) rootView.findViewById(R.id.order_details);
        }

    }
}
