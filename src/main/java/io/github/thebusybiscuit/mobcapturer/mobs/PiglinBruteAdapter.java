package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.PiglinBrute;

public class PiglinBruteAdapter extends AbstractHumanoidAdapter<PiglinBrute> {

    public PiglinBruteAdapter() {
        super(PiglinBrute.class);
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
    public void apply(PiglinBrute entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAge(json.get("age").getAsInt());
        entity.setConversionTime(json.get("conversionTime").getAsInt());
        entity.setImmuneToZombification(json.get("immuneToZombification").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull PiglinBrute entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("age", entity.getAge());
        json.addProperty("baby", !entity.isAdult());
        json.addProperty("conversionTime", entity.getConversionTime());
        json.addProperty("immuneToZombification", entity.isImmuneToZombification());

        return json;
    }

}
