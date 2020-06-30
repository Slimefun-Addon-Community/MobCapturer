package io.github.thebusybiscuit.mobcapturer;

import java.util.Optional;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PelletListener implements Listener {

    private final MobCapturer plugin;

    public PelletListener(MobCapturer plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball && e.getEntity() instanceof LivingEntity && e.getDamager().hasMetadata("mob_capturing_cannon")) {
            Optional<ItemStack> optional = plugin.capture((LivingEntity) e.getEntity());

            if (optional.isPresent()) {
                e.getDamager().removeMetadata("mob_capturing_cannon", plugin);
                e.getEntity().remove();
                e.getEntity().getWorld().dropItemNaturally(((LivingEntity) e.getEntity()).getEyeLocation(), optional.get());
            }
        }
    }

}
