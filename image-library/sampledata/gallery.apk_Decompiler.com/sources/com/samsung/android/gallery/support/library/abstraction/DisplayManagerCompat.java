package com.samsung.android.gallery.support.library.abstraction;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.allshare.Device;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DisplayManagerCompat {
    protected final String TAG;
    protected final DisplayManager mDisplayManager;

    public DisplayManagerCompat(Context context) {
        String tag = tag();
        this.TAG = tag;
        DisplayManager displayManager = (DisplayManager) context.getApplicationContext().getSystemService("display");
        this.mDisplayManager = displayManager;
        Log.v(tag, "constructor {" + getSimpleName(context) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getSimpleName(displayManager) + "}");
        if (displayManager == null) {
            throw new IllegalStateException("DISPLAY_SERVICE not available");
        }
    }

    private int getDisplayCount() {
        Display[] displays = this.mDisplayManager.getDisplays();
        if (displays != null) {
            return displays.length;
        }
        return 0;
    }

    private String getDisplayLog(Display display) {
        if (display == null) {
            return "Display(null)";
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealMetrics(displayMetrics);
        StringBuilder sb2 = new StringBuilder("Display(");
        sb2.append(display.getDisplayId());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(display.getName());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(displayMetrics.widthPixels);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, displayMetrics.heightPixels, ")");
    }

    public String getActiveDlnaDeviceUid() {
        return null;
    }

    public final Display getDisplay(int i2) {
        return this.mDisplayManager.getDisplay(i2);
    }

    public final String getDisplayName(int i2) {
        Display display = this.mDisplayManager.getDisplay(i2);
        if (display != null) {
            return display.getName();
        }
        return null;
    }

    public final Display[] getDisplays() {
        return this.mDisplayManager.getDisplays();
    }

    public String getP2pAddress() {
        return null;
    }

    public String getPresentationOwner(int i2) {
        return "";
    }

    public final int getPrimaryPresentationId() {
        Display[] displays = this.mDisplayManager.getDisplays("android.hardware.display.category.PRESENTATION");
        if (displays == null) {
            return -1;
        }
        for (Display display : displays) {
            if (display.getState() == 2 && (display.getFlags() & 268443650) != 0) {
                return display.getDisplayId();
            }
        }
        return -1;
    }

    public final String getSimpleName(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj.getClass().getSimpleName() + com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + Integer.toHexString(obj.hashCode());
    }

    public boolean hasActiveDlnaDevice() {
        return false;
    }

    public boolean isActiveDlnaUsedByVideo() {
        return false;
    }

    public boolean isConnectedCameraController() {
        return false;
    }

    public boolean isHdmiConnected() {
        return false;
    }

    public boolean isScreenSharingPaused() {
        return false;
    }

    public boolean isScreenSharingSupported() {
        return false;
    }

    public boolean isWfdConnected() {
        return false;
    }

    public boolean isWfdSupported() {
        return false;
    }

    public boolean isWifiDisplayDmrSupported() {
        return false;
    }

    public boolean isWifiDisplayMultiViewMode() {
        return false;
    }

    public final void registerDisplayListener(DisplayManager.DisplayListener displayListener, Handler handler) {
        this.mDisplayManager.registerDisplayListener(displayListener, handler);
    }

    public String tag() {
        return "DisplayManagerCompat";
    }

    public String toString() {
        int primaryPresentationId = getPrimaryPresentationId();
        return this.TAG + "{" + getDisplayCount() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isScreenSharingSupported() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isWfdSupported() + "|" + isWfdConnected() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isHdmiConnected() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + hasActiveDlnaDevice() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + primaryPresentationId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getPresentationOwner(primaryPresentationId) + "}\n" + getDisplayLog(getDisplays());
    }

    public final void unregisterDisplayListener(DisplayManager.DisplayListener displayListener) {
        this.mDisplayManager.unregisterDisplayListener(displayListener);
    }

    public final Display[] getDisplays(String str) {
        System.currentTimeMillis();
        return this.mDisplayManager.getDisplays(str);
    }

    private String getDisplayLog(Display[] displayArr) {
        if (displayArr == null || displayArr.length == 0) {
            return "Display{null}";
        }
        StringBuilder sb2 = new StringBuilder(128);
        for (Display displayLog : displayArr) {
            sb2.append(getDisplayLog(displayLog));
            sb2.append(ArcCommonLog.TAG_COMMA);
        }
        return sb2.substring(0, sb2.length() - 2);
    }

    public void unregisterDisplayStatusListener(DisplayStatusListener displayStatusListener) {
    }

    public void unregisterWifiDisplayParameterListener(WifiDisplayParameterListener wifiDisplayParameterListener) {
    }

    public void registerDisplayStatusListener(DisplayStatusListener displayStatusListener, Handler handler) {
    }

    public void registerWifiDisplayParameterListener(WifiDisplayParameterListener wifiDisplayParameterListener, Handler handler) {
    }

    public void setActiveDlnaState(Device device, int i2, boolean z) {
    }
}
