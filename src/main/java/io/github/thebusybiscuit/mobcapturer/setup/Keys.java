package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;

/**
 * All the {@link NamespacedKey}s and key strings in MobCapturer.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public final class Keys {
    public static final NamespacedKey DATA = new NamespacedKey(MobCapturer.getInstance(), "captured_mob");
    public static final NamespacedKey INVENTORY = new NamespacedKey(MobCapturer.getInstance(), "mob_inventory");
    public static final String MOB_CAPTURING_PELLET = "mob_capturing_cannon";

    private Keys() {}
}
