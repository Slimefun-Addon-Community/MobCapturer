package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

/**
 * All the {@link ItemGroup}s in MobCapturer.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public final class ItemGroups {
    public static final NestedItemGroup MAIN = new NestedItemGroup(
        new NamespacedKey(MobCapturer.getInstance(), "mob_capturer"),
        new CustomItemStack(
            SlimefunUtils.getCustomHead("d429ff1d2015cb11398471bb2f895f7b4c3ccec201e4ad7a86ff24b744878c"),
            "&dMob Capturer"
        )
    );
    public static final SubItemGroup TOOLS = new SubItemGroup(
        new NamespacedKey(MobCapturer.getInstance(), "tools"),
        MAIN,
        new CustomItemStack(
            Material.BLAZE_ROD,
            "&dTools"
        )
    );
    public static final SubItemGroup MOB_EGGS = new SubItemGroup(
        new NamespacedKey(MobCapturer.getInstance(), "mob_eggs"),
        MAIN,
        new CustomItemStack(
            Material.CHICKEN_SPAWN_EGG,
            "&aMob Eggs"
        )
    );

    private ItemGroups() {}
}
