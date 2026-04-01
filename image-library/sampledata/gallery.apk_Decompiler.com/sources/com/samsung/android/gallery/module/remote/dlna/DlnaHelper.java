package com.samsung.android.gallery.module.remote.dlna;

import N2.j;
import Z3.a;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.allshare.Device;
import com.samsung.android.allshare.DeviceFinder;
import com.samsung.android.allshare.ERROR;
import com.samsung.android.allshare.Item;
import com.samsung.android.allshare.ServiceConnector;
import com.samsung.android.allshare.ServiceProvider;
import com.samsung.android.allshare.extension.DeviceChecker;
import com.samsung.android.allshare.media.ContentInfo;
import com.samsung.android.allshare.media.ImageViewer;
import com.samsung.android.allshare.media.MediaDeviceFinder;
import com.samsung.android.allshare.media.MediaServiceProvider;
import com.samsung.android.gallery.app.remote.DlnaManager;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DlnaHelper {
    private int mBeforeConnectionState;
    /* access modifiers changed from: private */
    public final WeakReference<Context> mContext;
    protected MediaDeviceFinder mDeviceFinder;
    private final DeviceFinder.IDeviceFinderEventListener mDeviceListener = new DeviceFinder.IDeviceFinderEventListener() {
        public void onDeviceAdded(String str, Device device, ERROR error) {
            StringBuilder sb2 = new StringBuilder("onDeviceAdded Device{");
            sb2.append(device.getDeviceType());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(device.getName());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(device.getModelName());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(device.getDeviceDomain());
            sb2.append("} ");
            if (str == device.getDeviceType()) {
                str = "";
            }
            sb2.append(str);
            Log.rm("DlnaHelper", sb2.toString());
            synchronized (DlnaHelper.this.mDevices) {
                try {
                    if (!DlnaHelper.this.mDevices.contains(device)) {
                        DlnaHelper.this.mDevices.add(device);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void onDeviceRemoved(Device.DeviceType deviceType, Device device, ERROR error) {
            Device.DeviceType deviceType2;
            StringBuilder sb2 = new StringBuilder("onDeviceRemoved Device{");
            sb2.append(device.getDeviceType());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(device.getName());
            sb2.append("} ");
            if (deviceType != device.getDeviceType()) {
                deviceType2 = deviceType;
            } else {
                deviceType2 = "";
            }
            sb2.append(deviceType2);
            Log.rm("DlnaHelper", sb2.toString());
            synchronized (DlnaHelper.this.mDevices) {
                DlnaHelper.this.mDevices.remove(device);
            }
            if (deviceType == Device.DeviceType.DEVICE_IMAGEVIEWER) {
                DlnaHelper.this.notifyDeviceRemoved(device.getID());
            }
        }
    };
    protected final ArrayList<Device> mDevices = new ArrayList<>();
    private DisplayManagerCompat mDisplayManager;
    protected DlnaItemInfo mDlnaItemInfo;
    protected boolean mIsCalledStopPlayer;
    private boolean mIsDlnaSwitching;
    private boolean mIsNeedToPlayImage;
    protected int mLastPlayedActivityId;
    private final WeakReference<DlnaDisplayListener> mListenerRef;
    private boolean mNeedAllShareUnbind;
    /* access modifiers changed from: private */
    public final ConcurrentLinkedQueue<PlayInfo> mPlayInfoQueue = new ConcurrentLinkedQueue<>();
    private PlayerThread mPlayerThread;
    /* access modifiers changed from: private */
    public MediaServiceProvider mServiceProvider;
    private ImageViewer mTempViewer;
    private boolean mUseAsf = false;
    protected ImageViewer mViewer;
    /* access modifiers changed from: private */
    public boolean mViewerOnPlaying;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DlnaDisplayListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DlnaServiceListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlayInfo {
        public final ImageViewer viewer;

        public PlayInfo(ImageViewer imageViewer) {
            this.viewer = imageViewer;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PlayerThread extends Thread {
        private final Object mLock;

        public /* synthetic */ PlayerThread(DlnaHelper dlnaHelper, int i2) {
            this();
        }

        public void resumeIfWaiting() {
            synchronized (this.mLock) {
                this.mLock.notify();
            }
        }

        public void run() {
            setName("PlayerThread");
            Looper.prepare();
            do {
                PlayInfo playInfo = null;
                while (!DlnaHelper.this.mPlayInfoQueue.isEmpty()) {
                    playInfo = (PlayInfo) DlnaHelper.this.mPlayInfoQueue.poll();
                }
                if (playInfo == null) {
                    Log.rme("PlayerThread", "player failed. null info");
                    return;
                }
                DlnaHelper dlnaHelper = DlnaHelper.this;
                Item prepareItem = dlnaHelper.mDlnaItemInfo.prepareItem((Context) dlnaHelper.mContext.get());
                if (prepareItem == null) {
                    Log.rme("PlayerThread", "player failed. null item to show");
                    DlnaHelper.this.updateActiveDlnaState(playInfo.viewer, 0);
                    return;
                } else if (!DlnaHelper.this.mPlayInfoQueue.isEmpty()) {
                    Log.rm("PlayerThread", "player inserted {" + DlnaHelper.this.mPlayInfoQueue.size() + "}");
                } else {
                    ImageViewer imageViewer = DlnaHelper.this.mViewer;
                    if (!(imageViewer == null || imageViewer == playInfo.viewer)) {
                        imageViewer.stop();
                    }
                    DlnaHelper dlnaHelper2 = DlnaHelper.this;
                    dlnaHelper2.mViewer = playInfo.viewer;
                    if (dlnaHelper2.isVideo()) {
                        Log.rm("PlayerThread", "this is a video file so we do not show to ImageViewer");
                        Utils.showToast((Context) DlnaHelper.this.mContext.get(), R$string.open_video_player_to_play_video_dlna, 0);
                        return;
                    }
                    DlnaHelper dlnaHelper3 = DlnaHelper.this;
                    if (dlnaHelper3.mViewer != null) {
                        dlnaHelper3.mViewerOnPlaying = true;
                        try {
                            DlnaHelper.this.mViewer.show(prepareItem, new ContentInfo.Builder().build());
                        } catch (Exception e) {
                            Log.rme("PlayerThread", "Allshare imageViewer show is failed: e=" + e.getMessage());
                        }
                    }
                }
            } while (!DlnaHelper.this.mPlayInfoQueue.isEmpty());
            try {
                Looper.myLooper().quit();
            } catch (Exception unused) {
            }
        }

        private PlayerThread() {
            this.mLock = new Object();
        }
    }

    public DlnaHelper(Context context, DlnaDisplayListener dlnaDisplayListener) {
        this.mContext = new WeakReference<>(context);
        this.mListenerRef = new WeakReference<>(dlnaDisplayListener);
    }

    private void addDevicesToList(Device.DeviceDomain deviceDomain, Device.DeviceType deviceType) {
        ArrayList arrayList;
        if (deviceDomain == null) {
            arrayList = this.mDeviceFinder.getDevices(deviceType);
        } else {
            arrayList = this.mDeviceFinder.getDevices(deviceDomain, deviceType);
        }
        if (arrayList != null) {
            this.mDevices.addAll(arrayList);
        }
    }

    private boolean checkIsImageType(String str) {
        return str.contains("image");
    }

    private boolean checkIsVideoType(String str) {
        return str.contains("video");
    }

    /* access modifiers changed from: private */
    public void disconnectWithPlayDevice() {
        Log.rm("DlnaHelper", "disconnectWithPlayDevice");
        this.mViewerOnPlaying = false;
        this.mViewer = null;
    }

    private Device findDeviceById(String str) {
        Device findDeviceByIdInner;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.mDevices) {
            findDeviceByIdInner = findDeviceByIdInner(str, this.mDevices);
        }
        return findDeviceByIdInner;
    }

    private ImageViewer findImageViewer(String str, ArrayList<Device> arrayList) {
        return findDevice(arrayList, str, 3);
    }

    private ArrayList<Device> getCheckedDeviceList() {
        Device.DeviceType deviceType = Device.DeviceType.DEVICE_IMAGEVIEWER;
        ArrayList arrayList = new ArrayList();
        synchronized (this.mDevices) {
            try {
                Iterator<Device> it = this.mDevices.iterator();
                while (it.hasNext()) {
                    Device next = it.next();
                    if (next != null && next.getDeviceType() == deviceType) {
                        arrayList.add(next);
                    }
                }
            } catch (ConcurrentModificationException e) {
                Log.rme("DlnaHelper", e.toString());
                return null;
            }
        }
        return DeviceChecker.getDeviceCheckedList(arrayList);
    }

    private DisplayManagerCompat getDisplayManagerCompat() {
        try {
            return SeApiCompat.getDisplayManagerCompat(AppResources.getAppContext());
        } catch (Exception e) {
            Log.rme("DlnaHelper", "getDisplayManagerCompat fail e = " + e.getMessage());
            return null;
        }
    }

    private boolean isDlnaStatusDisconnect(int i2) {
        if (!this.mViewerOnPlaying || i2 != 0) {
            return false;
        }
        return true;
    }

    private boolean isPlayerChangeable(String str) {
        if (str != null && this.mUseAsf) {
            return true;
        }
        StringBuilder k = j.k("isPlayerChangeable failed. {", str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        k.append(!this.mUseAsf);
        k.append("}");
        Log.rme("DlnaHelper", k.toString());
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isVideo() {
        DlnaItemInfo dlnaItemInfo = this.mDlnaItemInfo;
        if (dlnaItemInfo == null || !dlnaItemInfo.getMimeType().contains("video")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void notifyDeviceRemoved(String str) {
        StringBuilder k = j.k("notifyDeviceRemoved {", str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        k.append(this.mViewerOnPlaying);
        k.append("}");
        Log.rm("DlnaHelper", k.toString());
        if (removedDeviceIsPlaying(str)) {
            updateActiveDlnaState(this.mViewer, 0);
            this.mViewer = null;
            this.mViewerOnPlaying = false;
            showRemovedDialog();
        }
    }

    /* access modifiers changed from: private */
    public void onImageViewerDeviceChanged(ImageViewer.ImageViewerState imageViewerState, ERROR error) {
        boolean z;
        StringBuilder sb2 = new StringBuilder("onImageViewerDeviceChanged {s=");
        sb2.append(imageViewerState);
        sb2.append(",e=");
        sb2.append(error);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mViewer != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("}");
        Log.rm("DlnaHelper", sb2.toString());
        if (imageViewerState == ImageViewer.ImageViewerState.CONTENT_CHANGED) {
            stateContentChanged();
        } else if (imageViewerState == ImageViewer.ImageViewerState.SHOWING && error == ERROR.SUCCESS) {
            stateChangedShowing();
        } else if (imageViewerState != ImageViewer.ImageViewerState.STOPPED) {
        } else {
            if (error == ERROR.CONTENT_NOT_AVAILABLE || error == ERROR.FAIL) {
                stateChangedStopOrAbnormalCase();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onImageViewerShowResponseReceived(ERROR error) {
        if (error != ERROR.SUCCESS) {
            if (error == ERROR.PERMISSION_NOT_ALLOWED) {
                Utils.showToast(this.mContext.get(), R$string.permission_denied_dlna_connection, 0);
            }
            updateActiveDlnaState(this.mViewer, 0);
        }
    }

    /* access modifiers changed from: private */
    public void onImageViewerStopResponseReceived(ERROR error) {
        boolean z;
        StringBuilder sb2 = new StringBuilder("onImageViewerStopResponseReceived {e=");
        sb2.append(error);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mViewer != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("}");
        Log.rm("DlnaHelper", sb2.toString());
        ImageViewer imageViewer = this.mViewer;
        if (imageViewer != null) {
            updateActiveDlnaState(imageViewer, 0);
            this.mIsDlnaSwitching = false;
            this.mViewerOnPlaying = false;
            if (this.mNeedAllShareUnbind) {
                stopDlnaService();
                this.mNeedAllShareUnbind = false;
            }
        }
    }

    private void playImage(ImageViewer imageViewer) {
        this.mPlayInfoQueue.offer(new PlayInfo(imageViewer));
        DisplayManagerCompat displayManagerCompat = getDisplayManagerCompat();
        PlayerThread playerThread = this.mPlayerThread;
        if ((playerThread == null || !playerThread.isAlive()) && (displayManagerCompat == null || !displayManagerCompat.isActiveDlnaUsedByVideo())) {
            Log.rm("DlnaHelper", "playImage: run new player thread");
            PlayerThread playerThread2 = new PlayerThread(this, 0);
            this.mPlayerThread = playerThread2;
            playerThread2.start();
        } else if (this.mPlayerThread != null) {
            Log.rm("DlnaHelper", "playImage: resume if waiting");
            this.mPlayerThread.resumeIfWaiting();
        } else {
            Log.rme("DlnaHelper", "playImage: failed by thread");
        }
    }

    private boolean removedDeviceIsPlaying(String str) {
        boolean z;
        ImageViewer imageViewer = this.mViewer;
        if (imageViewer == null || !imageViewer.getID().equals(str)) {
            z = false;
        } else {
            z = true;
        }
        if (!isPlaying() || !z) {
            return false;
        }
        return true;
    }

    private void showRemovedDialog() {
        DlnaDisplayListener dlnaDisplayListener = this.mListenerRef.get();
        if (dlnaDisplayListener != null) {
            ((a) dlnaDisplayListener).f2471a.onDlnaDisplayDisconnected();
        }
    }

    private void stateChangedShowing() {
        this.mViewerOnPlaying = true;
        this.mIsCalledStopPlayer = false;
        updateActiveDlnaState(this.mViewer, 1);
        this.mPlayerThread.resumeIfWaiting();
    }

    private void stateChangedStopOrAbnormalCase() {
        if (RemoteDisplayState.getInstance().isDMRConnected()) {
            this.mViewerOnPlaying = false;
        }
        updateDlnaConnectionState(this.mViewer, 0, true);
        this.mIsDlnaSwitching = false;
        Blackboard.postGlobal("command://FinishDlnaActivity", (Object) null);
    }

    private void stateContentChanged() {
        this.mViewerOnPlaying = false;
        ImageViewer imageViewer = this.mViewer;
        if (imageViewer == null) {
            Log.rm("DlnaHelper", "viewer is not prepared, so skip update dlna state");
        } else {
            updateDlnaConnectionState(imageViewer, 0, true);
        }
    }

    /* access modifiers changed from: private */
    public void updateActiveDlnaState(Device device, int i2) {
        StringBuilder o2 = C0086a.o(i2, "updateActiveDlnaState {new=", ",old=");
        o2.append(this.mBeforeConnectionState);
        o2.append("}");
        Log.rm("DlnaHelper", o2.toString());
        if (i2 != 1) {
            updateDlnaConnectionState(device, i2, false);
        } else if (this.mBeforeConnectionState == 3) {
            updateDlnaConnectionState(device, i2, false);
        }
    }

    private void updateDlnaConnectionState(Device device, int i2, boolean z) {
        DisplayManagerCompat displayManagerCompat;
        if (checkUpdateStatePossible(device) != null && (displayManagerCompat = getDisplayManagerCompat()) != null) {
            if (isDlnaStatusDisconnect(i2) || !isVideo() || z) {
                StringBuilder o2 = C0086a.o(i2, "updateActiveDlnaState state=", ", isSwitchingDevice=");
                o2.append(this.mIsDlnaSwitching);
                Log.rm("DlnaHelper", o2.toString());
                displayManagerCompat.setActiveDlnaState(device, i2, this.mIsDlnaSwitching);
                this.mBeforeConnectionState = i2;
            }
        }
    }

    public void changePlayer(int i2, String str, DlnaItemInfo dlnaItemInfo) {
        String str2;
        boolean z = true;
        if (!isPlayerChangeable(str) || dlnaItemInfo == null) {
            StringBuilder sb2 = new StringBuilder("changePlayer failed. { ");
            if (dlnaItemInfo != null) {
                z = false;
            }
            sb2.append(z);
            sb2.append("}");
            Log.rm("DlnaHelper", sb2.toString());
            return;
        }
        initializeDlnaPlay(str, dlnaItemInfo);
        ImageViewer imageViewer = this.mViewer;
        if (imageViewer != null && imageViewer.getID().equals(str)) {
            Log.rm("DlnaHelper", "show a remain image");
        }
        ArrayList<Device> checkedDeviceList = getCheckedDeviceList();
        if (checkedDeviceList == null) {
            Log.w("DlnaHelper", "changePlayer failed. no checked device list");
            return;
        }
        ImageViewer findImageViewer = findImageViewer(str, checkedDeviceList);
        if (findImageViewer == null) {
            Log.w("DlnaHelper", "changePlayer failed. no such device id=" + str);
            return;
        }
        StringBuilder k = j.k("changePlayer {", str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        ImageViewer imageViewer2 = this.mViewer;
        if (imageViewer2 != null) {
            str2 = imageViewer2.getID();
        } else {
            str2 = "null";
        }
        k.append(str2);
        k.append("}");
        Log.rm("DlnaHelper", k.toString());
        findImageViewer.setEventListener(new R9.a(this));
        findImageViewer.setResponseListener(new ImageViewer.IImageViewerResponseListener() {
            public void onGetStateResponseReceived(ImageViewer.ImageViewerState imageViewerState, ERROR error) {
                Log.rm("DlnaHelper", "onGetStateResponseReceived {e=" + error + "}");
            }

            public void onShowResponseReceived(Item item, ContentInfo contentInfo, ERROR error) {
                Log.rm("DlnaHelper", "onShowResponseReceived {e=" + error + "}");
                DlnaHelper.this.onImageViewerShowResponseReceived(error);
            }

            public void onStopResponseReceived(ERROR error) {
                boolean z;
                StringBuilder sb2 = new StringBuilder("onStopResponseReceived {e=");
                sb2.append(error);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                boolean z3 = false;
                if (DlnaHelper.this.mServiceProvider != null) {
                    z = true;
                } else {
                    z = false;
                }
                sb2.append(z);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (DlnaHelper.this.mViewer != null) {
                    z3 = true;
                }
                sb2.append(z3);
                sb2.append("}");
                Log.rm("DlnaHelper", sb2.toString());
                if (DlnaHelper.this.mServiceProvider != null) {
                    DlnaHelper.this.onImageViewerStopResponseReceived(error);
                    DlnaHelper.this.disconnectWithPlayDevice();
                    DlnaHelper.this.onDlnaServiceDeleted();
                }
            }
        });
        this.mLastPlayedActivityId = i2;
        DlnaItemInfo dlnaItemInfo2 = this.mDlnaItemInfo;
        if (dlnaItemInfo2 == null || dlnaItemInfo2.getFilePath() == null) {
            this.mIsNeedToPlayImage = true;
            this.mTempViewer = findImageViewer;
            return;
        }
        playImage(findImageViewer);
    }

    public final Device checkUpdateStatePossible(Device device) {
        if (!Features.isEnabled(Features.USE_SCREEN_SHARING)) {
            Log.rm("DlnaHelper", "updateActiveDlnaState failed. screen sharing not supported");
            return null;
        } else if (device != null) {
            return device;
        } else {
            if (device != null || this.mViewer != null) {
                return this.mViewer;
            }
            Log.rme("DlnaHelper", "updateActiveDlnaState failed. device is null ");
            return null;
        }
    }

    public Device findDevice(ArrayList<Device> arrayList, String str, int i2) {
        boolean z = true;
        if (!(str == null || arrayList == null)) {
            if (i2 == 2) {
                Iterator<Device> it = arrayList.iterator();
                while (it.hasNext()) {
                    Device next = it.next();
                    if (next != null && str.equals(next.getName())) {
                        return next;
                    }
                }
            } else if (i2 == 3) {
                return findDeviceByIdInner(str, arrayList);
            } else {
                if (i2 == 1) {
                    Iterator<Device> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        Device next2 = it2.next();
                        if (next2 != null && str.equals(next2.getProductCapInfo(Device.InformationType.P2P_MAC_ADDRESS))) {
                            return next2;
                        }
                    }
                }
            }
        }
        StringBuilder u = C0212a.u("findDevice failed {", str, GlobalPostProcInternalPPInterface.SPLIT_REGEX, i2, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (arrayList == null) {
            z = false;
        }
        u.append(z);
        u.append("}");
        Log.rme("DlnaHelper", u.toString());
        return null;
    }

    public Device findDeviceByIdInner(String str, ArrayList<Device> arrayList) {
        Iterator<Device> it = arrayList.iterator();
        while (it.hasNext()) {
            Device next = it.next();
            if (next != null && str.equals(next.getID())) {
                return next;
            }
        }
        return null;
    }

    public Device getSelectDisplayDeviceId(String str, int i2) {
        String str2;
        ArrayList<Device> checkedDeviceList = getCheckedDeviceList();
        if (checkedDeviceList == null) {
            Log.rme("DlnaHelper", "getSelectDisplayDeviceId failed. viewer is null");
            return null;
        }
        Device findDevice = findDevice(checkedDeviceList, str, i2);
        if (findDevice == null) {
            refreshDeviceList();
            findDevice = findDevice(getCheckedDeviceList(), str, i2);
        }
        StringBuilder u = C0212a.u("getSelectDisplayDeviceId key=", str, ", findType=", i2, " > Device{");
        if (findDevice != null) {
            str2 = findDevice.getName();
        } else {
            str2 = "null";
        }
        u.append(str2);
        u.append("}");
        Log.rm("DlnaHelper", u.toString());
        return findDevice;
    }

    public final boolean impossibleStopPlayer() {
        if (this.mViewer == null || this.mIsCalledStopPlayer) {
            return true;
        }
        return false;
    }

    public void initializeDlnaPlay(String str, DlnaItemInfo dlnaItemInfo) {
        if (this.mDisplayManager == null) {
            this.mDisplayManager = getDisplayManagerCompat();
        }
        setItemInfo(dlnaItemInfo);
        updateActiveDlnaState(findDeviceById(str), 3);
    }

    public boolean isConnecting() {
        if (!this.mViewerOnPlaying || this.mBeforeConnectionState != 3) {
            return false;
        }
        return true;
    }

    public boolean isPlaying() {
        return this.mViewerOnPlaying;
    }

    public void onDlnaServiceCreated(ServiceProvider serviceProvider, ServiceConnector.ServiceState serviceState) {
        Log.rm("DlnaHelper", "onDlnaServiceCreated {" + serviceState + "}");
        if (serviceState == ServiceConnector.ServiceState.ENABLED) {
            this.mUseAsf = true;
        }
        this.mServiceProvider = (MediaServiceProvider) serviceProvider;
        MediaDeviceFinder deviceFinder = serviceProvider.getDeviceFinder();
        this.mDeviceFinder = deviceFinder;
        deviceFinder.setDeviceFinderEventListener(Device.DeviceType.DEVICE_PROVIDER, this.mDeviceListener);
        this.mDeviceFinder.setDeviceFinderEventListener(Device.DeviceType.DEVICE_IMAGEVIEWER, this.mDeviceListener);
        refreshDeviceList();
    }

    public void onDlnaServiceDeleted() {
        Log.rm("DlnaHelper", "onDlnaServiceDeleted {" + this.mUseAsf + "}");
        if (this.mUseAsf) {
            this.mUseAsf = false;
            ServiceConnector.deleteServiceProvider(this.mServiceProvider);
            startDlnaService((DlnaServiceListener) null);
            return;
        }
        this.mServiceProvider = null;
        MediaDeviceFinder mediaDeviceFinder = this.mDeviceFinder;
        if (mediaDeviceFinder != null) {
            mediaDeviceFinder.setDeviceFinderEventListener(Device.DeviceType.DEVICE_PROVIDER, (DeviceFinder.IDeviceFinderEventListener) null);
            this.mDeviceFinder.setDeviceFinderEventListener(Device.DeviceType.DEVICE_IMAGEVIEWER, (DeviceFinder.IDeviceFinderEventListener) null);
            this.mDeviceFinder = null;
        }
        ImageViewer imageViewer = this.mViewer;
        if (imageViewer != null) {
            imageViewer.setEventListener((ImageViewer.IImageViewerEventListener) null);
            this.mViewer.setResponseListener((ImageViewer.IImageViewerResponseListener) null);
        }
        this.mDisplayManager = null;
    }

    public boolean playImageContinuously(int i2, DlnaItemInfo dlnaItemInfo) {
        boolean z;
        DisplayManagerCompat displayManagerCompat;
        if (this.mLastPlayedActivityId != i2) {
            Log.rm("DlnaHelper", "playImageContinuously skip {" + this.mViewerOnPlaying + "}");
            if (this.mViewerOnPlaying) {
                stopPlayer();
                disconnectWithPlayDevice();
            }
            return false;
        }
        StringBuilder sb2 = new StringBuilder("playImageContinuously {");
        if (this.mViewer != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(dlnaItemInfo.getMimeType());
        sb2.append("}");
        Log.rm("DlnaHelper", sb2.toString());
        if (!(this.mViewer == null || dlnaItemInfo.getMimeType() == null)) {
            if (checkIsImageType(dlnaItemInfo.getMimeType())) {
                setItemInfo(dlnaItemInfo);
                playImage(this.mViewer);
                return true;
            } else if (checkIsVideoType(dlnaItemInfo.getMimeType()) && ((displayManagerCompat = getDisplayManagerCompat()) == null || !displayManagerCompat.isActiveDlnaUsedByVideo())) {
                Utils.showToast(this.mContext.get(), R$string.open_video_player_to_play_video_dlna, 1);
            }
        }
        return false;
    }

    public void refreshDeviceList() {
        if (this.mDeviceFinder == null) {
            Log.w("DlnaHelper", "refreshDeviceList failed. null device finder");
            return;
        }
        if (!SdkConfig.atLeast(SdkConfig.GED.R)) {
            this.mDeviceFinder.refresh();
        }
        this.mDevices.clear();
        Device.DeviceDomain deviceDomain = Device.DeviceDomain.LOCAL_NETWORK;
        addDevicesToList(deviceDomain, Device.DeviceType.DEVICE_PROVIDER);
        Device.DeviceType deviceType = Device.DeviceType.DEVICE_IMAGEVIEWER;
        addDevicesToList(deviceDomain, deviceType);
        addDevicesToList((Device.DeviceDomain) null, deviceType);
        Log.rm("DlnaHelper", "refreshDeviceList {" + this.mDevices.size() + "}");
    }

    public void setDlnaSwitching(boolean z) {
        this.mIsDlnaSwitching = z;
    }

    public void setItemInfo(DlnaItemInfo dlnaItemInfo) {
        String str;
        if (dlnaItemInfo == null || dlnaItemInfo.getFilePath() == null || dlnaItemInfo.getMimeType() == null || dlnaItemInfo.getName() == null) {
            StringBuilder sb2 = new StringBuilder("setItemInfo failed. invalid item {");
            if (dlnaItemInfo == null) {
                str = "null";
            } else {
                str = dlnaItemInfo.getMimeType();
            }
            sb2.append(str);
            sb2.append("}");
            Log.rm("DlnaHelper", sb2.toString());
            return;
        }
        Log.rm("DlnaHelper", "setItemInfo {" + this.mIsNeedToPlayImage + "}");
        this.mDlnaItemInfo = dlnaItemInfo;
        if (this.mIsNeedToPlayImage) {
            playImage(this.mTempViewer);
            this.mIsNeedToPlayImage = false;
            this.mTempViewer = null;
        }
    }

    public void startDlnaService(final DlnaServiceListener dlnaServiceListener) {
        Log.rm("DlnaHelper", "startDlnaService");
        try {
            Context context = this.mContext.get();
            if (context != null) {
                ServiceConnector.createServiceProvider(context.getApplicationContext(), new ServiceConnector.IServiceConnectEventListener() {
                    public void onCreated(ServiceProvider serviceProvider, ServiceConnector.ServiceState serviceState) {
                        DlnaHelper.this.onDlnaServiceCreated(serviceProvider, serviceState);
                        DlnaServiceListener dlnaServiceListener = dlnaServiceListener;
                        if (dlnaServiceListener != null) {
                            ((DlnaManager) ((U3.a) dlnaServiceListener).e).onDlnaServiceCreated();
                        }
                    }

                    public void onDeleted(ServiceProvider serviceProvider) {
                        if (DlnaHelper.this.isPlaying()) {
                            Log.rm("DlnaHelper", "onDeleted skip");
                        } else {
                            DlnaHelper.this.onDlnaServiceDeleted();
                        }
                    }
                }, "com.samsung.android.allshare.media");
            }
        } catch (NoClassDefFoundError | NoSuchMethodError | SecurityException e) {
            Log.rme("DlnaHelper", "startDlnaService failed" + e);
            throw e;
        }
    }

    public void stopDlnaService() {
        Log.rm("DlnaHelper", "stopDlnaService {" + this.mViewerOnPlaying + "}");
        if (this.mViewerOnPlaying) {
            this.mNeedAllShareUnbind = true;
            return;
        }
        disconnectWithPlayDevice();
        MediaServiceProvider mediaServiceProvider = this.mServiceProvider;
        if (mediaServiceProvider != null) {
            this.mUseAsf = false;
            ServiceConnector.deleteServiceProvider(mediaServiceProvider);
        }
    }

    public void stopPlayer() {
        boolean z = true;
        if (impossibleStopPlayer()) {
            StringBuilder sb2 = new StringBuilder("stopPlayer failed. {");
            if (this.mViewer == null) {
                z = false;
            }
            sb2.append(z);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mIsCalledStopPlayer);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mViewerOnPlaying);
            sb2.append("}");
            Log.rm("DlnaHelper", sb2.toString());
            Blackboard.postGlobal("command://FinishDlnaActivity", (Object) null);
            return;
        }
        Log.rm("DlnaHelper", "stopPlayer {" + this.mViewer.getViewerState() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mViewerOnPlaying + "}");
        if (this.mViewerOnPlaying) {
            this.mViewer.stop();
            this.mIsCalledStopPlayer = true;
        }
        Blackboard.postGlobal("command://FinishDlnaActivity", (Object) null);
    }
}
