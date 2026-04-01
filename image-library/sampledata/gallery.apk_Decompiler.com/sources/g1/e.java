package G1;

import P1.c;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends Binder implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ c f285a;

    public e(c cVar) {
        this.f285a = cVar;
        attachInterface(this, "com.google.android.gms.location.internal.ILocationStatusCallback");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: android.os.Parcelable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) {
        /*
            r2 = this;
            r0 = 16777215(0xffffff, float:2.3509886E-38)
            r1 = 1
            if (r3 <= r0) goto L_0x000d
            boolean r5 = super.onTransact(r3, r4, r5, r6)
            if (r5 == 0) goto L_0x0014
            return r1
        L_0x000d:
            java.lang.String r5 = r2.getInterfaceDescriptor()
            r4.enforceInterface(r5)
        L_0x0014:
            if (r3 != r1) goto L_0x0083
            android.os.Parcelable$Creator<com.google.android.gms.common.api.Status> r3 = com.google.android.gms.common.api.Status.CREATOR
            int r5 = G1.d.f284a
            int r5 = r4.readInt()
            r6 = 0
            if (r5 != 0) goto L_0x0023
            r3 = r6
            goto L_0x0029
        L_0x0023:
            java.lang.Object r3 = r3.createFromParcel(r4)
            android.os.Parcelable r3 = (android.os.Parcelable) r3
        L_0x0029:
            com.google.android.gms.common.api.Status r3 = (com.google.android.gms.common.api.Status) r3
            android.os.Parcelable$Creator r5 = android.location.Location.CREATOR
            int r0 = r4.readInt()
            if (r0 != 0) goto L_0x0034
            goto L_0x003b
        L_0x0034:
            java.lang.Object r5 = r5.createFromParcel(r4)
            r6 = r5
            android.os.Parcelable r6 = (android.os.Parcelable) r6
        L_0x003b:
            android.location.Location r6 = (android.location.Location) r6
            int r4 = r4.dataAvail()
            if (r4 > 0) goto L_0x0077
            P1.c r2 = r2.f285a
            int r4 = r3.d
            if (r4 > 0) goto L_0x004d
            r2.a(r6)
            return r1
        L_0x004d:
            android.app.PendingIntent r4 = r3.f
            if (r4 == 0) goto L_0x0057
            u1.j r4 = new u1.j
            r4.<init>(r3)
            goto L_0x005c
        L_0x0057:
            u1.d r4 = new u1.d
            r4.<init>(r3)
        L_0x005c:
            P1.h r2 = r2.f571a
            r2.getClass()
            java.lang.Object r3 = r2.b
            monitor-enter(r3)
            r2.n()     // Catch:{ all -> 0x0074 }
            r2.f575a = r1     // Catch:{ all -> 0x0074 }
            r2.e = r4     // Catch:{ all -> 0x0074 }
            monitor-exit(r3)     // Catch:{ all -> 0x0074 }
            java.lang.Object r3 = r2.d
            Kd.a r3 = (Kd.a) r3
            r3.i(r2)
            return r1
        L_0x0074:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0074 }
            throw r2
        L_0x0077:
            android.os.BadParcelableException r2 = new android.os.BadParcelableException
            java.lang.String r3 = "Parcel data not fully consumed, unread size: "
            java.lang.String r3 = c0.C0086a.i(r4, r3)
            r2.<init>(r3)
            throw r2
        L_0x0083:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: G1.e.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }

    public final IBinder asBinder() {
        return this;
    }
}
