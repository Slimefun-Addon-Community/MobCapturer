package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

public final class Items {
    public static final SlimefunItemStack CANNON = new SlimefunItemStack(
        "MOB_CANNON",
        Material.BLAZE_ROD,
        "&6Mob Capturing Cannon",
        "",
        "&eRight Click &7to shoot a &fMob Caging Pellet"
    );
    public static final SlimefunItemStack PELLET = new SlimefunItemStack(
        "MOB_CAPTURING_PELLET",
        "983b30e9d135b05190eea2c3ac61e2ab55a2d81e1a58dbb26983a14082664",
        "&fMob Capturing Pellet",
        "",
        "&7These Pellets are used as",
        "&7Ammunition for your &6Mob Capturing Cannon"
    );

    private Items() {}
}
