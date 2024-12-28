package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.utils.compatibility.FrogVariantX;

import org.bukkit.ChatColor;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Frog.Variant;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class FrogAdapter extends AnimalsAdapter<Frog> {

    public FrogAdapter() {
        super(Frog.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Variant: " + ChatColor.WHITE + ChatUtils.humanize(json.get("variant").getAsString()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Frog entity, JsonObject json) {
        super.apply(entity, json);

        FrogVariantX.set(entity, json.get("variant").getAsString());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Frog entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("variant", FrogVariantX.get(entity));

        return json;
    }

}
