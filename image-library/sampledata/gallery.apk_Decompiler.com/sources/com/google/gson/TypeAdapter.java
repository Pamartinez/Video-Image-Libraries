package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TypeAdapter<T> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class NullSafeTypeAdapter extends TypeAdapter<T> {
        private NullSafeTypeAdapter() {
        }

        public T read(JsonReader jsonReader) {
            if (jsonReader.peek() != JsonToken.NULL) {
                return TypeAdapter.this.read(jsonReader);
            }
            jsonReader.nextNull();
            return null;
        }

        public String toString() {
            return "NullSafeTypeAdapter[" + TypeAdapter.this + "]";
        }

        public void write(JsonWriter jsonWriter, T t) {
            if (t == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter.this.write(jsonWriter, t);
            }
        }
    }

    public final T fromJson(Reader reader) {
        return read(new JsonReader(reader));
    }

    public final T fromJsonTree(JsonElement jsonElement) {
        try {
            return read(new JsonTreeReader(jsonElement));
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }

    public final TypeAdapter<T> nullSafe() {
        if (!(this instanceof NullSafeTypeAdapter)) {
            return new NullSafeTypeAdapter();
        }
        return this;
    }

    public abstract T read(JsonReader jsonReader);

    public final void toJson(Writer writer, T t) {
        write(new JsonWriter(writer), t);
    }

    public final JsonElement toJsonTree(T t) {
        try {
            JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
            write(jsonTreeWriter, t);
            return jsonTreeWriter.get();
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }

    public abstract void write(JsonWriter jsonWriter, T t);

    public final T fromJson(String str) {
        return fromJson((Reader) new StringReader(str));
    }

    public final String toJson(T t) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, t);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }
}
