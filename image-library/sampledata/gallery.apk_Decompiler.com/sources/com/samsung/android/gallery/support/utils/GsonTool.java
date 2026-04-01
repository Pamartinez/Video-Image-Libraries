package com.samsung.android.gallery.support.utils;

import A.a;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GsonTool {
    public static void appendObjectToJson(String str, Object obj) {
        writeObjectToJson(str, obj, true, TimeUtil.getTimestamp());
    }

    public static <T> T fromJsonFile(Class<T> cls, String str) {
        FileReader fileReader;
        Gson create = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        try {
            fileReader = new FileReader(str);
            T fromJson = create.fromJson((Reader) fileReader, cls);
            fileReader.close();
            return fromJson;
        } catch (IOException e) {
            Log.e((CharSequence) "GsonTool", "fromJsonFile failed", (Throwable) e);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static <T> T fromJsonString(Class<T> cls, String str) {
        return new GsonBuilder().serializeNulls().setPrettyPrinting().create().fromJson(str, cls);
    }

    public static boolean toJsonFile(Object obj, String str) {
        FileWriter fileWriter;
        if (FileUtils.exists(str)) {
            FileUtils.delete(str);
        }
        String jsonString = toJsonString(obj);
        try {
            fileWriter = new FileWriter(str, false);
            fileWriter.write(jsonString);
            fileWriter.flush();
            Log.d("GsonTool", "toJsonFile ", Integer.valueOf(jsonString.length()), str);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            Log.e((CharSequence) "GsonTool", "toJsonFile failed", (Throwable) e);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static JsonObject toJsonObject(String str) {
        try {
            return JsonParser.parseString(str).getAsJsonObject();
        } catch (Exception e) {
            a.s(e, new StringBuilder("toJsonObject failed. e="), "GsonTool");
            return null;
        }
    }

    public static String toJsonString(Object obj) {
        return toJsonString(obj, true);
    }

    public static String toJsonStringForNetwork(Object obj) {
        return new GsonBuilder().serializeNulls().disableHtmlEscaping().create().toJson(obj);
    }

    public static void writeObjectToJson(String str, Object obj, boolean z, String str2) {
        FileWriter fileWriter;
        String json = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create().toJson(obj);
        try {
            fileWriter = new FileWriter(str, true);
            if (z && str2 != null) {
                fileWriter.write(System.lineSeparator());
                fileWriter.write(System.lineSeparator());
                fileWriter.write(str2);
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.write(json);
            fileWriter.flush();
            Log.d("GsonTool", "dump to file");
            fileWriter.close();
            return;
        } catch (IOException e) {
            Log.e((CharSequence) "GsonTool", "writeObjectToJson failed", (Throwable) e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static String toJsonString(Object obj, boolean z) {
        GsonBuilder disableHtmlEscaping = new GsonBuilder().serializeNulls().disableHtmlEscaping();
        if (z) {
            disableHtmlEscaping.setPrettyPrinting();
        }
        return disableHtmlEscaping.create().toJson(obj);
    }

    public static <T> T fromJsonString(TypeToken<T> typeToken, String str) {
        return new GsonBuilder().serializeNulls().setPrettyPrinting().create().fromJson(str, typeToken.getType());
    }
}
