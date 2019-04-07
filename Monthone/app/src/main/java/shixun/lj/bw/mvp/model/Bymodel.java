package shixun.lj.bw.mvp.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Byinterface;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

/*Time:2019/3/26
 *Author:刘江
 *Description:
 */public class Bymodel implements Byinterface.Bmodel {
    @Override
    public void requestdata(String uid, String sid, final Callback Callback) {
        String url = "http://172.17.8.100/small/order/verify/v1/findShoppingCart";
        OkHttpUtils.getinstance().doGet1(url, uid, sid, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Callback.onshibai();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Callback.onchenggong(response.body().string());

            }
        });

    }
}
