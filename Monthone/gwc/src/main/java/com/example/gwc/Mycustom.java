package com.example.gwc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*Time:2019/4/3
 *Author:刘江
 *Description:
 */public class Mycustom extends LinearLayout {
    private Button add;
    private Button reduce;
    private TextView textView;

    public Mycustom(Context context) {
        super(context);
    }

    public Mycustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
        initLisenter();
    }


    public Mycustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void Init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.mycustom, this, false);
        add = view.findViewById(R.id.plus);
        reduce = view.findViewById(R.id.reduce);
        textView = view.findViewById(R.id.text);
        addView(view);
    }

    //
    private void initLisenter() {
        //减
        reduce.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reduce();
            }
        });
        //加
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                jia();
            }
        });

    }

    //减
    public void reduce() {
        String old = textView.getText().toString();
        int newNub = 0;
        if (old.isEmpty()) {
            newNub = Integer.parseInt(old);
            newNub--;
        }
        if (newNub <0) {
            newNub = 0;
            textView.setText(newNub + "");
            Toast.makeText(getContext(), "不能再少了", Toast.LENGTH_SHORT).show();
        } else {
            textView.setText(newNub + "");
            if (onNumChangedListener != null) {
                onNumChangedListener.onNumChanged(Integer.parseInt(old), newNub);
            }
        }
    }

    //加
    public void jia() {
        String sum = textView.getText().toString();
        int newsum = 0;
        if (!sum.isEmpty()) {
            newsum = Integer.parseInt(sum);
            newsum++;
        }
        textView.setText(newsum + "");
        if (onNumChangedListener != null) {
            onNumChangedListener.onNumChanged(Integer.parseInt(sum), newsum);
        }
    }


    //传值的接口回调
    interface OnNumChangedListener {
        void onNumChanged(int old, int newNub);
    }

    OnNumChangedListener onNumChangedListener;

    public void setOnNumChangedsListener(OnNumChangedListener onNumChangedListener) {
        this.onNumChangedListener = onNumChangedListener;
    }
}
