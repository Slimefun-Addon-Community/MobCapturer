package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Creeper;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class CreeperAdapter implements MobAdapter<Creeper> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "Powered: " + ChatColor.WHITE + json.get("powered").getAsBoolean());

        return lore;
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Creeper entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setPowered(json.get("powered").getAsBoolean());
        entity.setExplosionRadius(json.get("radius").getAsInt());
        entity.setMaxFuseTicks(json.get("maxFuseTicks").getAsInt());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Creeper entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("powered", entity.isPowered());
        json.addProperty("radius", entity.getExplosionRadius());
        json.addProperty("maxFuseTicks", entity.getMaxFuseTicks());

        return json;
    }

    @Nonnull
    @Override
    public Class<Creeper> getEntityClass() {
        return Creeper.class;
    }

}
