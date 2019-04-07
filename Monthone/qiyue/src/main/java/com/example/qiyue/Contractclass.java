package com.example.qiyue;

public interface Contractclass {
    //p层
    public interface DataPresenter {

        public void attachView(Contractclass.Dataview dataview);

        public void destory();

        public void requestData();
    }

    //view层
    public interface Dataview {
        public void showBannnerDada(String data);

    }

    //model层
    public interface dataModel {

        public void responeseMode(Callback callback);

        public interface Callback {
            public void getBannnerDada(String data);
        }

    }


}
