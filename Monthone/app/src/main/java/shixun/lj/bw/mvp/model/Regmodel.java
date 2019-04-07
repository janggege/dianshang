package shixun.lj.bw.mvp.model;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Rrginterface;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

public class Regmodel implements Rrginterface.Regmodle {
    @Override
    public void Requset(Map<String, String> list, final Callback claaback) {
        String url = "http://172.17.8.100/small/user/v1/register";
        OkHttpUtils.getinstance().doPost(url, list, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                claaback.onerror();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                claaback.onchenggong(response.body().string());
            }
        });
    }
}
