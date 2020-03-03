package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Slime;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class SlimeAdapter<T extends Slime> implements MobAdapter<T> {
	
	@Override
	public void apply(T entity, JsonObject json) {
		MobAdapter.super.apply(entity, json);
		
		entity.setSize(json.get("size").getAsInt());
	}
	
	@Override
	public JsonObject save(T entity) {
		JsonObject json = MobAdapter.super.save(entity);
		
		json.addProperty("size", entity.getSize());
		
		return json;
	}

}
