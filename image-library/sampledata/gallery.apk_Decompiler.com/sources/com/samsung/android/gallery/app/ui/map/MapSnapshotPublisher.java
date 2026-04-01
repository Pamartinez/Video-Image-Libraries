package com.samsung.android.gallery.app.ui.map;

import V6.a;
import V6.b;
import V6.c;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.map.staticmarker.SnapShotMapFragment;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.SystemEnvironment;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MapSnapshotPublisher extends Subscriber {
    private int mChRetryCounter;
    private boolean mIsRemoveTransactionRunning;
    SnapShotMapFragment mMapFragment;
    long mMapLoadingTime;
    private double mRequestedLatitude;
    private double mRequestedLongitude;
    private int mSnapBias = 0;

    public MapSnapshotPublisher(Blackboard blackboard) {
        super(blackboard);
        GalleryPreference instance = GalleryPreference.getInstance();
        PreferenceName preferenceName = PreferenceName.RESERVE_CLEAR_MAP_CACHE;
        if (instance.loadBoolean(preferenceName, false)) {
            Log.d(this.TAG, "clear map cache");
            GalleryPreference.getInstance().saveState(preferenceName, false);
            CacheManager.getInstance().clear(7);
        }
        SystemEnvironment.addObserver(this.TAG.toString(), new b(this), 2);
    }

    /* access modifiers changed from: private */
    /* renamed from: checkChinaMapReady */
    public void lambda$onSnapShotRequest$0(double d, double d2) {
        if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            SnapShotMapFragment snapShotMapFragment = this.mMapFragment;
            if (snapShotMapFragment != null) {
                if (Arrays.equals(snapShotMapFragment.getLocation(), new double[]{d, d2})) {
                    StringCompat stringCompat = this.TAG;
                    Log.i(stringCompat, "mChRetryCounter " + this.mChRetryCounter);
                    Bitmap chinaSnapshot = this.mMapFragment.chinaSnapshot();
                    if (isChMapLoaded(chinaSnapshot)) {
                        onSnapshotReady(chinaSnapshot);
                        return;
                    }
                    int i2 = this.mChRetryCounter - 1;
                    this.mChRetryCounter = i2;
                    if (i2 > 0) {
                        ThreadUtil.postOnUiThreadDelayed(new c(this, d, d2, 1), 500);
                        return;
                    }
                    Log.e((CharSequence) this.TAG, "fail get Ch map", Logger.vt(this.mMapLoadingTime));
                    return;
                }
            }
            Log.w(this.TAG, "CheckChMapReady fail");
        }
    }

    private void commitMapFragment(SnapShotMapFragment snapShotMapFragment) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            getActivity().getSupportFragmentManager().beginTransaction().add((int) R.id.mapHiddenFrame, (Fragment) snapShotMapFragment).commitAllowingStateLoss();
            Log.d(this.TAG, "commitMapFragment", Logger.getSimpleName((Object) snapShotMapFragment), Long.valueOf(currentTimeMillis));
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "commitMapFragment failed e=" + e);
        }
    }

    private void init() {
        SnapShotMapFragment snapShotMapFragment = new SnapShotMapFragment();
        this.mMapFragment = snapShotMapFragment;
        snapShotMapFragment.setMapSnapshotReadyListener(new b(this));
        if (!this.mMapFragment.isAdded()) {
            commitMapFragment(this.mMapFragment);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeMapFragment$2() {
        this.mIsRemoveTransactionRunning = false;
    }

    /* access modifiers changed from: private */
    public void onExitFromViewer(Object obj, Bundle bundle) {
        removeMapFragment();
    }

    /* access modifiers changed from: private */
    public void onLocaleChanged(Context context) {
        Log.d(this.TAG, "onLocaleChanged", Locale.getDefault());
        removeMapFragment();
        GalleryPreference.getInstance().saveState(PreferenceName.RESERVE_CLEAR_MAP_CACHE, true);
    }

    /* access modifiers changed from: private */
    public void onSnapShotRequest(Object obj, Bundle bundle) {
        if (this.mIsRemoveTransactionRunning) {
            Log.e(this.TAG, "Remove transaction is running, so dismiss request.");
            return;
        }
        double d = BundleWrapper.getDouble(bundle, "latitude");
        double d2 = BundleWrapper.getDouble(bundle, "longitude");
        this.mSnapBias = BundleWrapper.getInt(bundle, "snap_bias");
        this.mRequestedLatitude = d;
        this.mRequestedLongitude = d2;
        if (MapUtil.isValidLocation(d, d2)) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Log.e(this.TAG, "onSnapShotRequest failed : null activity");
                return;
            }
            View findViewById = activity.findViewById(R.id.mapHiddenFrame);
            if (findViewById == null) {
                findViewById = ((ViewStub) activity.findViewById(R.id.map_hidden_frame)).inflate().findViewById(R.id.mapHiddenFrame);
            }
            if (this.mMapFragment == null) {
                init();
            }
            if (findViewById != null) {
                ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
                layoutParams.width = BundleWrapper.getInt(bundle, "width");
                layoutParams.height = (this.mSnapBias * 2) + BundleWrapper.getInt(bundle, "height");
                findViewById.setLayoutParams(layoutParams);
                this.mMapFragment.setLocation(d, d2);
                this.mMapFragment.moveCamera();
                this.mMapLoadingTime = System.currentTimeMillis();
                if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
                    this.mChRetryCounter = 20;
                    ThreadUtil.postOnUiThreadDelayed(new c(this, d, d2, 0), 500);
                }
            }
        }
    }

    private void postMapSnapshotFailure() {
        this.mBlackboard.post(new UriBuilder("data://MapSnapShot_failure").appendArg("latitude", this.mRequestedLatitude).appendArg("longitude", this.mRequestedLongitude).build(), (Object) null);
    }

    private void removeMapFragment() {
        SnapShotMapFragment snapShotMapFragment = this.mMapFragment;
        this.mMapFragment = null;
        if (snapShotMapFragment != null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                snapShotMapFragment.pauseContainer();
                this.mIsRemoveTransactionRunning = true;
                getActivity().getSupportFragmentManager().beginTransaction().remove(snapShotMapFragment).runOnCommit(new V3.b(1, this)).commitAllowingStateLoss();
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "removeMapFragment" + Logger.vt(currentTimeMillis));
            } catch (Exception e) {
                this.mIsRemoveTransactionRunning = false;
                StringCompat stringCompat2 = this.TAG;
                Log.w(stringCompat2, "removeMapFragment failed e=" + e.getMessage());
            }
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("data://MapSnapShot"), new a(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_exit_from_viewer", new a(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_stop", new a(this, 1)).setWorkingOnUI());
    }

    public FragmentActivity getActivity() {
        return (FragmentActivity) BlackboardUtils.readActivity(this.mBlackboard);
    }

    public boolean isChMapLoaded(Bitmap bitmap) {
        if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            return !BitmapUtils.hasSameColor(bitmap, -2172970);
        }
        return false;
    }

    public void onSnapshotReady(Bitmap bitmap) {
        SnapShotMapFragment snapShotMapFragment = this.mMapFragment;
        if (snapShotMapFragment == null || bitmap == null) {
            postMapSnapshotFailure();
            return;
        }
        double[] location = snapShotMapFragment.getLocation();
        UriBuilder appendArg = new UriBuilder("data://MapSnapShot").appendArg("latitude", location[0]).appendArg("longitude", location[1]).appendArg("width", bitmap.getWidth()).appendArg("height", bitmap.getHeight());
        int i2 = this.mSnapBias;
        if (i2 > 0) {
            appendArg.appendArg("snap_bias", i2);
            bitmap = new BitmapOperator(bitmap).crop(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight() - (this.mSnapBias * 2))).apply();
        }
        this.mBlackboard.post(appendArg.build(), bitmap.copy(Bitmap.Config.ARGB_8888, false));
        StringCompat stringCompat = this.TAG;
        C0212a.z(new Object[]{this.mMapFragment.getView(), bitmap, Long.valueOf(this.mMapLoadingTime)}, new StringBuilder("ready"), stringCompat);
    }
}
