package shixun.lj.bw.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import shixun.lj.bw.R;


public class Jiesuan extends AppCompatActivity {

    private Button bt1;
    private CheckBox weixin;
    private CheckBox zfb;
    private CheckBox yue;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiesuan);
        Initview();
        Initdata();


    }

    private void Initview() {
        preferences = getSharedPreferences("c", Loginactivity.MODE_PRIVATE);
        bt1 = findViewById(R.id.btyue);
        weixin = findViewById(R.id.weixin);
        zfb = findViewById(R.id.zhifubao);
        yue = findViewById(R.id.yue);
    }

    private void Initdata() {
        Intent intent = getIntent();
        int sum = intent.getExtras().getInt("sum");
        bt1.setText("余额支付" + sum + "元");
        weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (weixin.isChecked()) {
                    Toast.makeText(Jiesuan.this, "暂不支持", Toast.LENGTH_SHORT).show();
                }

            }
        });
        zfb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (zfb.isChecked()) {
                    Toast.makeText(Jiesuan.this, "暂不支持", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sessionId = preferences.getString("sessionId", "");
                int userId = preferences.getInt("userId", 20);
            }
        });

    }
}
