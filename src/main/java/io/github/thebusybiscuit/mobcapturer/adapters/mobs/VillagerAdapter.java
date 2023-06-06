package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.NBTAdapter;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

/**
 * This is an adapter for the {@link VillagerAdapter}.
 * It stores data about its profession, biome, and memories (including trades and gossip).
 */
public class VillagerAdapter implements MobAdapter<Villager>, NBTAdapter<Villager> {
    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        // IsBaby is displayed by the ZombieAdapter
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "Profession: " + ChatColor.WHITE + ChatUtils.humanize(json.get("profession").getAsString()));
        lore.add(ChatColor.GRAY + "Biome: " + ChatColor.WHITE + ChatUtils.humanize(json.get("biome").getAsString()));

        // Memories are displayed by the NBTAdapter

        return lore;
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Villager entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("profession", entity.getProfession().name());
        json.addProperty("biome", entity.getVillagerType().name());

        // Trades and gossip are saved by the NBTAdapter

        return json;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Villager entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        Profession profession = Profession.valueOf(json.get("profession").getAsString());
        entity.setProfession(profession);

        Villager.Type biome = Villager.Type.valueOf(json.get("biome").getAsString());
        entity.setVillagerType(biome);

        // Trades and gossip are applied by the NBTAdapter
    }

    @Nonnull
    @Override
    public Class<Villager> getEntityClass() {
        return Villager.class;
    }
}
