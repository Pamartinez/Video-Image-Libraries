package O1;

import Fd.C0744a;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import u1.C0285c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends com.google.android.gms.common.internal.a implements C0285c {

    /* renamed from: A  reason: collision with root package name */
    public final Bundle f553A;
    public final Integer B;
    public final boolean y = true;
    public final C0744a z;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public a(android.content.Context r8, android.os.Looper r9, Fd.C0744a r10, android.os.Bundle r11, u1.g r12, u1.h r13) {
        /*
            r7 = this;
            r3 = 44
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r12
            r6 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r7 = 1
            r0.y = r7
            r0.z = r4
            r0.f553A = r11
            java.lang.Object r7 = r4.f3376i
            java.lang.Integer r7 = (java.lang.Integer) r7
            r0.B = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: O1.a.<init>(android.content.Context, android.os.Looper, Fd.a, android.os.Bundle, u1.g, u1.h):void");
    }

    public final boolean e() {
        return this.y;
    }

    public final int j() {
        return 12451000;
    }

    public final IInterface m(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (queryLocalInterface instanceof c) {
            return (c) queryLocalInterface;
        }
        return new E1.a(iBinder, "com.google.android.gms.signin.internal.ISignInService", 0);
    }

    public final Bundle o() {
        C0744a aVar = this.z;
        boolean equals = this.f1339c.getPackageName().equals((String) aVar.d);
        Bundle bundle = this.f553A;
        if (!equals) {
            bundle.putString("com.google.android.gms.signin.internal.realClientPackageName", (String) aVar.d);
        }
        return bundle;
    }

    public final String q() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    public final String r() {
        return "com.google.android.gms.signin.service.START";
    }
}
