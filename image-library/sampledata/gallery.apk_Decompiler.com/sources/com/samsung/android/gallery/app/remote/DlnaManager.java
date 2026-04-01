package com.samsung.android.gallery.app.remote;

import V3.b;
import Z3.a;
import android.content.Context;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.allshare.Device;
import com.samsung.android.gallery.app.remote.dlna.DlnaDisconnectDialog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remote.dlna.DlnaHelper;
import com.samsung.android.gallery.module.remote.dlna.DlnaItemInfo;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.RandomNumber;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DlnaManager {
    private final Object LOCK = new Object();
    private int mConnectedId;
    private String mDeviceAddress;
    private DlnaDisconnectDialog mDisconnectDialog;
    private DisplayManagerCompat mDisplayManager = SeApiCompat.getDisplayManagerCompat(AppResources.getAppContext());
    private DlnaHelper mDlnaHelper = null;
    private DlnaItemInfo mDlnaItemInfo;
    private int mGalleryId;
    private boolean mIsBound;
    private MediaItem mMediaItem;
    private String mP2PAddress;
    private boolean mRequestDlna;
    private Runnable mRequestDlnaRunnable;

    private boolean connectDlnaInternal(String str, boolean z) {
        if (str == null) {
            Log.rm("DlnaManager", "connectDlna failed. null address");
            return false;
        }
        Device selectDisplayDeviceId = this.mDlnaHelper.getSelectDisplayDeviceId(str, getFindDeviceKey(str));
        if (selectDisplayDeviceId == null) {
            Log.rme("DlnaManager", "connectDlna failed. null selectDevice");
            return false;
        }
        this.mDlnaHelper.setDlnaSwitching(z);
        this.mDlnaHelper.changePlayer(this.mGalleryId, selectDisplayDeviceId.getID(), this.mDlnaItemInfo);
        Log.rm("DlnaManager", "connectDlna" + Logger.v(str));
        return true;
    }

    private boolean disconnectDlna(String str) {
        if (str == null) {
            Log.rmv("DlnaManager", "disconnectDlna failed. null address");
            return false;
        }
        if (this.mDlnaHelper.getSelectDisplayDeviceId(str, getFindDeviceKey(str)) == null) {
            Log.rme("DlnaManager", "disconnectDlna failed. null selectDevice");
            return false;
        }
        Log.rm("DlnaManager", "disconnectDlna" + Logger.v(str));
        this.mDlnaHelper.stopPlayer();
        return true;
    }

    private int getFindDeviceKey(String str) {
        if (str == null) {
            return -1;
        }
        if (str.startsWith("uuid:")) {
            return 3;
        }
        if (str.contains(NumericEnum.SEP)) {
            return 1;
        }
        return 0;
    }

    private boolean isDlnaContinuousable() {
        return !RemoteDisplayState.getInstance().isDMRConnected();
    }

    /* access modifiers changed from: private */
    public void onDlnaDisplayDisconnected() {
        DlnaDisconnectDialog dlnaDisconnectDialog = this.mDisconnectDialog;
        if (dlnaDisconnectDialog != null) {
            dlnaDisconnectDialog.showDialog();
        }
    }

    /* access modifiers changed from: private */
    public void onDlnaServiceCreated() {
        this.mIsBound = true;
        if (this.mRequestDlna) {
            requestDlnaConnection(this.mDeviceAddress, this.mMediaItem, false);
            return;
        }
        Runnable runnable = this.mRequestDlnaRunnable;
        this.mRequestDlnaRunnable = null;
        if (runnable != null) {
            ThreadUtil.postOnBgThreadDelayed(runnable, 500);
        }
    }

    private boolean requestDlnaConnection(String str, MediaItem mediaItem, boolean z) {
        Log.rm("DlnaManager", "requestDlnaConnection" + Logger.v(str));
        this.mDeviceAddress = str;
        boolean item = setItem(mediaItem);
        if (!RemoteDisplayState.getInstance().isDMRConnected() || item) {
            return connectDlnaInternal(str, z);
        }
        Log.rmv("DlnaManager", "requestDlnaConnection failed. item is not prepared yet.");
        return false;
    }

    private boolean setItem(MediaItem mediaItem) {
        String str;
        if (mediaItem == null) {
            Log.rm("DlnaManager", "setItem failed. null item");
            return false;
        }
        this.mMediaItem = mediaItem;
        String displayName = mediaItem.getDisplayName();
        if (displayName != null) {
            str = FileUtils.getBaseName(displayName);
        } else {
            str = "";
        }
        DlnaItemInfo dlnaItemInfo = new DlnaItemInfo(mediaItem.getPath(), mediaItem.getMimeType(), str, mediaItem.getCloudServerId(), mediaItem.getCloudId());
        this.mDlnaItemInfo = dlnaItemInfo;
        this.mDlnaHelper.setItemInfo(dlnaItemInfo);
        return true;
    }

    private void showMotionPhotoToast(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isMotionPhoto()) {
            Utils.showToast(AppResources.getAppContext(), (int) R.string.motion_photo_toast_using_dlna_original, 0);
        }
    }

    public boolean connectDlna(String str, MediaItem mediaItem, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        StringBuilder sb2 = new StringBuilder("connectDlna");
        sb2.append(Logger.v(str, "switching=" + z));
        Log.rm("DlnaManager", sb2.toString());
        return requestDlnaConnection(str, mediaItem, z);
    }

    public boolean connectOriginalContents(int i2, MediaItem mediaItem) {
        String str;
        DisplayManagerCompat displayManagerCompat = this.mDisplayManager;
        if (displayManagerCompat != null && displayManagerCompat.isActiveDlnaUsedByVideo()) {
            Utils.showToast(AppResources.getAppContext(), (int) R.string.send_dlna_error_toast_launched_video_player, 0);
            return false;
        } else if (this.mP2PAddress != null) {
            Utils.showToast(AppResources.getAppContext(), (int) R.string.unable_to_play_presentation, 0);
            return false;
        } else {
            DisplayManagerCompat displayManagerCompat2 = this.mDisplayManager;
            if (displayManagerCompat2 != null) {
                str = displayManagerCompat2.getP2pAddress();
            } else {
                str = null;
            }
            if (str == null || !connectDlna(str, mediaItem, true)) {
                Log.rme("DlnaManager", "connectOriginalContents failed. can not get p2p Address {" + str + "}");
                return false;
            }
            this.mP2PAddress = str;
            showMotionPhotoToast(mediaItem);
            Blackboard.postEventGlobal(EventMessage.obtain(9003, Integer.valueOf(i2)));
            this.mConnectedId = i2;
            return true;
        }
    }

    public DlnaManager create(String str, MediaItem mediaItem) {
        boolean z;
        Context context = Blackboard.getContext();
        this.mGalleryId = RandomNumber.nextInt();
        this.mDisconnectDialog = new DlnaDisconnectDialog(context);
        if (str != null) {
            z = true;
        } else {
            z = false;
        }
        this.mRequestDlna = z;
        this.mDeviceAddress = str;
        this.mMediaItem = mediaItem;
        this.mDlnaHelper = new DlnaHelper(context, new a(this));
        startDlnaService();
        Log.rm("DlnaManager", "create");
        return this;
    }

    public void destroy() {
        stopDlnaService();
        this.mDisplayManager = null;
        this.mRequestDlna = false;
        this.mDeviceAddress = null;
        this.mMediaItem = null;
        Log.rm("DlnaManager", "destroy");
    }

    public boolean disconnectOriginalContents() {
        if (this.mP2PAddress == null || this.mDlnaHelper.isConnecting() || !requestDlnaDisconnection()) {
            return false;
        }
        this.mConnectedId = 0;
        this.mP2PAddress = null;
        return true;
    }

    public boolean isBound() {
        return this.mIsBound;
    }

    public boolean isConnectedDlna() {
        return this.mDlnaHelper.isPlaying();
    }

    public boolean requestDlnaDisconnection() {
        String str;
        Log.rm("DlnaManager", "requestDlnaDisconnection");
        if (this.mDeviceAddress == null) {
            DisplayManagerCompat displayManagerCompat = this.mDisplayManager;
            if (displayManagerCompat != null) {
                str = displayManagerCompat.getActiveDlnaDeviceUid();
            } else {
                str = null;
            }
            this.mDeviceAddress = str;
        }
        return disconnectDlna(this.mDeviceAddress);
    }

    public void setOnDlnaServiceBound(Runnable runnable) {
        this.mRequestDlnaRunnable = runnable;
    }

    public void startDlnaService() {
        if (!RemoteDisplayState.getInstance().isDMRConnected() && !this.mRequestDlna) {
            return;
        }
        if (!this.mIsBound) {
            DlnaHelper dlnaHelper = this.mDlnaHelper;
            if (dlnaHelper != null) {
                dlnaHelper.startDlnaService(new U3.a(26, this));
            }
        } else if (this.mRequestDlna) {
            connectDlna(this.mDeviceAddress, this.mMediaItem, false);
        } else {
            Log.rm("DlnaManager", "startDlnaService skip {" + this.mIsBound + ArcCommonLog.TAG_COMMA + this.mRequestDlna + "}");
        }
    }

    public void stopDlnaService() {
        synchronized (this.LOCK) {
            try {
                DisplayManagerCompat displayManagerCompat = this.mDisplayManager;
                if (displayManagerCompat == null || !displayManagerCompat.isActiveDlnaUsedByVideo()) {
                    Log.rm("DlnaManager", "stopDlnaService");
                    requestDlnaDisconnection();
                    DlnaHelper dlnaHelper = this.mDlnaHelper;
                    Objects.requireNonNull(dlnaHelper);
                    ThreadUtil.postOnBgThread(new b(18, dlnaHelper));
                    this.mIsBound = false;
                    this.mDeviceAddress = null;
                    return;
                }
                Log.rm("DlnaManager", "stopDlnaService skip. used by video");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void updateContents(MediaItem mediaItem) {
        if (mediaItem != null && isConnectedDlna() && isDlnaContinuousable()) {
            Log.rm("DlnaManager", "updateDlnaContents :DlnaHelper#playImageContinuously");
            this.mDlnaHelper.playImageContinuously(this.mGalleryId, new DlnaItemInfo(mediaItem.getPath(), mediaItem.getMimeType(), mediaItem.getDisplayName(), mediaItem.getCloudServerId(), mediaItem.getCloudId()));
        }
    }
}
