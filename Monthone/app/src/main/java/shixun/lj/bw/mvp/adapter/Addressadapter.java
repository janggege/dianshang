package shixun.lj.bw.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Address;

/*Time:2019/3/29
 *Author:刘江
 *Description:
 */public class Addressadapter extends RecyclerView.Adapter<Addressadapter.Viewholder> {
    List<Address.ResultBean> result;
    Context context;

    public Addressadapter(List<Address.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public Addressadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.addressiteam, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Addressadapter.Viewholder viewholder, int i) {
        viewholder.t1.setText(result.get(i).getRealName());
        viewholder.t2.setText(result.get(i).getPhone());
        viewholder.t4.setText(result.get(i).getAddress());
        viewholder.t5.setText(result.get(i).getZipCode());


    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private final TextView t1;
        private final TextView t2;
        private final TextView t3;
        private final TextView t4;
        private final TextView t5;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.at1);
            t2 = itemView.findViewById(R.id.at2);
            t3 = itemView.findViewById(R.id.at3);
            t4 = itemView.findViewById(R.id.at4);
            t5 = itemView.findViewById(R.id.at5);


        }
    }
}
