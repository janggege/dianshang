package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;

import shixun.lj.bw.mvp.inteface.Addressinterface;
import shixun.lj.bw.mvp.model.Addressmodel;

/*Time:2019/3/29
 *Author:刘江
 *Description:
 */public class Addrsspresenter implements Addressinterface.addresspresenter {

    private Addressmodel addressmodel;
    private SoftReference<Addressmodel> addressmodelSoftReference;
    Addressinterface.Addressview addressviewl;

    @Override
    public void addressAttachview(Addressinterface.Addressview addressviewl) {
        addressmodel = new Addressmodel();
        addressmodelSoftReference = new SoftReference<>(addressmodel);
        this.addressviewl = addressviewl;


    }

    @Override
    public void addressDetachview() {
        addressmodelSoftReference.clear();
    }

    @Override
    public void addressQequest(String uid, String sid) {
        addressmodel.Addressmodel(uid, sid, new Addressinterface.Addressmodel.Callback() {
            @Override
            public void chenggong(String data) {
                addressviewl.Addressview(data);
            }

            @Override
            public void shibai() {

            }
        });
    }
}
