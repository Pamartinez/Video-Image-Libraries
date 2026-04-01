package com.samsung.android.gallery.support.library.abstraction;

import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StorageVolumeCompat {
    public String directory;
    public String name;
    public boolean primary;
    public boolean removable;
    public String state;
    public String subSystem;

    public boolean isMounted() {
        return "mounted".equals(this.state);
    }

    public boolean isSdcard() {
        if (!this.removable || !isMounted() || !"sd".equals(this.subSystem)) {
            return false;
        }
        return true;
    }

    public boolean isUsb() {
        if (!this.removable || !isMounted() || !"usb".equals(this.subSystem)) {
            return false;
        }
        return true;
    }

    public String toString() {
        char c5;
        StringBuilder sb2 = new StringBuilder("StorageVolume{");
        if (this.primary) {
            c5 = 'p';
        } else if (this.removable) {
            c5 = 'r';
        } else {
            c5 = 'u';
        }
        sb2.append(c5);
        sb2.append(',');
        sb2.append(this.name);
        sb2.append(',');
        sb2.append(this.directory);
        sb2.append(',');
        sb2.append(this.subSystem);
        sb2.append(',');
        return C0086a.m(sb2, this.state, '}');
    }
}
