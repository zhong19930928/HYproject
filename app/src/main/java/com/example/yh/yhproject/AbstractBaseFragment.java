package com.example.yh.yhproject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 基础Fragement类
 */
public abstract class AbstractBaseFragment extends Fragment {
    public LinearLayout m_vContentView = null;
    public String title = "";
    private LinearLayout l_content;
    protected LinearLayout right_btn;
    protected LinearLayout top_title_layout;
    public View parentView;
    protected LinearLayout left_ayout;

    /**
     * 设置页面控件事件和状态
     */
    public abstract void beforeInitView();

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInitView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        m_vContentView = (LinearLayout) inflater.inflate(
                R.layout.base_fragment1, null);
        top_title_layout = (LinearLayout) m_vContentView
                .findViewById(R.id.top_title_layout);
        left_ayout = (LinearLayout) m_vContentView
                .findViewById(R.id.title_left_layout);
        OnClickListener cListener = new clickListener();
        left_ayout.setOnClickListener(cListener);
        RelativeLayout leftRelativeLayout = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        leftRelativeLayout.setLayoutParams(params);

        TextView title_tv = (TextView) m_vContentView
                .findViewById(R.id.top_title);
        title_tv.setText(title);
        right_btn = (LinearLayout) m_vContentView
                .findViewById(R.id.title_right_btn);
        l_content = (LinearLayout) m_vContentView
                .findViewById(R.id.main_content);
        return m_vContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setViewStatus();
    }

    class clickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
        }
    }

    // 设置进去id
    public void setContentView(int layout) {
        parentView = LayoutInflater.from(getActivity()).inflate(layout, null);
        if (l_content != null) {
            l_content.removeAllViews();
            l_content.addView(parentView, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        }
    }

    // 设置进去view
    public void setContentViews(View view) {
        parentView = view;
        if (l_content != null) {
            l_content.removeAllViews();
            l_content.addView(parentView, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        }
    }

    public void showRightBtn(View right_view) {
        if (right_btn != null) {
            right_btn.removeAllViews();
            right_btn.addView(right_view);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        onPagePause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nullViewDrawablesRecursive(m_vContentView);
        m_vContentView = null;
        onPageDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        onPageResume();
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

}
