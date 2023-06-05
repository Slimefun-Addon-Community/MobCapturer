package io.github.thebusybiscuit.mobcapturer.adapters;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

/**
 * This is an interface used for adapting entities which have an inventory of items.
 */
public interface InventoryAdapter<T extends LivingEntity> {

    /**
     * Applies the serialized inventory to an entity.
     * @param entity
     * @param inventory
     */
    @ParametersAreNonnullByDefault
    void applyInventory(T entity, Map<String, ItemStack> inventory);

    /**
     * Serializes the inventory of an entity.
     * @param entity
     * @return
     */
    @Nonnull
    Map<String, ItemStack> saveInventory(@Nonnull T entity);
}
