package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Wolf;

import com.google.gson.JsonObject;

import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;

public class WolfAdapter extends AbstractTameableAdapter<Wolf> {
	
	public WolfAdapter() {
		super(Wolf.class);
	}
	
	@Override
	public List<String> getLore(JsonObject json) {
		List<String> lore = super.getLore(json);
		
		if (!json.get("ownerUUID").isJsonNull()) {
			lore.add(ChatColor.GRAY + "Collar Color: " + ChatColor.WHITE + StringUtils.format(json.get("collarColor").getAsString()));
			lore.add(ChatColor.GRAY + "Sitting: " + ChatColor.WHITE + json.get("sitting").getAsBoolean());
		}
		else {
			lore.add(ChatColor.GRAY + "Angry: " + ChatColor.WHITE + json.get("angry").getAsBoolean());
		}
		
		return lore;
	}

	@Override
	public void apply(Wolf entity, JsonObject json) {
		super.apply(entity, json);
		
		entity.setAngry(json.get("angry").getAsBoolean());
		entity.setSitting(json.get("sitting").getAsBoolean());
		entity.setCollarColor(DyeColor.valueOf(json.get("collarColor").getAsString()));
	}
	
	@Override
	public JsonObject saveData(Wolf entity) {
		JsonObject json = super.saveData(entity);
		
		json.addProperty("angry", entity.isAngry());
		json.addProperty("sitting", entity.isSitting());
		json.addProperty("collarColor", entity.getCollarColor().name());
		
		return json;
	}

}
