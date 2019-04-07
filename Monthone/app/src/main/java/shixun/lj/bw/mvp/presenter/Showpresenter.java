package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;

import shixun.lj.bw.mvp.inteface.Showinterface;
import shixun.lj.bw.mvp.model.Showmodel;

public class Showpresenter implements Showinterface.Showpersenterlis {
    Showinterface.Showviewlins Showviewlins;
    private SoftReference<Showmodel> showmodelSoftReference;
    private Showmodel showmodel;

    @Override
    public void attachview(Showinterface.Showviewlins Showviewlins) {
        showmodel = new Showmodel();
        showmodelSoftReference = new SoftReference<>(showmodel);
        this.Showviewlins = Showviewlins;

    }

    @Override
    public void detachview() {
        showmodelSoftReference.clear();
    }

    @Override
    public void getdata() {
        showmodel.Request(new Showinterface.Showmodellins.Callback() {
            @Override
            public void onchenggong(String data) {
                Showviewlins.Showview(data);
            }

            @Override
            public void onshibai() {

            }
        });

    }
}
