package io.github.thebusybiscuit.mobcapturer;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.gson.JsonObject;

class MobEgg<T extends LivingEntity> {
	
	private final MobAdapter<T> adapter;
	private final ItemStack egg;
	
	MobEgg(MobAdapter<T> adapter, ItemStack egg) {
		this.adapter = adapter;
		this.egg = egg;
	}

	public ItemStack getItem(NamespacedKey key, T entity) {
		JsonObject json = adapter.save(entity);
		
		ItemStack item = egg.clone();
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, adapter, json);
		item.setItemMeta(meta);
		
		return item;
	}

}
