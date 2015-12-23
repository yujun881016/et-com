package com.et.common.http.response;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 类说明
 * Created by Wangchao on 15/12/14 18:30.
 * Author: Wangchao
 * Email: super0086@qq.com
 * Version: V1.0
 */
public class EntityObject<T> implements Serializable {
    private int count;
    private int total;
    private T info;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
