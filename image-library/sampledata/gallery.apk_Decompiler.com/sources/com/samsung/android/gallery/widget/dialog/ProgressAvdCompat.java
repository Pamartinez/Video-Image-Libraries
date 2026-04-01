package com.samsung.android.gallery.widget.dialog;

import A.a;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import n5.f;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProgressAvdCompat extends ProgressAvdBuilder {
    protected final String TAG = getClass().getSimpleName();
    private AlertDialog mDialog;

    public ProgressAvdCompat(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideProgressBar$1() {
        ViewUtils.setVisibility(this.mAvdView, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMessage$0(int i2) {
        Context context;
        TextView textView = this.mMessageView;
        if (textView != null && i2 > 0 && (context = this.mContext) != null) {
            textView.setText(context.getString(i2));
        }
    }

    public ProgressAvdCompat build() {
        this.mDialog = super.create();
        return this;
    }

    public void dismiss() {
        try {
            if (this.mDialog != null) {
                ImageView imageView = this.mAvdView;
                if (imageView != null) {
                    AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) imageView.getDrawable();
                    animatedVectorDrawable.stop();
                    animatedVectorDrawable.clearAnimationCallbacks();
                }
                this.mDialog.dismiss();
                this.mDialog = null;
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to dismiss dialog e="), this.TAG);
        }
    }

    public void hideProgressBar() {
        ViewUtils.post(this.mAvdView, new e(3, this));
    }

    public boolean isShowing() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return false;
        }
        return true;
    }

    public ProgressAvdCompat setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        super.setOnCancelListener(onCancelListener);
        return this;
    }

    public ProgressAvdCompat setProgressMessage(int i2) {
        if (i2 > 0) {
            super.setProgressMessage(this.mContext.getString(i2));
        }
        return this;
    }

    public ProgressAvdCompat show() {
        if (this.mDialog != null) {
            ImageView imageView = this.mAvdView;
            if (imageView != null) {
                final AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) imageView.getDrawable();
                animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                    public void onAnimationEnd(Drawable drawable) {
                        animatedVectorDrawable.start();
                    }
                });
                animatedVectorDrawable.start();
            }
            this.mDialog.show();
        }
        return this;
    }

    public void updateMessage(int i2) {
        ViewUtils.post(this.mMessageView, new f(this, i2, 3));
    }
}
