package com.et.common.base;

import android.app.Application;


import com.et.common.util.SPTool;
import com.et.common.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Description: application基类
 * Created by Wangchao on 15/12/17 17:35.
 * Author: Wangchao
 * Email: super0086@qq.com
 * Version: V1.0
 */
public class BaseApplication extends Application{

    private static final String SPTOOL_TIME_DELTA_BETWEEN_SERVER_CLIENT = "sptool_time_delta_between_server_client";

    @Override
    public void onCreate() {
        super.onCreate();
        SPTool.init(this);
    }

    public static long getDeltaBetweenServerAndClientTime(String httpResponseDate) {
        long deltaBetweenServerAndClientTime = 0;
        try {
            if (StringUtil.isEmpty(httpResponseDate)) {
                deltaBetweenServerAndClientTime = SPTool.getLong(SPTOOL_TIME_DELTA_BETWEEN_SERVER_CLIENT, 0);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                Date serverDateUAT = sdf.parse(httpResponseDate);
                deltaBetweenServerAndClientTime = serverDateUAT.getTime() - System.currentTimeMillis();
                SPTool.saveLong(SPTOOL_TIME_DELTA_BETWEEN_SERVER_CLIENT,deltaBetweenServerAndClientTime);
            }
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            return deltaBetweenServerAndClientTime;
        }
    }
}
