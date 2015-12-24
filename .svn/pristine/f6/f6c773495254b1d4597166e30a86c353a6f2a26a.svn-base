package com.et.common.http;

/**
 * Description: 自定义网络异常处理类
 * Created by Wangchao on 15/11/30 17:33.
 * Author: Wangchao
 * Email: super0086@qq.com
 * Version: V1.0
 */
public class RetrofitThrowable extends Throwable {
    private int errorCode = 400_500;//默认返回4xx 5xx
    private String error;
    private String errorDescription;

    public int getErrorCode() {
        return errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        return "RetrofitThrowable{" +
                "errorCode=" + errorCode +
                ", error='" + error + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                '}';
    }
}
