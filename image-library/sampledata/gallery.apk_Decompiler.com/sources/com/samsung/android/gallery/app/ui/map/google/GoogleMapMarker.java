package com.samsung.android.gallery.app.ui.map.google;

import H1.a;
import H1.d;
import M1.b;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.model.MapLatLng;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GoogleMapMarker implements IMapMarker {
    private final b mMarker;

    public GoogleMapMarker(b bVar) {
        this.mMarker = bVar;
    }

    public String getId() {
        b bVar = this.mMarker;
        bVar.getClass();
        try {
            a aVar = (a) bVar.f417a;
            Parcel b = aVar.b(aVar.c(), 2);
            String readString = b.readString();
            b.recycle();
            return readString;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove() {
        b bVar = this.mMarker;
        bVar.getClass();
        try {
            a aVar = (a) bVar.f417a;
            aVar.d(aVar.c(), 1);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAlpha(float f) {
        b bVar = this.mMarker;
        bVar.getClass();
        try {
            a aVar = (a) bVar.f417a;
            Parcel c5 = aVar.c();
            c5.writeFloat(f);
            aVar.d(c5, 25);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void setIcon(Bitmap bitmap) {
        b bVar = this.mMarker;
        B1.b U = C0246a.U(bitmap);
        try {
            a aVar = (a) bVar.f417a;
            Parcel c5 = aVar.c();
            d.d(c5, (C1.a) U.e);
            aVar.d(c5, 18);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPosition(MapLatLng mapLatLng) {
        b bVar = this.mMarker;
        LatLng latLng = GoogleModelConverter.getLatLng(mapLatLng);
        bVar.getClass();
        if (latLng != null) {
            try {
                a aVar = (a) bVar.f417a;
                Parcel c5 = aVar.c();
                d.c(c5, latLng);
                aVar.d(c5, 3);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("latlng cannot be null - a position is required.");
        }
    }
}
