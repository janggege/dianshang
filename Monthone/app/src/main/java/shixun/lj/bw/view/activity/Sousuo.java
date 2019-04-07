package shixun.lj.bw.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shixun.lj.bw.R;

public class Sousuo extends AppCompatActivity {
    @BindView(R.id.flayout)
    AutoFlowLayout flayout;
    @BindView(R.id.fan)
    MyLinerfan fan;
    @BindView(R.id.simage)
    ImageView simage;
    private ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        ButterKnife.bind(this);
        list = new ArrayList<>();

        fan.setOnclick(new MyLinerfan.onclick1() {
            @Override
            public void showdata(String s) {
                list.add(s);
                getAdds(list);
                Intent intent = new Intent(Sousuo.this, Sousuoone.class);
                intent.putExtra("s", s);
                startActivity(intent);

            }

            @Override
            public void fan() {
                finish();
            }
        });


    }


    private void getAdds(final ArrayList<String> list) {
        flayout.setAdapter(new FlowAdapter(list) {
            @Override
            public View getView(int i) {
                View inflate = LayoutInflater.from(Sousuo.this).inflate(R.layout.iteam, null, false);
                TextView text2 = inflate.findViewById(R.id.ltext2);
                text2.setText(list.get(i));
                list.clear();
                return inflate;
            }
        });

    }


    @OnClick({R.id.simage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.simage:
                list.clear();
                flayout.removeAllViews();
                break;
        }
    }
}
