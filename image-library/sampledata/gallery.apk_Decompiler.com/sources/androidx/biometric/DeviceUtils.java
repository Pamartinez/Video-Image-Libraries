package androidx.biometric;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class DeviceUtils {
    public static boolean shouldDelayShowingPrompt(Context context, String str) {
        return false;
    }

    public static boolean shouldHideFingerprintDialog(Context context, String str) {
        return false;
    }

    public static boolean shouldUseFingerprintForCrypto(Context context, String str, String str2) {
        return false;
    }
}
