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
 * 订单页面
 */

public class OrderFragment extends BaseFragment {

    private ListView mListComment;//已评论list
    private LinearLayout mCommentContainer;
    private ListView mListUncomment;//未评论list
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
        //获取对应的订单数据信息
        listCom =  SystemUtils.getUserCommentOrderDetails(getActivity(),!SystemUtils.isIsAdminLogin());//已评论
        listUnCom =  SystemUtils.getUserUnCommentOrderDetails(getActivity(),!SystemUtils.isIsAdminLogin());//未评论

        //初始化适配器
        if(listCom==null || listCom.size()<=0){
            listCom = new ArrayList<>();
            orderAdapterCom = new OrderAdapter(getActivity(),listCom);
        }else {
            orderAdapterCom = new OrderAdapter(getActivity(),listCom);
        }
        //绑定适配器
        mListComment.setAdapter(orderAdapterCom);

        //初始化适配器
        if(listUnCom==null || listUnCom.size()<=0){
            listUnCom = new ArrayList<>();
            orderAdapterUnCom = new OrderAdapter(getActivity(),listUnCom);
        }else {
            orderAdapterUnCom = new OrderAdapter(getActivity(),listUnCom);
        }
        //绑定适配器
        mListUncomment.setAdapter(orderAdapterUnCom);

        //设置未评论listview的点击事件
        mListUncomment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //不是admin登录则弹出评论对话框，否则提示没有权限评论
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
                //得到对应的buyNo后调用封装的方法评论（评论做的操作有二，一是更新订单信息表的是否评论字段为yes，二是插入一条评论信息到评论表）
                boolean flag = SystemUtils.comment(buyNo,editText.getText().toString(),des);
                if(flag){
                    ((BaseActivity)getActivity()).showToastShort("评论成功");
                    if(listUnCom.size()>=position)
                    {
                        listUnCom.remove(position);
                        //刷新页面list
                        orderAdapterUnCom.notifyDataSetChanged();
                    }
                    listCom.add(orderDetails);
                    //刷新页面list
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
