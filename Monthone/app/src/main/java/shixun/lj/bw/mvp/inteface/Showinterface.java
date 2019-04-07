package shixun.lj.bw.mvp.inteface;

public interface Showinterface {
    interface Showpersenterlis {
        void attachview(Showviewlins Showviewlins);

        void detachview();

        void getdata();
    }

    interface Showviewlins {
        void Showview(String data);
    }

    interface Showmodellins {
        void Request(Callback callback);

        interface Callback {
            void onchenggong(String data);

            void onshibai();
        }

    }
}
