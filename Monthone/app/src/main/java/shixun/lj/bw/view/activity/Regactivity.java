package shixun.lj.bw.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
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
import shixun.lj.bw.mvp.Bean.Reg;
import shixun.lj.bw.mvp.inteface.Rrginterface;
import shixun.lj.bw.mvp.okttp.Utils;
import shixun.lj.bw.mvp.presenter.Regpresenter;

public class Regactivity extends AppCompatActivity implements Rrginterface.Regview {

    @BindView(R.id.zname)
    EditText zname;
    @BindView(R.id.yanzheng)
    EditText yanzheng;
    @BindView(R.id.yanz)
    TextView yanz;
    @BindView(R.id.zpwd)
    EditText zpwd;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.reg)
    Button reg;
    private Regpresenter regpresenter;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regactivity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.reg)
    public void onViewClicked() {
        String name = zname.getText().toString();
        String pwd = zpwd.getText().toString();
        if (name.length() == 0 && pwd.length() == 0) {
            Toast.makeText(Regactivity.this, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Utils.isMobileNO(name)) {
            Toast.makeText(Regactivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.length() < 4) {
            Toast.makeText(Regactivity.this, "密码不能小于4位", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", name);
        map.put("pwd", pwd);
        regpresenter = new Regpresenter();
        regpresenter.attachview(this);
        regpresenter.getdata(map);

    }

    @Override
    public void Rview(final String data) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Reg reg = gson.fromJson(data, Reg.class);
                String status = reg.getStatus();

                if (status.equals("0000")) {
                    Toast.makeText(Regactivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Regactivity.this, Loginactivity.class);
                    startActivity(intent);
                    finish();

                } else if (status.equals("1001")) {
                    Toast.makeText(Regactivity.this, "该手机号已注册，不能重复注册！", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
   
    }
}
