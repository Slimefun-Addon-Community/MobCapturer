package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import lombok.experimental.UtilityClass;

/**
 * All the {@link ItemGroup}s in MobCapturer.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
@UtilityClass
public final class ItemGroups {

    public static final NestedItemGroup MAIN = new NestedItemGroup(
        new NamespacedKey(MobCapturer.getInstance(), "mob_capturer"),
        new SlimefunItemStack(
            "MOB_CAPTURER",
            "d429ff1d2015cb11398471bb2f895f7b4c3ccec201e4ad7a86ff24b744878c",
            "&dMob Capturer"
        ).item()
    );
    public static final SubItemGroup TOOLS = new SubItemGroup(
        new NamespacedKey(MobCapturer.getInstance(), "tools"),
        MAIN,
        CustomItemStack.create(
            ItemStacks.MOB_CANNON.item(),
            "&dTools"
        )
    );
    public static final SubItemGroup MOB_EGGS = new SubItemGroup(
        new NamespacedKey(MobCapturer.getInstance(), "mob_eggs"),
        MAIN,
        CustomItemStack.create(
            Material.CHICKEN_SPAWN_EGG,
            "&aMob Eggs"
        )
    );
}
