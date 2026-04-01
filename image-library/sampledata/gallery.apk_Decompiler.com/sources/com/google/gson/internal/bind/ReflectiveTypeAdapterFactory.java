package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.ReflectionAccessFilterHelper;
import com.google.gson.internal.TroubleshootingGuide;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.samsung.android.gallery.support.utils.MapUtil;
import i.C0212a;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final List<ReflectionAccessFilter> reflectionFilters;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Adapter<T, A> extends TypeAdapter<T> {
        private final FieldsData fieldsData;

        public Adapter(FieldsData fieldsData2) {
            this.fieldsData = fieldsData2;
        }

        public abstract A createAccumulator();

        public abstract T finalize(A a7);

        public T read(JsonReader jsonReader) {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Object createAccumulator = createAccumulator();
            Map<String, BoundField> map = this.fieldsData.deserializedFields;
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    BoundField boundField = map.get(jsonReader.nextName());
                    if (boundField == null) {
                        jsonReader.skipValue();
                    } else {
                        readField(createAccumulator, jsonReader, boundField);
                    }
                }
                jsonReader.endObject();
                return finalize(createAccumulator);
            } catch (IllegalStateException e) {
                throw new JsonSyntaxException((Throwable) e);
            } catch (IllegalAccessException e7) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e7);
            }
        }

        public abstract void readField(A a7, JsonReader jsonReader, BoundField boundField);

        public void write(JsonWriter jsonWriter, T t) {
            if (t == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (BoundField write : this.fieldsData.serializedFields) {
                    write.write(jsonWriter, t);
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException e) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class BoundField {
        final Field field;
        final String fieldName;
        final String serializedName;

        public BoundField(String str, Field field2) {
            this.serializedName = str;
            this.field = field2;
            this.fieldName = field2.getName();
        }

        public abstract void readIntoArray(JsonReader jsonReader, int i2, Object[] objArr);

        public abstract void readIntoField(JsonReader jsonReader, Object obj);

        public abstract void write(JsonWriter jsonWriter, Object obj);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FieldsData {
        public static final FieldsData EMPTY = new FieldsData(Collections.EMPTY_MAP, Collections.EMPTY_LIST);
        public final Map<String, BoundField> deserializedFields;
        public final List<BoundField> serializedFields;

        public FieldsData(Map<String, BoundField> map, List<BoundField> list) {
            this.deserializedFields = map;
            this.serializedFields = list;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RecordAdapter<T> extends Adapter<T, Object[]> {
        static final Map<Class<?>, Object> PRIMITIVE_DEFAULTS = primitiveDefaults();
        private final Map<String, Integer> componentIndices = new HashMap();
        private final Constructor<T> constructor;
        private final Object[] constructorArgsDefaults;

        public RecordAdapter(Class<T> cls, FieldsData fieldsData, boolean z) {
            super(fieldsData);
            Constructor<T> canonicalRecordConstructor = ReflectionHelper.getCanonicalRecordConstructor(cls);
            this.constructor = canonicalRecordConstructor;
            if (z) {
                ReflectiveTypeAdapterFactory.checkAccessible((Object) null, canonicalRecordConstructor);
            } else {
                ReflectionHelper.makeAccessible(canonicalRecordConstructor);
            }
            String[] recordComponentNames = ReflectionHelper.getRecordComponentNames(cls);
            for (int i2 = 0; i2 < recordComponentNames.length; i2++) {
                this.componentIndices.put(recordComponentNames[i2], Integer.valueOf(i2));
            }
            Class[] parameterTypes = this.constructor.getParameterTypes();
            this.constructorArgsDefaults = new Object[parameterTypes.length];
            for (int i7 = 0; i7 < parameterTypes.length; i7++) {
                this.constructorArgsDefaults[i7] = PRIMITIVE_DEFAULTS.get(parameterTypes[i7]);
            }
        }

        private static Map<Class<?>, Object> primitiveDefaults() {
            HashMap hashMap = new HashMap();
            hashMap.put(Byte.TYPE, (byte) 0);
            hashMap.put(Short.TYPE, (short) 0);
            hashMap.put(Integer.TYPE, 0);
            hashMap.put(Long.TYPE, 0L);
            hashMap.put(Float.TYPE, Float.valueOf(0.0f));
            hashMap.put(Double.TYPE, Double.valueOf(MapUtil.INVALID_LOCATION));
            hashMap.put(Character.TYPE, 0);
            hashMap.put(Boolean.TYPE, Boolean.FALSE);
            return hashMap;
        }

        public Object[] createAccumulator() {
            return (Object[]) this.constructorArgsDefaults.clone();
        }

        public T finalize(Object[] objArr) {
            try {
                return this.constructor.newInstance(objArr);
            } catch (IllegalAccessException e) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
            } catch (IllegalArgumentException | InstantiationException e7) {
                throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(objArr), e7);
            } catch (InvocationTargetException e8) {
                throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(objArr), e8.getCause());
            }
        }

        public void readField(Object[] objArr, JsonReader jsonReader, BoundField boundField) {
            Integer num = this.componentIndices.get(boundField.fieldName);
            if (num != null) {
                boundField.readIntoArray(jsonReader, num.intValue(), objArr);
                return;
            }
            StringBuilder sb2 = new StringBuilder("Could not find the index in the constructor '");
            sb2.append(ReflectionHelper.constructorToString(this.constructor));
            sb2.append("' for field with name '");
            throw new IllegalStateException(C0212a.p(sb2, boundField.fieldName, "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters."));
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor2, FieldNamingStrategy fieldNamingStrategy, Excluder excluder2, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory, List<ReflectionAccessFilter> list) {
        this.constructorConstructor = constructorConstructor2;
        this.fieldNamingPolicy = fieldNamingStrategy;
        this.excluder = excluder2;
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
        this.reflectionFilters = list;
    }

    /* access modifiers changed from: private */
    public static <M extends AccessibleObject & Member> void checkAccessible(Object obj, M m) {
        if (Modifier.isStatic(((Member) m).getModifiers())) {
            obj = null;
        }
        if (!ReflectionAccessFilterHelper.canAccess(m, obj)) {
            throw new JsonIOException(C0212a.A(ReflectionHelper.getAccessibleObjectDescription(m, true), " is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type."));
        }
    }

    private BoundField createBoundField(Gson gson, Field field, Method method, String str, TypeToken<?> typeToken, boolean z, boolean z3) {
        boolean z7;
        TypeAdapter<?> typeAdapter;
        final TypeAdapter<?> typeAdapter2;
        TypeAdapter<?> typeAdapter3;
        final boolean isPrimitive = Primitives.isPrimitive(typeToken.getRawType());
        int modifiers = field.getModifiers();
        final boolean z9 = false;
        boolean z10 = true;
        if (!Modifier.isStatic(modifiers) || !Modifier.isFinal(modifiers)) {
            z7 = false;
        } else {
            z7 = false;
            z9 = true;
        }
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        if (jsonAdapter != null) {
            typeAdapter = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter, false);
        } else {
            typeAdapter = null;
        }
        if (typeAdapter == null) {
            z10 = z7;
        }
        TypeToken<?> typeToken2 = typeToken;
        if (typeAdapter == null) {
            typeAdapter = gson.getAdapter(typeToken2);
        }
        final TypeAdapter<?> typeAdapter4 = typeAdapter;
        if (z) {
            if (z10) {
                typeAdapter3 = typeAdapter4;
            } else {
                typeAdapter3 = new TypeAdapterRuntimeTypeWrapper<>(gson, typeAdapter4, typeToken2.getType());
            }
            typeAdapter2 = typeAdapter3;
        } else {
            typeAdapter2 = typeAdapter4;
        }
        final Method method2 = method;
        final boolean z11 = z3;
        return new BoundField(str, field) {
            public void readIntoArray(JsonReader jsonReader, int i2, Object[] objArr) {
                Object read = typeAdapter4.read(jsonReader);
                if (read != null || !isPrimitive) {
                    objArr[i2] = read;
                    return;
                }
                throw new JsonParseException("null is not allowed as value for record component '" + this.fieldName + "' of primitive type; at path " + jsonReader.getPath());
            }

            public void readIntoField(JsonReader jsonReader, Object obj) {
                Object read = typeAdapter4.read(jsonReader);
                if (read != null || !isPrimitive) {
                    if (z11) {
                        ReflectiveTypeAdapterFactory.checkAccessible(obj, this.field);
                    } else if (z9) {
                        throw new JsonIOException(C0212a.l("Cannot set value of 'static final' ", ReflectionHelper.getAccessibleObjectDescription(this.field, false)));
                    }
                    this.field.set(obj, read);
                }
            }

            public void write(JsonWriter jsonWriter, Object obj) {
                Object obj2;
                if (z11) {
                    Method method = method2;
                    if (method == null) {
                        ReflectiveTypeAdapterFactory.checkAccessible(obj, this.field);
                    } else {
                        ReflectiveTypeAdapterFactory.checkAccessible(obj, method);
                    }
                }
                Method method2 = method2;
                if (method2 != null) {
                    try {
                        obj2 = method2.invoke(obj, (Object[]) null);
                    } catch (InvocationTargetException e) {
                        throw new JsonIOException(C0212a.m("Accessor ", ReflectionHelper.getAccessibleObjectDescription(method2, false), " threw exception"), e.getCause());
                    }
                } else {
                    obj2 = this.field.get(obj);
                }
                if (obj2 != obj) {
                    jsonWriter.name(this.serializedName);
                    typeAdapter2.write(jsonWriter, obj2);
                }
            }
        };
    }

    private static IllegalArgumentException createDuplicateFieldException(Class<?> cls, String str, Field field, Field field2) {
        throw new IllegalArgumentException("Class " + cls.getName() + " declares multiple JSON fields named '" + str + "'; conflict is caused by fields " + ReflectionHelper.fieldToString(field) + " and " + ReflectionHelper.fieldToString(field2) + "\nSee " + TroubleshootingGuide.createUrl("duplicate-fields"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.FieldsData getBoundFields(com.google.gson.Gson r20, com.google.gson.reflect.TypeToken<?> r21, java.lang.Class<?> r22, boolean r23, boolean r24) {
        /*
            r19 = this;
            r0 = r19
            r8 = r22
            boolean r1 = r8.isInterface()
            if (r1 == 0) goto L_0x000d
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData r0 = com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.FieldsData.EMPTY
            return r0
        L_0x000d:
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap
            r9.<init>()
            java.util.LinkedHashMap r10 = new java.util.LinkedHashMap
            r10.<init>()
            r11 = r21
            r1 = r23
            r12 = r8
        L_0x001c:
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            if (r12 == r2) goto L_0x0142
            java.lang.reflect.Field[] r13 = r12.getDeclaredFields()
            r14 = 1
            r15 = 0
            if (r12 == r8) goto L_0x003c
            int r2 = r13.length
            if (r2 <= 0) goto L_0x003c
            java.util.List<com.google.gson.ReflectionAccessFilter> r1 = r0.reflectionFilters
            com.google.gson.ReflectionAccessFilter$FilterResult r1 = com.google.gson.internal.ReflectionAccessFilterHelper.getFilterResult(r1, r12)
            com.google.gson.ReflectionAccessFilter$FilterResult r2 = com.google.gson.ReflectionAccessFilter.FilterResult.BLOCK_ALL
            if (r1 == r2) goto L_0x003e
            com.google.gson.ReflectionAccessFilter$FilterResult r2 = com.google.gson.ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE
            if (r1 != r2) goto L_0x003b
            r1 = r14
            goto L_0x003c
        L_0x003b:
            r1 = r15
        L_0x003c:
            r7 = r1
            goto L_0x005f
        L_0x003e:
            com.google.gson.JsonIOException r0 = new com.google.gson.JsonIOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "ReflectionAccessFilter does not permit using reflection for "
            r1.<init>(r2)
            r1.append(r12)
            java.lang.String r2 = " (supertype of "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r2 = "). Register a TypeAdapter for this type or adjust the access filter."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x005f:
            int r1 = r13.length
            r2 = r15
        L_0x0061:
            if (r2 >= r1) goto L_0x0127
            r3 = r2
            r2 = r13[r3]
            boolean r6 = r0.includeField(r2, r14)
            boolean r4 = r0.includeField(r2, r15)
            if (r6 != 0) goto L_0x007b
            if (r4 != 0) goto L_0x007b
            r17 = r1
            r18 = r3
            r16 = r11
            r11 = r15
            goto L_0x011b
        L_0x007b:
            r5 = 0
            if (r24 == 0) goto L_0x00b4
            int r16 = r2.getModifiers()
            boolean r16 = java.lang.reflect.Modifier.isStatic(r16)
            if (r16 == 0) goto L_0x008a
            r14 = r15
            goto L_0x00b5
        L_0x008a:
            java.lang.reflect.Method r5 = com.google.gson.internal.reflect.ReflectionHelper.getAccessor(r12, r2)
            if (r7 != 0) goto L_0x0093
            com.google.gson.internal.reflect.ReflectionHelper.makeAccessible(r5)
        L_0x0093:
            java.lang.Class<com.google.gson.annotations.SerializedName> r14 = com.google.gson.annotations.SerializedName.class
            java.lang.annotation.Annotation r16 = r5.getAnnotation(r14)
            if (r16 == 0) goto L_0x00b4
            java.lang.annotation.Annotation r14 = r2.getAnnotation(r14)
            if (r14 == 0) goto L_0x00a2
            goto L_0x00b4
        L_0x00a2:
            java.lang.String r0 = com.google.gson.internal.reflect.ReflectionHelper.getAccessibleObjectDescription(r5, r15)
            com.google.gson.JsonIOException r1 = new com.google.gson.JsonIOException
            java.lang.String r2 = "@SerializedName on "
            java.lang.String r3 = " is not supported"
            java.lang.String r0 = i.C0212a.m(r2, r0, r3)
            r1.<init>((java.lang.String) r0)
            throw r1
        L_0x00b4:
            r14 = r4
        L_0x00b5:
            if (r7 != 0) goto L_0x00bc
            if (r5 != 0) goto L_0x00bc
            com.google.gson.internal.reflect.ReflectionHelper.makeAccessible(r2)
        L_0x00bc:
            java.lang.reflect.Type r4 = r11.getType()
            java.lang.reflect.Type r15 = r2.getGenericType()
            java.lang.reflect.Type r4 = com.google.gson.internal.GsonTypes.resolve(r4, r12, r15)
            java.util.List r15 = r0.getFieldNames(r2)
            r16 = r11
            r11 = 0
            java.lang.Object r17 = r15.get(r11)
            java.lang.String r17 = (java.lang.String) r17
            com.google.gson.reflect.TypeToken r4 = com.google.gson.reflect.TypeToken.get((java.lang.reflect.Type) r4)
            r18 = r3
            r3 = r5
            r5 = r4
            r4 = r17
            r17 = r1
            r1 = r20
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField r3 = r0.createBoundField(r1, r2, r3, r4, r5, r6, r7)
            if (r14 == 0) goto L_0x0109
            java.util.Iterator r0 = r15.iterator()
        L_0x00ed:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0109
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r5 = r9.put(r1, r3)
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField r5 = (com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField) r5
            if (r5 != 0) goto L_0x0102
            goto L_0x00ed
        L_0x0102:
            java.lang.reflect.Field r0 = r5.field
            java.lang.IllegalArgumentException r0 = createDuplicateFieldException(r8, r1, r0, r2)
            throw r0
        L_0x0109:
            if (r6 == 0) goto L_0x011b
            java.lang.Object r0 = r10.put(r4, r3)
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField r0 = (com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField) r0
            if (r0 != 0) goto L_0x0114
            goto L_0x011b
        L_0x0114:
            java.lang.reflect.Field r0 = r0.field
            java.lang.IllegalArgumentException r0 = createDuplicateFieldException(r8, r4, r0, r2)
            throw r0
        L_0x011b:
            int r2 = r18 + 1
            r0 = r19
            r15 = r11
            r11 = r16
            r1 = r17
            r14 = 1
            goto L_0x0061
        L_0x0127:
            r16 = r11
            java.lang.reflect.Type r0 = r16.getType()
            java.lang.reflect.Type r1 = r12.getGenericSuperclass()
            java.lang.reflect.Type r0 = com.google.gson.internal.GsonTypes.resolve(r0, r12, r1)
            com.google.gson.reflect.TypeToken r11 = com.google.gson.reflect.TypeToken.get((java.lang.reflect.Type) r0)
            java.lang.Class r12 = r11.getRawType()
            r0 = r19
            r1 = r7
            goto L_0x001c
        L_0x0142:
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData r0 = new com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Collection r2 = r10.values()
            r1.<init>(r2)
            r0.<init>(r9, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(com.google.gson.Gson, com.google.gson.reflect.TypeToken, java.lang.Class, boolean, boolean):com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData");
    }

    private List<String> getFieldNames(Field field) {
        List<String> list;
        String str;
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        if (serializedName == null) {
            str = this.fieldNamingPolicy.translateName(field);
            list = this.fieldNamingPolicy.alternateNames(field);
        } else {
            str = serializedName.value();
            list = Arrays.asList(serializedName.alternate());
        }
        if (list.isEmpty()) {
            return Collections.singletonList(str);
        }
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.add(str);
        arrayList.addAll(list);
        return arrayList;
    }

    private boolean includeField(Field field, boolean z) {
        return !this.excluder.excludeField(field, z);
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        boolean z;
        Class<? super T> rawType = typeToken.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        if (ReflectionHelper.isAnonymousOrNonStaticLocal(rawType)) {
            return new TypeAdapter<T>() {
                public T read(JsonReader jsonReader) {
                    jsonReader.skipValue();
                    return null;
                }

                public String toString() {
                    return "AnonymousOrNonStaticLocalClassAdapter";
                }

                public void write(JsonWriter jsonWriter, T t) {
                    jsonWriter.nullValue();
                }
            };
        }
        ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, rawType);
        if (filterResult != ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
            if (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE) {
                z = true;
            } else {
                z = false;
            }
            if (ReflectionHelper.isRecord(rawType)) {
                return new RecordAdapter(rawType, getBoundFields(gson, typeToken, rawType, z, true), z);
            }
            TypeToken<T> typeToken2 = typeToken;
            return new FieldReflectionAdapter(this.constructorConstructor.get(typeToken2, true), getBoundFields(gson, typeToken2, rawType, z, false));
        }
        throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + rawType + ". Register a TypeAdapter for this type or adjust the access filter.");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FieldReflectionAdapter<T> extends Adapter<T, T> {
        private final ObjectConstructor<T> constructor;

        public FieldReflectionAdapter(ObjectConstructor<T> objectConstructor, FieldsData fieldsData) {
            super(fieldsData);
            this.constructor = objectConstructor;
        }

        public T createAccumulator() {
            return this.constructor.construct();
        }

        public void readField(T t, JsonReader jsonReader, BoundField boundField) {
            boundField.readIntoField(jsonReader, t);
        }

        public T finalize(T t) {
            return t;
        }
    }
}
