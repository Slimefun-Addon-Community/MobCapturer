package io.github.thebusybiscuit.mobcapturer.utils;

import lombok.experimental.UtilityClass;

/**
 * Reflection utilities used for backward compatibility.
 * Other plugins should not use this class.
 */
@UtilityClass
@SuppressWarnings("unchecked")
public final class ReflectionUtils {

    public static Object invoke(Object instance, String methodName, Object... args) {
        Class<?> currentClass = instance.getClass();
        while (currentClass != null) {
            try {
                return currentClass.getDeclaredMethod(methodName).invoke(instance, args);
            } catch (Exception x) {
                currentClass = currentClass.getSuperclass();
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
