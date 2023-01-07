package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.Raider;

public class RaiderAdapter<T extends Raider> extends AbstractHumanoidAdapter<T> {

    public RaiderAdapter(@Nonnull Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        entity.setCanJoinRaid(json.get("canJoinRaid").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("canJoinRaid", entity.isCanJoinRaid());

        return json;
    }

}
