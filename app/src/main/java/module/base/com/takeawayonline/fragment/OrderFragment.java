package module.base.com.takeawayonline.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.adapter.OrderAdapter;
import module.base.com.takeawayonline.base.BaseActivity;
import module.base.com.takeawayonline.base.BaseFragment;
import module.base.com.takeawayonline.bean.OrderDetails;
import module.base.com.takeawayonline.logic.SystemUtils;

/**
 * Created by jeff on 18-9-8.
 */

public class OrderFragment extends BaseFragment {

    private ListView mListComment;
    private LinearLayout mCommentContainer;
    private ListView mListUncomment;
    private LinearLayout mUncommentContainer;

    OrderAdapter orderAdapterCom;
    OrderAdapter orderAdapterUnCom;

    List<List<OrderDetails>> listCom;
    List<List<OrderDetails>> listUnCom;

    @Override
    protected int getFragmentLayout() {
        return R.layout.order_fragment_layout;
    }

    @Override
    protected void initFragment(View view) {

        mListComment = view.findViewById(R.id.list_comment);
        mListUncomment = view.findViewById(R.id.list_uncomment);
        mCommentContainer = view.findViewById(R.id.commentContainer);
        mUncommentContainer = view.findViewById(R.id.uncommentContainer);

    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        listCom =  SystemUtils.getUserCommentOrderDetails(getActivity(),!SystemUtils.isIsAdminLogin());//已评论
        listUnCom =  SystemUtils.getUserUnCommentOrderDetails(getActivity(),!SystemUtils.isIsAdminLogin());//未评论
        if(listCom==null || listCom.size()<=0){
            listCom = new ArrayList<>();
            orderAdapterCom = new OrderAdapter(getActivity(),listCom);
        }else {
            orderAdapterCom = new OrderAdapter(getActivity(),listCom);
        }
        mListComment.setAdapter(orderAdapterCom);

        if(listUnCom==null || listUnCom.size()<=0){
            listUnCom = new ArrayList<>();
            orderAdapterUnCom = new OrderAdapter(getActivity(),listUnCom);
        }else {
            orderAdapterUnCom = new OrderAdapter(getActivity(),listUnCom);
        }

        mListUncomment.setAdapter(orderAdapterUnCom);
        mListUncomment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!SystemUtils.isIsAdminLogin()){
                    showDiaLogCommet(position);
                }else {
                    ((BaseActivity)getActivity()).showToastShort("不能评论他人订单!");
                }
            }
        });
    }

    private void showDiaLogCommet(final int position) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("请输入评价");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout,null);
        final EditText editText = view.findViewById(R.id.commentContent);
        dialogBuilder.setView(view);
        AlertDialog dialog = dialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<OrderDetails> orderDetails = (List<OrderDetails>) orderAdapterUnCom.getItem(position);
                String buyNo = orderAdapterUnCom.getCommentStatus().buyNo[position];
                String des = orderAdapterUnCom.getCommentStatus().des[position];
                boolean flag = SystemUtils.comment(buyNo,editText.getText().toString(),des);
                if(flag){
                    ((BaseActivity)getActivity()).showToastShort("评论成功");
                    if(listUnCom.size()>=position)
                    {
                        listUnCom.remove(position);
                        orderAdapterUnCom.notifyDataSetChanged();
                    }
                    listCom.add(orderDetails);
                    orderAdapterCom.swapData(listCom);
                }else {
                    ((BaseActivity)getActivity()).showToastShort("评论失败");
                }
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).create();
        dialog.show();
    }

}
