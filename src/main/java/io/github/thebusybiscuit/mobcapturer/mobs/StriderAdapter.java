package io.github.thebusybiscuit.mobcapturer.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.Strider;

public class StriderAdapter extends AnimalsAdapter<Strider> {

    public StriderAdapter() {
        super(Strider.class);
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Strider entity, JsonObject json) {
        super.apply(entity, json);

        entity.setShivering(json.get("shivering").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Strider entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("shivering", entity.isShivering());

        return json;
    }

}
