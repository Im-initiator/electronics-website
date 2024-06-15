package com.electronics_store.utils;

import java.lang.reflect.Field;
import java.util.Objects;

public class ObjectUtils {

    public static boolean isObjectEmpty(Object obj){
        if (obj == null) return true;

        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (!Objects.isNull(value)) return false;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }

}
