package androidx.biometric;

import android.os.Build;
import android.security.identity.IdentityCredential;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import java.lang.ref.WeakReference;
import java.security.Signature;
import java.util.concurrent.Executor;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BiometricPrompt {
    private FragmentManager mClientFragmentManager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AuthenticationResult {
        private final int mAuthenticationType;
        private final CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject cryptoObject, int i2) {
            this.mCryptoObject = cryptoObject;
            this.mAuthenticationType = i2;
        }

        public int getAuthenticationType() {
            return this.mAuthenticationType;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PromptInfo {
        private final int mAllowedAuthenticators;
        private final CharSequence mDescription;
        private final boolean mIsConfirmationRequired;
        private final boolean mIsDeviceCredentialAllowed;
        private final CharSequence mNegativeButtonText;
        private final CharSequence mSubtitle;
        private final CharSequence mTitle;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Builder {
            private int mAllowedAuthenticators = 0;
            private CharSequence mDescription = null;
            private boolean mIsConfirmationRequired = true;
            private boolean mIsDeviceCredentialAllowed = false;
            private CharSequence mNegativeButtonText = null;
            private CharSequence mSubtitle = null;
            private CharSequence mTitle = null;

            public PromptInfo build() {
                boolean z;
                if (TextUtils.isEmpty(this.mTitle)) {
                    throw new IllegalArgumentException("Title must be set and non-empty.");
                } else if (AuthenticatorUtils.isSupportedCombination(this.mAllowedAuthenticators)) {
                    int i2 = this.mAllowedAuthenticators;
                    if (i2 != 0) {
                        z = AuthenticatorUtils.isDeviceCredentialAllowed(i2);
                    } else {
                        z = this.mIsDeviceCredentialAllowed;
                    }
                    if (TextUtils.isEmpty(this.mNegativeButtonText) && !z) {
                        throw new IllegalArgumentException("Negative text must be set and non-empty.");
                    } else if (TextUtils.isEmpty(this.mNegativeButtonText) || !z) {
                        return new PromptInfo(this.mTitle, this.mSubtitle, this.mDescription, this.mNegativeButtonText, this.mIsConfirmationRequired, this.mIsDeviceCredentialAllowed, this.mAllowedAuthenticators);
                    } else {
                        throw new IllegalArgumentException("Negative text must not be set if device credential authentication is allowed.");
                    }
                } else {
                    throw new IllegalArgumentException("Authenticator combination is unsupported on API " + Build.VERSION.SDK_INT + ": " + AuthenticatorUtils.convertToString(this.mAllowedAuthenticators));
                }
            }

            public Builder setAllowedAuthenticators(int i2) {
                this.mAllowedAuthenticators = i2;
                return this;
            }

            public Builder setDescription(CharSequence charSequence) {
                this.mDescription = charSequence;
                return this;
            }

            public Builder setTitle(CharSequence charSequence) {
                this.mTitle = charSequence;
                return this;
            }
        }

        public PromptInfo(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, boolean z, boolean z3, int i2) {
            this.mTitle = charSequence;
            this.mSubtitle = charSequence2;
            this.mDescription = charSequence3;
            this.mNegativeButtonText = charSequence4;
            this.mIsConfirmationRequired = z;
            this.mIsDeviceCredentialAllowed = z3;
            this.mAllowedAuthenticators = i2;
        }

        public int getAllowedAuthenticators() {
            return this.mAllowedAuthenticators;
        }

        public CharSequence getDescription() {
            return this.mDescription;
        }

        public CharSequence getNegativeButtonText() {
            CharSequence charSequence = this.mNegativeButtonText;
            if (charSequence != null) {
                return charSequence;
            }
            return "";
        }

        public CharSequence getSubtitle() {
            return this.mSubtitle;
        }

        public CharSequence getTitle() {
            return this.mTitle;
        }

        public boolean isConfirmationRequired() {
            return this.mIsConfirmationRequired;
        }

        @Deprecated
        public boolean isDeviceCredentialAllowed() {
            return this.mIsDeviceCredentialAllowed;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ResetCallbackObserver implements LifecycleObserver {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void resetCallback() {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().resetClientCallback();
            }
        }
    }

    public BiometricPrompt(FragmentActivity fragmentActivity, AuthenticationCallback authenticationCallback) {
        if (fragmentActivity == null) {
            throw new IllegalArgumentException("FragmentActivity must not be null.");
        } else if (authenticationCallback != null) {
            init(fragmentActivity.getSupportFragmentManager(), getViewModel(fragmentActivity), (Executor) null, authenticationCallback);
        } else {
            throw new IllegalArgumentException("AuthenticationCallback must not be null.");
        }
    }

    private void authenticateInternal(PromptInfo promptInfo, CryptoObject cryptoObject) {
        FragmentManager fragmentManager = this.mClientFragmentManager;
        if (fragmentManager == null) {
            Log.e("BiometricPromptCompat", "Unable to start authentication. Client fragment manager was null.");
        } else if (fragmentManager.isStateSaved()) {
            Log.e("BiometricPromptCompat", "Unable to start authentication. Called after onSaveInstanceState().");
        } else {
            findOrAddBiometricFragment(this.mClientFragmentManager).authenticate(promptInfo, cryptoObject);
        }
    }

    private static BiometricFragment findBiometricFragment(FragmentManager fragmentManager) {
        return (BiometricFragment) fragmentManager.findFragmentByTag("androidx.biometric.BiometricFragment");
    }

    private static BiometricFragment findOrAddBiometricFragment(FragmentManager fragmentManager) {
        BiometricFragment findBiometricFragment = findBiometricFragment(fragmentManager);
        if (findBiometricFragment != null) {
            return findBiometricFragment;
        }
        BiometricFragment newInstance = BiometricFragment.newInstance();
        fragmentManager.beginTransaction().add((Fragment) newInstance, "androidx.biometric.BiometricFragment").commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
        return newInstance;
    }

    private static BiometricViewModel getViewModel(FragmentActivity fragmentActivity) {
        if (fragmentActivity != null) {
            return (BiometricViewModel) new ViewModelProvider(fragmentActivity).get(BiometricViewModel.class);
        }
        return null;
    }

    private void init(FragmentManager fragmentManager, BiometricViewModel biometricViewModel, Executor executor, AuthenticationCallback authenticationCallback) {
        this.mClientFragmentManager = fragmentManager;
        if (biometricViewModel != null) {
            if (executor != null) {
                biometricViewModel.setClientExecutor(executor);
            }
            biometricViewModel.setClientCallback(authenticationCallback);
        }
    }

    public void authenticate(PromptInfo promptInfo) {
        if (promptInfo != null) {
            authenticateInternal(promptInfo, (CryptoObject) null);
            return;
        }
        throw new IllegalArgumentException("PromptInfo cannot be null.");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CryptoObject {
        private final Cipher mCipher;
        private final IdentityCredential mIdentityCredential;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(Signature signature) {
            this.mSignature = signature;
            this.mCipher = null;
            this.mMac = null;
            this.mIdentityCredential = null;
        }

        public Cipher getCipher() {
            return this.mCipher;
        }

        public IdentityCredential getIdentityCredential() {
            return this.mIdentityCredential;
        }

        public Mac getMac() {
            return this.mMac;
        }

        public Signature getSignature() {
            return this.mSignature;
        }

        public CryptoObject(Cipher cipher) {
            this.mSignature = null;
            this.mCipher = cipher;
            this.mMac = null;
            this.mIdentityCredential = null;
        }

        public CryptoObject(Mac mac) {
            this.mSignature = null;
            this.mCipher = null;
            this.mMac = mac;
            this.mIdentityCredential = null;
        }

        public CryptoObject(IdentityCredential identityCredential) {
            this.mSignature = null;
            this.mCipher = null;
            this.mMac = null;
            this.mIdentityCredential = identityCredential;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class AuthenticationCallback {
        public void onAuthenticationFailed() {
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        }

        public void onAuthenticationError(int i2, CharSequence charSequence) {
        }
    }
}
