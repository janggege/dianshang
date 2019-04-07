package shixun.lj.bw.mvp.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.mvp.inteface.Quanziinterface;
import shixun.lj.bw.mvp.okttp.OkHttpUtils;

/*Time:2019/3/28
 *Author:刘江
 *Description:
 */public class Quanzimodel implements Quanziinterface.Quanzimodel {
    @Override
    public void getdata(int page, int count, final Callback Callback) {
        String url = "http://172.17.8.100/small/circle/v1/findCircleList?page=" + page + "&&count=" + count + "";
        OkHttpUtils.getinstance().doGet(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Callback.chenggong(response.body().string());
            }
        });
    }
}
