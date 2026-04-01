package com.samsung.android.sesl.outerGlow;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0017¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sesl/outerGlow/UniformValueDeserializer;", "Lcom/google/gson/JsonDeserializer;", "", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "graphic-solution_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UniformValueDeserializer implements JsonDeserializer<Object> {
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        Object obj;
        j.e(jsonElement, "json");
        j.e(type, "typeOfT");
        j.e(jsonDeserializationContext, "context");
        if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (asJsonPrimitive.isNumber()) {
                obj = Float.valueOf(asJsonPrimitive.getAsFloat());
            } else if (asJsonPrimitive.isString()) {
                obj = asJsonPrimitive.getAsString();
            } else if (asJsonPrimitive.isBoolean()) {
                obj = Boolean.valueOf(asJsonPrimitive.getAsBoolean());
            } else {
                throw new JsonParseException("Unsupported primitive uniform value type");
            }
            j.b(obj);
            return obj;
        } else if (jsonElement.isJsonArray()) {
            JsonArray asJsonArray = jsonElement.getAsJsonArray();
            int size = asJsonArray.size();
            if (size == 2) {
                return new float[]{asJsonArray.get(0).getAsFloat(), asJsonArray.get(1).getAsFloat()};
            } else if (size == 3) {
                return new float[]{asJsonArray.get(0).getAsFloat(), asJsonArray.get(1).getAsFloat(), asJsonArray.get(2).getAsFloat()};
            } else if (size != 4) {
                ArrayList arrayList = new ArrayList(asJsonArray.size());
                Iterator<JsonElement> it = asJsonArray.iterator();
                while (it.hasNext()) {
                    JsonElement next = it.next();
                    if (next.isJsonPrimitive()) {
                        JsonPrimitive asJsonPrimitive2 = next.getAsJsonPrimitive();
                        if (asJsonPrimitive2.isNumber()) {
                            arrayList.add(Float.valueOf(asJsonPrimitive2.getAsFloat()));
                        } else if (asJsonPrimitive2.isString()) {
                            arrayList.add(asJsonPrimitive2.getAsString());
                        } else if (asJsonPrimitive2.isBoolean()) {
                            arrayList.add(Boolean.valueOf(asJsonPrimitive2.getAsBoolean()));
                        } else {
                            throw new JsonParseException("Unsupported array element type");
                        }
                    } else {
                        throw new JsonParseException("Array elements must be primitives");
                    }
                }
                return arrayList;
            } else {
                return new float[]{asJsonArray.get(0).getAsFloat(), asJsonArray.get(1).getAsFloat(), asJsonArray.get(2).getAsFloat(), asJsonArray.get(3).getAsFloat()};
            }
        } else {
            throw new JsonParseException("Unsupported uniform value type");
        }
    }
}
