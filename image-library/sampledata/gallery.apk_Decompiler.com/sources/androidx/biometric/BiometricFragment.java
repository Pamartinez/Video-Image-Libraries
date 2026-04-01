package androidx.biometric;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.biometric.BiometricPrompt;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.samsung.android.sdk.cover.ScoverState;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BiometricFragment extends Fragment {
    Handler mHandler = new Handler(Looper.getMainLooper());
    BiometricViewModel mViewModel;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static Intent createConfirmDeviceCredentialIntent(KeyguardManager keyguardManager, CharSequence charSequence, CharSequence charSequence2) {
            return keyguardManager.createConfirmDeviceCredentialIntent(charSequence, charSequence2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static void authenticate(BiometricPrompt biometricPrompt, CancellationSignal cancellationSignal, Executor executor, BiometricPrompt.AuthenticationCallback authenticationCallback) {
            biometricPrompt.authenticate(cancellationSignal, executor, authenticationCallback);
        }

        public static BiometricPrompt buildPrompt(BiometricPrompt.Builder builder) {
            return builder.build();
        }

        public static BiometricPrompt.Builder createPromptBuilder(Context context) {
            return new BiometricPrompt.Builder(context);
        }

        public static void setDescription(BiometricPrompt.Builder builder, CharSequence charSequence) {
            builder.setDescription(charSequence);
        }

        public static void setNegativeButton(BiometricPrompt.Builder builder, CharSequence charSequence, Executor executor, DialogInterface.OnClickListener onClickListener) {
            builder.setNegativeButton(charSequence, executor, onClickListener);
        }

        public static void setSubtitle(BiometricPrompt.Builder builder, CharSequence charSequence) {
            builder.setSubtitle(charSequence);
        }

        public static void setTitle(BiometricPrompt.Builder builder, CharSequence charSequence) {
            builder.setTitle(charSequence);
        }

        public static void authenticate(BiometricPrompt biometricPrompt, BiometricPrompt.CryptoObject cryptoObject, CancellationSignal cancellationSignal, Executor executor, BiometricPrompt.AuthenticationCallback authenticationCallback) {
            biometricPrompt.authenticate(cryptoObject, cancellationSignal, executor, authenticationCallback);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static void setConfirmationRequired(BiometricPrompt.Builder builder, boolean z) {
            builder.setConfirmationRequired(z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api30Impl {
        public static void setAllowedAuthenticators(BiometricPrompt.Builder builder, int i2) {
            builder.setAllowedAuthenticators(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PromptExecutor implements Executor {
        private final Handler mPromptHandler = new Handler(Looper.getMainLooper());

        public void execute(Runnable runnable) {
            this.mPromptHandler.post(runnable);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ShowPromptForAuthenticationRunnable implements Runnable {
        private final WeakReference<BiometricFragment> mFragmentRef;

        public ShowPromptForAuthenticationRunnable(BiometricFragment biometricFragment) {
            this.mFragmentRef = new WeakReference<>(biometricFragment);
        }

        public void run() {
            if (this.mFragmentRef.get() != null) {
                this.mFragmentRef.get().showPromptForAuthentication();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StopDelayingPromptRunnable implements Runnable {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        public StopDelayingPromptRunnable(BiometricViewModel biometricViewModel) {
            this.mViewModelRef = new WeakReference<>(biometricViewModel);
        }

        public void run() {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().setDelayingPrompt(false);
            }
        }
    }

    private static int checkForFingerprintPreAuthenticationErrors(FingerprintManagerCompat fingerprintManagerCompat) {
        if (!fingerprintManagerCompat.isHardwareDetected()) {
            return 12;
        }
        if (!fingerprintManagerCompat.hasEnrolledFingerprints()) {
            return 11;
        }
        return 0;
    }

    private void connectViewModel() {
        if (getActivity() != null) {
            BiometricViewModel biometricViewModel = (BiometricViewModel) new ViewModelProvider(getActivity()).get(BiometricViewModel.class);
            this.mViewModel = biometricViewModel;
            biometricViewModel.getAuthenticationResult().observe(this, new Observer<BiometricPrompt.AuthenticationResult>() {
                public void onChanged(BiometricPrompt.AuthenticationResult authenticationResult) {
                    if (authenticationResult != null) {
                        BiometricFragment.this.onAuthenticationSucceeded(authenticationResult);
                        BiometricFragment.this.mViewModel.setAuthenticationResult((BiometricPrompt.AuthenticationResult) null);
                    }
                }
            });
            this.mViewModel.getAuthenticationError().observe(this, new Observer<BiometricErrorData>() {
                public void onChanged(BiometricErrorData biometricErrorData) {
                    if (biometricErrorData != null) {
                        BiometricFragment.this.onAuthenticationError(biometricErrorData.getErrorCode(), biometricErrorData.getErrorMessage());
                        BiometricFragment.this.mViewModel.setAuthenticationError((BiometricErrorData) null);
                    }
                }
            });
            this.mViewModel.getAuthenticationHelpMessage().observe(this, new Observer<CharSequence>() {
                public void onChanged(CharSequence charSequence) {
                    if (charSequence != null) {
                        BiometricFragment.this.onAuthenticationHelp(charSequence);
                        BiometricFragment.this.mViewModel.setAuthenticationError((BiometricErrorData) null);
                    }
                }
            });
            this.mViewModel.isAuthenticationFailurePending().observe(this, new Observer<Boolean>() {
                public void onChanged(Boolean bool) {
                    if (bool.booleanValue()) {
                        BiometricFragment.this.onAuthenticationFailed();
                        BiometricFragment.this.mViewModel.setAuthenticationFailurePending(false);
                    }
                }
            });
            this.mViewModel.isNegativeButtonPressPending().observe(this, new Observer<Boolean>() {
                public void onChanged(Boolean bool) {
                    if (bool.booleanValue()) {
                        if (BiometricFragment.this.isManagingDeviceCredentialButton()) {
                            BiometricFragment.this.onDeviceCredentialButtonPressed();
                        } else {
                            BiometricFragment.this.onCancelButtonPressed();
                        }
                        BiometricFragment.this.mViewModel.setNegativeButtonPressPending(false);
                    }
                }
            });
            this.mViewModel.isFingerprintDialogCancelPending().observe(this, new Observer<Boolean>() {
                public void onChanged(Boolean bool) {
                    if (bool.booleanValue()) {
                        BiometricFragment.this.cancelAuthentication(1);
                        BiometricFragment.this.dismiss();
                        BiometricFragment.this.mViewModel.setFingerprintDialogCancelPending(false);
                    }
                }
            });
        }
    }

    private void dismissFingerprintDialog() {
        this.mViewModel.setPromptShowing(false);
        if (isAdded()) {
            FragmentManager parentFragmentManager = getParentFragmentManager();
            FingerprintDialogFragment fingerprintDialogFragment = (FingerprintDialogFragment) parentFragmentManager.findFragmentByTag("androidx.biometric.FingerprintDialogFragment");
            if (fingerprintDialogFragment == null) {
                return;
            }
            if (fingerprintDialogFragment.isAdded()) {
                fingerprintDialogFragment.dismissAllowingStateLoss();
            } else {
                parentFragmentManager.beginTransaction().remove(fingerprintDialogFragment).commitAllowingStateLoss();
            }
        }
    }

    private int getDismissDialogDelay() {
        Context context = getContext();
        if (context == null || !DeviceUtils.shouldHideFingerprintDialog(context, Build.MODEL)) {
            return 2000;
        }
        return 0;
    }

    private void handleConfirmCredentialResult(int i2) {
        if (i2 == -1) {
            sendSuccessAndDismiss(new BiometricPrompt.AuthenticationResult((BiometricPrompt.CryptoObject) null, 1));
        } else {
            sendErrorAndDismiss(10, getString(R$string.generic_error_user_canceled));
        }
    }

    private boolean isFingerprintDialogNeededForCrypto() {
        FragmentActivity activity = getActivity();
        if (activity == null || this.mViewModel.getCryptoObject() == null || !DeviceUtils.shouldUseFingerprintForCrypto(activity, Build.MANUFACTURER, Build.MODEL)) {
            return false;
        }
        return true;
    }

    private boolean isFingerprintDialogNeededForErrorHandling() {
        return false;
    }

    private boolean isUsingFingerprintDialog() {
        if (isFingerprintDialogNeededForCrypto() || isFingerprintDialogNeededForErrorHandling()) {
            return true;
        }
        return false;
    }

    private void launchConfirmCredentialActivity() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.e("BiometricFragment", "Failed to check device credential. Client FragmentActivity not found.");
            return;
        }
        KeyguardManager keyguardManager = KeyguardUtils.getKeyguardManager(activity);
        if (keyguardManager == null) {
            sendErrorAndDismiss(12, getString(R$string.generic_error_no_keyguard));
            return;
        }
        CharSequence title = this.mViewModel.getTitle();
        CharSequence subtitle = this.mViewModel.getSubtitle();
        CharSequence description = this.mViewModel.getDescription();
        if (subtitle == null) {
            subtitle = description;
        }
        Intent createConfirmDeviceCredentialIntent = Api21Impl.createConfirmDeviceCredentialIntent(keyguardManager, title, subtitle);
        if (createConfirmDeviceCredentialIntent == null) {
            sendErrorAndDismiss(14, getString(R$string.generic_error_no_device_credential));
            return;
        }
        this.mViewModel.setConfirmingDeviceCredential(true);
        if (isUsingFingerprintDialog()) {
            dismissFingerprintDialog();
        }
        createConfirmDeviceCredentialIntent.setFlags(134742016);
        startActivityForResult(createConfirmDeviceCredentialIntent, 1);
    }

    public static BiometricFragment newInstance() {
        return new BiometricFragment();
    }

    private void sendErrorToClient(final int i2, final CharSequence charSequence) {
        if (this.mViewModel.isConfirmingDeviceCredential()) {
            Log.v("BiometricFragment", "Error not sent to client. User is confirming their device credential.");
        } else if (!this.mViewModel.isAwaitingResult()) {
            Log.w("BiometricFragment", "Error not sent to client. Client is not awaiting a result.");
        } else {
            this.mViewModel.setAwaitingResult(false);
            this.mViewModel.getClientExecutor().execute(new Runnable() {
                public void run() {
                    BiometricFragment.this.mViewModel.getClientCallback().onAuthenticationError(i2, charSequence);
                }
            });
        }
    }

    private void sendFailureToClient() {
        if (!this.mViewModel.isAwaitingResult()) {
            Log.w("BiometricFragment", "Failure not sent to client. Client is not awaiting a result.");
        } else {
            this.mViewModel.getClientExecutor().execute(new Runnable() {
                public void run() {
                    BiometricFragment.this.mViewModel.getClientCallback().onAuthenticationFailed();
                }
            });
        }
    }

    private void sendSuccessAndDismiss(BiometricPrompt.AuthenticationResult authenticationResult) {
        sendSuccessToClient(authenticationResult);
        dismiss();
    }

    private void sendSuccessToClient(final BiometricPrompt.AuthenticationResult authenticationResult) {
        if (!this.mViewModel.isAwaitingResult()) {
            Log.w("BiometricFragment", "Success not sent to client. Client is not awaiting a result.");
            return;
        }
        this.mViewModel.setAwaitingResult(false);
        this.mViewModel.getClientExecutor().execute(new Runnable() {
            public void run() {
                BiometricFragment.this.mViewModel.getClientCallback().onAuthenticationSucceeded(authenticationResult);
            }
        });
    }

    private void showBiometricPromptForAuthentication() {
        BiometricPrompt.Builder createPromptBuilder = Api28Impl.createPromptBuilder(requireContext().getApplicationContext());
        CharSequence title = this.mViewModel.getTitle();
        CharSequence subtitle = this.mViewModel.getSubtitle();
        CharSequence description = this.mViewModel.getDescription();
        if (title != null) {
            Api28Impl.setTitle(createPromptBuilder, title);
        }
        if (subtitle != null) {
            Api28Impl.setSubtitle(createPromptBuilder, subtitle);
        }
        if (description != null) {
            Api28Impl.setDescription(createPromptBuilder, description);
        }
        CharSequence negativeButtonText = this.mViewModel.getNegativeButtonText();
        if (!TextUtils.isEmpty(negativeButtonText)) {
            Api28Impl.setNegativeButton(createPromptBuilder, negativeButtonText, this.mViewModel.getClientExecutor(), this.mViewModel.getNegativeButtonListener());
        }
        Api29Impl.setConfirmationRequired(createPromptBuilder, this.mViewModel.isConfirmationRequired());
        Api30Impl.setAllowedAuthenticators(createPromptBuilder, this.mViewModel.getAllowedAuthenticators());
        authenticateWithBiometricPrompt(Api28Impl.buildPrompt(createPromptBuilder), getContext());
    }

    private void showFingerprintDialogForAuthentication() {
        Context applicationContext = requireContext().getApplicationContext();
        FingerprintManagerCompat from = FingerprintManagerCompat.from(applicationContext);
        int checkForFingerprintPreAuthenticationErrors = checkForFingerprintPreAuthenticationErrors(from);
        if (checkForFingerprintPreAuthenticationErrors != 0) {
            sendErrorAndDismiss(checkForFingerprintPreAuthenticationErrors, ErrorUtils.getFingerprintErrorString(applicationContext, checkForFingerprintPreAuthenticationErrors));
        } else if (isAdded()) {
            this.mViewModel.setFingerprintDialogDismissedInstantly(true);
            if (!DeviceUtils.shouldHideFingerprintDialog(applicationContext, Build.MODEL)) {
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        BiometricFragment.this.mViewModel.setFingerprintDialogDismissedInstantly(false);
                    }
                }, 500);
                FingerprintDialogFragment.newInstance().show(getParentFragmentManager(), "androidx.biometric.FingerprintDialogFragment");
            }
            this.mViewModel.setCanceledFrom(0);
            authenticateWithFingerprint(from, applicationContext);
        }
    }

    private void showFingerprintErrorMessage(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = getString(R$string.default_error_msg);
        }
        this.mViewModel.setFingerprintDialogState(2);
        this.mViewModel.setFingerprintDialogHelpMessage(charSequence);
    }

    public void authenticate(BiometricPrompt.PromptInfo promptInfo, BiometricPrompt.CryptoObject cryptoObject) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Log.e("BiometricFragment", "Not launching prompt. Client activity was null.");
            return;
        }
        this.mViewModel.setPromptInfo(promptInfo);
        AuthenticatorUtils.getConsolidatedAuthenticators(promptInfo, cryptoObject);
        this.mViewModel.setCryptoObject(cryptoObject);
        if (isManagingDeviceCredentialButton()) {
            this.mViewModel.setNegativeButtonTextOverride(getString(R$string.confirm_device_credential_password));
        } else {
            this.mViewModel.setNegativeButtonTextOverride((CharSequence) null);
        }
        if (isManagingDeviceCredentialButton() && BiometricManager.from(activity).canAuthenticate(ScoverState.TYPE_NFC_SMART_COVER) != 0) {
            this.mViewModel.setAwaitingResult(true);
            launchConfirmCredentialActivity();
        } else if (this.mViewModel.isDelayingPrompt()) {
            this.mHandler.postDelayed(new ShowPromptForAuthenticationRunnable(this), 600);
        } else {
            showPromptForAuthentication();
        }
    }

    public void authenticateWithBiometricPrompt(android.hardware.biometrics.BiometricPrompt biometricPrompt, Context context) {
        String str;
        BiometricPrompt.CryptoObject wrapForBiometricPrompt = CryptoObjectUtils.wrapForBiometricPrompt(this.mViewModel.getCryptoObject());
        CancellationSignal biometricCancellationSignal = this.mViewModel.getCancellationSignalProvider().getBiometricCancellationSignal();
        PromptExecutor promptExecutor = new PromptExecutor();
        BiometricPrompt.AuthenticationCallback biometricCallback = this.mViewModel.getAuthenticationCallbackProvider().getBiometricCallback();
        if (wrapForBiometricPrompt == null) {
            try {
                Api28Impl.authenticate(biometricPrompt, biometricCancellationSignal, promptExecutor, biometricCallback);
            } catch (NullPointerException e) {
                Log.e("BiometricFragment", "Got NPE while authenticating with biometric prompt.", e);
                if (context != null) {
                    str = context.getString(R$string.default_error_msg);
                } else {
                    str = "";
                }
                sendErrorAndDismiss(1, str);
            }
        } else {
            Api28Impl.authenticate(biometricPrompt, wrapForBiometricPrompt, biometricCancellationSignal, promptExecutor, biometricCallback);
        }
    }

    public void authenticateWithFingerprint(FingerprintManagerCompat fingerprintManagerCompat, Context context) {
        try {
            fingerprintManagerCompat.authenticate(CryptoObjectUtils.wrapForFingerprintManager(this.mViewModel.getCryptoObject()), 0, this.mViewModel.getCancellationSignalProvider().getFingerprintCancellationSignal(), this.mViewModel.getAuthenticationCallbackProvider().getFingerprintCallback(), (Handler) null);
        } catch (NullPointerException e) {
            Log.e("BiometricFragment", "Got NPE while authenticating with fingerprint.", e);
            sendErrorAndDismiss(1, ErrorUtils.getFingerprintErrorString(context, 1));
        }
    }

    public void cancelAuthentication(int i2) {
        if (i2 == 3 || !this.mViewModel.isIgnoringCancel()) {
            if (isUsingFingerprintDialog()) {
                this.mViewModel.setCanceledFrom(i2);
                if (i2 == 1) {
                    sendErrorToClient(10, ErrorUtils.getFingerprintErrorString(getContext(), 10));
                }
            }
            this.mViewModel.getCancellationSignalProvider().cancel();
        }
    }

    public void dismiss() {
        this.mViewModel.setPromptShowing(false);
        dismissFingerprintDialog();
        if (!this.mViewModel.isConfirmingDeviceCredential() && isAdded()) {
            getParentFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        }
        Context context = getContext();
        if (context != null && DeviceUtils.shouldDelayShowingPrompt(context, Build.MODEL)) {
            this.mViewModel.setDelayingPrompt(true);
            this.mHandler.postDelayed(new StopDelayingPromptRunnable(this.mViewModel), 600);
        }
    }

    public boolean isManagingDeviceCredentialButton() {
        return false;
    }

    public void onActivityResult(int i2, int i7, Intent intent) {
        super.onActivityResult(i2, i7, intent);
        if (i2 == 1) {
            this.mViewModel.setConfirmingDeviceCredential(false);
            handleConfirmCredentialResult(i7);
        }
    }

    public void onAuthenticationError(final int i2, final CharSequence charSequence) {
        if (!ErrorUtils.isKnownError(i2)) {
            i2 = 8;
        }
        getContext();
        if (isUsingFingerprintDialog()) {
            if (charSequence == null) {
                charSequence = ErrorUtils.getFingerprintErrorString(getContext(), i2);
            }
            if (i2 == 5) {
                int canceledFrom = this.mViewModel.getCanceledFrom();
                if (canceledFrom == 0 || canceledFrom == 3) {
                    sendErrorToClient(i2, charSequence);
                }
                dismiss();
                return;
            }
            if (this.mViewModel.isFingerprintDialogDismissedInstantly()) {
                sendErrorAndDismiss(i2, charSequence);
            } else {
                showFingerprintErrorMessage(charSequence);
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        BiometricFragment.this.sendErrorAndDismiss(i2, charSequence);
                    }
                }, (long) getDismissDialogDelay());
            }
            this.mViewModel.setFingerprintDialogDismissedInstantly(true);
            return;
        }
        if (charSequence == null) {
            charSequence = getString(R$string.default_error_msg) + " " + i2;
        }
        sendErrorAndDismiss(i2, charSequence);
    }

    public void onAuthenticationFailed() {
        if (isUsingFingerprintDialog()) {
            showFingerprintErrorMessage(getString(R$string.fingerprint_not_recognized));
        }
        sendFailureToClient();
    }

    public void onAuthenticationHelp(CharSequence charSequence) {
        if (isUsingFingerprintDialog()) {
            showFingerprintErrorMessage(charSequence);
        }
    }

    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
        sendSuccessAndDismiss(authenticationResult);
    }

    public void onCancelButtonPressed() {
        CharSequence negativeButtonText = this.mViewModel.getNegativeButtonText();
        if (negativeButtonText == null) {
            negativeButtonText = getString(R$string.default_error_msg);
        }
        sendErrorAndDismiss(13, negativeButtonText);
        cancelAuthentication(2);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        connectViewModel();
    }

    public void onDeviceCredentialButtonPressed() {
        launchConfirmCredentialActivity();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void sendErrorAndDismiss(int i2, CharSequence charSequence) {
        sendErrorToClient(i2, charSequence);
        dismiss();
    }

    public void showPromptForAuthentication() {
        if (this.mViewModel.isPromptShowing()) {
            return;
        }
        if (getContext() == null) {
            Log.w("BiometricFragment", "Not showing biometric prompt. Context is null.");
            return;
        }
        this.mViewModel.setPromptShowing(true);
        this.mViewModel.setAwaitingResult(true);
        if (isUsingFingerprintDialog()) {
            showFingerprintDialogForAuthentication();
        } else {
            showBiometricPromptForAuthentication();
        }
    }
}
