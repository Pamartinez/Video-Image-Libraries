package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final Gson context;
    private final TypeAdapter<T> delegate;
    private final Type type;

    public TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type2) {
        this.context = gson;
        this.delegate = typeAdapter;
        this.type = type2;
    }

    private static Type getRuntimeTypeIfMoreSpecific(Type type2, Object obj) {
        if (obj == null) {
            return type2;
        }
        if ((type2 instanceof Class) || (type2 instanceof TypeVariable)) {
            return obj.getClass();
        }
        return type2;
    }

    private static boolean isReflective(TypeAdapter<?> typeAdapter) {
        TypeAdapter<?> serializationDelegate;
        while ((typeAdapter instanceof SerializationDelegatingTypeAdapter) && (serializationDelegate = ((SerializationDelegatingTypeAdapter) typeAdapter).getSerializationDelegate()) != typeAdapter) {
            typeAdapter = serializationDelegate;
        }
        return typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter;
    }

    public T read(JsonReader jsonReader) {
        return this.delegate.read(jsonReader);
    }

    public void write(JsonWriter jsonWriter, T t) {
        TypeAdapter<T> typeAdapter = this.delegate;
        Type runtimeTypeIfMoreSpecific = getRuntimeTypeIfMoreSpecific(this.type, t);
        if (runtimeTypeIfMoreSpecific != this.type) {
            typeAdapter = this.context.getAdapter(TypeToken.get(runtimeTypeIfMoreSpecific));
            if ((typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter) && !isReflective(this.delegate)) {
                typeAdapter = this.delegate;
            }
        }
        typeAdapter.write(jsonWriter, t);
    }
}
