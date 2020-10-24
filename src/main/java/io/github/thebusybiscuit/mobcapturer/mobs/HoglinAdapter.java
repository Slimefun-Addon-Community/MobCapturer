package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Hoglin;

import com.google.gson.JsonObject;

public class HoglinAdapter extends AnimalsAdapter<Hoglin> {

    public HoglinAdapter() {
        super(Hoglin.class);
    }

    @Override
    public void apply(Hoglin entity, JsonObject json) {
        super.apply(entity, json);

        entity.setIsAbleToBeHunted(json.get("ableToBeHunted").getAsBoolean());
        entity.setConversionTime(json.get("conversionTime").getAsInt());
        entity.setImmuneToZombification(json.get("immuneToZombification").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Hoglin entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("ableToBeHunted", entity.isAbleToBeHunted());
        json.addProperty("conversionTime", entity.getConversionTime());
        json.addProperty("immuneToZombification", entity.isImmuneToZombification());

        return json;
    }

}
