package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class HorseAdapter extends AbstractHorseAdapter<Horse> {

    public HorseAdapter() {
        super(Horse.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Style: " + ChatColor.WHITE + ChatUtils.humanize(json.get("style").getAsString()));
        lore.add(ChatColor.GRAY + "Color: " + ChatColor.WHITE + ChatUtils.humanize(json.get("color").getAsString()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Horse entity, JsonObject json) {
        super.apply(entity, json);

        entity.setStyle(Style.valueOf(json.get("style").getAsString()));
        entity.setColor(Color.valueOf(json.get("color").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Horse entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("style", entity.getStyle().name());
        json.addProperty("color", entity.getColor().name());

        return json;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void applyInventory(Horse entity, Map<String, ItemStack> inventory) {
        super.applyInventory(entity, inventory);

        inventory.put("armor", entity.getInventory().getArmor());
    }

    @Nonnull
    @Override
    public Map<String, ItemStack> saveInventory(@Nonnull Horse entity) {
        Map<String, ItemStack> inventory = super.saveInventory(entity);

        inventory.put("armor", entity.getInventory().getArmor());

        return inventory;
    }

}
