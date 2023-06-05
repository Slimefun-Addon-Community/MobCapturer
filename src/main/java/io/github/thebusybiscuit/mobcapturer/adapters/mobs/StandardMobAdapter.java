package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import javax.annotation.Nonnull;

import org.bukkit.entity.Mob;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;

/**
 * This is an adapter used for any mobs which have no extra attributes to store,
 * other than those stored for all living entities.
 * 
 * Mobs like Spiders, Squids, Bats, Ghasts, Salmon, Cod, etc...
 */
public class StandardMobAdapter<T extends Mob> implements MobAdapter<T> {

    private final Class<T> entityClass;

    public StandardMobAdapter(@Nonnull Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Nonnull
    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

}
