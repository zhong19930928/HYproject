package com.example.yh.yhproject;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private LinearLayout ll_mainactivty_one,ll_mainactivty_two,ll_mainactivty_three,ll_mainactivty_four;
    private ImageView iv_mainactivty_one,iv_mainactivty_two,iv_mainactivty_three,iv_mainactivty_four;
    private TextView tv_mainactivty_one,tv_mainactivty_two,tv_mainactivty_three,tv_mainactivty_four;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mainactivity);
        initView();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }

    private void initView() {
        ll_mainactivty_one = (LinearLayout) findViewById(R.id.ll_mainactivty_one);
        ll_mainactivty_two = (LinearLayout) findViewById(R.id.ll_mainactivty_two);
        ll_mainactivty_three = (LinearLayout) findViewById(R.id.ll_mainactivty_three);
        ll_mainactivty_four = (LinearLayout) findViewById(R.id.ll_mainactivty_four);
        ll_mainactivty_one.setOnClickListener(this);
        ll_mainactivty_two.setOnClickListener(this);
        ll_mainactivty_three.setOnClickListener(this);
        ll_mainactivty_four.setOnClickListener(this);
        iv_mainactivty_one = (ImageView) findViewById(R.id.iv_mainactivty_one);
        iv_mainactivty_two = (ImageView) findViewById(R.id.iv_mainactivty_two);
        iv_mainactivty_three = (ImageView) findViewById(R.id.iv_mainactivty_three);
        iv_mainactivty_four = (ImageView) findViewById(R.id.iv_mainactivty_four);
        tv_mainactivty_one = (TextView) findViewById(R.id.tv_mainactivty_one);
        tv_mainactivty_two = (TextView) findViewById(R.id.tv_mainactivty_two);
        tv_mainactivty_three = (TextView) findViewById(R.id.tv_mainactivty_three);
        tv_mainactivty_four = (TextView) findViewById(R.id.tv_mainactivty_four);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ll_mainactivty_one:
                setTabSelection(0);
                break;
            case R.id.ll_mainactivty_two:
                setTabSelection(1);
                break;
            case R.id.ll_mainactivty_three:
                setTabSelection(2);
                break;
            case R.id.ll_mainactivty_four:
                setTabSelection(3);
                break;

            default:
                break;
        }
    }

    public void setTabSelection(int tabSelection){
        // 重置按钮
        resetBtn();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (tabSelection)
        {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                iv_mainactivty_one.setImageResource(R.drawable.sk_startsport_focus);
                if (fragment1 == null)
                {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragment1 = new Fragment1();
                    transaction.add(R.id.main_fragment, fragment1);
                } else
                {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment1);
                }
                break;
            case 1:
                iv_mainactivty_two.setImageResource(R.drawable.sk_trainplay_focus);
                if (fragment2 == null)
                {
                    fragment2 = new Fragment2();
                    transaction.add(R.id.main_fragment, fragment2);
                } else
                {
                    transaction.show(fragment2);
                }
                break;
            case 2:
                iv_mainactivty_three.setImageResource(R.drawable.sk_sportcircle_focus);
                if (fragment3 == null)
                {
                    fragment3 = new Fragment3();
                    transaction.add(R.id.main_fragment, fragment3);
                } else
                {
                    transaction.show(fragment3);
                }
                break;
            case 3:
                iv_mainactivty_four.setImageResource(R.drawable.sk_star_focus);
                if (fragment4 == null)
                {
                    fragment4 = new Fragment4();
                    transaction.add(R.id.main_fragment, fragment4);
                } else
                {
                    transaction.show(fragment4);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (fragment1 != null)
        {
            transaction.hide(fragment1);
        }
        if (fragment2 != null)
        {
            transaction.hide(fragment2);
        }
        if (fragment3 != null)
        {
            transaction.hide(fragment3);
        }
        if (fragment4 != null)
        {
            transaction.hide(fragment4);
        }
    }

    private void resetBtn() {
        iv_mainactivty_one.setImageResource(R.drawable.sk_startsport_normal);
        iv_mainactivty_two.setImageResource(R.drawable.sk_trainplay_normal);
        iv_mainactivty_three.setImageResource(R.drawable.sk_sportcircle_normal);
        iv_mainactivty_four.setImageResource(R.drawable.sk_star_normal);
    }
}
