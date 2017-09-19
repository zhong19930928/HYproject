package com.example.yh.yhproject;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import core.ApiRequestFactory;
import core.ApiRequestMethods;
import core.ApiUrl;
import core.ToastUtils;
import okhttp3.Call;

/**
 * Created by yh
 * on 2017/4/8.
 */
public class Fragment1 extends AbstractBaseFragment {
    private View view;
    private TextView tv_example;
    @Override
    public void beforeInitView() {

    }

    @Override
    public void setViewStatus() {
        view = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_one, null);
        setContentViews(view);
        tv_example = (TextView) view.findViewById(R.id.tv_example);
        TestGetData();
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

    /**
     *@method 异步请求数据例子
     *@author suhu
     *@time 2017/4/8 15:09
     *@param
     *
     */
    private void TestGetData(){
        ApiRequestMethods.getData(getActivity(), ApiUrl.TEST, 0, 1, 20, new ApiRequestFactory.HttpCallBackListener() {
            @Override
            public void onSuccess(String response,String url,int id) {
                tv_example.setText(response);
            }
            @Override
            public void failure(Call call, Exception e, int id) {
                ToastUtils.disPlayShortCenter(getActivity(),"0000000");
            }
        }, true);

    }
}
