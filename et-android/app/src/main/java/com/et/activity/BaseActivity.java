package com.et.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;

/**
 * Created by YuJun on 15/12/22.
 * 14:14.
 * Email: 614977826@qq.com.
 */
public abstract class BaseActivity extends Activity{
    protected ContentResolver contentResolver;
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        contentResolver = getContentResolver();
//        initView();
//        initTitle();
//        initListener();
    }
    protected abstract void initTitle();
    protected abstract void initView();
    protected abstract void initListener();

}
