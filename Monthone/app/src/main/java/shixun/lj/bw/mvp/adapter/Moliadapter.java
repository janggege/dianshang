package shixun.lj.bw.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Show;

public class Moliadapter extends RecyclerView.Adapter<Moliadapter.Viewholder> {
    List<Show.ResultBean.MlssBean.CommodityListBeanXX> mlss;
    Context context;

    public Moliadapter(List<Show.ResultBean.MlssBean.CommodityListBeanXX> mlss, Context context) {
        this.mlss = mlss;
        this.context = context;
    }

    @NonNull
    @Override
    public Moliadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.molione, viewGroup, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Moliadapter.Viewholder viewholder, int i) {
        Glide.with(context).load(mlss.get(i).getMasterPic()).into(viewholder.imageView);
        viewholder.textView.setText(mlss.get(i).getCommodityName());
        viewholder.textView1.setText("￥" + mlss.get(i).getPrice());
        final int commodityId = mlss.get(i).getCommodityId();
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (monclick != null) {
                    monclick.mclick(commodityId);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlss.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;
        private final TextView textView1;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.moi1);
            textView = itemView.findViewById(R.id.mot1);
            textView1 = itemView.findViewById(R.id.mot2);
        }
    }

    interface monclick {
        void mclick(int id);

    }

    monclick monclick;

    public void setMonclick(Moliadapter.monclick monclick) {
        this.monclick = monclick;
    }
}
