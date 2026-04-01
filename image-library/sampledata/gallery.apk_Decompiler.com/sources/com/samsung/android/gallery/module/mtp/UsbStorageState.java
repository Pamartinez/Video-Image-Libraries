package com.samsung.android.gallery.module.mtp;

import android.text.TextUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UsbStorageState {
    private final Object LOCK = new Object();
    private volatile int mountedCount;
    private volatile StorageVolumeCompat volume;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final UsbStorageState instance = new UsbStorageState();
    }

    public static UsbStorageState getInstance() {
        return LazyHolder.instance;
    }

    public int getMountedCount() {
        int i2;
        synchronized (this.LOCK) {
            i2 = this.mountedCount;
        }
        return i2;
    }

    public String getVolumeName() {
        String str;
        synchronized (this.LOCK) {
            if (this.volume == null) {
                str = null;
            } else {
                str = this.volume.name;
            }
        }
        return str;
    }

    public boolean isMounted() {
        boolean z;
        synchronized (this.LOCK) {
            if (this.volume != null) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public void notifyIfAbsent() {
        if (!TextUtils.isEmpty(getVolumeName())) {
            notifyIfChanged();
        }
    }

    public void notifyIfChanged() {
        notifyIfChanged(FileUtils.getUsbStorageVolume(AppResources.getAppContext()));
    }

    public void notifyIfChanged(StorageVolumeCompat storageVolumeCompat) {
        String str;
        String volumeName = getVolumeName();
        if (storageVolumeCompat == null) {
            str = null;
        } else {
            str = storageVolumeCompat.name;
        }
        int mountedCount2 = getMountedCount();
        synchronized (this.LOCK) {
            this.volume = storageVolumeCompat;
            this.mountedCount = FileUtils.getUsbStorageVolumes(AppResources.getAppContext()).size();
        }
        boolean z = !TextUtils.equals(volumeName, str) || mountedCount2 != this.mountedCount;
        Log.d("UsbStorageState", "notifyIfChanged", Boolean.valueOf(z), str, Integer.valueOf(this.mountedCount));
        if (z) {
            Blackboard.getApplicationInstance().publish("event/UsbStorageVolumeChanged", str != null ? str : "");
        }
        Blackboard applicationInstance = Blackboard.getApplicationInstance();
        if (str == null) {
            str = "";
        }
        applicationInstance.publish("event/UsbStorageVolumeChanged", str);
    }
}
