package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Phantom;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class PhantomAdapter implements MobAdapter<Phantom> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "Size: " + ChatColor.WHITE + (json.get("size").getAsInt() + 1));

        return lore;
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Phantom entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setSize(json.get("size").getAsInt());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Phantom entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("size", entity.getSize());

        return json;
    }

    @Nonnull
    @Override
    public Class<Phantom> getEntityClass() {
        return Phantom.class;
    }

}
