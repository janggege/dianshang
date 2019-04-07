package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;
import java.util.Map;

import shixun.lj.bw.mvp.inteface.Logininterface;
import shixun.lj.bw.mvp.model.Loginmodel;

public class Loginpresenter implements Logininterface.Loginpersenter {
    Logininterface.Loginview loginview;
    private SoftReference<Loginmodel> reference;
    private Loginmodel loginmodel;

    @Override
    public void attachview(Logininterface.Loginview loginview) {
        loginmodel = new Loginmodel();
        reference = new SoftReference<>(loginmodel);
        this.loginview = loginview;


    }

    @Override
    public void detachview() {
        reference.clear();

    }

    @Override
    public void getdata(Map<String, String> list) {
        loginmodel.Requestdata(list, new Logininterface.Logmodelclick.Callback() {
            @Override
            public void onchenggong(String data) {
                loginview.Loginviewclick(data);
            }

            @Override
            public void error() {

            }
        });

    }
}
