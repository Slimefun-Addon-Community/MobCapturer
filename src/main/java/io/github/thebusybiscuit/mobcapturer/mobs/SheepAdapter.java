package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Sheep;

import com.google.gson.JsonObject;

import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;

public class SheepAdapter extends AnimalsAdapter<Sheep> {
	
	public SheepAdapter() {
		super(Sheep.class);
	}
	
	@Override
	public List<String> getLore(JsonObject json) {
		List<String> lore = super.getLore(json);

		lore.add(ChatColor.GRAY + "Color: " + ChatColor.WHITE + StringUtils.format(json.get("woolColor").getAsString()));
		
		return lore;
	}

	@Override
	public void apply(Sheep entity, JsonObject json) {
		super.apply(entity, json);
		
		entity.setSheared(json.get("sheared").getAsBoolean());
		entity.setColor(DyeColor.valueOf(json.get("woolColor").getAsString()));
	}
	
	@Override
	public JsonObject saveData(Sheep entity) {
		JsonObject json = super.saveData(entity);
		
		json.addProperty("sheared", entity.isSheared());
		json.addProperty("woolColor", entity.getColor().name());
		
		return json;
	}

}
