package shixun.lj.bw.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import shixun.lj.bw.R;
import shixun.lj.bw.view.fragment.Fragmentfive;
import shixun.lj.bw.view.fragment.Fragmentfour;
import shixun.lj.bw.view.fragment.Fragmentone;
import shixun.lj.bw.view.fragment.Fragmentthere;
import shixun.lj.bw.view.fragment.Fragmenttwo;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.group)
    RadioGroup group;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final Fragmentone fragmentone = new Fragmentone();
        final Fragmenttwo fragmenttwo = new Fragmenttwo();
        final Fragmentthere fragmentthere1 = new Fragmentthere();
        final Fragmentfour fragmentfour = new Fragmentfour();
        final Fragmentfive fragmentfive = new Fragmentfive();
        transaction.add(R.id.layout, fragmentone);
        transaction.add(R.id.layout, fragmenttwo);
        transaction.add(R.id.layout, fragmentthere1);
        transaction.add(R.id.layout, fragmentfour);
        transaction.add(R.id.layout, fragmentfive);
        transaction.show(fragmentone).hide(fragmenttwo).hide(fragmentthere1).hide(fragmentfour).hide(fragmentfive);
        group.check(R.id.bt1);
        transaction.commit();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.bt1:
                        transaction1.show(fragmentone).hide(fragmenttwo).hide(fragmentthere1).hide(fragmentfour).hide(fragmentfive);
                        break;
                    case R.id.bt2:
                        transaction1.hide(fragmentone).show(fragmenttwo).hide(fragmentthere1).hide(fragmentfour).hide(fragmentfive);
                        break;
                    case R.id.bt3:
                        transaction1.hide(fragmentone).hide(fragmenttwo).show(fragmentthere1).hide(fragmentfour).hide(fragmentfive);
                        break;
                    case R.id.bt4:
                        transaction1.hide(fragmentone).hide(fragmenttwo).hide(fragmentthere1).show(fragmentfour).hide(fragmentfive);
                        break;
                    case R.id.bt5:
                        transaction1.hide(fragmentone).hide(fragmenttwo).hide(fragmentthere1).hide(fragmentfour).show(fragmentfive);
                        break;


                }
                transaction1.commit();
            }
        });

    }
}
