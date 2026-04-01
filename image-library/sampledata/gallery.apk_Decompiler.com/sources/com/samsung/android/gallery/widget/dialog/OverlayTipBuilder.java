package com.samsung.android.gallery.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class OverlayTipBuilder {
    protected final Context mContext;
    protected final int mLayoutId;
    private View mOKView;
    private DialogInterface.OnDismissListener mOnDismissListener;

    public OverlayTipBuilder(Context context, int i2) {
        this.mContext = context;
        this.mLayoutId = i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$bindView$1(AlertDialog alertDialog, View view) {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$create$0(Window window, DialogInterface dialogInterface) {
        if (window != null) {
            window.setDimAmount(0.75f);
        }
    }

    public void bindView(View view, AlertDialog alertDialog) {
        View findViewById = view.findViewById(R$id.tip_ok_button);
        this.mOKView = findViewById;
        ViewUtils.setOnClickListener(findViewById, new a(1, alertDialog));
    }

    public AlertDialog create() {
        return create(this.mContext);
    }

    public OverlayTipBuilder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }

    public AlertDialog create(Context context) {
        View inflate = LayoutInflater.from(context).inflate(this.mLayoutId, (ViewGroup) null);
        AlertDialog create = new AlertDialog.Builder(context).setView(inflate).create();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(true);
        DialogInterface.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            create.setOnDismissListener(onDismissListener);
        }
        Window window = create.getWindow();
        if (window != null) {
            window.setGravity(17);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        create.setOnShowListener(new c(window));
        bindView(inflate, create);
        return create;
    }
}
