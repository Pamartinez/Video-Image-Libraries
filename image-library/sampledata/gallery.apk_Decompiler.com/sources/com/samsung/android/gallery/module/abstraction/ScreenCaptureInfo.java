package com.samsung.android.gallery.module.abstraction;

import android.text.TextUtils;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.AppResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ScreenCaptureInfo {
    ScreenShot("Screenshot", R$string.screenshot),
    ScreenRecording("Screen recording", R$string.capture_info_screen_recording);
    
    final int stringResId;
    final String value;

    private ScreenCaptureInfo(String str, int i2) {
        this.value = str;
        this.stringResId = i2;
    }

    public static ScreenCaptureInfo get(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (ScreenCaptureInfo screenCaptureInfo : values()) {
            if (screenCaptureInfo.value.equals(str)) {
                return screenCaptureInfo;
            }
        }
        return null;
    }

    public String getString() {
        return AppResources.getString(this.stringResId);
    }
}
