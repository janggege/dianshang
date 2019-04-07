package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;
import java.util.Map;

import shixun.lj.bw.mvp.inteface.Rrginterface;
import shixun.lj.bw.mvp.model.Regmodel;

public class Regpresenter implements Rrginterface.Regpresenter {
    Rrginterface.Regview Regview;
    private Regmodel regmodel;

    private SoftReference<Regmodel> reference;

    @Override
    public void attachview(Rrginterface.Regview Regview) {
        regmodel = new Regmodel();
        reference = new SoftReference<>(regmodel);
        this.Regview = Regview;


    }

    @Override
    public void detachvirew() {
        reference.clear();
    }

    @Override
    public void getdata(Map<String, String> list) {
        regmodel.Requset(list, new Rrginterface.Regmodle.Callback() {
            @Override
            public void onchenggong(String data) {
                Regview.Rview(data);

            }

            @Override
            public void onerror() {

            }
        });

    }
}
