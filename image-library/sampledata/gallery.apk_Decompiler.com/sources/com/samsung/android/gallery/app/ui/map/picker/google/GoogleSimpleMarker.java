package com.samsung.android.gallery.app.ui.map.picker.google;

import H1.a;
import H1.d;
import M1.b;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.gallery.module.map.abstraction.ISimpleMarker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class GoogleSimpleMarker implements ISimpleMarker {
    private final b mMarker;

    public GoogleSimpleMarker(b bVar) {
        this.mMarker = bVar;
        bVar.getClass();
        try {
            a aVar = (a) bVar.f417a;
            Parcel c5 = aVar.c();
            int i2 = d.f335a;
            c5.writeInt(1);
            aVar.d(c5, 9);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public double[] getPosition() {
        return new double[]{this.mMarker.a().d, this.mMarker.a().e};
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

    public void updateTitle(String str) {
        b bVar = this.mMarker;
        bVar.getClass();
        try {
            a aVar = (a) bVar.f417a;
            Parcel c5 = aVar.c();
            c5.writeString(str);
            aVar.d(c5, 5);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
