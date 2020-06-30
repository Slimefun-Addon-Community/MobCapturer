package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonObject;

import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;

public class HorseAdapter extends AbstractHorseAdapter<Horse> {

    public HorseAdapter() {
        super(Horse.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Style: " + ChatColor.WHITE + StringUtils.format(json.get("style").getAsString()));
        lore.add(ChatColor.GRAY + "Color: " + ChatColor.WHITE + StringUtils.format(json.get("color").getAsString()));

        return lore;
    }

    @Override
    public void apply(Horse entity, JsonObject json) {
        super.apply(entity, json);

        entity.setStyle(Style.valueOf(json.get("style").getAsString()));
        entity.setColor(Color.valueOf(json.get("color").getAsString()));
    }

    @Override
    public JsonObject saveData(Horse entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("style", entity.getStyle().name());
        json.addProperty("color", entity.getColor().name());

        return json;
    }

    @Override
    public void applyInventory(Horse entity, Map<String, ItemStack> inventory) {
        super.applyInventory(entity, inventory);

        inventory.put("armor", entity.getInventory().getArmor());
    }

    @Override
    public Map<String, ItemStack> saveInventory(Horse entity) {
        Map<String, ItemStack> inventory = super.saveInventory(entity);

        inventory.put("armor", entity.getInventory().getArmor());

        return inventory;
    }

}
