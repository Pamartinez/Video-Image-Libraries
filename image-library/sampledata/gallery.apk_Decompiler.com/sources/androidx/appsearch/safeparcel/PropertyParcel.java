package androidx.appsearch.safeparcel;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appsearch.app.AppSearchBlobHandle;
import androidx.appsearch.app.EmbeddingVector;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PropertyParcel extends AbstractSafeParcelable implements Parcelable {
    public static final Parcelable.Creator<PropertyParcel> CREATOR = new PropertyParcelCreator();
    private final AppSearchBlobHandle[] mBlobHandleValues;
    private final boolean[] mBooleanValues;
    private final byte[][] mBytesValues;
    private final GenericDocumentParcel[] mDocumentValues;
    private final double[] mDoubleValues;
    private final EmbeddingVector[] mEmbeddingValues;
    private Integer mHashCode;
    private final long[] mLongValues;
    private final String mPropertyName;
    private final String[] mStringValues;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private AppSearchBlobHandle[] mBlobHandleValues;
        private boolean[] mBooleanValues;
        private byte[][] mBytesValues;
        private GenericDocumentParcel[] mDocumentValues;
        private double[] mDoubleValues;
        private EmbeddingVector[] mEmbeddingValues;
        private long[] mLongValues;
        private String mPropertyName;
        private String[] mStringValues;

        public Builder(String str) {
            Objects.requireNonNull(str);
            this.mPropertyName = str;
        }

        public PropertyParcel build() {
            return new PropertyParcel(this.mPropertyName, this.mStringValues, this.mLongValues, this.mDoubleValues, this.mBooleanValues, this.mBytesValues, this.mDocumentValues, this.mEmbeddingValues, this.mBlobHandleValues);
        }

        public Builder setBooleanValues(boolean[] zArr) {
            Objects.requireNonNull(zArr);
            this.mBooleanValues = zArr;
            return this;
        }

        public Builder setBytesValues(byte[][] bArr) {
            Objects.requireNonNull(bArr);
            this.mBytesValues = bArr;
            return this;
        }

        public Builder setDocumentValues(GenericDocumentParcel[] genericDocumentParcelArr) {
            Objects.requireNonNull(genericDocumentParcelArr);
            this.mDocumentValues = genericDocumentParcelArr;
            return this;
        }

        public Builder setDoubleValues(double[] dArr) {
            Objects.requireNonNull(dArr);
            this.mDoubleValues = dArr;
            return this;
        }

        public Builder setEmbeddingValues(EmbeddingVector[] embeddingVectorArr) {
            Objects.requireNonNull(embeddingVectorArr);
            this.mEmbeddingValues = embeddingVectorArr;
            return this;
        }

        public Builder setLongValues(long[] jArr) {
            Objects.requireNonNull(jArr);
            this.mLongValues = jArr;
            return this;
        }

        public Builder setStringValues(String[] strArr) {
            Objects.requireNonNull(strArr);
            this.mStringValues = strArr;
            return this;
        }
    }

    public PropertyParcel(String str, String[] strArr, long[] jArr, double[] dArr, boolean[] zArr, byte[][] bArr, GenericDocumentParcel[] genericDocumentParcelArr, EmbeddingVector[] embeddingVectorArr, AppSearchBlobHandle[] appSearchBlobHandleArr) {
        Objects.requireNonNull(str);
        this.mPropertyName = str;
        this.mStringValues = strArr;
        this.mLongValues = jArr;
        this.mDoubleValues = dArr;
        this.mBooleanValues = zArr;
        this.mBytesValues = bArr;
        this.mDocumentValues = genericDocumentParcelArr;
        this.mEmbeddingValues = embeddingVectorArr;
        this.mBlobHandleValues = appSearchBlobHandleArr;
        checkOnlyOneArrayCanBeSet();
    }

    private void checkOnlyOneArrayCanBeSet() {
        int i2;
        if (this.mStringValues != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (this.mLongValues != null) {
            i2++;
        }
        if (this.mDoubleValues != null) {
            i2++;
        }
        if (this.mBooleanValues != null) {
            i2++;
        }
        if (this.mBytesValues != null) {
            i2++;
        }
        if (this.mDocumentValues != null) {
            i2++;
        }
        if (this.mEmbeddingValues != null) {
            i2++;
        }
        if (this.mBlobHandleValues != null) {
            i2++;
        }
        if (i2 == 0 || i2 > 1) {
            throw new IllegalArgumentException("One and only one type array can be set in PropertyParcel");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PropertyParcel)) {
            return false;
        }
        PropertyParcel propertyParcel = (PropertyParcel) obj;
        if (this.mPropertyName.equals(propertyParcel.mPropertyName) && Arrays.equals(this.mStringValues, propertyParcel.mStringValues) && Arrays.equals(this.mLongValues, propertyParcel.mLongValues) && Arrays.equals(this.mDoubleValues, propertyParcel.mDoubleValues) && Arrays.equals(this.mBooleanValues, propertyParcel.mBooleanValues) && Arrays.deepEquals(this.mBytesValues, propertyParcel.mBytesValues) && Arrays.equals(this.mDocumentValues, propertyParcel.mDocumentValues) && Arrays.deepEquals(this.mEmbeddingValues, propertyParcel.mEmbeddingValues) && Arrays.deepEquals(this.mBlobHandleValues, propertyParcel.mBlobHandleValues)) {
            return true;
        }
        return false;
    }

    public AppSearchBlobHandle[] getBlobHandleValues() {
        return this.mBlobHandleValues;
    }

    public boolean[] getBooleanValues() {
        return this.mBooleanValues;
    }

    public byte[][] getBytesValues() {
        return this.mBytesValues;
    }

    public GenericDocumentParcel[] getDocumentValues() {
        return this.mDocumentValues;
    }

    public double[] getDoubleValues() {
        return this.mDoubleValues;
    }

    public EmbeddingVector[] getEmbeddingValues() {
        return this.mEmbeddingValues;
    }

    public long[] getLongValues() {
        return this.mLongValues;
    }

    public String getPropertyName() {
        return this.mPropertyName;
    }

    public String[] getStringValues() {
        return this.mStringValues;
    }

    public Object getValues() {
        String[] strArr = this.mStringValues;
        if (strArr != null) {
            return strArr;
        }
        long[] jArr = this.mLongValues;
        if (jArr != null) {
            return jArr;
        }
        double[] dArr = this.mDoubleValues;
        if (dArr != null) {
            return dArr;
        }
        boolean[] zArr = this.mBooleanValues;
        if (zArr != null) {
            return zArr;
        }
        byte[][] bArr = this.mBytesValues;
        if (bArr != null) {
            return bArr;
        }
        GenericDocumentParcel[] genericDocumentParcelArr = this.mDocumentValues;
        if (genericDocumentParcelArr != null) {
            return genericDocumentParcelArr;
        }
        EmbeddingVector[] embeddingVectorArr = this.mEmbeddingValues;
        if (embeddingVectorArr != null) {
            return embeddingVectorArr;
        }
        AppSearchBlobHandle[] appSearchBlobHandleArr = this.mBlobHandleValues;
        if (appSearchBlobHandleArr != null) {
            return appSearchBlobHandleArr;
        }
        return null;
    }

    public int hashCode() {
        int i2;
        if (this.mHashCode == null) {
            String[] strArr = this.mStringValues;
            if (strArr != null) {
                i2 = Arrays.hashCode(strArr);
            } else {
                long[] jArr = this.mLongValues;
                if (jArr != null) {
                    i2 = Arrays.hashCode(jArr);
                } else {
                    double[] dArr = this.mDoubleValues;
                    if (dArr != null) {
                        i2 = Arrays.hashCode(dArr);
                    } else {
                        boolean[] zArr = this.mBooleanValues;
                        if (zArr != null) {
                            i2 = Arrays.hashCode(zArr);
                        } else {
                            byte[][] bArr = this.mBytesValues;
                            if (bArr != null) {
                                i2 = Arrays.deepHashCode(bArr);
                            } else {
                                GenericDocumentParcel[] genericDocumentParcelArr = this.mDocumentValues;
                                if (genericDocumentParcelArr != null) {
                                    i2 = Arrays.hashCode(genericDocumentParcelArr);
                                } else {
                                    EmbeddingVector[] embeddingVectorArr = this.mEmbeddingValues;
                                    if (embeddingVectorArr != null) {
                                        i2 = Arrays.deepHashCode(embeddingVectorArr);
                                    } else {
                                        AppSearchBlobHandle[] appSearchBlobHandleArr = this.mBlobHandleValues;
                                        if (appSearchBlobHandleArr != null) {
                                            i2 = Arrays.deepHashCode(appSearchBlobHandleArr);
                                        } else {
                                            i2 = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            this.mHashCode = Integer.valueOf(Objects.hash(new Object[]{this.mPropertyName, Integer.valueOf(i2)}));
        }
        return this.mHashCode.intValue();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        PropertyParcelCreator.writeToParcel(this, parcel, i2);
    }
}
