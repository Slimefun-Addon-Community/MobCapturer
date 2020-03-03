package io.github.thebusybiscuit.mobcapturer.mobs;

import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.MushroomCow.Variant;

import com.google.gson.JsonObject;

public class MooshroomAdapter extends AnimalsAdapter<MushroomCow> {
	
	@Override
	public void apply(MushroomCow entity, JsonObject json) {
		super.apply(entity, json);
		
		entity.setVariant(Variant.valueOf(json.get("variant").getAsString()));
	}
	
	@Override
	public JsonObject save(MushroomCow entity) {
		JsonObject json = super.save(entity);
		
		json.addProperty("variant", entity.getVariant().name());
		
		return json;
	}

}
