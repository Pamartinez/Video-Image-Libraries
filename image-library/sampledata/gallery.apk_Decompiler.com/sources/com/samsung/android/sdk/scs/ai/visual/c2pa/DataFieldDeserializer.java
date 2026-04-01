package com.samsung.android.sdk.scs.ai.visual.c2pa;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/DataFieldDeserializer;", "Lcom/google/gson/JsonDeserializer;", "", "<init>", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DataFieldDeserializer implements JsonDeserializer<Object> {
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        List list = null;
        if (jsonElement == null) {
            return null;
        }
        if (jsonElement.isJsonObject()) {
            jsonElement = jsonElement.getAsJsonObject().getAsJsonArray("actions");
        } else if (!jsonElement.isJsonArray()) {
            jsonElement = null;
        }
        if (jsonDeserializationContext != null) {
            list = (List) jsonDeserializationContext.deserialize(jsonElement, new DataFieldDeserializer$deserialize$actionsList$1().getType());
        }
        return new Data(list);
    }
}
