package com.samsung.android.gallery.app.ui.map.base;

import U3.a;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapPresenter;
import com.samsung.android.gallery.app.ui.map.base.IGalleryMapView;
import com.samsung.android.gallery.app.ui.map.factory.GalleryMapFactory;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GalleryMapFragment<V extends IGalleryMapView, P extends GalleryMapPresenter> extends MvpBaseFragment<V, P> implements IGalleryMapView {
    protected MapContainer mMapContainer;
    /* access modifiers changed from: private */
    public View mMapView;
    View.OnAttachStateChangeListener onAttachStateChangeListener = new View.OnAttachStateChangeListener() {
        public void onViewAttachedToWindow(View view) {
            StringCompat access$000 = GalleryMapFragment.this.TAG;
            Log.d(access$000, "view Attached : " + GalleryMapFragment.this);
        }

        public void onViewDetachedFromWindow(View view) {
            StringCompat access$100 = GalleryMapFragment.this.TAG;
            Log.d(access$100, "view Detached : " + GalleryMapFragment.this);
        }
    };

    private void createMap(Bundle bundle) {
        Log.d(this.TAG, "createMap");
        MapContainer createMap = GalleryMapFactory.createMap(getContext());
        this.mMapContainer = createMap;
        createMap.onCreate(bundle);
        this.mMapContainer.getMapAsync(new a(19, this));
    }

    private void initActivityToolbar() {
        Toolbar toolbar;
        int i2;
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        if (readActivity != null && (toolbar = (Toolbar) readActivity.findViewById(R.id.activity_toolbar)) != null) {
            if (isActivityToolbarSupported()) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            toolbar.setVisibility(i2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r0 = getToolbar();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateMapTopPadding() {
        /*
            r3 = this;
            boolean r0 = r3.isToolbarPaddingNeeded()
            if (r0 != 0) goto L_0x0007
            goto L_0x0032
        L_0x0007:
            com.samsung.android.gallery.widget.toolbar.GalleryToolbar r0 = r3.getToolbar()
            if (r0 == 0) goto L_0x0032
            android.view.ViewTreeObserver r1 = r0.getViewTreeObserver()     // Catch:{ IllegalStateException -> 0x001a }
            com.samsung.android.gallery.app.ui.map.base.GalleryMapFragment$2 r2 = new com.samsung.android.gallery.app.ui.map.base.GalleryMapFragment$2     // Catch:{ IllegalStateException -> 0x001a }
            r2.<init>(r0)     // Catch:{ IllegalStateException -> 0x001a }
            r1.addOnPreDrawListener(r2)     // Catch:{ IllegalStateException -> 0x001a }
            return
        L_0x001a:
            r0 = move-exception
            com.samsung.android.gallery.support.utils.StringCompat r3 = r3.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "addOnPreDrawListener failed e="
            r1.<init>(r2)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.samsung.android.gallery.support.utils.Log.d(r3, r0)
        L_0x0032:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.map.base.GalleryMapFragment.updateMapTopPadding():void");
    }

    public int getLayoutId() {
        return R.layout.mapview_layout;
    }

    public FrameLayout getMapViewParent(View view) {
        return (FrameLayout) view;
    }

    public boolean hasMap() {
        MapContainer mapContainer = this.mMapContainer;
        if (mapContainer == null || !mapContainer.hasMap()) {
            return false;
        }
        return true;
    }

    public void initView(View view) {
        if (this.mMapView == null) {
            Log.d(this.TAG, "initView");
            View view2 = this.mMapContainer.getView();
            this.mMapView = view2;
            if (view2 != null) {
                FrameLayout mapViewParent = getMapViewParent(view);
                mapViewParent.addView(this.mMapView, 0, new FrameLayout.LayoutParams(-1, -1));
                updateMapTopPadding();
                mapViewParent.addOnAttachStateChangeListener(this.onAttachStateChangeListener);
                this.mMapView.setImportantForAccessibility(4);
                initCopyright(this.mMapContainer);
            }
        }
    }

    public boolean isActivityToolbarSupported() {
        return false;
    }

    public boolean isToolbarPaddingNeeded() {
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateMapTopPadding();
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        createMap(bundle);
    }

    public void onDestroy() {
        this.mMapContainer.onDestroy();
        try {
            ViewUtils.removeSelf(this.mMapView);
            ViewUtils.removeAllViews((ViewGroup) this.mMapView);
        } catch (Exception unused) {
        }
        super.onDestroy();
    }

    public void onDetach() {
        View view;
        super.onDetach();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onDetach : " + this);
        View view2 = this.mMapView;
        if (view2 != null && (view = (View) view2.getParent()) != null) {
            view.removeOnAttachStateChangeListener(this.onAttachStateChangeListener);
        }
    }

    public void onDump(PrintWriter printWriter, String str) {
        boolean z;
        String str2;
        super.onDump(printWriter, str);
        View view = this.mMapView;
        if (view != null) {
            if (view.getParent() == null || !((View) this.mMapView.getParent()).isAttachedToWindow()) {
                z = false;
            } else {
                z = true;
            }
            StringBuilder t = C0212a.t(str, "mMapView.parent = ");
            t.append(Logger.toString((Object) this.mMapView.getParent()));
            if (z) {
                str2 = " Attached";
            } else {
                str2 = " Detached";
            }
            t.append(str2);
            Logger.dumpLog(printWriter, t.toString());
            if (!z && !isDetached()) {
                Logger.dumpLog(printWriter, str + "[WARN] Fragment attached but view detached");
            }
        }
    }

    public void onLowMemory() {
        try {
            this.mMapContainer.onLowMemory();
        } catch (Error | Exception unused) {
        }
        super.onLowMemory();
    }

    public void onMapReady(Object obj) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "OnMapReady " + obj);
    }

    public void onMapReload() {
        Log.i(this.TAG, "onMapReload");
    }

    public void onPause() {
        this.mMapContainer.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mMapContainer.onResume();
    }

    public void onStart() {
        super.onStart();
        this.mMapContainer.onStart();
    }

    public void onStop() {
        this.mMapContainer.onStop();
        super.onStop();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (GalleryMapFactory.isEmptyMap(this.mMapContainer)) {
            createMap((Bundle) null);
            ((GalleryMapPresenter) this.mPresenter).onViewCreated(view);
            onMapReload();
        }
        initActivityToolbar();
    }

    public boolean supportPinchShrink() {
        return false;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        if (isDetached()) {
            str = "/Detached";
        } else {
            str = "/Attached";
        }
        sb2.append(str);
        return sb2.toString();
    }

    public GalleryMapPresenter createPresenter(IGalleryMapView iGalleryMapView) {
        return new GalleryMapPresenter(getBlackboard(), this);
    }

    public void onPostResume() {
    }

    public void initCopyright(MapContainer mapContainer) {
    }
}
