package shixun.lj.bw.mvp.inteface;

/*Time:2019/3/25
 *Author:刘江
 *Description:
 */public interface Xinagqing {
    interface Xpresenter {
        void attachview(Xview xview);

        void detachview();

        void getdata(int id);
    }

    interface Xview {
        void Xview(String data);
    }

    interface Xmodle {
        void onrequestdata( int id,Callback callback);
        interface Callback {
            void suess(String data);
            void onerror();
        }
    }
}
