package com.samsung.android.gallery.module.remote.v2;

import A.a;
import N2.j;
import R6.c;
import S9.b;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayStatusListener;
import com.samsung.android.gallery.support.library.abstraction.WifiDisplayParameterListener;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoteDisplayState {
    /* access modifiers changed from: private */
    public static final Uri APP_CAST_URI = Uri.parse("content://com.samsung.android.smartmirroring/app_cast_sent_result");
    private static final Uri SMART_VIEW_HIGH_RESOLUTION_CHECK_URI = Uri.parse("content://com.samsung.android.smartmirroring/high_resolution_mode_support_display");
    private static final RemoteDisplayState sInstance = new RemoteDisplayState();
    private final Object LOCK = new Object();
    /* access modifiers changed from: private */
    public boolean mAppCastRunning = false;
    private final ContentObserver mAppCastStatusObserver = new ContentObserver(ThreadUtil.getBackgroundThreadHandler()) {
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            try {
                long currentTimeMillis = System.currentTimeMillis();
                RemoteDisplayState.this.mAppCastRunning = Boolean.parseBoolean(AppResources.getAppContext().getContentResolver().getType(RemoteDisplayState.APP_CAST_URI));
                Log.rm("RemoteDisplayState", "isAppCastRunning " + Logger.vt(Boolean.valueOf(z), Boolean.valueOf(RemoteDisplayState.this.mAppCastRunning), Long.valueOf(currentTimeMillis)));
            } catch (Exception e) {
                j.s(e, new StringBuilder("isAppCastRunning failed e="), "RemoteDisplayState");
            }
            RemoteDisplayState.this.mMirroringState.updateParam(MirroringConnectionParam.APP_CASTING, RemoteDisplayState.this.mAppCastRunning);
        }
    };
    private Display mConnectedDisplay;
    /* access modifiers changed from: private */
    public int mConnectedDisplayId = -1;
    private final DisplayManager.DisplayListener mDisplayListener = new DisplayManager.DisplayListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDisplayAdded$0(int i2) {
            String str;
            if (i2 == 0 || (RemoteDisplayState.this.mDisplayManager != null && RemoteDisplayState.this.mDisplayManager.getDisplay(i2) == null)) {
                Log.rm("RemoteDisplayState", "onDisplayAdded ignore id {" + i2 + "}");
                return;
            }
            if (RemoteDisplayState.this.mDisplayManager != null) {
                str = RemoteDisplayState.this.mDisplayManager.getDisplayName(i2);
            } else {
                str = "NULL";
            }
            if ("HiddenSpace".equals(str)) {
                Log.rm("RemoteDisplayState", "onDisplayAdded ignore for HiddenSpace {" + i2 + "}");
                return;
            }
            Log.rm("RemoteDisplayState", "onDisplayAdded" + Logger.v(Integer.valueOf(i2), Integer.valueOf(RemoteDisplayState.this.mConnectedDisplayId), str));
            RemoteDisplayState.this.updateState();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDisplayRemoved$1(int i2) {
            Log.rm("RemoteDisplayState", "onDisplayRemoved" + Logger.v(Integer.valueOf(i2), Integer.valueOf(RemoteDisplayState.this.mConnectedDisplayId)));
            if (i2 != -1 && i2 != 0 && i2 == RemoteDisplayState.this.mConnectedDisplayId) {
                RemoteDisplayState.this.setMirroringState(MirroringConnectionState.DISCONNECTED);
            }
        }

        public void onDisplayAdded(int i2) {
            RemoteDisplayState.this.mExecutor.submit(new b(this, i2, 1));
        }

        public void onDisplayChanged(int i2) {
            Log.rm("RemoteDisplayState", "onDisplayChanged" + Logger.v(Integer.valueOf(i2), Integer.valueOf(RemoteDisplayState.this.mConnectedDisplayId)));
            if (i2 != -1 && i2 != 0 && i2 == RemoteDisplayState.this.mConnectedDisplayId) {
                Blackboard.getApplicationInstance().post("event/remote/requestRemoteDisplayData", (Object) null);
            }
        }

        public void onDisplayRemoved(int i2) {
            RemoteDisplayState.this.mExecutor.submit(new b(this, i2, 0));
        }
    };
    /* access modifiers changed from: private */
    public DisplayManagerCompat mDisplayManager;
    /* access modifiers changed from: private */
    public final ExecutorService mExecutor = Executors.newSingleThreadExecutor(new Object());
    private final DisplayStatusListener mExtendedDisplayListener = new DisplayStatusListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onConnectionStatusChanged$0(int i2) {
            Object obj;
            StringBuilder sb2 = new StringBuilder("onConnectionStatusChanged : {");
            if (i2 == 2) {
                obj = "Connected";
            } else if (i2 == 0) {
                obj = "NotConnected";
            } else {
                obj = Integer.valueOf(i2);
            }
            sb2.append(obj);
            sb2.append("}");
            Log.rm("RemoteDisplayState", sb2.toString());
            if (i2 == 0) {
                RemoteDisplayState.this.setMirroringState(MirroringConnectionState.DISCONNECTED);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onScreenSharingStatusChanged$1(int i2) {
            Object obj;
            StringBuilder sb2 = new StringBuilder("onScreenSharingStatusChanged : {");
            if (i2 == 6) {
                obj = "Resumed";
            } else if (i2 == 7) {
                obj = "Paused";
            } else {
                obj = Integer.valueOf(i2);
            }
            sb2.append(obj);
            sb2.append("}");
            Log.rm("RemoteDisplayState", sb2.toString());
            if (i2 == 6) {
                RemoteDisplayState.this.mMirroringState.removeParam(MirroringConnectionParam.PAUSED);
            } else if (i2 == 7) {
                RemoteDisplayState.this.mMirroringState.addParam(MirroringConnectionParam.PAUSED);
                Blackboard.postGlobal("command://FinishDlnaActivity", (Object) null);
            }
        }

        public void onConnectionStatusChanged(int i2) {
            RemoteDisplayState.this.mExecutor.submit(new c(this, i2, 0));
        }

        public void onScreenSharingStatusChanged(int i2) {
            RemoteDisplayState.this.mExecutor.submit(new c(this, i2, 1));
        }
    };
    private Boolean mIsActiveDlnaDevice;
    /* access modifiers changed from: private */
    public final MirroringState mMirroringState = new MirroringState();
    protected final HashSet<Integer> mObserverRegisters = new HashSet<>();
    final WifiDisplayParameterListener mWifiDisplayParameterListener = new b(this);

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.concurrent.ThreadFactory] */
    private RemoteDisplayState() {
    }

    private DisplayManagerCompat getDisplayManagerCompat() {
        if (this.mDisplayManager == null) {
            try {
                this.mDisplayManager = SeApiCompat.getDisplayManagerCompat(AppResources.getAppContext());
            } catch (Exception e) {
                a.s(e, new StringBuilder("getDisplayManager failed e="), "RemoteDisplayState");
            }
        }
        return this.mDisplayManager;
    }

    public static RemoteDisplayState getInstance() {
        return sInstance;
    }

    private boolean isActiveDlnaDevice() {
        DisplayManagerCompat displayManagerCompat = getDisplayManagerCompat();
        if (displayManagerCompat != null) {
            return displayManagerCompat.hasActiveDlnaDevice();
        }
        return false;
    }

    private boolean isAppCastRunning() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            boolean parseBoolean = Boolean.parseBoolean(AppResources.getAppContext().getContentResolver().getType(APP_CAST_URI));
            Log.rm("RemoteDisplayState", "isAppCastRunning " + Logger.vt(Boolean.valueOf(parseBoolean), Long.valueOf(currentTimeMillis)));
            return parseBoolean;
        } catch (Exception e) {
            j.s(e, new StringBuilder("isAppCastRunning failed e="), "RemoteDisplayState");
            return false;
        }
    }

    private boolean isSupportHighRes() {
        try {
            return Boolean.parseBoolean(AppResources.getAppContext().getContentResolver().getType(SMART_VIEW_HIGH_RESOLUTION_CHECK_URI));
        } catch (Exception e) {
            Log.rme("RemoteDisplayState", "isSupportHighRes failed e=" + e.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$new$0(Runnable runnable) {
        return new Thread(runnable, "RemoteDisplayState");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(Map map, String str) {
        boolean z;
        MirroringState mirroringState = this.mMirroringState;
        MirroringConnectionParam mirroringConnectionParam = MirroringConnectionParam.DMR;
        if (!Boolean.TRUE.equals(map.get("dmr")) || "multi".equals(str)) {
            z = false;
        } else {
            z = true;
        }
        mirroringState.updateParam(mirroringConnectionParam, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(String str, String str2, Map map) {
        this.mExecutor.submit(new c(this, map, str2, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$4() {
        this.mMirroringState.updateParam(MirroringConnectionParam.VIDEO_PLAYING, isVideoPlayingOnRemote());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$5() {
        this.mMirroringState.updateParam(MirroringConnectionParam.APP_CASTING, isAppCastRunning());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$unregisterObserver$1() {
        setMirroringState(MirroringConnectionState.UNKNOWN);
    }

    private void registerAppCastObserver() {
        try {
            AppResources.getAppContext().getContentResolver().registerContentObserver(APP_CAST_URI, false, this.mAppCastStatusObserver);
            Log.rm("RemoteDisplayState", "registerAppCastObserver");
        } catch (SecurityException unused) {
            Log.rme("RemoteDisplayState", "registerAppCastObserver failed. not supported");
        } catch (Exception e) {
            Log.rme("RemoteDisplayState", "registerAppCastObserver failed. e=" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void setMirroringState(MirroringConnectionState mirroringConnectionState) {
        Log.rm("RemoteDisplayState", "setState : " + mirroringConnectionState);
        if (mirroringConnectionState == MirroringConnectionState.UNKNOWN || mirroringConnectionState == MirroringConnectionState.DISCONNECTED) {
            this.mConnectedDisplayId = -1;
            this.mConnectedDisplay = null;
            this.mIsActiveDlnaDevice = null;
        }
        this.mMirroringState.setConnectionState(mirroringConnectionState);
    }

    private void unregisterAppCastObserver() {
        try {
            AppResources.getAppContext().getContentResolver().unregisterContentObserver(this.mAppCastStatusObserver);
            Log.rm("RemoteDisplayState", "unregisterAppCastObserver " + this);
        } catch (SecurityException unused) {
            Log.rme("RemoteDisplayState", "unregisterAppCastObserver failed. not supported");
        } catch (Exception e) {
            Log.rme("RemoteDisplayState", "unregisterAppCastObserver failed e=" + e.getMessage());
        }
    }

    private void updateConnectionParams() {
        boolean z;
        this.mMirroringState.updateParam(MirroringConnectionParam.PAUSED, this.mDisplayManager.isScreenSharingPaused());
        this.mMirroringState.updateParam(MirroringConnectionParam.VIDEO_PLAYING, isVideoPlayingOnRemote());
        this.mMirroringState.updateParam(MirroringConnectionParam.APP_CASTING, isAppCastRunning());
        MirroringState mirroringState = this.mMirroringState;
        MirroringConnectionParam mirroringConnectionParam = MirroringConnectionParam.DMR;
        boolean z3 = false;
        if (!this.mDisplayManager.isWifiDisplayDmrSupported() || this.mDisplayManager.isWifiDisplayMultiViewMode()) {
            z = false;
        } else {
            z = true;
        }
        mirroringState.updateParam(mirroringConnectionParam, z);
        MirroringState mirroringState2 = this.mMirroringState;
        MirroringConnectionParam mirroringConnectionParam2 = MirroringConnectionParam.HIGH_RES;
        if (SdkConfig.atLeast(SdkConfig.GED.T) && isSupportHighRes()) {
            z3 = true;
        }
        mirroringState2.updateParam(mirroringConnectionParam2, z3);
    }

    /* access modifiers changed from: private */
    public void updateState() {
        long currentTimeMillis = System.currentTimeMillis();
        DisplayManagerCompat displayManagerCompat = this.mDisplayManager;
        if (displayManagerCompat == null) {
            Log.rme("RemoteDisplayState", "updateStatus failed. displayManager is null");
            return;
        }
        int primaryPresentationId = displayManagerCompat.getPrimaryPresentationId();
        this.mConnectedDisplayId = primaryPresentationId;
        if (primaryPresentationId <= 0 || this.mDisplayManager.isHdmiConnected() || this.mDisplayManager.isConnectedCameraController()) {
            unregisterAppCastObserver();
            setMirroringState(MirroringConnectionState.DISCONNECTED);
        } else {
            this.mConnectedDisplay = this.mDisplayManager.getDisplay(this.mConnectedDisplayId);
            registerAppCastObserver();
            updateConnectionParams();
            setMirroringState(MirroringConnectionState.CONNECTED);
        }
        Log.rm("RemoteDisplayState", "updateStatus" + Logger.vt(toString(), Long.valueOf(currentTimeMillis)));
    }

    public Display getConnectedDisplay() {
        return this.mConnectedDisplay;
    }

    public boolean isBackgroundPlaying() {
        return this.mMirroringState.isBackgroundPlaying();
    }

    public boolean isConnected() {
        return this.mMirroringState.isConnected();
    }

    public boolean isDMRConnected() {
        return this.mMirroringState.isDMRConnected();
    }

    public boolean isDlnaConnected() {
        if (this.mIsActiveDlnaDevice == null) {
            this.mIsActiveDlnaDevice = Boolean.valueOf(isActiveDlnaDevice());
        }
        return this.mIsActiveDlnaDevice.booleanValue();
    }

    public boolean isPauseConnected() {
        return this.mMirroringState.isPaused();
    }

    public boolean isVideoPlayingOnRemote() {
        Boolean bool = this.mIsActiveDlnaDevice;
        if (bool != null && bool.booleanValue()) {
            return this.mDisplayManager.isActiveDlnaUsedByVideo();
        }
        String presentationOwner = this.mDisplayManager.getPresentationOwner(this.mConnectedDisplayId);
        if (presentationOwner == null || !presentationOwner.contains("com.samsung.android.video")) {
            return false;
        }
        return true;
    }

    public void onDlnaConnected() {
        this.mIsActiveDlnaDevice = Boolean.TRUE;
        Blackboard.postGlobal("global://remote2/event/smart_view_status_updated", (Object) null);
    }

    public void onDlnaDisconnected() {
        this.mIsActiveDlnaDevice = null;
        Blackboard.postGlobal("global://remote2/event/smart_view_status_updated", (Object) null);
    }

    public void onResume() {
        if (this.mMirroringState.isConnected()) {
            this.mExecutor.submit(new S9.c(this, 2));
            this.mExecutor.submit(new S9.c(this, 3));
        }
    }

    public void registerObserver(ObserverRegister observerRegister) {
        if (this.mObserverRegisters.isEmpty()) {
            try {
                DisplayManagerCompat displayManagerCompat = getDisplayManagerCompat();
                if (displayManagerCompat != null) {
                    displayManagerCompat.registerDisplayListener(this.mDisplayListener, new Handler(Looper.getMainLooper()));
                    displayManagerCompat.registerDisplayStatusListener(this.mExtendedDisplayListener, new Handler(Looper.getMainLooper()));
                    if (Features.isEnabled(Features.USE_SCREEN_SHARING)) {
                        displayManagerCompat.registerWifiDisplayParameterListener(this.mWifiDisplayParameterListener, new Handler(Looper.getMainLooper()));
                    }
                } else {
                    Log.e("RemoteDisplayState", "registerObserver failed. null display manager");
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("registerObserver failed e="), "RemoteDisplayState");
            }
        }
        synchronized (this.LOCK) {
            this.mObserverRegisters.add(Integer.valueOf(observerRegister.getRegisterKey()));
        }
        this.mExecutor.submit(new S9.c(this, 0));
    }

    public boolean supportHighRes() {
        return this.mMirroringState.supportHighRes();
    }

    public String toString() {
        return Logger.v(this.mMirroringState, this.mConnectedDisplay, Integer.valueOf(this.mConnectedDisplayId), this.mIsActiveDlnaDevice, Boolean.valueOf(this.mAppCastRunning));
    }

    public void unregisterObserver(ObserverRegister observerRegister) {
        ExecutorService executorService;
        S9.c cVar;
        synchronized (this.LOCK) {
            this.mObserverRegisters.remove(Integer.valueOf(observerRegister.getRegisterKey()));
        }
        if (this.mObserverRegisters.isEmpty()) {
            try {
                DisplayManagerCompat displayManagerCompat = getDisplayManagerCompat();
                if (displayManagerCompat != null) {
                    displayManagerCompat.unregisterDisplayListener(this.mDisplayListener);
                    displayManagerCompat.unregisterDisplayStatusListener(this.mExtendedDisplayListener);
                    if (Features.isEnabled(Features.USE_SCREEN_SHARING)) {
                        displayManagerCompat.unregisterWifiDisplayParameterListener(this.mWifiDisplayParameterListener);
                    }
                } else {
                    Log.e("RemoteDisplayState", "unregisterObserver failed. null display manager");
                }
                executorService = this.mExecutor;
                cVar = new S9.c(this, 1);
            } catch (Exception e) {
                Log.e("RemoteDisplayState", "unregisterObserver failed e=" + e.getMessage());
                executorService = this.mExecutor;
                cVar = new S9.c(this, 1);
            } catch (Throwable th) {
                this.mExecutor.submit(new S9.c(this, 1));
                throw th;
            }
            executorService.submit(cVar);
        }
    }
}
