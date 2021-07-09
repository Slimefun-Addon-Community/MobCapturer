package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.PigZombie;

import com.google.gson.JsonObject;

public class ZombifiedPiglinAdapter extends AbstractHumanoidAdapter<PigZombie> {

    public ZombifiedPiglinAdapter() {
        super(PigZombie.class);
    }

    @Override
    public void apply(PigZombie entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAnger(json.get("anger").getAsInt());
    }

    @Override
    public JsonObject saveData(PigZombie entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("anger", entity.getAnger());

        return json;
    }

}
