package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Pig;

/**
 * This is an adapter for the {@link Pig}.
 * Stores whether the pig has a saddle or not.
 */
public class PigAdapter extends AnimalsAdapter<Pig> {

    public PigAdapter() {
        super(Pig.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        if (json.get("saddle").getAsBoolean()) {
            lore.add(ChatColor.GRAY + "+ Saddle");
        }

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Pig entity, JsonObject json) {
        super.apply(entity, json);

        entity.setSaddle(json.get("saddle").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Pig entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("saddle", entity.hasSaddle());

        return json;
    }

}
