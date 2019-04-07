package shixun.lj.bw.mvp.inteface;

import java.util.Map;

/*Time:2019/4/2
 *Author:刘江
 *Description:
 */public interface Chuangjiandingdan {
    interface chuangpresenter {
        void attachview(chuangview chuangview);

        void detachview();

        void getData(String uid,String sid,Map<String,String>map);
    }

    interface chuangview {
        void chuangview(String data);
    }

    interface chuanmodel {
        void chuanmodel(String uid,String sid,Map<String,String>map, Callback callback);

        interface Callback {
            void chenggong(String data);

            void shibai();
        }
    }
}
