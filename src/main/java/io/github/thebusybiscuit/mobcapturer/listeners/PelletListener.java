package io.github.thebusybiscuit.mobcapturer.listeners;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.mobcapturer.items.MobEgg;
import io.github.thebusybiscuit.mobcapturer.setup.Keys;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;

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
    public void onProjectileHit(@Nonnull EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball pellet
            && e.getEntity() instanceof LivingEntity entity
            && pellet.hasMetadata(Keys.MOB_CAPTURING_PELLET)
            && pellet.getShooter() instanceof Player player
            && canCapture(player, entity)
        ) {
            Optional<ItemStack> optional = capture(entity);

            if (optional.isPresent()) {
                pellet.removeMetadata(Keys.MOB_CAPTURING_PELLET, plugin);
                entity.remove();
                entity.getWorld().dropItemNaturally(entity.getEyeLocation(), optional.get());
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
        if (Slimefun.getProtectionManager().hasPermission(p, entity.getLocation(), Interaction.ATTACK_ENTITY)) {
            if (MobCapturer.getRegistry().getConfig().getBoolean("options.capture-named-mobs")) {
                return true;
            } else {
                return entity.getCustomName() == null;
            }
        } else {
            return false;
        }
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
}
