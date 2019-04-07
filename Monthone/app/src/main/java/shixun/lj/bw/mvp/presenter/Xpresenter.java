package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;

import shixun.lj.bw.mvp.inteface.Xinagqing;
import shixun.lj.bw.mvp.model.Xmodel;

/*Time:2019/3/25
 *Author:刘江
 *Description:
 */public class Xpresenter implements Xinagqing.Xpresenter {
    Xinagqing.Xview xview;
    private Xmodel xmodel;
    private SoftReference<Xmodel> xmodelSoftReference;

    @Override
    public void attachview(Xinagqing.Xview xview) {
        xmodel = new Xmodel();
        xmodelSoftReference = new SoftReference<>(xmodel);
        this.xview = xview;

    }

    @Override
    public void detachview() {
        xmodelSoftReference.clear();
    }

    @Override
    public void getdata(int id) {
        xmodel.onrequestdata(id, new Xinagqing.Xmodle.Callback() {
            @Override
            public void suess(String data) {
                xview.Xview(data);
            }

            @Override
            public void onerror() {

            }
        });

    }
}
