package com.ua.itclusterjava2024.wrappers;

import com.ua.itclusterjava2024.entity.Teachers;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

@Component
public class Patcher<T> {

    public void patch(T existingObject, T incompleteObject) throws IllegalAccessException {
        Class<?> objectClass = existingObject.getClass();
        Field[] fields = objectClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(incompleteObject);
            if (value != null) {
                field.set(existingObject, value);
            }
            field.setAccessible(false);
        }
    }
}


