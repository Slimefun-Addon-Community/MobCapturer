package io.github.thebusybiscuit.mobcapturer.adapters;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

/**
 * This is an adapter which stores the data for an entity's equipment slots.
 * 
 * Implement this class and override its methods if the entity has additional slots.
 */
public interface EquipmentAdapter<T extends LivingEntity> extends InventoryAdapter<T> {
    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public default void applyInventory(T entity, Map<String, ItemStack> inventory) {
        EntityEquipment equipment = entity.getEquipment();

        if (equipment != null) {
            equipment.setItemInMainHand(inventory.get("mainHand"));
            equipment.setItemInOffHand(inventory.get("offHand"));
            equipment.setHelmet(inventory.get("helmet"));
            equipment.setChestplate(inventory.get("chestplate"));
            equipment.setLeggings(inventory.get("leggings"));
            equipment.setBoots(inventory.get("boots"));
        }
    }

    @Nonnull
    @Override
    public default Map<String, ItemStack> saveInventory(@Nonnull T entity) {
        Map<String, ItemStack> inv = new HashMap<>();

        EntityEquipment equipment = entity.getEquipment();

        if (equipment != null) {
            inv.put("mainHand", equipment.getItemInMainHand());
            inv.put("offHand", equipment.getItemInOffHand());
            inv.put("helmet", equipment.getHelmet());
            inv.put("chestplate", equipment.getChestplate());
            inv.put("leggings", equipment.getLeggings());
            inv.put("boots", equipment.getBoots());
        }

        return inv;
    }
}
