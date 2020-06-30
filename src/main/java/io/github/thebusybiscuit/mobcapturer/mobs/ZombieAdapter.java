package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Zombie;

import com.google.gson.JsonObject;

public class ZombieAdapter<T extends Zombie> extends AbstractHumanoidAdapter<T> {

    public ZombieAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Baby: " + ChatColor.WHITE + json.get("baby").getAsBoolean());

        return lore;
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("baby", entity.isBaby());

        if (entity.isConverting()) {
            json.addProperty("conversionTime", entity.getConversionTime());
        }

        return json;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        entity.setBaby(json.get("baby").getAsBoolean());

        if (json.has("conversionTime")) {
            entity.setConversionTime(json.get("conversionTime").getAsInt());
        }
    }

}
