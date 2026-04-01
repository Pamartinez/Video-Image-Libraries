package ge;

import He.F;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

/* renamed from: ge.l0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1040l0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f4531a = Logger.getLogger(C1040l0.class.getName());

    public static Object a(JsonReader jsonReader) {
        F.t(jsonReader.hasNext(), "unexpected end of JSON");
        boolean z = false;
        switch (C1037k0.f4529a[jsonReader.peek().ordinal()]) {
            case 1:
                jsonReader.beginArray();
                ArrayList arrayList = new ArrayList();
                while (jsonReader.hasNext()) {
                    arrayList.add(a(jsonReader));
                }
                if (jsonReader.peek() == JsonToken.END_ARRAY) {
                    z = true;
                }
                F.t(z, "Bad token: " + jsonReader.getPath());
                jsonReader.endArray();
                return Collections.unmodifiableList(arrayList);
            case 2:
                jsonReader.beginObject();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                while (jsonReader.hasNext()) {
                    linkedHashMap.put(jsonReader.nextName(), a(jsonReader));
                }
                if (jsonReader.peek() == JsonToken.END_OBJECT) {
                    z = true;
                }
                F.t(z, "Bad token: " + jsonReader.getPath());
                jsonReader.endObject();
                return Collections.unmodifiableMap(linkedHashMap);
            case 3:
                return jsonReader.nextString();
            case 4:
                return Double.valueOf(jsonReader.nextDouble());
            case 5:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 6:
                jsonReader.nextNull();
                return null;
            default:
                throw new IllegalStateException("Bad token: " + jsonReader.getPath());
        }
    }
}
