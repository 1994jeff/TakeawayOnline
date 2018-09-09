package module.base.com.takeawayonline.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类
 * 所有的Fragment均继承自BaseFragment
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(),container,false);
        initFragment(view);
        return view;
    }

    //通过抽象方法，继承的fragment必须实现此方法返回fragment的布局资源文件
    protected abstract int getFragmentLayout();

    //通过抽象方法，继承的fragment实例化布局控件
    protected abstract void initFragment(View view);
}
