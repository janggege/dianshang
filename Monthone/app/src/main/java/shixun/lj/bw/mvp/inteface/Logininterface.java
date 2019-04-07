package shixun.lj.bw.mvp.inteface;

import java.util.Map;

public interface Logininterface {
     interface Loginpersenter {
        void attachview(Loginview loginview);

        void detachview();

        void getdata(Map<String, String> list);
    }

    public interface Loginview {
        void Loginviewclick(String data);

    }

    public interface Logmodelclick {
        void Requestdata(Map<String, String> list, Callback callback);

        public interface Callback {
            void onchenggong(String data);

            void error();
        }

    }
}
