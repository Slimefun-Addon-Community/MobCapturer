package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.AbstractHorse;

public class UndeadHorseAdapter<T extends AbstractHorse> extends AbstractHorseAdapter<T> {

    public UndeadHorseAdapter(Class<T> entityClass) {
        super(entityClass);
    }

}
