package androidx.appsearch.safeparcel;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appsearch.app.EmbeddingVector;
import androidx.appsearch.safeparcel.PropertyParcel;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GenericDocumentParcel extends AbstractSafeParcelable implements Parcelable {
    public static final Parcelable.Creator<GenericDocumentParcel> CREATOR = new GenericDocumentParcelCreator();
    /* access modifiers changed from: private */
    public final long mCreationTimestampMillis;
    private Integer mHashCode;
    /* access modifiers changed from: private */
    public final String mId;
    /* access modifiers changed from: private */
    public final String mNamespace;
    /* access modifiers changed from: private */
    public final List<String> mParentTypes;
    private final List<PropertyParcel> mProperties;
    /* access modifiers changed from: private */
    public final Map<String, PropertyParcel> mPropertyMap;
    /* access modifiers changed from: private */
    public final String mSchemaType;
    /* access modifiers changed from: private */
    public final int mScore;
    /* access modifiers changed from: private */
    public final long mTtlMillis;

    public GenericDocumentParcel(String str, String str2, String str3, long j2, long j3, int i2, List<PropertyParcel> list, List<String> list2) {
        this(str, str2, str3, j2, j3, i2, list, createPropertyMapFromPropertyArray(list), list2);
    }

    private static Map<String, PropertyParcel> createPropertyMapFromPropertyArray(List<PropertyParcel> list) {
        Objects.requireNonNull(list);
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            PropertyParcel propertyParcel = list.get(i2);
            arrayMap.put(propertyParcel.getPropertyName(), propertyParcel);
        }
        return arrayMap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GenericDocumentParcel)) {
            return false;
        }
        GenericDocumentParcel genericDocumentParcel = (GenericDocumentParcel) obj;
        if (!this.mNamespace.equals(genericDocumentParcel.mNamespace) || !this.mId.equals(genericDocumentParcel.mId) || !this.mSchemaType.equals(genericDocumentParcel.mSchemaType) || this.mTtlMillis != genericDocumentParcel.mTtlMillis || this.mCreationTimestampMillis != genericDocumentParcel.mCreationTimestampMillis || this.mScore != genericDocumentParcel.mScore || !Objects.equals(this.mProperties, genericDocumentParcel.mProperties) || !Objects.equals(this.mPropertyMap, genericDocumentParcel.mPropertyMap) || !Objects.equals(this.mParentTypes, genericDocumentParcel.mParentTypes)) {
            return false;
        }
        return true;
    }

    public long getCreationTimestampMillis() {
        return this.mCreationTimestampMillis;
    }

    public String getId() {
        return this.mId;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public List<String> getParentTypes() {
        return this.mParentTypes;
    }

    public List<PropertyParcel> getProperties() {
        return this.mProperties;
    }

    public Map<String, PropertyParcel> getPropertyMap() {
        return this.mPropertyMap;
    }

    public Set<String> getPropertyNames() {
        return this.mPropertyMap.keySet();
    }

    public String getSchemaType() {
        return this.mSchemaType;
    }

    public int getScore() {
        return this.mScore;
    }

    public long getTtlMillis() {
        return this.mTtlMillis;
    }

    public int hashCode() {
        if (this.mHashCode == null) {
            this.mHashCode = Integer.valueOf(Objects.hash(new Object[]{this.mNamespace, this.mId, this.mSchemaType, Long.valueOf(this.mTtlMillis), Integer.valueOf(this.mScore), Long.valueOf(this.mCreationTimestampMillis), Integer.valueOf(Objects.hashCode(this.mProperties)), Integer.valueOf(Objects.hashCode(this.mPropertyMap)), Integer.valueOf(Objects.hashCode(this.mParentTypes))}));
        }
        return this.mHashCode.intValue();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        GenericDocumentParcelCreator.writeToParcel(this, parcel, i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private long mCreationTimestampMillis;
        private String mId;
        private String mNamespace;
        private List<String> mParentTypes;
        private Map<String, PropertyParcel> mPropertyMap;
        private String mSchemaType;
        private int mScore;
        private long mTtlMillis;

        public Builder(String str, String str2, String str3) {
            Objects.requireNonNull(str);
            this.mNamespace = str;
            Objects.requireNonNull(str2);
            this.mId = str2;
            Objects.requireNonNull(str3);
            this.mSchemaType = str3;
            this.mCreationTimestampMillis = -1;
            this.mTtlMillis = 0;
            this.mScore = 0;
            this.mPropertyMap = new ArrayMap();
        }

        public GenericDocumentParcel build() {
            if (this.mCreationTimestampMillis == -1) {
                this.mCreationTimestampMillis = System.currentTimeMillis();
            }
            return new GenericDocumentParcel(this.mNamespace, this.mId, this.mSchemaType, this.mCreationTimestampMillis, this.mTtlMillis, this.mScore, new ArrayList(this.mPropertyMap.values()), this.mParentTypes);
        }

        public Builder clearProperty(String str) {
            Objects.requireNonNull(str);
            this.mPropertyMap.remove(str);
            return this;
        }

        public Builder putInPropertyMap(String str, String[] strArr) {
            putInPropertyMap(str, new PropertyParcel.Builder(str).setStringValues(strArr).build());
            return this;
        }

        public Builder setCreationTimestampMillis(long j2) {
            this.mCreationTimestampMillis = j2;
            return this;
        }

        public Builder setParentTypes(List<String> list) {
            if (list == null) {
                this.mParentTypes = null;
                return this;
            }
            this.mParentTypes = new ArrayList(list);
            return this;
        }

        public Builder setScore(int i2) {
            this.mScore = i2;
            return this;
        }

        public Builder setTtlMillis(long j2) {
            if (j2 >= 0) {
                this.mTtlMillis = j2;
                return this;
            }
            throw new IllegalArgumentException("Document ttlMillis cannot be negative.");
        }

        public Builder putInPropertyMap(String str, boolean[] zArr) {
            putInPropertyMap(str, new PropertyParcel.Builder(str).setBooleanValues(zArr).build());
            return this;
        }

        public Builder putInPropertyMap(String str, double[] dArr) {
            putInPropertyMap(str, new PropertyParcel.Builder(str).setDoubleValues(dArr).build());
            return this;
        }

        public Builder(GenericDocumentParcel genericDocumentParcel) {
            Objects.requireNonNull(genericDocumentParcel);
            this.mNamespace = genericDocumentParcel.mNamespace;
            this.mId = genericDocumentParcel.mId;
            this.mSchemaType = genericDocumentParcel.mSchemaType;
            this.mCreationTimestampMillis = genericDocumentParcel.mCreationTimestampMillis;
            this.mTtlMillis = genericDocumentParcel.mTtlMillis;
            this.mScore = genericDocumentParcel.mScore;
            Map access$600 = genericDocumentParcel.mPropertyMap;
            this.mPropertyMap = new ArrayMap(access$600.size());
            for (PropertyParcel propertyParcel : access$600.values()) {
                this.mPropertyMap.put(propertyParcel.getPropertyName(), propertyParcel);
            }
            this.mParentTypes = genericDocumentParcel.mParentTypes;
        }

        public Builder putInPropertyMap(String str, long[] jArr) {
            putInPropertyMap(str, new PropertyParcel.Builder(str).setLongValues(jArr).build());
            return this;
        }

        public Builder putInPropertyMap(String str, byte[][] bArr) {
            putInPropertyMap(str, new PropertyParcel.Builder(str).setBytesValues(bArr).build());
            return this;
        }

        public Builder putInPropertyMap(String str, GenericDocumentParcel[] genericDocumentParcelArr) {
            putInPropertyMap(str, new PropertyParcel.Builder(str).setDocumentValues(genericDocumentParcelArr).build());
            return this;
        }

        public Builder putInPropertyMap(String str, EmbeddingVector[] embeddingVectorArr) {
            putInPropertyMap(str, new PropertyParcel.Builder(str).setEmbeddingValues(embeddingVectorArr).build());
            return this;
        }

        public Builder putInPropertyMap(String str, PropertyParcel propertyParcel) {
            Objects.requireNonNull(propertyParcel);
            this.mPropertyMap.put(str, propertyParcel);
            return this;
        }
    }

    public GenericDocumentParcel(String str, String str2, String str3, long j2, long j3, int i2, List<PropertyParcel> list, Map<String, PropertyParcel> map, List<String> list2) {
        Objects.requireNonNull(str);
        this.mNamespace = str;
        Objects.requireNonNull(str2);
        this.mId = str2;
        Objects.requireNonNull(str3);
        this.mSchemaType = str3;
        this.mCreationTimestampMillis = j2;
        this.mTtlMillis = j3;
        this.mScore = i2;
        Objects.requireNonNull(list);
        this.mProperties = list;
        Objects.requireNonNull(map);
        this.mPropertyMap = map;
        this.mParentTypes = list2;
    }
}
