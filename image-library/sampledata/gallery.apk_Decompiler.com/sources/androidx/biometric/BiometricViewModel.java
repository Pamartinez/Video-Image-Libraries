package androidx.biometric;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import androidx.biometric.AuthenticationCallbackProvider;
import androidx.biometric.BiometricPrompt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BiometricViewModel extends ViewModel {
    private AuthenticationCallbackProvider mAuthenticationCallbackProvider;
    private MutableLiveData<BiometricErrorData> mAuthenticationError;
    private MutableLiveData<CharSequence> mAuthenticationHelpMessage;
    private MutableLiveData<BiometricPrompt.AuthenticationResult> mAuthenticationResult;
    private int mCanceledFrom = 0;
    private CancellationSignalProvider mCancellationSignalProvider;
    private BiometricPrompt.AuthenticationCallback mClientCallback;
    private Executor mClientExecutor;
    private BiometricPrompt.CryptoObject mCryptoObject;
    private MutableLiveData<CharSequence> mFingerprintDialogHelpMessage;
    private int mFingerprintDialogPreviousState = 0;
    private MutableLiveData<Integer> mFingerprintDialogState;
    private MutableLiveData<Boolean> mIsAuthenticationFailurePending;
    private boolean mIsAwaitingResult;
    private boolean mIsConfirmingDeviceCredential;
    private boolean mIsDelayingPrompt;
    private MutableLiveData<Boolean> mIsFingerprintDialogCancelPending;
    private boolean mIsFingerprintDialogDismissedInstantly = true;
    private boolean mIsIgnoringCancel;
    private MutableLiveData<Boolean> mIsNegativeButtonPressPending;
    private boolean mIsPromptShowing;
    private DialogInterface.OnClickListener mNegativeButtonListener;
    private CharSequence mNegativeButtonTextOverride;
    private BiometricPrompt.PromptInfo mPromptInfo;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CallbackListener extends AuthenticationCallbackProvider.Listener {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        public CallbackListener(BiometricViewModel biometricViewModel) {
            this.mViewModelRef = new WeakReference<>(biometricViewModel);
        }

        public void onError(int i2, CharSequence charSequence) {
            if (this.mViewModelRef.get() != null && !this.mViewModelRef.get().isConfirmingDeviceCredential() && this.mViewModelRef.get().isAwaitingResult()) {
                this.mViewModelRef.get().setAuthenticationError(new BiometricErrorData(i2, charSequence));
            }
        }

        public void onFailure() {
            if (this.mViewModelRef.get() != null && this.mViewModelRef.get().isAwaitingResult()) {
                this.mViewModelRef.get().setAuthenticationFailurePending(true);
            }
        }

        public void onHelp(CharSequence charSequence) {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().setAuthenticationHelpMessage(charSequence);
            }
        }

        public void onSuccess(BiometricPrompt.AuthenticationResult authenticationResult) {
            if (this.mViewModelRef.get() != null && this.mViewModelRef.get().isAwaitingResult()) {
                if (authenticationResult.getAuthenticationType() == -1) {
                    authenticationResult = new BiometricPrompt.AuthenticationResult(authenticationResult.getCryptoObject(), this.mViewModelRef.get().getInferredAuthenticationResultType());
                }
                this.mViewModelRef.get().setAuthenticationResult(authenticationResult);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultExecutor implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NegativeButtonListener implements DialogInterface.OnClickListener {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        public NegativeButtonListener(BiometricViewModel biometricViewModel) {
            this.mViewModelRef = new WeakReference<>(biometricViewModel);
        }

        public void onClick(DialogInterface dialogInterface, int i2) {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().setNegativeButtonPressPending(true);
            }
        }
    }

    private static <T> void updateValue(MutableLiveData<T> mutableLiveData, T t) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            mutableLiveData.setValue(t);
        } else {
            mutableLiveData.postValue(t);
        }
    }

    public int getAllowedAuthenticators() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return AuthenticatorUtils.getConsolidatedAuthenticators(promptInfo, this.mCryptoObject);
        }
        return 0;
    }

    public AuthenticationCallbackProvider getAuthenticationCallbackProvider() {
        if (this.mAuthenticationCallbackProvider == null) {
            this.mAuthenticationCallbackProvider = new AuthenticationCallbackProvider(new CallbackListener(this));
        }
        return this.mAuthenticationCallbackProvider;
    }

    public MutableLiveData<BiometricErrorData> getAuthenticationError() {
        if (this.mAuthenticationError == null) {
            this.mAuthenticationError = new MutableLiveData<>();
        }
        return this.mAuthenticationError;
    }

    public LiveData<CharSequence> getAuthenticationHelpMessage() {
        if (this.mAuthenticationHelpMessage == null) {
            this.mAuthenticationHelpMessage = new MutableLiveData<>();
        }
        return this.mAuthenticationHelpMessage;
    }

    public LiveData<BiometricPrompt.AuthenticationResult> getAuthenticationResult() {
        if (this.mAuthenticationResult == null) {
            this.mAuthenticationResult = new MutableLiveData<>();
        }
        return this.mAuthenticationResult;
    }

    public int getCanceledFrom() {
        return this.mCanceledFrom;
    }

    public CancellationSignalProvider getCancellationSignalProvider() {
        if (this.mCancellationSignalProvider == null) {
            this.mCancellationSignalProvider = new CancellationSignalProvider();
        }
        return this.mCancellationSignalProvider;
    }

    public BiometricPrompt.AuthenticationCallback getClientCallback() {
        if (this.mClientCallback == null) {
            this.mClientCallback = new BiometricPrompt.AuthenticationCallback() {
            };
        }
        return this.mClientCallback;
    }

    public Executor getClientExecutor() {
        Executor executor = this.mClientExecutor;
        if (executor != null) {
            return executor;
        }
        return new DefaultExecutor();
    }

    public BiometricPrompt.CryptoObject getCryptoObject() {
        return this.mCryptoObject;
    }

    public CharSequence getDescription() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return promptInfo.getDescription();
        }
        return null;
    }

    public LiveData<CharSequence> getFingerprintDialogHelpMessage() {
        if (this.mFingerprintDialogHelpMessage == null) {
            this.mFingerprintDialogHelpMessage = new MutableLiveData<>();
        }
        return this.mFingerprintDialogHelpMessage;
    }

    public int getFingerprintDialogPreviousState() {
        return this.mFingerprintDialogPreviousState;
    }

    public LiveData<Integer> getFingerprintDialogState() {
        if (this.mFingerprintDialogState == null) {
            this.mFingerprintDialogState = new MutableLiveData<>();
        }
        return this.mFingerprintDialogState;
    }

    public int getInferredAuthenticationResultType() {
        int allowedAuthenticators = getAllowedAuthenticators();
        if (!AuthenticatorUtils.isSomeBiometricAllowed(allowedAuthenticators) || AuthenticatorUtils.isDeviceCredentialAllowed(allowedAuthenticators)) {
            return -1;
        }
        return 2;
    }

    public DialogInterface.OnClickListener getNegativeButtonListener() {
        if (this.mNegativeButtonListener == null) {
            this.mNegativeButtonListener = new NegativeButtonListener(this);
        }
        return this.mNegativeButtonListener;
    }

    public CharSequence getNegativeButtonText() {
        CharSequence charSequence = this.mNegativeButtonTextOverride;
        if (charSequence != null) {
            return charSequence;
        }
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return promptInfo.getNegativeButtonText();
        }
        return null;
    }

    public CharSequence getSubtitle() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return promptInfo.getSubtitle();
        }
        return null;
    }

    public CharSequence getTitle() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo != null) {
            return promptInfo.getTitle();
        }
        return null;
    }

    public LiveData<Boolean> isAuthenticationFailurePending() {
        if (this.mIsAuthenticationFailurePending == null) {
            this.mIsAuthenticationFailurePending = new MutableLiveData<>();
        }
        return this.mIsAuthenticationFailurePending;
    }

    public boolean isAwaitingResult() {
        return this.mIsAwaitingResult;
    }

    public boolean isConfirmationRequired() {
        BiometricPrompt.PromptInfo promptInfo = this.mPromptInfo;
        if (promptInfo == null || promptInfo.isConfirmationRequired()) {
            return true;
        }
        return false;
    }

    public boolean isConfirmingDeviceCredential() {
        return this.mIsConfirmingDeviceCredential;
    }

    public boolean isDelayingPrompt() {
        return this.mIsDelayingPrompt;
    }

    public LiveData<Boolean> isFingerprintDialogCancelPending() {
        if (this.mIsFingerprintDialogCancelPending == null) {
            this.mIsFingerprintDialogCancelPending = new MutableLiveData<>();
        }
        return this.mIsFingerprintDialogCancelPending;
    }

    public boolean isFingerprintDialogDismissedInstantly() {
        return this.mIsFingerprintDialogDismissedInstantly;
    }

    public boolean isIgnoringCancel() {
        return this.mIsIgnoringCancel;
    }

    public LiveData<Boolean> isNegativeButtonPressPending() {
        if (this.mIsNegativeButtonPressPending == null) {
            this.mIsNegativeButtonPressPending = new MutableLiveData<>();
        }
        return this.mIsNegativeButtonPressPending;
    }

    public boolean isPromptShowing() {
        return this.mIsPromptShowing;
    }

    public void resetClientCallback() {
        this.mClientCallback = null;
    }

    public void setAuthenticationError(BiometricErrorData biometricErrorData) {
        if (this.mAuthenticationError == null) {
            this.mAuthenticationError = new MutableLiveData<>();
        }
        updateValue(this.mAuthenticationError, biometricErrorData);
    }

    public void setAuthenticationFailurePending(boolean z) {
        if (this.mIsAuthenticationFailurePending == null) {
            this.mIsAuthenticationFailurePending = new MutableLiveData<>();
        }
        updateValue(this.mIsAuthenticationFailurePending, Boolean.valueOf(z));
    }

    public void setAuthenticationHelpMessage(CharSequence charSequence) {
        if (this.mAuthenticationHelpMessage == null) {
            this.mAuthenticationHelpMessage = new MutableLiveData<>();
        }
        updateValue(this.mAuthenticationHelpMessage, charSequence);
    }

    public void setAuthenticationResult(BiometricPrompt.AuthenticationResult authenticationResult) {
        if (this.mAuthenticationResult == null) {
            this.mAuthenticationResult = new MutableLiveData<>();
        }
        updateValue(this.mAuthenticationResult, authenticationResult);
    }

    public void setAwaitingResult(boolean z) {
        this.mIsAwaitingResult = z;
    }

    public void setCanceledFrom(int i2) {
        this.mCanceledFrom = i2;
    }

    public void setClientCallback(BiometricPrompt.AuthenticationCallback authenticationCallback) {
        this.mClientCallback = authenticationCallback;
    }

    public void setClientExecutor(Executor executor) {
        this.mClientExecutor = executor;
    }

    public void setConfirmingDeviceCredential(boolean z) {
        this.mIsConfirmingDeviceCredential = z;
    }

    public void setCryptoObject(BiometricPrompt.CryptoObject cryptoObject) {
        this.mCryptoObject = cryptoObject;
    }

    public void setDelayingPrompt(boolean z) {
        this.mIsDelayingPrompt = z;
    }

    public void setFingerprintDialogCancelPending(boolean z) {
        if (this.mIsFingerprintDialogCancelPending == null) {
            this.mIsFingerprintDialogCancelPending = new MutableLiveData<>();
        }
        updateValue(this.mIsFingerprintDialogCancelPending, Boolean.valueOf(z));
    }

    public void setFingerprintDialogDismissedInstantly(boolean z) {
        this.mIsFingerprintDialogDismissedInstantly = z;
    }

    public void setFingerprintDialogHelpMessage(CharSequence charSequence) {
        if (this.mFingerprintDialogHelpMessage == null) {
            this.mFingerprintDialogHelpMessage = new MutableLiveData<>();
        }
        updateValue(this.mFingerprintDialogHelpMessage, charSequence);
    }

    public void setFingerprintDialogPreviousState(int i2) {
        this.mFingerprintDialogPreviousState = i2;
    }

    public void setFingerprintDialogState(int i2) {
        if (this.mFingerprintDialogState == null) {
            this.mFingerprintDialogState = new MutableLiveData<>();
        }
        updateValue(this.mFingerprintDialogState, Integer.valueOf(i2));
    }

    public void setNegativeButtonPressPending(boolean z) {
        if (this.mIsNegativeButtonPressPending == null) {
            this.mIsNegativeButtonPressPending = new MutableLiveData<>();
        }
        updateValue(this.mIsNegativeButtonPressPending, Boolean.valueOf(z));
    }

    public void setNegativeButtonTextOverride(CharSequence charSequence) {
        this.mNegativeButtonTextOverride = charSequence;
    }

    public void setPromptInfo(BiometricPrompt.PromptInfo promptInfo) {
        this.mPromptInfo = promptInfo;
    }

    public void setPromptShowing(boolean z) {
        this.mIsPromptShowing = z;
    }
}
