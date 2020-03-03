package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Pig;

import com.google.gson.JsonObject;

public class PigAdapter extends AnimalsAdapter<Pig> {
	
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
