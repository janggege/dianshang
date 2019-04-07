package shixun.lj.bw.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Login;
import shixun.lj.bw.mvp.inteface.Logininterface;
import shixun.lj.bw.mvp.okttp.Utils;
import shixun.lj.bw.mvp.presenter.Loginpresenter;

public class Loginactivity extends AppCompatActivity implements Logininterface.Loginview {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.login)
    Button login;
    private Loginpresenter loginpresenter;
    private Handler handler = new Handler();
    private SharedPreferences preferences;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        ButterKnife.bind(this);
        loginpresenter = new Loginpresenter();
        loginpresenter.attachview(this);
        preferences = getSharedPreferences("c", Loginactivity.MODE_PRIVATE);
        checkBox = findViewById(R.id.check);

        boolean check = preferences.getBoolean("check", false);
        if (check) {
            String name1 = preferences.getString("name", "");
            String pwd1 = preferences.getString("pwd", "");
            name.setText(name1);
            pwd.setText(pwd1);
            checkBox.setChecked(true);
        }


    }

    @OnClick({R.id.zhuce, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce:
                startActivity(new Intent(Loginactivity.this, Regactivity.class));
                finish();
                break;
            case R.id.login:
                String name1 = name.getText().toString();
                String pwd1 = pwd.getText().toString();
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean("check", checkBox.isChecked());
                edit.putString("name", name1);
                edit.putString("pwd", pwd1);
                edit.commit();
                if (name1.length() == 0 && pwd1.length() == 0) {
                    Toast.makeText(Loginactivity.this, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Utils.isMobileNO(name1)) {
                    Toast.makeText(Loginactivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd1.length() < 4) {
                    Toast.makeText(Loginactivity.this, "密码不能小于4位", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("phone", name1);
                map.put("pwd", pwd1);
                loginpresenter.getdata(map);

                break;
        }
    }

    @Override
    public void Loginviewclick(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Login login = gson.fromJson(data, Login.class);
                String staues = login.getStatus();
                Login.ResultBean result = login.getResult();
                String nickName = result.getNickName();
                String headPic = result.getHeadPic();
                String phone = result.getPhone();
                String sessionId = result.getSessionId();
                int userId = result.getUserId();
                int sex = result.getSex();
                Log.i("yyyyyyyy", "run: " + sessionId + "" + userId);
                Log.i("wwwwwww", "run: " + sessionId + "   " + userId);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("nickName", nickName);
                edit.putString("headPic", headPic);
                edit.putString("phone", phone);
                edit.putString("sessionId", sessionId);
                edit.putInt("userId", userId);
                edit.putInt("sex", sex);
                edit.putString("staues", staues);
                edit.commit();
                if (staues.equals("0000")) {
                    Toast.makeText(Loginactivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Loginactivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (staues.equals("1001")) {
                    Toast.makeText(Loginactivity.this, "手机号或密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginpresenter.detachview();
    }
}
