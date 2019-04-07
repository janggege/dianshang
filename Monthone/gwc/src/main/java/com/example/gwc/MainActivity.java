package com.example.gwc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gwc.bean.Bean;
import com.example.gwc.bean.Data;
import com.example.gwc.bean.spus;
import com.example.gwc.myadapter.Myadapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Myadapter myadapter;
    private CheckBox quan;
    private List<Data> data;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String a = (String) msg.obj;
                    Gson gson = new Gson();
                    Bean bean = gson.fromJson(a, Bean.class);
                    data = bean.getData();
                    myadapter = new Myadapter(data, MainActivity.this);
                    recyclerView.setAdapter(myadapter);
                    break;
            }

        }
    };
    private TextView zong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initview();
        InitData();
        quan();
        shang();


    }

    //数据
    private void InitData() {
        String url = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
        Okhttputils.getinstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.what = 1;
                message.obj = string;
                handler.sendMessage(message);

            }
        });
    }


    private void shang() {
        //商家控制全选
        myadapter.setGroupListener(new Myadapter.onGroupListener() {
            @Override
            public void onGroup(List<Data> listData) {
                double priceSum = 0.0;//累计总价
                int num1 = 0;//累计商品数量
                int num2 = 0;//累计商品商品选中数量
                for (int i = 0; i < data.size(); i++) {
                    List<spus> data1 = data.get(i).getSpus();
                    for (int j = 0; j < data1.size(); j++) {
                        num1++;
                        boolean childChecked = data1.get(j).isChecked();
                        if (childChecked) {
                            num2++;
                            String price = data1.get(j).getSkus().get(0).getPrice();
                            int price1 = Integer.parseInt(price);
                            String praise_num = data1.get(j).getPraise_num();
                            int num = Integer.parseInt(praise_num);
                            priceSum += price1 * num;
                        }
                    }
                }
                //判断商品选中数量和商品数量是否相等
                if (num1 == num2) {
                    quan.setChecked(true);
                } else {
                    quan.setChecked(false);
                }
                zong.setText("总价:" + priceSum);
            }
        });


    }

    private void Initview() {
        recyclerView = findViewById(R.id.recy);
        quan = findViewById(R.id.quan);
        zong = findViewById(R.id.zong);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }


    //全选   反选；
    public void quan() {
        getCheckAllOrNot();
    }

    private void getCheckAllOrNot() {

        quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double priceSum = 0.0;//累计总价
                boolean isChecked = ((CheckBox) v).isChecked();
                //判断全选复选框是否选中
                if (isChecked) {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setChecked(true);
                        List<spus> spus = data.get(i).getSpus();

                        for (int j = 0; j < spus.size(); j++) {
                            spus.get(j).setChecked(true);
                            String praise_num = spus.get(j).getPraise_num();
                            int num = Integer.parseInt(praise_num);
                            String price = spus.get(j).getSkus().get(0).getPrice();
                            double v1 = Double.parseDouble(price);

                            priceSum += v1 * num;
                        }
                    }
                    myadapter.notifyDataSetChanged();
                    zong.setText("总价:" + priceSum);
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setChecked(false);
                        List<spus> spus = data.get(i).getSpus();

                        for (int j = 0; j < spus.size(); j++) {
                            spus.get(j).setChecked(false);
                            String praise_num = spus.get(j).getPraise_num();
                            int num = Integer.parseInt(praise_num);
                            String price = spus.get(j).getSkus().get(0).getPrice();
                            double v1 = Double.parseDouble(price);
                            priceSum += v1 * num;
                        }
                    }
                    myadapter.notifyDataSetChanged();
                    zong.setText("总价:" + 0.0);
                }
            }
        });


    }
}
