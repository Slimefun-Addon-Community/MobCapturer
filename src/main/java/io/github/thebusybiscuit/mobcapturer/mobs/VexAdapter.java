package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Vex;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class VexAdapter implements MobAdapter<Vex> {
	
	@Override
	public List<String> getLore(JsonObject json) {
		List<String> lore = MobAdapter.super.getLore(json);

		lore.add(ChatColor.GRAY + "Charging: " + ChatColor.RESET + json.get("charging").getAsBoolean());
		
		return lore;
	}
	
	@Override
	public void apply(Vex entity, JsonObject json) {
		MobAdapter.super.apply(entity, json);
		
		entity.setCharging(json.get("charging").getAsBoolean());
	}
	
	@Override
	public JsonObject save(Vex entity) {
		JsonObject json = MobAdapter.super.save(entity);
		
		json.addProperty("charging", entity.isCharging());
		
		return json;
	}

	@Override
	public Class<Vex> getEntityClass() {
		return Vex.class;
	}

}
