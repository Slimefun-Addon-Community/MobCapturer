package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonObject;

public class ChestedHorseAdapter<T extends ChestedHorse> extends AbstractHorseAdapter<T> {

    public ChestedHorseAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Chest: " + ChatColor.WHITE + json.get("chest").getAsBoolean());

        return lore;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        entity.setCarryingChest(json.get("chest").getAsBoolean());
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("chest", entity.isCarryingChest());

        return json;
    }

    @Override
    public Map<String, ItemStack> saveInventory(T entity) {
        Map<String, ItemStack> inv = new HashMap<>();

        for (int slot = 0; slot < entity.getInventory().getSize(); slot++) {
            inv.put(String.valueOf(slot), entity.getInventory().getItem(slot));
        }

        return inv;
    }

    @Override
    public void applyInventory(T entity, Map<String, ItemStack> inventory) {
        for (Map.Entry<String, ItemStack> entry : inventory.entrySet()) {
            entity.getInventory().setItem(Integer.parseInt(entry.getKey()), entry.getValue());
        }
    }

}
