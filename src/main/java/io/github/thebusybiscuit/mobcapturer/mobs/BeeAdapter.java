package io.github.thebusybiscuit.mobcapturer.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.Bee;

public class BeeAdapter extends AnimalsAdapter<Bee> {

    public BeeAdapter() {
        super(Bee.class);
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Bee entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAnger(json.get("anger").getAsInt());
        entity.setCannotEnterHiveTicks(json.get("cannotEnterHiveTicks").getAsInt());
        entity.setHasNectar(json.get("nectar").getAsBoolean());
        entity.setHasStung(json.get("stung").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Bee entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("anger", entity.getAnger());
        json.addProperty("cannotEnterHiveTicks", entity.getCannotEnterHiveTicks());
        json.addProperty("nectar", entity.hasNectar());
        json.addProperty("stung", entity.hasStung());

        return json;
    }

}
