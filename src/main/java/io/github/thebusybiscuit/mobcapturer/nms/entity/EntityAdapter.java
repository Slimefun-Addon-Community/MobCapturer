package io.github.thebusybiscuit.mobcapturer.nms.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import javax.annotation.Nullable;

import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.entity.Entity;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.DoughLogger;
import io.github.thebusybiscuit.slimefun4.libraries.dough.versions.MinecraftVersion;

public interface EntityAdapter {
    public String addAdditionalSaveData(Entity targetEntity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException;
    public void readAdditionalSaveData(Entity targetEntity, String data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException;
    public String getTypeName(Entity targetEntity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    public static @Nullable EntityAdapter get() {
        try {
            MinecraftVersion version = MinecraftVersion.get();

            if (version.isAtLeast(1, 19, 4)) {
                return new EntityAdapter19v4();
            } else {
                // Old mappings
                throw new NotImplementedException("Not implemented yet");
            }
        } catch (Exception x) {
            DoughLogger logger = new DoughLogger("mobcapturer-entityadapter");
            logger.log(Level.SEVERE, "Failed to detect Entity NBT methods", x);
            return null;
        }

    }
}
