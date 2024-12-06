package io.github.thebusybiscuit.mobcapturer.utils.compatibility;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.annotation.Nonnull;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.ZombieVillager;

import lombok.experimental.UtilityClass;

// TODO: This needs to be changed since 1.22 the enum methods will be removed
@UtilityClass
public final class VillagerProfessionX {

    @Nonnull
    public static String getFromZombieVillager(@Nonnull ZombieVillager entity) {
        try {
            // get the profession of the zombie villager
            var getProfMethod = entity.getClass().getDeclaredMethod("getVillagerProfession");
            Object prof = getProfMethod.invoke(entity);

            var getKeyMethod = prof.getClass().getDeclaredMethod("getKey");
            var nsKey = (NamespacedKey) getKeyMethod.invoke(prof);
            return nsKey.getKey().toUpperCase(Locale.ROOT);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return "Unknown";
        }
    }

    public static void setToZombieVillager(@Nonnull ZombieVillager entity, @Nonnull String profession) {
        try {
            // get the profession of the zombie villager
            var getProfMethod = entity.getClass().getDeclaredMethod("getVillagerProfession");
            Object prof = getProfMethod.invoke(entity);

            var valueOfMethod = prof.getClass().getDeclaredMethod("valueOf", String.class);
            Object newProf = valueOfMethod.invoke(prof, profession);

            var setProfMethod = entity.getClass().getDeclaredMethod("setVillagerProfession", prof.getClass());
            setProfMethod.invoke(entity, newProf);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Ignore
        }
    }
}
