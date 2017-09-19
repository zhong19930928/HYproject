package com.example.yh.yhproject;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import core.OtCrashHandler;
import okhttp3.OkHttpClient;

/**
 * Created by suhu on 2017/4/8.
 */

public class SportApp extends Application{


    private static SportApp mInstance;
    public Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        //配置Cookie(包含Session)
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        //设置可访问所有的https网站
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .cookieJar(cookieJar)
                //配置Log
                .addInterceptor(new LoggerInterceptor("TAG"))
                //https
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();

        OkHttpUtils.initClient(okHttpClient);

        // 测试 注意上线时要一定要去掉
        OtCrashHandler otCrashHandler = OtCrashHandler.getInstance();
        otCrashHandler.init(this);
        mInstance = this;

    }

    public Context getContext() {
        if (context == null){
            context = this.getApplicationContext();
        }
        return context;
    }

    public static SportApp getInstance() {
        return mInstance;

    }
}
