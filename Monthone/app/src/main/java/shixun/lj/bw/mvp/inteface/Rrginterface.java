package shixun.lj.bw.mvp.inteface;

import java.util.Map;

public interface Rrginterface {
    public interface Regpresenter {
        void attachview(Regview Regview);

        void detachvirew();

        void getdata(Map<String, String> list);
    }

    public interface Regview {
        void Rview(String data);
    }

    public interface Regmodle {
        void Requset(Map<String, String> list, Callback claaback);

        interface Callback {
            void onchenggong(String data);

            void onerror();
        }
    }


}
