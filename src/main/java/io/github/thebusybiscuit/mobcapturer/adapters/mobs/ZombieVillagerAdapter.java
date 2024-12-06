package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ZombieVillager;

import io.github.thebusybiscuit.mobcapturer.utils.compatibility.VillagerProfessionX;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class ZombieVillagerAdapter extends ZombieAdapter<ZombieVillager> {

    public ZombieVillagerAdapter() {
        super(ZombieVillager.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Profession: " + ChatColor.WHITE + ChatUtils.humanize(json.get("profession").getAsString()));

        return lore;
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull ZombieVillager entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("profession", VillagerProfessionX.getFromZombieVillager(entity));
        json.addProperty("conversionPlayer", entity.getConversionPlayer() == null ? null : entity.getConversionPlayer().getUniqueId().toString());

        return json;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(ZombieVillager entity, JsonObject json) {
        super.apply(entity, json);

        var profession = json.get("profession").getAsString();

        if (!profession.equals("Unknown")) {
            VillagerProfessionX.setToZombieVillager(entity, profession);
        }

        JsonElement player = json.get("conversionPlayer");

        if (!player.isJsonNull()) {
            entity.setConversionPlayer(Bukkit.getOfflinePlayer(UUID.fromString(player.getAsString())));
        }
    }

}
