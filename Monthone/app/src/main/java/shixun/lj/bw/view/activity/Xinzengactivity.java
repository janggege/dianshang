package shixun.lj.bw.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


import java.util.HashMap;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Dizhi;
import shixun.lj.bw.mvp.inteface.Adddizhiinterface;
import shixun.lj.bw.mvp.presenter.Dizhipresenter;

public class Xinzengactivity extends AppCompatActivity implements Adddizhiinterface.dizhiview {
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private TextView ed33;
    private EditText ed4;
    private EditText ed5;
    private Dizhipresenter dizhipresenter;
    private int userId;
    private String sessionId;
    private Handler handler = new Handler();
    private static final String TAG = "Xinzengactivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinzengactivity);
        ed1 = findViewById(R.id.at1);
        ed2 = findViewById(R.id.at2);
        ed3 = findViewById(R.id.at3);
        ed33 = findViewById(R.id.at33);
        ed4 = findViewById(R.id.at4);
        ed5 = findViewById(R.id.at5);
        Button bao = findViewById(R.id.bao);
        SharedPreferences sharedPreferences = getSharedPreferences("c", Xiangqing.MODE_PRIVATE);
        sessionId = sharedPreferences.getString("sessionId", "");
        userId = sharedPreferences.getInt("userId", 20);
        dizhipresenter = new Dizhipresenter();
        dizhipresenter.attachview(this);


        bao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed1.getText().toString();
                String phone = ed2.getText().toString();
                String address = ed4.getText().toString();
                String bm = ed5.getText().toString();
                HashMap<String, String> map = new HashMap<>();
                map.put("realName", name);
                map.put("phone", phone);
                map.put("address", address);
                map.put("zipCode", bm);
                dizhipresenter.getdata(userId + "", sessionId, map);
            }
        });

    }


    @Override
    public void Adddizhi(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Dizhi dizhi = gson.fromJson(data, Dizhi.class);
                String message = dizhi.getMessage();
                Toast.makeText(Xinzengactivity.this, message, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Xinzengactivity.this, Addressactivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dizhipresenter.detachview();
    }
}
