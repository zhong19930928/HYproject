package com.example.yh.yhproject;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yh
 * on 2017/4/8.
 */
public class Fragment2 extends AbstractBaseFragment {
    private View view;
    private TextView tv;
    @Override
    public void beforeInitView() {

    }

    @Override
    public void setViewStatus() {
        view = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_one, null);
        setContentViews(view);
        tv = (TextView) view.findViewById(R.id.tv_example);
        tv.setText("第二个Fragment");
    }

    @Override
    public void onPageResume() {

    }

    @Override
    public void onPagePause() {

    }

    @Override
    public void onPageDestroy() {

    }
}
