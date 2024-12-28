package io.github.thebusybiscuit.mobcapturer.utils.compatibility;

import org.bukkit.entity.Frog;

import io.github.thebusybiscuit.mobcapturer.utils.ReflectionUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class FrogVariantX {

    public static String get(Frog entity) {
        Object obj = ReflectionUtils.invoke(entity, "getVariant");
        return obj != null ? obj.toString() : "UNKNOWN";
    }

    public static void set(Frog entity, String obj) {
        ReflectionUtils.invoke(entity, "setVariant", ReflectionUtils.valueOf(Frog.Variant.class, obj));
    }
}
