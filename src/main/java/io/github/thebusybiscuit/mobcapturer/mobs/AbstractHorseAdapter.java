package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.InventoryAdapter;
import me.mrCookieSlime.Slimefun.cscorelib2.math.DoubleHandler;

class AbstractHorseAdapter<T extends AbstractHorse> extends AbstractTameableAdapter<T> implements InventoryAdapter<T> {

    public AbstractHorseAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Jump Strength: " + ChatColor.WHITE + DoubleHandler.fixDouble(json.get("jumpStrength").getAsDouble()));

        return lore;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        entity.setMaxDomestication(json.get("maxDomestication").getAsInt());
        entity.setDomestication(json.get("domestication").getAsInt());
        entity.setJumpStrength(json.get("jumpStrength").getAsDouble());
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("maxDomestication", entity.getMaxDomestication());
        json.addProperty("domestication", entity.getDomestication());
        json.addProperty("jumpStrength", entity.getJumpStrength());

        return json;
    }

    @Override
    public void applyInventory(T entity, Map<String, ItemStack> inventory) {
        entity.getInventory().setSaddle(inventory.get("saddle"));
    }

    @Override
    public Map<String, ItemStack> saveInventory(T entity) {
        Map<String, ItemStack> inventory = new HashMap<>();

        inventory.put("saddle", entity.getInventory().getSaddle());

        return inventory;
    }

}
