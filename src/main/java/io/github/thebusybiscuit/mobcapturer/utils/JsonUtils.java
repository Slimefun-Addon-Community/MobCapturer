package io.github.thebusybiscuit.mobcapturer.utils;

import java.lang.reflect.Type;
import java.util.Map;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class JsonUtils {

    private static final Gson GSON = new Gson();

    @Nonnull
    public static Map<String, Object> toMap(@Nonnull JsonObject obj) {
        Preconditions.checkArgument(obj != null, "JsonObject cannot be null");

        // @formatter:off
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        // @formatter:on
        return GSON.fromJson(obj, type);
    }
}
