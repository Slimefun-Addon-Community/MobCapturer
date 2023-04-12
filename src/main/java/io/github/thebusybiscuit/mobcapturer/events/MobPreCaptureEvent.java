package io.github.thebusybiscuit.mobcapturer.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import io.github.thebusybiscuit.mobcapturer.items.MobPellet;

/**
 * This event is fired when a {@link LivingEntity} is hit by {@link MobPellet}.
 * It can be cancelled so the {@link LivingEntity} will not be captured.
 *
 * @author ybw0014
 */
public final class MobPreCaptureEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Nonnull
    private final Player player;
    @Nonnull
    private final LivingEntity entity;
    private boolean cancelled;

    @ParametersAreNonnullByDefault
    public MobPreCaptureEvent(Player player, LivingEntity entity) {
        this.player = player;
        this.entity = entity;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Nonnull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
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
