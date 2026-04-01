package com.samsung.android.gallery.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SeslProgressBar;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$style;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import o4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProgressCircleBuilder {
    public static final int AVD_4STAR_DRAWABLE = R$drawable.avd_gallery_remaster_loader;
    protected final Context mContext;
    private int mGravity = 17;
    private boolean mIsCancelable = false;
    private boolean mIsFlexMode;
    protected boolean mIsLightTheme;
    private CharSequence mMessage;
    protected TextView mMessageView;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private boolean mRemoveCircle;
    private boolean mRemoveDialogAnim;
    private boolean mRemoveDimBehind;
    /* access modifiers changed from: private */
    public boolean mRestartAvd;
    private int mTextColorId;
    private boolean mUse4Star;

    public ProgressCircleBuilder(Context context) {
        this.mContext = context;
    }

    private void apply4Star(View view, LinearLayout linearLayout) {
        final AnonymousClass1 r0 = new ImageView(view.getContext()) {
            public void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) getDrawable();
                if (ProgressCircleBuilder.this.mRestartAvd) {
                    animatedVectorDrawable.reset();
                    animatedVectorDrawable.start();
                    ProgressCircleBuilder.this.mRestartAvd = false;
                }
            }
        };
        r0.setImageResource(AVD_4STAR_DRAWABLE);
        final AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) r0.getDrawable();
        animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
            public void onAnimationEnd(Drawable drawable) {
                if (r0.hasWindowFocus()) {
                    animatedVectorDrawable.start();
                } else {
                    ProgressCircleBuilder.this.mRestartAvd = true;
                }
            }
        });
        r0.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                animatedVectorDrawable.start();
            }

            public void onViewDetachedFromWindow(View view) {
            }
        });
        ViewUtils.replaceView(linearLayout.findViewById(R$id.progress_circle), r0);
    }

    private void inflateProgressBar(View view) {
        int i2;
        View findViewById = view.findViewById(R$id.progress_circle);
        if (ViewUtils.isViewStub(findViewById)) {
            if (this.mIsLightTheme) {
                i2 = R$layout.theme_sesl_progress_bar;
            } else {
                i2 = R$layout.basic_sesl_progress_bar;
            }
            ViewStub viewStub = (ViewStub) findViewById;
            viewStub.setLayoutResource(i2);
            viewStub.inflate();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$create$0(TextView textView) {
        textView.setTextColor(textView.getContext().getColor(this.mTextColorId));
    }

    private void updateProgressBarLayout(Context context, View view) {
        int dpToPixel = ResourceCompat.dpToPixel(context, 5.0f);
        SeslProgressBar seslProgressBar = (SeslProgressBar) view.findViewById(R$id.progress_circle);
        seslProgressBar.setBackground(context.getDrawable(R$drawable.progress_circle_bg));
        seslProgressBar.setElevation((float) dpToPixel);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) seslProgressBar.getLayoutParams();
        int i2 = dpToPixel * 2;
        layoutParams.leftMargin = i2;
        layoutParams.topMargin = i2;
        layoutParams.rightMargin = i2;
        layoutParams.bottomMargin = i2;
        seslProgressBar.setLayoutParams(layoutParams);
    }

    private void updateProgressCircleLayout(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.progress_circle_layout);
        if (linearLayout != null) {
            if (this.mIsFlexMode) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.bottomMargin = view.getContext().getResources().getDimensionPixelSize(R$dimen.processing_progress_layout_margin_bottom);
                linearLayout.setLayoutParams(layoutParams);
            }
            if (this.mRemoveCircle) {
                linearLayout.setVisibility(8);
            }
            if (this.mUse4Star) {
                apply4Star(view, linearLayout);
            }
        }
    }

    public void bindView(AlertDialog alertDialog, View view) {
        updateProgressCircleLayout(view);
        TextView textView = (TextView) view.findViewById(R$id.message_text);
        this.mMessageView = textView;
        if (this.mMessage != null) {
            textView.setVisibility(0);
            this.mMessageView.setText(this.mMessage);
            this.mMessageView.setGravity(1);
        }
    }

    public AlertDialog create() {
        return create(this.mContext, R$layout.progress_circle_dialog);
    }

    public ProgressCircleBuilder removeCircle() {
        this.mRemoveCircle = true;
        return this;
    }

    public ProgressCircleBuilder removeDialogAnim() {
        this.mRemoveDialogAnim = true;
        return this;
    }

    public ProgressCircleBuilder removeDimBehind() {
        this.mRemoveDimBehind = true;
        return this;
    }

    public ProgressCircleBuilder setCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mOnCancelListener = onCancelListener;
        return this;
    }

    public ProgressCircleBuilder setFlexMode(boolean z) {
        this.mIsFlexMode = z;
        return this;
    }

    public ProgressCircleBuilder setLightTheme(boolean z) {
        this.mIsLightTheme = z;
        return this;
    }

    public ProgressCircleBuilder setProgressMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        return this;
    }

    public ProgressCircleBuilder setTextColor(int i2) {
        this.mTextColorId = i2;
        return this;
    }

    public AlertDialog create(Context context, int i2) {
        View inflate = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        if (this.mTextColorId != 0) {
            Optional.ofNullable((TextView) inflate.findViewById(R$id.message_text)).ifPresent(new a(16, this));
        }
        AlertDialog create = new AlertDialog.Builder(context).setView(inflate).create();
        create.setCanceledOnTouchOutside(this.mIsCancelable);
        if (this.mOnCancelListener != null) {
            create.setCancelable(true);
            create.setOnCancelListener(this.mOnCancelListener);
        } else {
            create.setCancelable(false);
        }
        Window window = create.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(this.mGravity);
            if (this.mRemoveDimBehind) {
                window.clearFlags(2);
            }
            if (this.mRemoveDialogAnim) {
                window.setWindowAnimations(R$style.DialogNoAnimation);
            }
        }
        inflateProgressBar(inflate);
        if (this.mRemoveDimBehind) {
            updateProgressBarLayout(context, inflate);
        }
        bindView(create, inflate);
        return create;
    }
}
