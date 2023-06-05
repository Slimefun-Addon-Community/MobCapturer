package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import javax.annotation.Nonnull;

import org.bukkit.entity.AbstractSkeleton;

/**
 * This is an adapter for the {@link AbstractSkeleton}.
 * It is used for all Skeletons, including Strays and Wither Skeletons.
 */
public class SkeletonAdapter<T extends AbstractSkeleton> extends AbstractHumanoidAdapter<T> {

    public SkeletonAdapter(@Nonnull Class<T> entityClass) {
        super(entityClass);
    }

}
