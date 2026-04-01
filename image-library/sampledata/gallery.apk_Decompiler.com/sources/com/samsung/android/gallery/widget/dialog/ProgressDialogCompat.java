package com.samsung.android.gallery.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProgressDialogCompat extends ProgressCircleBuilder {
    private Boolean mCancelable;
    private Boolean mCanceledOnTouchOutside;
    private AlertDialog mDialog;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private DialogInterface.OnKeyListener mOnKeyListener;

    public ProgressDialogCompat(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMessage$0(CharSequence charSequence) {
        TextView textView = this.mMessageView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public ProgressDialogCompat build() {
        AlertDialog create = super.create();
        this.mDialog = create;
        if (create != null) {
            DialogInterface.OnCancelListener onCancelListener = this.mOnCancelListener;
            if (onCancelListener != null) {
                create.setOnCancelListener(onCancelListener);
            }
            DialogInterface.OnKeyListener onKeyListener = this.mOnKeyListener;
            if (onKeyListener != null) {
                this.mDialog.setOnKeyListener(onKeyListener);
            }
            Boolean bool = this.mCancelable;
            if (bool != null) {
                this.mDialog.setCancelable(bool.booleanValue());
            }
            Boolean bool2 = this.mCanceledOnTouchOutside;
            if (bool2 != null) {
                this.mDialog.setCanceledOnTouchOutside(bool2.booleanValue());
            }
        }
        return this;
    }

    public void dismiss() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDialog = null;
        }
    }

    public TextView getMessageView() {
        return this.mMessageView;
    }

    public boolean isShowing() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return false;
        }
        return true;
    }

    public ProgressDialogCompat setCancelable(boolean z) {
        this.mCancelable = Boolean.valueOf(z);
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.setCancelable(z);
        }
        return this;
    }

    public ProgressDialogCompat setCanceledOnTouchOutside(boolean z) {
        this.mCanceledOnTouchOutside = Boolean.valueOf(z);
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.setCanceledOnTouchOutside(z);
        }
        return this;
    }

    public ProgressDialogCompat setProgressMessage(int i2) {
        super.setProgressMessage(this.mContext.getString(i2));
        return this;
    }

    public ProgressDialogCompat show() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.show();
        }
        return this;
    }

    public void updateMessage(CharSequence charSequence) {
        ViewUtils.post(this.mMessageView, new e(11, this, charSequence));
    }

    public ProgressDialogCompat setLightTheme(boolean z) {
        this.mIsLightTheme = z;
        return this;
    }
}
