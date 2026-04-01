package androidx.biometric;

import android.content.Context;
import android.util.Log;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BiometricManager {
    private final android.hardware.biometrics.BiometricManager mBiometricManager;
    private final FingerprintManagerCompat mFingerprintManager = null;
    private final Injector mInjector;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static android.hardware.biometrics.BiometricManager create(Context context) {
            return (android.hardware.biometrics.BiometricManager) context.getSystemService(android.hardware.biometrics.BiometricManager.class);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api30Impl {
        public static int canAuthenticate(android.hardware.biometrics.BiometricManager biometricManager, int i2) {
            return biometricManager.canAuthenticate(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultInjector implements Injector {
        private final Context mContext;

        public DefaultInjector(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public android.hardware.biometrics.BiometricManager getBiometricManager() {
            return Api29Impl.create(this.mContext);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Injector {
        android.hardware.biometrics.BiometricManager getBiometricManager();
    }

    public BiometricManager(Injector injector) {
        this.mInjector = injector;
        this.mBiometricManager = injector.getBiometricManager();
    }

    public static BiometricManager from(Context context) {
        return new BiometricManager(new DefaultInjector(context));
    }

    public int canAuthenticate(int i2) {
        android.hardware.biometrics.BiometricManager biometricManager = this.mBiometricManager;
        if (biometricManager != null) {
            return Api30Impl.canAuthenticate(biometricManager, i2);
        }
        Log.e("BiometricManager", "Failure in canAuthenticate(). BiometricManager was null.");
        return 1;
    }
}
