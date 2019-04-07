package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;
import java.util.Map;

import shixun.lj.bw.mvp.inteface.Adddizhiinterface;
import shixun.lj.bw.mvp.model.Dizhimodel;

/*Time:2019/3/30
 *Author:刘江
 *Description:
 */public class Dizhipresenter implements Adddizhiinterface.adddizhipresenter {

    private Dizhimodel dizhimodel;
    private SoftReference<Dizhimodel> dizhimodelSoftReference;
    Adddizhiinterface.dizhiview dizhiview;

    @Override
    public void attachview(Adddizhiinterface.dizhiview dizhiview) {
        dizhimodel = new Dizhimodel();
        dizhimodelSoftReference = new SoftReference<>(dizhimodel);
        this.dizhiview = dizhiview;
    }

    @Override
    public void detachview() {
        dizhimodelSoftReference.clear();
    }

    @Override
    public void getdata(String uid, String sid, Map<String, String> map) {
        dizhimodel.Requestdata(uid, sid, map, new Adddizhiinterface.adddizhimodel.Callback() {
            @Override
            public void onchenggong(String data) {
                dizhiview.Adddizhi(data);
            }

            @Override
            public void shibai() {

            }
        });

    }
}
