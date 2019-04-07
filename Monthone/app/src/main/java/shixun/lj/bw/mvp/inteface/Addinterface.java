package shixun.lj.bw.mvp.inteface;

/*Time:2019/3/27
 *Author:刘江
 *Description:
 */public interface Addinterface {
    interface Addpresenter {
        void addattachview(Addview Addview);

        void adddetachview();

        void getadd(String data, String uid, String sid);
    }

    interface Addview {
        void addview(String fandata);
    }

    interface Addmodel {
        void Requestadddata(String data, String uid, String sid, Callbanck callbanck);

        interface Callbanck {
            void chenggong(String fandta);

            void shibai();
        }
    }

}
