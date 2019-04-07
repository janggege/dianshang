package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;

import shixun.lj.bw.mvp.inteface.Quanziinterface;
import shixun.lj.bw.mvp.model.Quanzimodel;

/*Time:2019/3/28
 *Author:刘江
 *Description:
 */public class Quanzipresenter implements Quanziinterface.Quanzipersenter {

    private Quanzimodel quanzimodel;
    private SoftReference<Quanzimodel> quanzimodelSoftReference;
    Quanziinterface.QuanziView quanziView;

    @Override
    public void attachview(Quanziinterface.QuanziView quanziView) {
        quanzimodel = new Quanzimodel();
        quanzimodelSoftReference = new SoftReference<>(quanzimodel);
        this.quanziView = quanziView;

    }

    @Override
    public void detachview() {
        quanzimodelSoftReference.clear();
    }

    @Override
    public void requestdata(int page, int count) {
        quanzimodel.getdata(page, count, new Quanziinterface.Quanzimodel.Callback() {
            @Override
            public void chenggong(String data) {
                quanziView.QuanziData(data);
            }

            @Override
            public void shibai() {

            }
        });

    }
}
