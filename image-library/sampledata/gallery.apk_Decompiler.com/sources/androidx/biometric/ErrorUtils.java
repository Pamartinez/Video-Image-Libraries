package androidx.biometric;

import android.content.Context;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ErrorUtils {
    public static String getFingerprintErrorString(Context context, int i2) {
        if (context == null) {
            return "";
        }
        if (i2 == 1) {
            return context.getString(R$string.fingerprint_error_hw_not_available);
        }
        if (i2 != 7) {
            switch (i2) {
                case 9:
                    break;
                case 10:
                    return context.getString(R$string.fingerprint_error_user_canceled);
                case 11:
                    return context.getString(R$string.fingerprint_error_no_fingerprints);
                case 12:
                    return context.getString(R$string.fingerprint_error_hw_not_present);
                default:
                    Log.e("BiometricUtils", "Unknown error code: " + i2);
                    return context.getString(R$string.default_error_msg);
            }
        }
        return context.getString(R$string.fingerprint_error_lockout);
    }

    public static boolean isKnownError(int i2) {
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return true;
            default:
                return false;
        }
    }
}
