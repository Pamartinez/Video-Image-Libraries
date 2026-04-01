package com.samsung.android.gallery.image360.activity.viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.WindowInsets;
import com.samsung.android.gallery.image360.R$string;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.WindowUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Image360Model {
    private Bitmap mBitmap;
    private String mFilePath;
    private int mHeight;
    private boolean mIsSef360Image;
    private boolean mIsSensorSupported;
    private WindowInsets mSystemBarInsets;
    private IGallery360Viewer.ViewMode mViewMode;
    private final int[] mViewModeNames = {R$string.gallery360viewer_mode_360, R$string.gallery360viewer_mode_round, R$string.gallery360viewer_mode_stretched, R$string.gallery360viewer_mode_dual, R$string.gallery360viewer_mode_panorama};
    private int mWidth;

    private boolean isDismissInsets(Context context) {
        return SystemUi.isInMultiWindowMode(context);
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public IGallery360Viewer.ViewMode getCurrentViewMode() {
        return this.mViewMode;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public Rect getNavigationBarInsets(Context context) {
        if (isDismissInsets(context)) {
            return new Rect();
        }
        return WindowUtils.getNavigationBarInsets(this.mSystemBarInsets);
    }

    public Rect getStatusBarInsets(Context context) {
        if (isDismissInsets(context)) {
            return new Rect();
        }
        return WindowUtils.getStatusBarInsets(this.mSystemBarInsets);
    }

    public int getViewModeName() {
        return this.mViewModeNames[this.mViewMode.ordinal()];
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isSef360Image() {
        return this.mIsSef360Image;
    }

    public boolean isSensorSupported() {
        return this.mIsSensorSupported;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public void setCurrentViewMode(IGallery360Viewer.ViewMode viewMode) {
        this.mViewMode = viewMode;
    }

    public void setFilePath(String str) {
        this.mFilePath = str;
    }

    public void setSef360Image(boolean z) {
        this.mIsSef360Image = z;
    }

    public void setSize(int i2, int i7) {
        this.mWidth = i2;
        this.mHeight = i7;
    }

    public void setSystemBarInsets(WindowInsets windowInsets) {
        this.mSystemBarInsets = windowInsets;
    }

    public void setViewerInfo(IGallery360Viewer.ViewMode viewMode, boolean z) {
        this.mViewMode = viewMode;
        this.mIsSensorSupported = z;
    }
}
