package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Warden;
import org.bukkit.entity.Wither;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.NBTAdapter;

/**
 * This is an adapter for the {@link Warden}.
 * It saves the warden's memories (including its anger and suspects).
 */
public class WitherAdapter implements MobAdapter<Wither> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        if (json.get("invulnTicks") != null && json.get("invulnTicks").getAsInt() > 0) {
            lore.add(ChatColor.GRAY + "Invulnerability: " + ChatColor.WHITE + Math.floor(json.get("invulnTicks").getAsInt() / 20) + " seconds");
        }

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Wither entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        if (json.get("invulnTicks") != null) {
            entity.setInvulnerabilityTicks(json.get("invulnTicks").getAsInt());
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Wither entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        // Memories are handled by the NBTAdapter

        json.addProperty("invulnTicks", entity.getInvulnerabilityTicks());

        return json;
    }

    @Nonnull
    @Override
    public Class<Wither> getEntityClass() {
        return Wither.class;
    }

}
