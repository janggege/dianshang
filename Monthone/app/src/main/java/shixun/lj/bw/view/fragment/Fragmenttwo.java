package shixun.lj.bw.view.fragment;

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

import com.google.gson.Gson;

import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Quanzi;
import shixun.lj.bw.mvp.adapter.QuanziAdapter;
import shixun.lj.bw.mvp.inteface.Quanziinterface;
import shixun.lj.bw.mvp.presenter.Quanzipresenter;

/*
  name:刘江
  data:2019
*/public class Fragmenttwo extends Fragment implements Quanziinterface.QuanziView {
    private static final int page = 1;
    private static final int count = 5;
    private Handler handler = new Handler();
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmenttwo, null, false);
        recyclerView = view.findViewById(R.id.recythere);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        Quanzipresenter quanzipresenter = new Quanzipresenter();
        quanzipresenter.attachview(this);
        quanzipresenter.requestdata(page, count);
        return view;
    }

    @Override
    public void QuanziData(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Quanzi quanzi = gson.fromJson(data, Quanzi.class);
                List<Quanzi.ResultBean> result = quanzi.getResult();
                QuanziAdapter quanziAdapter = new QuanziAdapter(result, getActivity());
                recyclerView.setAdapter(quanziAdapter);
            }
        });
    }
}
