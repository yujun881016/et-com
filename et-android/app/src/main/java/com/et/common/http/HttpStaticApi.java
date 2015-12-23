package com.et.common.http;

public class HttpStaticApi {

//    public static final String BASE_URL = "http://test.uccc.cc/api/api/v1/";
//    public static final String BASE_URL = "http://dev.uccc.cc/api/v1/";
	public static final String BASE_URL = "https://jingle.uccc.cc/api/v1/";

    /**登录*/
	public static final String LOGIN = "security/users/login";
	/**获取手机验证码地址*/
	public static final String SEND_SMS_CODE = "security/users/send_sms_code";
	/**验证手机验证码地址*/
	public static final String CHECK_CODE = "security/users/check_code";
	/**用户设置密码地址*/
	public static final String SET_PASSWORD = "security/users/set_password";
	/**用户修改密码地址*/
	public static final String UPDATE_PASSWORD = "security/users/{salesmenId}/updatePassword";
    /**设置头像*/
	public static final String AVATAR = "security/{userId}/profile/avatar";//"users/{userId}/profile/avatar";//不需要
	/**修改手机号*/
	public static final String MODIFY_PHONE = "security/users/{userId}/modify_phone";
	/**获取用户信息\更新用户信息*/
	public static final String PROFILE = "security/users/{salesmanId}/profile";
    /**获取联系人列表*/
    public static final String ALL_CONTACTS = "contacts/tenants/{tenantID}";
	/**创建联系人*/
	public static final String BUILD_CONTACT = "contacts/tenants/{tenantID}";
	/**任务列表、创建任务*/
	public static final String TASK = "tasks/tenants/{tenantID}";
	/**任务更新*/
	public static final String EDIT_TASK = TASK+"/{taskID}";
	/**编辑联系人*/
	public static final String EDIT_CONTACT = "contacts/tenants/{tenantID}/{contactsID}";
	/**打电话*/
	public static final String MAKE_CALL = "callcenter/tenants/{tenantId}/{salesmenId}/{thisDN}/{otherDN}/{isHidden}";
	/**查找电话录音*/
	public static final String FIND_RECORD = "callcenter/tenants/{tenantId}/record/{id}/{callId}";//"/callcenter/api/v1/callcenter/record/{id}/{callId}";
    /**联系人录音记录*/
	public static final String CONTACT_RECORDS = "callcenter/tenants/{tenantId}/records/{salesmenId}/{otherDN}";
	/** 上传房源图片*/
	public static final String ADD_IMAGES = "resource/images/tenants/{tenantId}";
    /**房源列表、创建*/
    public static final String REALTY = "resource/houses/tenants/{tenantId}";
	/**房源 更新、详情*/
	public static final String REALTY_UPDATE_DETAIL = REALTY+"/{houseId}";
	/**我的消息*/
	public static final String MINE_MESSAGE = "myconfig/message/tenants/{tenantId}/{token}";
	/**消息置为已读*/
	public static final String MINE_MESSAGE_READ = "myconfig/message/tenants/{tenantId}/{uuid}/{flag}";
	/**获取推广验证码*/
	public static final String MINE_RECOMMEND = "security/sysconfig/tenants/{tenantID}/qrcode";
}
