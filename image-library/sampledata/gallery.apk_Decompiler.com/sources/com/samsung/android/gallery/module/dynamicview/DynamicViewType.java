package com.samsung.android.gallery.module.dynamicview;

import com.samsung.android.gallery.support.config.SdkConfig;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DynamicViewType {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ACTION_TYPE {
        EATING(0, 1),
        DANCING(1, 1),
        JUMPING(2, 1),
        RUNNING(3, 1),
        PLAY_WITH_PET(4, 1),
        HAND_GESTURE(5, 2),
        WALKING(8, 1),
        RINGS_EXCHANGE(9, 2),
        FIREWORK(10, 2),
        BIRTHDAY(11, 2),
        FACE_ZOOM_IN(12, 3),
        FACE_ZOOM_OUT(13, 3),
        HOLDING(14, 2),
        FACE_HAPPY(15, 3),
        FACE_SURPRISE(16, 3),
        FACE_SAD(22, 2),
        FACE_HAPPY_2(23, 2),
        FACE_SURPRISE_2(24, 2),
        COMPOUND(30, 2);
        
        final int mActionType;
        final int mClipMode;

        private ACTION_TYPE(int i2, int i7) {
            this.mActionType = i2;
            this.mClipMode = i7;
        }
    }

    public static int getSuggestionsType(int i2) {
        long seconds = (TimeUnit.MILLISECONDS.toSeconds((long) i2) % 60) % 3;
        if (seconds == 0) {
            return 0;
        }
        if (seconds == 1) {
            return 1;
        }
        return 3;
    }

    public static boolean isSupportShortClip(int i2) {
        if (SdkConfig.atLeast(SdkConfig.SEM.S)) {
            return true;
        }
        ACTION_TYPE[] values = ACTION_TYPE.values();
        int length = values.length;
        int i7 = 0;
        while (i7 < length) {
            ACTION_TYPE action_type = values[i7];
            if (action_type.mActionType != i2) {
                i7++;
            } else if (action_type.mClipMode != 2) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
