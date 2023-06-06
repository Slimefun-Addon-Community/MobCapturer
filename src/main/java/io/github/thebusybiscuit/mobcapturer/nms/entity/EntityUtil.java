package io.github.thebusybiscuit.mobcapturer.nms.entity;

import java.util.logging.Level;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.DoughLogger;

public class EntityUtil {
    private static final EntityAdapter adapter = EntityAdapter.get();

    /**
     * @param targetEntity The entity to target
     * @return A string containing a serialized NBT tag representing additional data about the entity.
     */
    public static String addAdditionalSaveData(org.bukkit.entity.Entity targetEntity) {
        if (adapter != null) {
            try {
                return adapter.addAdditionalSaveData(targetEntity);
            } catch (Exception x) {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * Reads additional data from the serialized NBT tag and applies it to the entity.
     * @param targetEntity The entity to target
     * @param data A string containing a serialized NBT tag representing additional data about the entity.
     */
    public static void readAdditionalSaveData(org.bukkit.entity.Entity targetEntity, String data) {
        if (adapter != null) {
            try {
                adapter.readAdditionalSaveData(targetEntity, data);
            } catch (Exception x) {
                return;
            }
        } else {
            return;
        }
    }

    public static String getTypeName(org.bukkit.entity.Entity targetEntity) {
        if (adapter != null) {
            try {
                return adapter.getTypeName(targetEntity);
            } catch (Exception x) {
                DoughLogger logger = new DoughLogger("mobcapturer-entityutil");
                logger.log(Level.SEVERE, "Failed to get Entity type", x);
                return "ERROR";
            }
        } else {
            return "unknown";
        }
    }
}
