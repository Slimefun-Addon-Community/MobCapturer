package io.github.thebusybiscuit.mobcapturer.adapters;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public interface InventoryAdapter<T extends LivingEntity> {

    @ParametersAreNonnullByDefault
    void applyInventory(T entity, Map<String, ItemStack> inventory);

    @Nonnull
    Map<String, ItemStack> saveInventory(@Nonnull T entity);

}
