package shixun.lj.bw.mvp.inteface;

/*Time:2019/3/26
 *Author:刘江
 *Description:
 */public interface Byinterface {
    interface Bpresenter {
        void attachbview(Bview bview);

        void detachbview();

        void getbdata(String uid, String sid);
    }

    interface Bview {
        void Bfview(String data);
    }

    interface Bmodel {
        void requestdata(String uid, String sid, Callback Callback);

        interface Callback {
            void onchenggong(String data);

            void onshibai();

        }

    }
}
