package com.et.common.http;


import com.et.beans.EventMainEntity;
import com.et.beans.EventNull;
import com.et.common.http.response.EtResponse;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Description: 网络请求回掉基类
 * Created by Wangchao on 15/11/13 17:31.
 * Author: Wangchao
 * Email: super0086@qq.com
 * Version: V1.0
 */
public abstract class BaseCallback<T> implements Callback<T> {

    public final static int OK_CODE = 200;
    public final static int ERROR_CODE_40000 = 40000;//系统未知错误（理论上不会出现）
    public final static int ERROR_CODE_41001 = 41001;//用户未登录
    public final static int ERROR_CODE_41002 = 41002;//账号未注册
    public final static int ERROR_CODE_41003 = 41003;//账号已从该设备上被挤出，请重新登录
    public final static int ERROR_CODE_41004 = 41004;//该号码暂时被锁定
    public final static int ERROR_CODE_41005 = 41005;//发验证码短信过于频繁
    public final static int ERROR_CODE_41006 = 41006;//验证码输入错误
    public final static int ERROR_CODE_41007 = 41007;//修改/找回密码 token 无效
    public final static int ERROR_CODE_41008 = 41008;//手机号密码登录不匹配
    public final static int ERROR_CODE_41009 = 41009;//手机号被注册 // 用不到
    public final static int ERROR_CODE_41010 = 41010;//用户 id 不存在
    public final static int ERROR_CODE_41011 = 41011;//Authorization-Token无效或者Expire-In过期
    public final static int ERROR_CODE_41012 = 41012;//客户端参数非法
    public final static int ERROR_CODE_43001 = 43001;//联系人设备为空
    public final static int ERROR_CODE_46001 = 46001;//上传文件类型错误
    public final static int ERROR_CODE_46002 = 46002;//上传失败
    public final static int ERROR_CODE_47001 = 47001;//呼叫失败

    @Override
    public void onResponse(Response response, Retrofit retrofit) {
        int http_status_code = response.code();
        if (OK_CODE==http_status_code) {
            Object body = response.body();
            if(body!=null) {
                EtResponse etResponse = (EtResponse<T>) body;
                int code = etResponse.getCode();
                if (ERROR_CODE_41003 == code) {
                    EventMainEntity entity = new EventMainEntity();
                    entity.setType(EventMainEntity.TYPE_FLAG_RESPONSE);
                    entity.setResponse(response);
                    EventBus.getDefault().post(response);
                } else {
                    onSuccess(response, retrofit);
                }
            } else {
                //  15/12/15 数据为空无法解析
                EventNull entity = new EventNull(http_status_code);
                EventBus.getDefault().post(entity);
            }
        } else {
            //  15/12/15 发生4xx 5xx 错误
            EventNull entity = new EventNull(http_status_code);
            EventBus.getDefault().post(entity);
            /*RetrofitThrowable t = new RetrofitThrowable();
            String message = response.message();
            t.setErrorCode(http_status_code);
            t.setError(message);
            onFailure(t);*/
        }

    }

    @Override
    public void onFailure(Throwable throwable) {
        RetrofitThrowable t = new RetrofitThrowable();
        t.initCause(throwable);
        t.setStackTrace(throwable.getStackTrace());
        t.setErrorDescription(throwable.getMessage());
        onFailure(t);
    }

    /**
     * 根据返回码判断是否需要在页面提示
     * @param code
     * @return
     */
    public boolean isShowToastError(int code) {
        boolean isError = false;
        switch (code) {
            case ERROR_CODE_40000:
                isError = true;
                break;
            case ERROR_CODE_41001:
                isError = true;
                break;
            case ERROR_CODE_41002:
                isError = true;
                break;
            case ERROR_CODE_41003:
                isError = true;
                break;
            case ERROR_CODE_41004:
                isError = true;
                break;
            case ERROR_CODE_41005:
                isError = true;
                break;
            case ERROR_CODE_41006:
                isError = true;
                break;
            case ERROR_CODE_41007:
                isError = true;
                break;
            case ERROR_CODE_41008:
                isError = true;
                break;
            case ERROR_CODE_41009:
                isError = true;
                break;
            case ERROR_CODE_41010:
                isError = true;
                break;
            case ERROR_CODE_41011:
                isError = true;
                break;
            case ERROR_CODE_41012:
                isError = true;
                break;
            case ERROR_CODE_43001:
                isError = true;
                break;
            case ERROR_CODE_46001:
                isError = true;
                break;
            case ERROR_CODE_46002:
                isError = true;
                break;
            case ERROR_CODE_47001:
                isError = true;
                break;
            default:break;
        }
        return isError;
    }

    public abstract void onSuccess(Response<EtResponse> response, Retrofit retrofit);

    public abstract void onFailure(RetrofitThrowable t);
}