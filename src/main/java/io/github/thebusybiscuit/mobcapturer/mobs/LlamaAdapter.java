package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Llama.Color;

import com.google.gson.JsonObject;

import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;

public class LlamaAdapter extends ChestedHorseAdapter<Llama> {

    public LlamaAdapter() {
        super(Llama.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Color: " + ChatColor.RESET + StringUtils.format(json.get("color").getAsString()));

        return lore;
    }

    @Override
    public void apply(Llama entity, JsonObject json) {
        super.apply(entity, json);

        entity.setColor(Color.valueOf(json.get("color").getAsString()));
        entity.setStrength(json.get("spitStrength").getAsInt());
    }

    @Override
    public JsonObject saveData(Llama entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("color", entity.getColor().name());
        json.addProperty("spitStrength", entity.getStrength());

        return json;
    }

}
