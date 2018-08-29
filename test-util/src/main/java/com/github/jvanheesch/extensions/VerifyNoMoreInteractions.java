package com.github.jvanheesch.extensions;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mock;

import java.lang.reflect.Field;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class VerifyNoMoreInteractions implements AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) {
        context.getTestInstance().ifPresent(instanceOfTestClass -> {
            Object[] mocks = getMocks(instanceOfTestClass);
            if (mocks.length != 0) {
                verifyNoMoreInteractions(mocks);
            }
        });
    }

    private static Object[] getMocks(Object instanceOfTestClass) {
        return FieldUtils.getFieldsListWithAnnotation(instanceOfTestClass.getClass(), Mock.class).stream()
                .map(field -> getMock(field, instanceOfTestClass))
                .toArray();
    }

    private static Object getMock(Field field, Object object) {
        Object o = null;
        try {
            field.setAccessible(true);
            o = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
