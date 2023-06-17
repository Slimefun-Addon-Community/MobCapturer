package io.github.thebusybiscuit.mobcapturer.setup;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Camel;
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
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.CamelAdapter;
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
import io.github.thebusybiscuit.mobcapturer.adapters.mobs.SnifferAdapter;
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
        //<editor-fold desc="Animals">
        // https://minecraft-heads.com/custom-heads/decoration/935-spawn-egg-cow
        registerMob(EntityType.COW, new AnimalsAdapter<>(Cow.class), "9419f15ff54dae5d040f9b9d8eb2a8989e676710922a0ca164da613ca61e9");
        // https://minecraft-heads.com/custom-heads/decoration/934-spawn-egg-chicken
        registerMob(EntityType.CHICKEN, new AnimalsAdapter<>(Chicken.class), "d429ff1d2015cb11398471bb2f895f7b4c3ccec201e4ad7a86ff24b744878c");
        // https://minecraft-heads.com/custom-heads/decoration/939-spawn-egg-pig
        registerMob(EntityType.PIG, new PigAdapter(), "527ad51dd773b72dca1c13e6f3547a83181aad91165282999bbdf13a3b3c9");
        // https://minecraft-heads.com/custom-heads/decoration/937-spawn-egg-mooshroom
        registerMob(EntityType.MUSHROOM_COW, new MooshroomAdapter(), "41b3b02e36ae9850df25aa09c2ca5a17b9c6616ce53e0b141ad360b6c67c");
        // https://minecraft-heads.com/custom-heads/decoration/940-spawn-egg-rabbit
        registerMob(EntityType.RABBIT, new RabbitAdapter(), "63e06ed8809243e317393f6162679b2c1fe6911eda2d30cb99cfc82d347cb");
        // https://minecraft-heads.com/custom-heads/decoration/936-spawn-egg-sheep
        registerMob(EntityType.SHEEP, new SheepAdapter(), "ff481f77347fe59c083665c9efbb49071d493ea2027454aee87735d63bf3b");
        // https://minecraft-heads.com/custom-heads/decoration/23584-spawn-egg-turtle
        registerMob(EntityType.TURTLE, new AnimalsAdapter<>(Turtle.class), "15a45e24cadc18f305291af45a22fc8b3607a675baa31ed583d3a56b15223c5c");
        // https://minecraft-heads.com/custom-heads/decoration/23579-spawn-egg-polar-bear
        registerMob(EntityType.POLAR_BEAR, new AnimalsAdapter<>(PolarBear.class), "291abcab7a20b28195c0f1786db28c7670c2979243de71703b04e9d93f59aa8d");
        // https://minecraft-heads.com/custom-heads/decoration/23589-spawn-egg-panda
        registerMob(EntityType.PANDA, new PandaAdapter(), "1ab24611bb37ce3971fdbf01ba3f11bd2e4c72f5d40b6d8d8d536d69e695cd0c");
        // https://minecraft-heads.com/custom-heads/decoration/48545-spawn-egg-fox
        registerMob(EntityType.FOX, new FoxAdapter(), "fcfd0230988ea3337126fc2c06f24cbc81ecf8ee8011abfe3993824bac33d260");
        // https://minecraft-heads.com/custom-heads/decoration/48546-spawn-egg-bee
        registerMob(EntityType.BEE, new BeeAdapter(), "a5ed2a6d8ba4dba6db2a475dc1ca69f49459d5f95b7095a5b2d8a6d2c944e74e");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
            // https://minecraft-heads.com/custom-heads/decoration/46398-spawn-egg-axolotl
            registerMob(EntityType.AXOLOTL, new AxolotlAdapter(), "62d90ad63dd826df02994abdcc6c2306163e1072d1b9e63ad4e7d7d1cf87cdf9");
            // https://minecraft-heads.com/custom-heads/decoration/46397-spawn-egg-goat
            registerMob(EntityType.GOAT, new GoatAdapter(), "33f7fe31285bd2ca74516b07852e079447f524fd9cc0b7d4db003b165d5b4b4");
        }
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            // https://minecraft-heads.com/custom-heads/decoration/56964-spawn-egg-frog
            registerMob(EntityType.FROG, new FrogAdapter(), "2d767bfee87d1d301084c74dadade50fc49263a465e4c1065549dbf8403f194c");
        }
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20)) {
            // https://minecraft-heads.com/custom-heads/decoration/62266-sniffer-egg
            registerMob(EntityType.SNIFFER, new SnifferAdapter(), "bae445d1392cb559a68b4f4401e1aa3ec5e7f9e89c0b62632bccf0ac3b41b4b");
        }
        //</editor-fold>

        //<editor-fold desc="Mobs">
        // https://minecraft-heads.com/custom-heads/decoration/961-spawn-egg-slime
        registerMob(EntityType.SLIME, new SlimeAdapter<>(Slime.class), "9330af17f8512ed3b49e78bca7ef2d83f2dc1e598a8cb542ecc3b6becee9f57");
        // https://minecraft-heads.com/custom-heads/decoration/962-spawn-egg-spider
        registerMob(EntityType.SPIDER, new StandardMobAdapter<>(Spider.class), "5d59aa78cb7e9b6ca6fee4121329059dd68afddc0c8b53a906b7953994e8a76");
        // https://minecraft-heads.com/custom-heads/decoration/946-spawn-egg-cave-spider
        registerMob(EntityType.CAVE_SPIDER, new StandardMobAdapter<>(CaveSpider.class), "16617131250e578333a441fdf4a5b8c62163640a9d06cd67db89031d03accf6");
        // https://minecraft-heads.com/custom-heads/decoration/947-spawn-egg-creeper
        registerMob(EntityType.CREEPER, new CreeperAdapter(), "87c63d9079b75f90979783cf07ca726f65e3024415ac622a7c906cd25082af");
        // https://minecraft-heads.com/custom-heads/decoration/23580-spawn-egg-phantom
        registerMob(EntityType.PHANTOM, new PhantomAdapter(), "9381dfeac8a050d330fde058bad5f5e6f228f28cd379761c1147e17c4ed605b");
        // https://minecraft-heads.com/custom-heads/decoration/959-spawn-egg-silverfish
        registerMob(EntityType.SILVERFISH, new StandardMobAdapter<>(Silverfish.class), "d06310a8952b265c6e6bed4348239ddea8e5482c8c68be6fff981ba8056bf2e");
        // https://minecraft-heads.com/custom-heads/decoration/933-spawn-egg-bat
        registerMob(EntityType.BAT, new StandardMobAdapter<>(Bat.class), "93c8aa3fde295fa9f9c27f734bdbab11d33a2e43e855accd7465352377413b");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            // https://minecraft-heads.com/custom-heads/decoration/56963-spawn-egg-allay
            registerMob(EntityType.ALLAY, new AllayAdapter(), "6c3f114efbd908284c7aadd81993769057361dd756bf5e7883b8e0b1cea446e7");
        }
        //</editor-fold>

        //<editor-fold desc="Water Mobs">
        // https://minecraft-heads.com/custom-heads/decoration/942-spawn-egg-squid
        registerMob(EntityType.SQUID, new StandardMobAdapter<>(Squid.class), "449088861fc1e14b605a5154d79fa7dd65e041a5c635d24744b3e152535");
        // https://minecraft-heads.com/custom-heads/decoration/957-spawn-egg-guardian
        registerMob(EntityType.GUARDIAN, new StandardMobAdapter<>(Guardian.class), "fe119aaa4999648a75b978efafa97edab1cdca1ff1d8301ba61cdc2db1606e22");
        // https://minecraft-heads.com/custom-heads/decoration/23710-spawn-egg-elder-guardian
        registerMob(EntityType.ELDER_GUARDIAN, new StandardMobAdapter<>(ElderGuardian.class), "9a839d8256c81cf1db8da8ff3f7b80cce2f865b80c9f66aea5340e697ea3e219");
        // https://minecraft-heads.com/custom-heads/decoration/23591-spawn-egg-dolphin
        registerMob(EntityType.DOLPHIN, new StandardMobAdapter<>(Dolphin.class), "2480cd9577e2173e1c9de5e41318bd859696215a0a7de9242f01c01b8e6c06bf");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
            // https://minecraft-heads.com/custom-heads/decoration/48544-spawn-egg-glow-squid
            registerMob(EntityType.GLOW_SQUID, new GlowSquidAdapter(), "e5d31559261b3e79024751fe07b711c8feef51d56c03635226955805bc42894e");
        }
        //</editor-fold>

        //<editor-fold desc="Tameables">
        // https://minecraft-heads.com/custom-heads/decoration/944-spawn-egg-wolf
        registerMob(EntityType.WOLF, new WolfAdapter(), "4399c973d6496d1d258492c28d4c95956ac3a253762bf15f7644af1f5728dd");
        // https://minecraft-heads.com/custom-heads/decoration/938-spawn-egg-ocelot
        registerMob(EntityType.OCELOT, new AnimalsAdapter<>(Ocelot.class), "c579a743b66bd0b4d559898ed1b9857a49f1127d9d237bed3dc97bceb9379a5");
        // https://minecraft-heads.com/custom-heads/decoration/23966-spawn-egg-cat
        registerMob(EntityType.CAT, new CatAdapter(), "f2154e385f9ccb187b53cf290fe9a24f98c7d583c368986c7806c312c3f29d79");
        // https://minecraft-heads.com/custom-heads/decoration/23588-spawn-egg-parrot
        registerMob(EntityType.PARROT, new ParrotAdapter(), "6eabd8fffc0371877e88ffc3cd0315200534dd2fcc6034588000736fb80033e5");
        //</editor-fold>

        //<editor-fold desc="Nether">
        // https://minecraft-heads.com/custom-heads/decoration/945-spawn-egg-blaze
        registerMob(EntityType.BLAZE, new StandardMobAdapter<>(Blaze.class), "533acae6e075a578ccfc7dc2d5a15dbccfa8f59c609f9703889ef54c742c56");
        // https://minecraft-heads.com/custom-heads/decoration/958-spawn-egg-magma-cube
        registerMob(EntityType.MAGMA_CUBE, new SlimeAdapter<>(MagmaCube.class), "1185657c38acdd8f95e1d2cd1115bb0f11139ad2b3ce442267e69706d916e");
        // https://minecraft-heads.com/custom-heads/decoration/955-spawn-egg-ghast
        registerMob(EntityType.GHAST, new StandardMobAdapter<>(Ghast.class), "c442c228f099fdfc1c6b46dfc80b252d81f7fb1739deb16ee7a597c17f7c9");
        // https://minecraft-heads.com/custom-heads/decoration/48541-spawn-egg-piglin
        registerMob(EntityType.PIGLIN, new PiglinAdapter(), "f2f8166103130f36def281bd47537fe6ab66a09dde018f2792bc078e4525678c");
        // https://minecraft-heads.com/custom-heads/decoration/48540-spawn-egg-piglin-brute
        registerMob(EntityType.PIGLIN_BRUTE, new PiglinBruteAdapter(), "4b54b01c300f73a62b56ada9acf9277e2579d1188f5c76742a52d192e56afd3a");
        // https://minecraft-heads.com/custom-heads/decoration/965-spawn-egg-zombie-pigman
        registerMob(EntityType.ZOMBIFIED_PIGLIN, new ZombifiedPiglinAdapter(), "5f7de9fafa8fcb77d5944e628b72042b9f7988de43e422983c78d3762d6d7");
        // https://minecraft-heads.com/custom-heads/decoration/48543-spawn-egg-hoglin
        registerMob(EntityType.HOGLIN, new HoglinAdapter(), "b35f54a0f643369a3a4b7d5582704745497001152768dab1328a70dbfee1cb1c");
        // https://minecraft-heads.com/custom-heads/decoration/48542-spawn-egg-zoglin
        registerMob(EntityType.ZOGLIN, new ZoglinAdapter(), "debc97935d49df69911c008145b9c2d8906a11b0aa38effd8d50fe7b9682a7a0");
        // https://minecraft-heads.com/custom-heads/decoration/48539-spawn-egg-strider
        registerMob(EntityType.STRIDER, new StriderAdapter(), "3820f04b3f177c0463a05697766f3bdaf6fa28f1ac163572e27fde7c78afe7be");
        //</editor-fold>

        //<editor-fold desc="Ender things">
        // https://minecraft-heads.com/custom-heads/decoration/23585-spawn-egg-shulker
        registerMob(EntityType.SHULKER, new ShulkerAdapter(), "d04252216231b3f744c9ff4ace7084ae9f4164f8b384c65410848a19617af4d");
        // https://minecraft-heads.com/custom-heads/decoration/954-spawn-egg-endermite
        registerMob(EntityType.ENDERMITE, new EndermiteAdapter(), "3beac501e97db1cc035287d068a8eb538e55ef802f5cca25683933a243136c");
        //</editor-fold>

        //<editor-fold desc="Golems">
        // unknown source
        registerMob(EntityType.SNOWMAN, new SnowmanAdapter(), "2e4385d58fe46dd96422f31d35bbd1568e5819bbdb7a196c9f113424582cf977");
        // unknown source
        registerMob(EntityType.IRON_GOLEM, new IronGolemAdapter(), "c442c228f099fdfc1c6b46dfc80b252d81f7fb1739deb16ee7a597c17f7c9");
        //</editor-fold>

        //<editor-fold desc="Illagers">
        // https://minecraft-heads.com/custom-heads/decoration/963-spawn-egg-witch
        registerMob(EntityType.WITCH, new RaiderAdapter<>(Witch.class), "afbdceef773d959b49ddd9615f4269c176e23154d45752667428dc4e3fd4d");
        // https://minecraft-heads.com/custom-heads/decoration/23578-spawn-egg-vindicator
        registerMob(EntityType.VINDICATOR, new RaiderAdapter<>(Vindicator.class), "8e8e3de7718a54553dd2fc5b2415a08b05f2339b772fe181b65c507fda4e34c1");
        // https://minecraft-heads.com/custom-heads/decoration/25038-spawn-egg-pillager
        registerMob(EntityType.PILLAGER, new RaiderAdapter<>(Pillager.class), "8fd4983e30b277f0b97b7d8c6f8a0358201be226a2c55e2a0d390c3942ec2df5");
        // https://minecraft-heads.com/custom-heads/decoration/23854-spawn-egg-ravager
        registerMob(EntityType.RAVAGER, new RaiderAdapter<>(Ravager.class), "79b625b80cfb0baf04eebbd2cb1ff9f1010b02f4df21b3baf86eb812ab7eba8b");
        // https://minecraft-heads.com/custom-heads/decoration/23581-spawn-egg-evoker
        registerMob(EntityType.EVOKER, new MagicIllagerAdapter<>(Evoker.class), "ff1eeb387d55b0886a69b6ec62a6e69706f32aba2547e10583060b976341f9be");
        // unknown source
        registerMob(EntityType.ILLUSIONER, new MagicIllagerAdapter<>(Illusioner.class), "ff1eeb387d55b0886a69b6ec62a6e69706f32aba2547e10583060b976341f9be");
        // https://minecraft-heads.com/custom-heads/decoration/23708-spawn-egg-vex
        registerMob(EntityType.VEX, new VexAdapter(), "dc7eb861fd9999bf87a300e3ddd03c57313ddfba221d1c7d4bd62cef31446ca1");
        //</editor-fold>

        //<editor-fold desc="Fish">
        // https://minecraft-heads.com/custom-heads/decoration/23711-spawn-egg-cod-fish
        registerMob(EntityType.COD, new StandardMobAdapter<>(Cod.class), "bd29b25579f9d3a67b612ae8ef96b31feca6c9e7e6c70ac81156d778cbe7db9d");
        // https://minecraft-heads.com/custom-heads/decoration/23586-spawn-egg-salmon
        registerMob(EntityType.SALMON, new StandardMobAdapter<>(Salmon.class), "5c46c568e8b5c55853a92869ea19c00b7720c328a2f16c5950b9e2e897fc27a1");
        // https://minecraft-heads.com/custom-heads/decoration/23587-spawn-egg-pufferfish
        registerMob(EntityType.PUFFERFISH, new PufferFishAdapter(), "5d5e7d191478efafe23a654de802760f42a0dd83dfc9817f87d460fcf32978df");
        // https://minecraft-heads.com/custom-heads/decoration/23712-spawn-egg-tropical-fish
        registerMob(EntityType.TROPICAL_FISH, new TropicalFishAdapter(), "2e4385d58fe46dd96422f31d35bbd1568e5819bbdb7a196c9f113424582cf977");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            // https://minecraft-heads.com/custom-heads/decoration/56962-spawn-egg-tadpole
            registerMob(EntityType.TADPOLE, new TadpoleAdapter(), "d1f04d08180a7a5cc63055587a2ecde5ac86f9f48f0e3b0ee3a986638877aeef"); // not egg texture, bucket of tadpole
        }
        //</editor-fold>

        //<editor-fold desc="Horses">
        // https://minecraft-heads.com/custom-heads/decoration/936-spawn-egg-horse
        registerMob(EntityType.HORSE, new HorseAdapter(), "5c6d5abbf68ccb2386bf16af25ac38d8b77bb0e043152461bd97f3f630dbb8bc");
        // https://minecraft-heads.com/custom-heads/decoration/23905-spawn-egg-donkey
        registerMob(EntityType.DONKEY, new ChestedHorseAdapter<>(Donkey.class), "db522f6d77c0696c9d1f2ad49bfa3cb8205a5e623af1c420bd740dc471914e97");
        // https://minecraft-heads.com/custom-heads/decoration/23967-spawn-egg-mule
        registerMob(EntityType.MULE, new ChestedHorseAdapter<>(Mule.class), "e4ad78f7ada7c6376449ef949c9c87fdece882b5a2f14cfbf8eac6fea657f4c7");
        // https://minecraft-heads.com/custom-heads/decoration/24661-spawn-egg-zombie-horse
        registerMob(EntityType.ZOMBIE_HORSE, new UndeadHorseAdapter<>(ZombieHorse.class), "ec5b6f8ef1d75f73a5290c9367d2b9b823bc963de2a366fd6550bcace2751205");
        // https://minecraft-heads.com/custom-heads/decoration/24268-spawn-egg-skeleton-horse
        registerMob(EntityType.SKELETON_HORSE, new UndeadHorseAdapter<>(SkeletonHorse.class), "9dc084b7874268973006c897a03d8906cc9b3df8c39bce93d87ec0df507bbe0d");
        // https://minecraft-heads.com/custom-heads/decoration/23709-spawn-egg-llama
        registerMob(EntityType.LLAMA, new LlamaAdapter<>(Llama.class), "5cbc6bd92728d79cfa6d8f23cbae9d912f495920b9e95ef691a1967fef8a4453");
        // https://minecraft-heads.com/custom-heads/decoration/48537-spawn-egg-wandering-trader-llama
        registerMob(EntityType.TRADER_LLAMA, new LlamaAdapter<>(TraderLlama.class), "73ca3bfb6602a96a57369cbc85eba8bb53df796d0df3dbc3798fa3d8e9e30275");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20)) {
            // https://minecraft-heads.com/custom-heads/animals/58939-camel
            registerMob(EntityType.CAMEL, new CamelAdapter<>(Camel.class), "74b8a333dfa92e7e5a95ad4ae2d84b1bafa33dc28c054925277f60e79dafc8c4"); // no egg texture
        }
        //</editor-fold>

        //<editor-fold desc="Skeletons">
        // https://minecraft-heads.com/custom-heads/decoration/960-spawn-egg-skeleton
        registerMob(EntityType.SKELETON, new SkeletonAdapter<>(Skeleton.class), "377055cadacbb0f8f35c1d18acc2ed86e0bcc6d73dda71e4c59f7ea28b7b27b6");
        // https://minecraft-heads.com/custom-heads/decoration/23707-spawn-egg-wither-skeleton
        registerMob(EntityType.WITHER_SKELETON, new SkeletonAdapter<>(WitherSkeleton.class), "337223d01906ab63af1a15988343b8637e85930b905c35125b545b398c59e1c5");
        // https://minecraft-heads.com/custom-heads/decoration/23705-spawn-egg-stray
        registerMob(EntityType.STRAY, new SkeletonAdapter<>(Stray.class), "5b45aae241779f0617ffaff468f3f2cf666d2f8a803002f9ae1ba0f14ed79fdd");
        //</editor-fold>

        //<editor-fold desc="Zombies">
        // https://minecraft-heads.com/custom-heads/decoration/964-spawn-egg-zombie
        registerMob(EntityType.ZOMBIE, new ZombieAdapter<>(Zombie.class), "77f844bfea25429d45e1fcf96ef6654dfaaa6fc902dc1b6b68c0abc1343447");
        // https://minecraft-heads.com/custom-heads/decoration/23590-spawn-egg-drowned
        registerMob(EntityType.DROWNED, new ZombieAdapter<>(Drowned.class), "13a29552b3abaf388145be8a9067e5ea4d1af945e7bf5e2ca852cec87fd1b17b");
        // https://minecraft-heads.com/custom-heads/decoration/23706-spawn-egg-husk
        registerMob(EntityType.HUSK, new ZombieAdapter<>(Husk.class), "40fd8d86e7057aae33f5d79dcb03685c88c8137cfcb2349bc874b9f87e934276");
        // https://minecraft-heads.com/custom-heads/decoration/23583-spawn-egg-zombie-villager
        registerMob(EntityType.ZOMBIE_VILLAGER, new ZombieVillagerAdapter(), "364b4c94dda909ecc778db93572b847a5df51c4a49894d6a9df59b8df97842c5");
        //</editor-fold>
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
