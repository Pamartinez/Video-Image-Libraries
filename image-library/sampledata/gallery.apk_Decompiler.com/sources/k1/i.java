package K1;

import F1.a;
import O3.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends a {
    public final /* synthetic */ int b = 3;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f382c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(b bVar) {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter", 1);
        this.f382c = bVar;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.os.IInterface] */
    /* JADX WARNING: type inference failed for: r2v1, types: [E1.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean c(int r5, android.os.Parcel r6, android.os.Parcel r7) {
        /*
            r4 = this;
            int r0 = r4.b
            switch(r0) {
                case 0: goto L_0x017f;
                case 1: goto L_0x0135;
                case 2: goto L_0x00e7;
                case 3: goto L_0x009b;
                case 4: goto L_0x0072;
                case 5: goto L_0x003f;
                case 6: goto L_0x0020;
                default: goto L_0x0005;
            }
        L_0x0005:
            r0 = 1
            if (r5 != r0) goto L_0x001e
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r5 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r5 = H1.d.a(r6, r5)
            com.google.android.gms.maps.model.LatLng r5 = (com.google.android.gms.maps.model.LatLng) r5
            H1.d.b(r6)
            java.lang.Object r4 = r4.f382c
            K1.c r4 = (K1.c) r4
            r4.a(r5)
            r7.writeNoException()
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            return r0
        L_0x0020:
            r6 = 1
            if (r5 != r6) goto L_0x003d
            java.lang.Object r4 = r4.f382c
            U3.a r4 = (U3.a) r4
            int r5 = r4.d
            java.lang.Object r4 = r4.e
            switch(r5) {
                case 21: goto L_0x0034;
                default: goto L_0x002e;
            }
        L_0x002e:
            com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer r4 = (com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer) r4
            r4.lambda$setSnapShotCallback$1()
            goto L_0x0039
        L_0x0034:
            com.samsung.android.gallery.module.map.abstraction.MapContainer$OnCameraIdleListener r4 = (com.samsung.android.gallery.module.map.abstraction.MapContainer.OnCameraIdleListener) r4
            r4.onListen()
        L_0x0039:
            r7.writeNoException()
            goto L_0x003e
        L_0x003d:
            r6 = 0
        L_0x003e:
            return r6
        L_0x003f:
            java.lang.Object r4 = r4.f382c
            U3.a r4 = (U3.a) r4
            r0 = 1
            if (r5 == r0) goto L_0x0060
            r1 = 2
            if (r5 == r1) goto L_0x004b
            r0 = 0
            goto L_0x0071
        L_0x004b:
            android.os.IBinder r5 = r6.readStrongBinder()
            C1.a r5 = C1.b.d(r5)
            H1.d.b(r6)
            java.lang.Object r5 = C1.b.e(r5)
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            r4.b(r5)
            goto L_0x006e
        L_0x0060:
            android.os.Parcelable$Creator r5 = android.graphics.Bitmap.CREATOR
            android.os.Parcelable r5 = H1.d.a(r6, r5)
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            H1.d.b(r6)
            r4.b(r5)
        L_0x006e:
            r7.writeNoException()
        L_0x0071:
            return r0
        L_0x0072:
            r6 = 1
            if (r5 != r6) goto L_0x0099
            java.lang.Object r4 = r4.f382c
            O3.b r4 = (O3.b) r4
            int r5 = r4.d
            switch(r5) {
                case 15: goto L_0x008a;
                default: goto L_0x007e;
            }
        L_0x007e:
            java.lang.Object r5 = r4.f
            com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer r5 = (com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer) r5
            java.lang.Object r4 = r4.e
            K1.e r4 = (K1.e) r4
            r5.lambda$onMapReadyAgain$2(r4)
            goto L_0x0095
        L_0x008a:
            java.lang.Object r5 = r4.f
            com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer r5 = (com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer) r5
            java.lang.Object r4 = r4.e
            com.samsung.android.gallery.module.map.abstraction.MapContainer$OnSnapshotReadyListener r4 = (com.samsung.android.gallery.module.map.abstraction.MapContainer.OnSnapshotReadyListener) r4
            r5.lambda$setSnapShotCallback$0(r4)
        L_0x0095:
            r7.writeNoException()
            goto L_0x009a
        L_0x0099:
            r6 = 0
        L_0x009a:
            return r6
        L_0x009b:
            java.lang.Object r4 = r4.f382c
            K1.b r4 = (K1.b) r4
            r0 = 1
            if (r5 == r0) goto L_0x00c7
            r1 = 2
            if (r5 == r1) goto L_0x00a7
            r0 = 0
            goto L_0x00e6
        L_0x00a7:
            android.os.IBinder r5 = r6.readStrongBinder()
            H1.c r5 = H1.b.d(r5)
            H1.d.b(r6)
            M1.b r6 = new M1.b
            r6.<init>(r5)
            android.view.View r4 = r4.getInfoContents(r6)
            C1.b r5 = new C1.b
            r5.<init>(r4)
            r7.writeNoException()
            H1.d.d(r7, r5)
            goto L_0x00e6
        L_0x00c7:
            android.os.IBinder r5 = r6.readStrongBinder()
            H1.c r5 = H1.b.d(r5)
            H1.d.b(r6)
            M1.b r6 = new M1.b
            r6.<init>(r5)
            android.view.View r4 = r4.getInfoWindow(r6)
            C1.b r5 = new C1.b
            r5.<init>(r4)
            r7.writeNoException()
            H1.d.d(r7, r5)
        L_0x00e6:
            return r0
        L_0x00e7:
            java.lang.Object r4 = r4.f382c
            K1.d r4 = (K1.d) r4
            r0 = 1
            if (r5 == r0) goto L_0x011e
            r1 = 2
            if (r5 == r1) goto L_0x010a
            r1 = 3
            if (r5 == r1) goto L_0x00f6
            r0 = 0
            goto L_0x0134
        L_0x00f6:
            android.os.IBinder r5 = r6.readStrongBinder()
            H1.c r5 = H1.b.d(r5)
            H1.d.b(r6)
            M1.b r6 = new M1.b
            r6.<init>(r5)
            r4.onMarkerDragEnd(r6)
            goto L_0x0131
        L_0x010a:
            android.os.IBinder r5 = r6.readStrongBinder()
            H1.c r5 = H1.b.d(r5)
            H1.d.b(r6)
            M1.b r6 = new M1.b
            r6.<init>(r5)
            r4.onMarkerDrag(r6)
            goto L_0x0131
        L_0x011e:
            android.os.IBinder r5 = r6.readStrongBinder()
            H1.c r5 = H1.b.d(r5)
            H1.d.b(r6)
            M1.b r6 = new M1.b
            r6.<init>(r5)
            r4.onMarkerDragStart(r6)
        L_0x0131:
            r7.writeNoException()
        L_0x0134:
            return r0
        L_0x0135:
            r0 = 1
            if (r5 != r0) goto L_0x017d
            android.os.IBinder r5 = r6.readStrongBinder()
            if (r5 != 0) goto L_0x0140
            r5 = 0
            goto L_0x0155
        L_0x0140:
            java.lang.String r1 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
            android.os.IInterface r2 = r5.queryLocalInterface(r1)
            boolean r3 = r2 instanceof L1.f
            if (r3 == 0) goto L_0x014e
            r5 = r2
            L1.f r5 = (L1.f) r5
            goto L_0x0155
        L_0x014e:
            L1.f r2 = new L1.f
            r3 = 2
            r2.<init>(r5, r1, r3)
            r5 = r2
        L_0x0155:
            H1.d.b(r6)
            K1.e r6 = new K1.e
            r6.<init>(r5)
            java.lang.Object r4 = r4.f382c
            Y6.a r4 = (Y6.a) r4
            int r5 = r4.f2470a
            java.lang.Object r4 = r4.b
            switch(r5) {
                case 0: goto L_0x0174;
                case 1: goto L_0x016e;
                default: goto L_0x0168;
            }
        L_0x0168:
            com.samsung.android.gallery.app.ui.map.picker.google.GoogleMapPickerContainer r4 = (com.samsung.android.gallery.app.ui.map.picker.google.GoogleMapPickerContainer) r4
            r4.onMapReady((K1.e) r6)
            goto L_0x0179
        L_0x016e:
            com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer r4 = (com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer) r4
            r4.onMapReadyAgain(r6)
            goto L_0x0179
        L_0x0174:
            com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer r4 = (com.samsung.android.gallery.app.ui.map.google.GoogleMapContainer) r4
            r4.onMapReady(r6)
        L_0x0179:
            r7.writeNoException()
            goto L_0x017e
        L_0x017d:
            r0 = 0
        L_0x017e:
            return r0
        L_0x017f:
            r0 = 1
            if (r5 != r0) goto L_0x01a5
            android.os.IBinder r5 = r6.readStrongBinder()
            H1.c r5 = H1.b.d(r5)
            H1.d.b(r6)
            M1.b r6 = new M1.b
            r6.<init>(r5)
            java.lang.Object r4 = r4.f382c
            U3.a r4 = (U3.a) r4
            java.lang.Object r4 = r4.e
            com.samsung.android.gallery.module.map.abstraction.MapContainer$OnMarkerClickListener r4 = (com.samsung.android.gallery.module.map.abstraction.MapContainer.OnMarkerClickListener) r4
            boolean r4 = r4.onClick(r6)
            r7.writeNoException()
            r7.writeInt(r4)
            goto L_0x01a6
        L_0x01a5:
            r0 = 0
        L_0x01a6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: K1.i.c(int, android.os.Parcel, android.os.Parcel):boolean");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(c cVar) {
        super("com.google.android.gms.maps.internal.IOnMapClickListener", 1);
        this.f382c = cVar;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(d dVar) {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener", 1);
        this.f382c = dVar;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(b bVar) {
        super("com.google.android.gms.maps.internal.IOnMapLoadedCallback", 1);
        this.f382c = bVar;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(U3.a aVar) {
        super("com.google.android.gms.maps.internal.IOnMarkerClickListener", 1);
        this.f382c = aVar;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(U3.a aVar, byte b5) {
        super("com.google.android.gms.maps.internal.ISnapshotReadyCallback", 1);
        this.f382c = aVar;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(U3.a aVar, char c5) {
        super("com.google.android.gms.maps.internal.IOnCameraIdleListener", 1);
        this.f382c = aVar;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(Y6.a aVar) {
        super("com.google.android.gms.maps.internal.IOnMapReadyCallback", 1);
        this.f382c = aVar;
    }
}
