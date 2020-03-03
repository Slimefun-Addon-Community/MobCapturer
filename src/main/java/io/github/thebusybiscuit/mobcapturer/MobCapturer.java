package io.github.thebusybiscuit.mobcapturer;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Turtle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.mobcapturer.items.MobCannon;
import io.github.thebusybiscuit.mobcapturer.items.MobPellet;
import io.github.thebusybiscuit.mobcapturer.mobs.AnimalsAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.CreeperAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.MooshroomAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.PhantomAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.PigAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.RabbitAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.SheepAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.ShulkerAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.SlimeAdapter;
import io.github.thebusybiscuit.mobcapturer.mobs.StandardMobAdapter;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.CSCoreLibPlugin.cscorelib2.updater.GitHubBuildsUpdater;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
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
		SlimefunItemStack pellet = new SlimefunItemStack("MOB_CAPTURING_PELLET", "983b30e9d135b05190eea2c3ac61e2ab55a2d81e1a58dbb26983a14082664", "&rMob Capturing Pellet", "", "&7These Pellets are used as", "&7Ammunition for your &6Mob Capturing Cannon");
		
		new MobCannon(this, category, cannon, pellet, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
			null, SlimefunItems.STEEL_INGOT, SlimefunItems.HOOK,
			SlimefunItems.STEEL_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.STEEL_INGOT,
			SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.STEEL_INGOT, null
		}).register(this);
		
		new MobPellet(category, pellet, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
			new ItemStack(Material.STRING), new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.STRING),
			SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.EGG), SlimefunItems.MAGIC_LUMP_2,
			new ItemStack(Material.STRING), new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.STRING)
		}).register(this);
		
		register("Bat", EntityType.BAT, new StandardMobAdapter<>(Bat.class), "93c8aa3fde295fa9f9c27f734bdbab11d33a2e43e855accd7465352377413b");
		register("Squid", EntityType.SQUID, new StandardMobAdapter<>(Squid.class), "449088861fc1e14b605a5154d79fa7dd65e041a5c635d24744b3e152535");
		
		register("Cow", EntityType.COW, new AnimalsAdapter<>(Cow.class), "9419f15ff54dae5d040f9b9d8eb2a8989e676710922a0ca164da613ca61e9");
		register("Chicken", EntityType.CHICKEN, new AnimalsAdapter<>(Chicken.class), "d429ff1d2015cb11398471bb2f895f7b4c3ccec201e4ad7a86ff24b744878c");
		register("Pig", EntityType.PIG, new PigAdapter(), "527ad51dd773b72dca1c13e6f3547a83181aad91165282999bbdf13a3b3c9");
		register("Mooshroom", EntityType.MUSHROOM_COW, new MooshroomAdapter(), "41b3b02e36ae9850df25aa09c2ca5a17b9c6616ce53e0b141ad360b6c67c");
		register("Rabbit", EntityType.RABBIT, new RabbitAdapter(), "63e06ed8809243e317393f6162679b2c1fe6911eda2d30cb99cfc82d347cb");
		register("Sheep", EntityType.SHEEP, new SheepAdapter(), "ff481f77347fe59c083665c9efbb49071d493ea2027454aee87735d63bf3b");
		register("Turtle", EntityType.TURTLE, new AnimalsAdapter<>(Turtle.class), "15a45e24cadc18f305291af45a22fc8b3607a675baa31ed583d3a56b15223c5c");
		register("Polar Bear", EntityType.POLAR_BEAR, new AnimalsAdapter<>(PolarBear.class), "291abcab7a20b28195c0f1786db28c7670c2979243de71703b04e9d93f59aa8d");
		register("Dolphin", EntityType.DOLPHIN, new StandardMobAdapter<>(Dolphin.class), "2480cd9577e2173e1c9de5e41318bd859696215a0a7de9242f01c01b8e6c06bf");
		
		register("Slime", EntityType.SLIME, new SlimeAdapter<>(Slime.class), "9330af17f8512ed3b49e78bca7ef2d83f2dc1e598a8cb542ecc3b6becee9f57");
		register("Spider", EntityType.SPIDER, new StandardMobAdapter<>(Spider.class), "5d59aa78cb7e9b6ca6fee4121329059dd68afddc0c8b53a906b7953994e8a76");
		register("Cave Spider", EntityType.CAVE_SPIDER, new StandardMobAdapter<>(CaveSpider.class), "16617131250e578333a441fdf4a5b8c62163640a9d06cd67db89031d03accf6");
		register("Guardian", EntityType.GUARDIAN, new StandardMobAdapter<>(Guardian.class), "fe119aaa4999648a75b978efafa97edab1cdca1ff1d8301ba61cdc2db1606e22");
		register("Elder Guardian", EntityType.ELDER_GUARDIAN, new StandardMobAdapter<>(ElderGuardian.class), "9a839d8256c81cf1db8da8ff3f7b80cce2f865b80c9f66aea5340e697ea3e219");
		register("Phantom", EntityType.PHANTOM, new PhantomAdapter(), "9381dfeac8a050d330fde058bad5f5e6f228f28cd379761c1147e17c4ed605b");
		
		register("Creeper", EntityType.CREEPER, new CreeperAdapter(), "87c63d9079b75f90979783cf07ca726f65e3024415ac622a7c906cd25082af");
		register("Blaze", EntityType.BLAZE, new StandardMobAdapter<>(Blaze.class), "533acae6e075a578ccfc7dc2d5a15dbccfa8f59c609f9703889ef54c742c56");
		register("Magma Cube", EntityType.MAGMA_CUBE, new SlimeAdapter<>(MagmaCube.class), "1185657c38acdd8f95e1d2cd1115bb0f11139ad2b3ce442267e69706d916e");
		register("Ghast", EntityType.GHAST, new StandardMobAdapter<>(Ghast.class), "c442c228f099fdfc1c6b46dfc80b252d81f7fb1739deb16ee7a597c17f7c9");
		register("Silverfish", EntityType.SILVERFISH, new StandardMobAdapter<>(Silverfish.class), "d06310a8952b265c6e6bed4348239ddea8e5482c8c68be6fff981ba8056bf2e");
		register("Shulker", EntityType.SHEEP, new ShulkerAdapter(), "d04252216231b3f744c9ff4ace7084ae9f4164f8b384c65410848a19617af4d");
		
	}
	
	public <T extends LivingEntity> void register(String name, EntityType type, MobAdapter<T> adapter, String eggTexture) {
		SlimefunItemStack itemstack = new SlimefunItemStack("MOB_EGG_" + type.toString(), eggTexture, "&aMob Egg &7()", "", "&7Right Click this Item on a Block", "&rto release your captured Mob");
		
		MobEgg<T> egg = new MobEgg<>(category, itemstack, key, adapter, recipeType, new ItemStack[] {
			null, null, null, null, new CustomItem(SkullItem.fromHash(eggTexture), "&r" + name), null, null, null, null
		});
		
		egg.register(this);
		
		if (!egg.isDisabled()) {
			adapters.put(type, egg);
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
			ItemStack item = egg.getEggItem(entity);
			return Optional.of(item);
		}
		else {
			return Optional.empty();
		}
	}

}
