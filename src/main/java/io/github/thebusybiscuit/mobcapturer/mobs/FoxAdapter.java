package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Fox.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;

public class FoxAdapter extends AnimalsAdapter<Fox> {

    public FoxAdapter() {
        super(Fox.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Variant: " + ChatColor.WHITE + StringUtils.format(json.get("foxType").getAsString()));
        if (json.get("crouching").getAsBoolean()){
          lore.add(ChatColor.GRAY + "Crouching: " + ChatColor.WHITE + json.get("crouching").getAsBoolean());
        } else if (json.get("sitting").getAsBoolean()) {
          lore.add(ChatColor.GRAY + "Sitting: " + ChatColor.WHITE + json.get("sitting").getAsBoolean());
        } else if (json.get("sleeping").getAsBoolean()) {
          lore.add(ChatColor.GRAY + "Sleeping: " + ChatColor.WHITE + json.get("sleeping").getAsBoolean());
        }


        JsonElement firstElement = json.get("firstTrustedPlayerName");
        JsonElement secondElement = json.get("secondTrustedPlayerName");
        if (!firstElement.isJsonNull()) {
            if (secondElement.isJsonNull()) {
                lore.add(ChatColor.GRAY + "Trusted Player: " + ChatColor.WHITE + firstElement.getAsString());
            } else {
              lore.add(ChatColor.GRAY + "Trusted Players: " + ChatColor.WHITE + firstElement.getAsString() + ", " + secondElement.getAsString());
            }
        }

        return lore;
    }

    @Override
    public void apply(Fox entity, JsonObject json) {
        super.apply(entity, json);

        entity.setFoxType(Type.valueOf(json.get("foxType").getAsString()));
        entity.setCrouching(json.get("crouching").getAsBoolean());
        entity.setSitting(json.get("sitting").getAsBoolean());
        entity.setSleeping(json.get("sleeping").getAsBoolean());


        JsonElement element = json.get("firstTrustedPlayerUUID");
        if (!element.isJsonNull()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(element.getAsString()));
            entity.setFirstTrustedPlayer(player);
        }
        element = json.get("secondTrustedPlayerUUID");
        if (!element.isJsonNull()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(element.getAsString()));
            entity.setSecondTrustedPlayer(player);
        }
    }

    @Override
    public JsonObject saveData(Fox entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("foxType", entity.getFoxType().name());
        json.addProperty("crouching", entity.isCrouching());
        json.addProperty("sitting", entity.isSitting());
        json.addProperty("sleeping", entity.isSleeping());
        json.addProperty("firstTrustedPlayerUUID", entity.getFirstTrustedPlayer() == null ? null : entity.getFirstTrustedPlayer().getUniqueId().toString());
        json.addProperty("firstTrustedPlayerName", entity.getFirstTrustedPlayer() == null ? null : entity.getFirstTrustedPlayer().getName());
        json.addProperty("secondTrustedPlayerUUID", entity.getSecondTrustedPlayer() == null ? null : entity.getSecondTrustedPlayer().getUniqueId().toString());
        json.addProperty("secondTrustedPlayerName", entity.getSecondTrustedPlayer() == null ? null : entity.getSecondTrustedPlayer().getName());

        return json;
    }

}
