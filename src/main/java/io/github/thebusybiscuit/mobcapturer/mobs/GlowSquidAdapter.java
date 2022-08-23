package io.github.thebusybiscuit.mobcapturer.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.GlowSquid;

public class GlowSquidAdapter extends StandardMobAdapter<GlowSquid> {

    public GlowSquidAdapter() {
        super(GlowSquid.class);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(GlowSquid entity, JsonObject json) {
        super.apply(entity, json);

        entity.setDarkTicksRemaining(json.get("darkTicksRemaining").getAsInt());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull GlowSquid entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("darkTicksRemaining", entity.getDarkTicksRemaining());

        return json;
    }

}
