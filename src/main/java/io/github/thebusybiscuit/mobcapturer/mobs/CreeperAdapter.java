package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Creeper;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class CreeperAdapter implements MobAdapter<Creeper> {
	
	@Override
	public void apply(Creeper entity, JsonObject json) {
		MobAdapter.super.apply(entity, json);
		
		entity.setPowered(json.get("powered").getAsBoolean());
		entity.setExplosionRadius(json.get("radius").getAsInt());
		entity.setMaxFuseTicks(json.get("maxFuseTicks").getAsInt());
	}
	
	@Override
	public JsonObject save(Creeper entity) {
		JsonObject json = MobAdapter.super.save(entity);
		
		json.addProperty("powered", entity.isPowered());
		json.addProperty("radius", entity.getExplosionRadius());
		json.addProperty("maxFuseTicks", entity.getMaxFuseTicks());
		
		return json;
	}

}
