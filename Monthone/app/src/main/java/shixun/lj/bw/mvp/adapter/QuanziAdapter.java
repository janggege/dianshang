package shixun.lj.bw.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Quanzi;

/*Time:2019/3/28
 *Author:刘江
 *Description:
 */public class QuanziAdapter extends RecyclerView.Adapter<QuanziAdapter.Viewholder> {
    List<Quanzi.ResultBean> result;
    Context context;

    public QuanziAdapter(List<Quanzi.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public QuanziAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.xinagqingiteam, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanziAdapter.Viewholder viewholder, int i) {
        viewholder.t1.setText(result.get(i).getNickName());
        viewholder.t2.setText(result.get(i).getCreateTime() + "");
        viewholder.t3.setText(result.get(i).getContent());

        Glide.with(context).load(result.get(i).getHeadPic()).into(viewholder.i1);
        String image = result.get(i).getImage();


        Glide.with(context).load(image).into(viewholder.i2);
        Glide.with(context).load(image).into(viewholder.i3);


    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private final ImageView i1;
        private final ImageView i2;
        private final ImageView i3;
        private final TextView t1;
        private final TextView t2;
        private final TextView t3;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            i1 = itemView.findViewById(R.id.qi1);
            i2 = itemView.findViewById(R.id.qi2);
            i3 = itemView.findViewById(R.id.qi3);
            t1 = itemView.findViewById(R.id.qt1);
            t2 = itemView.findViewById(R.id.qt2);
            t3 = itemView.findViewById(R.id.qt3);
        }
    }
}
