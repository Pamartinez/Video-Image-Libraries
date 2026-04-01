package Sd;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends w {
    public static final B[] f = B.values();
    public final B e;

    public C(Bundle bundle) {
        super(bundle);
        int i2 = bundle.getInt("SYNC_STATUS", 0);
        B[] bArr = f;
        if (i2 < bArr.length) {
            this.e = bArr[i2];
        } else {
            this.e = B.Unknown;
        }
    }
}
