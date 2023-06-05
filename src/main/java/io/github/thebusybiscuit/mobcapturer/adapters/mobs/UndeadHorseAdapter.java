package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import javax.annotation.Nonnull;

import org.bukkit.entity.AbstractHorse;

/**
 * This is an adapter for the Zombie Horse and the Skeleton Horse.
 */
public class UndeadHorseAdapter<T extends AbstractHorse> extends AbstractHorseAdapter<T> {

    public UndeadHorseAdapter(@Nonnull Class<T> entityClass) {
        super(entityClass);
    }

}
