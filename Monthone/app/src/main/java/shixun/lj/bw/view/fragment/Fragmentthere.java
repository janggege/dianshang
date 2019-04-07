package shixun.lj.bw.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Gouwuche;
import shixun.lj.bw.mvp.adapter.Gouwucheadapter;
import shixun.lj.bw.mvp.inteface.Addinterface;
import shixun.lj.bw.mvp.inteface.Byinterface;
import shixun.lj.bw.mvp.presenter.Addpresenter;
import shixun.lj.bw.mvp.presenter.Bypresenter;
import shixun.lj.bw.view.activity.Xiangqing;

/*
  name:刘江
  data:2019
//*/public class Fragmentthere extends Fragment implements Byinterface.Bview, Addinterface.Addview {

    @BindView(R.id.quan)
    CheckBox quan;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.jiesuan)
    Button jiesuan;
    Unbinder unbinder;
    private Bypresenter bypresenter;
    private SwipeMenuRecyclerView recyclerView;
    private Gouwucheadapter gouwucheadapter;
    private TextView money1;
    private List<Gouwuche.ResultBean> result;
    private Handler handler = new Handler();
    private String sessionId;
    private int userId;
    private String staues;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentthere, null, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerView = view.findViewById(R.id.grecy);
        money1 = view.findViewById(R.id.money);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        //侧滑删除
        recyclerView.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
        recyclerView.setSwipeMenuCreator(SwipeMenuCreator);
        recyclerView.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        //取sessid  和 uid
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("c", Context.MODE_PRIVATE);


        sessionId = sharedPreferences.getString("sessionId", "");
        userId = sharedPreferences.getInt("userId", 1);
        staues = sharedPreferences.getString("staues", "");
        //购物车
        bypresenter = new Bypresenter();
        bypresenter.attachbview(this);
        bypresenter.getbdata(userId + "", sessionId);

        return view;
    }

    //购物车返回的数据
    @Override
    public void Bfview(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Gouwuche gouwuche = gson.fromJson(data, Gouwuche.class);
                result = gouwuche.getResult();
                if (!staues.equals("0000")) {//判断有没有登录
                    Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    gouwucheadapter = new Gouwucheadapter(result, getActivity());
                    gouwucheadapter.setOnchangecheck(new Gouwucheadapter.onchangecheck() {
                        @Override
                        public void onchange(boolean a) {
                            quan.setChecked(a);
                        }
                    });
                    gouwucheadapter.setFanmoney(new Gouwucheadapter.fanmoney() {
                        @Override
                        public void onmoney(int money) {
                            money1.setText(money + "");
                        }
                    });
                    recyclerView.setAdapter(gouwucheadapter);
                }
            }
        });


    }

    SwipeMenuCreator SwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                    .setBackground(R.drawable.selector_red)
                    .setTextColor(Color.WHITE)
                    .setText("删除")
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);

        }
    };
    //     菜单点击监听。
    SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection();//左边还是右边菜单
            int adapterPosition = menuBridge.getAdapterPosition();//    ecyclerView的Item的position。


            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                result.remove(adapterPosition);//删除item
                gouwucheadapter.notifyDataSetChanged();
            }

        }
    };

    @OnClick({R.id.quan, R.id.jiesuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quan://全选   反选
                if (quan.isChecked()) {
                    gouwucheadapter.checked(true);

                } else {
                    gouwucheadapter.checked(false);
                }
                break;
            case R.id.jiesuan:
                Toast.makeText(getActivity(), "结算", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        bypresenter.detachbview();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();//进入页面是刷新
        bypresenter = new Bypresenter();
        bypresenter.attachbview(this);
        bypresenter.getbdata(userId + "", sessionId);

    }

    @Override
    public void addview(String fandata) {

    }
}
