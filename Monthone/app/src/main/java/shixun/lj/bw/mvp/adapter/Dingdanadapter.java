package shixun.lj.bw.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Xiang;
import shixun.lj.bw.view.activity.Myview;

/*Time:2019/3/31
 *Author:刘江
 *Description:
 */public class Dingdanadapter extends RecyclerView.Adapter<Dingdanadapter.Viewholder> {
    ArrayList<Xiang.ResultBean> list;
    Context context;
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public Dingdanadapter(ArrayList<Xiang.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Dingdanadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dingdaniteam, viewGroup, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Dingdanadapter.Viewholder viewholder, int i) {
        viewholder.text1.setText(list.get(i).getCommodityName());
        viewholder.text2.setText("￥" + list.get(i).getPrice());
        String picture = list.get(i).getPicture();
        String[] split = picture.split(",");
        String image = split[0];
        Glide.with(context).load(image).into(viewholder.imageView);

        viewholder.myview.setOnclick(new Myview.onclick() {
            @Override
            public void inclick() {
                notifyDataSetChanged();
            }
        });

        EditText editText = viewholder.myview.findViewById(R.id.ed1);
        String s = editText.getText().toString();
        int num = Integer.parseInt(s);
        int price = list.get(i).getPrice();
        map.put(price, num);
        getGetmoney();
    }

    private void getGetmoney() {
        int sum = 0;
        for (Integer key : map.keySet()) {
            sum += key * map.get(key);
        }
        onclick.onclick(sum);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView text1;
        private final TextView text2;
        private final Myview myview;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.dimage);
            text1 = itemView.findViewById(R.id.dtext1);
            text2 = itemView.findViewById(R.id.dtext2);
            myview = itemView.findViewById(R.id.myview);
        }
    }

    public interface onclick {
        void onclick(int money);
    }

    onclick onclick;

    public void setOnclick(Dingdanadapter.onclick onclick) {
        this.onclick = onclick;
    }
}
