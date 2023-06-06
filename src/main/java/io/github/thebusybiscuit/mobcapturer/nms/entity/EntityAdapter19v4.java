package io.github.thebusybiscuit.mobcapturer.nms.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.entity.Entity;

import io.github.thebusybiscuit.mobcapturer.nms.ReflectionUtils2;
import io.github.thebusybiscuit.slimefun4.libraries.dough.reflection.ReflectionUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.versions.UnknownServerVersionException;

public class EntityAdapter19v4 implements EntityAdapter {

    /**
     * public EntityZombieVillager getHandle()
     */
    private final Method getHandle;

    /**
     * protected void readAdditionalSaveData(CompoundTag var1);
     */
    private final Method addAdditionalSaveData_method;
    private final String OBF_addAdditionalSaveData = "b";

    /**
     * protected void addAdditionalSaveData(CompoundTag var1);
     */
    private final Method readAdditionalSaveData_method;
    private final String OBF_readAdditionalSaveData = "a";

    /**
     * private final EntityType<?> type;
     */
    private final Field type_field;
    private final String OBF_type = "p";

    /**
     * public String getDescriptionId();
     */
    private final Method getDescriptionId;
    private final String OBF_getDescriptionId = "g";

    /**
     * public CompoundTag();
     */
    private final Constructor<?> newCompoundTag;

    /**
     * public String getAsString();
     */
    private final Method getAsString;
    private final String OBF_getAsString = "f_";

    /**
     * public static CompoundTag parseTag(String text);
     */
    private final Method parseTag;
    private final String OBF_parseTag = "a";

    EntityAdapter19v4() throws NoSuchMethodException, SecurityException, ClassNotFoundException, UnknownServerVersionException, NoSuchFieldException {
        super();

        // org.bukkit.craftbukkit.CraftEntity
        getHandle = ReflectionUtils.getOBCClass("entity.CraftEntity").getDeclaredMethod("getHandle");
        // net.minecraft.world.entity.Entity
        addAdditionalSaveData_method = ReflectionUtils2.getPrivateMethodOrAlternative(ReflectionUtils.getNetMinecraftClass("world.entity.Entity"), "addAdditionalSaveData", OBF_addAdditionalSaveData, ReflectionUtils.getNetMinecraftClass("nbt.NBTTagCompound"));
        addAdditionalSaveData_method.setAccessible(true);
        readAdditionalSaveData_method = ReflectionUtils2.getPrivateMethodOrAlternative(ReflectionUtils.getNetMinecraftClass("world.entity.Entity"), "readAdditionalSaveData", OBF_readAdditionalSaveData, ReflectionUtils.getNetMinecraftClass("nbt.NBTTagCompound"));
        readAdditionalSaveData_method.setAccessible(true);
        type_field = ReflectionUtils2.getFieldOrAlternative(ReflectionUtils.getNetMinecraftClass("world.entity.Entity"), "type", OBF_type);
        type_field.setAccessible(true);
        // net.minecraft.world.entity.EntityType -> world.entity.EntityTypes
        getDescriptionId = ReflectionUtils2.getMethodOrAlternative(ReflectionUtils.getNetMinecraftClass("world.entity.EntityTypes"), "getDescriptionId", OBF_getDescriptionId);
        // net.minecraft.nbt.CompoundTag -> nbt.NBTTagCompound
        newCompoundTag = ReflectionUtils.getConstructor(ReflectionUtils.getNetMinecraftClass("nbt.NBTTagCompound"));
        // net.minecraft.nbt.Tag -> nbt.NBTBase
        getAsString = ReflectionUtils.getMethodOrAlternative(ReflectionUtils.getNetMinecraftClass("nbt.NBTBase"), "getAsString", OBF_getAsString);
        // net.minecraft.nbt.TagParser -> nbt.MojangsonParser
        parseTag = ReflectionUtils2.getMethodOrAlternative(ReflectionUtils.getNetMinecraftClass("nbt.MojangsonParser"), "parseTag", OBF_parseTag, String.class);
    }

    @Override
    public String addAdditionalSaveData(Entity targetEntity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        Object nmsEntity = getHandle.invoke(targetEntity);
        Object compoundTag = newCompoundTag.newInstance();

        addAdditionalSaveData_method.invoke(nmsEntity, compoundTag);

        return (String) getAsString.invoke(compoundTag);
    }

    @Override
    public void readAdditionalSaveData(Entity targetEntity, String data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        Object nmsEntity = getHandle.invoke(targetEntity);
        Object compoundTag = parseTag.invoke(null, data);

        readAdditionalSaveData_method.invoke(nmsEntity, compoundTag);
    }
    
    @Override
    public String getTypeName(Entity targetEntity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object nmsEntity = getHandle.invoke(targetEntity);
        Object type = type_field.get(nmsEntity);
        
        return (String) getDescriptionId.invoke(type);
    }
}
