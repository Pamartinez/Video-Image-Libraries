package androidx.appsearch.util;

import android.os.Bundle;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BundleUtil {
    public static Bundle deepCopy(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeBundle(bundle);
            byte[] marshall = obtain.marshall();
            obtain.unmarshall(marshall, 0, marshall.length);
            obtain.setDataPosition(0);
            return obtain.readBundle(BundleUtil.class.getClassLoader());
        } finally {
            obtain.recycle();
        }
    }
}
