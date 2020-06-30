package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Llama.Color;

import com.google.gson.JsonObject;

import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;

public class LlamaAdapter<T extends Llama> extends ChestedHorseAdapter<T> {

    public LlamaAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Color: " + ChatColor.WHITE + StringUtils.format(json.get("color").getAsString()));

        return lore;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        entity.setColor(Color.valueOf(json.get("color").getAsString()));
        entity.setStrength(json.get("spitStrength").getAsInt());
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("color", entity.getColor().name());
        json.addProperty("spitStrength", entity.getStrength());

        return json;
    }

}
