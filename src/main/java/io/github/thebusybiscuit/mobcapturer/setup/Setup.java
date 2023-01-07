package io.github.thebusybiscuit.mobcapturer.setup;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cod;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Mule;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Ravager;
import org.bukkit.entity.Salmon;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Stray;
import org.bukkit.entity.TraderLlama;
import org.bukkit.entity.Turtle;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.AllayAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.AnimalsAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.AxolotlAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.BeeAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.CatAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.ChestedHorseAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.CreeperAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.EndermiteAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.FoxAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.FrogAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.GlowSquidAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.GoatAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.HoglinAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.HorseAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.IronGolemAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.LlamaAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.MagicIllagerAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.MooshroomAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.PandaAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.ParrotAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.PhantomAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.PigAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.PiglinAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.PiglinBruteAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.PufferFishAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.RabbitAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.RaiderAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.SheepAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.ShulkerAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.SkeletonAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.SlimeAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.SnowmanAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.StandardMobAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.StriderAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.TadpoleAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.TropicalFishAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.UndeadHorseAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.VexAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.WolfAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.ZoglinAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.ZombieAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.ZombieVillagerAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.ZombifiedPiglinAdapter;
import io.github.thebusybiscuit.mobcapturer.items.MobCannon;
import io.github.thebusybiscuit.mobcapturer.items.MobEgg;
import io.github.thebusybiscuit.mobcapturer.items.MobPellet;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

/**
 * Setup {@link MobCapturer}.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public final class Setup {
    private Setup() {}

    public static void setup() {
        MobCapturer plugin = MobCapturer.getInstance();

        // items
        new MobPellet(
            ItemGroups.TOOLS,
            ItemStacks.MOB_CAPTURING_PELLET,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.STRING), new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.STRING),
                SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.EGG), SlimefunItems.MAGIC_LUMP_2,
                new ItemStack(Material.STRING), new ItemStack(Material.IRON_NUGGET), new ItemStack(Material.STRING)
            }
        ).register(plugin);

        new MobCannon(
            ItemGroups.TOOLS,
            ItemStacks.MOB_CANNON,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                null, SlimefunItems.STEEL_INGOT, SlimefunItems.HOOK,
                SlimefunItems.STEEL_INGOT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.STEEL_INGOT,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.STEEL_INGOT, null
            }
        ).register(plugin);

        setupMobEggs();

        // researches
        Researches.MOB_CAPTURING.addItems(ItemStacks.MOB_CANNON, ItemStacks.MOB_CAPTURING_PELLET);
        Researches.MOB_CAPTURING.register();
    }

    private static void setupMobEggs() {
        // Animals
        registerMob(EntityType.COW, new AnimalsAdapter<>(Cow.class), "9419f15ff54dae5d040f9b9d8eb2a8989e676710922a0ca164da613ca61e9");
        registerMob(EntityType.CHICKEN, new AnimalsAdapter<>(Chicken.class), "d429ff1d2015cb11398471bb2f895f7b4c3ccec201e4ad7a86ff24b744878c");
        registerMob(EntityType.PIG, new PigAdapter(), "527ad51dd773b72dca1c13e6f3547a83181aad91165282999bbdf13a3b3c9");
        registerMob(EntityType.MUSHROOM_COW, new MooshroomAdapter(), "41b3b02e36ae9850df25aa09c2ca5a17b9c6616ce53e0b141ad360b6c67c");
        registerMob(EntityType.RABBIT, new RabbitAdapter(), "63e06ed8809243e317393f6162679b2c1fe6911eda2d30cb99cfc82d347cb");
        registerMob(EntityType.SHEEP, new SheepAdapter(), "ff481f77347fe59c083665c9efbb49071d493ea2027454aee87735d63bf3b");
        registerMob(EntityType.TURTLE, new AnimalsAdapter<>(Turtle.class), "15a45e24cadc18f305291af45a22fc8b3607a675baa31ed583d3a56b15223c5c");
        registerMob(EntityType.POLAR_BEAR, new AnimalsAdapter<>(PolarBear.class), "291abcab7a20b28195c0f1786db28c7670c2979243de71703b04e9d93f59aa8d");
        registerMob(EntityType.PANDA, new PandaAdapter(), "1ab24611bb37ce3971fdbf01ba3f11bd2e4c72f5d40b6d8d8d536d69e695cd0c");
        registerMob(EntityType.FOX, new FoxAdapter(), "db8c11abae08927c8ae413d22f2f556c954c7d8a4edf0aa3f4b86597fb7b1cec");
        registerMob(EntityType.BEE, new BeeAdapter(), "208e55d3b4a865f4888174c02592a631d0a10475fa2686b490d465860fe8cf91");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
            registerMob(EntityType.AXOLOTL, new AxolotlAdapter(), "62d90ad63dd826df02994abdcc6c2306163e1072d1b9e63ad4e7d7d1cf87cdf9");
            registerMob(EntityType.GOAT, new GoatAdapter(), "33f7fe31285bd2ca74516b07852e079447f524fd9cc0b7d4db003b165d5b4b4");
        }
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            registerMob(EntityType.FROG, new FrogAdapter(), "d2c3b98ada19957f8d83a7d42faf81a290fae7d08dbf6c1f8992a1ada44b31"); // not egg texture
        }

        // Mobs
        registerMob(EntityType.SLIME, new SlimeAdapter<>(Slime.class), "9330af17f8512ed3b49e78bca7ef2d83f2dc1e598a8cb542ecc3b6becee9f57");
        registerMob(EntityType.SPIDER, new StandardMobAdapter<>(Spider.class), "5d59aa78cb7e9b6ca6fee4121329059dd68afddc0c8b53a906b7953994e8a76");
        registerMob(EntityType.CAVE_SPIDER, new StandardMobAdapter<>(CaveSpider.class), "16617131250e578333a441fdf4a5b8c62163640a9d06cd67db89031d03accf6");
        registerMob(EntityType.CREEPER, new CreeperAdapter(), "87c63d9079b75f90979783cf07ca726f65e3024415ac622a7c906cd25082af");
        registerMob(EntityType.PHANTOM, new PhantomAdapter(), "9381dfeac8a050d330fde058bad5f5e6f228f28cd379761c1147e17c4ed605b");
        registerMob(EntityType.SILVERFISH, new StandardMobAdapter<>(Silverfish.class), "d06310a8952b265c6e6bed4348239ddea8e5482c8c68be6fff981ba8056bf2e");
        registerMob(EntityType.BAT, new StandardMobAdapter<>(Bat.class), "93c8aa3fde295fa9f9c27f734bdbab11d33a2e43e855accd7465352377413b");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            registerMob(EntityType.ALLAY, new AllayAdapter(), "40e1c7064af7dee68677efaa95f6e6e01430b006dd91638ea2a61849254488ec"); // not egg texture
        }

        // Water Mobs
        registerMob(EntityType.SQUID, new StandardMobAdapter<>(Squid.class), "449088861fc1e14b605a5154d79fa7dd65e041a5c635d24744b3e152535");
        registerMob(EntityType.GUARDIAN, new StandardMobAdapter<>(Guardian.class), "fe119aaa4999648a75b978efafa97edab1cdca1ff1d8301ba61cdc2db1606e22");
        registerMob(EntityType.ELDER_GUARDIAN, new StandardMobAdapter<>(ElderGuardian.class), "9a839d8256c81cf1db8da8ff3f7b80cce2f865b80c9f66aea5340e697ea3e219");
        registerMob(EntityType.DOLPHIN, new StandardMobAdapter<>(Dolphin.class), "2480cd9577e2173e1c9de5e41318bd859696215a0a7de9242f01c01b8e6c06bf");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
            registerMob(EntityType.GLOW_SQUID, new GlowSquidAdapter(), "e5d31559261b3e79024751fe07b711c8feef51d56c03635226955805bc42894e");
        }

        // Tameables
        registerMob(EntityType.WOLF, new WolfAdapter(), "4399c973d6496d1d258492c28d4c95956ac3a253762bf15f7644af1f5728dd");
        registerMob(EntityType.OCELOT, new AnimalsAdapter<>(Ocelot.class), "c579a743b66bd0b4d559898ed1b9857a49f1127d9d237bed3dc97bceb9379a5");
        registerMob(EntityType.CAT, new CatAdapter(), "f2154e385f9ccb187b53cf290fe9a24f98c7d583c368986c7806c312c3f29d79");
        registerMob(EntityType.PARROT, new ParrotAdapter(), "6eabd8fffc0371877e88ffc3cd0315200534dd2fcc6034588000736fb80033e5");

        // Nether
        registerMob(EntityType.BLAZE, new StandardMobAdapter<>(Blaze.class), "533acae6e075a578ccfc7dc2d5a15dbccfa8f59c609f9703889ef54c742c56");
        registerMob(EntityType.MAGMA_CUBE, new SlimeAdapter<>(MagmaCube.class), "1185657c38acdd8f95e1d2cd1115bb0f11139ad2b3ce442267e69706d916e");
        registerMob(EntityType.GHAST, new StandardMobAdapter<>(Ghast.class), "c442c228f099fdfc1c6b46dfc80b252d81f7fb1739deb16ee7a597c17f7c9");
        registerMob(EntityType.PIGLIN, new PiglinAdapter(), "f2f621045771323d916c26bfb5ebec5738dbff8301246fe1481e9f9d25326f3b");
        registerMob(EntityType.PIGLIN_BRUTE, new PiglinBruteAdapter(), "b13a4d678041dff776fffd96486e90f1b5e115af33647454caf401fd3fd00d5e");
        registerMob(EntityType.ZOMBIFIED_PIGLIN, new ZombifiedPiglinAdapter(), "4db2e40571acefdcb7f15f94e1f174c3b9c299921f14da052628a0ba18e0c323");
        registerMob(EntityType.HOGLIN, new HoglinAdapter(), "a6eddc5a25d4ff8f9f3490d673613093e65b866d369a26f8ee8b93983c692be3");
        registerMob(EntityType.ZOGLIN, new ZoglinAdapter(), "31a6d71f3587ad1888d757fea265614ab7d0204daade131954fb675e92585227");
        registerMob(EntityType.STRIDER, new StriderAdapter(), "34f2759b413a8a645504fb5074dae1dddcc8af150b7fe06cc5832667e776b6f7");

        // Ender things
        registerMob(EntityType.SHULKER, new ShulkerAdapter(), "d04252216231b3f744c9ff4ace7084ae9f4164f8b384c65410848a19617af4d");
        registerMob(EntityType.ENDERMITE, new EndermiteAdapter(), "3beac501e97db1cc035287d068a8eb538e55ef802f5cca25683933a243136c");

        // Golems
        registerMob(EntityType.SNOWMAN, new SnowmanAdapter(), "2e4385d58fe46dd96422f31d35bbd1568e5819bbdb7a196c9f113424582cf977");
        registerMob(EntityType.IRON_GOLEM, new IronGolemAdapter(), "c442c228f099fdfc1c6b46dfc80b252d81f7fb1739deb16ee7a597c17f7c9");

        // Illagers
        registerMob(EntityType.WITCH, new RaiderAdapter<>(Witch.class), "afbdceef773d959b49ddd9615f4269c176e23154d45752667428dc4e3fd4d");
        registerMob(EntityType.VINDICATOR, new RaiderAdapter<>(Vindicator.class), "8e8e3de7718a54553dd2fc5b2415a08b05f2339b772fe181b65c507fda4e34c1");
        registerMob(EntityType.PILLAGER, new RaiderAdapter<>(Pillager.class), "8fd4983e30b277f0b97b7d8c6f8a0358201be226a2c55e2a0d390c3942ec2df5");
        registerMob(EntityType.RAVAGER, new RaiderAdapter<>(Ravager.class), "79b625b80cfb0baf04eebbd2cb1ff9f1010b02f4df21b3baf86eb812ab7eba8b");
        registerMob(EntityType.EVOKER, new MagicIllagerAdapter<>(Evoker.class), "ff1eeb387d55b0886a69b6ec62a6e69706f32aba2547e10583060b976341f9be");
        registerMob(EntityType.ILLUSIONER, new MagicIllagerAdapter<>(Illusioner.class), "ff1eeb387d55b0886a69b6ec62a6e69706f32aba2547e10583060b976341f9be");
        registerMob(EntityType.VEX, new VexAdapter(), "dc7eb861fd9999bf87a300e3ddd03c57313ddfba221d1c7d4bd62cef31446ca1");

        // Fish
        registerMob(EntityType.COD, new StandardMobAdapter<>(Cod.class), "bd29b25579f9d3a67b612ae8ef96b31feca6c9e7e6c70ac81156d778cbe7db9d");
        registerMob(EntityType.SALMON, new StandardMobAdapter<>(Salmon.class), "5c46c568e8b5c55853a92869ea19c00b7720c328a2f16c5950b9e2e897fc27a1");
        registerMob(EntityType.PUFFERFISH, new PufferFishAdapter(), "5d5e7d191478efafe23a654de802760f42a0dd83dfc9817f87d460fcf32978df");
        registerMob(EntityType.TROPICAL_FISH, new TropicalFishAdapter(), "2e4385d58fe46dd96422f31d35bbd1568e5819bbdb7a196c9f113424582cf977");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            registerMob(EntityType.TADPOLE, new TadpoleAdapter(), "33fcf39f9db88835249f90af1fad377b8c71107d903a4050a963fb4ac58b17c1"); // not egg texture, bucket of tadpole
        }

        // Horses
        registerMob(EntityType.HORSE, new HorseAdapter(), "5c6d5abbf68ccb2386bf16af25ac38d8b77bb0e043152461bd97f3f630dbb8bc");
        registerMob(EntityType.DONKEY, new ChestedHorseAdapter<>(Donkey.class), "db522f6d77c0696c9d1f2ad49bfa3cb8205a5e623af1c420bd740dc471914e97");
        registerMob(EntityType.MULE, new ChestedHorseAdapter<>(Mule.class), "e4ad78f7ada7c6376449ef949c9c87fdece882b5a2f14cfbf8eac6fea657f4c7");
        registerMob(EntityType.ZOMBIE_HORSE, new UndeadHorseAdapter<>(ZombieHorse.class), "ec5b6f8ef1d75f73a5290c9367d2b9b823bc963de2a366fd6550bcace2751205");
        registerMob(EntityType.SKELETON_HORSE, new UndeadHorseAdapter<>(SkeletonHorse.class), "9dc084b7874268973006c897a03d8906cc9b3df8c39bce93d87ec0df507bbe0d");
        registerMob(EntityType.LLAMA, new LlamaAdapter<>(Llama.class), "5cbc6bd92728d79cfa6d8f23cbae9d912f495920b9e95ef691a1967fef8a4453");
        registerMob(EntityType.TRADER_LLAMA, new LlamaAdapter<>(TraderLlama.class), "5cbc6bd92728d79cfa6d8f23cbae9d912f495920b9e95ef691a1967fef8a4453");

        // Skeletons
        registerMob(EntityType.SKELETON, new SkeletonAdapter<>(Skeleton.class), "377055cadacbb0f8f35c1d18acc2ed86e0bcc6d73dda71e4c59f7ea28b7b27b6");
        registerMob(EntityType.WITHER_SKELETON, new SkeletonAdapter<>(WitherSkeleton.class), "337223d01906ab63af1a15988343b8637e85930b905c35125b545b398c59e1c5");
        registerMob(EntityType.STRAY, new SkeletonAdapter<>(Stray.class), "5b45aae241779f0617ffaff468f3f2cf666d2f8a803002f9ae1ba0f14ed79fdd");

        // Zombies
        registerMob(EntityType.ZOMBIE, new ZombieAdapter<>(Zombie.class), "77f844bfea25429d45e1fcf96ef6654dfaaa6fc902dc1b6b68c0abc1343447");
        registerMob(EntityType.DROWNED, new ZombieAdapter<>(Drowned.class), "13a29552b3abaf388145be8a9067e5ea4d1af945e7bf5e2ca852cec87fd1b17b");
        registerMob(EntityType.HUSK, new ZombieAdapter<>(Husk.class), "40fd8d86e7057aae33f5d79dcb03685c88c8137cfcb2349bc874b9f87e934276");
        registerMob(EntityType.ZOMBIE_VILLAGER, new ZombieVillagerAdapter(), "364b4c94dda909ecc778db93572b847a5df51c4a49894d6a9df59b8df97842c5");

    }

    @ParametersAreNonnullByDefault
    private static <T extends LivingEntity> void registerMob(EntityType type, MobAdapter<T> adapter,
                                                             String eggTexture) {
        String name = ChatUtils.humanize(type.name());

        MobEgg<T> egg = new MobEgg<>(
            ItemGroups.MOB_EGGS,
            ItemStacks.buildMobEgg(type, eggTexture),
            adapter,
            RecipeTypes.MOB_CAPTURING,
            new ItemStack[] {
                null, null, null,
                null, new CustomItemStack(SlimefunUtils.getCustomHead(eggTexture), ChatColor.WHITE + name), null,
                null, null, null
            }
        );

        egg.register(MobCapturer.getInstance());

        if (!egg.isDisabled()) {
            Researches.MOB_CAPTURING.addItems(egg);
            MobCapturer.getRegistry().getAdapters().put(type, egg);
        }
    }
}
