package io.github.thebusybiscuit.mobcapturer.utils.compatibility;

import java.util.Locale;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;

import io.github.thebusybiscuit.mobcapturer.utils.ReflectionUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class VillagerProfessionX {

    public static String getFromZombieVillager(ZombieVillager entity) {
        var profession = ReflectionUtils.invoke(entity, "getVillagerProfession");
        if (profession == null) {
            return "UNKNOWN";
        }

        var nsKey = (NamespacedKey) ReflectionUtils.invoke(profession, "getKey");
        return nsKey.getKey().toUpperCase(Locale.ROOT);
    }

    public static void setToZombieVillager(ZombieVillager entity, String obj) {
        ReflectionUtils.invoke(entity, "setVillagerProfession", ReflectionUtils.valueOf(Villager.Profession.class, obj));
    }
}
