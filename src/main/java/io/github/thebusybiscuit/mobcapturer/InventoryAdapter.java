package io.github.thebusybiscuit.mobcapturer;

import java.util.Map;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public interface InventoryAdapter<T extends LivingEntity> {

    void applyInventory(T entity, Map<String, ItemStack> inventory);

    Map<String, ItemStack> saveInventory(T entity);

}
