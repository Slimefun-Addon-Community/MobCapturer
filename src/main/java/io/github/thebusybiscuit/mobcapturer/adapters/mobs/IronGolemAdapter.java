package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.IronGolem;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;

/**
 * This is an adapter for the {@link IronGolem}.
 * It stores whether the Golem was player-made (if true, it will never retaliate against the player)
 */
public class IronGolemAdapter implements MobAdapter<IronGolem> {

    @Override
    @ParametersAreNonnullByDefault
    public void apply(IronGolem entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setPlayerCreated(json.get("playerMade").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull IronGolem entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("playerMade", entity.isPlayerCreated());

        return json;
    }

    @Nonnull
    @Override
    public Class<IronGolem> getEntityClass() {
        return IronGolem.class;
    }

}
