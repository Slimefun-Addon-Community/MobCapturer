package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Sniffer;

public class SnifferAdapter extends AnimalsAdapter<Sniffer> {

    public SnifferAdapter() {
        super(Sniffer.class);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Sniffer entity, JsonObject json) {
        super.apply(entity, json);

        entity.setState(Sniffer.State.valueOf(json.get("state").getAsString()));

        JsonArray exploredLocations = json.get("exploredLocations").getAsJsonArray();
        for (JsonElement element : exploredLocations) {
            try {
                entity.addExploredLocation(deserializeLocation(element));
            } catch (IllegalArgumentException ignored) {
                // Ignore invalid locations
            }
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Sniffer entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("state", entity.getState().toString());

        JsonArray exploredLocations = new JsonArray();
        entity.getExploredLocations().forEach(location -> exploredLocations.add(serializeLocation(location)));
        json.add("exploredLocations", exploredLocations);

        return json;
    }

    @Nonnull
    private JsonObject serializeLocation(@Nonnull Location location) {
        final JsonObject obj = new JsonObject();
        obj.addProperty("world", location.getWorld().getName());
        obj.addProperty("x", location.getX());
        obj.addProperty("y", location.getY());
        obj.addProperty("z", location.getZ());
        obj.addProperty("yaw", location.getYaw());
        obj.addProperty("pitch", location.getPitch());
        return obj;
    }

    @Nonnull
    private Location deserializeLocation(@Nonnull JsonElement element) {
        if (!(element instanceof JsonObject obj)) {
            throw new IllegalArgumentException("Not a JSON Object");
        }
        final JsonObject json = obj.getAsJsonObject();
        final JsonElement worldObj = json.get("world");
        final JsonElement xObj = json.get("x");
        final JsonElement yObj = json.get("y");
        final JsonElement zObj = json.get("z");
        final JsonElement yawObj = json.get("yaw");
        final JsonElement pitchObj = json.get("pitch");

        if (worldObj instanceof JsonPrimitive worldName && worldName.isString()
            && xObj instanceof JsonPrimitive x && x.isNumber()
            && yObj instanceof JsonPrimitive y && y.isNumber()
            && zObj instanceof JsonPrimitive z && z.isNumber()
            && yawObj instanceof JsonPrimitive yaw && yaw.isNumber()
            && pitchObj instanceof JsonPrimitive pitch && pitch.isNumber()
        ) {
            World world = Bukkit.getWorld(worldName.getAsString());
            if (world == null) {
                throw new IllegalArgumentException("Unknown world: " + worldName.getAsString());
            }

            return new Location(world, x.getAsDouble(), y.getAsDouble(), z.getAsDouble(), yaw.getAsFloat(), pitch.getAsFloat());
        } else {
            throw new IllegalArgumentException("Malformed Location JSON");
        }
    }

}
