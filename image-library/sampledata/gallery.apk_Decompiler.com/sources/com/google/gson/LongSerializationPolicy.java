package com.google.gson;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum LongSerializationPolicy {
    DEFAULT {
        public JsonElement serialize(Long l) {
            if (l == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive((Number) l);
        }
    },
    STRING {
        public JsonElement serialize(Long l) {
            if (l == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive(l.toString());
        }
    };

    public abstract JsonElement serialize(Long l);
}
