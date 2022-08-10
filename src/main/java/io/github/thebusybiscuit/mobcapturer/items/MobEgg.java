package io.github.thebusybiscuit.mobcapturer.items;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.thebusybiscuit.mobcapturer.InventoryAdapter;
import io.github.thebusybiscuit.mobcapturer.MobAdapter;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;

public class MobEgg<T extends LivingEntity> extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {

    private final NamespacedKey dataKey;
    private final NamespacedKey inventoryKey;
    private final MobAdapter<T> adapter;

    @ParametersAreNonnullByDefault
    public MobEgg(ItemGroup itemGroup, SlimefunItemStack item, NamespacedKey dataKey, NamespacedKey inventoryKey, MobAdapter<T> adapter, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.dataKey = dataKey;
        this.inventoryKey = inventoryKey;
        this.adapter = adapter;
    }

    @SuppressWarnings("unchecked")
    public ItemStack getEggItem(@Nonnull T entity) {
        JsonObject json = adapter.saveData(entity);

        ItemStack item = getItem().clone();
        ItemMeta meta = item.getItemMeta();

        meta.setLore(adapter.getLore(json));
        meta.getPersistentDataContainer().set(dataKey, adapter, json);

        if (adapter instanceof InventoryAdapter) {
            FileConfiguration yaml = new YamlConfiguration();

            for (Map.Entry<String, ItemStack> entry : ((InventoryAdapter<T>) adapter).saveInventory(entity).entrySet()) {
                yaml.set(entry.getKey(), entry.getValue());
            }

            meta.getPersistentDataContainer().set(inventoryKey, PersistentDataType.STRING, yaml.saveToString());
        }

        item.setItemMeta(meta);

        return item;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nonnull
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            Optional<Block> block = e.getClickedBlock();

            if (block.isPresent()) {
                Block b = block.get();

                if (canPlaceMob(e.getPlayer(), b.getRelative(e.getClickedFace()).getLocation())) {
                    T entity = b.getWorld().spawn(b.getRelative(e.getClickedFace()).getLocation(), adapter.getEntityClass());

                    PersistentDataContainer container = e.getItem().getItemMeta().getPersistentDataContainer();
                    JsonObject json = container.get(dataKey, adapter);
                    ItemUtils.consumeItem(e.getItem(), false);

                    if (json != null) {
                        adapter.apply(entity, json);

                        if (adapter instanceof InventoryAdapter) {
                            Map<String, ItemStack> inventory = new HashMap<>();

                            try (Reader reader = new StringReader(container.get(inventoryKey, PersistentDataType.STRING))) {
                                FileConfiguration yaml = YamlConfiguration.loadConfiguration(reader);

                                for (String key : yaml.getKeys(true)) {
                                    Object obj = yaml.get(key);

                                    if (obj instanceof ItemStack) {
                                        inventory.put(key, (ItemStack) obj);
                                    }
                                }
                            } catch (IOException x) {
                                x.printStackTrace();
                            }

                            ((InventoryAdapter<T>) adapter).applyInventory(entity, inventory);
                        }
                    }
                }
            }
        };
    }

    @ParametersAreNonnullByDefault
    protected boolean canPlaceMob(Player p, Location l) {
        return Slimefun.getProtectionManager().hasPermission(p, l, Interaction.PLACE_BLOCK);
    }

}
