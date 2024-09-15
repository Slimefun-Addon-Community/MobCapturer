package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;

import lombok.experimental.UtilityClass;

/**
 * All the {@link Research}es in MobCapturer.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
@UtilityClass
public final class Researches {

    public static final Research MOB_CAPTURING = new Research(new NamespacedKey(MobCapturer.getInstance(), "mob_capturing"), 32652, "Capturing Mobs", 28);
}
