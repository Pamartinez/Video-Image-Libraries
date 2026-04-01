package com.samsung.android.gallery.app.ui.map.picker.google;

import A4.C0371f;
import H1.a;
import K1.c;
import K1.d;
import K1.e;
import K1.f;
import K1.i;
import K1.j;
import M1.b;
import P1.h;
import a6.g;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.maps.model.LatLng;
import com.samsung.android.gallery.module.map.abstraction.ISimpleMarker;
import com.samsung.android.gallery.module.map.abstraction.MapPickerContainer;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import u1.C0284b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GoogleMapPickerContainer extends MapPickerContainer<e> {
    private static final boolean FEATURE_SUPPORT_GOOGLE_PLAY_SERVICE = Features.isEnabled(Features.SUPPORT_GOOGLE_PLAY_SERVICE);
    private final c mClickListener = new g(2, this);
    final GoogleLocApi mGoogleLocApi;
    private f mGoogleMapView;
    private final d mMarkerDragListener = new d() {
        public void onMarkerDragEnd(b bVar) {
            MapPickerContainer.MarkerDragEndListener access$000 = GoogleMapPickerContainer.this.mOnMarkerDragEndListener;
            ((D3.c) access$000).d.lambda$initMapContainer$1(new GoogleSimpleMarker(bVar));
        }

        public void onMarkerDragStart(b bVar) {
            boolean z;
            bVar.getClass();
            H1.c cVar = bVar.f417a;
            try {
                a aVar = (a) cVar;
                Parcel b = aVar.b(aVar.c(), 13);
                int i2 = H1.d.f335a;
                if (b.readInt() != 0) {
                    z = true;
                } else {
                    z = false;
                }
                b.recycle();
                if (z) {
                    try {
                        a aVar2 = (a) cVar;
                        aVar2.d(aVar2.c(), 12);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (RemoteException e7) {
                throw new RuntimeException(e7);
            }
        }

        public void onMarkerDrag(b bVar) {
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GoogleLocApiImpl2 extends GoogleLocApi {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$requestByLocationService$0(long j2, Consumer consumer, Location location) {
            boolean z;
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("requestByLocationService");
            boolean z3 = false;
            if (location != null) {
                z = true;
            } else {
                z = false;
            }
            A.a.A(new Object[]{Boolean.valueOf(z), Long.valueOf(j2)}, sb2, str);
            Consumer<Location> consumer2 = this.mLocationCallback;
            if (consumer2 != null) {
                consumer2.accept(location);
            }
            if (location != null) {
                z3 = true;
            }
            consumer.accept(Boolean.valueOf(z3));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$requestByLocationService$1(Consumer consumer, Exception exc) {
            Log.e((CharSequence) this.TAG, "requestByLocationService failed", exc.getMessage());
            consumer.accept(Boolean.FALSE);
        }

        public boolean requestByLocationService(Context context, Consumer<Boolean> consumer) {
            long currentTimeMillis = System.currentTimeMillis();
            int i2 = J1.a.f365a;
            u1.f fVar = new u1.f(context, G1.c.f283i, C0284b.f1932a, u1.e.b);
            Y1.f fVar2 = new Y1.f();
            fVar2.b = true;
            fVar2.d = G1.a.d;
            fVar2.f951c = 2414;
            h b = fVar.b(0, fVar2.a());
            a aVar = new a(this, currentTimeMillis, consumer);
            b.getClass();
            P1.g gVar = P1.d.f572a;
            P1.f fVar3 = new P1.f((Executor) gVar, aVar);
            Kd.a aVar2 = (Kd.a) b.d;
            aVar2.h(fVar3);
            b.o();
            aVar2.h(new P1.f((Executor) gVar, new b(this, consumer)));
            b.o();
            return true;
        }
    }

    public GoogleMapPickerContainer(Context context) {
        super(context);
        this.mGoogleMapView = new f(context);
        GoogleLocApiImpl2 googleLocApiImpl2 = new GoogleLocApiImpl2();
        this.mGoogleLocApi = googleLocApiImpl2;
        googleLocApiImpl2.setLocationListener(new U9.b(24, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(LatLng latLng) {
        this.mOnClickListener.onMapClicked(latLng.d, latLng.e);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Location location) {
        if (location != null) {
            storeCurrentLocation(location.getLatitude(), location.getLongitude());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestCurrentLocationUpdate$2(Runnable runnable, Context context, Boolean bool) {
        if (bool.booleanValue()) {
            runnable.run();
        } else {
            requestByLocationManager(context, runnable);
        }
    }

    public View getView() {
        return this.mGoogleMapView;
    }

    public void moveCamera() {
        MAP map = this.mMap;
        if (map == null) {
            Log.w(this.TAG, "fail moveCamera");
        } else {
            ((e) map).b(GoogleSimpleConverter.newCameraPosition(this.mLocation, 13.0f));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mGoogleMapView.b(bundle);
        this.mGoogleMapView.a(new Y6.a(2, this));
    }

    public void onDestroy() {
        super.onDestroy();
        this.mGoogleMapView.c();
        MAP map = this.mMap;
        if (map != null) {
            ((e) map).c();
            this.mMap = null;
        }
        this.mGoogleMapView = null;
        this.mGoogleLocApi.recycle();
    }

    public void onPause() {
        this.mGoogleMapView.d();
    }

    public void onResume() {
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

    public void requestCurrentLocationUpdate(Context context, Runnable runnable) {
        if (FEATURE_SUPPORT_GOOGLE_PLAY_SERVICE) {
            if (!this.mGoogleLocApi.requestByLocationService(context, new C0371f((Object) this, (Object) runnable, (Object) context, 16))) {
                Log.d(this.TAG, "LocationClient failed");
            } else {
                return;
            }
        }
        requestByLocationManager(context, runnable);
    }

    public ISimpleMarker addMarker(e eVar, double[] dArr, Bitmap bitmap, String str) {
        return GoogleSimpleConverter.convertToGalleryMarker(eVar, dArr, bitmap, str);
    }

    public e getMap() {
        return (e) this.mMap;
    }

    public void onMapReady(e eVar) {
        super.onMapReady(eVar);
        if (FEATURE_SUPPORT_GOOGLE_PLAY_SERVICE) {
            this.mGoogleLocApi.connectIfReady(AppResources.getAppContext());
        }
    }

    public void setCompassEnabled(e eVar, boolean z) {
        eVar.d().i(z);
    }

    public void setInfoWindowAdapter(e eVar) {
        AnonymousClass2 r0 = new K1.b() {
            public View getInfoContents(b bVar) {
                return null;
            }

            public View getInfoWindow(b bVar) {
                GoogleMapPickerContainer googleMapPickerContainer = GoogleMapPickerContainer.this;
                bVar.getClass();
                try {
                    a aVar = (a) bVar.f417a;
                    Parcel b = aVar.b(aVar.c(), 6);
                    String readString = b.readString();
                    b.recycle();
                    return googleMapPickerContainer.getInfoWindowView(readString);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        L1.f fVar = eVar.f380a;
        try {
            i iVar = new i((K1.b) r0);
            Parcel c5 = fVar.c();
            H1.d.d(c5, iVar);
            fVar.d(c5, 33);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMap(e eVar) {
        this.mMap = eVar;
    }

    public void setMapListeners(e eVar) {
        eVar.f(this.mClickListener);
        d dVar = this.mMarkerDragListener;
        L1.f fVar = eVar.f380a;
        if (dVar == null) {
            try {
                Parcel c5 = fVar.c();
                H1.d.d(c5, (IInterface) null);
                fVar.d(c5, 31);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            i iVar = new i(dVar);
            Parcel c6 = fVar.c();
            H1.d.d(c6, iVar);
            fVar.d(c6, 31);
        }
    }

    public void setRotateGesturesEnabled(e eVar, boolean z) {
        eVar.d().j(z);
    }

    public void setZoomControlsEnabled(e eVar, boolean z) {
        eVar.d().k(z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class GoogleLocApi {
        final String TAG = getClass().getSimpleName();
        Consumer<Location> mLocationCallback;

        public void recycle() {
            this.mLocationCallback = null;
        }

        public abstract boolean requestByLocationService(Context context, Consumer<Boolean> consumer);

        public GoogleLocApi setLocationListener(Consumer<Location> consumer) {
            this.mLocationCallback = consumer;
            return this;
        }

        public void connectIfReady(Context context) {
        }
    }
}
