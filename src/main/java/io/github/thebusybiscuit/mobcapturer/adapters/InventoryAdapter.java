package io.github.thebusybiscuit.mobcapturer.adapters;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

/**
 * This is an interface used for adapting entities which have an inventory of items.
 * 
 * Use {@link EquipmentAdapter} for entities which have standard equipment slots (armor and hands).
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

    /**
     * Appends to the lore of the mob egg based on the entity's inventory.
     * 
     * @param lore The existing lore of the mob egg
     * @param entity The entity to get the inventory from
     * @return The modified lore
     */
    @Nonnull
    default List<String> appendLoreWithInventory(@Nonnull List<String> lore, T entity) {
        Map<String, ItemStack> inventory = saveInventory(entity);
        
        int itemCount = 0;
        for (Map.Entry<String, ItemStack> entry : inventory.entrySet()) {
            if (entry.getValue().getType() == Material.AIR) continue;

            itemCount += 1;
        }

        if (itemCount > 0) {
            lore.add(ChatColor.GRAY + "Items: " + ChatColor.WHITE + itemCount);
        } else {
            lore.add(ChatColor.GRAY + "Items: " + ChatColor.WHITE + "None");
        }

        return lore;
    }
}
