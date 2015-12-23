package com.et.constants;


import com.et.common.util.Utils;

/**
 * Created by YuJun on 15/10/26.
 *
 */
public class Constants {
    public static final boolean UMENG_LOG_IS_ENCRYPT = false;

    /**应用文件根目录*/
    public static final String ET_PATH_ROOT = Utils.getAppDir()+"com.uccc.jingle/";
    /**应用日志目录*/
    public static final String ET_PATH_LOG = ET_PATH_ROOT+"logs/";
    /**应用上传图片目录*/
    public static final String ET_PATH_UPLOAD_IMAGE = ET_PATH_ROOT+"upload_image/";
    /**上传图片大小限制KB*/
    public static final int UPLOAD_IMAGE_MIN_BYTE = 150;
    /**全量一次拉取数据条数最大值*/
    public static final int PULL_SERVER_DATA_COUNT_MAX = 100_000;
    /**分页拉取数据条数最大值*/
    public static final int LOAD_MORE_SERVER_DATA_COUNT_MAX = 20;
    public static final String FRAGMENT_LOGO = "fragment_logo";
    public static final String FRAGMENT_PARAMS = "fragment_params";
    /**sptool保存key*/
    public static final String SPTOOL_DEVICEID_TIME = "sptool_current_time";


}

