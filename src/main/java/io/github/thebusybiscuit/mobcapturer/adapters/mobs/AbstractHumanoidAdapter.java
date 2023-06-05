package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.bukkit.entity.Monster;

import io.github.thebusybiscuit.mobcapturer.adapters.EquipmentAdapter;
import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;

/**
 * This is a basic adapter for all humanoid {@link Monster}s.
 * It stores the entity's picked up equipment.
 */
class AbstractHumanoidAdapter<T extends Monster> implements MobAdapter<T>, EquipmentAdapter<T> {

    private final Class<T> entityClass;

    public AbstractHumanoidAdapter(@Nonnull Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(T entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        JsonElement element = json.get("canPickupItems");
        if (!element.isJsonNull()) {
            entity.setCanPickupItems(element.getAsBoolean());
        }

        // Equipment is handled by applyInventory()
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull T entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("canPickupItems", entity.getCanPickupItems());

        // Equipment is handled by saveInventory()

        return json;
    }

    @Nonnull
    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

}
