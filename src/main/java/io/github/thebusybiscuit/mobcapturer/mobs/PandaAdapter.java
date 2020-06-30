package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Panda.Gene;

import com.google.gson.JsonObject;

import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;

public class PandaAdapter extends AnimalsAdapter<Panda> {
	
	public PandaAdapter() {
		super(Panda.class);
	}
	
	@Override
	public List<String> getLore(JsonObject json) {
		List<String> lore = super.getLore(json);

		lore.add(ChatColor.GRAY + "Main Gene: " + ChatColor.WHITE + StringUtils.format(json.get("mainGene").getAsString()));
		lore.add(ChatColor.GRAY + "Hidden Gene: " + ChatColor.WHITE + StringUtils.format(json.get("hiddenGene").getAsString()));
		
		return lore;
	}

	@Override
	public void apply(Panda entity, JsonObject json) {
		super.apply(entity, json);
		
		entity.setMainGene(Gene.valueOf(json.get("mainGene").getAsString()));
		entity.setHiddenGene(Gene.valueOf(json.get("hiddenGene").getAsString()));
	}
	
	@Override
	public JsonObject saveData(Panda entity) {
		JsonObject json = super.saveData(entity);
		
		json.addProperty("mainGene", entity.getMainGene().name());
		json.addProperty("hiddenGene", entity.getHiddenGene().name());
		
		return json;
	}

}
