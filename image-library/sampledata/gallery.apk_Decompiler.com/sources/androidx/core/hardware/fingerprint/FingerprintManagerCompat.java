package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.os.Handler;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FingerprintManagerCompat {
    private final Context mContext;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static void authenticate(Object obj, Object obj2, CancellationSignal cancellationSignal, int i2, Object obj3, Handler handler) {
            ((FingerprintManager) obj).authenticate((FingerprintManager.CryptoObject) obj2, cancellationSignal, i2, (FingerprintManager.AuthenticationCallback) obj3, handler);
        }

        public static FingerprintManager.CryptoObject getCryptoObject(Object obj) {
            return ((FingerprintManager.AuthenticationResult) obj).getCryptoObject();
        }

        public static FingerprintManager getFingerprintManagerOrNull(Context context) {
            if (context.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
                return (FingerprintManager) context.getSystemService(FingerprintManager.class);
            }
            return null;
        }

        public static boolean hasEnrolledFingerprints(Object obj) {
            return ((FingerprintManager) obj).hasEnrolledFingerprints();
        }

        public static boolean isHardwareDetected(Object obj) {
            return ((FingerprintManager) obj).isHardwareDetected();
        }

        public static CryptoObject unwrapCryptoObject(Object obj) {
            FingerprintManager.CryptoObject cryptoObject = (FingerprintManager.CryptoObject) obj;
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.getCipher() != null) {
                return new CryptoObject(cryptoObject.getCipher());
            }
            if (cryptoObject.getSignature() != null) {
                return new CryptoObject(cryptoObject.getSignature());
            }
            if (cryptoObject.getMac() != null) {
                return new CryptoObject(cryptoObject.getMac());
            }
            return null;
        }

        public static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject cryptoObject) {
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.getCipher() != null) {
                return new FingerprintManager.CryptoObject(cryptoObject.getCipher());
            }
            if (cryptoObject.getSignature() != null) {
                return new FingerprintManager.CryptoObject(cryptoObject.getSignature());
            }
            if (cryptoObject.getMac() != null) {
                return new FingerprintManager.CryptoObject(cryptoObject.getMac());
            }
            return null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class AuthenticationCallback {
        public abstract void onAuthenticationError(int i2, CharSequence charSequence);

        public abstract void onAuthenticationFailed();

        public abstract void onAuthenticationHelp(int i2, CharSequence charSequence);

        public abstract void onAuthenticationSucceeded(AuthenticationResult authenticationResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AuthenticationResult {
        private final CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject cryptoObject) {
            this.mCryptoObject = cryptoObject;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    private FingerprintManagerCompat(Context context) {
        this.mContext = context;
    }

    public static FingerprintManagerCompat from(Context context) {
        return new FingerprintManagerCompat(context);
    }

    private static FingerprintManager getFingerprintManagerOrNull(Context context) {
        return Api23Impl.getFingerprintManagerOrNull(context);
    }

    public static CryptoObject unwrapCryptoObject(FingerprintManager.CryptoObject cryptoObject) {
        return Api23Impl.unwrapCryptoObject(cryptoObject);
    }

    private static FingerprintManager.AuthenticationCallback wrapCallback(final AuthenticationCallback authenticationCallback) {
        return new FingerprintManager.AuthenticationCallback() {
            public void onAuthenticationError(int i2, CharSequence charSequence) {
                AuthenticationCallback.this.onAuthenticationError(i2, charSequence);
            }

            public void onAuthenticationFailed() {
                AuthenticationCallback.this.onAuthenticationFailed();
            }

            public void onAuthenticationHelp(int i2, CharSequence charSequence) {
                AuthenticationCallback.this.onAuthenticationHelp(i2, charSequence);
            }

            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
                AuthenticationCallback.this.onAuthenticationSucceeded(new AuthenticationResult(FingerprintManagerCompat.unwrapCryptoObject(Api23Impl.getCryptoObject(authenticationResult))));
            }
        };
    }

    private static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject cryptoObject) {
        return Api23Impl.wrapCryptoObject(cryptoObject);
    }

    @Deprecated
    public void authenticate(CryptoObject cryptoObject, int i2, androidx.core.os.CancellationSignal cancellationSignal, AuthenticationCallback authenticationCallback, Handler handler) {
        authenticate(cryptoObject, i2, cancellationSignal != null ? (CancellationSignal) cancellationSignal.getCancellationSignalObject() : null, authenticationCallback, handler);
    }

    public boolean hasEnrolledFingerprints() {
        FingerprintManager fingerprintManagerOrNull = getFingerprintManagerOrNull(this.mContext);
        if (fingerprintManagerOrNull == null || !Api23Impl.hasEnrolledFingerprints(fingerprintManagerOrNull)) {
            return false;
        }
        return true;
    }

    public boolean isHardwareDetected() {
        FingerprintManager fingerprintManagerOrNull = getFingerprintManagerOrNull(this.mContext);
        if (fingerprintManagerOrNull == null || !Api23Impl.isHardwareDetected(fingerprintManagerOrNull)) {
            return false;
        }
        return true;
    }

    public void authenticate(CryptoObject cryptoObject, int i2, CancellationSignal cancellationSignal, AuthenticationCallback authenticationCallback, Handler handler) {
        FingerprintManager fingerprintManagerOrNull = getFingerprintManagerOrNull(this.mContext);
        if (fingerprintManagerOrNull != null) {
            CancellationSignal cancellationSignal2 = cancellationSignal;
            Api23Impl.authenticate(fingerprintManagerOrNull, wrapCryptoObject(cryptoObject), cancellationSignal2, i2, wrapCallback(authenticationCallback), handler);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CryptoObject {
        private final Cipher mCipher;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(Signature signature) {
            this.mSignature = signature;
            this.mCipher = null;
            this.mMac = null;
        }

        public Cipher getCipher() {
            return this.mCipher;
        }

        public Mac getMac() {
            return this.mMac;
        }

        public Signature getSignature() {
            return this.mSignature;
        }

        public CryptoObject(Cipher cipher) {
            this.mCipher = cipher;
            this.mSignature = null;
            this.mMac = null;
        }

        public CryptoObject(Mac mac) {
            this.mMac = mac;
            this.mCipher = null;
            this.mSignature = null;
        }
    }
}
