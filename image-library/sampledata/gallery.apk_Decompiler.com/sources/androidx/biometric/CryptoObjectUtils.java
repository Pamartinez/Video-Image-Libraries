package androidx.biometric;

import android.hardware.biometrics.BiometricPrompt;
import android.security.identity.IdentityCredential;
import android.util.Log;
import androidx.biometric.BiometricPrompt;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class CryptoObjectUtils {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static BiometricPrompt.CryptoObject create(Cipher cipher) {
            return new BiometricPrompt.CryptoObject(cipher);
        }

        public static Cipher getCipher(BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getCipher();
        }

        public static Mac getMac(BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getMac();
        }

        public static Signature getSignature(BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getSignature();
        }

        public static BiometricPrompt.CryptoObject create(Signature signature) {
            return new BiometricPrompt.CryptoObject(signature);
        }

        public static BiometricPrompt.CryptoObject create(Mac mac) {
            return new BiometricPrompt.CryptoObject(mac);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api30Impl {
        public static BiometricPrompt.CryptoObject create(IdentityCredential identityCredential) {
            return new BiometricPrompt.CryptoObject(identityCredential);
        }

        public static IdentityCredential getIdentityCredential(BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getIdentityCredential();
        }
    }

    public static BiometricPrompt.CryptoObject unwrapFromBiometricPrompt(BiometricPrompt.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        Cipher cipher = Api28Impl.getCipher(cryptoObject);
        if (cipher != null) {
            return new BiometricPrompt.CryptoObject(cipher);
        }
        Signature signature = Api28Impl.getSignature(cryptoObject);
        if (signature != null) {
            return new BiometricPrompt.CryptoObject(signature);
        }
        Mac mac = Api28Impl.getMac(cryptoObject);
        if (mac != null) {
            return new BiometricPrompt.CryptoObject(mac);
        }
        IdentityCredential identityCredential = Api30Impl.getIdentityCredential(cryptoObject);
        if (identityCredential != null) {
            return new BiometricPrompt.CryptoObject(identityCredential);
        }
        return null;
    }

    public static BiometricPrompt.CryptoObject unwrapFromFingerprintManager(FingerprintManagerCompat.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        Cipher cipher = cryptoObject.getCipher();
        if (cipher != null) {
            return new BiometricPrompt.CryptoObject(cipher);
        }
        Signature signature = cryptoObject.getSignature();
        if (signature != null) {
            return new BiometricPrompt.CryptoObject(signature);
        }
        Mac mac = cryptoObject.getMac();
        if (mac != null) {
            return new BiometricPrompt.CryptoObject(mac);
        }
        return null;
    }

    public static BiometricPrompt.CryptoObject wrapForBiometricPrompt(BiometricPrompt.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        Cipher cipher = cryptoObject.getCipher();
        if (cipher != null) {
            return Api28Impl.create(cipher);
        }
        Signature signature = cryptoObject.getSignature();
        if (signature != null) {
            return Api28Impl.create(signature);
        }
        Mac mac = cryptoObject.getMac();
        if (mac != null) {
            return Api28Impl.create(mac);
        }
        IdentityCredential identityCredential = cryptoObject.getIdentityCredential();
        if (identityCredential != null) {
            return Api30Impl.create(identityCredential);
        }
        return null;
    }

    public static FingerprintManagerCompat.CryptoObject wrapForFingerprintManager(BiometricPrompt.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        Cipher cipher = cryptoObject.getCipher();
        if (cipher != null) {
            return new FingerprintManagerCompat.CryptoObject(cipher);
        }
        Signature signature = cryptoObject.getSignature();
        if (signature != null) {
            return new FingerprintManagerCompat.CryptoObject(signature);
        }
        Mac mac = cryptoObject.getMac();
        if (mac != null) {
            return new FingerprintManagerCompat.CryptoObject(mac);
        }
        if (cryptoObject.getIdentityCredential() != null) {
            Log.e("CryptoObjectUtils", "Identity credential is not supported by FingerprintManager.");
        }
        return null;
    }
}
