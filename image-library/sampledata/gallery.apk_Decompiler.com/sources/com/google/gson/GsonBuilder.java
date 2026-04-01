package com.google.gson;

import c0.C0086a;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.GsonPreconditions;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.samsung.android.gallery.support.utils.MapUtil;
import i.C0212a;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GsonBuilder {
    private boolean complexMapKeySerialization;
    private String datePattern;
    private int dateStyle;
    private boolean escapeHtmlChars;
    private Excluder excluder;
    private final List<TypeAdapterFactory> factories;
    private FieldNamingStrategy fieldNamingPolicy;
    private FormattingStyle formattingStyle;
    private boolean generateNonExecutableJson;
    private final List<TypeAdapterFactory> hierarchyFactories;
    private final Map<Type, InstanceCreator<?>> instanceCreators;
    private LongSerializationPolicy longSerializationPolicy;
    private ToNumberStrategy numberToNumberStrategy;
    private ToNumberStrategy objectToNumberStrategy;
    private final ArrayDeque<ReflectionAccessFilter> reflectionFilters;
    private boolean serializeNulls;
    private boolean serializeSpecialFloatingPointValues;
    private Strictness strictness;
    private int timeStyle;
    private boolean useJdkUnsafe;

    public GsonBuilder() {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        this.instanceCreators = new HashMap();
        this.factories = new ArrayList();
        this.hierarchyFactories = new ArrayList();
        this.serializeNulls = false;
        this.datePattern = Gson.DEFAULT_DATE_PATTERN;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.formattingStyle = Gson.DEFAULT_FORMATTING_STYLE;
        this.generateNonExecutableJson = false;
        this.strictness = Gson.DEFAULT_STRICTNESS;
        this.useJdkUnsafe = true;
        this.objectToNumberStrategy = Gson.DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
        this.numberToNumberStrategy = Gson.DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
        this.reflectionFilters = new ArrayDeque<>();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void addTypeAdaptersForDate(java.lang.String r4, int r5, int r6, java.util.List<com.google.gson.TypeAdapterFactory> r7) {
        /*
            boolean r0 = com.google.gson.internal.sql.SqlTypesSupport.SUPPORTS_SQL_TYPES
            r1 = 0
            if (r4 == 0) goto L_0x0026
            java.lang.String r2 = r4.trim()
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0026
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<java.util.Date> r5 = com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType.DATE
            com.google.gson.TypeAdapterFactory r5 = r5.createAdapterFactory(r4)
            if (r0 == 0) goto L_0x0024
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<? extends java.util.Date> r6 = com.google.gson.internal.sql.SqlTypesSupport.TIMESTAMP_DATE_TYPE
            com.google.gson.TypeAdapterFactory r1 = r6.createAdapterFactory(r4)
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<? extends java.util.Date> r6 = com.google.gson.internal.sql.SqlTypesSupport.DATE_DATE_TYPE
            com.google.gson.TypeAdapterFactory r4 = r6.createAdapterFactory(r4)
            goto L_0x0045
        L_0x0024:
            r4 = r1
            goto L_0x0045
        L_0x0026:
            r4 = 2
            if (r5 != r4) goto L_0x002b
            if (r6 == r4) goto L_0x0050
        L_0x002b:
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<java.util.Date> r4 = com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType.DATE
            com.google.gson.TypeAdapterFactory r4 = r4.createAdapterFactory(r5, r6)
            if (r0 == 0) goto L_0x0043
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<? extends java.util.Date> r1 = com.google.gson.internal.sql.SqlTypesSupport.TIMESTAMP_DATE_TYPE
            com.google.gson.TypeAdapterFactory r1 = r1.createAdapterFactory(r5, r6)
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<? extends java.util.Date> r2 = com.google.gson.internal.sql.SqlTypesSupport.DATE_DATE_TYPE
            com.google.gson.TypeAdapterFactory r5 = r2.createAdapterFactory(r5, r6)
            r3 = r5
            r5 = r4
            r4 = r3
            goto L_0x0045
        L_0x0043:
            r5 = r4
            goto L_0x0024
        L_0x0045:
            r7.add(r5)
            if (r0 == 0) goto L_0x0050
            r7.add(r1)
            r7.add(r4)
        L_0x0050:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.GsonBuilder.addTypeAdaptersForDate(java.lang.String, int, int, java.util.List):void");
    }

    private static int checkDateFormatStyle(int i2) {
        if (i2 >= 0 && i2 <= 3) {
            return i2;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "Invalid style: "));
    }

    private static boolean hasNonOverridableAdapter(Type type) {
        if (type == Object.class) {
            return true;
        }
        return false;
    }

    public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy exclusionStrategy) {
        Objects.requireNonNull(exclusionStrategy);
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy, false, true);
        return this;
    }

    public GsonBuilder addReflectionAccessFilter(ReflectionAccessFilter reflectionAccessFilter) {
        Objects.requireNonNull(reflectionAccessFilter);
        this.reflectionFilters.addFirst(reflectionAccessFilter);
        return this;
    }

    public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy exclusionStrategy) {
        Objects.requireNonNull(exclusionStrategy);
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy, true, false);
        return this;
    }

    public Gson create() {
        ArrayList arrayList = new ArrayList(this.hierarchyFactories.size() + this.factories.size() + 3);
        arrayList.addAll(this.factories);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(this.hierarchyFactories);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, arrayList);
        ArrayList arrayList3 = arrayList;
        Excluder excluder2 = this.excluder;
        FieldNamingStrategy fieldNamingStrategy = this.fieldNamingPolicy;
        HashMap hashMap = new HashMap(this.instanceCreators);
        boolean z = this.serializeNulls;
        boolean z3 = this.complexMapKeySerialization;
        boolean z7 = this.generateNonExecutableJson;
        boolean z9 = this.escapeHtmlChars;
        FormattingStyle formattingStyle2 = this.formattingStyle;
        Strictness strictness2 = this.strictness;
        boolean z10 = this.serializeSpecialFloatingPointValues;
        boolean z11 = this.useJdkUnsafe;
        LongSerializationPolicy longSerializationPolicy2 = this.longSerializationPolicy;
        String str = this.datePattern;
        int i2 = this.dateStyle;
        int i7 = this.timeStyle;
        Excluder excluder3 = excluder2;
        FieldNamingStrategy fieldNamingStrategy2 = fieldNamingStrategy;
        ArrayList arrayList4 = new ArrayList(this.factories);
        ArrayList arrayList5 = new ArrayList(this.hierarchyFactories);
        ToNumberStrategy toNumberStrategy = this.objectToNumberStrategy;
        ToNumberStrategy toNumberStrategy2 = this.numberToNumberStrategy;
        ArrayList arrayList6 = new ArrayList(this.reflectionFilters);
        ToNumberStrategy toNumberStrategy3 = toNumberStrategy;
        ArrayList arrayList7 = arrayList6;
        return new Gson(excluder3, fieldNamingStrategy2, hashMap, z, z3, z7, z9, formattingStyle2, strictness2, z10, z11, longSerializationPolicy2, str, i2, i7, arrayList4, arrayList5, arrayList3, toNumberStrategy3, toNumberStrategy2, arrayList7);
    }

    public GsonBuilder disableHtmlEscaping() {
        this.escapeHtmlChars = false;
        return this;
    }

    public GsonBuilder disableInnerClassSerialization() {
        this.excluder = this.excluder.disableInnerClassSerialization();
        return this;
    }

    public GsonBuilder disableJdkUnsafe() {
        this.useJdkUnsafe = false;
        return this;
    }

    public GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }

    public GsonBuilder excludeFieldsWithModifiers(int... iArr) {
        Objects.requireNonNull(iArr);
        this.excluder = this.excluder.withModifiers(iArr);
        return this;
    }

    public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    public GsonBuilder generateNonExecutableJson() {
        this.generateNonExecutableJson = true;
        return this;
    }

    public GsonBuilder registerTypeAdapter(Type type, Object obj) {
        boolean z;
        Objects.requireNonNull(type);
        boolean z3 = obj instanceof JsonSerializer;
        if (z3 || (obj instanceof JsonDeserializer) || (obj instanceof InstanceCreator) || (obj instanceof TypeAdapter)) {
            z = true;
        } else {
            z = false;
        }
        GsonPreconditions.checkArgument(z);
        if (!hasNonOverridableAdapter(type)) {
            if (obj instanceof InstanceCreator) {
                this.instanceCreators.put(type, (InstanceCreator) obj);
            }
            if (z3 || (obj instanceof JsonDeserializer)) {
                this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(type), obj));
            }
            if (obj instanceof TypeAdapter) {
                this.factories.add(TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter) obj));
            }
            return this;
        }
        throw new IllegalArgumentException("Cannot override built-in adapter for " + type);
    }

    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory typeAdapterFactory) {
        Objects.requireNonNull(typeAdapterFactory);
        this.factories.add(typeAdapterFactory);
        return this;
    }

    public GsonBuilder registerTypeHierarchyAdapter(Class<?> cls, Object obj) {
        boolean z;
        Objects.requireNonNull(cls);
        boolean z3 = obj instanceof JsonSerializer;
        if (z3 || (obj instanceof JsonDeserializer) || (obj instanceof TypeAdapter)) {
            z = true;
        } else {
            z = false;
        }
        GsonPreconditions.checkArgument(z);
        if ((obj instanceof JsonDeserializer) || z3) {
            this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(cls, obj));
        }
        if (obj instanceof TypeAdapter) {
            this.factories.add(TypeAdapters.newTypeHierarchyFactory(cls, (TypeAdapter) obj));
        }
        return this;
    }

    public GsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    public GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    public GsonBuilder setDateFormat(String str) {
        if (str != null) {
            try {
                new SimpleDateFormat(str);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(C0212a.m("The date pattern '", str, "' is not valid"), e);
            }
        }
        this.datePattern = str;
        return this;
    }

    public GsonBuilder setExclusionStrategies(ExclusionStrategy... exclusionStrategyArr) {
        Objects.requireNonNull(exclusionStrategyArr);
        for (ExclusionStrategy withExclusionStrategy : exclusionStrategyArr) {
            this.excluder = this.excluder.withExclusionStrategy(withExclusionStrategy, true, true);
        }
        return this;
    }

    public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy2) {
        return setFieldNamingStrategy(fieldNamingPolicy2);
    }

    public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
        Objects.requireNonNull(fieldNamingStrategy);
        this.fieldNamingPolicy = fieldNamingStrategy;
        return this;
    }

    public GsonBuilder setFormattingStyle(FormattingStyle formattingStyle2) {
        Objects.requireNonNull(formattingStyle2);
        this.formattingStyle = formattingStyle2;
        return this;
    }

    @Deprecated
    public GsonBuilder setLenient() {
        return setStrictness(Strictness.LENIENT);
    }

    public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy2) {
        Objects.requireNonNull(longSerializationPolicy2);
        this.longSerializationPolicy = longSerializationPolicy2;
        return this;
    }

    public GsonBuilder setNumberToNumberStrategy(ToNumberStrategy toNumberStrategy) {
        Objects.requireNonNull(toNumberStrategy);
        this.numberToNumberStrategy = toNumberStrategy;
        return this;
    }

    public GsonBuilder setObjectToNumberStrategy(ToNumberStrategy toNumberStrategy) {
        Objects.requireNonNull(toNumberStrategy);
        this.objectToNumberStrategy = toNumberStrategy;
        return this;
    }

    public GsonBuilder setPrettyPrinting() {
        return setFormattingStyle(FormattingStyle.PRETTY);
    }

    public GsonBuilder setStrictness(Strictness strictness2) {
        Objects.requireNonNull(strictness2);
        this.strictness = strictness2;
        return this;
    }

    public GsonBuilder setVersion(double d) {
        if (Double.isNaN(d) || d < MapUtil.INVALID_LOCATION) {
            throw new IllegalArgumentException("Invalid version: " + d);
        }
        this.excluder = this.excluder.withVersion(d);
        return this;
    }

    @Deprecated
    public GsonBuilder setDateFormat(int i2) {
        this.dateStyle = checkDateFormatStyle(i2);
        this.datePattern = null;
        return this;
    }

    public GsonBuilder setDateFormat(int i2, int i7) {
        this.dateStyle = checkDateFormatStyle(i2);
        this.timeStyle = checkDateFormatStyle(i7);
        this.datePattern = null;
        return this;
    }

    public GsonBuilder(Gson gson) {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        HashMap hashMap = new HashMap();
        this.instanceCreators = hashMap;
        ArrayList arrayList = new ArrayList();
        this.factories = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.hierarchyFactories = arrayList2;
        this.serializeNulls = false;
        this.datePattern = Gson.DEFAULT_DATE_PATTERN;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.formattingStyle = Gson.DEFAULT_FORMATTING_STYLE;
        this.generateNonExecutableJson = false;
        this.strictness = Gson.DEFAULT_STRICTNESS;
        this.useJdkUnsafe = true;
        this.objectToNumberStrategy = Gson.DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
        this.numberToNumberStrategy = Gson.DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
        ArrayDeque<ReflectionAccessFilter> arrayDeque = new ArrayDeque<>();
        this.reflectionFilters = arrayDeque;
        this.excluder = gson.excluder;
        this.fieldNamingPolicy = gson.fieldNamingStrategy;
        hashMap.putAll(gson.instanceCreators);
        this.serializeNulls = gson.serializeNulls;
        this.complexMapKeySerialization = gson.complexMapKeySerialization;
        this.generateNonExecutableJson = gson.generateNonExecutableJson;
        this.escapeHtmlChars = gson.htmlSafe;
        this.formattingStyle = gson.formattingStyle;
        this.strictness = gson.strictness;
        this.serializeSpecialFloatingPointValues = gson.serializeSpecialFloatingPointValues;
        this.longSerializationPolicy = gson.longSerializationPolicy;
        this.datePattern = gson.datePattern;
        this.dateStyle = gson.dateStyle;
        this.timeStyle = gson.timeStyle;
        arrayList.addAll(gson.builderFactories);
        arrayList2.addAll(gson.builderHierarchyFactories);
        this.useJdkUnsafe = gson.useJdkUnsafe;
        this.objectToNumberStrategy = gson.objectToNumberStrategy;
        this.numberToNumberStrategy = gson.numberToNumberStrategy;
        arrayDeque.addAll(gson.reflectionFilters);
    }
}
