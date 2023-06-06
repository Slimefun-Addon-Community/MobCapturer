package io.github.thebusybiscuit.mobcapturer.events;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import io.github.thebusybiscuit.mobcapturer.items.MobEgg;
import io.github.thebusybiscuit.mobcapturer.items.MobPellet;

/**
 * This event is fired when a {@link LivingEntity} is hit by {@link MobPellet},
 * and the {@link LivingEntity} has a corresponding {@link MobEgg}.
 * It can be cancelled so the {@link LivingEntity} will not be captured.
 *
 * @author ybw0014
 */
public class MobCaptureEvent extends MobCapturerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private boolean cancelled;

    @ParametersAreNonnullByDefault
    public MobCaptureEvent(Player player, LivingEntity entity) {
        super(player, entity);
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
}
