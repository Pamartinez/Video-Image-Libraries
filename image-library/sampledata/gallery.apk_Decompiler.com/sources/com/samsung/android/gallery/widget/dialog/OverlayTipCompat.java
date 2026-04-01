package com.samsung.android.gallery.widget.dialog;

import A.a;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OverlayTipCompat extends OverlayTipBuilder {
    protected final String TAG = getClass().getSimpleName();
    private AlertDialog mDialog;

    public OverlayTipCompat(Context context, int i2) {
        super(context, i2);
    }

    public OverlayTipCompat build() {
        this.mDialog = super.create();
        return this;
    }

    public void dismiss() {
        try {
            if (this.mDialog != null) {
                Log.d(this.TAG, "Dismiss overlay tip");
                this.mDialog.dismiss();
                this.mDialog = null;
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to dismiss dialog e="), this.TAG);
        }
    }

    public void refresh() {
        if (this.mDialog != null) {
            Log.d(this.TAG, "Refresh overlay tip");
            this.mDialog.setOnDismissListener((DialogInterface.OnDismissListener) null);
            dismiss();
            build();
            show();
        }
    }

    public OverlayTipCompat setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        super.setOnDismissListener(onDismissListener);
        return this;
    }

    public OverlayTipCompat show() {
        if (this.mDialog != null) {
            Log.d(this.TAG, "Show overlay tip");
            this.mDialog.show();
        }
        return this;
    }
}
