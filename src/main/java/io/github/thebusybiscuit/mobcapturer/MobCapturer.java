package io.github.thebusybiscuit.mobcapturer;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.mobcapturer.items.MobCannon;
import io.github.thebusybiscuit.mobcapturer.items.MobPellet;
import io.github.thebusybiscuit.mobcapturer.mobs.AnimalsAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.CreeperAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.MooshroomAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.PigAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.SlimeAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.StandardMobAdapter;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.CSCoreLibPlugin.cscorelib2.updater.GitHubBuildsUpdater;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;

public class MobCapturer extends JavaPlugin implements SlimefunAddon {
	
	private final NamespacedKey key = new NamespacedKey(this, "captured_mob");
	private final Map<EntityType, MobEgg<?>> adapters = new EnumMap<>(EntityType.class);
	
	private Category category;
	private RecipeType recipeType;
	
	@Override
	public void onEnable() {
		Config cfg = new Config(this);
		new Metrics(this, 6672);
		
		if (cfg.getBoolean("options.auto-update")) {
			new GitHubBuildsUpdater(this, getFile(), "TheBusyBiscuit/MobCapturer/master").start();
		}
		
		new CapturingListener(this);
		
		category = new Category(new NamespacedKey(this, "mob_capturer"), new CustomItem(SkullItem.fromHash("d429ff1d2015cb11398471bb2f895f7b4c3ccec201e4ad7a86ff24b744878c"), "&dMobCapturer"));
		
		SlimefunItemStack cannon = new SlimefunItemStack("MOB_CANNON", Material.BLAZE_ROD, "&6Mob Capturing Cannon", "", "&eRight Click &7to shoot a &rMob Caging Pellet");
		
		new MobCannon(category, cannon, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
			null, SlimefunItems.STEEL_INGOT, SlimefunItems.HOOK,
			SlimefunItems.STEEL_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.STEEL_INGOT,
			SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.STEEL_INGOT, null
		}).register(this);
		
		SlimefunItemStack pellet = new SlimefunItemStack("MOB_CAPTURING_PELLET", "983b30e9d135b05190eea2c3ac61e2ab55a2d81e1a58dbb26983a14082664", "&rMob Capturing Pellet", "", "&7These Pellets are used as", "&7Ammunition for your &6Mob Capturing Cannon");
		
		new MobPellet(category, pellet, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				new ItemStack(Material.STRING), new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.STRING),
				SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.EGG), SlimefunItems.MAGIC_LUMP_2,
				new ItemStack(Material.STRING), new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.STRING)
		}).register(this);
		
		register("Bat", EntityType.BAT, new StandardMobAdapter<>(), "93c8aa3fde295fa9f9c27f734bdbab11d33a2e43e855accd7465352377413b");
		
		register("Cow", EntityType.COW, new AnimalsAdapter<>(), "9419f15ff54dae5d040f9b9d8eb2a8989e676710922a0ca164da613ca61e9");
		register("Chicken", EntityType.CHICKEN, new AnimalsAdapter<>(), "d429ff1d2015cb11398471bb2f895f7b4c3ccec201e4ad7a86ff24b744878c");
		register("Pig", EntityType.PIG, new PigAdapter(), "527ad51dd773b72dca1c13e6f3547a83181aad91165282999bbdf13a3b3c9");
		register("Mooshroom", EntityType.MUSHROOM_COW, new MooshroomAdapter(), "41b3b02e36ae9850df25aa09c2ca5a17b9c6616ce53e0b141ad360b6c67c");

		register("Slime", EntityType.SLIME, new SlimeAdapter<>(), "9330af17f8512ed3b49e78bca7ef2d83f2dc1e598a8cb542ecc3b6becee9f57");
		
		register("Creeper", EntityType.CREEPER, new CreeperAdapter(), "87c63d9079b75f90979783cf07ca726f65e3024415ac622a7c906cd25082af");
		register("Blaze", EntityType.BLAZE, new StandardMobAdapter<>(), "533acae6e075a578ccfc7dc2d5a15dbccfa8f59c609f9703889ef54c742c56");
		register("Magma Cube", EntityType.MAGMA_CUBE, new SlimeAdapter<>(), "1185657c38acdd8f95e1d2cd1115bb0f11139ad2b3ce442267e69706d916e");
	}
	
	public <T extends LivingEntity> void register(String name, EntityType type, MobAdapter<T> adapter, String eggTexture) {
		SlimefunItemStack itemstack = new SlimefunItemStack("MOB_EGG_" + type.toString(), eggTexture, "&aMob Egg &7()", "", "&7Right Click this Item on a Block", "&rto release your captured Mob");
		
		SlimefunItem item = new SlimefunItem(category, itemstack, recipeType, new ItemStack[] {
			null, null, null, null, new CustomItem(SkullItem.fromHash(eggTexture), "&r" + name), null, null, null, null
		});
		
		item.register(this);
		
		if (!item.isDisabled()) {
			adapters.put(type, new MobEgg<T>(adapter, itemstack));
		}
	}

	@Override
	public String getBugTrackerURL() {
		return "https://github.com/TheBusyBiscuit/MobCapturer/issues";
	}

	@Override
	public JavaPlugin getJavaPlugin() {
		return this;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Optional<ItemStack> capture(LivingEntity entity) {
		MobEgg egg = adapters.get(entity.getType());
		
		if (egg != null) {
			ItemStack item = egg.getItem(key, entity);
			return Optional.of(item);
		}
		else {
			return Optional.empty();
		}
	}

}
