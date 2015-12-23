package com.et.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.et.activity.common.util.SystemUtils;
import com.et.activity.common.util.T;

import net.simonvt.menudrawer.MenuDrawer;

/**
 * Created by YuJun on 15/12/22.
 * 14:12.
 * Email: 614977826@qq.com.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{
    private long firstTime;
    private MenuDrawer mMenuDrawer;
    private ImageView right_img,left_img;
    private TextView title_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMenuDrawer();
        mMenuDrawer.setContentView(R.layout.main_activity);
        initView();
        initListener();
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        left_img = (ImageView) findViewById(R.id.left_img);
        right_img = (ImageView) findViewById(R.id.right_img);
        title_tv = (TextView) findViewById(R.id.content_tv);
    }

    @Override
    protected void initListener() {
        left_img.setOnClickListener(this);
        right_img.setOnClickListener(this);
    }

    private void initMenuDrawer() {
        mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND); //设置MenuDrawer的打开方式 STATIC:无法移动 OVERLAY: 在界面之上  BEHIND:在界面的下面
        mMenuDrawer.setMenuSize(Math.round(0.6f * SystemUtils.getDisplayWidth(this)));//设置抽屉的大小为屏幕的0.6并且四舍五入

        mMenuDrawer.setMenuView(R.layout.menu_layout);
        mMenuDrawer.setDropShadowEnabled(false);
        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);


    }

    @Override
    public void onBackPressed() {
        if(mMenuDrawer.isMenuVisible()){
            mMenuDrawer.closeMenu(true);
            return;
        }

        if (System.currentTimeMillis() - firstTime < 3000) {
            finish();
        } else {
            firstTime = System.currentTimeMillis();
            T.showShort(this, R.string.press_again_exit);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_img:
                mMenuDrawer.openMenu();
                break;
            case R.id.right_img:

                break;
        }
    }
}
