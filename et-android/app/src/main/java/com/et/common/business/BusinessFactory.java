package com.et.common.business;


import com.et.common.util.T;
import com.et.common.util.UIUtils;

import java.util.HashMap;

/**
 * @Description: 业务层调用入口
 * @author: Wangchao
 * @email: super0086@qq.com
 * @version: V1.0
 * Created by Wangchao on 15/11/5 19:00.
 */
public class BusinessFactory {

    private HashMap<String,BusinessInterface> businessMap;

    private BusinessFactory() {
    }

    private static class BusinessFactoryHolder {
        private static final BusinessFactory INSTANCE = new BusinessFactory();
    }

    public static final BusinessFactory getInstance() {
        return BusinessFactoryHolder.INSTANCE;
    }

    public BusinessInterface getBusinessInstance(Class clazz) {
        BusinessInterface business = null;
        try {
            if (clazz == null) {
                business = null;
                T.showShort(UIUtils.getContext(), "无效业务操作");
            } else {
                if (businessMap==null) {
                    businessMap = new HashMap<String,BusinessInterface>();
                }
                business = businessMap.get(clazz.getName());
                if (business==null) {
                    //判读该业务是否实例化过
                    business = (BusinessInterface) clazz.newInstance();
                    businessMap.put(clazz.getName(),business);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            business = null;
            T.showShort(UIUtils.getContext(), "无效业务操作");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            business = null;
            T.showShort(UIUtils.getContext(), "无效业务操作");
        } finally {
            return business;
        }
    }
}
