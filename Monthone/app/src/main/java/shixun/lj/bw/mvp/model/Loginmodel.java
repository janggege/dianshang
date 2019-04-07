package shixun.lj.bw.mvp.model;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Logininterface;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

public class Loginmodel implements Logininterface.Logmodelclick {
    private String url = "http://172.17.8.100/small/user/v1/login";

    @Override
    public void Requestdata(Map<String, String> list, final Callback callback) {
        OkHttpUtils.getinstance().doPost(url, list, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.error();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onchenggong(response.body().string());
            }
        });

    }
}
