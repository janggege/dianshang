package com.example.qiyue;

import java.lang.ref.SoftReference;

public class Presenterttwo implements Contractclass.DataPresenter {

    private SoftReference<MOdel> mOdelSoftReference;
    private MOdel mOdel;
    Contractclass.Dataview dataview;

    @Override
    public void attachView(final Contractclass.Dataview dataview) {
        mOdel = new MOdel();
        mOdelSoftReference = new SoftReference<>(mOdel);
        this.dataview = dataview;
    }

    @Override
    public void destory() {
        mOdelSoftReference.clear();

    }

    @Override
    public void requestData() {
        mOdel.responeseMode(new Contractclass.dataModel.Callback() {
            @Override
            public void getBannnerDada(String data) {
                dataview.showBannnerDada(data);
            }
        });
    }
}
