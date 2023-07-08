package io.github.thebusybiscuit.mobcapturer.listeners;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.mobcapturer.events.MobCaptureEvent;
import io.github.thebusybiscuit.mobcapturer.items.MobEgg;
import io.github.thebusybiscuit.mobcapturer.setup.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

/**
 * Listener for pellet hit.
 *
 * @author TheBusyBiscuit
 * @author levtey
 * @author ybw0014
 */
public class PelletListener implements Listener {

    private final MobCapturer plugin;

    public PelletListener(@Nonnull MobCapturer plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onProjectileHit(@Nonnull ProjectileHitEvent e) {
        if (e.getEntity() instanceof Snowball pellet
            && e.getHitEntity() instanceof LivingEntity entity
            && pellet.hasMetadata(Keys.MOB_CAPTURING_PELLET)
            && pellet.getShooter() instanceof Player player
            && canCapture(player, entity)
        ) {
            Optional<ItemStack> optional = capture(entity);

            if (optional.isPresent()) {
                pellet.removeMetadata(Keys.MOB_CAPTURING_PELLET, plugin);
                entity.remove();
                dropEgg(player, optional.get(), entity.getLocation());
            }
        }
    }

    /**
     * Check if {@link Player} can capture the specified {@link LivingEntity}.
     *
     * @param p
     *     The {@link Player} to be checked.
     * @param entity
     *     The specified {@link LivingEntity}.
     *
     * @return If {@link Player} can capture the specified {@link LivingEntity}.
     */
    @ParametersAreNonnullByDefault
    protected boolean canCapture(Player p, LivingEntity entity) {
        if (!MobCapturer.getRegistry().getAdapters().containsKey(entity.getType())) {
            return false;
        }

        // event check
        MobCaptureEvent captureEvent = new MobCaptureEvent(p, entity);
        Bukkit.getPluginManager().callEvent(captureEvent);
        return !captureEvent.isCancelled();
    }

    /**
     * Capture the specified {@link LivingEntity}.
     *
     * @param entity
     *     The specified {@link LivingEntity}.
     *
     * @return The egg {@link ItemStack}.
     */
    @Nonnull
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Optional<ItemStack> capture(@Nonnull LivingEntity entity) {
        MobEgg egg = MobCapturer.getRegistry().getAdapters().get(entity.getType());

        if (egg != null) {
            ItemStack item = egg.getEggItem(entity);
            return Optional.of(item);
        } else {
            return Optional.empty();
        }
    }

    /**
     * If mob eggs to inventory is enabled,
     * it will try to add the egg to the {@link Player}'s inventory.
     * If the inventory is full, it will drop the egg {@link ItemStack} at the {@link Location} of {@link Player}.
     * <p>
     * If mob eggs to inventory is disabled, drop the egg to the entity's {@link Location}.
     *
     * @param p
     *     The {@link Player} that is capturing the mob.
     * @param item
     *     The egg {@link ItemStack}.
     * @param loc
     *     The {@link Location} of the {@link LivingEntity}.
     */
    @ParametersAreNonnullByDefault
    protected void dropEgg(Player p, ItemStack item, Location loc) {
        Config config = MobCapturer.getRegistry().getConfig();
        if (config.getBoolean("options.mob-eggs-to-inventory")) {
            Map<Integer, ItemStack> remainingItems = p.getInventory().addItem(item);
            if (!remainingItems.isEmpty()) {
                for (ItemStack remainingItem : remainingItems.values()) {
                    p.getWorld().dropItemNaturally(p.getLocation(), remainingItem);
                }
            }
        } else {
            loc.getWorld().dropItemNaturally(loc, item);
        }
    }
}
