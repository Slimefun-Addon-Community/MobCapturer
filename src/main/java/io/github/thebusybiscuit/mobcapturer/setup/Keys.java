package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;

public final class Keys {
    public static final NamespacedKey DATA = new NamespacedKey(MobCapturer.getInstance(), "captured_mob");
    public static final NamespacedKey INVENTORY = new NamespacedKey(MobCapturer.getInstance(), "mob_inventory");
    private Keys() {}

}
