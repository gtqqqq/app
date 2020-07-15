package com.xkcoding.multi.datasource.mybatis;

import com.yida.entity.DailyComplianceStatistics;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class testField {
    public static void main(String[] args) {
        DailyComplianceStatistics model = new DailyComplianceStatistics();
        setModelField(model, new BigDecimal(11.11d), 2, "hour", "Ok");
        setModelField(model, new BigDecimal(11.11d), 3, "hour", "Ok");
        setModelField(model, new BigDecimal(11.11d), 4, "hour", "Ok");
        //  setModelField(model, new BigDecimal(11.11d), 5, "hour","Ok");
        setModelField(model, new BigDecimal(11.11d), 6, "hour", "Ok");
        setModelField(model, new BigDecimal(11.11d), 7, "hour", "Ok");
        System.out.println(model.getHour2Ok().doubleValue());
        System.out.println(fieldSum(model, "hour", "Ok"));

    }

    private static Double fieldSum(Object model, String keyPrefix, String keysuffix) {
        Double sum = 0.00d;
        Field[] rFields = model.getClass().getDeclaredFields();
        for (Field field : rFields) {
            field.setAccessible(true);
            if (field.getName().startsWith(keyPrefix) && field.getName().endsWith(keysuffix)) {
                try {
                    sum += field.get(model) != null ? ((BigDecimal) field.get(model)).doubleValue() : 0.00d;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                }
            }
        }
        return sum;
    }

    private static void setModelField(Object model, Object result, Integer key, String keyPrefix, String keysuffix) {
        try {
            Field field = model.getClass().getDeclaredField(keyPrefix + key + keysuffix);
            field.setAccessible(true);
            field.set(model, result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Field> getBeanPropertyFields(Class cl) {
        Map<String, Field> properties = new HashMap<String, Field>();
        for (; cl != null; cl = cl.getSuperclass()) {
            Field[] fields = cl.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isTransient(field.getModifiers())
                        || Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                field.setAccessible(true);

                properties.put(field.getName(), field);
            }
        }

        return properties;
    }
}
