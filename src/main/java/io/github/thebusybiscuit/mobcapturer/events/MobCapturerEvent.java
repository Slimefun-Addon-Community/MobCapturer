package io.github.thebusybiscuit.mobcapturer.events;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * This is an abstract event that has 2 fields: Player and LivingEntity.
 * Every MobCapturer event extends this class.
 *
 * @author ybw0014
 */
public abstract class MobCapturerEvent extends Event {
    @Nonnull
    private final Player player;
    @Nonnull
    private final LivingEntity entity;

    @ParametersAreNonnullByDefault
    public MobCapturerEvent(Player player, LivingEntity entity) {
        this.player = player;
        this.entity = entity;
    }

    /**
     * @return The {@link Player} that is capturing mob.
     */
    @Nonnull
    public Player getPlayer() {
        return player;
    }

    /**
     * @return The {@link LivingEntity} that is being captured.
     */
    @Nonnull
    public LivingEntity getEntity() {
        return entity;
    }
}
