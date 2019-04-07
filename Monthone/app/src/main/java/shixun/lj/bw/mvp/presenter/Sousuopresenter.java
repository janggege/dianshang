package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;

import shixun.lj.bw.mvp.inteface.Shousuointerface;
import shixun.lj.bw.mvp.model.Sousuomodel;

public class Sousuopresenter implements Shousuointerface.Shousuopresenterlis {

    private Sousuomodel sousuomodel;
    private SoftReference<Sousuomodel> sousuomodelSoftReference;
    Shousuointerface.Shousuoview shousuoview;

    @Override
    public void attchview(Shousuointerface.Shousuoview shousuoview) {
        sousuomodel = new Sousuomodel();
        sousuomodelSoftReference = new SoftReference<>(sousuomodel);
        this.shousuoview = shousuoview;

    }

    @Override
    public void detachview() {
        sousuomodelSoftReference.clear();
    }

    @Override
    public void getdata(String s, int page) {
        sousuomodel.Request(s, page, new Shousuointerface.Shoumodel.Callback() {
            @Override
            public void onchenggong(String data) {

                shousuoview.ssview(data);
            }

            @Override
            public void onshibai() {

            }
        });

    }
}
