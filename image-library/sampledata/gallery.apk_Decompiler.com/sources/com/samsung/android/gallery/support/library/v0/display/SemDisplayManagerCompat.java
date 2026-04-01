package com.samsung.android.gallery.support.library.v0.display;

import N2.j;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.SemDeviceStatusListener;
import android.hardware.display.SemDlnaDevice;
import android.hardware.display.SemWifiDisplay;
import android.hardware.display.SemWifiDisplayStatus;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import com.samsung.android.allshare.Device;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayStatusListener;
import com.samsung.android.gallery.support.library.utils.Reflector;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemDisplayManagerCompat extends DisplayManagerCompat {
    private final SemDeviceStatusListener mDeviceListener = new SemDeviceStatusListener() {
        public void onConnectionStatusChanged(int i2) {
            synchronized (SemDisplayManagerCompat.this.mListener) {
                SemDisplayManagerCompat.this.mListener.forEach(new b(i2, 2));
            }
        }

        public void onDlnaConnectionStatusChanged(boolean z) {
            synchronized (SemDisplayManagerCompat.this.mListener) {
                SemDisplayManagerCompat.this.mListener.forEach(new a(z));
            }
        }

        public void onQosLevelChanged(int i2) {
            synchronized (SemDisplayManagerCompat.this.mListener) {
                SemDisplayManagerCompat.this.mListener.forEach(new b(i2, 1));
            }
        }

        public void onScreenSharingStatusChanged(int i2) {
            synchronized (SemDisplayManagerCompat.this.mListener) {
                SemDisplayManagerCompat.this.mListener.forEach(new b(i2, 0));
            }
        }
    };
    /* access modifiers changed from: private */
    public final ArrayList<DisplayStatusListener> mListener = new ArrayList<>();
    private Integer mSupportType;

    public SemDisplayManagerCompat(Context context) {
        super(context);
    }

    private SemDlnaDevice getActiveDlnaDevice() {
        DisplayManager displayManager = this.mDisplayManager;
        if (displayManager != null) {
            return displayManager.semGetActiveDlnaDevice();
        }
        return null;
    }

    private int getActiveDlnaType() {
        try {
            System.currentTimeMillis();
            SemDlnaDevice activeDlnaDevice = getActiveDlnaDevice();
            if (activeDlnaDevice != null) {
                return activeDlnaDevice.getDlnaType();
            }
            return -1;
        } catch (Exception e) {
            j.C(e, new StringBuilder("getActiveDlnaType failed e="), this.TAG);
            return -1;
        }
    }

    private int getDisplayType(Display display) {
        try {
            Integer num = (Integer) Reflector.invoke((Class<?>) Display.class, (Object) display, "getType");
            if (num != null) {
                return num.intValue();
            }
            return -1;
        } catch (Exception e) {
            j.C(e, new StringBuilder("getDisplayType failed e="), this.TAG);
            return -1;
        }
    }

    private int getScreenSharingStatus() {
        DisplayManager displayManager = this.mDisplayManager;
        if (displayManager != null) {
            return displayManager.semGetScreenSharingStatus();
        }
        return -1;
    }

    private int getSupportType() {
        if (this.mSupportType == null) {
            try {
                this.mSupportType = Integer.valueOf(this.mDisplayManager.semCheckScreenSharingSupported());
            } catch (Exception e) {
                String str = this.TAG;
                Log.w(str, "getSupportType failed e=" + e.getMessage());
                this.mSupportType = -1;
            }
        }
        return this.mSupportType.intValue();
    }

    private SemWifiDisplayStatus getWifiDisplayStatus() {
        DisplayManager displayManager = this.mDisplayManager;
        if (displayManager != null) {
            return displayManager.semGetWifiDisplayStatus();
        }
        return null;
    }

    public String getActiveDlnaDeviceUid() {
        try {
            SemDlnaDevice activeDlnaDevice = getActiveDlnaDevice();
            if (activeDlnaDevice != null) {
                return activeDlnaDevice.getUid();
            }
            return null;
        } catch (Exception e) {
            j.C(e, new StringBuilder("getActiveDlnaDeviceUid failed e="), this.TAG);
            return null;
        }
    }

    public String getP2pAddress() {
        SemWifiDisplay semWifiDisplay;
        try {
            SemWifiDisplayStatus wifiDisplayStatus = getWifiDisplayStatus();
            if (wifiDisplayStatus != null) {
                semWifiDisplay = wifiDisplayStatus.getActiveDisplay();
            } else {
                semWifiDisplay = null;
            }
            if (semWifiDisplay != null) {
                return semWifiDisplay.getDeviceAddress();
            }
        } catch (Exception e) {
            j.C(e, new StringBuilder("getP2pAddress failed e="), this.TAG);
        }
        return null;
    }

    public String getPresentationOwner(int i2) {
        return this.mDisplayManager.semGetPresentationOwner(i2);
    }

    public boolean hasActiveDlnaDevice() {
        try {
            System.currentTimeMillis();
            if (getActiveDlnaDevice() != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            j.C(e, new StringBuilder("hasActiveDlnaDevice failed e="), this.TAG);
            return false;
        }
    }

    public boolean isActiveDlnaUsedByVideo() {
        if (getActiveDlnaType() == 0) {
            return true;
        }
        return false;
    }

    public boolean isConnectedCameraController() {
        try {
            SemWifiDisplayStatus wifiDisplayStatus = getWifiDisplayStatus();
            if (wifiDisplayStatus != null && wifiDisplayStatus.getActiveDisplayState() == 2 && wifiDisplayStatus.getConnectedState() == 1) {
                return true;
            }
            return false;
        } catch (Exception | NoSuchMethodError e) {
            Log.w(this.TAG, "isConnectedCameraController failed", e);
            return false;
        }
    }

    public boolean isHdmiConnected() {
        System.currentTimeMillis();
        return isHdmiConnected(this.mDisplayManager);
    }

    public boolean isScreenSharingPaused() {
        try {
            if (getScreenSharingStatus() == 7) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.w(this.TAG, "isWifiDisplayConnected failed", e);
            return false;
        }
    }

    public boolean isScreenSharingSupported() {
        if (getSupportType() == 0) {
            return true;
        }
        return false;
    }

    public boolean isWfdConnected() {
        try {
            SemWifiDisplayStatus wifiDisplayStatus = getWifiDisplayStatus();
            if (wifiDisplayStatus == null || wifiDisplayStatus.getActiveDisplayState() != 2) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.w(this.TAG, "isWifiDisplayConnected failed", e);
            return false;
        }
    }

    public boolean isWfdSupported() {
        int supportType = getSupportType();
        if (supportType == 0 || supportType == 1) {
            return true;
        }
        return false;
    }

    public boolean isWifiDisplayDmrSupported() {
        try {
            SemWifiDisplayStatus wifiDisplayStatus = getWifiDisplayStatus();
            if (wifiDisplayStatus == null || wifiDisplayStatus.getActiveDisplay() == null) {
                return false;
            }
            return wifiDisplayStatus.getActiveDisplay().isDmrSupportedType(1);
        } catch (Exception | NoSuchMethodError e) {
            Log.w(this.TAG, "isWifiDisplayDmrSupported failed", e);
            return false;
        }
    }

    public boolean isWifiDisplayMultiViewMode() {
        try {
            SemWifiDisplayStatus wifiDisplayStatus = getWifiDisplayStatus();
            if (wifiDisplayStatus == null || wifiDisplayStatus.getActiveDisplay() == null) {
                return false;
            }
            return "multi".equals(wifiDisplayStatus.getActiveDisplay().getViewMode());
        } catch (Exception | NoSuchMethodError e) {
            Log.w(this.TAG, "isWifiDisplayMultiViewModeSupported failed", e);
            return false;
        }
    }

    public void registerDisplayStatusListener(DisplayStatusListener displayStatusListener, Handler handler) {
        synchronized (this.mListener) {
            this.mListener.add(displayStatusListener);
            if (this.mListener.size() == 1) {
                try {
                    this.mDisplayManager.semRegisterDeviceStatusListener(this.mDeviceListener, handler);
                    String str = this.TAG;
                    Log.i(str, "registerDeviceStatusListener " + displayStatusListener);
                } catch (Exception e) {
                    String str2 = this.TAG;
                    Log.w(str2, "registerDeviceStatusListener failed e=" + e.getMessage());
                }
            }
        }
    }

    public void setActiveDlnaState(Device device, int i2, boolean z) {
        String str;
        try {
            System.currentTimeMillis();
            String name = device.getName();
            String iPAddress = device.getIPAddress();
            String productCapInfo = device.getProductCapInfo(Device.InformationType.P2P_MAC_ADDRESS);
            String nic = device.getNIC();
            String id = device.getID();
            if (device.getIcon() != null) {
                str = device.getIcon().toString();
            } else {
                str = null;
            }
            this.mDisplayManager.semSetActiveDlnaState(new SemDlnaDevice(name, iPAddress, productCapInfo, (String) null, nic, id, 1, z, str), i2);
        } catch (Exception e) {
            Exception exc = e;
            j.C(exc, new StringBuilder("setActiveDlnaState failed e="), this.TAG);
        }
    }

    public String tag() {
        return "SemDisplayManagerCompat";
    }

    public void unregisterDisplayStatusListener(DisplayStatusListener displayStatusListener) {
        synchronized (this.mListener) {
            this.mListener.remove(displayStatusListener);
            if (this.mListener.size() == 0) {
                try {
                    this.mDisplayManager.semUnregisterDeviceStatusListener(this.mDeviceListener);
                    String str = this.TAG;
                    Log.i(str, "unregisterDeviceStatusListener " + displayStatusListener);
                } catch (Exception e) {
                    String str2 = this.TAG;
                    Log.w(str2, "unregisterDeviceStatusListener failed e=" + e.getMessage());
                }
            }
        }
    }

    private boolean isHdmiConnected(DisplayManager displayManager) {
        Display[] displays = displayManager.getDisplays();
        if (displays != null) {
            for (Display displayType : displays) {
                if (getDisplayType(displayType) == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
