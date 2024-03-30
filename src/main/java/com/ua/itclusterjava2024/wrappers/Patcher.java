package com.ua.itclusterjava2024.wrappers;

import com.ua.itclusterjava2024.entity.Teachers;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public static void teachersPatcher(Teachers existingTeacher, Teachers incompleteTeacher) throws IllegalAccessException {

        //GET THE COMPILED VERSION OF THE CLASS
        Class<?> internClass = Teachers.class;
        Field[] internFields = internClass.getDeclaredFields();
        System.out.println(internFields.length);
        for (Field field : internFields) {
            System.out.println(field.getName());
            //CANT ACCESS IF THE FIELD IS PRIVATE
            field.setAccessible(true);

            //CHECK IF THE VALUE OF THE FIELD IS NOT NULL, IF NOT UPDATE EXISTING INTERN
            Object value = field.get(incompleteTeacher);
            if (value != null) {
                field.set(existingTeacher, value);
            }
            //MAKE THE FIELD PRIVATE AGAIN
            field.setAccessible(false);
        }

    }

}

