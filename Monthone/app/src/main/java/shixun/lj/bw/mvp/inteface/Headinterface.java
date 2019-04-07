package shixun.lj.bw.mvp.inteface;

public interface Headinterface {
    interface Headpresenetr {
        void attachview(Hview hview);

        void detachview();

        void getdata();
    }

    public interface Hview {
        void Hview(String data);
    }

    public interface HradModel {
        void Requestdata(Callback callback);

        interface Callback {
            void sucess(String data);

            void error();
        }
    }

}
