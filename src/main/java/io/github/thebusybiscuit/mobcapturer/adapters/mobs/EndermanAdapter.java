package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Enderman;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import java.util.List;

public class EndermanAdapter implements MobAdapter<Enderman> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "Carrying block: " + ChatColor.WHITE + json.has("carriedBlock"));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Enderman entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        if (json.has("carriedBlock")) {
            BlockData blockData = Bukkit.getServer().createBlockData(json.get("carriedBlock").getAsString());
            entity.setCarriedBlock(blockData);
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Enderman entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        BlockData blockData = entity.getCarriedBlock();
        if (blockData != null) {
            json.addProperty("carriedBlock", blockData.getAsString());
        }

        return json;
    }

    @Nonnull
    @Override
    public Class<Enderman> getEntityClass() {
        return Enderman.class;
    }

}
