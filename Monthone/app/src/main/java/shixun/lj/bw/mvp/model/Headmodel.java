package shixun.lj.bw.mvp.model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Headinterface;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

public class Headmodel implements Headinterface.HradModel {
    @Override
    public void Requestdata(final Callback callback) {
        String url = "http://172.17.8.100/small/commodity/v1/bannerShow";
        OkHttpUtils.getinstance().doGet(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.error();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.sucess(response.body().string());

            }
        });


    }
}
