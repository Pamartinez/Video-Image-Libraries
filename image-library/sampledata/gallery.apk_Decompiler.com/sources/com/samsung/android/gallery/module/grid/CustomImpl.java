package com.samsung.android.gallery.module.grid;

import android.content.Context;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CustomImpl extends GridHelper {
    int mDepthDefault;
    int mGridRes;
    int mGridSplitRes;
    PreferenceName mPreferenceName;
    int[] mSpans;
    int[] mSpansSplit;

    public CustomImpl(String str, int[] iArr) {
        super(str, iArr);
    }

    public CustomImpl cloneOf(CustomImpl customImpl) {
        this.mSpans = customImpl.mSpans;
        this.mSpansSplit = customImpl.mSpansSplit;
        this.mDepthDefault = customImpl.mDepthDefault;
        this.mPreferenceName = customImpl.mPreferenceName;
        this.mGridRes = customImpl.mGridRes;
        this.mGridSplitRes = customImpl.mGridSplitRes;
        return this;
    }

    public int getDefaultDepth() {
        return this.mDepthDefault;
    }

    public int[] getGridArray(Context context) {
        int[] iArr = this.mSpans;
        if (iArr != null) {
            return iArr;
        }
        return super.getGridArray(context);
    }

    public int getGridArrayResource() {
        return this.mGridRes;
    }

    public int getGridDepth() {
        if (this.mPreferenceName != null) {
            return super.getGridDepth();
        }
        return getDefaultDepth();
    }

    public PreferenceName getPreferenceName() {
        return this.mPreferenceName;
    }

    public int getSplitArrayResource() {
        return this.mGridSplitRes;
    }

    public int[] getSplitGridArray(Context context) {
        int[] iArr = this.mSpansSplit;
        if (iArr != null) {
            return iArr;
        }
        return super.getSplitGridArray(context);
    }

    public void saveGridDepth(int i2) {
        if (this.mPreferenceName != null) {
            super.saveGridDepth(i2);
        }
    }

    public CustomImpl setDepthDefault(int i2) {
        this.mDepthDefault = i2;
        return this;
    }

    public CustomImpl setResources(int i2, int i7) {
        this.mGridRes = i2;
        this.mGridSplitRes = i7;
        return this;
    }

    public CustomImpl setSpans(int[] iArr, int[] iArr2) {
        this.mSpans = iArr;
        this.mSpansSplit = iArr2;
        return this;
    }
}
