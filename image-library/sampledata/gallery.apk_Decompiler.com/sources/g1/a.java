package G1;

import v1.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements h {
    public static final /* synthetic */ a d = new Object();

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.os.Parcelable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void accept(java.lang.Object r9, java.lang.Object r10) {
        /*
            r8 = this;
            G1.f r9 = (G1.f) r9
            P1.c r10 = (P1.c) r10
            t1.c[] r8 = r9.k()
            java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
            r1 = 0
            r2 = 0
            if (r8 != 0) goto L_0x0010
            goto L_0x008f
        L_0x0010:
            int r3 = r8.length
            r4 = r1
        L_0x0012:
            if (r4 >= r3) goto L_0x0024
            r5 = r8[r4]
            java.lang.String r6 = "get_last_location_with_request"
            java.lang.String r7 = r5.d
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0021
            goto L_0x0025
        L_0x0021:
            int r4 = r4 + 1
            goto L_0x0012
        L_0x0024:
            r5 = r2
        L_0x0025:
            if (r5 != 0) goto L_0x0028
            goto L_0x008f
        L_0x0028:
            long r3 = r5.b()
            r5 = 1
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 < 0) goto L_0x008f
            android.os.IInterface r8 = r9.p()
            G1.g r8 = (G1.g) r8
            G1.e r9 = new G1.e
            r9.<init>(r10)
            android.os.Parcel r10 = android.os.Parcel.obtain()
            r10.writeInterfaceToken(r0)
            int r0 = G1.d.f284a
            r0 = 1
            r10.writeInt(r0)
            r2 = 20293(0x4f45, float:2.8437E-41)
            int r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.W(r10, r2)
            r3 = 8
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(r0, r3, r10)
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r10.writeLong(r3)
            r0 = 2
            r3 = 4
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(r0, r3, r10)
            r10.writeInt(r1)
            r0 = 3
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.Y(r0, r3, r10)
            r10.writeInt(r1)
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.X(r10, r2)
            r10.writeStrongBinder(r9)
            android.os.Parcel r9 = android.os.Parcel.obtain()
            android.os.IBinder r8 = r8.f287a     // Catch:{ all -> 0x0087 }
            r0 = 82
            r8.transact(r0, r10, r9, r1)     // Catch:{ all -> 0x0087 }
            r9.readException()     // Catch:{ all -> 0x0087 }
            r10.recycle()
            r9.recycle()
            return
        L_0x0087:
            r8 = move-exception
            r10.recycle()
            r9.recycle()
            throw r8
        L_0x008f:
            android.os.IInterface r8 = r9.p()
            G1.g r8 = (G1.g) r8
            android.os.Parcel r9 = android.os.Parcel.obtain()
            r9.writeInterfaceToken(r0)
            android.os.Parcel r0 = android.os.Parcel.obtain()
            android.os.IBinder r8 = r8.f287a     // Catch:{ RuntimeException -> 0x00c9 }
            r3 = 7
            r8.transact(r3, r9, r0, r1)     // Catch:{ RuntimeException -> 0x00c9 }
            r0.readException()     // Catch:{ RuntimeException -> 0x00c9 }
            r9.recycle()
            android.os.Parcelable$Creator r8 = android.location.Location.CREATOR
            int r9 = G1.d.f284a
            int r9 = r0.readInt()
            if (r9 != 0) goto L_0x00b7
            goto L_0x00be
        L_0x00b7:
            java.lang.Object r8 = r8.createFromParcel(r0)
            r2 = r8
            android.os.Parcelable r2 = (android.os.Parcelable) r2
        L_0x00be:
            android.location.Location r2 = (android.location.Location) r2
            r0.recycle()
            r10.a(r2)
            return
        L_0x00c7:
            r8 = move-exception
            goto L_0x00ce
        L_0x00c9:
            r8 = move-exception
            r0.recycle()     // Catch:{ all -> 0x00c7 }
            throw r8     // Catch:{ all -> 0x00c7 }
        L_0x00ce:
            r9.recycle()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: G1.a.accept(java.lang.Object, java.lang.Object):void");
    }
}
