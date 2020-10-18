package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Endermite;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class EndermiteAdapter implements MobAdapter<Endermite> {

    @Override
    public void apply(Endermite entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setPlayerSpawned(json.get("playerSpawned").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Endermite entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("playerSpawned", entity.isPlayerSpawned());

        return json;
    }

    @Override
    public Class<Endermite> getEntityClass() {
        return Endermite.class;
    }

}
