package io.github.thebusybiscuit.mobcapturer.items;

import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class MobCannon extends SimpleSlimefunItem<ItemUseHandler> {

	private final MobCapturer plugin;
	private final ItemStack pellet;
	
	public MobCannon(MobCapturer plugin, Category category, SlimefunItemStack item, ItemStack pellet, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, recipeType, recipe);
		
		this.plugin = plugin;
		this.pellet = pellet;
	}

	@Override
	public ItemUseHandler getItemHandler() {
		return e -> {
			if (SlimefunManager.containsSimilarItem(e.getPlayer().getInventory(), pellet, true)) {
				Snowball projectile = e.getPlayer().launchProjectile(Snowball.class);
				projectile.setMetadata("mob_capturing_cannon", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId()));
				
				// TODO: Remove pellet
			}
		};
	}

}
