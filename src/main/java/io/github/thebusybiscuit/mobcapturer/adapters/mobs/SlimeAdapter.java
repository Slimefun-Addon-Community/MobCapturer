package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Slime;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;

public class SlimeAdapter<T extends Slime> implements MobAdapter<T> {

    private final Class<T> entityClass;

    public SlimeAdapter(@Nonnull Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "Size: " + ChatColor.WHITE + json.get("size").getAsInt());

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(T entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setSize(json.get("size").getAsInt());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull T entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("size", entity.getSize());

        return json;
    }

    @Nonnull
    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

}
