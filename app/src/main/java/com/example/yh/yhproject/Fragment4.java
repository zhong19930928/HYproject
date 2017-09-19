package com.example.yh.yhproject;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yh
 * on 2017/4/8.
 */
public class Fragment4 extends AbstractBaseFragment {
    private TextView tv;
    private View view;
    @Override
    public void beforeInitView() {

    }

    @Override
    public void setViewStatus() {
        view = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_one, null);
        setContentViews(view);
        tv = (TextView) view.findViewById(R.id.tv_example);
        tv.setText("第四个Fragment");
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
