package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.Piglin;

public class PiglinAdapter extends AbstractPiglinAdapter<Piglin> {

    public PiglinAdapter() {
        super(Piglin.class);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Piglin entity, JsonObject json) {
        super.apply(entity, json);

        entity.setIsAbleToHunt(json.get("ableToHunt").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Piglin entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("ableToHunt", entity.isAbleToHunt());

        return json;
    }

}
