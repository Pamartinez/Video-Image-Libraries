package androidx.biometric;

import android.app.KeyguardManager;
import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class KeyguardUtils {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static KeyguardManager getKeyguardManager(Context context) {
            return (KeyguardManager) context.getSystemService(KeyguardManager.class);
        }
    }

    public static KeyguardManager getKeyguardManager(Context context) {
        return Api23Impl.getKeyguardManager(context);
    }
}
