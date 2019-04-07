package shixun.lj.bw.mvp.model;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Adddizhiinterface;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

/*Time:2019/3/30
 *Author:刘江
 *Description:
 */public class Dizhimodel implements Adddizhiinterface.adddizhimodel {
    @Override
    public void Requestdata(String uid, String sid, Map<String, String> map, final Callback callback) {
        String url = "http://172.17.8.100/small/user/verify/v1/addReceiveAddress";
        OkHttpUtils.getinstance().doPost1(url, uid, sid, map, new okhttp3.Callback() {
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
