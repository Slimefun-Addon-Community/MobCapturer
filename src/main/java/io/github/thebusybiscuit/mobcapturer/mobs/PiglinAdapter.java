package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Piglin;

public class PiglinAdapter extends AbstractHumanoidAdapter<Piglin> {

    public PiglinAdapter() {
        super(Piglin.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Baby: " + ChatColor.WHITE + json.get("baby").getAsBoolean());

        return lore;
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Piglin entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAge(json.get("age").getAsInt());
        entity.setIsAbleToHunt(json.get("ableToHunt").getAsBoolean());
        entity.setConversionTime(json.get("conversionTime").getAsInt());
        entity.setImmuneToZombification(json.get("immuneToZombification").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Piglin entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("age", entity.getAge());
        json.addProperty("baby", !entity.isAdult());
        json.addProperty("ableToHunt", entity.isAbleToHunt());
        json.addProperty("conversionTime", entity.getConversionTime());
        json.addProperty("immuneToZombification", entity.isImmuneToZombification());

        return json;
    }

}
