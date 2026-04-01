package com.samsung.android.app.sdk.deepsky.objectcapture.common;

import Tf.n;
import com.samsung.android.feature.SemFloatingFeature;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u000e\u0010\u000b\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\bR\u0010\u0010\u0010\u001a\u00020\u00058\u0006XD¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/common/Rune;", "", "<init>", "()V", "SUPPORT_NATIVE_AI", "", "SUPPORT_SAVE_AS_STICKER_FROM_STICKER_CENTER", "getSUPPORT_SAVE_AS_STICKER_FROM_STICKER_CENTER", "()Z", "SUPPORT_VIDEO_CLIPPER", "getSUPPORT_VIDEO_CLIPPER", "SUPPORT_VIDEO_CLIPPER_MODEL", "SUPPORT_DETECT_VIDEO_CLIPPER", "getSUPPORT_DETECT_VIDEO_CLIPPER", "SUPPORT_SRIB_CLIPPER", "getSUPPORT_SRIB_CLIPPER", "SUPPORT_AFTER_ONEUI_7_0", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Rune {
    public static final Rune INSTANCE = new Rune();
    public static final boolean SUPPORT_AFTER_ONEUI_7_0 = true;
    private static final boolean SUPPORT_DETECT_VIDEO_CLIPPER;
    private static final boolean SUPPORT_NATIVE_AI;
    private static final boolean SUPPORT_SAVE_AS_STICKER_FROM_STICKER_CENTER;
    private static final boolean SUPPORT_SRIB_CLIPPER;
    private static final boolean SUPPORT_VIDEO_CLIPPER;
    private static final boolean SUPPORT_VIDEO_CLIPPER_MODEL;

    static {
        boolean z = !SemFloatingFeature.getInstance().getBoolean("SEC_FLOATING_FEATURE_COMMON_DISABLE_NATIVE_AI");
        SUPPORT_NATIVE_AI = z;
        SUPPORT_SAVE_AS_STICKER_FROM_STICKER_CENTER = z;
        SUPPORT_VIDEO_CLIPPER = z;
        boolean z3 = !j.a(SemFloatingFeature.getInstance().getString("SEC_FLOATING_FEATURE_VIDEO_CONFIG_VIDEO_CLIPPING_MODE"), "");
        SUPPORT_VIDEO_CLIPPER_MODEL = z3;
        SUPPORT_DETECT_VIDEO_CLIPPER = z3;
        String string = SemFloatingFeature.getInstance().getString("SEC_FLOATING_FEATURE_VIDEO_CONFIG_VIDEO_CLIPPING_MODE");
        j.d(string, "getString(...)");
        SUPPORT_SRIB_CLIPPER = n.u0(string, "unifiedclipper");
    }

    private Rune() {
    }

    public final boolean getSUPPORT_DETECT_VIDEO_CLIPPER() {
        return SUPPORT_DETECT_VIDEO_CLIPPER;
    }

    public final boolean getSUPPORT_SAVE_AS_STICKER_FROM_STICKER_CENTER() {
        return SUPPORT_SAVE_AS_STICKER_FROM_STICKER_CENTER;
    }

    public final boolean getSUPPORT_SRIB_CLIPPER() {
        return SUPPORT_SRIB_CLIPPER;
    }

    public final boolean getSUPPORT_VIDEO_CLIPPER() {
        return SUPPORT_VIDEO_CLIPPER;
    }
}
