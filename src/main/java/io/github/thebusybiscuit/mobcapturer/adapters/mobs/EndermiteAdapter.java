package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.Endermite;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;

/**
 * This is an adapter for the {@link Endermite}.
 */
public class EndermiteAdapter implements MobAdapter<Endermite> {

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public void apply(Endermite entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        // This line is deprecated as the functionality no longer exists in newer versions of Minecraft
        entity.setPlayerSpawned(json.get("playerSpawned").getAsBoolean());
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public JsonObject saveData(@Nonnull Endermite entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        // This line is deprecated as the functionality no longer exists in newer versions of Minecraft
        json.addProperty("playerSpawned", entity.isPlayerSpawned());

        return json;
    }

    @Nonnull
    @Override
    public Class<Endermite> getEntityClass() {
        return Endermite.class;
    }

}
