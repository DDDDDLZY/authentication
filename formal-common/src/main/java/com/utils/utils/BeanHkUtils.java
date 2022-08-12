package com.utils.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.BeansException;

/**
 * @ClassName BeanNcUtils
 * @Description bean 处理
 * @Author linzhixiong
 * @Date 2021/4/26 下午2:11
 **/
public class BeanHkUtils {

    /**
     * bean属性复制
     * @param source 原对象
     * @param target 目标对象
     * @throws BeansException
     */
    public static Object copyProperties(Object source, Object target) throws BeansException {
        if(source==null){
            target = null;
        }else{
            target = JSONObject.parseObject(JSON.toJSONString(source, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect,
                    SerializerFeature.WriteDateUseDateFormat)).toJavaObject(target.getClass());
        }
        return target;
    }
}
