package com.example.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class MyBeanUtils {

    // 获取所有属性值位空属性名数组
    public static String[] getNullPropertyName(Object object) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for(PropertyDescriptor pd : pds) {
            String propertName = pd.getName();
            if(beanWrapper.getPropertyValue(propertName) == null) {
                nullPropertyNames.add(propertName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }
}
