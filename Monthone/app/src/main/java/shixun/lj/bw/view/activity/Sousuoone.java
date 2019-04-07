package shixun.lj.bw.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Sousuo;
import shixun.lj.bw.mvp.adapter.Sousuoadapter;
import shixun.lj.bw.mvp.inteface.Shousuointerface;
import shixun.lj.bw.mvp.presenter.Sousuopresenter;

public class Sousuoone extends AppCompatActivity implements Shousuointerface.Shousuoview {

    private Sousuopresenter sousuopresenter;
    int page = 1;
    private RecyclerView recyclerView;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuoone);
        Intent intent = getIntent();
        String s = intent.getExtras().getString("s");
        MyLiner myLiner = findViewById(R.id.myline);
        recyclerView = findViewById(R.id.srecy);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        myLiner.editText.setText(s);
        sousuopresenter = new Sousuopresenter();
        sousuopresenter.attchview(Sousuoone.this);
        sousuopresenter.getdata(s, page);


        myLiner.setOnclick(new MyLiner.onclick1() {
            @Override
            public void showdata(String s) {
                sousuopresenter.getdata(s, page);

            }
        });


    }

    @Override
    public void ssview(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Sousuo sousuo = gson.fromJson(data, Sousuo.class);
                List<Sousuo.ResultBean> result = sousuo.getResult();
                if (result.size() == 0) {
                    RelativeLayout relativeLayout = findViewById(R.id.rela);
                    relativeLayout.setVisibility(View.VISIBLE);
                } else {
                    RelativeLayout relativeLayout = findViewById(R.id.rela);

                    relativeLayout.setVisibility(View.GONE);
                }
                Sousuoadapter sousuoadapter = new Sousuoadapter(result, Sousuoone.this);
                recyclerView.setAdapter(sousuoadapter);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sousuopresenter.detachview();
    }
}
