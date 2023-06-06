package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;

import io.github.thebusybiscuit.mobcapturer.adapters.NBTAdapter;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

/**
 * This is an adapter for the {@link ZombieVillager}.
 * It stores data about whether it is being converted and its progress.
 * It also stores the original Villager's {@link Profession}, {@link Villager.Type}, Gossip, and Trades.
 */
public class ZombieVillagerAdapter extends ZombieAdapter<ZombieVillager> implements NBTAdapter<ZombieVillager> {

    public ZombieVillagerAdapter() {
        super(ZombieVillager.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        // IsBaby is displayed by the ZombieAdapter
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Profession: " + ChatColor.WHITE + ChatUtils.humanize(json.get("profession").getAsString()));
        lore.add(ChatColor.GRAY + "Biome: " + ChatColor.WHITE + ChatUtils.humanize(json.get("biome").getAsString()));

        if (json.has("conversionPlayer")) {
            lore.add(ChatColor.GRAY + "Is Converting: " + ChatColor.WHITE + "Yes");
            OfflinePlayer playerEntity = Bukkit.getOfflinePlayer(UUID.fromString(json.get("conversionPlayer").getAsString()));
            lore.add(ChatColor.GRAY + "Cured By Player: " + ChatColor.WHITE + playerEntity.getName());
        } else {
            lore.add(ChatColor.GRAY + "Is Converting: " + ChatColor.WHITE + "No");
        }

        return lore;
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull ZombieVillager entity) {
        // Age and IsBaby are saved by the ZombieAdapter
        JsonObject json = super.saveData(entity);

        json.addProperty("profession", entity.getVillagerProfession().name());
        json.addProperty("biome", entity.getVillagerType().name());

        // Trades and gossip are saved by the NBTAdapter

        if (entity.isConverting()) {
            json.addProperty("conversionPlayer", entity.getConversionPlayer() == null ? null : entity.getConversionPlayer().getUniqueId().toString());
            json.addProperty("conversionTime", entity.getConversionTime());
        }

        return json;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(ZombieVillager entity, JsonObject json) {
        super.apply(entity, json);

        Profession profession = Profession.valueOf(json.get("profession").getAsString());
        entity.setVillagerProfession(profession);

        Villager.Type biome = Villager.Type.valueOf(json.get("biome").getAsString());
        entity.setVillagerType(biome);
        
        // Trades and gossip are applied by the NBTAdapter

        JsonElement playerJson = json.get("conversionPlayer");
        if (playerJson != null && !playerJson.isJsonNull()) {
            OfflinePlayer playerEntity = Bukkit.getOfflinePlayer(UUID.fromString(playerJson.getAsString()));
            entity.setConversionPlayer(playerEntity);
        }

        JsonElement conversionTimeJson = json.get("conversionTime");
        if (conversionTimeJson != null && !conversionTimeJson.isJsonNull()) {
            int conversionTime = conversionTimeJson.getAsInt();
            entity.setConversionTime(conversionTime);
        }
    }

}
