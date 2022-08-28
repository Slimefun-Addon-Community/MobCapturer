package io.github.thebusybiscuit.mobcapturer.mobs;

import javax.annotation.Nonnull;

import org.bukkit.entity.AbstractSkeleton;

public class SkeletonAdapter<T extends AbstractSkeleton> extends AbstractHumanoidAdapter<T> {

    public SkeletonAdapter(@Nonnull Class<T> entityClass) {
        super(entityClass);
    }

}
