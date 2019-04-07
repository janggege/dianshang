package shixun.lj.bw.mvp.inteface;

/*Time:2019/3/29
 *Author:刘江
 *Description:
 */public interface Addressinterface {
    interface addresspresenter {
        void addressAttachview(Addressview addressviewl);

        void addressDetachview();

        void addressQequest(String uid, String sid);
    }

    interface Addressview {
        void Addressview(String data);
    }

    interface Addressmodel {
        void Addressmodel(String uid, String sid, Callback callback);

        interface Callback {
            void chenggong(String data);

            void shibai();
        }

    }


}
