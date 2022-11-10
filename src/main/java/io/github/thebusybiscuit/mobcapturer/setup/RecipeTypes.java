package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public final class RecipeTypes {
    public static final RecipeType MOB_CAPTURING = new RecipeType(
        new NamespacedKey(MobCapturer.getInstance(), "mob_capturing"),
        new CustomItemStack(
            Items.MOB_CANNON,
            "&6Mob Capturing Cannon",
            "&7Use a &6Mob Capturing Cannon",
            "&7to catch the given Mob."
        )
    );

    private RecipeTypes() {}
}
