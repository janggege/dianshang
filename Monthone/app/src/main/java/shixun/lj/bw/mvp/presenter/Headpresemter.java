package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;

import shixun.lj.bw.mvp.inteface.Headinterface;
import shixun.lj.bw.mvp.model.Headmodel;

public class Headpresemter implements Headinterface.Headpresenetr {
    Headinterface.Hview hview;
    private SoftReference<Headmodel> headmodelSoftReference;
    private Headmodel headmodel;

    @Override
    public void attachview(Headinterface.Hview hview) {
        headmodel = new Headmodel();
        headmodelSoftReference = new SoftReference<>(headmodel);
        this.hview = hview;

    }

    @Override
    public void detachview() {
        headmodelSoftReference.clear();

    }

    @Override
    public void getdata() {
        headmodel.Requestdata(new Headinterface.HradModel.Callback() {
            @Override
            public void sucess(String data) {
                hview.Hview(data);
            }

            @Override
            public void error() {

            }
        });


    }
}
