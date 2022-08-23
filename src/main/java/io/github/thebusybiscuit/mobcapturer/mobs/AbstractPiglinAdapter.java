package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.PiglinAbstract;

class AbstractPiglinAdapter<T extends PiglinAbstract> extends AbstractHumanoidAdapter<T> {

    public AbstractPiglinAdapter(@Nonnull Class<T> entityClass) {
        super(entityClass);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "Baby: " + ChatColor.WHITE + json.get("baby").getAsBoolean());

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAge(json.get("age").getAsInt());
        entity.setImmuneToZombification(json.get("immuneToZombification").getAsBoolean());
        entity.setConversionTime(json.get("conversionTime").getAsInt());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull T entity) {
        JsonObject json = super.saveData(entity);

        int conversionTime = -1;
        if (entity.isConverting()) {
            conversionTime = entity.getConversionTime();
        }

        json.addProperty("age", entity.getAge());
        json.addProperty("baby", !entity.isAdult());
        json.addProperty("immuneToZombification", entity.isImmuneToZombification());
        json.addProperty("conversionTime", conversionTime);

        return json;
    }
}
