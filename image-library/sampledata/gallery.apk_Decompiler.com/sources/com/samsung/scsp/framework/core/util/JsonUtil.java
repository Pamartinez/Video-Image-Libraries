package com.samsung.scsp.framework.core.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.samsung.scsp.framework.core.ScspException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JsonUtil {
    public static void addIfNotEmpty(JsonObject jsonObject, String str, String str2) {
        if (!StringUtil.isEmpty(str2)) {
            jsonObject.addProperty(str, str2);
        }
    }

    public static JsonObject toJson(String str) {
        try {
            return JsonParser.parseString(str).getAsJsonObject();
        } catch (JsonIOException | JsonSyntaxException | IllegalStateException e) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "An error occurred in the process of Json parsing.", e);
        }
    }

    public static JsonArray toJsonArray(String str) {
        try {
            return new JsonParser().parse(str).getAsJsonArray();
        } catch (JsonIOException | JsonSyntaxException | IllegalStateException e) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "An error occurred in the process of Json parsing.", e);
        }
    }

    public static JsonObject toJson(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        JsonReader jsonReader = new JsonReader(inputStreamReader);
        try {
            JsonObject asJsonObject = JsonParser.parseReader(jsonReader).getAsJsonObject();
            try {
                jsonReader.close();
                inputStreamReader.close();
            } catch (IOException unused) {
            }
            return asJsonObject;
        } catch (JsonIOException | JsonSyntaxException | IllegalStateException e) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "An error occurred in the process of Json parsing.", e);
        } catch (Throwable th) {
            try {
                jsonReader.close();
                inputStreamReader.close();
            } catch (IOException unused2) {
            }
            throw th;
        }
    }

    public static JsonArray toJsonArray(List<String> list) {
        try {
            JsonArray jsonArray = new JsonArray();
            for (String add : list) {
                jsonArray.add(add);
            }
            return jsonArray;
        } catch (JsonIOException | JsonSyntaxException | IllegalStateException e) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "An error occurred in the process of Json parsing.", e);
        }
    }
}
