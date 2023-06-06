package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.entity.WanderingTrader;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.NBTAdapter;

/**
 * This is an adapter for the {@link WanderingTraderAdapter}.
 * It stores data about its profession, biome, and memories (including trades and gossip).
 */
public class WanderingTraderAdapter implements MobAdapter<WanderingTrader>, NBTAdapter<WanderingTrader> {
    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        // IsBaby is displayed by the ZombieAdapter
        List<String> lore = MobAdapter.super.getLore(json);

        // Memories are displayed by the NBTAdapter

        return lore;
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull WanderingTrader entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        // Trades and gossip are saved by the NBTAdapter

        return json;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(WanderingTrader entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        // Trades and gossip are applied by the NBTAdapter
    }

    @Nonnull
    @Override
    public Class<WanderingTrader> getEntityClass() {
        return WanderingTrader.class;
    }
}
