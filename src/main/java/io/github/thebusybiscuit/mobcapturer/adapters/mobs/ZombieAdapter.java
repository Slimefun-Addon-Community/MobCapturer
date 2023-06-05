package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Zombie;

/**
 * This is an adapter for {@link Zombie}.
 * 
 * It is also used for the {@link org.bukkit.entity.Drowned} and {@link org.bukkit.entity.Husk}.
 */
public class ZombieAdapter<T extends Zombie> extends AbstractHumanoidAdapter<T> {

    public ZombieAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Baby: " + ChatColor.WHITE + json.get("baby").getAsBoolean());

        return lore;
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("age", entity.getAge());
        json.addProperty("baby", !entity.isAdult());

        if (entity.isConverting()) {
            json.addProperty("conversionTime", entity.getConversionTime());
        }

        return json;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAge(json.get("age").getAsInt());

        if (json.has("conversionTime")) {
            entity.setConversionTime(json.get("conversionTime").getAsInt());
        }
    }

}
