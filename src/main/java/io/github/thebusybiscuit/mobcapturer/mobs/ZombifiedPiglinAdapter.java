package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.PigZombie;

public class ZombifiedPiglinAdapter extends AbstractHumanoidAdapter<PigZombie> {

    public ZombifiedPiglinAdapter() {
        super(PigZombie.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Anger: " + ChatColor.WHITE + json.get("anger").getAsString());
        lore.add(ChatColor.GRAY + "Baby: " + ChatColor.WHITE + json.get("baby").getAsBoolean());

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(PigZombie entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAnger(json.get("anger").getAsInt());
        entity.setAge(json.get("age").getAsInt());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull PigZombie entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("anger", entity.getAnger());
        json.addProperty("age", entity.getAge());
        json.addProperty("baby", !entity.isAdult());

        return json;
    }

}
