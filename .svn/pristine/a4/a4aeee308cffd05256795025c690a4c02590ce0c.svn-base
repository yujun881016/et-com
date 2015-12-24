package com.et.common.base;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.et.activity.MainActivity;
import com.et.activity.R;
import com.et.beans.EventNull;
import com.et.common.util.T;
import com.et.common.util.UIUtils;

import de.greenrobot.event.EventBus;

@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment  {
	protected View rootView;
	private View main_actionbar;
	protected ProgressDialog waitDialog;
	@Override
	public void onStart() {
		super.onStart();
		if (!EventBus.getDefault().isRegistered(this)) {
			EventBus.getDefault().register(this);
		}
	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if (rootView == null) {
			initTitle();
			initView();
		} else {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null) {
				parent.removeView(rootView);
			}
			return rootView;
		}
		initData();
        initListener();
        return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	protected abstract void initTitle();
	protected abstract void initView();
	protected abstract void initListener();
	protected abstract void initData();


	@Override
	public void onDestroy() {
		//注销EventBus
		//EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	@Override
	public void onPause() {
        super.onPause();
        dismissWaitDialog();
        //注销EventBus
        EventBus.getDefault().unregister(this);
    }

	@Override
	public void onStop() {
		super.onStop();
		//注销EventBus
		//EventBus.getDefault().unregister(this);
	}

	@Override
	public void onResume() {
		// 判断界面再次转到前台的时候,主题是否发生改变.如果改变了,则需要重新加载activity
		//页面再次跳转到前台时，重新设置页面的头布局和事件监听
		initTitle();
		super.onResume();
	}


	protected void setRoot(boolean isRoot){
		main_actionbar =  getActivity().findViewById(R.id.tab_bar_layout);
		if (isRoot){
			main_actionbar.setVisibility(View.VISIBLE);
		}else{
			main_actionbar.setVisibility(View.GONE);
		}
	}

	public void showWaitDialog() {
		View v = UIUtils.inflate(R.layout.loading_dialog);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
		// 加载动画
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				getActivity(), R.anim.rotate);
		// 使用ImageView显示动画
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);

		waitDialog = new ProgressDialog(getActivity(), R.style.CustomProgressDialog);// 创建自定义样式dialog

		waitDialog.setCancelable(false);// 不可以用“返回键”取消
		waitDialog.show();
		waitDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
	}



	public void backSuccessHttp(String data, int method) {
		dismissWaitDialog();
	}

	public void backFailureHttp(String data, int method) {
		dismissWaitDialog();
	}
	public void dismissWaitDialog() {
		MainActivity.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (waitDialog != null && waitDialog.isShowing()) {
					waitDialog.dismiss();
					waitDialog = null;
				}
			}
		});

	}
	/**
	 * 结束当前fragment
	 * @param fragment
	 */
	public void remove(Fragment fragment){
		getFragmentManager().beginTransaction().remove(fragment).commit();
	}


	public void onEventMainThread(EventNull eventNull) {
		dismissWaitDialog();
		if (eventNull!=null && eventNull.getHttpStatusCode()>399 && eventNull.getHttpStatusCode()<600) {
			T.showShort(getActivity(), "服务器繁忙,请稍后再试!");
		}
	}
}
