package io.github.thebusybiscuit.mobcapturer.nms;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;

import io.github.thebusybiscuit.slimefun4.libraries.dough.reflection.ReflectionUtils;

public class ReflectionUtils2 {
    public static @Nullable Method getMethodOrAlternative(Class<?> c, String method, String alternative, Class<?>... params) {
        Method mainMethod = ReflectionUtils.getMethod(c, method, params);

        if (mainMethod != null) return mainMethod;

        return ReflectionUtils.getMethod(c, alternative, params);
    }

    public static @Nullable Field getFieldOrAlternative(Class<?> c, String field, String alternative) {
        Field mainField = null;
        try {
            mainField = ReflectionUtils.getField(c, field);
        } catch (NoSuchFieldException e) {
            // Ignore
        }

        if (mainField != null) return mainField;

        try {
            return ReflectionUtils.getField(c, alternative);
        } catch (NoSuchFieldException e) {
            return mainField;
        }
    }

    public static @Nullable Method getPrivateMethod(Class<?> c, String method, Class<?>... paramTypes) {
        Class<?>[] expectParamTypes = ReflectionUtils.toPrimitiveTypeArray(paramTypes);

        // getDelcaredMethods returns all private/protected methods, but not inherited ones
        for (Method m : c.getDeclaredMethods()) {
            Class<?>[] methodParameters = ReflectionUtils.toPrimitiveTypeArray(m.getParameterTypes());

            if ((m.getName().equals(method)) && (equalsTypeArray(methodParameters, expectParamTypes))) {
                return m;
            }
        }

        return null;
    }

    public static @Nullable Method getPrivateMethodOrAlternative(Class<?> c, String method, String alternative, Class<?>... paramTypes) {
        Method mainMethod = getPrivateMethod(c, method, paramTypes);

        if (mainMethod != null) return mainMethod;

        return getPrivateMethod(c, alternative, paramTypes);
    }

    private static boolean equalsTypeArray(@Nonnull Class<?>[] a, Class<?>[] b) {
        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if ((!a[i].equals(b[i])) && (!a[i].isAssignableFrom(b[i]))) {
                return false;
            }
        }

        return true;
    }
}
