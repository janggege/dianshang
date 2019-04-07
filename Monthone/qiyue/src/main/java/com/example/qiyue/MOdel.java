package com.example.qiyue;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MOdel implements Contractclass.dataModel {
    @Override
    public void responeseMode(final Callback callback) {
        String url = "http://mobile.bwstudent.com/small/commodity/v1/bannerShow";
        OkHttpUtils.getinstance().doGet(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.getBannnerDada(response.body().string());

            }
        });
    }
}
