package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Snowman;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class SnowmanAdapter implements MobAdapter<Snowman> {

    @Override
    public void apply(Snowman entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setDerp(json.get("derp").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Snowman entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("derp", entity.isDerp());

        return json;
    }

    @Override
    public Class<Snowman> getEntityClass() {
        return Snowman.class;
    }

}
