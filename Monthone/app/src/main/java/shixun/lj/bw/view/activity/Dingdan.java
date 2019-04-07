package shixun.lj.bw.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Address;
import shixun.lj.bw.mvp.Bean.Chuangjian;
import shixun.lj.bw.mvp.Bean.Xiang;
import shixun.lj.bw.mvp.adapter.Dingdanadapter;
import shixun.lj.bw.mvp.adapter.Mydingdan;
import shixun.lj.bw.mvp.inteface.Addressinterface;
import shixun.lj.bw.mvp.inteface.Chuangjiandingdan;
import shixun.lj.bw.mvp.inteface.Xinagqing;
import shixun.lj.bw.mvp.presenter.Addrsspresenter;
import shixun.lj.bw.mvp.presenter.Chuangjianpresenter;
import shixun.lj.bw.mvp.presenter.Xpresenter;

public class Dingdan extends AppCompatActivity implements Addressinterface.Addressview, Xinagqing.Xview, Chuangjiandingdan.chuangview {
    private Xpresenter xpresenter;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private Addrsspresenter addrsspresenter;
    private Handler handler = new Handler();
    private int userId;
    private String sessionId;
    private TextView money;
    private int sum1;
    private int sid;
    private Map<String, String> map = new HashMap<>();
    private Button tijiao;
    private List<Chuangjian> list;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        recyclerView1 = findViewById(R.id.ding1);
        recyclerView2 = findViewById(R.id.ding2);
        tijiao = findViewById(R.id.tijiao);
        money = findViewById(R.id.money);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Dingdan.this);
        recyclerView1.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(Dingdan.this);
        recyclerView2.setLayoutManager(linearLayoutManager1);
        SharedPreferences sharedPreferences = getSharedPreferences("c", Xiangqing.MODE_PRIVATE);
        sessionId = sharedPreferences.getString("sessionId", "");
        userId = sharedPreferences.getInt("userId", 20);
        addrsspresenter = new Addrsspresenter();
        addrsspresenter.addressAttachview(this);
        addrsspresenter.addressQequest(userId + "", sessionId);

        //商品
        final Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
        xpresenter = new Xpresenter();
        xpresenter.attachview(Dingdan.this);
        xpresenter.getdata(id);
        Chuangjiandingtan();


    }


    //地址返回的数据
    @Override
    public void Addressview(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("qqqqqqq", "run: " + data);
                Gson gson = new Gson();
                Address address = gson.fromJson(data, Address.class);
                List<Address.ResultBean> result = address.getResult();
                sid = result.get(0).getId();
                Mydingdan mydingdan = new Mydingdan(result, Dingdan.this);
                recyclerView1.setAdapter(mydingdan);
            }
        });
    }

    //商品返回的数据
    @Override
    public void Xview(final String data) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Xiang xiang = gson.fromJson(data, Xiang.class);
                Xiang.ResultBean result = xiang.getResult();
                ArrayList<Xiang.ResultBean> list = new ArrayList<Xiang.ResultBean>();
                list.add(result);
                Dingdanadapter dingdanadapter = new Dingdanadapter(list, Dingdan.this);
                dingdanadapter.setOnclick(new Dingdanadapter.onclick() {


                    @Override
                    public void onclick(int sum) {
                        sum1 = sum;

                        money.setText("共2件商品,需付款" + sum1 + "元");
                    }
                });
                recyclerView2.setAdapter(dingdanadapter);
            }
        });

    }

    //创建订单
    private void Chuangjiandingtan() {
        tijiao.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //创建订单
                Chuangjianpresenter chuangjianpresenter = new Chuangjianpresenter();
                chuangjianpresenter.attachview(Dingdan.this);
                Gson gson = new Gson();
                list = new ArrayList<>();
                s = gson.toJson(list);
                map.put("addressId", sid + "");
                map.put("orderInfo", s);
                map.put("totalPrice", sum1 + "");
                chuangjianpresenter.getData(userId + "", sessionId, map);

            }
        });
    }

    @Override
    public void chuangview(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Dingdan.this, "data" + data, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
