package com.google.gson;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = new JsonNull();

    public boolean equals(Object obj) {
        return obj instanceof JsonNull;
    }

    public int hashCode() {
        return JsonNull.class.hashCode();
    }

    public JsonNull deepCopy() {
        return INSTANCE;
    }
}
