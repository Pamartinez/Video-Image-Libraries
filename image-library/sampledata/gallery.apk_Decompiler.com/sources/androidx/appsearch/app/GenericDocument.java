package androidx.appsearch.app;

import android.util.Log;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import androidx.appsearch.util.IndentingStringBuilder;
import androidx.core.util.Preconditions;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GenericDocument {
    public static final GenericDocument EMPTY = new Builder("", "", "").build();
    /* access modifiers changed from: private */
    public final GenericDocumentParcel mDocumentParcel;

    public GenericDocument(GenericDocumentParcel genericDocumentParcel) {
        Objects.requireNonNull(genericDocumentParcel);
        this.mDocumentParcel = genericDocumentParcel;
    }

    private void appendPropertyString(String str, Object obj, IndentingStringBuilder indentingStringBuilder) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(obj);
        Preconditions.checkNotNull(indentingStringBuilder);
        indentingStringBuilder.append("\"").append(str).append("\": [");
        int i2 = 0;
        if (obj instanceof GenericDocument[]) {
            GenericDocument[] genericDocumentArr = (GenericDocument[]) obj;
            while (i2 < genericDocumentArr.length) {
                indentingStringBuilder.append("\n");
                indentingStringBuilder.increaseIndentLevel();
                genericDocumentArr[i2].appendGenericDocumentString(indentingStringBuilder);
                if (i2 != genericDocumentArr.length - 1) {
                    indentingStringBuilder.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                }
                indentingStringBuilder.append("\n");
                indentingStringBuilder.decreaseIndentLevel();
                i2++;
            }
        } else {
            int length = Array.getLength(obj);
            while (i2 < length) {
                Object obj2 = Array.get(obj, i2);
                if (obj2 instanceof String) {
                    indentingStringBuilder.append("\"").append((String) obj2).append("\"");
                } else if (obj2 instanceof byte[]) {
                    indentingStringBuilder.append(Arrays.toString((byte[]) obj2));
                } else if (obj2 != null) {
                    indentingStringBuilder.append(obj2.toString());
                }
                if (i2 != length - 1) {
                    indentingStringBuilder.append(ArcCommonLog.TAG_COMMA);
                }
                i2++;
            }
        }
        indentingStringBuilder.append("]");
    }

    private <T> Class<? extends T> findTargetClassToDeserialize(Class<T> cls, Map<String, List<String>> map, Map<String, List<String>> map2) {
        List<String> list;
        if (map.isEmpty()) {
            return cls;
        }
        Class<? extends T> assignableClassBySchemaName = AppSearchDocumentClassMap.getAssignableClassBySchemaName(map, getSchemaType(), cls);
        if (assignableClassBySchemaName != null) {
            return assignableClassBySchemaName;
        }
        if (map2.containsKey(getSchemaType())) {
            list = map2.get(getSchemaType());
        } else {
            list = getParentTypes();
        }
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                Class<? extends T> assignableClassBySchemaName2 = AppSearchDocumentClassMap.getAssignableClassBySchemaName(map, list.get(i2), cls);
                if (assignableClassBySchemaName2 != null) {
                    return assignableClassBySchemaName2;
                }
            }
        }
        Log.w("AppSearchGenericDocumen", "Cannot find any compatible target class to deserialize. Perhaps the annotation processor was not run or the generated document class map was proguarded out?\nTry to deserialize to " + cls.getCanonicalName() + " directly.");
        return cls;
    }

    private static Object flattenAccumulator(List<Object> list) {
        if (list.isEmpty()) {
            return null;
        }
        Object obj = list.get(0);
        if (obj instanceof String[]) {
            int i2 = 0;
            for (int i7 = 0; i7 < list.size(); i7++) {
                i2 += ((String[]) list.get(i7)).length;
            }
            String[] strArr = new String[i2];
            int i8 = 0;
            for (int i10 = 0; i10 < list.size(); i10++) {
                String[] strArr2 = (String[]) list.get(i10);
                System.arraycopy(strArr2, 0, strArr, i8, strArr2.length);
                i8 += strArr2.length;
            }
            return strArr;
        } else if (obj instanceof long[]) {
            int i11 = 0;
            for (int i12 = 0; i12 < list.size(); i12++) {
                i11 += ((long[]) list.get(i12)).length;
            }
            long[] jArr = new long[i11];
            int i13 = 0;
            for (int i14 = 0; i14 < list.size(); i14++) {
                long[] jArr2 = (long[]) list.get(i14);
                System.arraycopy(jArr2, 0, jArr, i13, jArr2.length);
                i13 += jArr2.length;
            }
            return jArr;
        } else if (obj instanceof double[]) {
            int i15 = 0;
            for (int i16 = 0; i16 < list.size(); i16++) {
                i15 += ((double[]) list.get(i16)).length;
            }
            double[] dArr = new double[i15];
            int i17 = 0;
            for (int i18 = 0; i18 < list.size(); i18++) {
                double[] dArr2 = (double[]) list.get(i18);
                System.arraycopy(dArr2, 0, dArr, i17, dArr2.length);
                i17 += dArr2.length;
            }
            return dArr;
        } else if (obj instanceof boolean[]) {
            int i19 = 0;
            for (int i20 = 0; i20 < list.size(); i20++) {
                i19 += ((boolean[]) list.get(i20)).length;
            }
            boolean[] zArr = new boolean[i19];
            int i21 = 0;
            for (int i22 = 0; i22 < list.size(); i22++) {
                boolean[] zArr2 = (boolean[]) list.get(i22);
                System.arraycopy(zArr2, 0, zArr, i21, zArr2.length);
                i21 += zArr2.length;
            }
            return zArr;
        } else if (obj instanceof byte[][]) {
            int i23 = 0;
            for (int i24 = 0; i24 < list.size(); i24++) {
                i23 += ((byte[][]) list.get(i24)).length;
            }
            byte[][] bArr = new byte[i23][];
            int i25 = 0;
            for (int i26 = 0; i26 < list.size(); i26++) {
                byte[][] bArr2 = (byte[][]) list.get(i26);
                System.arraycopy(bArr2, 0, bArr, i25, bArr2.length);
                i25 += bArr2.length;
            }
            return bArr;
        } else if (obj instanceof GenericDocumentParcel[]) {
            int i27 = 0;
            for (int i28 = 0; i28 < list.size(); i28++) {
                i27 += ((GenericDocumentParcel[]) list.get(i28)).length;
            }
            GenericDocumentParcel[] genericDocumentParcelArr = new GenericDocumentParcel[i27];
            int i29 = 0;
            for (int i30 = 0; i30 < list.size(); i30++) {
                GenericDocumentParcel[] genericDocumentParcelArr2 = (GenericDocumentParcel[]) list.get(i30);
                System.arraycopy(genericDocumentParcelArr2, 0, genericDocumentParcelArr, i29, genericDocumentParcelArr2.length);
                i29 += genericDocumentParcelArr2.length;
            }
            return genericDocumentParcelArr;
        } else {
            throw new IllegalStateException("Unexpected property type: " + obj);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: androidx.appsearch.safeparcel.GenericDocumentParcel[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: boolean[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: long[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: androidx.appsearch.safeparcel.PropertyParcel} */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object getRawPropertyFromRawDocument(androidx.appsearch.app.PropertyPath r6, int r7, java.util.Map<java.lang.String, androidx.appsearch.safeparcel.PropertyParcel> r8) {
        /*
            java.util.Objects.requireNonNull(r6)
            java.util.Objects.requireNonNull(r8)
        L_0x0006:
            int r0 = r6.size()
            r1 = 0
            if (r7 >= r0) goto L_0x0169
            androidx.appsearch.app.PropertyPath$PathSegment r0 = r6.get(r7)
            java.lang.String r2 = r0.getPropertyName()
            java.lang.Object r2 = r8.get(r2)
            if (r2 != 0) goto L_0x001c
            return r1
        L_0x001c:
            int r0 = r0.getPropertyIndex()
            r3 = -1
            if (r0 == r3) goto L_0x00ec
            r3 = r2
            androidx.appsearch.safeparcel.PropertyParcel r3 = (androidx.appsearch.safeparcel.PropertyParcel) r3
            java.lang.String[] r4 = r3.getStringValues()
            if (r4 == 0) goto L_0x0040
            java.lang.String[] r2 = r3.getStringValues()
            if (r2 == 0) goto L_0x003c
            int r3 = r2.length
            if (r0 >= r3) goto L_0x003c
            int r3 = r0 + 1
            java.lang.Object[] r0 = java.util.Arrays.copyOfRange(r2, r0, r3)
            goto L_0x003d
        L_0x003c:
            r0 = r1
        L_0x003d:
            r2 = r0
            goto L_0x00ec
        L_0x0040:
            long[] r4 = r3.getLongValues()
            if (r4 == 0) goto L_0x0056
            long[] r2 = r3.getLongValues()
            if (r2 == 0) goto L_0x003c
            int r3 = r2.length
            if (r0 >= r3) goto L_0x003c
            int r3 = r0 + 1
            long[] r0 = java.util.Arrays.copyOfRange(r2, r0, r3)
            goto L_0x003d
        L_0x0056:
            double[] r4 = r3.getDoubleValues()
            if (r4 == 0) goto L_0x006c
            double[] r2 = r3.getDoubleValues()
            if (r2 == 0) goto L_0x003c
            int r3 = r2.length
            if (r0 >= r3) goto L_0x003c
            int r3 = r0 + 1
            double[] r0 = java.util.Arrays.copyOfRange(r2, r0, r3)
            goto L_0x003d
        L_0x006c:
            boolean[] r4 = r3.getBooleanValues()
            if (r4 == 0) goto L_0x0082
            boolean[] r2 = r3.getBooleanValues()
            if (r2 == 0) goto L_0x003c
            int r3 = r2.length
            if (r0 >= r3) goto L_0x003c
            int r3 = r0 + 1
            boolean[] r0 = java.util.Arrays.copyOfRange(r2, r0, r3)
            goto L_0x003d
        L_0x0082:
            byte[][] r4 = r3.getBytesValues()
            if (r4 == 0) goto L_0x0098
            byte[][] r2 = r3.getBytesValues()
            if (r2 == 0) goto L_0x003c
            int r3 = r2.length
            if (r0 >= r3) goto L_0x003c
            int r3 = r0 + 1
            java.lang.Object[] r0 = java.util.Arrays.copyOfRange(r2, r0, r3)
            goto L_0x003d
        L_0x0098:
            androidx.appsearch.safeparcel.GenericDocumentParcel[] r4 = r3.getDocumentValues()
            if (r4 == 0) goto L_0x00aa
            androidx.appsearch.safeparcel.GenericDocumentParcel[] r2 = r3.getDocumentValues()
            if (r2 == 0) goto L_0x003c
            int r3 = r2.length
            if (r0 >= r3) goto L_0x003c
            r0 = r2[r0]
            goto L_0x003d
        L_0x00aa:
            androidx.appsearch.app.EmbeddingVector[] r4 = r3.getEmbeddingValues()
            if (r4 == 0) goto L_0x00c1
            androidx.appsearch.app.EmbeddingVector[] r2 = r3.getEmbeddingValues()
            if (r2 == 0) goto L_0x003c
            int r3 = r2.length
            if (r0 >= r3) goto L_0x003c
            int r3 = r0 + 1
            java.lang.Object[] r0 = java.util.Arrays.copyOfRange(r2, r0, r3)
            goto L_0x003d
        L_0x00c1:
            androidx.appsearch.app.AppSearchBlobHandle[] r4 = r3.getBlobHandleValues()
            if (r4 == 0) goto L_0x00d8
            androidx.appsearch.app.AppSearchBlobHandle[] r2 = r3.getBlobHandleValues()
            if (r2 == 0) goto L_0x003c
            int r3 = r2.length
            if (r0 >= r3) goto L_0x003c
            int r3 = r0 + 1
            java.lang.Object[] r0 = java.util.Arrays.copyOfRange(r2, r0, r3)
            goto L_0x003d
        L_0x00d8:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Unsupported value type: "
            r7.<init>(r8)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x00ec:
            if (r2 == 0) goto L_0x015b
            int r0 = r6.size()
            r3 = 1
            int r0 = r0 - r3
            if (r7 != r0) goto L_0x00f7
            goto L_0x015b
        L_0x00f7:
            boolean r0 = r2 instanceof androidx.appsearch.safeparcel.GenericDocumentParcel
            if (r0 == 0) goto L_0x0102
            androidx.appsearch.safeparcel.GenericDocumentParcel r2 = (androidx.appsearch.safeparcel.GenericDocumentParcel) r2
            java.util.Map r8 = r2.getPropertyMap()
            goto L_0x0143
        L_0x0102:
            boolean r0 = r2 instanceof androidx.appsearch.safeparcel.PropertyParcel
            if (r0 == 0) goto L_0x0147
            androidx.appsearch.safeparcel.PropertyParcel r2 = (androidx.appsearch.safeparcel.PropertyParcel) r2
            androidx.appsearch.safeparcel.GenericDocumentParcel[] r0 = r2.getDocumentValues()
            if (r0 == 0) goto L_0x0147
            androidx.appsearch.safeparcel.GenericDocumentParcel[] r0 = r2.getDocumentValues()
            r1 = 0
            if (r0 == 0) goto L_0x011f
            int r2 = r0.length
            if (r2 != r3) goto L_0x011f
            r8 = r0[r1]
            java.util.Map r8 = r8.getPropertyMap()
            goto L_0x0143
        L_0x011f:
            if (r0 == 0) goto L_0x0143
            java.util.ArrayList r8 = new java.util.ArrayList
            int r2 = r0.length
            r8.<init>(r2)
            int r2 = r0.length
        L_0x0128:
            if (r1 >= r2) goto L_0x013e
            r4 = r0[r1]
            int r5 = r7 + 1
            java.util.Map r4 = r4.getPropertyMap()
            java.lang.Object r4 = getRawPropertyFromRawDocument(r6, r5, r4)
            if (r4 == 0) goto L_0x013b
            r8.add(r4)
        L_0x013b:
            int r1 = r1 + 1
            goto L_0x0128
        L_0x013e:
            java.lang.Object r6 = flattenAccumulator(r8)
            return r6
        L_0x0143:
            int r7 = r7 + 1
            goto L_0x0006
        L_0x0147:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Failed to apply path to document; no nested value found: "
            r7.<init>(r8)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = "AppSearchGenericDocumen"
            android.util.Log.e(r7, r6)
            return r1
        L_0x015b:
            if (r2 == 0) goto L_0x0168
            boolean r6 = r2 instanceof androidx.appsearch.safeparcel.PropertyParcel
            if (r6 == 0) goto L_0x0168
            androidx.appsearch.safeparcel.PropertyParcel r2 = (androidx.appsearch.safeparcel.PropertyParcel) r2
            java.lang.Object r6 = r2.getValues()
            return r6
        L_0x0168:
            return r2
        L_0x0169:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appsearch.app.GenericDocument.getRawPropertyFromRawDocument(androidx.appsearch.app.PropertyPath, int, java.util.Map):java.lang.Object");
    }

    private static <T> T safeCastProperty(String str, Object obj, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        try {
            return cls.cast(obj);
        } catch (ClassCastException e) {
            Log.w("AppSearchGenericDocumen", "Error casting to requested type for path \"" + str + "\"", e);
            return null;
        }
    }

    private static void warnIfSinglePropertyTooLong(String str, String str2, int i2) {
        if (i2 > 1) {
            Log.w("AppSearchGenericDocumen", C0212a.q(C0212a.u("The value for \"", str2, "\" contains ", i2, " elements. Only the first one will be returned from getProperty"), str, "(). Try getProperty", str, "Array()."));
        }
    }

    public void appendGenericDocumentString(IndentingStringBuilder indentingStringBuilder) {
        Preconditions.checkNotNull(indentingStringBuilder);
        indentingStringBuilder.append("{\n");
        indentingStringBuilder.increaseIndentLevel();
        indentingStringBuilder.append("namespace: \"").append(getNamespace()).append("\",\n");
        indentingStringBuilder.append("id: \"").append(getId()).append("\",\n");
        indentingStringBuilder.append("score: ").append((Object) Integer.valueOf(getScore())).append(",\n");
        indentingStringBuilder.append("schemaType: \"").append(getSchemaType()).append("\",\n");
        List<String> parentTypes = getParentTypes();
        if (parentTypes != null) {
            indentingStringBuilder.append("parentTypes: ").append((Object) parentTypes).append("\n");
        }
        indentingStringBuilder.append("creationTimestampMillis: ").append((Object) Long.valueOf(getCreationTimestampMillis())).append(",\n");
        indentingStringBuilder.append("timeToLiveMillis: ").append((Object) Long.valueOf(getTtlMillis())).append(",\n");
        indentingStringBuilder.append("properties: {\n");
        String[] strArr = (String[]) getPropertyNames().toArray(new String[0]);
        Arrays.sort(strArr);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            Object checkNotNull = Preconditions.checkNotNull(getProperty(strArr[i2]));
            indentingStringBuilder.increaseIndentLevel();
            appendPropertyString(strArr[i2], checkNotNull, indentingStringBuilder);
            if (i2 != strArr.length - 1) {
                indentingStringBuilder.append(",\n");
            }
            indentingStringBuilder.decreaseIndentLevel();
        }
        indentingStringBuilder.append("\n");
        indentingStringBuilder.append("}");
        indentingStringBuilder.decreaseIndentLevel();
        indentingStringBuilder.append("\n");
        indentingStringBuilder.append("}");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GenericDocument)) {
            return false;
        }
        return this.mDocumentParcel.equals(((GenericDocument) obj).mDocumentParcel);
    }

    public long getCreationTimestampMillis() {
        return this.mDocumentParcel.getCreationTimestampMillis();
    }

    public GenericDocumentParcel getDocumentParcel() {
        return this.mDocumentParcel;
    }

    public String getId() {
        return this.mDocumentParcel.getId();
    }

    public String getNamespace() {
        return this.mDocumentParcel.getNamespace();
    }

    @Deprecated
    public List<String> getParentTypes() {
        List<String> parentTypes = this.mDocumentParcel.getParentTypes();
        if (parentTypes == null) {
            return null;
        }
        return Collections.unmodifiableList(parentTypes);
    }

    public Object getProperty(String str) {
        Objects.requireNonNull(str);
        Object rawPropertyFromRawDocument = getRawPropertyFromRawDocument(new PropertyPath(str), 0, this.mDocumentParcel.getPropertyMap());
        if (rawPropertyFromRawDocument instanceof GenericDocumentParcel) {
            return new GenericDocument[]{new GenericDocument((GenericDocumentParcel) rawPropertyFromRawDocument)};
        }
        if (!(rawPropertyFromRawDocument instanceof GenericDocumentParcel[])) {
            return rawPropertyFromRawDocument;
        }
        GenericDocumentParcel[] genericDocumentParcelArr = (GenericDocumentParcel[]) rawPropertyFromRawDocument;
        GenericDocument[] genericDocumentArr = new GenericDocument[genericDocumentParcelArr.length];
        for (int i2 = 0; i2 < genericDocumentParcelArr.length; i2++) {
            GenericDocumentParcel genericDocumentParcel = genericDocumentParcelArr[i2];
            if (genericDocumentParcel == null) {
                Log.e("AppSearchGenericDocumen", "The inner parcel is null at " + i2 + ", for path: " + str);
            } else {
                genericDocumentArr[i2] = new GenericDocument(genericDocumentParcel);
            }
        }
        return genericDocumentArr;
    }

    public boolean getPropertyBoolean(String str) {
        Preconditions.checkNotNull(str);
        boolean[] propertyBooleanArray = getPropertyBooleanArray(str);
        if (propertyBooleanArray == null || propertyBooleanArray.length == 0) {
            return false;
        }
        warnIfSinglePropertyTooLong("Boolean", str, propertyBooleanArray.length);
        return propertyBooleanArray[0];
    }

    public boolean[] getPropertyBooleanArray(String str) {
        Preconditions.checkNotNull(str);
        return (boolean[]) safeCastProperty(str, getProperty(str), boolean[].class);
    }

    public GenericDocument getPropertyDocument(String str) {
        Preconditions.checkNotNull(str);
        GenericDocument[] propertyDocumentArray = getPropertyDocumentArray(str);
        if (propertyDocumentArray == null || propertyDocumentArray.length == 0) {
            return null;
        }
        warnIfSinglePropertyTooLong("Document", str, propertyDocumentArray.length);
        return propertyDocumentArray[0];
    }

    public GenericDocument[] getPropertyDocumentArray(String str) {
        Preconditions.checkNotNull(str);
        return (GenericDocument[]) safeCastProperty(str, getProperty(str), GenericDocument[].class);
    }

    public long getPropertyLong(String str) {
        Preconditions.checkNotNull(str);
        long[] propertyLongArray = getPropertyLongArray(str);
        if (propertyLongArray == null || propertyLongArray.length == 0) {
            return 0;
        }
        warnIfSinglePropertyTooLong("Long", str, propertyLongArray.length);
        return propertyLongArray[0];
    }

    public long[] getPropertyLongArray(String str) {
        Preconditions.checkNotNull(str);
        return (long[]) safeCastProperty(str, getProperty(str), long[].class);
    }

    public Set<String> getPropertyNames() {
        return Collections.unmodifiableSet(this.mDocumentParcel.getPropertyNames());
    }

    public String getPropertyString(String str) {
        Preconditions.checkNotNull(str);
        String[] propertyStringArray = getPropertyStringArray(str);
        if (propertyStringArray == null || propertyStringArray.length == 0) {
            return null;
        }
        warnIfSinglePropertyTooLong("String", str, propertyStringArray.length);
        return propertyStringArray[0];
    }

    public String[] getPropertyStringArray(String str) {
        Preconditions.checkNotNull(str);
        return (String[]) safeCastProperty(str, getProperty(str), String[].class);
    }

    public String getSchemaType() {
        return this.mDocumentParcel.getSchemaType();
    }

    public int getScore() {
        return this.mDocumentParcel.getScore();
    }

    public long getTtlMillis() {
        return this.mDocumentParcel.getTtlMillis();
    }

    public int hashCode() {
        return this.mDocumentParcel.hashCode();
    }

    public <T> T toDocumentClass(Class<T> cls) {
        return toDocumentClass(cls, DocumentClassMappingContext.EMPTY);
    }

    public String toString() {
        IndentingStringBuilder indentingStringBuilder = new IndentingStringBuilder();
        appendGenericDocumentString(indentingStringBuilder);
        return indentingStringBuilder.toString();
    }

    public <T> T toDocumentClass(Class<T> cls, DocumentClassMappingContext documentClassMappingContext) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(documentClassMappingContext);
        return DocumentClassFactoryRegistry.getInstance().getOrCreateFactory(findTargetClassToDeserialize(cls, documentClassMappingContext.getDocumentClassMap(), documentClassMappingContext.getParentTypeMap())).fromGenericDocument(this, documentClassMappingContext);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder<BuilderType extends Builder> {
        private final BuilderType mBuilderTypeInstance;
        private final GenericDocumentParcel.Builder mDocumentParcelBuilder;

        public Builder(String str, String str2, String str3) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(str2);
            Preconditions.checkNotNull(str3);
            this.mBuilderTypeInstance = this;
            this.mDocumentParcelBuilder = new GenericDocumentParcel.Builder(str, str2, str3);
        }

        private void validatePropertyName(String str) {
            if (str.isEmpty()) {
                throw new IllegalArgumentException("Property name cannot be blank.");
            }
        }

        public GenericDocument build() {
            return new GenericDocument(this.mDocumentParcelBuilder.build());
        }

        public BuilderType clearProperty(String str) {
            Preconditions.checkNotNull(str);
            this.mDocumentParcelBuilder.clearProperty(str);
            return this.mBuilderTypeInstance;
        }

        public BuilderType setCreationTimestampMillis(long j2) {
            this.mDocumentParcelBuilder.setCreationTimestampMillis(j2);
            return this.mBuilderTypeInstance;
        }

        @Deprecated
        public BuilderType setParentTypes(List<String> list) {
            this.mDocumentParcelBuilder.setParentTypes(list);
            return this.mBuilderTypeInstance;
        }

        public BuilderType setPropertyBoolean(String str, boolean... zArr) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(zArr);
            validatePropertyName(str);
            this.mDocumentParcelBuilder.putInPropertyMap(str, zArr);
            return this.mBuilderTypeInstance;
        }

        public BuilderType setPropertyBytes(String str, byte[]... bArr) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(bArr);
            validatePropertyName(str);
            int i2 = 0;
            while (i2 < bArr.length) {
                if (bArr[i2] != null) {
                    i2++;
                } else {
                    throw new IllegalArgumentException(C0212a.j(i2, "The byte[] at ", " is null."));
                }
            }
            this.mDocumentParcelBuilder.putInPropertyMap(str, bArr);
            return this.mBuilderTypeInstance;
        }

        public BuilderType setPropertyDocument(String str, GenericDocument... genericDocumentArr) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(genericDocumentArr);
            validatePropertyName(str);
            GenericDocumentParcel[] genericDocumentParcelArr = new GenericDocumentParcel[genericDocumentArr.length];
            int i2 = 0;
            while (i2 < genericDocumentArr.length) {
                GenericDocument genericDocument = genericDocumentArr[i2];
                if (genericDocument != null) {
                    genericDocumentParcelArr[i2] = genericDocument.getDocumentParcel();
                    i2++;
                } else {
                    throw new IllegalArgumentException(C0212a.j(i2, "The document at ", " is null."));
                }
            }
            this.mDocumentParcelBuilder.putInPropertyMap(str, genericDocumentParcelArr);
            return this.mBuilderTypeInstance;
        }

        public BuilderType setPropertyDouble(String str, double... dArr) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(dArr);
            validatePropertyName(str);
            this.mDocumentParcelBuilder.putInPropertyMap(str, dArr);
            return this.mBuilderTypeInstance;
        }

        public BuilderType setPropertyEmbedding(String str, EmbeddingVector... embeddingVectorArr) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(embeddingVectorArr);
            validatePropertyName(str);
            int i2 = 0;
            while (i2 < embeddingVectorArr.length) {
                if (embeddingVectorArr[i2] != null) {
                    i2++;
                } else {
                    throw new IllegalArgumentException(C0212a.j(i2, "The EmbeddingVector at ", " is null."));
                }
            }
            this.mDocumentParcelBuilder.putInPropertyMap(str, embeddingVectorArr);
            return this.mBuilderTypeInstance;
        }

        public BuilderType setPropertyLong(String str, long... jArr) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(jArr);
            validatePropertyName(str);
            this.mDocumentParcelBuilder.putInPropertyMap(str, jArr);
            return this.mBuilderTypeInstance;
        }

        public BuilderType setPropertyString(String str, String... strArr) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(strArr);
            validatePropertyName(str);
            int i2 = 0;
            while (i2 < strArr.length) {
                if (strArr[i2] != null) {
                    i2++;
                } else {
                    throw new IllegalArgumentException(C0212a.j(i2, "The String at ", " is null."));
                }
            }
            this.mDocumentParcelBuilder.putInPropertyMap(str, strArr);
            return this.mBuilderTypeInstance;
        }

        public BuilderType setScore(int i2) {
            if (i2 >= 0) {
                this.mDocumentParcelBuilder.setScore(i2);
                return this.mBuilderTypeInstance;
            }
            throw new IllegalArgumentException("Document score cannot be negative.");
        }

        public BuilderType setTtlMillis(long j2) {
            if (j2 >= 0) {
                this.mDocumentParcelBuilder.setTtlMillis(j2);
                return this.mBuilderTypeInstance;
            }
            throw new IllegalArgumentException("Document ttlMillis cannot be negative.");
        }

        public Builder(GenericDocumentParcel.Builder builder) {
            Objects.requireNonNull(builder);
            this.mDocumentParcelBuilder = builder;
            this.mBuilderTypeInstance = this;
        }

        public Builder(GenericDocument genericDocument) {
            this(new GenericDocumentParcel.Builder(genericDocument.mDocumentParcel));
        }
    }
}
