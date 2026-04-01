package androidx.appsearch.safeparcel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GenericDocumentParcelCreator implements Parcelable.Creator<GenericDocumentParcel> {
    private static Bundle createBundleFromGenericDocumentParcel(GenericDocumentParcel genericDocumentParcel) {
        Bundle bundle = new Bundle();
        bundle.putString("namespace", genericDocumentParcel.getNamespace());
        bundle.putString("id", genericDocumentParcel.getId());
        bundle.putString("schemaType", genericDocumentParcel.getSchemaType());
        bundle.putStringArrayList("parentTypes", (ArrayList) genericDocumentParcel.getParentTypes());
        bundle.putInt("score", genericDocumentParcel.getScore());
        bundle.putLong("creationTimestampMillis", genericDocumentParcel.getCreationTimestampMillis());
        bundle.putLong("ttlMillis", genericDocumentParcel.getTtlMillis());
        Bundle bundle2 = new Bundle();
        List<PropertyParcel> properties = genericDocumentParcel.getProperties();
        for (int i2 = 0; i2 < properties.size(); i2++) {
            PropertyParcel propertyParcel = properties.get(i2);
            bundle2.putParcelable(propertyParcel.getPropertyName(), propertyParcel);
        }
        bundle.putBundle("properties", bundle2);
        return bundle;
    }

    private static GenericDocumentParcel createGenericDocumentParcelFromBundle(Bundle bundle) {
        String string = bundle.getString("namespace");
        String string2 = bundle.getString("id");
        String string3 = bundle.getString("schemaType");
        if (string == null || string2 == null || string3 == null) {
            throw new IllegalArgumentException("GenericDocumentParcel bundle doesn't have namespace, id, or schemaType.");
        }
        GenericDocumentParcel.Builder builder = new GenericDocumentParcel.Builder(string, string2, string3);
        ArrayList<String> stringArrayList = bundle.getStringArrayList("parentTypes");
        if (stringArrayList != null) {
            builder.setParentTypes(stringArrayList);
        }
        builder.setScore(bundle.getInt("score"));
        builder.setCreationTimestampMillis(bundle.getLong("creationTimestampMillis"));
        builder.setTtlMillis(bundle.getLong("ttlMillis"));
        Bundle bundle2 = bundle.getBundle("properties");
        if (bundle2 != null) {
            for (String next : bundle2.keySet()) {
                builder.putInPropertyMap(next, (PropertyParcel) bundle2.getParcelable(next));
            }
        }
        return builder.build();
    }

    public static void writeToParcel(GenericDocumentParcel genericDocumentParcel, Parcel parcel, int i2) {
        parcel.writeBundle(createBundleFromGenericDocumentParcel(genericDocumentParcel));
    }

    public GenericDocumentParcel createFromParcel(Parcel parcel) {
        return createGenericDocumentParcelFromBundle(parcel.readBundle(getClass().getClassLoader()));
    }

    public GenericDocumentParcel[] newArray(int i2) {
        return new GenericDocumentParcel[i2];
    }
}
