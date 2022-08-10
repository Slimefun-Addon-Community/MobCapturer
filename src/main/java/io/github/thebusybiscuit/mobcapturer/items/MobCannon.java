package io.github.thebusybiscuit.mobcapturer.items;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;

public class MobCannon extends SimpleSlimefunItem<ItemUseHandler> {

    private final MobCapturer plugin;
    private final MobPellet pellet;

    @ParametersAreNonnullByDefault
    public MobCannon(MobCapturer plugin, ItemGroup itemGroup, SlimefunItemStack item, MobPellet pellet, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.plugin = plugin;
        this.pellet = pellet;
    }

    @Override
    @Nonnull
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (consumeAmmo(e.getPlayer(), pellet)) {
                Snowball projectile = e.getPlayer().launchProjectile(Snowball.class);
                projectile.setMetadata("mob_capturing_cannon", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId()));
            }
        };
    }

    @ParametersAreNonnullByDefault
    private boolean consumeAmmo(Player p, MobPellet pellet) {
        if (p.getGameMode() == GameMode.CREATIVE) {
            return true;
        }

        for (ItemStack item : p.getInventory()) {
            if (pellet.isItem(item)) {
                ItemUtils.consumeItem(item, false);
                return true;
            }
        }

        return false;
    }

}
