package com.et.common.http;

import com.et.common.base.BaseApplication;
import com.et.common.util.L;
import com.et.common.util.MD5;
import com.et.common.util.SPTool;
import com.et.common.util.UIUtils;
import com.et.common.util.Utils;
import com.et.constants.Constants;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Description: MD5 interceptor
 * Created by Wangchao on 15/11/16 14:11.
 * Author: Wangchao
 * Email: super0086@qq.com
 * Version: V1.0
 */
public class MD5Interceptor implements Interceptor{
    private static final String DATE_SIGN = "date";

    private boolean isResetTimeStamp = false;

    public MD5Interceptor(boolean isResetTimeStamp) {
        this.isResetTimeStamp = isResetTimeStamp;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String deviceId = "";
        long currentTime = System.currentTimeMillis()+20000;

        if (isResetTimeStamp) {
            deviceId = Utils.getIMEI(UIUtils.getContext())+"_"+currentTime;
            SPTool.saveString(Constants.SPTOOL_DEVICEID_TIME, deviceId);
        } else {
            deviceId = SPTool.getString(Constants.SPTOOL_DEVICEID_TIME, "");
        }

        String mdString = currentTime + "\n" + request.method() + ":" + request.httpUrl() + "\n"+deviceId+":" + "UCCC@Jingle2015@)!%";
        String md5 = MD5.md5(mdString);
        L.i("MD5Interceptor", currentTime + "\n" + request.httpUrl() + "\n" + md5);
        Request newRequest = chain.request().newBuilder()
                .addHeader("Expire-In", ""+currentTime)
                .addHeader("Client-Id", "UCCC")
                .addHeader("Device-Id", deviceId)
//                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization-Token", md5)
                .build();
        Response response = chain.proceed(newRequest);
        String date = response.header(DATE_SIGN);
        BaseApplication.getDeltaBetweenServerAndClientTime(date);
        return response;
    }
}
