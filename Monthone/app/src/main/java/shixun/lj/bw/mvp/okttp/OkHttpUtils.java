package shixun.lj.bw.mvp.okttp;

import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/*
  name:刘江
  data:2019   //    单例模式     懒汉式     先声明      后实例化
*/
public class OkHttpUtils {
    public static OkHttpUtils okHttpUtils = null;


    //私有的构造方法
    public OkHttpUtils() {

    }

    //返回公共的静态的实例方法
    public static OkHttpUtils getinstance() {
        if (okHttpUtils == null) {
            //同步锁
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    //声明
    public static OkHttpClient okHttpClient = null;

    public static synchronized OkHttpClient getokhttpclient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("eeeeeeee", "log: " + message);
                }
            });

            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .writeTimeout(1000, TimeUnit.MILLISECONDS)
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .addInterceptor(httpLoggingInterceptor)//添加日志拦截器
                    .build();


        }
        return okHttpClient;

    }

    //封装  get
    public void doGet(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        getokhttpclient().newCall(request).enqueue(callback);

    }

    //封装  get1
    public void doGet1(String url, final String userId, final String sessionId, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {//添加应用拦截器
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("userId", userId)
                                .addHeader("sessionId", sessionId)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        getokhttpclient().newCall(request).enqueue(callback);
    }


    //网络请求封装post请求
    public void doPost(String url, Map<String, String> map, Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : map.keySet()) {
            builder.add(key, map.get(key));
        }

        //构建
        FormBody build = builder.build();
        Request request = new Request.Builder().post(build).url(url).build();
        getokhttpclient().newCall(request).enqueue(callback);
    }

    //网络请求封装post请求
    public void doPost1(String url, final String userId, final String sessionId, Map<String, String> map, Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : map.keySet()) {
            builder.add(key, map.get(key));
        }
        okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("userId", userId)
                        .addHeader("sessionId", sessionId)
                        .build();

                return chain.proceed(request);
            }
        }).build();

        //构建
        FormBody build = builder.build();
        Request request = new Request.Builder().post(build).url(url).build();
        getokhttpclient().newCall(request).enqueue(callback);
    }



    public void doput(String url, final String userId, final String sessionId, String data, Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("data", data);//请求体
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {//添加应用拦截器
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("userId", userId)//请求头
                                .addHeader("sessionId", sessionId)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        FormBody build = builder.build();
        Request request = new Request.Builder().url(url).put(build).build();
        getokhttpclient().newCall(request).enqueue(callback);

    }

}
