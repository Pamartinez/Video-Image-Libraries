package com.samsung.android.gallery.app.ui.map.factory;

import Ga.d;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.map.abstraction.IMap;
import com.samsung.android.gallery.module.map.abstraction.IMapFactory;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.abstraction.MapPickerContainer;
import com.samsung.android.gallery.module.map.abstraction.MarkerManager;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryMapFactory {
    private static final CharSequence TAG = "GalleryMapFactory";
    static final boolean USE_AMAP;
    static IMapFactory sInstance;

    static {
        IMapFactory iMapFactory;
        boolean isEnabled = Features.isEnabled(Features.IS_CHINESE_DEVICE);
        USE_AMAP = isEnabled;
        if (isEnabled) {
            iMapFactory = SgmlLoader.createFactory();
        } else {
            iMapFactory = new GoogleMapFactory();
        }
        sInstance = iMapFactory;
    }

    public static MapContainer createMap(Context context) {
        return sInstance.createMap(context);
    }

    public static MarkerManager createMarkerManager(Context context, IMap<?> iMap) {
        return sInstance.createMarkerManager(context, iMap);
    }

    public static MapPickerContainer createPickerMap(Context context) {
        return sInstance.createPickerMap(context);
    }

    public static MarkerManager createSimpleMarkerManager(Context context, IMap<?> iMap) {
        return sInstance.createSimpleMarkerManager(context, iMap);
    }

    public static void downloadGalleryPlugins(EventContext eventContext, Fragment fragment) {
        if (USE_AMAP && isEmptyMapFactory()) {
            FragmentActivity activity = fragment.getActivity();
            if (activity != null) {
                eventContext.subscribeInstantOnUi("lifecycle://on_activity_resume", new a(fragment, activity, eventContext));
            } else {
                Log.e(TAG, "prepare reload fail");
            }
            Log.i(TAG, "reload map");
        }
    }

    private static FragmentTransaction getFragmentTransaction(Fragment fragment, FragmentActivity fragmentActivity, FragmentTransaction fragmentTransaction) {
        if (fragmentActivity.getSupportFragmentManager().findFragmentByTag(fragment.getTag()) == fragment) {
            return fragmentActivity.getSupportFragmentManager().beginTransaction();
        }
        return fragment.getParentFragmentManager().beginTransaction();
    }

    public static boolean isEmptyMap(MapContainer<?> mapContainer) {
        return mapContainer instanceof DummyMapContainer;
    }

    private static boolean isEmptyMapFactory() {
        return sInstance instanceof EmptyMapFactory;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$downloadGalleryPlugins$0(Fragment fragment, FragmentActivity fragmentActivity, Object obj, Bundle bundle) {
        sInstance = SgmlLoader.createFactory();
        if (!isEmptyMapFactory()) {
            refreshFragment(fragment, fragmentActivity);
        } else {
            Log.e(TAG, "reload map fail");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$downloadGalleryPlugins$1(Fragment fragment, FragmentActivity fragmentActivity, EventContext eventContext, Object obj, Bundle bundle) {
        sInstance = SgmlLoader.createFactory();
        if (!isEmptyMapFactory()) {
            refreshFragment(fragment, fragmentActivity);
            return;
        }
        Log.e(TAG, "reload map fail");
        eventContext.subscribeInstantOnUi("command://reloadMap", new d(3, fragment, fragmentActivity));
    }

    private static void refreshFragment(Fragment fragment, FragmentActivity fragmentActivity) {
        if (!fragment.isAdded() || !fragment.isResumed()) {
            CharSequence charSequence = TAG;
            Log.e(charSequence, "refreshFragment failed : " + fragment);
            return;
        }
        FragmentTransaction fragmentTransaction = getFragmentTransaction(fragment, fragmentActivity, (FragmentTransaction) null);
        fragmentTransaction.detach(fragment);
        fragmentTransaction.commit();
        FragmentTransaction fragmentTransaction2 = getFragmentTransaction(fragment, fragmentActivity, fragmentTransaction);
        fragmentTransaction2.attach(fragment);
        fragmentTransaction2.commit();
        CharSequence charSequence2 = TAG;
        Log.i(charSequence2, "refreshFragment : " + fragment);
    }
}
