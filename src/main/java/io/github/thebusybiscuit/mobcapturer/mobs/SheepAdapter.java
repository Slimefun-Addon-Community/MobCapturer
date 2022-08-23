package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Sheep;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class SheepAdapter extends AnimalsAdapter<Sheep> {

    public SheepAdapter() {
        super(Sheep.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Color: " + ChatColor.WHITE + ChatUtils.humanize(json.get("woolColor").getAsString()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Sheep entity, JsonObject json) {
        super.apply(entity, json);

        entity.setSheared(json.get("sheared").getAsBoolean());
        entity.setColor(DyeColor.valueOf(json.get("woolColor").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Sheep entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("sheared", entity.isSheared());
        json.addProperty("woolColor", entity.getColor().name());

        return json;
    }

}
