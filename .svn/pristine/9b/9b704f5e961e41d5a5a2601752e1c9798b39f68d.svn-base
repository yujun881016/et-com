package com.et.common.util;


import android.util.Log;

import com.et.activity.BuildConfig;
import com.et.constants.Constants;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Log统一管理类
 * 
 * @author way
 * 
 */
public class L {
	public static boolean isDebug = BuildConfig.DEBUG;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	private static final String TAG = "way";

	// 下面四个是默认tag的函数
	public static void i(String msg) {
		if (isDebug)
			Log.i(TAG, msg);
	}

	public static void d(String msg) {
		if (isDebug)
			Log.d(TAG, msg);
	}

	public static void e(String msg) {
		if (isDebug)
			Log.e(TAG, msg);
	}

	public static void v(String msg) {
		if (isDebug)
			Log.v(TAG, msg);
	}
	//下面是传入类名打印log
	public static void i(Class<?> _class,String msg){
		if (isDebug)
			Log.i(_class.getName(), msg);
	}
	public static void d(Class<?> _class,String msg){
		if (isDebug)
			Log.i(_class.getName(), msg);
	}
	public static void e(Class<?> _class,String msg){
		if (isDebug)
			Log.i(_class.getName(), msg);
	}
	public static void v(Class<?> _class,String msg){
		if (isDebug)
			Log.i(_class.getName(), msg);
	}
	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}
	/**
	 * 新建txt文件,写入日志内容
	 * @param content
	 * @return
	 */
	public static synchronized boolean writeLog(String content) {
		boolean bRet = false;
		try {
			if (isDebug) {
				String dateStr = TimeUtils.getCurrentTimestampForString().substring(0, 15);// 当天日期10分钟保存一个日志文件
				String logfilePath = Constants.ET_PATH_LOG + "/log_" + dateStr + ".txt";
				FileOutputStream fs = null;
				File logPath = new  File(Constants.ET_PATH_LOG);
				if (!logPath.exists()) {
					logPath.mkdirs();
				}
				File logfile = new File(logfilePath);
				if (!(logfile.exists())) {// 不存在，则新建
					logfile.createNewFile();
					fs = new FileOutputStream(logfilePath);
				} else {// 尾部追加的方式
					fs = new FileOutputStream(logfilePath, true);
				}

				content = TimeUtils.getCurrentTimestampForString() + " " + content + "\r\n";//
				byte[] bytes = content.getBytes();
				fs.write(bytes, 0, bytes.length);
				fs.close();
				byte[] b2 = new byte[bytes.length];
				for (int i = 0; i < bytes.length; i++) {
					bytes[i] = b2[i];
				}
				bRet = true;
			}
		} catch (Exception e) {
			L.e("***writeLog**", "写日志异常");
			e.printStackTrace();
			bRet = false;
		} finally {
			return bRet;

		}
	}
}
