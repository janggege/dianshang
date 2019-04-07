package shixun.lj.bw.mvp.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Addressinterface;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

/*Time:2019/3/29
 *Author:刘江
 *Description:
 */public class Addressmodel implements Addressinterface.Addressmodel {
    @Override
    public void Addressmodel(String uid, String sid, final Callback callback) {
        String url = "http://172.17.8.100/small/user/verify/v1/receiveAddressList";
        OkHttpUtils.getinstance().doGet1(url, uid, sid, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.chenggong(response.body().string());

            }
        });
    }
}
