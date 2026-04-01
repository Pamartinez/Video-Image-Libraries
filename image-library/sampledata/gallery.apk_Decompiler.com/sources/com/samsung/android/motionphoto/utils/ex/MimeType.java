package com.samsung.android.motionphoto.utils.ex;

import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\t\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/MimeType;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "JPEG", "HEIC", "MP4", "toString", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MimeType {
    JPEG("image/jpeg"),
    HEIC("image/heic"),
    MP4(Encode.ContentType.VIDEO_MP4);
    
    private final String value;

    static {
        MimeType[] $values;
        $ENTRIES = c.t($values);
    }

    private MimeType(String str) {
        this.value = str;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public String toString() {
        return this.value;
    }
}
