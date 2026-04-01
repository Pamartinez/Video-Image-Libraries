package com.samsung.android.gallery.support.library.sef;

import android.content.Context;
import com.samsung.android.gallery.support.R$string;
import java.util.HashMap;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum CameraCaptureMode {
    Photo(1, 0),
    Pro(2, R$string.camera_capture_mode_pro),
    Portrait(3, R$string.camera_capture_mode_portrait),
    Night(4, R$string.camera_capture_mode_night),
    Panorama(5, R$string.camera_capture_mode_panorama),
    Food(6, R$string.camera_capture_mode_food),
    SingleTake(7, R$string.camera_capture_mode_single_take),
    Fun(8, R$string.camera_capture_mode_fun),
    Macro(9, R$string.camera_capture_mode_macro),
    VirtualAperture(106, 0);
    
    public final int stringResId;
    public final int value;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DataHolder {
        static final HashMap<Integer, CameraCaptureMode> map = null;

        static {
            map = new HashMap<Integer, CameraCaptureMode>() {
                {
                    Stream.of(CameraCaptureMode.values()).forEach(new a(0, this));
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$new$0(CameraCaptureMode cameraCaptureMode) {
                    put(Integer.valueOf(cameraCaptureMode.value), cameraCaptureMode);
                }
            };
        }
    }

    private CameraCaptureMode(int i2, int i7) {
        this.value = i2;
        this.stringResId = i7;
    }

    public static CameraCaptureMode of(int i2) {
        CameraCaptureMode cameraCaptureMode = DataHolder.map.get(Integer.valueOf(i2));
        if (cameraCaptureMode != null) {
            return cameraCaptureMode;
        }
        return Photo;
    }

    public String getString(Context context) {
        int i2 = this.stringResId;
        if (i2 > 0) {
            return context.getString(i2);
        }
        return null;
    }
}
