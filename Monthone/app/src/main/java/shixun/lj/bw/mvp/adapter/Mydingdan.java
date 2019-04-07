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
 */public class Mydingdan extends RecyclerView.Adapter<Mydingdan.Viewholder> {
    List<Address.ResultBean> result;
    Context context;

    public Mydingdan(List<Address.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }



    @NonNull
    @Override
    public Mydingdan.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.dingdan, viewGroup, false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Mydingdan.Viewholder viewholder, int i) {
        viewholder.name.setText(result.get(i).getRealName());
        viewholder.address.setText(result.get(i).getAddress());
        viewholder.phone.setText(result.get(i).getPhone());


    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView address;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dname);
            phone = itemView.findViewById(R.id.dphone);
            address = itemView.findViewById(R.id.daddress1);
        }
    }

}
