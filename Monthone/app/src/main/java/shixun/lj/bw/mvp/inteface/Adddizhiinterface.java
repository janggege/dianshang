package shixun.lj.bw.mvp.inteface;

import java.util.Map;

/*Time:2019/3/30
 *Author:刘江
 *Description:
 */public interface Adddizhiinterface {
    interface adddizhipresenter {
        void attachview(dizhiview dizhiview);

        void detachview();

        void getdata(String uid, String sid, Map<String, String> map);
    }

    interface dizhiview {
        void Adddizhi(String data);
    }

    interface adddizhimodel {
        void Requestdata(String uid, String sid, Map<String, String> map, Callback callback);

        interface Callback {
            void onchenggong(String data);

            void shibai();
        }
    }
}
