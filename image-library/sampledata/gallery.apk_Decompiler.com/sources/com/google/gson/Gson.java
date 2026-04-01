package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.NumberTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SerializationDelegatingTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.sql.SqlTypesSupport;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Gson {
    static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
    static final String DEFAULT_DATE_PATTERN = null;
    static final boolean DEFAULT_ESCAPE_HTML = true;
    static final FieldNamingStrategy DEFAULT_FIELD_NAMING_STRATEGY = FieldNamingPolicy.IDENTITY;
    static final FormattingStyle DEFAULT_FORMATTING_STYLE = FormattingStyle.COMPACT;
    static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    static final ToNumberStrategy DEFAULT_NUMBER_TO_NUMBER_STRATEGY = ToNumberPolicy.LAZILY_PARSED_NUMBER;
    static final ToNumberStrategy DEFAULT_OBJECT_TO_NUMBER_STRATEGY = ToNumberPolicy.DOUBLE;
    static final boolean DEFAULT_SERIALIZE_NULLS = false;
    static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
    static final Strictness DEFAULT_STRICTNESS = null;
    static final boolean DEFAULT_USE_JDK_UNSAFE = true;
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
    final List<TypeAdapterFactory> builderFactories;
    final List<TypeAdapterFactory> builderHierarchyFactories;
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;
    final String datePattern;
    final int dateStyle;
    final Excluder excluder;
    final List<TypeAdapterFactory> factories;
    final FieldNamingStrategy fieldNamingStrategy;
    final FormattingStyle formattingStyle;
    final boolean generateNonExecutableJson;
    final boolean htmlSafe;
    final Map<Type, InstanceCreator<?>> instanceCreators;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    final LongSerializationPolicy longSerializationPolicy;
    final ToNumberStrategy numberToNumberStrategy;
    final ToNumberStrategy objectToNumberStrategy;
    final List<ReflectionAccessFilter> reflectionFilters;
    final boolean serializeNulls;
    final boolean serializeSpecialFloatingPointValues;
    final Strictness strictness;
    private final ThreadLocal<Map<TypeToken<?>, TypeAdapter<?>>> threadLocalAdapterResults;
    final int timeStyle;
    private final ConcurrentMap<TypeToken<?>, TypeAdapter<?>> typeTokenCache;
    final boolean useJdkUnsafe;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FutureTypeAdapter<T> extends SerializationDelegatingTypeAdapter<T> {
        private TypeAdapter<T> delegate = null;

        private TypeAdapter<T> delegate() {
            TypeAdapter<T> typeAdapter = this.delegate;
            if (typeAdapter != null) {
                return typeAdapter;
            }
            throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
        }

        public TypeAdapter<T> getSerializationDelegate() {
            return delegate();
        }

        public T read(JsonReader jsonReader) {
            return delegate().read(jsonReader);
        }

        public void setDelegate(TypeAdapter<T> typeAdapter) {
            if (this.delegate == null) {
                this.delegate = typeAdapter;
                return;
            }
            throw new AssertionError("Delegate is already set");
        }

        public void write(JsonWriter jsonWriter, T t) {
            delegate().write(jsonWriter, t);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Gson() {
        /*
            r22 = this;
            com.google.gson.internal.Excluder r1 = com.google.gson.internal.Excluder.DEFAULT
            com.google.gson.FieldNamingStrategy r2 = DEFAULT_FIELD_NAMING_STRATEGY
            java.util.Map r3 = java.util.Collections.EMPTY_MAP
            com.google.gson.FormattingStyle r8 = DEFAULT_FORMATTING_STYLE
            com.google.gson.Strictness r9 = DEFAULT_STRICTNESS
            com.google.gson.LongSerializationPolicy r12 = com.google.gson.LongSerializationPolicy.DEFAULT
            java.lang.String r13 = DEFAULT_DATE_PATTERN
            java.util.List r16 = java.util.Collections.EMPTY_LIST
            com.google.gson.ToNumberStrategy r19 = DEFAULT_OBJECT_TO_NUMBER_STRATEGY
            com.google.gson.ToNumberStrategy r20 = DEFAULT_NUMBER_TO_NUMBER_STRATEGY
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 1
            r10 = 0
            r11 = 1
            r14 = 2
            r15 = 2
            r17 = r16
            r18 = r16
            r21 = r16
            r0 = r22
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.<init>():void");
    }

    private static void assertFullConsumption(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonSyntaxException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException((Throwable) e);
            } catch (IOException e7) {
                throw new JsonIOException((Throwable) e7);
            }
        }
    }

    private static TypeAdapter<AtomicLong> atomicLongAdapter(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLong>() {
            public AtomicLong read(JsonReader jsonReader) {
                return new AtomicLong(((Number) TypeAdapter.this.read(jsonReader)).longValue());
            }

            public void write(JsonWriter jsonWriter, AtomicLong atomicLong) {
                TypeAdapter.this.write(jsonWriter, Long.valueOf(atomicLong.get()));
            }
        }.nullSafe();
    }

    private static TypeAdapter<AtomicLongArray> atomicLongArrayAdapter(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLongArray>() {
            public AtomicLongArray read(JsonReader jsonReader) {
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(Long.valueOf(((Number) TypeAdapter.this.read(jsonReader)).longValue()));
                }
                jsonReader.endArray();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i2 = 0; i2 < size; i2++) {
                    atomicLongArray.set(i2, ((Long) arrayList.get(i2)).longValue());
                }
                return atomicLongArray;
            }

            public void write(JsonWriter jsonWriter, AtomicLongArray atomicLongArray) {
                jsonWriter.beginArray();
                int length = atomicLongArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    TypeAdapter.this.write(jsonWriter, Long.valueOf(atomicLongArray.get(i2)));
                }
                jsonWriter.endArray();
            }
        }.nullSafe();
    }

    public static void checkValidFloatingPoint(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private TypeAdapter<Number> doubleAdapter(boolean z) {
        if (z) {
            return TypeAdapters.DOUBLE;
        }
        return new TypeAdapter<Number>() {
            public Double read(JsonReader jsonReader) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Double.valueOf(jsonReader.nextDouble());
                }
                jsonReader.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, Number number) {
                if (number == null) {
                    jsonWriter.nullValue();
                    return;
                }
                double doubleValue = number.doubleValue();
                Gson.checkValidFloatingPoint(doubleValue);
                jsonWriter.value(doubleValue);
            }
        };
    }

    private TypeAdapter<Number> floatAdapter(boolean z) {
        if (z) {
            return TypeAdapters.FLOAT;
        }
        return new TypeAdapter<Number>() {
            public Float read(JsonReader jsonReader) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Float.valueOf((float) jsonReader.nextDouble());
                }
                jsonReader.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, Number number) {
                if (number == null) {
                    jsonWriter.nullValue();
                    return;
                }
                float floatValue = number.floatValue();
                Gson.checkValidFloatingPoint((double) floatValue);
                if (!(number instanceof Float)) {
                    number = Float.valueOf(floatValue);
                }
                jsonWriter.value(number);
            }
        };
    }

    private static TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy2) {
        if (longSerializationPolicy2 == LongSerializationPolicy.DEFAULT) {
            return TypeAdapters.LONG;
        }
        return new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Long.valueOf(jsonReader.nextLong());
                }
                jsonReader.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, Number number) {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(number.toString());
                }
            }
        };
    }

    @Deprecated
    public Excluder excluder() {
        return this.excluder;
    }

    public FieldNamingStrategy fieldNamingStrategy() {
        return this.fieldNamingStrategy;
    }

    public <T> T fromJson(String str, Class<T> cls) {
        return fromJson(str, TypeToken.get(cls));
    }

    public <T> TypeAdapter<T> getAdapter(TypeToken<T> typeToken) {
        boolean z;
        Objects.requireNonNull(typeToken, "type must not be null");
        TypeAdapter<T> typeAdapter = this.typeTokenCache.get(typeToken);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        Map map = this.threadLocalAdapterResults.get();
        if (map == null) {
            map = new HashMap();
            this.threadLocalAdapterResults.set(map);
            z = true;
        } else {
            TypeAdapter<T> typeAdapter2 = (TypeAdapter) map.get(typeToken);
            if (typeAdapter2 != null) {
                return typeAdapter2;
            }
            z = false;
        }
        try {
            FutureTypeAdapter futureTypeAdapter = new FutureTypeAdapter();
            map.put(typeToken, futureTypeAdapter);
            Iterator<TypeAdapterFactory> it = this.factories.iterator();
            TypeAdapter<T> typeAdapter3 = null;
            while (true) {
                if (it.hasNext()) {
                    typeAdapter3 = it.next().create(this, typeToken);
                    if (typeAdapter3 != null) {
                        futureTypeAdapter.setDelegate(typeAdapter3);
                        map.put(typeToken, typeAdapter3);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (typeAdapter3 != null) {
                if (z) {
                    this.typeTokenCache.putAll(map);
                }
                return typeAdapter3;
            }
            throw new IllegalArgumentException("GSON (2.13.1) cannot handle " + typeToken);
        } finally {
            if (z) {
                this.threadLocalAdapterResults.remove();
            }
        }
    }

    public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory typeAdapterFactory, TypeToken<T> typeToken) {
        Objects.requireNonNull(typeAdapterFactory, "skipPast must not be null");
        Objects.requireNonNull(typeToken, "type must not be null");
        if (this.jsonAdapterFactory.isClassJsonAdapterFactory(typeToken, typeAdapterFactory)) {
            typeAdapterFactory = this.jsonAdapterFactory;
        }
        boolean z = false;
        for (TypeAdapterFactory next : this.factories) {
            if (z) {
                TypeAdapter<T> create = next.create(this, typeToken);
                if (create != null) {
                    return create;
                }
            } else if (next == typeAdapterFactory) {
                z = true;
            }
        }
        if (!z) {
            return getAdapter(typeToken);
        }
        throw new IllegalArgumentException("GSON cannot serialize or deserialize " + typeToken);
    }

    public boolean htmlSafe() {
        return this.htmlSafe;
    }

    public GsonBuilder newBuilder() {
        return new GsonBuilder(this);
    }

    public JsonReader newJsonReader(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        Strictness strictness2 = this.strictness;
        if (strictness2 == null) {
            strictness2 = Strictness.LEGACY_STRICT;
        }
        jsonReader.setStrictness(strictness2);
        return jsonReader;
    }

    public JsonWriter newJsonWriter(Writer writer) {
        if (this.generateNonExecutableJson) {
            writer.write(JSON_NON_EXECUTABLE_PREFIX);
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        jsonWriter.setFormattingStyle(this.formattingStyle);
        jsonWriter.setHtmlSafe(this.htmlSafe);
        Strictness strictness2 = this.strictness;
        if (strictness2 == null) {
            strictness2 = Strictness.LEGACY_STRICT;
        }
        jsonWriter.setStrictness(strictness2);
        jsonWriter.setSerializeNulls(this.serializeNulls);
        return jsonWriter;
    }

    public boolean serializeNulls() {
        return this.serializeNulls;
    }

    public String toJson(Object obj) {
        if (obj == null) {
            return toJson((JsonElement) JsonNull.INSTANCE);
        }
        return toJson(obj, (Type) obj.getClass());
    }

    public JsonElement toJsonTree(Object obj) {
        if (obj == null) {
            return JsonNull.INSTANCE;
        }
        return toJsonTree(obj, obj.getClass());
    }

    public String toString() {
        return "{serializeNulls:" + this.serializeNulls + ",factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
    }

    public <T> T fromJson(String str, Type type) {
        return fromJson(str, TypeToken.get(type));
    }

    public <T> T fromJson(String str, TypeToken<T> typeToken) {
        if (str == null) {
            return null;
        }
        return fromJson((Reader) new StringReader(str), typeToken);
    }

    public String toJson(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        toJson(obj, type, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public JsonElement toJsonTree(Object obj, Type type) {
        JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
        toJson(obj, type, (JsonWriter) jsonTreeWriter);
        return jsonTreeWriter.get();
    }

    public <T> T fromJson(Reader reader, Class<T> cls) {
        return fromJson(reader, TypeToken.get(cls));
    }

    public Gson(Excluder excluder2, FieldNamingStrategy fieldNamingStrategy2, Map<Type, InstanceCreator<?>> map, boolean z, boolean z3, boolean z7, boolean z9, FormattingStyle formattingStyle2, Strictness strictness2, boolean z10, boolean z11, LongSerializationPolicy longSerializationPolicy2, String str, int i2, int i7, List<TypeAdapterFactory> list, List<TypeAdapterFactory> list2, List<TypeAdapterFactory> list3, ToNumberStrategy toNumberStrategy, ToNumberStrategy toNumberStrategy2, List<ReflectionAccessFilter> list4) {
        boolean z12 = z10;
        boolean z13 = z11;
        List<ReflectionAccessFilter> list5 = list4;
        this.threadLocalAdapterResults = new ThreadLocal<>();
        this.typeTokenCache = new ConcurrentHashMap();
        this.excluder = excluder2;
        this.fieldNamingStrategy = fieldNamingStrategy2;
        this.instanceCreators = map;
        ConstructorConstructor constructorConstructor2 = new ConstructorConstructor(map, z13, list5);
        this.constructorConstructor = constructorConstructor2;
        this.serializeNulls = z;
        this.complexMapKeySerialization = z3;
        this.generateNonExecutableJson = z7;
        this.htmlSafe = z9;
        this.formattingStyle = formattingStyle2;
        this.strictness = strictness2;
        this.serializeSpecialFloatingPointValues = z12;
        this.useJdkUnsafe = z13;
        LongSerializationPolicy longSerializationPolicy3 = longSerializationPolicy2;
        this.longSerializationPolicy = longSerializationPolicy3;
        this.datePattern = str;
        this.dateStyle = i2;
        this.timeStyle = i7;
        this.builderFactories = list;
        this.builderHierarchyFactories = list2;
        ToNumberStrategy toNumberStrategy3 = toNumberStrategy;
        this.objectToNumberStrategy = toNumberStrategy3;
        ToNumberStrategy toNumberStrategy4 = toNumberStrategy2;
        this.numberToNumberStrategy = toNumberStrategy4;
        this.reflectionFilters = list5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        arrayList.add(ObjectTypeAdapter.getFactory(toNumberStrategy3));
        arrayList.add(excluder2);
        arrayList.addAll(list3);
        arrayList.add(TypeAdapters.STRING_FACTORY);
        arrayList.add(TypeAdapters.INTEGER_FACTORY);
        arrayList.add(TypeAdapters.BOOLEAN_FACTORY);
        arrayList.add(TypeAdapters.BYTE_FACTORY);
        arrayList.add(TypeAdapters.SHORT_FACTORY);
        TypeAdapter<Number> longAdapter = longAdapter(longSerializationPolicy3);
        arrayList.add(TypeAdapters.newFactory(Long.TYPE, Long.class, longAdapter));
        TypeAdapter<Number> doubleAdapter = doubleAdapter(z12);
        arrayList.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter));
        TypeAdapter<Number> floatAdapter = floatAdapter(z12);
        arrayList.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter));
        arrayList.add(NumberTypeAdapter.getFactory(toNumberStrategy4));
        arrayList.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
        arrayList.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
        arrayList.add(TypeAdapters.newFactory(AtomicLong.class, atomicLongAdapter(longAdapter)));
        arrayList.add(TypeAdapters.newFactory(AtomicLongArray.class, atomicLongArrayAdapter(longAdapter)));
        arrayList.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
        arrayList.add(TypeAdapters.CHARACTER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUILDER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUFFER_FACTORY);
        arrayList.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
        arrayList.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
        arrayList.add(TypeAdapters.newFactory(LazilyParsedNumber.class, TypeAdapters.LAZILY_PARSED_NUMBER));
        arrayList.add(TypeAdapters.URL_FACTORY);
        arrayList.add(TypeAdapters.URI_FACTORY);
        arrayList.add(TypeAdapters.UUID_FACTORY);
        arrayList.add(TypeAdapters.CURRENCY_FACTORY);
        arrayList.add(TypeAdapters.LOCALE_FACTORY);
        arrayList.add(TypeAdapters.INET_ADDRESS_FACTORY);
        arrayList.add(TypeAdapters.BIT_SET_FACTORY);
        arrayList.add(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);
        arrayList.add(TypeAdapters.CALENDAR_FACTORY);
        if (SqlTypesSupport.SUPPORTS_SQL_TYPES) {
            arrayList.add(SqlTypesSupport.TIME_FACTORY);
            arrayList.add(SqlTypesSupport.DATE_FACTORY);
            arrayList.add(SqlTypesSupport.TIMESTAMP_FACTORY);
        }
        arrayList.add(ArrayTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.CLASS_FACTORY);
        arrayList.add(new CollectionTypeAdapterFactory(constructorConstructor2));
        arrayList.add(new MapTypeAdapterFactory(constructorConstructor2, z3));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor2);
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(TypeAdapters.ENUM_FACTORY);
        ConstructorConstructor constructorConstructor3 = constructorConstructor2;
        arrayList.add(new ReflectiveTypeAdapterFactory(constructorConstructor3, fieldNamingStrategy2, excluder2, jsonAdapterAnnotationTypeAdapterFactory, list5));
        this.factories = Collections.unmodifiableList(arrayList);
    }

    public <T> T fromJson(Reader reader, Type type) {
        return fromJson(reader, TypeToken.get(type));
    }

    public void toJson(Object obj, Appendable appendable) {
        if (obj != null) {
            toJson(obj, (Type) obj.getClass(), appendable);
        } else {
            toJson((JsonElement) JsonNull.INSTANCE, appendable);
        }
    }

    public <T> T fromJson(Reader reader, TypeToken<T> typeToken) {
        JsonReader newJsonReader = newJsonReader(reader);
        T fromJson = fromJson(newJsonReader, typeToken);
        assertFullConsumption(fromJson, newJsonReader);
        return fromJson;
    }

    public void toJson(Object obj, Type type, Appendable appendable) {
        try {
            toJson(obj, type, newJsonWriter(Streams.writerForAppendable(appendable)));
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }

    public <T> T fromJson(JsonReader jsonReader, Type type) {
        return fromJson(jsonReader, TypeToken.get(type));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006f, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008c, code lost:
        throw new java.lang.AssertionError("AssertionError (GSON 2.13.1): " + r7.getMessage(), r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0092, code lost:
        throw new com.google.gson.JsonSyntaxException((java.lang.Throwable) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0098, code lost:
        throw new com.google.gson.JsonSyntaxException((java.lang.Throwable) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0099, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009a, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009d, code lost:
        r8.setStrictness(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a1, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a7, code lost:
        throw new com.google.gson.JsonSyntaxException((java.lang.Throwable) r7);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006b A[ExcHandler: AssertionError (r7v6 'e' java.lang.AssertionError A[CUSTOM_DECLARE]), Splitter:B:6:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d A[ExcHandler: IOException (r7v5 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:6:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006f A[ExcHandler: IllegalStateException (r7v4 'e' java.lang.IllegalStateException A[CUSTOM_DECLARE]), Splitter:B:6:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a2 A[SYNTHETIC, Splitter:B:36:0x00a2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T fromJson(com.google.gson.stream.JsonReader r8, com.google.gson.reflect.TypeToken<T> r9) {
        /*
            r7 = this;
            java.lang.String r0 = "AssertionError (GSON 2.13.1): "
            java.lang.String r1 = "Type adapter '"
            com.google.gson.Strictness r2 = r8.getStrictness()
            com.google.gson.Strictness r3 = r7.strictness
            if (r3 == 0) goto L_0x0010
            r8.setStrictness(r3)
            goto L_0x001d
        L_0x0010:
            com.google.gson.Strictness r3 = r8.getStrictness()
            com.google.gson.Strictness r4 = com.google.gson.Strictness.LEGACY_STRICT
            if (r3 != r4) goto L_0x001d
            com.google.gson.Strictness r3 = com.google.gson.Strictness.LENIENT
            r8.setStrictness(r3)
        L_0x001d:
            r8.peek()     // Catch:{ EOFException -> 0x0099, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r3 = 0
            com.google.gson.TypeAdapter r7 = r7.getAdapter(r9)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Object r4 = r7.read(r8)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Class r5 = r9.getRawType()     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Class r5 = com.google.gson.internal.Primitives.wrap(r5)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            if (r4 == 0) goto L_0x0073
            boolean r5 = r5.isInstance(r4)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            if (r5 == 0) goto L_0x003a
            goto L_0x0073
        L_0x003a:
            java.lang.ClassCastException r5 = new java.lang.ClassCastException     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r6.<init>(r1)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.String r7 = "' returned wrong type; requested "
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Class r7 = r9.getRawType()     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.String r7 = " but got instance of "
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Class r7 = r4.getClass()     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.String r7 = "\nVerify that the adapter was registered for the correct type."
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.String r7 = r6.toString()     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r5.<init>(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            throw r5     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
        L_0x0069:
            r7 = move-exception
            goto L_0x00a8
        L_0x006b:
            r7 = move-exception
            goto L_0x0077
        L_0x006d:
            r7 = move-exception
            goto L_0x008d
        L_0x006f:
            r7 = move-exception
            goto L_0x0093
        L_0x0071:
            r7 = move-exception
            goto L_0x009b
        L_0x0073:
            r8.setStrictness(r2)
            return r4
        L_0x0077:
            java.lang.AssertionError r9 = new java.lang.AssertionError     // Catch:{ all -> 0x0069 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0069 }
            r1.<init>(r0)     // Catch:{ all -> 0x0069 }
            java.lang.String r0 = r7.getMessage()     // Catch:{ all -> 0x0069 }
            r1.append(r0)     // Catch:{ all -> 0x0069 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x0069 }
            r9.<init>(r0, r7)     // Catch:{ all -> 0x0069 }
            throw r9     // Catch:{ all -> 0x0069 }
        L_0x008d:
            com.google.gson.JsonSyntaxException r9 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x0069 }
            r9.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0069 }
            throw r9     // Catch:{ all -> 0x0069 }
        L_0x0093:
            com.google.gson.JsonSyntaxException r9 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x0069 }
            r9.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0069 }
            throw r9     // Catch:{ all -> 0x0069 }
        L_0x0099:
            r7 = move-exception
            r3 = 1
        L_0x009b:
            if (r3 == 0) goto L_0x00a2
            r8.setStrictness(r2)
            r7 = 0
            return r7
        L_0x00a2:
            com.google.gson.JsonSyntaxException r9 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x0069 }
            r9.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0069 }
            throw r9     // Catch:{ all -> 0x0069 }
        L_0x00a8:
            r8.setStrictness(r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.fromJson(com.google.gson.stream.JsonReader, com.google.gson.reflect.TypeToken):java.lang.Object");
    }

    public void toJson(Object obj, Type type, JsonWriter jsonWriter) {
        TypeAdapter<?> adapter = getAdapter(TypeToken.get(type));
        Strictness strictness2 = jsonWriter.getStrictness();
        Strictness strictness3 = this.strictness;
        if (strictness3 != null) {
            jsonWriter.setStrictness(strictness3);
        } else if (jsonWriter.getStrictness() == Strictness.LEGACY_STRICT) {
            jsonWriter.setStrictness(Strictness.LENIENT);
        }
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        boolean serializeNulls2 = jsonWriter.getSerializeNulls();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        jsonWriter.setSerializeNulls(this.serializeNulls);
        try {
            adapter.write(jsonWriter, obj);
            jsonWriter.setStrictness(strictness2);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls2);
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        } catch (AssertionError e7) {
            throw new AssertionError("AssertionError (GSON 2.13.1): " + e7.getMessage(), e7);
        } catch (Throwable th) {
            jsonWriter.setStrictness(strictness2);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls2);
            throw th;
        }
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> cls) {
        return getAdapter(TypeToken.get(cls));
    }

    public String toJson(JsonElement jsonElement) {
        StringWriter stringWriter = new StringWriter();
        toJson(jsonElement, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public <T> T fromJson(JsonElement jsonElement, Class<T> cls) {
        return fromJson(jsonElement, TypeToken.get(cls));
    }

    public <T> T fromJson(JsonElement jsonElement, Type type) {
        return fromJson(jsonElement, TypeToken.get(type));
    }

    public void toJson(JsonElement jsonElement, Appendable appendable) {
        try {
            toJson(jsonElement, newJsonWriter(Streams.writerForAppendable(appendable)));
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }

    public <T> T fromJson(JsonElement jsonElement, TypeToken<T> typeToken) {
        if (jsonElement == null) {
            return null;
        }
        return fromJson((JsonReader) new JsonTreeReader(jsonElement), typeToken);
    }

    public void toJson(JsonElement jsonElement, JsonWriter jsonWriter) {
        Strictness strictness2 = jsonWriter.getStrictness();
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        boolean serializeNulls2 = jsonWriter.getSerializeNulls();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        jsonWriter.setSerializeNulls(this.serializeNulls);
        Strictness strictness3 = this.strictness;
        if (strictness3 != null) {
            jsonWriter.setStrictness(strictness3);
        } else if (jsonWriter.getStrictness() == Strictness.LEGACY_STRICT) {
            jsonWriter.setStrictness(Strictness.LENIENT);
        }
        try {
            Streams.write(jsonElement, jsonWriter);
            jsonWriter.setStrictness(strictness2);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls2);
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        } catch (AssertionError e7) {
            throw new AssertionError("AssertionError (GSON 2.13.1): " + e7.getMessage(), e7);
        } catch (Throwable th) {
            jsonWriter.setStrictness(strictness2);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls2);
            throw th;
        }
    }
}
