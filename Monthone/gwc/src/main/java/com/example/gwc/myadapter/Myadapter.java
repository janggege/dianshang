package com.example.gwc.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gwc.R;
import com.example.gwc.bean.Data;
import com.example.gwc.bean.spus;

import java.util.List;

/*Time:2019/4/2
 *Author:刘江
 *Description:
 */public class Myadapter extends RecyclerView.Adapter<Myadapter.Viewholder> {

    private List<Data> listData;
    Context context;
    private SpAdapter spAdapter;


    public Myadapter(List<Data> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }


    @NonNull
    @Override
    public Myadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.iteam, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myadapter.Viewholder viewholder, final int i) {
        Data data = listData.get(i);
        viewholder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //商品适配器接口监听

                spAdapter.setChildListener(new SpAdapter.onChildListener() {
                    @Override
                    public void onChild() {
                        //商品监听到就调用商家接口回调到主页面
                        if (groupListener != null) {
                            groupListener.onGroup(listData);
                        }
                        //声明变量
                        boolean isChecked = true;
                        //遍历商品状态
                        for (int j = 0; j < listData.size(); j++) {
                            boolean childChecked = listData.get(j).isChecked();
                            if (!childChecked) {
                                isChecked = false;
                                break;
                            }
                        }
                        //通过商品状态改变商家状态
                        viewholder.checkBox.setChecked(isChecked);
                        listData.get(i).setChecked(isChecked);
                    }
                });

            }
        });
        viewholder.bind(data);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        private final TextView textView1;
        private final RecyclerView recyclerView;
        private final CheckBox checkBox;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text1);
            recyclerView = itemView.findViewById(R.id.recy);
            checkBox = itemView.findViewById(R.id.shangcheck);
        }

        public void bind(Data data) {
            String name = data.getName();
            textView1.setText(name);
            checkBox.setChecked(data.isChecked());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            List<spus> spus = data.getSpus();
            spAdapter = new SpAdapter(spus, context);
            //商家控制商品
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先改变商家状态
                    checkBox.setChecked(checkBox.isChecked());
                    //改变商品状态-->调用商品适配器方法
                    spAdapter.setCheckChild(checkBox.isChecked());
                }
            });


            recyclerView.setAdapter(spAdapter);
        }
    }

    public interface onGroupListener {
        void onGroup(List<Data> listData);
    }

    public onGroupListener groupListener;

    public void setGroupListener(onGroupListener groupListener) {
        this.groupListener = groupListener;
    }


}
