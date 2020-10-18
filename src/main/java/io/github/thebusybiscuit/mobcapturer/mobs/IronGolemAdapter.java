package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.IronGolem;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class IronGolemAdapter implements MobAdapter<IronGolem> {

    @Override
    public void apply(IronGolem entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setPlayerCreated(json.get("playerMade").getAsBoolean());
    }

    @Override
    public JsonObject saveData(IronGolem entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("playerMade", entity.isPlayerCreated());

        return json;
    }

    @Override
    public Class<IronGolem> getEntityClass() {
        return IronGolem.class;
    }

}
