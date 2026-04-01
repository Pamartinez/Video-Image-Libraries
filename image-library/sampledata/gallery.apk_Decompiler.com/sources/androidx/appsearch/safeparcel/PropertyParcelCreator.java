package androidx.appsearch.safeparcel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appsearch.app.EmbeddingVector;
import androidx.appsearch.safeparcel.PropertyParcel;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PropertyParcelCreator implements Parcelable.Creator<PropertyParcel> {
    private static Bundle createBundleFromPropertyParcel(PropertyParcel propertyParcel) {
        Objects.requireNonNull(propertyParcel);
        Bundle bundle = new Bundle();
        bundle.putString("propertyName", propertyParcel.getPropertyName());
        String[] stringValues = propertyParcel.getStringValues();
        long[] longValues = propertyParcel.getLongValues();
        double[] doubleValues = propertyParcel.getDoubleValues();
        boolean[] booleanValues = propertyParcel.getBooleanValues();
        byte[][] bytesValues = propertyParcel.getBytesValues();
        GenericDocumentParcel[] documentValues = propertyParcel.getDocumentValues();
        EmbeddingVector[] embeddingValues = propertyParcel.getEmbeddingValues();
        if (stringValues != null) {
            bundle.putStringArray("stringArray", stringValues);
            return bundle;
        } else if (longValues != null) {
            bundle.putLongArray("longArray", longValues);
            return bundle;
        } else if (doubleValues != null) {
            bundle.putDoubleArray("doubleArray", doubleValues);
            return bundle;
        } else if (booleanValues != null) {
            bundle.putBooleanArray("booleanArray", booleanValues);
            return bundle;
        } else {
            int i2 = 0;
            if (bytesValues != null) {
                ArrayList arrayList = new ArrayList(bytesValues.length);
                while (i2 < bytesValues.length) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putByteArray("byteArray", bytesValues[i2]);
                    arrayList.add(bundle2);
                    i2++;
                }
                bundle.putParcelableArrayList("bytesArray", arrayList);
                return bundle;
            } else if (documentValues != null) {
                bundle.putParcelableArray("docArray", documentValues);
                return bundle;
            } else {
                if (embeddingValues != null) {
                    ArrayList arrayList2 = new ArrayList(embeddingValues.length);
                    while (i2 < embeddingValues.length) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putFloatArray("embeddingValue", embeddingValues[i2].getValues());
                        bundle3.putString("embeddingModelSignature", embeddingValues[i2].getModelSignature());
                        arrayList2.add(bundle3);
                        i2++;
                    }
                    bundle.putParcelableArrayList("embeddingArray", arrayList2);
                }
                return bundle;
            }
        }
    }

    private static PropertyParcel createPropertyParcelFromBundle(Bundle bundle) {
        byte[] byteArray;
        Objects.requireNonNull(bundle);
        String string = bundle.getString("propertyName");
        Objects.requireNonNull(string);
        PropertyParcel.Builder builder = new PropertyParcel.Builder(string);
        String[] stringArray = bundle.getStringArray("stringArray");
        long[] longArray = bundle.getLongArray("longArray");
        double[] doubleArray = bundle.getDoubleArray("doubleArray");
        boolean[] booleanArray = bundle.getBooleanArray("booleanArray");
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("bytesArray");
        Parcelable[] parcelableArray = bundle.getParcelableArray("docArray");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("embeddingArray");
        if (stringArray != null) {
            builder.setStringValues(stringArray);
        } else if (longArray != null) {
            builder.setLongValues(longArray);
        } else if (doubleArray != null) {
            builder.setDoubleValues(doubleArray);
        } else if (booleanArray != null) {
            builder.setBooleanValues(booleanArray);
        } else {
            int i2 = 0;
            if (parcelableArrayList != null) {
                byte[][] bArr = new byte[parcelableArrayList.size()][];
                while (i2 < parcelableArrayList.size()) {
                    Bundle bundle2 = (Bundle) parcelableArrayList.get(i2);
                    if (!(bundle2 == null || (byteArray = bundle2.getByteArray("byteArray")) == null)) {
                        bArr[i2] = byteArray;
                    }
                    i2++;
                }
                builder.setBytesValues(bArr);
            } else if (parcelableArray != null) {
                GenericDocumentParcel[] genericDocumentParcelArr = new GenericDocumentParcel[parcelableArray.length];
                System.arraycopy(parcelableArray, 0, genericDocumentParcelArr, 0, parcelableArray.length);
                builder.setDocumentValues(genericDocumentParcelArr);
            } else if (parcelableArrayList2 != null) {
                EmbeddingVector[] embeddingVectorArr = new EmbeddingVector[parcelableArrayList2.size()];
                while (i2 < parcelableArrayList2.size()) {
                    Bundle bundle3 = (Bundle) parcelableArrayList2.get(i2);
                    if (bundle3 != null) {
                        float[] floatArray = bundle3.getFloatArray("embeddingValue");
                        String string2 = bundle3.getString("embeddingModelSignature");
                        if (!(floatArray == null || string2 == null)) {
                            embeddingVectorArr[i2] = new EmbeddingVector(floatArray, string2);
                        }
                    }
                    i2++;
                }
                builder.setEmbeddingValues(embeddingVectorArr);
            } else {
                throw new IllegalArgumentException("property bundle passed in doesn't have any value set.");
            }
        }
        return builder.build();
    }

    public static void writeToParcel(PropertyParcel propertyParcel, Parcel parcel, int i2) {
        parcel.writeBundle(createBundleFromPropertyParcel(propertyParcel));
    }

    public PropertyParcel createFromParcel(Parcel parcel) {
        return createPropertyParcelFromBundle(parcel.readBundle(getClass().getClassLoader()));
    }

    public PropertyParcel[] newArray(int i2) {
        return new PropertyParcel[i2];
    }
}
