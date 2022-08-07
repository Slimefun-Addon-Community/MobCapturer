package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Cat.Type;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class CatAdapter extends AbstractTameableAdapter<Cat> {

    public CatAdapter() {
        super(Cat.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Variant: " + ChatColor.WHITE + ChatUtils.humanize(json.get("catType").getAsString()));

        if (!json.get("ownerUUID").isJsonNull()) {
            lore.add(ChatColor.GRAY + "Collar Color: " + ChatColor.WHITE + ChatUtils.humanize(json.get("collarColor").getAsString()));
            lore.add(ChatColor.GRAY + "Sitting: " + ChatColor.WHITE + json.get("sitting").getAsBoolean());
        }

        return lore;
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Cat entity, JsonObject json) {
        super.apply(entity, json);

        entity.setCatType(Type.valueOf(json.get("catType").getAsString()));
        entity.setSitting(json.get("sitting").getAsBoolean());
        entity.setCollarColor(DyeColor.valueOf(json.get("collarColor").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Cat entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("catType", entity.getCatType().name());
        json.addProperty("sitting", entity.isSitting());
        json.addProperty("collarColor", entity.getCollarColor().name());

        return json;
    }

}
