package shixun.lj.bw.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Gouwuche;
import shixun.lj.bw.view.activity.Myview;

/*Time:2019/3/26
 *Author:刘江
 *Description:
 */public class Gouwucheadapter extends RecyclerView.Adapter<Gouwucheadapter.Viewholder> {

    List<Gouwuche.ResultBean> result;
    Context context;
    //全选  反选
    Map<String, Boolean> map = new HashMap<>();
    //价格
    Map<Integer, Integer> money = new HashMap<>();

    public Gouwucheadapter(List<Gouwuche.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
        checked2(false);
    }


    @NonNull
    @Override
    public Gouwucheadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.giteam, viewGroup, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Gouwucheadapter.Viewholder viewholder, final int i) {
        Glide.with(context).load(result.get(i).getPic()).into(viewholder.imageView);
        viewholder.textView.setText(result.get(i).getCommodityName());
        viewholder.textView1.setText("￥" + result.get(i).getPrice());

        String name = result.get(i).getCommodityName();

        viewholder.checkBox.setChecked(map.get(name));

        viewholder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money.clear();
                notifyDataSetChanged();


                String name = result.get(i).getCommodityName();
                map.put(name, viewholder.checkBox.isChecked());
                boolean flag = true;
                for (String key : map.keySet()) {
                    Boolean aBoolean = map.get(key);
                    if (!aBoolean) {
                        flag = false;
                        onchangecheck.onchange(flag);
                    }
                }
                if (flag) {
                    onchangecheck.onchange(flag);
                }
            }
        });
        viewholder.myview.setOnclick(new Myview.onclick() {
            @Override
            public void inclick() {
                notifyDataSetChanged();
            }
        });

        //价格的
        if (viewholder.checkBox.isChecked()) {
            EditText editText = viewholder.myview.findViewById(R.id.ed1);
            String s = editText.getText().toString();
            int i1 = Integer.parseInt(s);
            int price = result.get(i).getPrice();
            money.put(i1, price);
            getGetmoney();
        } else {
            money.clear();
            getGetmoney();
        }
    }

    private void getGetmoney() {
        int sum = 0;

        for (Integer key : money.keySet()) {
            sum += key * money.get(key);
        }
        fanmoney.onmoney(sum);

    }


    @Override
    public int getItemCount() {
        return result.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        private final TextView textView1;
        private final Myview myview;
        private final CheckBox checkBox;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check1);
            imageView = itemView.findViewById(R.id.gi);
            textView = itemView.findViewById(R.id.g1);
            textView1 = itemView.findViewById(R.id.g2);
            myview = itemView.findViewById(R.id.myview);
        }
    }

    public void checked(boolean check) {
        checked2(check);
        notifyDataSetChanged();
    }

    public void checked2(boolean check) {
        map.clear();
        for (int i = 0; i < result.size(); i++) {
            String name = result.get(i).getCommodityName();
            map.put(name, check);
        }
        notifyDataSetChanged();
    }

    //价格的回调
    public interface fanmoney {
        void onmoney(int money);
    }

    fanmoney fanmoney;

    public void setFanmoney(Gouwucheadapter.fanmoney fanmoney) {
        this.fanmoney = fanmoney;
    }

    //checkbox的回调
    public interface onchangecheck {
        void onchange(boolean a);
    }

    onchangecheck onchangecheck;

    public void setOnchangecheck(Gouwucheadapter.onchangecheck onchangecheck) {
        this.onchangecheck = onchangecheck;
    }
}
