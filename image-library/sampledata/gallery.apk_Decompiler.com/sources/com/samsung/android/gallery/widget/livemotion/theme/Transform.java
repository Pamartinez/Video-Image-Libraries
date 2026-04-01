package com.samsung.android.gallery.widget.livemotion.theme;

import B8.b;
import android.graphics.RectF;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.RectUtils;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Transform {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TYPE {
        SCALE_IN,
        PANNING_LEFT,
        SCALE_OUT,
        PANNING_RIGHT,
        SCALE_IN_1,
        TILT_UP,
        SCALE_OUT_1,
        TILT_DOWN,
        NONE;

        public boolean isPanningLeft() {
            if (this == PANNING_LEFT) {
                return true;
            }
            return false;
        }

        public boolean isPanningRight() {
            if (this == PANNING_RIGHT) {
                return true;
            }
            return false;
        }

        public boolean isScaleIn() {
            if (this == SCALE_IN || this == SCALE_IN_1) {
                return true;
            }
            return false;
        }

        public boolean isScaleOut() {
            if (this == SCALE_OUT || this == SCALE_OUT_1) {
                return true;
            }
            return false;
        }

        public boolean isTileDown() {
            if (this == TILT_DOWN) {
                return true;
            }
            return false;
        }

        public boolean isTiltUp() {
            if (this == TILT_UP) {
                return true;
            }
            return false;
        }
    }

    static float[] getPivotByROI(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isBroken() || !mediaItem.isImage() || !RectUtils.isValidRect(mediaItem.getCropRectRatio())) {
            return new float[]{0.5f, 0.5f};
        }
        RectF cropRectRatio = mediaItem.getCropRectRatio();
        float[] fArr = {cropRectRatio.left, cropRectRatio.top};
        if (mediaItem.getOrientation() == 90) {
            fArr[0] = cropRectRatio.bottom;
            fArr[1] = cropRectRatio.left;
            return fArr;
        } else if (mediaItem.getOrientation() == 180) {
            fArr[0] = 1.0f - cropRectRatio.right;
            fArr[1] = cropRectRatio.bottom;
            return fArr;
        } else {
            if (mediaItem.getOrientation() == 270) {
                fArr[0] = 1.0f - cropRectRatio.top;
                fArr[1] = 1.0f - cropRectRatio.right;
            }
            return fArr;
        }
    }

    static TYPE getType(MediaItem mediaItem) {
        if (mediaItem == null) {
            return TYPE.SCALE_IN;
        }
        if (!StoryHelper.isVideoItem(mediaItem)) {
            return TYPE.values()[(int) ((ThemeKey.getKey(mediaItem) % (((long) (TYPE.values().length - 1)) * 2)) / 2)];
        }
        return TYPE.NONE;
    }

    static boolean hasRoi(MediaItem mediaItem) {
        RectF rectF;
        if (mediaItem != null) {
            rectF = mediaItem.getCropRectRatio();
        } else {
            rectF = null;
        }
        return RectUtils.isValidRect(rectF);
    }

    static boolean isTranslate(int i2) {
        TYPE type = (TYPE) Arrays.stream(TYPE.values()).filter(new b(i2, 4)).findFirst().orElse(TYPE.NONE);
        if (type.isScaleOut() || type.isScaleIn()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    static /* synthetic */ boolean lambda$isTranslate$0(int i2, TYPE type) {
        if (type.ordinal() == i2) {
            return true;
        }
        return false;
    }

    static void printDebugLog(TYPE type, String str) {
    }
}
