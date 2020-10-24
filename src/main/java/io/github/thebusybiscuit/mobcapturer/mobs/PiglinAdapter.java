package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Piglin;

import com.google.gson.JsonObject;

public class PiglinAdapter extends AbstractHumanoidAdapter<Piglin> {

    public PiglinAdapter() {
        super(Piglin.class);
    }

    @Override
    public void apply(Piglin entity, JsonObject json) {
        super.apply(entity, json);

        entity.setIsAbleToHunt(json.get("ableToHunt").getAsBoolean());
        entity.setConversionTime(json.get("conversionTime").getAsInt());
        entity.setImmuneToZombification(json.get("immuneToZombification").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Piglin entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("ableToHunt", entity.isAbleToHunt());
        json.addProperty("conversionTime", entity.getConversionTime());
        json.addProperty("immuneToZombification", entity.isImmuneToZombification());

        return json;
    }

}
