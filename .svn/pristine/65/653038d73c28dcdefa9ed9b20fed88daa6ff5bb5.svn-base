package com.et.common.http.response;

import java.io.Serializable;

/**
 * Description: 类说明
 * Created by Wangchao on 15/12/14 18:24.
 * Author: Wangchao
 * Email: super0086@qq.com
 * Version: V1.0
 */
public class EtResponse<T> implements Serializable{
    private int code;
    private String info;
    private String description;
    private String messageType;
    private EntityObject<T> object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public EntityObject<T> getObject() {
        return object;
    }

    public void setObject(EntityObject<T> object) {
        this.object = object;
    }
}
