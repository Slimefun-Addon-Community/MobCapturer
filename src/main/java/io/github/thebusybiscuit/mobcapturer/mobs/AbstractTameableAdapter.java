package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Tameable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

class AbstractTameableAdapter<T extends Animals & Tameable> extends AnimalsAdapter<T> {
	
	public AbstractTameableAdapter(Class<T> entityClass) {
		super(entityClass);
	}
	
	@Override
	public List<String> getLore(JsonObject json) {
		List<String> lore = super.getLore(json);

		JsonElement element = json.get("ownerName");
		
		if (!element.isJsonNull()) {
			lore.add(ChatColor.GRAY + "Owner: " + ChatColor.WHITE + element.getAsString());
		}
		
		return lore;
	}
	
	@Override
	public JsonObject saveData(T entity) {
		JsonObject json = super.saveData(entity);

		json.addProperty("ownerUUID", entity.getOwner() == null ? null: entity.getOwner().getUniqueId().toString());
		json.addProperty("ownerName", entity.getOwner() == null ? null: entity.getOwner().getName());
		
		return json;
	}
	
	@Override
	public void apply(T entity, JsonObject json) {
		super.apply(entity, json);
		
		JsonElement element = json.get("ownerUUID");
		
		if (!element.isJsonNull()) {
			OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(element.getAsString()));
			entity.setOwner(player);
		}
	}
}
