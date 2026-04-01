package com.samsung.android.gallery.module.motionphoto;

import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MotionPhotoViewMode {
    ON(R$string.camera_capture_mode_motion_photo, R$drawable.gallery_ic_motion_viewtype, AnalyticsDetail.EVENT_DETAIL_MOTION_PHOTO_ON.toString(), true, MotionPlayViewer.PHOTO),
    BOOMERANG(r4, r5, r6, Features.isEnabled(r0), MotionPlayViewer.BOOMERANG),
    SLOW_MO(R$string.motion_photo_view_mode_slow_mo, R$drawable.gallery_ic_slowmotion_viewtype, AnalyticsDetail.EVENT_DETAIL_MOTION_PHOTO_SLOW_MO.toString(), Features.isEnabled(r0), MotionPlayViewer.SLOW_MO),
    OFF(R$string.motion_photo_view_mode_off, R$drawable.gallery_ic_motion_off_viewtype, AnalyticsDetail.EVENT_DETAIL_MOTION_PHOTO_OFF.toString(), true, MotionPlayViewer.PHOTO_MOTION_OFF);
    
    private final String mAnalyticsDetail;
    private final boolean mEnabled;
    private final int mIconResId;
    private final MotionPlayViewer mPlayViewer;
    private final int mTitleResId;

    private MotionPhotoViewMode(int i2, int i7, String str, boolean z, MotionPlayViewer motionPlayViewer) {
        this.mTitleResId = i2;
        this.mIconResId = i7;
        this.mAnalyticsDetail = str;
        this.mEnabled = z;
        this.mPlayViewer = motionPlayViewer;
    }

    public String getAnalyticsDetail() {
        return this.mAnalyticsDetail;
    }

    public int getIconResId() {
        return this.mIconResId;
    }

    public MotionPlayViewer getPlayViewer() {
        return this.mPlayViewer;
    }

    public int getTitleResId() {
        return this.mTitleResId;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public static MotionPhotoViewMode valueOf(int i2) {
        MotionPhotoViewMode motionPhotoViewMode = ON;
        if (i2 != motionPhotoViewMode.ordinal()) {
            MotionPhotoViewMode motionPhotoViewMode2 = BOOMERANG;
            if (i2 == motionPhotoViewMode2.ordinal()) {
                return motionPhotoViewMode2;
            }
            MotionPhotoViewMode motionPhotoViewMode3 = SLOW_MO;
            if (i2 == motionPhotoViewMode3.ordinal()) {
                return motionPhotoViewMode3;
            }
            MotionPhotoViewMode motionPhotoViewMode4 = OFF;
            if (i2 == motionPhotoViewMode4.ordinal()) {
                return motionPhotoViewMode4;
            }
        }
        return motionPhotoViewMode;
    }
}
