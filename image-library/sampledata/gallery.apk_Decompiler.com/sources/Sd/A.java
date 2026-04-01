package Sd;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A extends w {
    public static final z[] f = z.values();
    public final z e;

    public A(Bundle bundle) {
        super(bundle);
        int i2 = bundle.getInt("SYNC_SETTING", 0);
        z[] zVarArr = f;
        if (i2 < zVarArr.length) {
            this.e = zVarArr[i2];
        } else {
            this.e = z.Unknown;
        }
    }
}
