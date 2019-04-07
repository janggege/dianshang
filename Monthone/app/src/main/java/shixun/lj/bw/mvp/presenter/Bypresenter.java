package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;

import shixun.lj.bw.mvp.inteface.Byinterface;
import shixun.lj.bw.mvp.model.Bymodel;

/*Time:2019/3/26
 *Author:刘江
 *Description:
 */public class Bypresenter implements Byinterface.Bpresenter {
    Byinterface.Bview bview;
    private SoftReference<Bymodel> bymodelSoftReference;
    private Bymodel bymodel;

    @Override
    public void attachbview(Byinterface.Bview bview) {
        bymodel = new Bymodel();
        bymodelSoftReference = new SoftReference<>(bymodel);
        this.bview = bview;

    }

    @Override
    public void detachbview() {
        bymodelSoftReference.clear();
    }


    @Override
    public void getbdata(String uid, String sid) {
        bymodel.requestdata(uid, sid, new Byinterface.Bmodel.Callback() {
            @Override
            public void onchenggong(String data) {
                bview.Bfview(data);

            }

            @Override
            public void onshibai() {

            }
        });
    }


}
