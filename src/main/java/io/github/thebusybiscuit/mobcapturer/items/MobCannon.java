package io.github.thebusybiscuit.mobcapturer.items;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;

public class MobCannon extends SimpleSlimefunItem<ItemUseHandler> {

    private final MobCapturer plugin;
    private final MobPellet pellet;

    public MobCannon(MobCapturer plugin, Category category, SlimefunItemStack item, MobPellet pellet, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        this.plugin = plugin;
        this.pellet = pellet;
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (consumeAmmo(e.getPlayer(), pellet)) {
                Snowball projectile = e.getPlayer().launchProjectile(Snowball.class);
                projectile.setMetadata("mob_capturing_cannon", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId()));
            }
        };
    }

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
