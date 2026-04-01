package com.samsung.android.gallery.module.mtp;

import N2.j;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.mtp.MtpDevice;
import android.mtp.MtpDeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MtpDeviceLoader {
    private boolean mDeviceOpenRequested = false;
    private final ConcurrentHashMap<String, MtpDevice> mDevices = new ConcurrentHashMap<>();
    private final ArrayList<String> mIgnoredDevices = new ArrayList<>();
    private final AtomicBoolean mNeedCheckDeviceList = new AtomicBoolean();
    private final UsbManager mUsbManager;

    public MtpDeviceLoader(Context context) {
        this.mUsbManager = (UsbManager) context.getApplicationContext().getSystemService("usb");
    }

    private String getDeviceInfo(String str, UsbDevice usbDevice) {
        StringBuilder k = j.k("[", str, "][");
        k.append(usbDevice.getManufacturerName());
        k.append("][");
        k.append(usbDevice.getProductName());
        k.append("][");
        k.append(usbDevice.getDeviceProtocol());
        k.append("][");
        k.append(usbDevice.getDeviceSubclass());
        k.append("][");
        k.append(usbDevice.getVendorId());
        k.append("][");
        k.append(isCamera(usbDevice));
        k.append("][");
        k.append(this.mIgnoredDevices.contains(str));
        k.append("][");
        k.append(this.mUsbManager.hasPermission(usbDevice));
        k.append("]\n");
        return k.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDump$0(StringBuilder sb2, String str, UsbDevice usbDevice) {
        sb2.append(getDeviceInfo(str, usbDevice));
    }

    private void openDevice(UsbDevice usbDevice) {
        String deviceName = usbDevice.getDeviceName();
        Log.mt("MtpDeviceLoader", "open device start [" + deviceName + "]");
        boolean hasPermission = this.mUsbManager.hasPermission(usbDevice);
        boolean isCamera = isCamera(usbDevice);
        boolean contains = this.mIgnoredDevices.contains(deviceName);
        if (!isCamera || contains || !hasPermission) {
            Log.mtw("MtpDeviceLoader", "can not open device [" + isCamera + "][" + contains + "][" + hasPermission + "]");
            return;
        }
        UsbDeviceConnection openDevice = this.mUsbManager.openDevice(usbDevice);
        if (openDevice == null) {
            Log.mtw("MtpDeviceLoader", "can not open device connection : " + deviceName);
            this.mIgnoredDevices.add(deviceName);
            return;
        }
        Log.mt("MtpDeviceLoader", "open device request [" + this.mDeviceOpenRequested + "]");
        if (!this.mDeviceOpenRequested) {
            this.mDeviceOpenRequested = true;
            long currentTimeMillis = System.currentTimeMillis();
            MtpDevice mtpDevice = getMtpDevice(usbDevice);
            if (mtpDevice.open(openDevice)) {
                StringBuilder k = j.k("device is opened : ", deviceName, ", delay : ");
                k.append(System.currentTimeMillis() - currentTimeMillis);
                Log.mt("MtpDeviceLoader", k.toString());
                this.mDevices.put(usbDevice.getDeviceName(), mtpDevice);
            } else {
                Log.mtw("MtpDeviceLoader", "device is not opened : " + deviceName);
            }
            this.mDeviceOpenRequested = false;
        }
        Log.mt("MtpDeviceLoader", "open device end");
    }

    public void closeDevice(String str) {
        Log.mt("MtpDeviceLoader", "closeDevice : " + str);
        this.mIgnoredDevices.remove(str);
        MtpDevice mtpDevice = this.mDevices.get(str);
        if (mtpDevice != null) {
            mtpDevice.close();
            this.mDevices.remove(str);
            Log.mt("MtpDeviceLoader", "device is closed : " + str);
        }
    }

    public MtpDevice getDevice(String str) {
        return this.mDevices.get(str);
    }

    public List<MtpDevice> getDeviceList() {
        Log.mt("MtpDeviceLoader", "getDeviceList start");
        HashMap<String, UsbDevice> deviceList = this.mUsbManager.getDeviceList();
        if (deviceList == null || deviceList.isEmpty()) {
            Log.mtw("MtpDeviceLoader", "deviceList is null or empty");
        } else {
            for (UsbDevice next : deviceList.values()) {
                if (this.mDevices.get(next.getDeviceName()) == null) {
                    openDevice(next);
                } else {
                    Log.mt("MtpDeviceLoader", "device is already opened [" + next.getDeviceName() + "]");
                }
            }
        }
        Log.mt("MtpDeviceLoader", "getDeviceList end [" + this.mDevices.size() + "]");
        return new ArrayList(this.mDevices.values());
    }

    public String getDeviceModelName(String str) {
        MtpDevice device = getDevice(str);
        if (device == null) {
            Log.mtw("MtpDeviceLoader", "unable to getDeviceModelName. " + str + " is not opened.");
            return "";
        }
        MtpDeviceInfo deviceInfo = device.getDeviceInfo();
        if (deviceInfo == null) {
            Log.mtw("MtpDeviceLoader", "unable to getDeviceModelName. " + str + " info is null.");
            return "";
        }
        String trim = deviceInfo.getManufacturer().trim();
        String trim2 = deviceInfo.getModel().trim();
        Log.mt("MtpDeviceLoader", "manufacturer : " + trim + ", model : " + trim2);
        return j.f(new StringBuilder(), trim, " ", trim2);
    }

    public String getDump() {
        StringBuilder sb2 = new StringBuilder();
        try {
            HashMap<String, UsbDevice> deviceList = this.mUsbManager.getDeviceList();
            if (deviceList == null || deviceList.isEmpty()) {
                sb2.append("deviceList is null or empty");
            } else {
                deviceList.forEach(new a(this, sb2));
            }
        } catch (Exception e) {
            Log.mte("MtpDeviceLoader", "dum failed e=" + e.getMessage());
        }
        return sb2.toString();
    }

    public MtpDevice getMtpDevice(UsbDevice usbDevice) {
        return new MtpDevice(usbDevice);
    }

    public boolean isAvailable() {
        boolean isEmpty;
        if (this.mNeedCheckDeviceList.getAndSet(true) || !this.mDevices.isEmpty()) {
            isEmpty = this.mDevices.isEmpty();
        } else {
            isEmpty = getDeviceList().isEmpty();
        }
        return !isEmpty;
    }

    public boolean isCamera(UsbDevice usbDevice) {
        int interfaceCount = usbDevice.getInterfaceCount();
        for (int i2 = 0; i2 < interfaceCount; i2++) {
            UsbInterface usbInterface = usbDevice.getInterface(i2);
            if (usbInterface.getInterfaceClass() == 6 && usbInterface.getInterfaceSubclass() == 1 && usbInterface.getInterfaceProtocol() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isDeviceOpened(String str) {
        return this.mDevices.containsKey(str);
    }
}
