package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import com.google.gson.JsonObject;

import org.bukkit.entity.Sniffer;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class SnifferAdapter extends AnimalsAdapter<Sniffer> {

    public SnifferAdapter() {
        super(Sniffer.class);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Sniffer entity, JsonObject json) {
        super.apply(entity, json);

        entity.setState(Sniffer.State.valueOf(json.get("state").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Sniffer entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("state", entity.getState().toString());

        return json;
    }

}
