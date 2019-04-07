package shixun.lj.bw.view.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import shixun.lj.bw.R;


/*
  name:刘江
  data:2019
*/public class MyLinershou extends LinearLayout implements View.OnClickListener {


    public interface onclick1 {
        void showdata1();

        void showdata2();

        void showdata3();
    }

    onclick1 onclick;


    public void setOnclick(onclick1 onclick) {
        this.onclick = onclick;
    }

    public MyLinershou(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context, attrs);
    }


    public MyLinershou(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initview(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.mylinershou, null, false);
        addView(view);
        view.findViewById(R.id.stext1).setOnClickListener(this);
        view.findViewById(R.id.stext2).setOnClickListener(this);
        view.findViewById(R.id.simage1).setOnClickListener(this);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stext2:
                onclick.showdata1();
                break;
            case R.id.simage1:
                onclick.showdata2();
                break;
            case R.id.stext1:
                onclick.showdata3();
                break;
        }

    }
}
