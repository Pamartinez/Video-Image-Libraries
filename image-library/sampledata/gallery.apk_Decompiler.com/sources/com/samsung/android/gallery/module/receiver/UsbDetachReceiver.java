package com.samsung.android.gallery.module.receiver;

import A9.a;
import J6.c;
import N2.j;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.ProgressServiceUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UsbDetachReceiver extends BroadcastReceiver {
    private Blackboard mBlackboard = null;
    private String mDeviceName = null;
    private boolean mIsAllDetached = false;
    private boolean mIsImporting = false;
    private MtpClient mMtpClient = null;
    private ProgressServiceUtil mUtil = null;

    /* access modifiers changed from: private */
    /* renamed from: checkMtpEnvironment */
    public void lambda$closeDevice$1(Context context, String str, Blackboard blackboard) {
        this.mBlackboard = blackboard;
        if ("MtpImportService".equals(blackboard.read("data://running_service"))) {
            this.mIsImporting = true;
            this.mUtil.stopMtpService(context, this.mDeviceName);
        }
        StringBuilder k = j.k("check mtp environment [", str, "][");
        k.append(this.mIsImporting);
        k.append("]");
        Log.i("UsbDetachReceiver", k.toString());
        if (!restartIfNecessary()) {
            updateMtpState();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: closeDevice */
    public synchronized void lambda$onDetached$0(Context context, UsbDevice usbDevice) {
        this.mMtpClient = MtpClient.getInstance(context.getApplicationContext());
        this.mDeviceName = usbDevice.getDeviceName();
        if (unableToClose(usbDevice)) {
            Log.w("UsbDetachReceiver", this.mDeviceName + " is not closable");
            return;
        }
        this.mMtpClient.closeDevice(this.mDeviceName);
        boolean isAvailable = this.mMtpClient.isAvailable();
        this.mIsAllDetached = !isAvailable;
        if (!isAvailable) {
            CacheManager.getInstance().clear(3);
        }
        this.mUtil = new ProgressServiceUtil((String) null);
        Blackboard.getBlackboardMap().forEach(new a(9, this, context));
    }

    private void finishGallery() {
        GalleryPreference.getInstance().saveState("location://variable/currentv1", "location://timeline");
        this.mBlackboard.post("command://request_suicide", (Object) null);
    }

    private void finishMtpFragment(String str) {
        if ("location://mtp".equals(str) && this.mIsAllDetached) {
            Log.d("UsbDetachReceiver", "current is mtp tab and all devices are detached, so move to pictures tab");
            if (!isDrawerMode()) {
                this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
            } else {
                this.mBlackboard.post("command://bottomtab/select", "location://timeline");
            }
        }
    }

    private MediaItem getCurrentMediaItem(String str) {
        return (MediaItem) this.mBlackboard.read(ArgumentsUtil.getArgValue(str, "media_item"));
    }

    private boolean isDrawerMode() {
        return ((Boolean) this.mBlackboard.read("data://drawer_tab_enabled", Boolean.FALSE)).booleanValue();
    }

    private boolean isMtpImageViewer(String str) {
        MediaItem currentMediaItem;
        try {
            if (LocationKey.isContentViewer(str) && (currentMediaItem = getCurrentMediaItem(str)) != null && currentMediaItem.isMtp()) {
                return Objects.equals(this.mMtpClient.getDeviceNameFromPath(currentMediaItem.getPath()), this.mDeviceName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isMtpItemList(String str) {
        try {
            return Objects.equals(this.mMtpClient.getDeviceNameFromPath(ArgumentsUtil.getArgValue(str, "__absPath")), this.mDeviceName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean restartIfNecessary() {
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mBlackboard);
        if (readLocationKeyCurrent == null || "null".equals(readLocationKeyCurrent)) {
            if (!this.mBlackboard.getName().equals(Blackboard.getApplicationInstance().getName())) {
                Log.w("UsbDetachReceiver", "current is null and blackboard is not global instance, so finish gallery");
                finishGallery();
            }
            return false;
        }
        Log.d("UsbDetachReceiver", "current location : ".concat(readLocationKeyCurrent));
        if (isMtpImageViewer(readLocationKeyCurrent)) {
            Log.d("UsbDetachReceiver", "current is mtp image viewer, so restart gallery activity");
            this.mBlackboard.postBroadcastEvent(EventMessage.obtain(1063, 1, 0, (Object) null));
            return true;
        } else if (isMtpItemList(readLocationKeyCurrent)) {
            Log.d("UsbDetachReceiver", "current is mtp item list, so Restart gallery activity [" + this.mIsImporting + "]");
            if (!this.mIsImporting) {
                this.mBlackboard.postBroadcastEvent(EventMessage.obtain(1063, 1, 0, (Object) null));
            }
            return true;
        } else {
            finishMtpFragment(readLocationKeyCurrent);
            return false;
        }
    }

    private boolean unableToClose(UsbDevice usbDevice) {
        if (TextUtils.isEmpty(this.mDeviceName) || !this.mMtpClient.isCamera(usbDevice) || !this.mMtpClient.isDeviceOpened(this.mDeviceName)) {
            return true;
        }
        return false;
    }

    private void updateMtpState() {
        if (this.mIsAllDetached) {
            this.mBlackboard.post("command://UpdateBottomNavigationItem", (Object) null);
        } else {
            BlackboardUtils.postEventDataChanged(this.mBlackboard, EventMessage.obtain(105, 1, 0, (Object) null));
        }
    }

    public void onDetached(Context context, Intent intent) {
        if (Features.isEnabled(Features.IS_KNOX_MODE)) {
            Log.w("UsbDetachReceiver", "unable to handle broadcast, knox mode");
            return;
        }
        UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra(IdentityApiContract.Parameter.DEVICE);
        if (usbDevice == null) {
            Log.w("UsbDetachReceiver", "unable to handle broadcast, device is null");
        } else {
            SimpleThreadPool.getInstance().execute(new c(this, context, usbDevice, 25));
        }
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d("UsbDetachReceiver", "received action [" + action + "]");
        if (!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
            Log.w("UsbDetachReceiver", "unable to handle broadcast, not in option");
        } else {
            onDetached(context, intent);
        }
    }
}
