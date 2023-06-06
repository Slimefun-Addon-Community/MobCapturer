package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.Warden;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.NBTAdapter;

/**
 * This is an adapter for the {@link Warden}.
 * It saves the warden's memories (including its anger and suspects).
 */
public class WardenAdapter implements MobAdapter<Warden>, NBTAdapter<Warden> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        // Memories are handled by the NBTAdapter

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Warden entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        // Memories are handled by the NBTAdapter
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Warden entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        // Memories are handled by the NBTAdapter

        return json;
    }

    @Nonnull
    @Override
    public Class<Warden> getEntityClass() {
        return Warden.class;
    }

}
