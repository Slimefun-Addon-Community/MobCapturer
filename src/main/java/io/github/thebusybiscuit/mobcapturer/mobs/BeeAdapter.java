package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Bee;

import com.google.gson.JsonObject;

public class BeeAdapter extends AnimalsAdapter<Bee> {

    public BeeAdapter() {
        super(Bee.class);
    }

    @Override
    public void apply(Bee entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAnger(json.get("anger").getAsInt());
        entity.setCannotEnterHiveTicks(json.get("cannotEnterHiveTicks").getAsInt());
        entity.setHasNectar(json.get("nectar").getAsBoolean());
        entity.setHasStung(json.get("stung").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Bee entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("anger", entity.getAnger());
        json.addProperty("cannotEnterHiveTicks", entity.getCannotEnterHiveTicks());
        json.addProperty("nectar", entity.hasNectar());
        json.addProperty("stung", entity.hasStung());

        return json;
    }

}
