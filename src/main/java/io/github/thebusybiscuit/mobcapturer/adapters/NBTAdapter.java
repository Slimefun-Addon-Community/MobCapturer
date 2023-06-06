package io.github.thebusybiscuit.mobcapturer.adapters;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;

import io.github.thebusybiscuit.mobcapturer.nms.entity.EntityUtil;

/**
 * This is an adapter which stores the additional NBT data for an entity.
 */
public interface NBTAdapter<T extends LivingEntity> {
    @Nonnull
    @ParametersAreNonnullByDefault
    public default void applyNBTData(T entity, String nbtData) {
        EntityUtil.readAdditionalSaveData(entity, nbtData);
    }

    @Nonnull
    public default String saveNBTData(@Nonnull T entity) {
        return EntityUtil.addAdditionalSaveData(entity);
    }

    /**
     * Appends to the lore of the mob egg based on the entity's NBT data.
     * 
     * @param lore The existing lore of the mob egg
     * @param entity The entity to get the NBT data from
     * @return The modified lore
     */
    @Nonnull
    default List<String> appendLoreWithNbt(@Nonnull List<String> lore, T entity) {
        String nbtData = saveNBTData(entity);

        if (nbtData != null && nbtData.length() > 0) {
            lore.add(ChatColor.GRAY + "Memories: " + ChatColor.WHITE + "Stored");
        } else {
            lore.add(ChatColor.GRAY + "Memories: " + ChatColor.WHITE + "None");
        }

        return lore;
    }
}
