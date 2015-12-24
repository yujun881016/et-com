package com.et.activity;


import com.et.common.base.BaseApplication;
import com.et.common.util.SPTool;

/**
 * @author Administrator 全局存储容器 整个应用程序唯一实例 描述： 当android程序启动时系统会创建一个
 *         application对象，用来存储系统的一些 信息。
 *         android系统会为每个程序运行时创建一个Application类的对象且仅创建一个(单例)。
 *         且application对象的生命周期是整个程序中最长的，它的生命周期就等于这个程序的生命周期。
 *         因为它是全局的单例的，所以在不同的Activity,Service中获得的对象都是同一个对象。
 *         所以通过Application来进行一些，数据传递，数据共享,数据缓存等操作。
 */

public class EtApplication extends BaseApplication {

    private static EtApplication application;

    public static EtApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public void logout() {
        SPTool.clear();//登出后清除临时保存数据
    }
}