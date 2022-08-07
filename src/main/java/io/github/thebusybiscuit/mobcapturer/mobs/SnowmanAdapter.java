package io.github.thebusybiscuit.mobcapturer.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.Snowman;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class SnowmanAdapter implements MobAdapter<Snowman> {

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Snowman entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setDerp(json.get("derp").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Snowman entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("derp", entity.isDerp());

        return json;
    }

    @Nonnull
    @Override
    public Class<Snowman> getEntityClass() {
        return Snowman.class;
    }

}
