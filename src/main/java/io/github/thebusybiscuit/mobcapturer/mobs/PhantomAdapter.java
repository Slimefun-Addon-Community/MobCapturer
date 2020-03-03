package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Phantom;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class PhantomAdapter implements MobAdapter<Phantom> {
	
	@Override
	public void apply(Phantom entity, JsonObject json) {
		MobAdapter.super.apply(entity, json);
		
		entity.setSize(json.get("size").getAsInt());
	}
	
	@Override
	public JsonObject save(Phantom entity) {
		JsonObject json = MobAdapter.super.save(entity);
		
		json.addProperty("size", entity.getSize());
		
		return json;
	}

}
