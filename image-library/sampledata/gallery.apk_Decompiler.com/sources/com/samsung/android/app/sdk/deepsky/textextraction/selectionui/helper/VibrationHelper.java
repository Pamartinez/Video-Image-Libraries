package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper;

import Vf.C;
import Vf.C0886x;
import Vf.D;
import Vf.M;
import android.content.Context;
import android.media.AudioAttributes;
import android.os.VibrationEffect;
import android.provider.Settings;
import android.view.HapticFeedbackConstants;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import eg.f;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1227c;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\"\u0010\u0010\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0010\u0010\b\"\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/VibrationHelper;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "isVibrationEnabled", "()Z", "Landroid/os/VibrationEffect;", "createVibrationEffect", "()Landroid/os/VibrationEffect;", "Lme/x;", "hapticFeedback", "()V", "Landroid/content/Context;", "isLongPress", "Z", "setLongPress", "(Z)V", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VibrationHelper {
    /* access modifiers changed from: private */
    public static final AudioAttributes BACKGROUND_VIBRATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public final Context context;
    private boolean isLongPress;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/helper/VibrationHelper$Companion;", "", "<init>", "()V", "TAG", "", "SYSTEM_HAPTIC_FEEDBACK_ENABLED", "TAP_HAPTIC_PATTERN_INDEX", "", "LONG_PRESS_HAPTIC_PATTERN_INDEX", "BACKGROUND_VIBRATION_ATTRIBUTES", "Landroid/media/AudioAttributes;", "kotlin.jvm.PlatformType", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public VibrationHelper(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        if (!isVibrationEnabled()) {
            LibLogger.w("VibrationHelper", "Vibration is not enabled");
        }
    }

    /* access modifiers changed from: private */
    public final VibrationEffect createVibrationEffect() {
        int i2;
        if (this.isLongPress) {
            i2 = 1;
        } else {
            i2 = 41;
        }
        VibrationEffect semCreateWaveform = VibrationEffect.semCreateWaveform(HapticFeedbackConstants.semGetVibrationIndex(i2), -1, VibrationEffect.SemMagnitudeType.TYPE_TOUCH);
        j.d(semCreateWaveform, "semCreateWaveform(...)");
        return semCreateWaveform;
    }

    /* access modifiers changed from: private */
    public final boolean isVibrationEnabled() {
        if (Settings.System.getInt(this.context.getContentResolver(), "haptic_feedback_enabled", 1) == 1) {
            return true;
        }
        return false;
    }

    public final void hapticFeedback() {
        f fVar = M.f3843a;
        D.n(D.a(eg.e.d), (C0886x) null, (C) null, new VibrationHelper$hapticFeedback$1(this, (C1227c) null), 3);
    }

    public final void setLongPress(boolean z) {
        this.isLongPress = z;
    }
}
