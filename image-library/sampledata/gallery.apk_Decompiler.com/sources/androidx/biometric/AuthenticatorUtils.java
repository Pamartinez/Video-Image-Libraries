package androidx.biometric;

import androidx.biometric.BiometricPrompt;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AuthenticatorUtils {
    public static String convertToString(int i2) {
        if (i2 == 15) {
            return "BIOMETRIC_STRONG";
        }
        if (i2 == 255) {
            return "BIOMETRIC_WEAK";
        }
        if (i2 == 32768) {
            return "DEVICE_CREDENTIAL";
        }
        if (i2 == 32783) {
            return "BIOMETRIC_STRONG | DEVICE_CREDENTIAL";
        }
        if (i2 != 33023) {
            return String.valueOf(i2);
        }
        return "BIOMETRIC_WEAK | DEVICE_CREDENTIAL";
    }

    public static int getConsolidatedAuthenticators(BiometricPrompt.PromptInfo promptInfo, BiometricPrompt.CryptoObject cryptoObject) {
        int i2;
        if (promptInfo.getAllowedAuthenticators() != 0) {
            return promptInfo.getAllowedAuthenticators();
        }
        if (cryptoObject != null) {
            i2 = 15;
        } else {
            i2 = ScoverState.TYPE_NFC_SMART_COVER;
        }
        if (promptInfo.isDeviceCredentialAllowed()) {
            return 32768 | i2;
        }
        return i2;
    }

    public static boolean isDeviceCredentialAllowed(int i2) {
        if ((i2 & 32768) != 0) {
            return true;
        }
        return false;
    }

    public static boolean isSomeBiometricAllowed(int i2) {
        if ((i2 & 32767) != 0) {
            return true;
        }
        return false;
    }

    public static boolean isSupportedCombination(int i2) {
        if (i2 == 15 || i2 == 255 || i2 == 32768 || i2 == 32783 || i2 == 33023 || i2 == 0) {
            return true;
        }
        return false;
    }
}
