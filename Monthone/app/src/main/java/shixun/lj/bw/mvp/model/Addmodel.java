package shixun.lj.bw.mvp.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Addinterface;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

/*Time:2019/3/28
 *Author:刘江
 *Description:
 */public class Addmodel implements Addinterface.Addmodel {
    @Override
    public void Requestadddata(String data, String uid, String sid, final Callbanck callbanck) {
        String url = "http://172.17.8.100/small/order/verify/v1/syncShoppingCart";
        OkHttpUtils.getinstance().doput(url, uid, sid, data, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callbanck.chenggong(response.body().string());

            }
        });

    }
}
