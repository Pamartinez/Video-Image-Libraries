package androidx.biometric;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FingerprintDialogFragment extends DialogFragment {
    private int mErrorTextColor;
    private ImageView mFingerprintIcon;
    final Handler mHandler = new Handler(Looper.getMainLooper());
    TextView mHelpMessageView;
    private int mNormalTextColor;
    final Runnable mResetDialogRunnable = new Runnable() {
        public void run() {
            FingerprintDialogFragment.this.resetDialog();
        }
    };
    BiometricViewModel mViewModel;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static void startAnimation(Drawable drawable) {
            if (drawable instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) drawable).start();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        public static int getColorErrorAttr() {
            return R$attr.colorError;
        }
    }

    private void connectViewModel() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            BiometricViewModel biometricViewModel = (BiometricViewModel) new ViewModelProvider(activity).get(BiometricViewModel.class);
            this.mViewModel = biometricViewModel;
            biometricViewModel.getFingerprintDialogState().observe(this, new Observer<Integer>() {
                public void onChanged(Integer num) {
                    FingerprintDialogFragment fingerprintDialogFragment = FingerprintDialogFragment.this;
                    fingerprintDialogFragment.mHandler.removeCallbacks(fingerprintDialogFragment.mResetDialogRunnable);
                    FingerprintDialogFragment.this.updateFingerprintIcon(num.intValue());
                    FingerprintDialogFragment.this.updateHelpMessageColor(num.intValue());
                    FingerprintDialogFragment fingerprintDialogFragment2 = FingerprintDialogFragment.this;
                    fingerprintDialogFragment2.mHandler.postDelayed(fingerprintDialogFragment2.mResetDialogRunnable, 2000);
                }
            });
            this.mViewModel.getFingerprintDialogHelpMessage().observe(this, new Observer<CharSequence>() {
                public void onChanged(CharSequence charSequence) {
                    FingerprintDialogFragment fingerprintDialogFragment = FingerprintDialogFragment.this;
                    fingerprintDialogFragment.mHandler.removeCallbacks(fingerprintDialogFragment.mResetDialogRunnable);
                    FingerprintDialogFragment.this.updateHelpMessageText(charSequence);
                    FingerprintDialogFragment fingerprintDialogFragment2 = FingerprintDialogFragment.this;
                    fingerprintDialogFragment2.mHandler.postDelayed(fingerprintDialogFragment2.mResetDialogRunnable, 2000);
                }
            });
        }
    }

    private Drawable getAssetForTransition(int i2, int i7) {
        int i8;
        Context context = getContext();
        if (context == null) {
            Log.w("FingerprintFragment", "Unable to get asset. Context is null.");
            return null;
        }
        if (i2 == 0 && i7 == 1) {
            i8 = R$drawable.fingerprint_dialog_fp_icon;
        } else if (i2 == 1 && i7 == 2) {
            i8 = R$drawable.fingerprint_dialog_error;
        } else if (i2 == 2 && i7 == 1) {
            i8 = R$drawable.fingerprint_dialog_fp_icon;
        } else if (i2 != 1 || i7 != 3) {
            return null;
        } else {
            i8 = R$drawable.fingerprint_dialog_fp_icon;
        }
        return ContextCompat.getDrawable(context, i8);
    }

    private int getThemedColorFor(int i2) {
        Context context = getContext();
        FragmentActivity activity = getActivity();
        if (context == null || activity == null) {
            Log.w("FingerprintFragment", "Unable to get themed color. Context or activity is null.");
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(typedValue.data, new int[]{i2});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    public static FingerprintDialogFragment newInstance() {
        return new FingerprintDialogFragment();
    }

    private boolean shouldAnimateForTransition(int i2, int i7) {
        if (i2 == 0 && i7 == 1) {
            return false;
        }
        if (i2 == 1 && i7 == 2) {
            return true;
        }
        if (i2 == 2 && i7 == 1) {
            return true;
        }
        return false;
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        this.mViewModel.setFingerprintDialogCancelPending(true);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        connectViewModel();
        this.mErrorTextColor = getThemedColorFor(Api26Impl.getColorErrorAttr());
        this.mNormalTextColor = getThemedColorFor(16842808);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        CharSequence charSequence;
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(this.mViewModel.getTitle());
        View inflate = LayoutInflater.from(builder.getContext()).inflate(R$layout.fingerprint_dialog_layout, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R$id.fingerprint_subtitle);
        if (textView != null) {
            CharSequence subtitle = this.mViewModel.getSubtitle();
            if (TextUtils.isEmpty(subtitle)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(subtitle);
            }
        }
        TextView textView2 = (TextView) inflate.findViewById(R$id.fingerprint_description);
        if (textView2 != null) {
            CharSequence description = this.mViewModel.getDescription();
            if (TextUtils.isEmpty(description)) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
                textView2.setText(description);
            }
        }
        this.mFingerprintIcon = (ImageView) inflate.findViewById(R$id.fingerprint_icon);
        this.mHelpMessageView = (TextView) inflate.findViewById(R$id.fingerprint_error);
        if (AuthenticatorUtils.isDeviceCredentialAllowed(this.mViewModel.getAllowedAuthenticators())) {
            charSequence = getString(R$string.confirm_device_credential_password);
        } else {
            charSequence = this.mViewModel.getNegativeButtonText();
        }
        builder.setNegativeButton(charSequence, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                FingerprintDialogFragment.this.mViewModel.setNegativeButtonPressPending(true);
            }
        });
        builder.setView(inflate);
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    public void onResume() {
        super.onResume();
        this.mViewModel.setFingerprintDialogPreviousState(0);
        this.mViewModel.setFingerprintDialogState(1);
        this.mViewModel.setFingerprintDialogHelpMessage(getString(R$string.fingerprint_dialog_touch_sensor));
    }

    public void resetDialog() {
        Context context = getContext();
        if (context == null) {
            Log.w("FingerprintFragment", "Not resetting the dialog. Context is null.");
            return;
        }
        this.mViewModel.setFingerprintDialogState(1);
        this.mViewModel.setFingerprintDialogHelpMessage(context.getString(R$string.fingerprint_dialog_touch_sensor));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r3.mViewModel.getFingerprintDialogPreviousState();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateFingerprintIcon(int r4) {
        /*
            r3 = this;
            android.widget.ImageView r0 = r3.mFingerprintIcon
            if (r0 != 0) goto L_0x0005
            goto L_0x0011
        L_0x0005:
            androidx.biometric.BiometricViewModel r0 = r3.mViewModel
            int r0 = r0.getFingerprintDialogPreviousState()
            android.graphics.drawable.Drawable r1 = r3.getAssetForTransition(r0, r4)
            if (r1 != 0) goto L_0x0012
        L_0x0011:
            return
        L_0x0012:
            android.widget.ImageView r2 = r3.mFingerprintIcon
            r2.setImageDrawable(r1)
            boolean r0 = r3.shouldAnimateForTransition(r0, r4)
            if (r0 == 0) goto L_0x0020
            androidx.biometric.FingerprintDialogFragment.Api21Impl.startAnimation(r1)
        L_0x0020:
            androidx.biometric.BiometricViewModel r3 = r3.mViewModel
            r3.setFingerprintDialogPreviousState(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.biometric.FingerprintDialogFragment.updateFingerprintIcon(int):void");
    }

    public void updateHelpMessageColor(int i2) {
        boolean z;
        int i7;
        TextView textView = this.mHelpMessageView;
        if (textView != null) {
            if (i2 == 2) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                i7 = this.mErrorTextColor;
            } else {
                i7 = this.mNormalTextColor;
            }
            textView.setTextColor(i7);
        }
    }

    public void updateHelpMessageText(CharSequence charSequence) {
        TextView textView = this.mHelpMessageView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
