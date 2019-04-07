package shixun.lj.bw.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import shixun.lj.bw.R;
import shixun.lj.bw.data.Tou;
import shixun.lj.bw.mvp.Bean.Show;
import shixun.lj.bw.mvp.adapter.Myadapter;
import shixun.lj.bw.mvp.adapter.Xadapter;
import shixun.lj.bw.mvp.inteface.Headinterface;
import shixun.lj.bw.mvp.inteface.Showinterface;
import shixun.lj.bw.mvp.presenter.Headpresemter;
import shixun.lj.bw.mvp.presenter.Showpresenter;
import shixun.lj.bw.view.activity.MyLinershou;
import shixun.lj.bw.view.activity.Sousuo;
import shixun.lj.bw.view.activity.Xiangqing;

/*
  name:刘江
  data:2019
*/public class Fragmentone extends Fragment implements Headinterface.Hview, Showinterface.Showviewlins {


    private Headpresemter headpresemter;
    private Handler handler = new Handler();
    private RecyclerView recyclerView;
    private Showpresenter showpresenter;
    private List<Tou.ResultBean> banner;
    private Show.ResultBean result;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, null, false);
        recyclerView = view.findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        //解决滑动冲突、滑动不流畅
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setNestedScrollingEnabled(false);


        showpresenter = new Showpresenter();
        showpresenter.attachview(this);
        showpresenter.getdata();
        headpresemter = new Headpresemter();
        headpresemter.attachview(this);
        headpresemter.getdata();
        MyLinershou myLinershou = view.findViewById(R.id.mylieshou);

        myLinershou.setOnclick(new MyLinershou.onclick1() {
            @Override
            public void showdata1() {
                Toast.makeText(getActivity(), "a", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showdata2() {
                Toast.makeText(getActivity(), "a", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showdata3() {
                Intent intent = new Intent(getActivity(), Sousuo.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void Hview(final String data) {
        //集合设置给xbanner
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Tou tou = gson.fromJson(data, Tou.class);
                banner = tou.getResult();
                Myadapter myadapter = new Myadapter(result, getActivity(), banner);
                recyclerView.setAdapter(myadapter);
            }
        });


    }

    @Override
    public void Showview(final String data) {
        handler.post(new Runnable() {


            @Override
            public void run() {
                Gson gson = new Gson();
                Show show = gson.fromJson(data, Show.class);
                result = show.getResult();
                Myadapter myadapter = new Myadapter(result, getActivity(), banner);
                myadapter.setOnclick(new Myadapter.onclick() {
                    @Override
                    public void onclick(int id) {
                        Intent intent = new Intent(getActivity(), Xiangqing.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
                myadapter.setOnclick1(new Myadapter.onclick1() {
                    @Override
                    public void onclick1(int id) {
                        Intent intent = new Intent(getActivity(), Xiangqing.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
                myadapter.setOnclick2(new Myadapter.onclick2() {
                    @Override
                    public void onclick2(int id) {
                        Intent intent = new Intent(getActivity(), Xiangqing.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });


                recyclerView.setAdapter(myadapter);


            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        headpresemter.detachview();
        showpresenter.detachview();
    }


}
