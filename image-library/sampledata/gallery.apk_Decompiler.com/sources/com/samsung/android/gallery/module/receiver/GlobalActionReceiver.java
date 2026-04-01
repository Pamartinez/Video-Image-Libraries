package com.samsung.android.gallery.module.receiver;

import A.a;
import A4.C0387w;
import C3.i;
import O3.o;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.os.Build;
import android.os.Parcelable;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.sdk.SamsungAccountInfo;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloudSdk;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.microsoft.YourPhone;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.ByteBufferHolder;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sum.core.message.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GlobalActionReceiver extends BroadcastReceiver {
    private final ArrayList<AbsBroadcastReceiver> mBroadcastReceivers = new ArrayList<>();
    private final ArrayList<AbsContentObserver> mContentObservers = new ArrayList<>();
    private final IntentFilter mIntentFilter = createActionIntentFilter();
    private final AtomicInteger mRefCount = new AtomicInteger(0);
    private final ArrayList<SettingObserver> mSettingObservers = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final GlobalActionReceiver instance = new GlobalActionReceiver();
    }

    private void clearGlobalConfig() {
        DeviceInfo.clearDexMode();
        YourPhone.clearConnections();
        StorageUtil.setLowStorage(false);
        ByteBufferHolder.clear();
        TranslationManager.getInstance().release();
    }

    private IntentFilter createActionIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        if (SdkConfig.lessThan(SdkConfig.GED.Q)) {
            intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
            intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
        }
        intentFilter.addAction("com.samsung.account.SAMSUNGACCOUNT_SIGNOUT_COMPLETED");
        if (supportScloudMigration()) {
            intentFilter.addAction("com.samsung.android.scloud.MEDIA_MIGRATION_STATE_CHANGED");
        }
        if (SdkConfig.atLeast(SdkConfig.SEM.B)) {
            intentFilter.addAction("android.intent.action.PROFILE_ADDED");
            intentFilter.addAction("android.intent.action.PROFILE_REMOVED");
            intentFilter.addAction("android.intent.action.PROFILE_AVAILABLE");
            intentFilter.addAction("android.intent.action.PROFILE_UNAVAILABLE");
            intentFilter.addAction("android.intent.action.PROFILE_ACCESSIBLE");
            intentFilter.addAction("android.intent.action.PROFILE_INACCESSIBLE");
        } else {
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_ADDED");
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_REMOVED");
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_AVAILABLE");
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_UNAVAILABLE");
        }
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        if (Features.isEnabled(Features.SUPPORT_STORY_BADGE_ON_TAB)) {
            intentFilter.addAction("com.samsung.storyservice.new.autoStory");
        }
        if (Features.isEnabled(Features.SUPPORT_SHARING_SERVICE)) {
            intentFilter.addAction("com.android.gallery.action.SHARE_SERVICE_STOPPED");
        }
        intentFilter.addAction("com.samsung.android.scloud.app.broadcast.ACTION_MIGRATION_STATUS_CHANGED");
        intentFilter.addAction("android.intent.action.DATE_CHANGED");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("com.samsung.android.intent.action.NOTIFY_GALLERY_MEDIA_CHANGED");
        intentFilter.addAction("com.samsung.android.mdx.windowslink.onChangedMirroringState");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("com.samsung.intent.action.SEC_PRESENTATION_START");
        intentFilter.addAction("com.samsung.intent.action.SEC_PRESENTATION_STOP");
        intentFilter.addAction("com.android.sync.SYNC_CONN_STATUS_CHANGED");
        intentFilter.addAction("com.samsung.android.knox.intent.action.KNOX_RESTRICTIONS_CHANGED_INTERNAL");
        intentFilter.addAction("com.samsung.intent.action.ACTION_ADJUST_POP_OVER_OPTIONS");
        return intentFilter;
    }

    public static GlobalActionReceiver getInstance() {
        return LazyHolder.instance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Point lambda$processAdjustPopOverOptions$17(Parcelable parcelable) {
        return (Point) parcelable;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Point[] lambda$processAdjustPopOverOptions$18(int i2) {
        return new Point[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$processDateTimeChanged$16() {
        TimeUtil.updateToday(true);
        post("global://event/dateTimeChanged", Long.valueOf(System.currentTimeMillis()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$processSharingServiceStopped$14() {
        GalleryPreference.getInstance().saveState(PreferenceName.SHARE_SERVICE_V2_STATUS, 0);
        MdeSharingService.getInstance().clearServiceState();
        if (!MdeSharingService.getInstance().isServiceAvailable()) {
            Blackboard.getApplicationInstance().publish("global://setting/service/sharings", Boolean.FALSE);
        }
    }

    private boolean post(String str, Object obj) {
        Blackboard.getApplicationInstance().post(str, obj);
        return true;
    }

    private boolean processAction(Context context, Intent intent, String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2128145023:
                if (str.equals("android.intent.action.SCREEN_OFF")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1977157390:
                if (str.equals("com.samsung.intent.action.SEC_PRESENTATION_STOP")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1846941263:
                if (str.equals("android.intent.action.PROFILE_ACCESSIBLE")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1823736795:
                if (str.equals("android.intent.action.PROFILE_REMOVED")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1608292967:
                if (str.equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                    c5 = 4;
                    break;
                }
                break;
            case -1407094353:
                if (str.equals("com.samsung.intent.action.ACTION_ADJUST_POP_OVER_OPTIONS")) {
                    c5 = 5;
                    break;
                }
                break;
            case -1238404651:
                if (str.equals("android.intent.action.MANAGED_PROFILE_UNAVAILABLE")) {
                    c5 = 6;
                    break;
                }
                break;
            case -1181163412:
                if (str.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
                    c5 = 7;
                    break;
                }
                break;
            case -1162350254:
                if (str.equals("com.samsung.intent.action.SEC_PRESENTATION_START")) {
                    c5 = 8;
                    break;
                }
                break;
            case -1112607227:
                if (str.equals("android.intent.action.PROFILE_ADDED")) {
                    c5 = 9;
                    break;
                }
                break;
            case -1062385259:
                if (str.equals("android.intent.action.PROFILE_UNAVAILABLE")) {
                    c5 = 10;
                    break;
                }
                break;
            case -864107122:
                if (str.equals("android.intent.action.MANAGED_PROFILE_AVAILABLE")) {
                    c5 = 11;
                    break;
                }
                break;
            case -836357701:
                if (str.equals("com.samsung.android.scloud.app.broadcast.ACTION_MIGRATION_STATUS_CHANGED")) {
                    c5 = 12;
                    break;
                }
                break;
            case -730838620:
                if (str.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                    c5 = 13;
                    break;
                }
                break;
            case -385593787:
                if (str.equals("android.intent.action.MANAGED_PROFILE_ADDED")) {
                    c5 = 14;
                    break;
                }
                break;
            case -267337901:
                if (str.equals("com.samsung.account.SAMSUNGACCOUNT_SIGNOUT_COMPLETED")) {
                    c5 = 15;
                    break;
                }
                break;
            case 11531734:
                if (str.equals("android.intent.action.PROFILE_INACCESSIBLE")) {
                    c5 = 16;
                    break;
                }
                break;
            case 502473491:
                if (str.equals("android.intent.action.TIMEZONE_CHANGED")) {
                    c5 = 17;
                    break;
                }
                break;
            case 545843819:
                if (str.equals("com.samsung.storyservice.new.autoStory")) {
                    c5 = 18;
                    break;
                }
                break;
            case 823795052:
                if (str.equals("android.intent.action.USER_PRESENT")) {
                    c5 = 19;
                    break;
                }
                break;
            case 1041332296:
                if (str.equals("android.intent.action.DATE_CHANGED")) {
                    c5 = 20;
                    break;
                }
                break;
            case 1051477093:
                if (str.equals("android.intent.action.MANAGED_PROFILE_REMOVED")) {
                    c5 = 21;
                    break;
                }
                break;
            case 1275921166:
                if (str.equals("com.samsung.android.scloud.MEDIA_MIGRATION_STATE_CHANGED")) {
                    c5 = 22;
                    break;
                }
                break;
            case 1389924933:
                if (str.equals("com.samsung.android.mdx.windowslink.onChangedMirroringState")) {
                    c5 = 23;
                    break;
                }
                break;
            case 1413203413:
                if (str.equals("com.samsung.android.knox.intent.action.KNOX_RESTRICTIONS_CHANGED_INTERNAL")) {
                    c5 = 24;
                    break;
                }
                break;
            case 1551413331:
                if (str.equals("com.android.gallery.action.SHARE_SERVICE_STOPPED")) {
                    c5 = 25;
                    break;
                }
                break;
            case 1841634460:
                if (str.equals("com.samsung.android.intent.action.NOTIFY_GALLERY_MEDIA_CHANGED")) {
                    c5 = 26;
                    break;
                }
                break;
            case 1964884765:
                if (str.equals("com.android.sync.SYNC_CONN_STATUS_CHANGED")) {
                    c5 = 27;
                    break;
                }
                break;
            case 2014285134:
                if (str.equals("android.intent.action.PROFILE_AVAILABLE")) {
                    c5 = 28;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return post("global://event/screenOff", (Object) null);
            case 1:
                return processPresentationStop(intent);
            case 2:
            case 3:
            case 6:
            case 9:
            case 10:
            case 11:
            case 14:
            case 16:
            case 21:
            case 28:
                return processKnoxProfileEvent();
            case 4:
                new UsbDetachReceiver().onDetached(context, intent);
                return true;
            case 5:
                return processAdjustPopOverOptions(intent);
            case 7:
            case 13:
                return processDeviceStorage("android.intent.action.DEVICE_STORAGE_OK".equals(str));
            case 8:
                return processPresentationStart(intent);
            case 12:
                return processOneDriveMigration(intent);
            case 15:
                return processSignOutCompleted();
            case 17:
            case 20:
                return processDateTimeChanged();
            case 18:
                return processNewAutoStory();
            case 19:
                return post("global://event/keyguardUnlocked", (Object) null);
            case 22:
                return processScloudMigration(intent);
            case 23:
                YourPhone.clearConnections();
                return true;
            case 24:
                return post("global://event/knoxRestrictionsChanged", (Object) null);
            case 25:
                return processSharingServiceStopped();
            case 26:
                return processSmartSwitchUpdateDb();
            case 27:
                return post("global://event/globalAutoSyncChanged", (Object) null);
            default:
                return false;
        }
    }

    private boolean processAdjustPopOverOptions(Intent intent) {
        Point[] pointArr;
        int[] intArrayExtra = intent.getIntArrayExtra("width");
        int[] intArrayExtra2 = intent.getIntArrayExtra("height");
        int[] intArrayExtra3 = intent.getIntArrayExtra(Message.KEY_POSITION);
        if (Build.VERSION.SDK_INT >= 33) {
            pointArr = (Point[]) intent.getParcelableArrayExtra("margin", Point.class);
        } else {
            Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("margin");
            if (parcelableArrayExtra == null) {
                pointArr = null;
            } else {
                pointArr = (Point[]) Arrays.stream(parcelableArrayExtra).map(new o(11)).toArray(new C0387w(28));
            }
        }
        return post("adjust/popover/options", new Object[]{intArrayExtra, intArrayExtra2, pointArr, intArrayExtra3});
    }

    private boolean processDateTimeChanged() {
        SimpleThreadPool.getInstance().execute(new M9.o(23, this));
        return true;
    }

    private boolean processDeviceStorage(boolean z) {
        StorageUtil.setLowStorage(!z);
        Blackboard.postEventGlobal(EventMessage.obtain(1004));
        return true;
    }

    private boolean processKnoxProfileEvent() {
        KnoxUtil.getInstance().reload();
        return true;
    }

    private boolean processNewAutoStory() {
        Blackboard.postBroadcastEventGlobal(EventMessage.obtain(102, (Object) null));
        ThreadUtil.postOnBgThreadDelayed(new i(15), 300);
        return true;
    }

    private boolean processOneDriveMigration(Intent intent) {
        String stringExtra = intent.getStringExtra("LinkState");
        Log.d("GlobalActionReceiver", "processOneDriveMigration " + stringExtra);
        SimpleThreadPool.getInstance().execute(new i(13));
        return true;
    }

    private boolean processPresentationStart(Intent intent) {
        if (!"com.sec.android.gallery3d".equals(intent.getStringExtra("ownerPackageName"))) {
            return post("event/remote/remoteDisplayOccupied", (Object) null);
        }
        return false;
    }

    private boolean processPresentationStop(Intent intent) {
        if (!"com.sec.android.gallery3d".equals(intent.getStringExtra("ownerPackageName"))) {
            return post("event/remote/remoteDisplayReleased", (Object) null);
        }
        return false;
    }

    private boolean processScloudMigration(Intent intent) {
        int intExtra = intent.getIntExtra("migration_state", -1);
        Log.d("GlobalActionReceiver", "processScloudMigration " + intExtra);
        Blackboard.postBroadcastEventGlobal(EventMessage.obtain(1061, Integer.valueOf(intExtra)));
        return true;
    }

    private boolean processSharingServiceStopped() {
        ThreadUtil.postOnBgThread(new i(14));
        return true;
    }

    private boolean processSignOutCompleted() {
        SamsungCloudSdk.initAccountInfo((SamsungAccountInfo) null);
        return true;
    }

    private boolean processSmartSwitchUpdateDb() {
        BlackboardUtils.forceRefreshPicturesDataGlobal();
        return true;
    }

    private boolean supportScloudMigration() {
        if (!SdkConfig.FirstApiLevel.LESS_THAN_P || PreferenceCache.SCloudMigrationComplete.getBoolean()) {
            return false;
        }
        return true;
    }

    private boolean supportSecMpMigration() {
        if (!SdkConfig.atLeast(SdkConfig.GED.Q) || PreferenceCache.SecMpMigrationCompleted.getBoolean()) {
            return false;
        }
        return true;
    }

    public void onCreate(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        AndroidCompat.registerReceiver(context, this, this.mIntentFilter);
        this.mBroadcastReceivers.add(new PackagesBroadcastReceiver());
        this.mBroadcastReceivers.add(new CloudSyncStatusReceiver());
        this.mBroadcastReceivers.add(new DlnaBroadcastReceiver());
        this.mBroadcastReceivers.forEach(new d(context, 0));
        SamsungCloudCompat.registerSamsungCloud2(context);
        this.mSettingObservers.clear();
        this.mSettingObservers.add(new SettingRotationObserver());
        if (Features.isEnabled(Features.SUPPORT_DUAL_SCREEN)) {
            this.mSettingObservers.add(new SettingRotationSecondObserver());
        }
        this.mSettingObservers.add(new SettingShapeButtonObserver());
        this.mSettingObservers.add(new SettingReduceTransObserver());
        if (Features.isEnabled(Features.SUPPORT_AUTO_BLOCKER)) {
            this.mSettingObservers.add(new SettingSharedAlbumBlockObserver());
        }
        if (SuperHdrConfig.supportSystemConfig()) {
            this.mSettingObservers.add(new SettingSuperHdrObserver());
        }
        this.mSettingObservers.forEach(new d(context, 1));
        this.mContentObservers.clear();
        if (supportSecMpMigration()) {
            this.mContentObservers.add(new SecMpMigrationObserver());
        }
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            this.mContentObservers.add(new UsbStorageObserver());
        }
        if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK)) {
            this.mContentObservers.add(new PDEStateObserver());
        }
        this.mContentObservers.forEach(new d(context, 2));
        a.A(new Object[]{Integer.valueOf(this.mSettingObservers.size()), Integer.valueOf(this.mContentObservers.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("onCreate"), "GlobalActionReceiver");
    }

    public void onDestroy(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        AndroidCompat.unregisterReceiver(context, this);
        this.mBroadcastReceivers.forEach(new d(context, 3));
        this.mBroadcastReceivers.clear();
        SamsungCloudCompat.unregisterSamsungCloud2();
        this.mSettingObservers.forEach(new d(context, 4));
        this.mSettingObservers.clear();
        this.mContentObservers.forEach(new d(context, 5));
        this.mContentObservers.clear();
        clearGlobalConfig();
        a.x(new StringBuilder("onDestroy +"), currentTimeMillis, "GlobalActionReceiver");
    }

    public void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            Log.d("GlobalActionReceiver", "onReceive completed action=" + action);
            if (action != null) {
                if (processAction(context, intent, action)) {
                    return;
                }
            }
            Log.e("GlobalActionReceiver", "onReceive action[" + action + "] not handled");
        } catch (Exception unused) {
            Log.e("GlobalActionReceiver", "onReceive failed " + intent);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean register(android.content.Context r3) {
        /*
            r2 = this;
            java.lang.String r0 = "registerReceiver failed {"
            monitor-enter(r2)
            if (r3 == 0) goto L_0x0015
            java.util.concurrent.atomic.AtomicInteger r1 = r2.mRefCount     // Catch:{ all -> 0x0013 }
            int r1 = r1.getAndIncrement()     // Catch:{ all -> 0x0013 }
            if (r1 != 0) goto L_0x0015
            r2.onCreate(r3)     // Catch:{ all -> 0x0013 }
            monitor-exit(r2)
            r2 = 1
            return r2
        L_0x0013:
            r3 = move-exception
            goto L_0x0035
        L_0x0015:
            if (r3 != 0) goto L_0x0037
            java.lang.String r3 = "GlobalActionReceiver"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0013 }
            r1.<init>(r0)     // Catch:{ all -> 0x0013 }
            java.util.concurrent.atomic.AtomicInteger r0 = r2.mRefCount     // Catch:{ all -> 0x0013 }
            int r0 = r0.get()     // Catch:{ all -> 0x0013 }
            r1.append(r0)     // Catch:{ all -> 0x0013 }
            java.lang.String r0 = "}"
            r1.append(r0)     // Catch:{ all -> 0x0013 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x0013 }
            com.samsung.android.gallery.support.utils.Log.e(r3, r0)     // Catch:{ all -> 0x0013 }
            goto L_0x0037
        L_0x0035:
            monitor-exit(r2)     // Catch:{ all -> 0x0013 }
            throw r3
        L_0x0037:
            monitor-exit(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.receiver.GlobalActionReceiver.register(android.content.Context):boolean");
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("GlobalActionReceiver{\n");
        Iterator<String> actionsIterator = this.mIntentFilter.actionsIterator();
        while (actionsIterator.hasNext()) {
            sb2.append("\t");
            sb2.append(actionsIterator.next());
            sb2.append("\n");
        }
        sb2.append("}");
        return sb2.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean unregister(android.content.Context r3) {
        /*
            r2 = this;
            java.lang.String r0 = "unregisterReceiver failed {"
            monitor-enter(r2)
            if (r3 == 0) goto L_0x0016
            java.util.concurrent.atomic.AtomicInteger r1 = r2.mRefCount     // Catch:{ all -> 0x0014 }
            int r1 = r1.decrementAndGet()     // Catch:{ all -> 0x0014 }
            if (r1 != 0) goto L_0x0016
            r2.onDestroy(r3)     // Catch:{ all -> 0x0014 }
            monitor-exit(r2)
            r2 = 1
            return r2
        L_0x0014:
            r3 = move-exception
            goto L_0x0036
        L_0x0016:
            if (r3 != 0) goto L_0x0038
            java.lang.String r3 = "GlobalActionReceiver"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0014 }
            r1.<init>(r0)     // Catch:{ all -> 0x0014 }
            java.util.concurrent.atomic.AtomicInteger r0 = r2.mRefCount     // Catch:{ all -> 0x0014 }
            int r0 = r0.get()     // Catch:{ all -> 0x0014 }
            r1.append(r0)     // Catch:{ all -> 0x0014 }
            java.lang.String r0 = "}"
            r1.append(r0)     // Catch:{ all -> 0x0014 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x0014 }
            com.samsung.android.gallery.support.utils.Log.e(r3, r0)     // Catch:{ all -> 0x0014 }
            goto L_0x0038
        L_0x0036:
            monitor-exit(r2)     // Catch:{ all -> 0x0014 }
            throw r3
        L_0x0038:
            monitor-exit(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.receiver.GlobalActionReceiver.unregister(android.content.Context):boolean");
    }
}
