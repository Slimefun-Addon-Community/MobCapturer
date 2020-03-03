package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.DyeColor;
import org.bukkit.entity.Shulker;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class ShulkerAdapter implements MobAdapter<Shulker> {
	
	@Override
	public void apply(Shulker entity, JsonObject json) {
		MobAdapter.super.apply(entity, json);
		
		entity.setColor(DyeColor.valueOf(json.get("color").getAsString()));
	}
	
	@Override
	public JsonObject save(Shulker entity) {
		JsonObject json = MobAdapter.super.save(entity);
		
		json.addProperty("color", entity.getColor().name());
		
		return json;
	}

	@Override
	public Class<Shulker> getEntityClass() {
		return Shulker.class;
	}

}
