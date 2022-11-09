package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.bukkit.entity.Monster;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.mobcapturer.adapters.InventoryAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;

class AbstractHumanoidAdapter<T extends Monster> implements MobAdapter<T>, InventoryAdapter<T> {

    private final Class<T> entityClass;

    public AbstractHumanoidAdapter(@Nonnull Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(T entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        JsonElement element = json.get("canPickupItems");
        if (!element.isJsonNull()) {
            entity.setCanPickupItems(element.getAsBoolean());
        }

        EntityEquipment equipment = entity.getEquipment();

        if (equipment != null) {
            equipment.setItemInMainHandDropChance(json.get("mainHandDropChance").getAsFloat());
            equipment.setItemInOffHandDropChance(json.get("offHandDropChance").getAsFloat());
            equipment.setHelmetDropChance(json.get("helmetDropChance").getAsFloat());
            equipment.setChestplateDropChance(json.get("chestplateDropChance").getAsFloat());
            equipment.setLeggingsDropChance(json.get("leggingsDropChance").getAsFloat());
            equipment.setBootsDropChance(json.get("bootsDropChance").getAsFloat());
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull T entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("canPickupItems", entity.getCanPickupItems());

        EntityEquipment equipment = entity.getEquipment();

        if (equipment != null) {
            json.addProperty("mainHandDropChance", equipment.getItemInMainHandDropChance());
            json.addProperty("offHandDropChance", equipment.getItemInOffHandDropChance());
            json.addProperty("helmetDropChance", equipment.getHelmetDropChance());
            json.addProperty("chestplateDropChance", equipment.getChestplateDropChance());
            json.addProperty("leggingsDropChance", equipment.getLeggingsDropChance());
            json.addProperty("bootsDropChance", equipment.getBootsDropChance());
        }

        return json;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void applyInventory(T entity, Map<String, ItemStack> inventory) {
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
    public Map<String, ItemStack> saveInventory(@Nonnull T entity) {
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

    @Nonnull
    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

}
