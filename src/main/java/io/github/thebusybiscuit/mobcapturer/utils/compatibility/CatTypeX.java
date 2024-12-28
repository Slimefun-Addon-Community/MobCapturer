package io.github.thebusybiscuit.mobcapturer.utils.compatibility;

import org.bukkit.entity.Cat;

import io.github.thebusybiscuit.mobcapturer.utils.ReflectionUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class CatTypeX {

    public static String get(Cat entity) {
        Object obj = ReflectionUtils.invoke(entity, "getCatType");
        return obj != null ? obj.toString() : "UNKNOWN";
    }

    public static void set(Cat entity, String obj) {
        ReflectionUtils.invoke(entity, "setCatType", ReflectionUtils.valueOf(Cat.Type.class, obj));
    }
}
