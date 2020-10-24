package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.PiglinBrute;

import com.google.gson.JsonObject;

public class PiglinBruteAdapter extends AbstractHumanoidAdapter<PiglinBrute> {

    public PiglinBruteAdapter() {
        super(PiglinBrute.class);
    }

    @Override
    public void apply(PiglinBrute entity, JsonObject json) {
        super.apply(entity, json);

        entity.setConversionTime(json.get("conversionTime").getAsInt());
        entity.setImmuneToZombification(json.get("immuneToZombification").getAsBoolean());
    }

    @Override
    public JsonObject saveData(PiglinBrute entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("conversionTime", entity.getConversionTime());
        json.addProperty("immuneToZombification", entity.isImmuneToZombification());

        return json;
    }

}
