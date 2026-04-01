package com.google.gson.internal.sql;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SqlTimeTypeAdapter extends TypeAdapter<Time> {
    static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Time.class) {
                return new SqlTimeTypeAdapter();
            }
            return null;
        }
    };
    private final DateFormat format;

    private SqlTimeTypeAdapter() {
        this.format = new SimpleDateFormat("hh:mm:ss a");
    }

    public Time read(JsonReader jsonReader) {
        Time time;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        String nextString = jsonReader.nextString();
        synchronized (this) {
            TimeZone timeZone = this.format.getTimeZone();
            try {
                time = new Time(this.format.parse(nextString).getTime());
                this.format.setTimeZone(timeZone);
            } catch (ParseException e) {
                throw new JsonSyntaxException("Failed parsing '" + nextString + "' as SQL Time; at path " + jsonReader.getPreviousPath(), e);
            } catch (Throwable th) {
                this.format.setTimeZone(timeZone);
                throw th;
            }
        }
        return time;
    }

    public void write(JsonWriter jsonWriter, Time time) {
        String format2;
        if (time == null) {
            jsonWriter.nullValue();
            return;
        }
        synchronized (this) {
            format2 = this.format.format(time);
        }
        jsonWriter.value(format2);
    }
}
