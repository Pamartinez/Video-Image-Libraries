package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class JsonStreamParser implements Iterator<JsonElement> {
    private final Object lock;
    private final JsonReader parser;

    public JsonStreamParser(String str) {
        this((Reader) new StringReader(str));
    }

    public boolean hasNext() {
        boolean z;
        synchronized (this.lock) {
            try {
                if (this.parser.peek() != JsonToken.END_DOCUMENT) {
                    z = true;
                } else {
                    z = false;
                }
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException((Throwable) e);
            } catch (IOException e7) {
                throw new JsonIOException((Throwable) e7);
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public JsonStreamParser(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        this.parser = jsonReader;
        jsonReader.setStrictness(Strictness.LENIENT);
        this.lock = new Object();
    }

    public JsonElement next() {
        if (hasNext()) {
            try {
                return Streams.parse(this.parser);
            } catch (OutOfMemoryError | StackOverflowError e) {
                throw new JsonParseException("Failed parsing JSON source to Json", e);
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
