package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.Raider;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class RaiderAdapter<T extends Raider> implements MobAdapter<T> {
	
	private final Class<T> entityClass;
	
	public RaiderAdapter(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public void apply(T entity, JsonObject json) {
		MobAdapter.super.apply(entity, json);
		
		entity.setCanJoinRaid(json.get("canJoinRaid").getAsBoolean());
	}
	
	@Override
	public JsonObject save(T entity) {
		JsonObject json = MobAdapter.super.save(entity);
		
		json.addProperty("canJoinRaid", entity.isCanJoinRaid());
		
		return json;
	}

	@Override
	public Class<T> getEntityClass() {
		return entityClass;
	}
	
}
