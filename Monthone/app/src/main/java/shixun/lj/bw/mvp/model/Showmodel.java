package shixun.lj.bw.mvp.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Showinterface;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

public class Showmodel implements Showinterface.Showmodellins {
    @Override
    public void Request(final Callback callback) {
        String url = "http://172.17.8.100/small/commodity/v1/commodityList";
        OkHttpUtils.getinstance().doGet(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onchenggong(response.body().string());

            }
        });
    }
}
