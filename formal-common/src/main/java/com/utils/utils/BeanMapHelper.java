package com.utils.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class BeanMapHelper {
    /**
     * @param src      需要转换的实例对象
     * @param desClass 被转成的目标对象的类
     * @return 被转成的目标对象的实例对象
     * @Description:实体一转实体类二(用于DO,PO,BO,VO之间的互转)
     * @example: Student student=new Student("1","小明","123");
     * StudentVO studentVO=(StudentVO)Bean2Bean(student,StudentVO.class);
     */
    public static Object BeanToBean(Object src, Class<?> desClass) {
        try {
            Object result = desClass.newInstance();

            for (Map.Entry<String, Object> entry : objectToMap(src).entrySet()) {
                for (Map.Entry<String, Object> entry1 : objectToMap(result).entrySet()) {
                    if (entry.getKey().equals(entry1.getKey())) {
                        entry1.setValue(entry.getValue());
                    }
                }
            }
            result = mapToObject(objectToMap(src), desClass);
            return result;
        } catch (Exception e) {
            System.err.println("转换异常");
        }
        return null;
    }


    /**
     * Map转javaBean
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    /**
     * Map和javaBean互转摘自：
     * https://blog.csdn.net/dcb_ripple/article/details/73250050
     * javaBean转Map
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }
}
