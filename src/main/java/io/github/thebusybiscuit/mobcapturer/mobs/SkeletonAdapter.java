package io.github.thebusybiscuit.mobcapturer.mobs;

import javax.annotation.Nonnull;

import org.bukkit.entity.Skeleton;

public class SkeletonAdapter<T extends Skeleton> extends AbstractHumanoidAdapter<T> {

    public SkeletonAdapter(@Nonnull Class<T> entityClass) {
        super(entityClass);
    }

}
