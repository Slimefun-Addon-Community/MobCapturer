package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Strider;

import com.google.gson.JsonObject;

public class StriderAdapter extends AnimalsAdapter<Strider> {

    public StriderAdapter() {
        super(Strider.class);
    }

    @Override
    public void apply(Strider entity, JsonObject json) {
        super.apply(entity, json);

        entity.setShivering(json.get("shivering").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Strider entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("shivering", entity.isShivering());

        return json;
    }

}
