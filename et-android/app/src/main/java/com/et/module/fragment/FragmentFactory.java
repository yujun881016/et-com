package com.et.module.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.et.activity.MainActivity;
import com.et.common.base.BaseFragment;
import com.et.common.business.BusinessInterface;
import com.et.common.util.T;
import com.et.common.util.UIUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by YuJun on 15/10/26.
 * Fragment工厂
 */
public class FragmentFactory {
    protected static Map<String,BaseFragment> mFragmentCache;

    public static BaseFragment getFragment(Class clazz){
        BaseFragment fragment = null;
        try {
            if (clazz == null) {
                fragment = null;
                T.showShort(UIUtils.getContext(), "无效业务操作");
            } else {
                if (mFragmentCache==null) {
                    mFragmentCache = new HashMap<String,BaseFragment>();
                }
                fragment = mFragmentCache.get(clazz.getName());
                if (fragment==null) {
                    //判读该业务是否实例化过
                    fragment = (BaseFragment) clazz.newInstance();
                    mFragmentCache.put(clazz.getName(),fragment);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            fragment = null;
            T.showShort(UIUtils.getContext(), "无效业务操作");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fragment = null;
            T.showShort(UIUtils.getContext(), "无效业务操作");
        } finally {
            return fragment;
        }
    }
    public static void remove(Class clazz){
        mFragmentCache.remove(clazz);
    }
    public static FragmentTransaction getTransaction(MainActivity fragmentActivity)
    {
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        return transaction;
    }
}
