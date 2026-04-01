package com.samsung.android.gallery.app.ui.dialog.abstraction;

import N2.j;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.sec.android.gallery3d.R;
import r4.a;
import r4.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EditBaseDialog extends BaseDialog implements DialogInterface.OnKeyListener {
    private boolean mClipVisible;
    protected InputMethodManager mInputMethodManager;
    private long mReceiveTime = 0;
    private IntentFilter mSipIntentFilter;
    private BroadcastReceiver mSipReceiver;
    private boolean mSipVisible;

    private int convertPixelsToDp(float f) {
        return (int) (f / (((float) getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    private int getDialogWidth() {
        float f;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return DeviceInfo.getDefaultDisplay().getWidth();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int convertPixelsToDp = convertPixelsToDp((float) displayMetrics.widthPixels);
        if (convertPixelsToDp < 480) {
            f = 1.0f;
        } else if (convertPixelsToDp < 600) {
            f = 0.6836f;
        } else if (convertPixelsToDp < 960) {
            f = 0.6f;
        } else if (convertPixelsToDp < 1920) {
            f = 0.375f;
        } else {
            f = 0.25f;
        }
        return (int) (f * ((float) displayMetrics.widthPixels));
    }

    private void hideSoftInput(Context context, IBinder iBinder) {
        if (getEditTextView() != null) {
            try {
                setSoftInputModeWhenHide(context);
                ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 0);
            } catch (IllegalArgumentException | NullPointerException e) {
                j.u(e, new StringBuilder("hideSoftInput failed. e="), this.TAG);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initInputMethodManager$0(View view) {
        this.mInputMethodManager.showSoftInput(view, 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSipResume$1(View view) {
        this.mInputMethodManager.showSoftInput(view, 0);
        if (supportClipboardEx() && this.mClipVisible) {
            SeApiCompat.showClipboardDialog(getContext());
        }
    }

    private void onSipPause(View view) {
        Context context = view.getContext();
        if (supportClipboardEx()) {
            this.mClipVisible = SeApiCompat.isClipboardShowing(context);
        }
        if (System.currentTimeMillis() - this.mReceiveTime < 150) {
            this.mSipVisible = true;
        }
        BroadcastReceiver broadcastReceiver = this.mSipReceiver;
        if (broadcastReceiver != null) {
            AndroidCompat.unregisterReceiver(context, broadcastReceiver);
            this.mSipReceiver = null;
        }
        hideSoftInput(context, view.getWindowToken());
    }

    private void onSipResume(View view) {
        Dialog dialog = getDialog();
        if (dialog != null && dialog.isShowing()) {
            if (view instanceof EditText) {
                view.requestFocus();
                ((EditText) view).setSelectAllOnFocus(false);
            }
            setSipBroadcastReceiver();
            if (isSipVisible()) {
                view.postDelayed(new b(this, view, 1), 600);
            }
        }
    }

    private void setSipBroadcastReceiver() {
        if (getEditTextView() != null) {
            if (this.mSipIntentFilter == null) {
                IntentFilter intentFilter = new IntentFilter();
                this.mSipIntentFilter = intentFilter;
                intentFilter.addAction("ResponseAxT9Info");
            }
            if (this.mSipReceiver == null) {
                this.mSipReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        EditBaseDialog.this.onSipReceive(context, intent);
                    }
                };
            }
            AndroidCompat.registerReceiver(getContext(), this.mSipReceiver, this.mSipIntentFilter);
        }
    }

    private void updateLayout() {
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = getDialogWidth();
        attributes.height = -2;
        getDialog().getWindow().setAttributes(attributes);
    }

    public void closeDialog() {
        InputMethodManager inputMethodManager;
        View editTextView = getEditTextView();
        if (!(editTextView == null || (inputMethodManager = (InputMethodManager) editTextView.getContext().getSystemService("input_method")) == null)) {
            inputMethodManager.hideSoftInputFromWindow(editTextView.getWindowToken(), 0);
        }
        dismissAllowingStateLoss();
    }

    public Dialog createDialog(Bundle bundle) {
        String str;
        View inflate = LayoutInflater.from(getContext()).inflate(getLayoutId(), (ViewGroup) null);
        bindViews(inflate);
        initDialog();
        initInputMethodManager();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (useCustomTitle()) {
            str = "";
        } else {
            str = getTitle();
        }
        AlertDialog create = builder.setTitle((CharSequence) str).setView(inflate).setNegativeButton(getNegativeButtonResource(), getNegativeButtonClickListener()).setPositiveButton(getPositiveButtonResource(), getPositiveButtonClickListener()).create();
        create.setOnKeyListener(this);
        return create;
    }

    public View getEditTextView() {
        return null;
    }

    public abstract int getLayoutId();

    public DialogInterface.OnClickListener getNegativeButtonClickListener() {
        return null;
    }

    public int getNegativeButtonResource() {
        return R.string.cancel;
    }

    public DialogInterface.OnClickListener getPositiveButtonClickListener() {
        return null;
    }

    public int getPositiveButtonResource() {
        return R.string.ok;
    }

    public String getTitle() {
        return "";
    }

    public void initInputMethodManager() {
        View editTextView = getEditTextView();
        if (editTextView != null) {
            this.mInputMethodManager = (InputMethodManager) editTextView.getContext().getSystemService("input_method");
            if (!SeApiCompat.isAccessoryKeyboardState(editTextView.getContext().getApplicationContext()) && !isStateHidden()) {
                editTextView.postDelayed(new b(this, editTextView, 0), 300);
            }
        }
    }

    public final boolean isPositiveButtonEnabled() {
        if (getDialog() != null) {
            return ((AlertDialog) getDialog()).getButton(-1).isEnabled();
        }
        return false;
    }

    public boolean isSipVisible() {
        return this.mSipVisible;
    }

    public boolean isStateHidden() {
        return false;
    }

    public void onBackPressed() {
        closeDialog();
    }

    public void onClickNegative(View view) {
        closeDialog();
    }

    public void onClickPositive(View view) {
        Log.d(this.TAG, "onClickPositive");
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (SdkConfig.lessThan(SdkConfig.SEM.B)) {
            updateLayout();
        }
    }

    public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        if (i2 != 4 && i2 != 111) {
            return super.onKeyClicked(dialogInterface, i2, keyEvent);
        }
        onBackPressed();
        return true;
    }

    public void onPause() {
        super.onPause();
        View editTextView = getEditTextView();
        if (editTextView != null) {
            onSipPause(editTextView);
        }
    }

    public void onResume() {
        super.onResume();
        postInitDialog();
        ((AlertDialog) getDialog()).getButton(-2).setOnClickListener(new a(this, 0));
        ((AlertDialog) getDialog()).getButton(-1).setOnClickListener(new a(this, 1));
        View editTextView = getEditTextView();
        if (editTextView != null) {
            onSipResume(editTextView);
        }
    }

    public void onSipReceive(Context context, Intent intent) {
        this.mReceiveTime = System.currentTimeMillis();
        this.mSipVisible = intent.getBooleanExtra("AxT9IME.isVisibleWindow", true);
    }

    public final void setPositiveButtonEnabled(boolean z) {
        if (getDialog() != null) {
            ((AlertDialog) getDialog()).getButton(-1).setEnabled(z);
        }
    }

    public void setSoftInputModeWhenHide(Context context) {
        ((Activity) context).getWindow().setSoftInputMode(32);
    }

    public boolean useCustomTitle() {
        return false;
    }

    public void initDialog() {
    }

    public void postInitDialog() {
    }

    public void bindViews(View view) {
    }
}
