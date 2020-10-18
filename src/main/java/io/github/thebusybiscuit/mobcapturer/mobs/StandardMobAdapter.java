package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Mob;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class StandardMobAdapter<T extends Mob> implements MobAdapter<T> {

    private final Class<T> entityClass;

    public StandardMobAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

}
