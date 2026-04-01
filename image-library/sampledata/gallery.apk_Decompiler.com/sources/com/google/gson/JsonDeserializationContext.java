package com.google.gson;

import java.lang.reflect.Type;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface JsonDeserializationContext {
    <T> T deserialize(JsonElement jsonElement, Type type);
}
