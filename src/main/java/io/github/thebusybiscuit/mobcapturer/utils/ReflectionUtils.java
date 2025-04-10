package io.github.thebusybiscuit.mobcapturer.utils;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nullable;

import lombok.experimental.UtilityClass;

/**
 * Reflection utilities used for backward compatibility.
 * Other plugins should not use this class.
 */
@UtilityClass
public final class ReflectionUtils {

    @Nullable
    public static Object invoke(Object instance, String methodName, Object... args) {
        Class<?> currentClass = instance.getClass();
        Class<?>[] paramTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = (args[i] != null) ? args[i].getClass() : Object.class;
        }
        while (currentClass != null) {
            try {
                var method = currentClass.getDeclaredMethod(methodName, paramTypes);
                method.setAccessible(true);
                return method.invoke(instance, args);
            } catch (NoSuchMethodException ex) {
                currentClass = currentClass.getSuperclass();
            } catch (IllegalAccessException | InvocationTargetException ex) {
                return null;
            }
        }
        return null;
    }

    public static Object valueOf(Class<?> clazz, String fieldName) {
        if (clazz.isEnum()) {
            return Enum.valueOf(clazz.asSubclass(Enum.class), fieldName);
        } else {
            try {
                return clazz.getField(fieldName).get(null);
            } catch (Exception x) {
                return null;
            }
        }
    }
}
