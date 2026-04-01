package com.samsung.android.gallery.app.ui.map.picker.google;

import C1.b;
import K1.a;
import K1.e;
import L1.d;
import M1.c;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.samsung.android.gallery.module.map.abstraction.ISimpleMarker;
import o1.C0246a;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class GoogleSimpleConverter {
    public static ISimpleMarker convertToGalleryMarker(e eVar, double[] dArr, Bitmap bitmap, String str) {
        c cVar = new c();
        cVar.d = new LatLng(dArr[0], dArr[1]);
        cVar.e = str;
        cVar.g = C0246a.U(bitmap);
        return new GoogleSimpleMarker(eVar.a(cVar));
    }

    public static a newCameraPosition(double[] dArr, float f) {
        CameraPosition cameraPosition = new CameraPosition(new LatLng(dArr[0], dArr[1]), f, 0.0f, 0.0f);
        try {
            L1.a aVar = d.d;
            r.c(aVar, "CameraUpdateFactory is not initialized");
            Parcel c5 = aVar.c();
            H1.d.c(c5, cameraPosition);
            Parcel b = aVar.b(c5, 7);
            C1.a d = b.d(b.readStrongBinder());
            b.recycle();
            return new a(d);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
