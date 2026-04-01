package Rd;

import N2.t;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends t {
    public final ParcelFileDescriptor g;

    public a(int i2, int i7, String str, ParcelFileDescriptor parcelFileDescriptor) {
        super(i2, i7, str);
        this.g = parcelFileDescriptor;
    }

    public static a j(Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) {
        t b = t.b(bundle);
        if (bundle != null) {
            bundle.getString("filterId", (String) null);
        }
        return new a(b.d, b.e, (String) b.f, parcelFileDescriptor);
    }

    public static a k(Exception exc) {
        return new a(2, 90000000, "There is an exception, please check  { " + exc.getMessage() + "}", (ParcelFileDescriptor) null);
    }
}
