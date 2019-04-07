package shixun.lj.bw.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Gouwuche;
import shixun.lj.bw.mvp.Bean.Tongbu;
import shixun.lj.bw.mvp.Bean.Xiang;
import shixun.lj.bw.mvp.inteface.Addinterface;
import shixun.lj.bw.mvp.inteface.Byinterface;
import shixun.lj.bw.mvp.inteface.Xinagqing;
import shixun.lj.bw.mvp.presenter.Addpresenter;
import shixun.lj.bw.mvp.presenter.Bypresenter;
import shixun.lj.bw.mvp.presenter.Xpresenter;

public class Xiangqing extends AppCompatActivity implements Xinagqing.Xview, Byinterface.Bview, Addinterface.Addview {

    private Xpresenter xpresenter;
    private Handler handler = new Handler();
    private FlyBanner flyBanner;
    private WebView webView;
    private ImageView imageView;
    private int commodityId;
    private int userId;
    private String sessionId;
    private ImageView mai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        Intent intent = getIntent();
        final int id = intent.getExtras().getInt("id");
        flyBanner = findViewById(R.id.flaybanner);
        webView = findViewById(R.id.webview);
        imageView = findViewById(R.id.gou);
        mai = findViewById(R.id.mai);
        xpresenter = new Xpresenter();
        xpresenter.attachview(Xiangqing.this);
        xpresenter.getdata(id);
        //买的点击事件
        mai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Xiangqing.this, Dingdan.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });
        //加入购物车的电机事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("c", Xiangqing.MODE_PRIVATE);
                String staues = sharedPreferences.getString("staues", "");
                sessionId = sharedPreferences.getString("sessionId", "");
                userId = sharedPreferences.getInt("userId", 20);
                if (staues.equals("0000")) {//在登录的情况下，先查询购物车
                    Bypresenter bypresenter = new Bypresenter();
                    bypresenter.attachbview(Xiangqing.this);
                    bypresenter.getbdata(userId + "", sessionId);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Xiangqing.this);
                    builder.setTitle("是否登录?");
                    //点击对话框以外的区域是否让对话框消失
                    builder.setCancelable(true);
                    //设置正面按钮
                    builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Xiangqing.this, Loginactivity.class));
                        }
                    });
                    //设置反面按钮
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Xiangqing.this, "取消", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }

            }
        });


    }

    //详情返回的数据
    @Override
    public void Xview(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Xiang xiang = gson.fromJson(data, Xiang.class);
                String picture = xiang.getResult().getPicture();
                commodityId = xiang.getResult().getCommodityId();
                String[] split = picture.split(",");
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    String image = split[i];
                    list.add(image);
                }
                flyBanner.setImagesUrl(list);
                String details = xiang.getResult().getDetails();
                webView.loadData(details, "text/html; charset=UTF-8", null);
                //webview的自适应屏幕
                WebSettings settings = webView.getSettings();
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);

            }
        });

    }





    @Override
    public void Bfview(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                ArrayList<Tongbu> list = new ArrayList<>();

                Gouwuche gouwuche = gson.fromJson(data, Gouwuche.class);
                List<Gouwuche.ResultBean> result = gouwuche.getResult();
                for (int i = 0; i < result.size(); i++) {
                    list.add(new Tongbu(result.get(i).getCommodityId(), 1));
                }
                list.add(new Tongbu(commodityId, 1));
                String s = gson.toJson(list);

                Addpresenter addpresenter = new Addpresenter();

                addpresenter.addattachview(Xiangqing.this);
                addpresenter.getadd(s, userId + "", sessionId);
            }
        });

    }

    //添加购物车返回的数据
    @Override
    public void addview(final String fandata) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Xiangqing.this, "" + fandata, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        xpresenter.detachview();
    }


}
