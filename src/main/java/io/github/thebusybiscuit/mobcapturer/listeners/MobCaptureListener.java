package io.github.thebusybiscuit.mobcapturer.listeners;

import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.mobcapturer.events.MobCaptureEvent;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;

/**
 * Listener for mob capturing.
 *
 * @author ybw0014
 */
public class MobCaptureListener implements Listener {

    private final MobCapturer plugin;

    public MobCaptureListener(@Nonnull MobCapturer plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMobCapture(@Nonnull MobCaptureEvent e) {
        Config config = MobCapturer.getRegistry().getConfig();
        LivingEntity entity = e.getEntity();
        Player p = e.getPlayer();

        // permission check
        if (!Slimefun.getProtectionManager().hasPermission(p, entity.getLocation(), Interaction.ATTACK_ENTITY)) {
            e.setCancelled(true);
            return;
        }

        // has custom name
        if (entity.getCustomName() != null) {
            // not going to capture named mobs
            if (!config.getBoolean("options.capture-named-mobs")) {
                e.setCancelled(true);
                return;
            }

            // check ignored name list
            List<String> ignoredMobNames = config.getStringList("options.ignored-mobs");
            if (ignoredMobNames.size() > 0) {
                String strippedEntityName = ChatColor.stripColor(entity.getCustomName());
                for (String ignoredMobName : ignoredMobNames) {
                    if (ignoredMobName.equalsIgnoreCase(strippedEntityName)) {
                        e.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }
}
