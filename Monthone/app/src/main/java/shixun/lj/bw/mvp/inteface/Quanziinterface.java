package shixun.lj.bw.mvp.inteface;

/*Time:2019/3/28
 *Author:刘江
 *Description:
 */public interface Quanziinterface {
    public interface Quanzipersenter {

        void attachview(QuanziView quanziView);

        void detachview();

        void requestdata(int page, int count);
    }

    interface QuanziView {
        void QuanziData(String data);
    }

    interface Quanzimodel {
        void getdata(int page, int count, Callback Callback);

        interface Callback {
            void chenggong(String data);

            void shibai();

        }
    }
}
