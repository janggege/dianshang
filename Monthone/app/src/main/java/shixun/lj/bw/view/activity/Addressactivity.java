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

import com.google.gson.Gson;

import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Address;
import shixun.lj.bw.mvp.adapter.Myaddressadapte;
import shixun.lj.bw.mvp.inteface.Addressinterface;
import shixun.lj.bw.mvp.presenter.Addrsspresenter;

public class Addressactivity extends AppCompatActivity implements Addressinterface.Addressview {
    private int userId;
    private String sessionId;
    private Handler handler = new Handler();
    private RecyclerView recyclerView;
    private Addrsspresenter addrsspresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressactivity);
        SharedPreferences sharedPreferences = getSharedPreferences("c", Xiangqing.MODE_PRIVATE);
        sessionId = sharedPreferences.getString("sessionId", "");
        userId = sharedPreferences.getInt("userId", 20);
        recyclerView = findViewById(R.id.addrecy);
        Button zeng = findViewById(R.id.zeng);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        addrsspresenter = new Addrsspresenter();
        addrsspresenter.addressAttachview(this);
        addrsspresenter.addressQequest(userId + "", sessionId);
        zeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Addressactivity.this, Xinzengactivity.class));
                finish();
            }
        });

    }

    @Override
    public void Addressview(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("qqqqqqq", "run: " + data);
                Gson gson = new Gson();
                Address address = gson.fromJson(data, Address.class);
                List<Address.ResultBean> result = address.getResult();
                Myaddressadapte myaddressadapte = new Myaddressadapte(result, Addressactivity.this);
                recyclerView.setAdapter(myaddressadapte);

            }
        });


    }

    //进入这个页面的时候立即请求数据
    @Override
    protected void onStart() {
        super.onStart();
        Addrsspresenter addrsspresenter = new Addrsspresenter();
        addrsspresenter.addressAttachview(this);
        addrsspresenter.addressQequest(userId + "", sessionId);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addrsspresenter.addressDetachview();
    }
}
