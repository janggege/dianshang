package com.example.qiyue;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qiyue.bean.Tou;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contractclass.Dataview {

    private Presenterttwo presenterttwo;
    private Handler handler = new Handler();
    private XBanner xBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xBanner = findViewById(R.id.xbanner);
        presenterttwo = new Presenterttwo();
        presenterttwo.attachView(this);
        presenterttwo.requestData();
    }


    @Override
    public void showBannnerDada(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Tou tou = gson.fromJson(data, Tou.class);
                final List<Tou.ResultBean> result = tou.getResult();
                //集合设置给xbanner
                xBanner.setData(result, null);
                //加载图片
                xBanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(MainActivity.this).load(result.get(position).getImageUrl()).into((ImageView) view);
                        //延迟时间
                        banner.setPageChangeDuration(2000);
                    }
                });
                //跳转


            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterttwo.destory();
    }
}
