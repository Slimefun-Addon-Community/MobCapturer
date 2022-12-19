package io.github.thebusybiscuit.mobcapturer.items;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.mobcapturer.setup.ItemStacks;
import io.github.thebusybiscuit.mobcapturer.setup.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;

/**
 * The Mob Cannon can shoot mob capturing pellets.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public class MobCannon extends SimpleSlimefunItem<ItemUseHandler> {

    @ParametersAreNonnullByDefault
    public MobCannon(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (consumeAmmo(e.getPlayer())) {
                Snowball projectile = e.getPlayer().launchProjectile(Snowball.class);
                projectile.setMetadata(Keys.MOB_CAPTURING_PELLET, new FixedMetadataValue(MobCapturer.getInstance(),
                    e.getPlayer().getUniqueId()));
            }
        };
    }

    @ParametersAreNonnullByDefault
    private boolean consumeAmmo(Player p) {
        if (p.getGameMode() == GameMode.CREATIVE) {
            return true;
        }

        for (ItemStack item : p.getInventory()) {
            if (ItemStacks.MOB_CAPTURING_PELLET.getItem().isItem(item)) {
                ItemUtils.consumeItem(item, false);
                return true;
            }
        }

        return false;
    }

}
