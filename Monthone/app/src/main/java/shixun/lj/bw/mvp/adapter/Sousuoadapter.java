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
import shixun.lj.bw.mvp.Bean.Sousuo;

public class Sousuoadapter extends RecyclerView.Adapter<Sousuoadapter.Viewholder> {
    List<Sousuo.ResultBean> result;
    Context context;

    public Sousuoadapter(List<Sousuo.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.suos, viewGroup, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        viewholder.textView.setText(result.get(i).getCommodityName());
        viewholder.textView1.setText("￥" + result.get(i).getPrice());
        Glide.with(context).load(result.get(i).getMasterPic()).into(viewholder.imageView);

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;
        private final TextView textView1;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.i);
            textView = itemView.findViewById(R.id.t);
            textView1 = itemView.findViewById(R.id.t1);
        }
    }
}
