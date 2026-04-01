package androidx.biometric;

import android.hardware.biometrics.BiometricPrompt;
import androidx.biometric.BiometricPrompt;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AuthenticationCallbackProvider {
    private BiometricPrompt.AuthenticationCallback mBiometricCallback;
    private FingerprintManagerCompat.AuthenticationCallback mFingerprintCallback;
    final Listener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static BiometricPrompt.AuthenticationCallback createCallback(final Listener listener) {
            return new BiometricPrompt.AuthenticationCallback() {
                public void onAuthenticationError(int i2, CharSequence charSequence) {
                    Listener.this.onError(i2, charSequence);
                }

                public void onAuthenticationFailed() {
                    Listener.this.onFailure();
                }

                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
                    BiometricPrompt.CryptoObject cryptoObject;
                    int i2;
                    if (authenticationResult != null) {
                        cryptoObject = CryptoObjectUtils.unwrapFromBiometricPrompt(authenticationResult.getCryptoObject());
                    } else {
                        cryptoObject = null;
                    }
                    if (authenticationResult != null) {
                        i2 = Api30Impl.getAuthenticationType(authenticationResult);
                    } else {
                        i2 = -1;
                    }
                    Listener.this.onSuccess(new BiometricPrompt.AuthenticationResult(cryptoObject, i2));
                }

                public void onAuthenticationHelp(int i2, CharSequence charSequence) {
                }
            };
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api30Impl {
        public static int getAuthenticationType(BiometricPrompt.AuthenticationResult authenticationResult) {
            return authenticationResult.getAuthenticationType();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Listener {
        public abstract void onError(int i2, CharSequence charSequence);

        public abstract void onFailure();

        public abstract void onHelp(CharSequence charSequence);

        public abstract void onSuccess(BiometricPrompt.AuthenticationResult authenticationResult);
    }

    public AuthenticationCallbackProvider(Listener listener) {
        this.mListener = listener;
    }

    public BiometricPrompt.AuthenticationCallback getBiometricCallback() {
        if (this.mBiometricCallback == null) {
            this.mBiometricCallback = Api28Impl.createCallback(this.mListener);
        }
        return this.mBiometricCallback;
    }

    public FingerprintManagerCompat.AuthenticationCallback getFingerprintCallback() {
        if (this.mFingerprintCallback == null) {
            this.mFingerprintCallback = new FingerprintManagerCompat.AuthenticationCallback() {
                public void onAuthenticationError(int i2, CharSequence charSequence) {
                    AuthenticationCallbackProvider.this.mListener.onError(i2, charSequence);
                }

                public void onAuthenticationFailed() {
                    AuthenticationCallbackProvider.this.mListener.onFailure();
                }

                public void onAuthenticationHelp(int i2, CharSequence charSequence) {
                    AuthenticationCallbackProvider.this.mListener.onHelp(charSequence);
                }

                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult authenticationResult) {
                    BiometricPrompt.CryptoObject cryptoObject;
                    if (authenticationResult != null) {
                        cryptoObject = CryptoObjectUtils.unwrapFromFingerprintManager(authenticationResult.getCryptoObject());
                    } else {
                        cryptoObject = null;
                    }
                    AuthenticationCallbackProvider.this.mListener.onSuccess(new BiometricPrompt.AuthenticationResult(cryptoObject, 2));
                }
            };
        }
        return this.mFingerprintCallback;
    }
}
