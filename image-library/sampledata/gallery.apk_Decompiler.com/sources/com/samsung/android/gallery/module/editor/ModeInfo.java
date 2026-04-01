package com.samsung.android.gallery.module.editor;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModeInfo {
    private final String[] mBixbySubInfo;
    private final int mMode;
    private final Uri mOriginalPath;

    public ModeInfo(int i2, Uri uri) {
        this.mMode = i2;
        this.mOriginalPath = uri;
        this.mBixbySubInfo = null;
    }

    public Uri getOriginalPath() {
        return this.mOriginalPath;
    }

    public String[] getSubInfo() {
        return this.mBixbySubInfo;
    }

    public boolean hasBixbySubInfo() {
        if (this.mBixbySubInfo != null) {
            return true;
        }
        return false;
    }

    public boolean isAutoTiltMode() {
        if (this.mMode == 4) {
            return true;
        }
        return false;
    }

    public boolean isBixbyFilerMode() {
        if (this.mMode == 5) {
            return true;
        }
        return false;
    }

    public boolean isEditMode() {
        if (this.mMode == 0) {
            return true;
        }
        return false;
    }

    public boolean isPasteMode() {
        if (this.mMode == 1) {
            return true;
        }
        return false;
    }

    public boolean isReflectionRemovalMode() {
        if (this.mMode == 3) {
            return true;
        }
        return false;
    }

    public boolean isRemoveBgPeopleMode() {
        if (this.mMode == 6) {
            return true;
        }
        return false;
    }

    public boolean isShadowRemovalMode() {
        if (this.mMode == 2) {
            return true;
        }
        return false;
    }

    public ModeInfo(int i2, Uri uri, boolean z) {
        this.mMode = i2;
        this.mOriginalPath = uri;
        String[] strArr = null;
        if (z) {
            strArr = new String[]{null, "save_result"};
        }
        this.mBixbySubInfo = strArr;
    }

    public ModeInfo(int i2, Uri uri, String[] strArr) {
        this.mMode = i2;
        this.mOriginalPath = uri;
        this.mBixbySubInfo = strArr;
    }
}
