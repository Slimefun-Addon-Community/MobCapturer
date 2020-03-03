package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Rabbit.Type;

import com.google.gson.JsonObject;

public class RabbitAdapter extends AnimalsAdapter<Rabbit> {
	
	@Override
	public void apply(Rabbit entity, JsonObject json) {
		super.apply(entity, json);
		
		entity.setRabbitType(Type.valueOf(json.get("rabbitType").getAsString()));
	}
	
	@Override
	public JsonObject save(Rabbit entity) {
		JsonObject json = super.save(entity);
		
		json.addProperty("rabbitType", entity.getRabbitType().name());
		
		return json;
	}

}
