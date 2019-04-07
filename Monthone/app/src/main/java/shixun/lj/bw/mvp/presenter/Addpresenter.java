package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;

import shixun.lj.bw.mvp.inteface.Addinterface;
import shixun.lj.bw.mvp.model.Addmodel;

/*Time:2019/3/28
 *Author:刘江
 *Description:
 */public class Addpresenter implements Addinterface.Addpresenter {
    Addinterface.Addview Addview;
    private Addmodel addmodel;
    private SoftReference<Addmodel> softReference;

    @Override
    public void addattachview(Addinterface.Addview Addview) {
        addmodel = new Addmodel();
        softReference = new SoftReference<>(addmodel);
        this.Addview = Addview;

    }

    @Override
    public void adddetachview() {
        softReference.clear();
    }

    @Override
    public void getadd(String data, String uid, String sid) {
        addmodel.Requestadddata(data, uid, sid, new Addinterface.Addmodel.Callbanck() {
            @Override
            public void chenggong(String fandta) {
                Addview.addview(fandta);
            }

            @Override
            public void shibai() {

            }
        });

    }
}
