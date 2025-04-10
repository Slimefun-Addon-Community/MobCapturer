package io.github.thebusybiscuit.mobcapturer.utils.compatibility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.Nonnull;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.bukkit.Registry;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.mobcapturer.utils.ReflectionUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class AttributeX {

    private static final List<Object> allAttributes = new ArrayList<>();

    static {
        try {
            final var registry = ReflectionUtils.valueOf(Registry.class, "ATTRIBUTE");
            final var registryIterator = ReflectionUtils.invoke(registry, "iterator");
            while ((boolean) ReflectionUtils.invoke(registryIterator, "hasNext")) {
                allAttributes.add(ReflectionUtils.invoke(registryIterator, "next"));
            }
        } catch (Exception ex) {
            MobCapturer.getInstance().getLogger().log(Level.SEVERE, "Failed to load attributes", ex);
        }
    }

    @Nonnull
    public static JsonObject serializeAttributesFromEntity(@Nonnull LivingEntity entity) {
        JsonObject attributes = new JsonObject();

        for (var attribute : allAttributes) {
            AttributeInstance instance = (AttributeInstance) ReflectionUtils.invoke(entity, "getAttribute", attribute);
            if (instance != null) {
                JsonObject attributeObj = new JsonObject();
                attributeObj.addProperty("base", instance.getBaseValue());

                JsonArray modifiers = new JsonArray();

                for (AttributeModifier modifier : instance.getModifiers()) {
                    JsonObject mod = new JsonObject();
                    Map<String, Object> serializedMod = modifier.serialize();

                    for (var entry : serializedMod.entrySet()) {
                        mod.addProperty(entry.getKey(), entry.getValue().toString());
                    }

                    modifiers.add(mod);
                }

                attributeObj.add("modifiers", modifiers);

                attributes.add(attribute.toString(), attributeObj);
            }
        }

        return attributes;
    }
}
