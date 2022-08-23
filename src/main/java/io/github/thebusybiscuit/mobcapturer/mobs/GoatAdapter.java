package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Goat;

public class GoatAdapter extends AnimalsAdapter<Goat> {

    public GoatAdapter() {
        super(Goat.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Screaming: " + ChatColor.WHITE + json.get("screaming").getAsBoolean());

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Goat entity, JsonObject json) {
        super.apply(entity, json);

        entity.setScreaming(json.get("screaming").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Goat entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("screaming", entity.isScreaming());

        return json;
    }

}
