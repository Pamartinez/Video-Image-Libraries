package com.samsung.android.gallery.app.ui.map.google;

import A0.l;
import K1.e;
import K1.f;
import K1.j;
import L1.c;
import L1.d;
import L1.g;
import O3.b;
import U3.a;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.View;
import android.widget.RelativeLayout;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.model.MapLatLng;
import com.samsung.android.gallery.module.map.model.MapLatLngBounds;
import com.samsung.android.gallery.module.map.model.MapVisibleRegion;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GoogleMapContainer extends MapContainer<e> {
    private MapContainer.OnGalleryMapReadyListener mCallback;
    private e mGoogleMap;
    private final f mGoogleMapView;
    private int mObscuringTopPadding;
    boolean mOnMapLoaded = false;
    boolean mResumed = false;
    MapContainer.OnSnapshotReadyListener mSanShotCallback;
    private final int mSidePaddingForPreventObscuring;

    public GoogleMapContainer(Context context) {
        this.mGoogleMapView = new f(context);
        this.mSidePaddingForPreventObscuring = context.getResources().getDimensionPixelSize(R.dimen.rounded_corner_radius) >> 1;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMapReadyAgain$2(e eVar) {
        int i2 = this.mSidePaddingForPreventObscuring;
        eVar.i(i2, 0, i2);
        if (this.mResumed) {
            Log.d(this.TAG, "do snapshot on map ready again");
            MapContainer.OnSnapshotReadyListener onSnapshotReadyListener = this.mSanShotCallback;
            Objects.requireNonNull(onSnapshotReadyListener);
            eVar.j(new a(24, onSnapshotReadyListener));
            return;
        }
        Log.e(this.TAG, "snapshot failed onMapReadyAgain");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSnapShotCallback$0(MapContainer.OnSnapshotReadyListener onSnapshotReadyListener) {
        this.mOnMapLoaded = true;
        if (this.mGoogleMap == null) {
            return;
        }
        if (this.mResumed) {
            Log.d(this.TAG, "do snapshot");
            e eVar = this.mGoogleMap;
            Objects.requireNonNull(onSnapshotReadyListener);
            eVar.j(new a(24, onSnapshotReadyListener));
            return;
        }
        Log.e(this.TAG, "snapshot failed");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSnapShotCallback$1() {
        if (this.mGoogleMap != null && this.mOnMapLoaded) {
            this.mGoogleMapView.a(new Y6.a(1, this));
        }
    }

    /* access modifiers changed from: private */
    public void onMapReady(e eVar) {
        this.mGoogleMap = eVar;
        if (eVar != null) {
            int i2 = this.mSidePaddingForPreventObscuring;
            eVar.i(i2, this.mObscuringTopPadding, i2);
        }
        this.mCallback.onMapReady(eVar);
    }

    /* access modifiers changed from: private */
    public void onMapReadyAgain(e eVar) {
        this.mGoogleMap = eVar;
        eVar.g(new b(16, this, eVar));
    }

    public void animateCamera(double d, double d2, float f) {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            eVar.b(d.t(new LatLng(d, d2), f));
        }
    }

    public MapLatLng getGalleryMapLatLng(double d, double d2) {
        return new MapLatLng(d, d2);
    }

    public IMapMarker getGalleryMarker(Object obj) {
        return new GoogleMapMarker((M1.b) obj);
    }

    public void getMapAsync(MapContainer.OnGalleryMapReadyListener<e> onGalleryMapReadyListener) {
        this.mCallback = onGalleryMapReadyListener;
        this.mGoogleMapView.a(new Y6.a(0, this));
    }

    public double getMapZoom() {
        e eVar = this.mGoogleMap;
        if (eVar == null) {
            return -1.0d;
        }
        eVar.getClass();
        try {
            L1.f fVar = eVar.f380a;
            Parcel b = fVar.b(fVar.c(), 1);
            CameraPosition cameraPosition = (CameraPosition) H1.d.a(b, CameraPosition.CREATOR);
            b.recycle();
            return (double) cameraPosition.e;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public View getView() {
        return this.mGoogleMapView;
    }

    public MapVisibleRegion getVisibleRegion() {
        e eVar = this.mGoogleMap;
        E1.a aVar = null;
        if (eVar == null) {
            return null;
        }
        eVar.getClass();
        try {
            L1.f fVar = eVar.f380a;
            Parcel b = fVar.b(fVar.c(), 26);
            IBinder readStrongBinder = b.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                if (queryLocalInterface instanceof L1.b) {
                    aVar = (L1.b) queryLocalInterface;
                } else {
                    aVar = new E1.a(readStrongBinder, "com.google.android.gms.maps.internal.IProjectionDelegate", 2);
                }
            }
            b.recycle();
            try {
                Parcel b5 = aVar.b(aVar.c(), 3);
                M1.d dVar = (M1.d) H1.d.a(b5, M1.d.CREATOR);
                b5.recycle();
                return new MapVisibleRegion(GoogleModelConverter.getMapLatLng(dVar.d), GoogleModelConverter.getMapLatLng(dVar.e), GoogleModelConverter.getMapLatLng(dVar.f), GoogleModelConverter.getMapLatLng(dVar.g), GoogleModelConverter.getMapLatLngBounds(dVar));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    public void handleDensityChanged(Context context) {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            eVar.c();
        }
    }

    public boolean hasMap() {
        if (this.mGoogleMap != null) {
            return true;
        }
        return false;
    }

    public void initCopyright() {
        repositionLogoToTop(this.mGoogleMapView, "GoogleWatermark", "GoogleCopyrights");
    }

    public void moveCamera(double d, double d2, float f) {
        if (this.mGoogleMapView == null || this.mGoogleMap == null) {
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("moveCamera failed {map?");
            boolean z = false;
            sb2.append(this.mGoogleMap != null);
            sb2.append(",mapView?");
            if (this.mGoogleMapView != null) {
                z = true;
            }
            sb2.append(z);
            sb2.append("}");
            Log.e(str, sb2.toString());
        } else if (!MapUtil.isValidLocation(d, d2)) {
            Log.d(this.TAG, "moveCamera skip by invalid loc");
        } else {
            e eVar = this.mGoogleMap;
            K1.a t = d.t(new LatLng(d, d2), f);
            eVar.getClass();
            try {
                L1.f fVar = eVar.f380a;
                C1.a aVar = t.f379a;
                Parcel c5 = fVar.c();
                H1.d.d(c5, aVar);
                fVar.d(c5, 4);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onClusteredMapReady() {
        super.onClusteredMapReady();
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            eVar.d().j(false);
            this.mGoogleMap.d().i(false);
        }
    }

    public void onCreate(Bundle bundle) {
        this.mGoogleMapView.b(bundle);
    }

    public void onDestroy() {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            eVar.g((b) null);
            this.mGoogleMap.h((a) null);
            this.mGoogleMap.c();
            this.mGoogleMap = null;
        }
        this.mGoogleMapView.c();
    }

    public void onLowMemory() {
        l lVar = this.mGoogleMapView.d.f383a;
        if (lVar != null) {
            try {
                g gVar = (g) lVar.f;
                gVar.d(gVar.c(), 6);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onPause() {
        this.mResumed = false;
        this.mGoogleMapView.d();
    }

    public void onResume() {
        this.mResumed = true;
        j jVar = this.mGoogleMapView.d;
        jVar.getClass();
        jVar.c((Bundle) null, new C1.e(jVar, 1));
    }

    public void onStart() {
        j jVar = this.mGoogleMapView.d;
        jVar.getClass();
        jVar.c((Bundle) null, new C1.e(jVar, 0));
    }

    public void onStop() {
        this.mGoogleMapView.e();
    }

    public void repositionLogoToTop(View view, String... strArr) {
        if (view != null) {
            for (String str : strArr) {
                try {
                    View findViewWithTag = view.findViewWithTag(str);
                    if (findViewWithTag != null) {
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewWithTag.getLayoutParams();
                        layoutParams.addRule(10, -1);
                        layoutParams.removeRule(12);
                        findViewWithTag.setImportantForAccessibility(2);
                    }
                } catch (Exception e) {
                    A.a.s(e, N2.j.k("repositionLogoToTop ", str, " failed. e="), this.TAG);
                }
            }
        }
    }

    public void setAllGesturesEnabled(boolean z) {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            B1.b d = eVar.d();
            d.getClass();
            try {
                c cVar = (c) d.e;
                Parcel c5 = cVar.c();
                int i2 = H1.d.f335a;
                c5.writeInt(z ? 1 : 0);
                cVar.d(c5, 8);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setMapOnClickListener(MapContainer.OnClickListener onClickListener) {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            eVar.f(new a(20, onClickListener));
        }
    }

    public void setMarkerOnClickListener(MapContainer.OnMarkerClickListener onMarkerClickListener) {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            eVar.h(new a(23, onMarkerClickListener));
        }
    }

    public void setOnCameraIdleListener(MapContainer.OnCameraIdleListener onCameraIdleListener) {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            Objects.requireNonNull(onCameraIdleListener);
            eVar.e(new a(21, onCameraIdleListener));
        }
    }

    public void setSnapShotCallback(MapContainer.OnSnapshotReadyListener onSnapshotReadyListener) {
        this.mSanShotCallback = onSnapshotReadyListener;
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            eVar.g(new b(15, this, onSnapshotReadyListener));
            this.mGoogleMap.e(new a(22, this));
        }
    }

    public void setTopPadding(int i2) {
        this.mObscuringTopPadding = i2;
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            int i7 = this.mSidePaddingForPreventObscuring;
            eVar.i(i7, i2, i7);
        }
        f fVar = this.mGoogleMapView;
        if (fVar != null) {
            fVar.invalidate();
        }
    }

    public void setZoomControlsEnabled(boolean z) {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            eVar.d().k(z);
        }
    }

    public void setZoomLevel(float f) {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            try {
                L1.a aVar = d.d;
                r.c(aVar, "CameraUpdateFactory is not initialized");
                Parcel c5 = aVar.c();
                c5.writeFloat(f);
                Parcel b = aVar.b(c5, 4);
                C1.a d = C1.b.d(b.readStrongBinder());
                b.recycle();
                r.b(d);
                try {
                    L1.f fVar = eVar.f380a;
                    Parcel c6 = fVar.c();
                    H1.d.d(c6, d);
                    fVar.d(c6, 4);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            } catch (RemoteException e7) {
                throw new RuntimeException(e7);
            }
        }
    }

    public e getMapInstance() {
        return this.mGoogleMap;
    }

    public void moveCamera(MapLatLngBounds mapLatLngBounds, int i2) {
        e eVar = this.mGoogleMap;
        if (eVar != null) {
            LatLngBounds latLngBounds = GoogleModelConverter.getLatLngBounds(mapLatLngBounds);
            r.c(latLngBounds, "bounds must not be null");
            try {
                L1.a aVar = d.d;
                r.c(aVar, "CameraUpdateFactory is not initialized");
                Parcel c5 = aVar.c();
                H1.d.c(c5, latLngBounds);
                c5.writeInt(i2);
                Parcel b = aVar.b(c5, 10);
                C1.a d = C1.b.d(b.readStrongBinder());
                b.recycle();
                r.b(d);
                try {
                    L1.f fVar = eVar.f380a;
                    Parcel c6 = fVar.c();
                    H1.d.d(c6, d);
                    fVar.d(c6, 4);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            } catch (RemoteException e7) {
                throw new RuntimeException(e7);
            }
        } else {
            Log.e(this.TAG, "fail move");
        }
    }
}
