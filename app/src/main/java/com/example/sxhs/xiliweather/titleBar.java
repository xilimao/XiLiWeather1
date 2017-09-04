package com.example.sxhs.xiliweather;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by sxhs on 2017/9/1.
 */

public class titleBar extends RelativeLayout {

    private ImageView img1;
    private ImageView img2;
    private TextView txt1;
    private RelativeLayout layout_titlebar_rootlayout;
    private int mColor = Color.BLUE;
    private int mTextColor = Color.WHITE;
    private float mTextSize;
    private String titlename;

    public titleBar(Context context) {
        super(context);
    }

    public titleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        mColor = mTypedArray.getColor(R.styleable.TitleBar_title_bg, Color.BLUE);
        mTextColor = mTypedArray.getColor(R.styleable.TitleBar_title_text_color, Color.WHITE);
        titlename = mTypedArray.getString(R.styleable.TitleBar_title_text);
        //mTextSize = mTypedArray.getFloat(R.styleable.TitleBar_title_textSize);
        //获取资源后要及时回收
        mTypedArray.recycle();
        initView(context);
    }

    public titleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.attrs_rs, this, true);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        txt1 = (TextView) findViewById(R.id.txt1);
        layout_titlebar_rootlayout = (RelativeLayout) findViewById(R.id.title_attrs);
        layout_titlebar_rootlayout.setBackgroundColor(mColor);
        txt1.setTextColor(mTextColor);
        setTitle(titlename);

    }

    public void setTitle(String titlename) {
        if (!TextUtils.isEmpty(titlename)) {
            txt1.setText(titlename);
        }
    }

    public void setLeftListener(OnClickListener onClickListener) {
        img1.setOnClickListener(onClickListener);
    }

    public void setRightListener(OnClickListener onClickListener) {
        img2.setOnClickListener(onClickListener);
    }
}
