package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.Allay;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.mobcapturer.adapters.InventoryAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;

public class AllayAdapter implements MobAdapter<Allay>, InventoryAdapter<Allay> {

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Allay entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        if (hasAllayMethods() && json.has("duplicationCooldown")) {
            entity.setDuplicationCooldown(json.get("duplicationCooldown").getAsLong());
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Allay entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        if (hasAllayMethods()) {
            json.addProperty("duplicationCooldown", entity.getDuplicationCooldown());
        }

        return json;
    }

    private boolean hasAllayMethods() {
        try {
            Allay.class.getMethod("getDuplicationCooldown");
            return true;
        } catch (NoSuchMethodException ex) {
            return false;
        }
    }

    @Nonnull
    @Override
    public Map<String, ItemStack> saveInventory(@Nonnull Allay entity) {
        Map<String, ItemStack> inv = new HashMap<>();

        EntityEquipment equipment = entity.getEquipment();

        if (equipment != null) {
            inv.put("mainHand", equipment.getItemInMainHand());
        }

        return inv;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void applyInventory(Allay entity, Map<String, ItemStack> inventory) {
        EntityEquipment equipment = entity.getEquipment();

        if (equipment != null) {
            equipment.setItemInMainHand(inventory.get("mainHand"));
        }
    }

    @Nonnull
    @Override
    public Class<Allay> getEntityClass() {
        return Allay.class;
    }

}
