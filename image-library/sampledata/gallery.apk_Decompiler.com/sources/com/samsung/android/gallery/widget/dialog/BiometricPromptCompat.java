package com.samsung.android.gallery.widget.dialog;

import A.a;
import A4.S;
import D9.e;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BiometricPromptCompat {
    final BiometricPrompt.AuthenticationCallback mAuthCallback = new BiometricPrompt.AuthenticationCallback() {
        public void onAuthenticationError(int i2, CharSequence charSequence) {
            int i7;
            super.onAuthenticationError(i2, charSequence);
            if (i2 == 11 || i2 == 14) {
                i7 = 2;
            } else {
                i7 = 1;
            }
            Log.w((CharSequence) "BiometricPromptCompat", "onAuthenticationError", Integer.valueOf(i2), Integer.valueOf(i7), charSequence);
            Consumer<Integer> consumer = BiometricPromptCompat.this.mCallback;
            if (consumer != null) {
                consumer.accept(Integer.valueOf(i7));
            }
        }

        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Log.w("BiometricPromptCompat", "onAuthenticationFailed");
            Consumer<Integer> consumer = BiometricPromptCompat.this.mCallback;
            if (consumer != null) {
                consumer.accept(1);
            }
        }

        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
            super.onAuthenticationSucceeded(authenticationResult);
            Log.d("BiometricPromptCompat", "onAuthenticationSucceeded");
            Consumer<Integer> consumer = BiometricPromptCompat.this.mCallback;
            if (consumer != null) {
                consumer.accept(0);
            }
        }
    };
    private int mAuthLevel = 33023;
    BiometricPrompt mBiometricPrompt;
    Consumer<Integer> mCallback;
    Dialog mCustomDialog;
    String mPreferenceKey;
    BiometricPrompt.PromptInfo mPromptInfo;
    private String mTitle;
    private int mTitleResId;

    private String getTitle(Context context) {
        int i2 = this.mTitleResId;
        if (i2 > 0) {
            return context.getString(i2);
        }
        return this.mTitle;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupScreenLock$2(Activity activity, DialogInterface dialogInterface, int i2) {
        try {
            Intent intent = new Intent("android.settings.SECURITY_SETTINGS");
            intent.setFlags(268435456);
            activity.startActivity(intent, ActivityOptions.makeBasic().setLaunchDisplayId(0).toBundle());
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("setupScreenLock failed. e="), "BiometricPromptCompat");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$showCustomPinPrompt$0(Consumer consumer, Boolean bool) {
        if (consumer != null) {
            consumer.accept(Integer.valueOf(bool.booleanValue() ^ true ? 1 : 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showCustomPinPrompt$1(Context context, String str, Consumer consumer, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            GalleryPreference.getInstance().saveState(this.mPreferenceKey, str2);
            showCustomPinPrompt(context, str, consumer);
        }
    }

    public static void setupScreenLock(Activity activity) {
        if (activity != null) {
            ThreadUtil.runOnUiThread(new g8.a(activity, 1));
        }
    }

    public void authenticate(FragmentActivity fragmentActivity) {
        if (this.mBiometricPrompt == null) {
            this.mBiometricPrompt = new BiometricPrompt(fragmentActivity, this.mAuthCallback);
            this.mPromptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle(getTitle(fragmentActivity)).setDescription((CharSequence) null).setAllowedAuthenticators(this.mAuthLevel).build();
        }
        this.mBiometricPrompt.authenticate(this.mPromptInfo);
    }

    public void authenticateCustom(FragmentActivity fragmentActivity) {
        authenticateCustom(fragmentActivity, "LockAlbumAuth");
    }

    public void dismiss() {
        Dialog dialog = this.mCustomDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mCustomDialog = null;
        }
        if (this.mBiometricPrompt != null) {
            this.mBiometricPrompt = null;
        }
    }

    public void release() {
        dismiss();
        this.mCallback = null;
    }

    public BiometricPromptCompat setCallback(Consumer<Integer> consumer) {
        this.mCallback = consumer;
        return this;
    }

    public BiometricPromptCompat setTitle(int i2) {
        this.mTitleResId = i2;
        return this;
    }

    public Dialog showCustomPinPrompt(Context context, String str, Consumer<Integer> consumer) {
        String loadString = GalleryPreference.getInstance().loadString(this.mPreferenceKey, (String) null);
        if (loadString != null) {
            return new CustomPinPrompt().setTitle(str).setAuthKey(loadString).authenticate(context, new e(2, consumer));
        }
        Context context2 = context;
        return new CustomPinPrompt().setTitle((String) null).createNew(context2, new S(this, context2, str, consumer, 18));
    }

    public void authenticateCustom(FragmentActivity fragmentActivity, String str) {
        String loadString = GalleryPreference.getInstance().loadString(str, (String) null);
        if (SdkConfig.lessThan(SdkConfig.GED.R) || !TextUtils.isEmpty(loadString)) {
            this.mPreferenceKey = str;
            this.mCustomDialog = showCustomPinPrompt(fragmentActivity, getTitle(fragmentActivity), this.mCallback);
            return;
        }
        authenticate(fragmentActivity);
    }
}
