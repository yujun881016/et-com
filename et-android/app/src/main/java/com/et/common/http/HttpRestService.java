package com.et.common.http;

import android.os.RecoverySystem;

import com.et.module.Interface.service.ServerAPIService;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Retrofit;

/**
 * Description: http rest网络请求服务入口
 * Created by Wangchao on 15/11/15 16:36.
 * Author: Wangchao
 * Email: super0086@qq.com
 * Version: V1.0
 */
public class HttpRestService {

    private static final long CONNECTION_TIMEOUT = 90;//默认超时时间为90秒
    private ServerAPIService service;
    private LogsInterceptor logsInterceptor;

    private HttpRestService() {
    }

    private static class HttpRestServiceHolder {
        private static final HttpRestService INSTANCE = new HttpRestService();
    }

    public static final HttpRestService getInstance() {
        return HttpRestServiceHolder.INSTANCE;
    }


    public ServerAPIService getRetrofitService() {
        return getRetrofitService(false, null, null);
    }

    /**
     * 重置时间戳,默认使用本地sptool
     */
    public ServerAPIService getRetrofitService(boolean isResetTimeStamp) {
        return getRetrofitService(isResetTimeStamp, null, null);
    }

    public ServerAPIService getRetrofitService(File file) {
        return getRetrofitService(false, null, file);
    }

    public synchronized ServerAPIService getRetrofitService(boolean isResetTimeStamp, RecoverySystem.ProgressListener progressListener, File file) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        client.setReadTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        client.setWriteTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        Interceptor md5Interceptor = new MD5Interceptor(isResetTimeStamp);
        if (logsInterceptor == null) {
            logsInterceptor = new LogsInterceptor();
            logsInterceptor.setLevel(LogsInterceptor.Level.BODY);
        }
        client.interceptors().add(md5Interceptor);
        client.interceptors().add(logsInterceptor);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(HttpStaticApi.BASE_URL)
                .client(client);
        Retrofit retrofit = builder.build();

        service = retrofit.create(ServerAPIService.class);
        return service;
    }

    public void requestRestHttp(Call call, BaseCallback callback) {
        try {
            call.enqueue(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
