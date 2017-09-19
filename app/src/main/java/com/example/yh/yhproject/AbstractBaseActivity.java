package com.example.yh.yhproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 基础Activity类
 */
public abstract class AbstractBaseActivity extends Activity {
    protected final String TAG = getClass().getSimpleName();
    protected ViewGroup m_vContentView = null;
    protected String title = "";
    protected LinearLayout right_btn;
    protected ImageButton leftButton;
    protected LinearLayout left_ayout;
    protected LinearLayout top_title_layout, img_backhome;
    protected TextView title_tv;
    protected Bundle bundle;

    /**
     * 初始化activity传递的参数
     */
    public abstract void initIntentParam(Intent intent);

    /**
     * 定义页面控件
     */
    public abstract void initView();

    /**
     * 设置页面控件事件和状态
     */
    public abstract void setViewStatus();

    /**
     * == onResume()
     */
    public abstract void onPageResume();

    /**
     * == onPause()
     */
    public abstract void onPagePause();

    /**
     * == onDestroy()
     */
    public abstract void onPageDestroy();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentParam(getIntent());
        setContentView(R.layout.base_fragment);
        bundle = savedInstanceState;
        initView();
        setViewStatus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPagePause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nullViewDrawablesRecursive(m_vContentView);
        leftButton = null;
        m_vContentView = null;
        onPageDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onPageResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void showRightBtn(View right_view) {
        right_btn = (LinearLayout) findViewById(R.id.title_right_btn);
        if (right_btn != null) {
            right_btn.removeAllViews();
            right_btn.addView(right_view);
        }
    }

    public void showBaxkbtn(View right_view) {
        img_backhome = (LinearLayout) findViewById(R.id.liner_backhome);
        if (img_backhome != null) {
            img_backhome.removeAllViews();
            img_backhome.addView(right_view);
        }
    }

    @Override
    public void setContentView(int layout) {
        ViewGroup mainView = (ViewGroup) LayoutInflater.from(this).inflate(
                layout, null);
        setContentView(mainView);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        m_vContentView = (ViewGroup) view;
        top_title_layout = (LinearLayout) m_vContentView
                .findViewById(R.id.top_title_layout);
        title_tv = (TextView) findViewById(R.id.top_title);
        title_tv.setEllipsize(TruncateAt.MARQUEE);
        title_tv.setSingleLine(true);
        title_tv.setMarqueeRepeatLimit(3); // 跑马灯循环次数
        // 获取焦点
        title_tv.setFocusable(true);
        title_tv.setFocusableInTouchMode(true);
        title_tv.requestFocus();
        title_tv.setHorizontallyScrolling(true);
        title_tv.setText(title);
        left_ayout = (LinearLayout) m_vContentView
                .findViewById(R.id.title_left_layout);
        left_ayout.setOnClickListener(new clickListener());
        img_backhome = (LinearLayout) m_vContentView
                .findViewById(R.id.liner_backhome);
//		img_backhome.setOnClickListener(new imgBackclicklister());
        leftButton = new ImageButton(this);
        leftButton.setBackgroundResource(R.drawable.sport_title_back_selector);
        left_ayout.addView(leftButton);
        leftButton.setOnClickListener(new clickListener());
    }

    public void showContentView(int layout) {
        View view = LayoutInflater.from(this).inflate(layout, null);
        LinearLayout m_llContent = (LinearLayout) findViewById(R.id.main_content);
        if (m_llContent != null) {
            m_llContent.removeAllViews();
            m_llContent.addView(view, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        }
    }

    class clickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            finish();
        }
    }

    protected void nullViewDrawablesRecursive(View view) {
        if (view != null) {
            try {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int index = 0; index < childCount; index++) {
                    View child = viewGroup.getChildAt(index);
                    nullViewDrawablesRecursive(child);
                }
            } catch (Exception e) {
            }
            nullViewDrawable(view);
        }
    }

    protected void nullViewDrawable(View view) {
        try {
            view.setBackgroundDrawable(null);
            if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(null);
                imageView.setBackgroundDrawable(null);
            }
        } catch (Exception e) {
        }
    }

    /**
     *@method 固定字体大小
     *@author suhu
     *@time 2016/10/11 11:19
     *
    */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

}
