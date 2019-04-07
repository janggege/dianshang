package shixun.lj.bw.mvp.model;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Chuangjiandingdan;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

/*Time:2019/4/2
 *Author:刘江
 *Description:
 */public class Chuangjianmodel implements Chuangjiandingdan.chuanmodel {

    @Override
    public void chuanmodel(String uid, String sid, Map<String, String> map, final Callback callback) {
        String url = "http://172.17.8.100/small/order/verify/v1/createOrder";
        OkHttpUtils.getinstance().doPost1(url, uid, sid, map,new okhttp3.Callback() {
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
