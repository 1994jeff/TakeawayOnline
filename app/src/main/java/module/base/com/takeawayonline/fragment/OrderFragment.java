package module.base.com.takeawayonline.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import module.base.com.takeawayonline.R;
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

        List<OrderDetails> list =  SystemUtils.getOrderDetails(getActivity());
    }

}
