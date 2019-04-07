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

public class Pzshadapter extends RecyclerView.Adapter<Pzshadapter.Viewholder> {
    List<Show.ResultBean.PzshBean.CommodityListBeanX> pzsh;
    Context context;

    public Pzshadapter(List<Show.ResultBean.PzshBean.CommodityListBeanX> pzsh, Context context) {
        this.pzsh = pzsh;
        this.context = context;
    }

    @NonNull
    @Override
    public Pzshadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.pzshone, viewGroup, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Pzshadapter.Viewholder viewholder, int i) {
        Glide.with(context).load(pzsh.get(i).getMasterPic()).into(viewholder.imageView);
        viewholder.textView.setText(pzsh.get(i).getCommodityName());
        viewholder.textView1.setText("￥" + pzsh.get(i).getPrice());
        final int commodityId = pzsh.get(i).getCommodityId();
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ponclick!=null){
                    ponclick.ponclick(commodityId);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return pzsh.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;
        private final TextView textView1;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pi1);
            textView = itemView.findViewById(R.id.pt1);
            textView1 = itemView.findViewById(R.id.pt2);
        }
    }

    interface ponclick {
        void ponclick(int id);
    }

    ponclick ponclick;

    public void setPonclick(Pzshadapter.ponclick ponclick) {
        this.ponclick = ponclick;
    }
}
