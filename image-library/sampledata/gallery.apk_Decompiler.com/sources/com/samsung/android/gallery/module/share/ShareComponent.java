package com.samsung.android.gallery.module.share;

import android.text.TextUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareComponent {
    String className = null;
    boolean fromBixby = false;
    String packageName = null;

    public static ShareComponent builder() {
        return new ShareComponent();
    }

    public String getClassName() {
        return this.className;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public boolean hasComponents() {
        if (TextUtils.isEmpty(this.packageName) || TextUtils.isEmpty(this.className)) {
            return false;
        }
        return true;
    }

    public boolean isFromBixby() {
        return this.fromBixby;
    }

    public ShareComponent setClassName(String str) {
        this.className = str;
        return this;
    }

    public ShareComponent setFromBixby() {
        this.fromBixby = true;
        return this;
    }

    public ShareComponent setPackageName(String str) {
        this.packageName = str;
        return this;
    }
}
