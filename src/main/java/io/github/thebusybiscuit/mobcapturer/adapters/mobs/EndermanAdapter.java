package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;
import net.md_5.bungee.api.chat.TranslatableComponent;

/**
 * This is an adapter for the {@link Endermite}.
 */
public class EndermanAdapter implements MobAdapter<Enderman> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        if (json.get("carriedBlock") != null) {
            BlockData blockData = Bukkit.createBlockData(json.get("carriedBlock").getAsString());
            TranslatableComponent blockName = new TranslatableComponent(blockData.getMaterial().getTranslationKey());
            String blockString = blockName.toLegacyText();

            lore.add(ChatColor.GRAY + "Carried Block: " + ChatColor.WHITE + "" + blockString);
        }

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Enderman entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        if (json.get("carriedBlock") != null) {
            BlockData blockData = Bukkit.createBlockData(json.get("carriedBlock").getAsString());
            entity.setCarriedBlock(blockData);
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Enderman entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        if (entity.getCarriedBlock() != null) {
            json.addProperty("carriedBlock", entity.getCarriedBlock().getAsString());
        }

        return json;
    }

    @Nonnull
    @Override
    public Class<Enderman> getEntityClass() {
        return Enderman.class;
    }

}
