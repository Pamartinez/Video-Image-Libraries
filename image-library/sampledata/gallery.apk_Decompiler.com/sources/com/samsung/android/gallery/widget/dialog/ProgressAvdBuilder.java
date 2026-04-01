package com.samsung.android.gallery.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import tb.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ProgressAvdBuilder {
    private int mAvdResId = -1;
    protected ImageView mAvdView;
    protected final Context mContext;
    private CharSequence mMessage;
    protected TextView mMessageView;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private DialogInterface.OnKeyListener mOnKeyListener;

    public ProgressAvdBuilder(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$create$0(Window window, DialogInterface dialogInterface) {
        if (window != null) {
            window.setDimAmount(0.57f);
        }
    }

    public void bindView(View view) {
        TextView textView = (TextView) view.findViewById(R$id.message_text);
        this.mMessageView = textView;
        if (this.mMessage != null) {
            textView.setVisibility(0);
            this.mMessageView.setText(this.mMessage);
            this.mMessageView.setGravity(1);
        }
        ImageView imageView = (ImageView) view.findViewById(R$id.progress_avd);
        this.mAvdView = imageView;
        if (imageView != null && this.mAvdResId != -1) {
            imageView.setImageDrawable(imageView.getContext().getDrawable(this.mAvdResId));
        }
    }

    public AlertDialog create() {
        return create(this.mContext);
    }

    public ProgressAvdBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mOnCancelListener = onCancelListener;
        return this;
    }

    public ProgressAvdBuilder setProgressMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        return this;
    }

    public AlertDialog create(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R$layout.progress_avd_dialog, (ViewGroup) null);
        AlertDialog create = new AlertDialog.Builder(context).setView(inflate).create();
        create.setCanceledOnTouchOutside(false);
        if (this.mOnCancelListener != null) {
            create.setCancelable(true);
            create.setOnCancelListener(this.mOnCancelListener);
        } else {
            create.setCancelable(false);
        }
        DialogInterface.OnKeyListener onKeyListener = this.mOnKeyListener;
        if (onKeyListener != null) {
            create.setOnKeyListener(onKeyListener);
        }
        Window window = create.getWindow();
        if (window != null) {
            window.setGravity(17);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        create.setOnShowListener(new j(window));
        bindView(inflate);
        return create;
    }
}
