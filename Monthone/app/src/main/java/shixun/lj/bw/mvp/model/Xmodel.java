package shixun.lj.bw.mvp.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Xinagqing;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

/*Time:2019/3/25
 *Author:刘江
 *Description:
 */public class Xmodel implements Xinagqing.Xmodle {
    @Override
    public void onrequestdata(int id, final Callback callback) {
        String url = "http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=" + id;
        OkHttpUtils.getinstance().doGet(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onerror();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.suess(response.body().string());

            }
        });
    }
}
