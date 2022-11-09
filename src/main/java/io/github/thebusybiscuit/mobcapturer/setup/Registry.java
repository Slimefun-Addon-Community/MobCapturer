package io.github.thebusybiscuit.mobcapturer.setup;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Nonnull;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

import org.bukkit.entity.EntityType;

import io.github.thebusybiscuit.mobcapturer.items.MobEgg;

public final class Registry {
    private final Map<EntityType, MobEgg<?>> adapters = new EnumMap<>(EntityType.class);
    private final Config config;

    public Registry(Config config) {
        this.config = config;
    }

    @Nonnull
    public Map<EntityType, MobEgg<?>> getAdapters() {
        return adapters;
    }

    @Nonnull
    public Config getConfig() {
        return config;
    }
}