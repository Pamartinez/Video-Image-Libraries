package com.samsung.android.app.sdk.deepsky.textextraction.util;

import L1.d;
import L2.a;
import android.os.Build;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import md.b;
import me.f;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0013\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u001b\u0010\u000b\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001b\u0010\u0010\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0013\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\b\u001a\u0004\b\u0012\u0010\nR\u0014\u0010\u0014\u001a\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0015R\u0017\u0010\u0019\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u0019\u0010\u000fR\u0017\u0010\u001a\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001a\u0010\u000fR\u0017\u0010\u001b\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0015\u001a\u0004\b\u001b\u0010\u000fR\u0017\u0010\u001c\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001c\u0010\u000fR\u0017\u0010\u001d\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0015\u001a\u0004\b\u001d\u0010\u000fR\u0017\u0010\u001e\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u0015\u001a\u0004\b\u001e\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/Rune;", "", "<init>", "()V", "", "semPlatformInt", "I", "FEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION$delegate", "Lme/f;", "getFEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION", "()I", "FEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION", "", "FEATURE_DISABLE_NATIVE_AI$delegate", "getFEATURE_DISABLE_NATIVE_AI", "()Z", "FEATURE_DISABLE_NATIVE_AI", "FEATURE_CONFIG_AI_VERSION$delegate", "getFEATURE_CONFIG_AI_VERSION", "FEATURE_CONFIG_AI_VERSION", "SUPPORT_AFTER_ONE_UI_4_1_1", "Z", "SUPPORT_AFTER_ONE_UI_6_1", "CONFIG_AI_VERSION_OVER_20242", "CONFIG_AI_VERSION_OVER_20261", "isSupportOcr", "isSupportCapsule", "isSupportTranslation", "isSupportImageInPainting", "isSupportVex", "isSupportScanToReminder", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Rune {
    private static final boolean CONFIG_AI_VERSION_OVER_20242;
    private static final boolean CONFIG_AI_VERSION_OVER_20261;
    private static final f FEATURE_CONFIG_AI_VERSION$delegate = d.q(new b(13));
    private static final f FEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION$delegate = d.q(new b(11));
    private static final f FEATURE_DISABLE_NATIVE_AI$delegate = d.q(new b(12));
    public static final Rune INSTANCE;
    private static final boolean SUPPORT_AFTER_ONE_UI_4_1_1;
    private static final boolean SUPPORT_AFTER_ONE_UI_6_1;
    private static final boolean isSupportCapsule;
    private static final boolean isSupportImageInPainting;
    private static final boolean isSupportOcr;
    private static final boolean isSupportScanToReminder;
    private static final boolean isSupportTranslation;
    private static final boolean isSupportVex = VexFwkImageTranslator.Companion.isAvailable();
    private static final int semPlatformInt;

    static {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        Rune rune = new Rune();
        INSTANCE = rune;
        int i2 = Build.VERSION.SEM_PLATFORM_INT;
        LibLogger.i("Rune", "SEM_PLATFORM_INT: " + i2);
        semPlatformInt = i2;
        boolean z11 = false;
        if (i2 >= 130500) {
            z = true;
        } else {
            z = false;
        }
        SUPPORT_AFTER_ONE_UI_4_1_1 = z;
        if (i2 >= 150100) {
            z3 = true;
        } else {
            z3 = false;
        }
        SUPPORT_AFTER_ONE_UI_6_1 = z3;
        if (rune.getFEATURE_CONFIG_AI_VERSION() >= 20242) {
            z7 = true;
        } else {
            z7 = false;
        }
        CONFIG_AI_VERSION_OVER_20242 = z7;
        if (rune.getFEATURE_CONFIG_AI_VERSION() >= 20261) {
            z9 = true;
        } else {
            z9 = false;
        }
        CONFIG_AI_VERSION_OVER_20261 = z9;
        isSupportOcr = z;
        isSupportCapsule = z3;
        if (rune.getFEATURE_DISABLE_NATIVE_AI() || !z3 || !z7) {
            z10 = false;
        } else {
            z10 = true;
        }
        isSupportTranslation = z10;
        if (rune.getFEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION() >= 5) {
            z11 = true;
        }
        isSupportImageInPainting = z11;
        isSupportScanToReminder = z9;
    }

    private Rune() {
    }

    /* access modifiers changed from: private */
    public static final int FEATURE_CONFIG_AI_VERSION_delegate$lambda$9() {
        Object obj;
        try {
            String string = SemFloatingFeature.getInstance().getString("SEC_FLOATING_FEATURE_COMMON_CONFIG_AI_VERSION");
            j.d(string, "getString(...)");
            int parseInt = Integer.parseInt(string);
            LibLogger.i("Rune", "FEATURE_CONFIG_AI_VERSION: " + parseInt);
            obj = Integer.valueOf(parseInt);
        } catch (Throwable th) {
            obj = a.l(th);
        }
        if (obj instanceof me.j) {
            obj = null;
        }
        Integer num = (Integer) obj;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public static final int FEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION_delegate$lambda$3() {
        Object obj;
        try {
            int i2 = SemFloatingFeature.getInstance().getInt("SEC_FLOATING_FEATURE_VISION_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION");
            LibLogger.i("Rune", "FEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION: " + i2);
            obj = Integer.valueOf(i2);
        } catch (Throwable th) {
            obj = a.l(th);
        }
        if (obj instanceof me.j) {
            obj = null;
        }
        Integer num = (Integer) obj;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public static final boolean FEATURE_DISABLE_NATIVE_AI_delegate$lambda$6() {
        Object obj;
        try {
            boolean z = SemFloatingFeature.getInstance().getBoolean("SEC_FLOATING_FEATURE_COMMON_DISABLE_NATIVE_AI");
            LibLogger.i("Rune", "FEATURE_DISABLE_NATIVE_AI: " + z);
            obj = Boolean.valueOf(z);
        } catch (Throwable th) {
            obj = a.l(th);
        }
        if (obj instanceof me.j) {
            obj = null;
        }
        Boolean bool = (Boolean) obj;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    private final int getFEATURE_CONFIG_AI_VERSION() {
        return ((Number) FEATURE_CONFIG_AI_VERSION$delegate.getValue()).intValue();
    }

    private final int getFEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION() {
        return ((Number) FEATURE_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION$delegate.getValue()).intValue();
    }

    private final boolean getFEATURE_DISABLE_NATIVE_AI() {
        return ((Boolean) FEATURE_DISABLE_NATIVE_AI$delegate.getValue()).booleanValue();
    }

    public final boolean isSupportCapsule() {
        return isSupportCapsule;
    }

    public final boolean isSupportImageInPainting() {
        return isSupportImageInPainting;
    }

    public final boolean isSupportOcr() {
        return isSupportOcr;
    }

    public final boolean isSupportTranslation() {
        return isSupportTranslation;
    }

    public final boolean isSupportVex() {
        return isSupportVex;
    }
}
