package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class JsonParser {
    public static JsonElement parseReader(Reader reader) {
        try {
            JsonReader jsonReader = new JsonReader(reader);
            JsonElement parseReader = parseReader(jsonReader);
            if (!parseReader.isJsonNull()) {
                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonSyntaxException("Did not consume the entire document.");
                }
            }
            return parseReader;
        } catch (MalformedJsonException | NumberFormatException e) {
            throw new JsonSyntaxException(e);
        } catch (IOException e7) {
            throw new JsonIOException((Throwable) e7);
        }
    }

    public static JsonElement parseString(String str) {
        return parseReader((Reader) new StringReader(str));
    }

    @Deprecated
    public JsonElement parse(String str) {
        return parseString(str);
    }

    @Deprecated
    public JsonElement parse(Reader reader) {
        return parseReader(reader);
    }

    @Deprecated
    public JsonElement parse(JsonReader jsonReader) {
        return parseReader(jsonReader);
    }

    public static JsonElement parseReader(JsonReader jsonReader) {
        Strictness strictness = jsonReader.getStrictness();
        if (strictness == Strictness.LEGACY_STRICT) {
            jsonReader.setStrictness(Strictness.LENIENT);
        }
        try {
            JsonElement parse = Streams.parse(jsonReader);
            jsonReader.setStrictness(strictness);
            return parse;
        } catch (OutOfMemoryError | StackOverflowError e) {
            throw new JsonParseException("Failed parsing JSON source: " + jsonReader + " to Json", e);
        } catch (Throwable th) {
            jsonReader.setStrictness(strictness);
            throw th;
        }
    }
}
