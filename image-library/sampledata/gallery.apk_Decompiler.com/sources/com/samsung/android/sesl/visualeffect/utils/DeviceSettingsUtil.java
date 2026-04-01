package com.samsung.android.sesl.visualeffect.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/utils/DeviceSettingsUtil;", "", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DeviceSettingsUtil {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final String TAG = "DeviceSettingsUtil";

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\bR\u0014\u0010\u000b\u001a\u00020\n8\u0002XD¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8\u0006XT¢\u0006\u0006\n\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/utils/DeviceSettingsUtil$Companion;", "", "<init>", "()V", "Landroid/content/Context;", "context", "", "isBlockedByReduceAnimations", "(Landroid/content/Context;)Z", "isBlockedByAnimatorDurationScale", "", "TAG", "Ljava/lang/String;", "REDUCE_ANIMATION", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isBlockedByAnimatorDurationScale(Context context) {
            j.e(context, "context");
            float f = Settings.System.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f);
            String access$getTAG$cp = DeviceSettingsUtil.TAG;
            Log.i(access$getTAG$cp, "isBlockedByAnimatorDurationScale duration: " + f);
            if (Float.compare(f, 0.0f) == 0) {
                return true;
            }
            return false;
        }

        public final boolean isBlockedByReduceAnimations(Context context) {
            j.e(context, "context");
            boolean z = false;
            if (Settings.System.getInt(context.getContentResolver(), "remove_animations", 0) == 1) {
                z = true;
            }
            Log.i(DeviceSettingsUtil.TAG, "isBlockedByReduceAnimations: " + z);
            return z;
        }

        private Companion() {
        }
    }
}
