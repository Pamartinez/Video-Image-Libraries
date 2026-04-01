package Dd;

import android.util.Log;

/* renamed from: Dd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0730a extends RuntimeException {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0730a(java.lang.String r3, android.os.Parcel r4) {
        /*
            r2 = this;
            int r0 = r4.dataPosition()
            int r4 = r4.dataSize()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r3 = " Parcel: pos="
            r1.append(r3)
            r1.append(r0)
            java.lang.String r3 = " size="
            r1.append(r3)
            r1.append(r4)
            java.lang.String r3 = r1.toString()
            r2.<init>(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Dd.C0730a.<init>(java.lang.String, android.os.Parcel):void");
    }

    public C0730a(String str, Exception exc) {
        super(str);
        Log.e("SharedMemoryException", str, exc);
    }

    public C0730a() {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }
}
