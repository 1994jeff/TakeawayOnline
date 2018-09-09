package module.base.com.takeawayonline.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import module.base.com.takeawayonline.R;
import module.base.com.takeawayonline.adapter.CommentAdapter;
import module.base.com.takeawayonline.base.BaseFragment;
import module.base.com.takeawayonline.bean.Comment;
import module.base.com.takeawayonline.logic.SystemUtils;

/**
 * Created by jeff on 18-9-8.
 */

public class CommentFragment extends BaseFragment {

    private ListView mCommentListView;
    private CommentAdapter adapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.comment_fragment;
    }

    @Override
    protected void initFragment(View view) {
        mCommentListView = view.findViewById(R.id.commentListView);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Comment> list = SystemUtils.getCommentByUser(!SystemUtils.isIsAdminLogin());
        if(list==null||list.size()<=0){
            list = new ArrayList<>();
        }
        adapter = new CommentAdapter(getActivity(),list);
        mCommentListView.setAdapter(adapter);
    }
}
