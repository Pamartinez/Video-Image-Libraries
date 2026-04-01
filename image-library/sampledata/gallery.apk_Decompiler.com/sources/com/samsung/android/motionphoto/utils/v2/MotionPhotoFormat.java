package com.samsung.android.motionphoto.utils.v2;

import B8.b;
import C3.C0392b;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MotionPhotoFormat {
    NONE("", 0),
    V1("mpv1", 1),
    V2("mpv2", 2),
    V3("mpv3", 3),
    V4("mpv4", 4);
    
    private final String version;
    private final int versionCode;

    private MotionPhotoFormat(String str, int i2) {
        this.version = str;
        this.versionCode = i2;
    }

    public static MotionPhotoFormat of(String str) {
        return (MotionPhotoFormat) Arrays.stream(values()).filter(new C0392b(str, 18)).findFirst().orElseThrow(new h(str, 0));
    }

    public String getVersion() {
        return this.version;
    }

    public static MotionPhotoFormat of(int i2) {
        return (MotionPhotoFormat) Arrays.stream(values()).filter(new b(i2, 13)).findFirst().orElseThrow(new i(i2, 0));
    }
}
