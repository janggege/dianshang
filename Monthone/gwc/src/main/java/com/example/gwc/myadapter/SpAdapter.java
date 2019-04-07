package com.example.gwc.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gwc.R;
import com.example.gwc.bean.spus;

import java.util.ArrayList;
import java.util.List;

/*Time:2019/4/2
 *Author:刘江
 *Description:
 */public class SpAdapter extends RecyclerView.Adapter<SpAdapter.Viewholder> {
    List<spus> listData;
    Context context;

    public SpAdapter(List<spus> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }


    @NonNull
    @Override
    public SpAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.spiteam, viewGroup, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpAdapter.Viewholder viewholder, final int i) {
        com.example.gwc.bean.spus spus = this.listData.get(i);
        //商品状态改变
        viewholder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (childListener != null) {
                    //先改变自身状态
                    listData.get(i).setChecked(isChecked);
                    childListener.onChild();
                }
            }
        });
        viewholder.onbind(spus);

    }

    public void setCheckChild(boolean checked) {
        //商家适配器调用的方法
        //通过该方法实现商家内部全选
        for (int i = 0; i < listData.size(); i++) {
            listData.get(i).setChecked(checked);
        }
        notifyDataSetChanged();
    }




    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView name;
        private final TextView price;
        private final CheckBox checkBox;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            checkBox = itemView.findViewById(R.id.pincheckbox);
        }

        public void onbind(com.example.gwc.bean.spus spus) {
            Glide.with(context).load(spus.getPic_url()).into(imageView);
            name.setText(spus.getName());
            checkBox.setChecked(spus.isChecked());
            String price = spus.getSkus().get(0).getPrice();
            this.price.setText(price);
        }


    }
    public interface onChildListener {
        void onChild();
    }

    public onChildListener childListener;

    public void setChildListener(onChildListener childListener) {
        this.childListener = childListener;
    }
}
