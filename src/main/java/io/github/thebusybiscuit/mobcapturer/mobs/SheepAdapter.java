package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.DyeColor;
import org.bukkit.entity.Sheep;

import com.google.gson.JsonObject;

public class SheepAdapter extends AnimalsAdapter<Sheep> {
	
	public SheepAdapter() {
		super(Sheep.class);
	}

	@Override
	public void apply(Sheep entity, JsonObject json) {
		super.apply(entity, json);
		
		entity.setSheared(json.get("sheared").getAsBoolean());
		entity.setColor(DyeColor.valueOf(json.get("woolColor").getAsString()));
	}
	
	@Override
	public JsonObject save(Sheep entity) {
		JsonObject json = super.save(entity);
		
		json.addProperty("sheared", entity.isSheared());
		json.addProperty("woolColor", entity.getColor().name());
		
		return json;
	}

}
