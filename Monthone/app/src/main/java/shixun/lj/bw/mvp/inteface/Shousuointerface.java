package shixun.lj.bw.mvp.inteface;

public interface Shousuointerface {
    interface Shousuopresenterlis {
        void attchview(Shousuoview shousuoview);

        void detachview();

        void getdata(String s, int page);
    }

    interface Shousuoview {
        void ssview(String data);
    }

    interface Shoumodel {
        void Request(String s, int page, Callback callback);

        interface Callback {
            void onchenggong(String data);

            void onshibai();
        }
    }
}
