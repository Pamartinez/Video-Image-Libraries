package com.google.gson.internal.bind;

import N2.j;
import c0.C0086a;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.NumberLimits;
import com.google.gson.internal.TroubleshootingGuide;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TypeAdapters {
    public static final TypeAdapter<AtomicBoolean> ATOMIC_BOOLEAN;
    public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY;
    public static final TypeAdapter<AtomicInteger> ATOMIC_INTEGER;
    public static final TypeAdapter<AtomicIntegerArray> ATOMIC_INTEGER_ARRAY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY;
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL = new TypeAdapter<BigDecimal>() {
        public BigDecimal read(JsonReader jsonReader) {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            try {
                return NumberLimits.parseBigDecimal(nextString);
            } catch (NumberFormatException e) {
                StringBuilder k = j.k("Failed parsing '", nextString, "' as BigDecimal; at path ");
                k.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(k.toString(), e);
            }
        }

        public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) {
            jsonWriter.value((Number) bigDecimal);
        }
    };
    public static final TypeAdapter<BigInteger> BIG_INTEGER = new TypeAdapter<BigInteger>() {
        public BigInteger read(JsonReader jsonReader) {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            try {
                return NumberLimits.parseBigInteger(nextString);
            } catch (NumberFormatException e) {
                StringBuilder k = j.k("Failed parsing '", nextString, "' as BigInteger; at path ");
                k.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(k.toString(), e);
            }
        }

        public void write(JsonWriter jsonWriter, BigInteger bigInteger) {
            jsonWriter.value((Number) bigInteger);
        }
    };
    public static final TypeAdapter<BitSet> BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY;
    public static final TypeAdapter<Boolean> BOOLEAN;
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING = new TypeAdapter<Boolean>() {
        public Boolean read(JsonReader jsonReader) {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Boolean.valueOf(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        public void write(JsonWriter jsonWriter, Boolean bool) {
            jsonWriter.value(bool == null ? "null" : bool.toString());
        }
    };
    public static final TypeAdapterFactory BOOLEAN_FACTORY;
    public static final TypeAdapter<Number> BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY;
    public static final TypeAdapter<Calendar> CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY;
    public static final TypeAdapter<Character> CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY;
    public static final TypeAdapter<Class> CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY;
    public static final TypeAdapter<Currency> CURRENCY;
    public static final TypeAdapterFactory CURRENCY_FACTORY;
    public static final TypeAdapter<Number> DOUBLE = new TypeAdapter<Number>() {
        public Number read(JsonReader jsonReader) {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Double.valueOf(jsonReader.nextDouble());
            }
            jsonReader.nextNull();
            return null;
        }

        public void write(JsonWriter jsonWriter, Number number) {
            if (number == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(number.doubleValue());
            }
        }
    };
    public static final TypeAdapterFactory ENUM_FACTORY = EnumTypeAdapter.FACTORY;
    public static final TypeAdapter<Number> FLOAT = new TypeAdapter<Number>() {
        public Number read(JsonReader jsonReader) {
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
            if (!(number instanceof Float)) {
                number = Float.valueOf(number.floatValue());
            }
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapter<InetAddress> INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
    public static final TypeAdapter<Number> INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY;
    public static final TypeAdapter<JsonElement> JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
    public static final TypeAdapter<LazilyParsedNumber> LAZILY_PARSED_NUMBER = new TypeAdapter<LazilyParsedNumber>() {
        public LazilyParsedNumber read(JsonReader jsonReader) {
            if (jsonReader.peek() != JsonToken.NULL) {
                return new LazilyParsedNumber(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }

        public void write(JsonWriter jsonWriter, LazilyParsedNumber lazilyParsedNumber) {
            jsonWriter.value((Number) lazilyParsedNumber);
        }
    };
    public static final TypeAdapter<Locale> LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY;
    public static final TypeAdapter<Number> LONG = new TypeAdapter<Number>() {
        public Number read(JsonReader jsonReader) {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Long.valueOf(jsonReader.nextLong());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException((Throwable) e);
            }
        }

        public void write(JsonWriter jsonWriter, Number number) {
            if (number == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(number.longValue());
            }
        }
    };
    public static final TypeAdapter<Number> SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY;
    public static final TypeAdapter<String> STRING;
    public static final TypeAdapter<StringBuffer> STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
    public static final TypeAdapter<StringBuilder> STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
    public static final TypeAdapterFactory STRING_FACTORY;
    public static final TypeAdapter<URI> URI;
    public static final TypeAdapterFactory URI_FACTORY;
    public static final TypeAdapter<URL> URL;
    public static final TypeAdapterFactory URL_FACTORY;
    public static final TypeAdapter<UUID> UUID;
    public static final TypeAdapterFactory UUID_FACTORY;

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$33  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass33 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.gson.stream.JsonToken[] r0 = com.google.gson.stream.JsonToken.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$gson$stream$JsonToken = r0
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NUMBER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.STRING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.TypeAdapters.AnonymousClass33.<clinit>():void");
        }
    }

    static {
        TypeAdapter<Class> nullSafe = new TypeAdapter<Class>() {
            public Class read(JsonReader jsonReader) {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?\nSee " + TroubleshootingGuide.createUrl("java-lang-class-unsupported"));
            }

            public void write(JsonWriter jsonWriter, Class cls) {
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?\nSee " + TroubleshootingGuide.createUrl("java-lang-class-unsupported"));
            }
        }.nullSafe();
        CLASS = nullSafe;
        CLASS_FACTORY = newFactory(Class.class, nullSafe);
        TypeAdapter<BitSet> nullSafe2 = new TypeAdapter<BitSet>() {
            public BitSet read(JsonReader jsonReader) {
                BitSet bitSet = new BitSet();
                jsonReader.beginArray();
                JsonToken peek = jsonReader.peek();
                int i2 = 0;
                while (peek != JsonToken.END_ARRAY) {
                    int i7 = AnonymousClass33.$SwitchMap$com$google$gson$stream$JsonToken[peek.ordinal()];
                    boolean z = true;
                    if (i7 == 1 || i7 == 2) {
                        int nextInt = jsonReader.nextInt();
                        if (nextInt == 0) {
                            z = false;
                        } else if (nextInt != 1) {
                            StringBuilder o2 = C0086a.o(nextInt, "Invalid bitset value ", ", expected 0 or 1; at path ");
                            o2.append(jsonReader.getPreviousPath());
                            throw new JsonSyntaxException(o2.toString());
                        }
                    } else if (i7 == 3) {
                        z = jsonReader.nextBoolean();
                    } else {
                        throw new JsonSyntaxException("Invalid bitset value type: " + peek + "; at path " + jsonReader.getPath());
                    }
                    if (z) {
                        bitSet.set(i2);
                    }
                    i2++;
                    peek = jsonReader.peek();
                }
                jsonReader.endArray();
                return bitSet;
            }

            public void write(JsonWriter jsonWriter, BitSet bitSet) {
                jsonWriter.beginArray();
                int length = bitSet.length();
                for (int i2 = 0; i2 < length; i2++) {
                    jsonWriter.value(bitSet.get(i2) ? 1 : 0);
                }
                jsonWriter.endArray();
            }
        }.nullSafe();
        BIT_SET = nullSafe2;
        BIT_SET_FACTORY = newFactory(BitSet.class, nullSafe2);
        AnonymousClass3 r0 = new TypeAdapter<Boolean>() {
            public Boolean read(JsonReader jsonReader) {
                JsonToken peek = jsonReader.peek();
                if (peek == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                } else if (peek == JsonToken.STRING) {
                    return Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString()));
                } else {
                    return Boolean.valueOf(jsonReader.nextBoolean());
                }
            }

            public void write(JsonWriter jsonWriter, Boolean bool) {
                jsonWriter.value(bool);
            }
        };
        BOOLEAN = r0;
        BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, r0);
        AnonymousClass5 r02 = new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    int nextInt = jsonReader.nextInt();
                    if (nextInt <= 255 && nextInt >= -128) {
                        return Byte.valueOf((byte) nextInt);
                    }
                    StringBuilder o2 = C0086a.o(nextInt, "Lossy conversion from ", " to byte; at path ");
                    o2.append(jsonReader.getPreviousPath());
                    throw new JsonSyntaxException(o2.toString());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException((Throwable) e);
                }
            }

            public void write(JsonWriter jsonWriter, Number number) {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value((long) number.byteValue());
                }
            }
        };
        BYTE = r02;
        BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, r02);
        AnonymousClass6 r03 = new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    int nextInt = jsonReader.nextInt();
                    if (nextInt <= 65535 && nextInt >= -32768) {
                        return Short.valueOf((short) nextInt);
                    }
                    StringBuilder o2 = C0086a.o(nextInt, "Lossy conversion from ", " to short; at path ");
                    o2.append(jsonReader.getPreviousPath());
                    throw new JsonSyntaxException(o2.toString());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException((Throwable) e);
                }
            }

            public void write(JsonWriter jsonWriter, Number number) {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value((long) number.shortValue());
                }
            }
        };
        SHORT = r03;
        SHORT_FACTORY = newFactory(Short.TYPE, Short.class, r03);
        AnonymousClass7 r04 = new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return Integer.valueOf(jsonReader.nextInt());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException((Throwable) e);
                }
            }

            public void write(JsonWriter jsonWriter, Number number) {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value((long) number.intValue());
                }
            }
        };
        INTEGER = r04;
        INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, r04);
        TypeAdapter<AtomicInteger> nullSafe3 = new TypeAdapter<AtomicInteger>() {
            public AtomicInteger read(JsonReader jsonReader) {
                try {
                    return new AtomicInteger(jsonReader.nextInt());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException((Throwable) e);
                }
            }

            public void write(JsonWriter jsonWriter, AtomicInteger atomicInteger) {
                jsonWriter.value((long) atomicInteger.get());
            }
        }.nullSafe();
        ATOMIC_INTEGER = nullSafe3;
        ATOMIC_INTEGER_FACTORY = newFactory(AtomicInteger.class, nullSafe3);
        TypeAdapter<AtomicBoolean> nullSafe4 = new TypeAdapter<AtomicBoolean>() {
            public AtomicBoolean read(JsonReader jsonReader) {
                return new AtomicBoolean(jsonReader.nextBoolean());
            }

            public void write(JsonWriter jsonWriter, AtomicBoolean atomicBoolean) {
                jsonWriter.value(atomicBoolean.get());
            }
        }.nullSafe();
        ATOMIC_BOOLEAN = nullSafe4;
        ATOMIC_BOOLEAN_FACTORY = newFactory(AtomicBoolean.class, nullSafe4);
        TypeAdapter<AtomicIntegerArray> nullSafe5 = new TypeAdapter<AtomicIntegerArray>() {
            public AtomicIntegerArray read(JsonReader jsonReader) {
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    try {
                        arrayList.add(Integer.valueOf(jsonReader.nextInt()));
                    } catch (NumberFormatException e) {
                        throw new JsonSyntaxException((Throwable) e);
                    }
                }
                jsonReader.endArray();
                int size = arrayList.size();
                AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
                for (int i2 = 0; i2 < size; i2++) {
                    atomicIntegerArray.set(i2, ((Integer) arrayList.get(i2)).intValue());
                }
                return atomicIntegerArray;
            }

            public void write(JsonWriter jsonWriter, AtomicIntegerArray atomicIntegerArray) {
                jsonWriter.beginArray();
                int length = atomicIntegerArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    jsonWriter.value((long) atomicIntegerArray.get(i2));
                }
                jsonWriter.endArray();
            }
        }.nullSafe();
        ATOMIC_INTEGER_ARRAY = nullSafe5;
        ATOMIC_INTEGER_ARRAY_FACTORY = newFactory(AtomicIntegerArray.class, nullSafe5);
        AnonymousClass14 r05 = new TypeAdapter<Character>() {
            public Character read(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String nextString = jsonReader.nextString();
                if (nextString.length() == 1) {
                    return Character.valueOf(nextString.charAt(0));
                }
                StringBuilder k = j.k("Expecting character, got: ", nextString, "; at ");
                k.append(jsonReader.getPreviousPath());
                throw new JsonSyntaxException(k.toString());
            }

            public void write(JsonWriter jsonWriter, Character ch) {
                jsonWriter.value(ch == null ? null : String.valueOf(ch));
            }
        };
        CHARACTER = r05;
        CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, r05);
        AnonymousClass15 r06 = new TypeAdapter<String>() {
            public String read(JsonReader jsonReader) {
                JsonToken peek = jsonReader.peek();
                if (peek == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                } else if (peek == JsonToken.BOOLEAN) {
                    return Boolean.toString(jsonReader.nextBoolean());
                } else {
                    return jsonReader.nextString();
                }
            }

            public void write(JsonWriter jsonWriter, String str) {
                jsonWriter.value(str);
            }
        };
        STRING = r06;
        STRING_FACTORY = newFactory(String.class, r06);
        AnonymousClass19 r07 = new TypeAdapter<StringBuilder>() {
            public StringBuilder read(JsonReader jsonReader) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return new StringBuilder(jsonReader.nextString());
                }
                jsonReader.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, StringBuilder sb2) {
                jsonWriter.value(sb2 == null ? null : sb2.toString());
            }
        };
        STRING_BUILDER = r07;
        STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, r07);
        AnonymousClass20 r08 = new TypeAdapter<StringBuffer>() {
            public StringBuffer read(JsonReader jsonReader) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return new StringBuffer(jsonReader.nextString());
                }
                jsonReader.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, StringBuffer stringBuffer) {
                jsonWriter.value(stringBuffer == null ? null : stringBuffer.toString());
            }
        };
        STRING_BUFFER = r08;
        STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, r08);
        AnonymousClass21 r09 = new TypeAdapter<URL>() {
            public URL read(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String nextString = jsonReader.nextString();
                if (nextString.equals("null")) {
                    return null;
                }
                return new URL(nextString);
            }

            public void write(JsonWriter jsonWriter, URL url) {
                jsonWriter.value(url == null ? null : url.toExternalForm());
            }
        };
        URL = r09;
        URL_FACTORY = newFactory(URL.class, r09);
        AnonymousClass22 r010 = new TypeAdapter<URI>() {
            public URI read(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    String nextString = jsonReader.nextString();
                    if (nextString.equals("null")) {
                        return null;
                    }
                    return new URI(nextString);
                } catch (URISyntaxException e) {
                    throw new JsonIOException((Throwable) e);
                }
            }

            public void write(JsonWriter jsonWriter, URI uri) {
                jsonWriter.value(uri == null ? null : uri.toASCIIString());
            }
        };
        URI = r010;
        URI_FACTORY = newFactory(URI.class, r010);
        AnonymousClass23 r011 = new TypeAdapter<InetAddress>() {
            public InetAddress read(JsonReader jsonReader) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return InetAddress.getByName(jsonReader.nextString());
                }
                jsonReader.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, InetAddress inetAddress) {
                jsonWriter.value(inetAddress == null ? null : inetAddress.getHostAddress());
            }
        };
        INET_ADDRESS = r011;
        INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, r011);
        AnonymousClass24 r012 = new TypeAdapter<UUID>() {
            public UUID read(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String nextString = jsonReader.nextString();
                try {
                    return UUID.fromString(nextString);
                } catch (IllegalArgumentException e) {
                    StringBuilder k = j.k("Failed parsing '", nextString, "' as UUID; at path ");
                    k.append(jsonReader.getPreviousPath());
                    throw new JsonSyntaxException(k.toString(), e);
                }
            }

            public void write(JsonWriter jsonWriter, UUID uuid) {
                jsonWriter.value(uuid == null ? null : uuid.toString());
            }
        };
        UUID = r012;
        UUID_FACTORY = newFactory(UUID.class, r012);
        TypeAdapter<Currency> nullSafe6 = new TypeAdapter<Currency>() {
            public Currency read(JsonReader jsonReader) {
                String nextString = jsonReader.nextString();
                try {
                    return Currency.getInstance(nextString);
                } catch (IllegalArgumentException e) {
                    StringBuilder k = j.k("Failed parsing '", nextString, "' as Currency; at path ");
                    k.append(jsonReader.getPreviousPath());
                    throw new JsonSyntaxException(k.toString(), e);
                }
            }

            public void write(JsonWriter jsonWriter, Currency currency) {
                jsonWriter.value(currency.getCurrencyCode());
            }
        }.nullSafe();
        CURRENCY = nullSafe6;
        CURRENCY_FACTORY = newFactory(Currency.class, nullSafe6);
        AnonymousClass26 r013 = new TypeAdapter<Calendar>() {
            private static final String DAY_OF_MONTH = "dayOfMonth";
            private static final String HOUR_OF_DAY = "hourOfDay";
            private static final String MINUTE = "minute";
            private static final String MONTH = "month";
            private static final String SECOND = "second";
            private static final String YEAR = "year";

            public Calendar read(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                jsonReader.beginObject();
                int i2 = 0;
                int i7 = 0;
                int i8 = 0;
                int i10 = 0;
                int i11 = 0;
                int i12 = 0;
                while (jsonReader.peek() != JsonToken.END_OBJECT) {
                    String nextName = jsonReader.nextName();
                    int nextInt = jsonReader.nextInt();
                    nextName.getClass();
                    char c5 = 65535;
                    switch (nextName.hashCode()) {
                        case -1181204563:
                            if (nextName.equals(DAY_OF_MONTH)) {
                                c5 = 0;
                                break;
                            }
                            break;
                        case -1074026988:
                            if (nextName.equals(MINUTE)) {
                                c5 = 1;
                                break;
                            }
                            break;
                        case -906279820:
                            if (nextName.equals(SECOND)) {
                                c5 = 2;
                                break;
                            }
                            break;
                        case 3704893:
                            if (nextName.equals(YEAR)) {
                                c5 = 3;
                                break;
                            }
                            break;
                        case 104080000:
                            if (nextName.equals(MONTH)) {
                                c5 = 4;
                                break;
                            }
                            break;
                        case 985252545:
                            if (nextName.equals(HOUR_OF_DAY)) {
                                c5 = 5;
                                break;
                            }
                            break;
                    }
                    switch (c5) {
                        case 0:
                            i8 = nextInt;
                            break;
                        case 1:
                            i11 = nextInt;
                            break;
                        case 2:
                            i12 = nextInt;
                            break;
                        case 3:
                            i2 = nextInt;
                            break;
                        case 4:
                            i7 = nextInt;
                            break;
                        case 5:
                            i10 = nextInt;
                            break;
                    }
                }
                jsonReader.endObject();
                return new GregorianCalendar(i2, i7, i8, i10, i11, i12);
            }

            public void write(JsonWriter jsonWriter, Calendar calendar) {
                if (calendar == null) {
                    jsonWriter.nullValue();
                    return;
                }
                jsonWriter.beginObject();
                jsonWriter.name(YEAR);
                jsonWriter.value((long) calendar.get(1));
                jsonWriter.name(MONTH);
                jsonWriter.value((long) calendar.get(2));
                jsonWriter.name(DAY_OF_MONTH);
                jsonWriter.value((long) calendar.get(5));
                jsonWriter.name(HOUR_OF_DAY);
                jsonWriter.value((long) calendar.get(11));
                jsonWriter.name(MINUTE);
                jsonWriter.value((long) calendar.get(12));
                jsonWriter.name(SECOND);
                jsonWriter.value((long) calendar.get(13));
                jsonWriter.endObject();
            }
        };
        CALENDAR = r013;
        CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, r013);
        AnonymousClass27 r014 = new TypeAdapter<Locale>() {
            public Locale read(JsonReader jsonReader) {
                String str = null;
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
                String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                if (stringTokenizer.hasMoreElements()) {
                    str = stringTokenizer.nextToken();
                }
                if (nextToken2 == null && str == null) {
                    return new Locale(nextToken);
                }
                if (str == null) {
                    return new Locale(nextToken, nextToken2);
                }
                return new Locale(nextToken, nextToken2, str);
            }

            public void write(JsonWriter jsonWriter, Locale locale) {
                jsonWriter.value(locale == null ? null : locale.toString());
            }
        };
        LOCALE = r014;
        LOCALE_FACTORY = newFactory(Locale.class, r014);
        JsonElementTypeAdapter jsonElementTypeAdapter = JsonElementTypeAdapter.ADAPTER;
        JSON_ELEMENT = jsonElementTypeAdapter;
        JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, jsonElementTypeAdapter);
    }

    private TypeAdapters() {
        throw new UnsupportedOperationException();
    }

    public static <TT> TypeAdapterFactory newFactory(final TypeToken<TT> typeToken, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.equals(TypeToken.this)) {
                    return typeAdapter;
                }
                return null;
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(final Class<TT> cls, final Class<? extends TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <T1> TypeAdapterFactory newTypeHierarchyFactory(final Class<T1> cls, final TypeAdapter<T1> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T2> TypeAdapter<T2> create(Gson gson, TypeToken<T2> typeToken) {
                final Class<? super T2> rawType = typeToken.getRawType();
                if (!cls.isAssignableFrom(rawType)) {
                    return null;
                }
                return new TypeAdapter<T1>() {
                    public T1 read(JsonReader jsonReader) {
                        T1 read = typeAdapter.read(jsonReader);
                        if (read == null || rawType.isInstance(read)) {
                            return read;
                        }
                        throw new JsonSyntaxException("Expected a " + rawType.getName() + " but was " + read.getClass().getName() + "; at path " + jsonReader.getPreviousPath());
                    }

                    public void write(JsonWriter jsonWriter, T1 t12) {
                        typeAdapter.write(jsonWriter, t12);
                    }
                };
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> cls, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.getRawType() == cls) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> cls, final Class<TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + "+" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }
}
