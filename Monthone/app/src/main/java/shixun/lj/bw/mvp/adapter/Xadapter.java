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

public class Xadapter extends RecyclerView.Adapter<Xadapter.Viewholder> {
    List<Show.ResultBean.RxxpBean.CommodityListBean> rxxp;
    Context context;

    public Xadapter(List<Show.ResultBean.RxxpBean.CommodityListBean> rxxp, Context context) {
        this.rxxp = rxxp;
        this.context = context;
    }

    @NonNull
    @Override
    public Xadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.xin1, viewGroup, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Xadapter.Viewholder viewholder, int i) {
        Glide.with(context).load(rxxp.get(i).getMasterPic()).into(viewholder.imageView);
        viewholder.textView.setText(rxxp.get(i).getCommodityName());
        viewholder.textView1.setText("￥" + rxxp.get(i).getPrice());
        final int commodityId = rxxp.get(i).getCommodityId();
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(xonclick!=null){
                    xonclick.onclick(commodityId);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return rxxp.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;
        private final TextView textView1;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.i1);
            textView = itemView.findViewById(R.id.t1);
            textView1 = itemView.findViewById(R.id.xt2);
        }
    }

    interface xonclick {
        void onclick(int i);
    }

    xonclick xonclick;

    public void setXonclick(Xadapter.xonclick xonclick) {
        this.xonclick = xonclick;
    }
}
