package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.bukkit.entity.Allay;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.mobcapturer.InventoryAdapter;
import io.github.thebusybiscuit.mobcapturer.MobAdapter;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;

public class AllayAdapter implements MobAdapter<Allay>, InventoryAdapter<Allay> {

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Allay entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        if (haveAllayMethods()) {
            JsonElement duplicationCooldown = json.get("duplicationCooldown");
            if (duplicationCooldown.isJsonObject()) {
                entity.setDuplicationCooldown(duplicationCooldown.getAsLong());
            }
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Allay entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        if (haveAllayMethods()) {
            json.addProperty("duplicationCooldown", entity.getDuplicationCooldown());
        }

        return json;
    }

    private boolean haveAllayMethods() {
        if (Slimefun.getMinecraftVersion() == MinecraftVersion.MINECRAFT_1_19) {
            return PaperLib.getMinecraftPatchVersion() >= 2;
        } else {
            return true;
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
