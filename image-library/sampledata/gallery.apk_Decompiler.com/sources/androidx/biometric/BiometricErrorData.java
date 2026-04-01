package androidx.biometric;

import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BiometricErrorData {
    private final int mErrorCode;
    private final CharSequence mErrorMessage;

    public BiometricErrorData(int i2, CharSequence charSequence) {
        this.mErrorCode = i2;
        this.mErrorMessage = charSequence;
    }

    private static String convertToString(CharSequence charSequence) {
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    private boolean isErrorMessageEqualTo(CharSequence charSequence) {
        String convertToString = convertToString(this.mErrorMessage);
        String convertToString2 = convertToString(charSequence);
        if (convertToString == null && convertToString2 == null) {
            return true;
        }
        if (convertToString == null || !convertToString.equals(convertToString2)) {
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BiometricErrorData) {
            BiometricErrorData biometricErrorData = (BiometricErrorData) obj;
            if (this.mErrorCode != biometricErrorData.mErrorCode || !isErrorMessageEqualTo(biometricErrorData.mErrorMessage)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mErrorCode), convertToString(this.mErrorMessage)});
    }
}
