package shixun.lj.bw.mvp.presenter;

import java.lang.ref.SoftReference;
import java.util.Map;

import shixun.lj.bw.mvp.inteface.Chuangjiandingdan;
import shixun.lj.bw.mvp.model.Chuangjianmodel;

/*Time:2019/4/2
 *Author:刘江
 *Description:
 */public class Chuangjianpresenter implements Chuangjiandingdan.chuangpresenter {
    Chuangjiandingdan.chuangview chuangview;
    private Chuangjianmodel chuangjianmodel;
    private SoftReference<Chuangjianmodel> chuangjianmodelSoftReference;

    @Override
    public void attachview(Chuangjiandingdan.chuangview chuangview) {
        chuangjianmodel = new Chuangjianmodel();
        chuangjianmodelSoftReference = new SoftReference<>(chuangjianmodel);
        this.chuangview = chuangview;

    }

    @Override
    public void detachview() {
        chuangjianmodelSoftReference.clear();
    }

    @Override
    public void getData(String uid, String sid,Map<String, String> map) {
        chuangjianmodel.chuanmodel(uid, sid,map, new Chuangjiandingdan.chuanmodel.Callback() {
            @Override
            public void chenggong(String data) {
                chuangview.chuangview(data);
            }

            @Override
            public void shibai() {

            }
        });
    }
}
