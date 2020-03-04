package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Pig;

import com.google.gson.JsonObject;

public class PigAdapter extends AnimalsAdapter<Pig> {
	
	public PigAdapter() {
		super(Pig.class);
	}
	
	@Override
	public List<String> getLore(JsonObject json) {
		List<String> lore = super.getLore(json);
		
		if (json.get("saddle").getAsBoolean()) {
			lore.add(ChatColor.GRAY + "+ Saddle");
		}
		
		return lore;
	}

	@Override
	public void apply(Pig entity, JsonObject json) {
		super.apply(entity, json);
		
		entity.setSaddle(json.get("saddle").getAsBoolean());
	}
	
	@Override
	public JsonObject save(Pig entity) {
		JsonObject json = super.save(entity);
		
		json.addProperty("saddle", entity.hasSaddle());
		
		return json;
	}

}
