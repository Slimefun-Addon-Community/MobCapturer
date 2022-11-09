package io.github.thebusybiscuit.mobcapturer.setup;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.entity.EntityType;

import io.github.thebusybiscuit.mobcapturer.items.MobEgg;

public final class Registry {
    private final Map<EntityType, MobEgg<?>> adapters = new EnumMap<>(EntityType.class);

    public Registry() {}

    @Nonnull
    public Map<EntityType, MobEgg<?>> getAdapters() {
        return adapters;
    }
}
