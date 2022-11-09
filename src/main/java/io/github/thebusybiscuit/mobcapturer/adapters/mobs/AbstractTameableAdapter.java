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
import org.bukkit.entity.Animals;
import org.bukkit.entity.Tameable;

class AbstractTameableAdapter<T extends Animals & Tameable> extends AnimalsAdapter<T> {

    public AbstractTameableAdapter(@Nonnull Class<T> entityClass) {
        super(entityClass);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        JsonElement element = json.get("ownerName");

        if (!element.isJsonNull()) {
            lore.add(ChatColor.GRAY + "Owner: " + ChatColor.WHITE + element.getAsString());
        }

        return lore;
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("ownerUUID", entity.getOwner() == null ? null : entity.getOwner().getUniqueId().toString());
        json.addProperty("ownerName", entity.getOwner() == null ? null : entity.getOwner().getName());

        return json;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        JsonElement element = json.get("ownerUUID");

        if (!element.isJsonNull()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(element.getAsString()));
            entity.setOwner(player);
        }
    }
}
