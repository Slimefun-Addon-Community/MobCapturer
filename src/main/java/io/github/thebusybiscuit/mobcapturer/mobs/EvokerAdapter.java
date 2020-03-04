package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Spellcaster.Spell;

import com.google.gson.JsonObject;

import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;

public class EvokerAdapter extends RaiderAdapter<Evoker> {
	
	public EvokerAdapter() {
		super(Evoker.class);
	}
	
	@Override
	public List<String> getLore(JsonObject json) {
		List<String> lore = super.getLore(json);
		
		lore.add(ChatColor.GRAY + "Spell: " + StringUtils.format(json.get("spell").getAsString()));
		
		return lore;
	}
	
	@Override
	public void apply(Evoker entity, JsonObject json) {
		super.apply(entity, json);
		
		entity.setSpell(Spell.valueOf(json.get("spell").getAsString()));
	}
	
	@Override
	public JsonObject save(Evoker entity) {
		JsonObject json = super.save(entity);
		
		json.addProperty("spell", entity.getSpell().name());
		
		return json;
	}
	
}
