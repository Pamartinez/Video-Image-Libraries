package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Locale;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DuplicateHeader {
    private final Context mContext;
    TextView mCountView;
    TextView mDeleteView;
    LinearLayout mDeleteViewLayout;
    /* access modifiers changed from: private */
    public int mFromCount = 0;
    /* access modifiers changed from: private */
    public int mIconAnimCount;
    ImageView mIconView;
    private final Interpolator mInterpolator;
    /* access modifiers changed from: private */
    public boolean mIsHeaderUpdated = false;
    LinearLayout mLayout;
    /* access modifiers changed from: private */
    public int mRepeat;
    protected ViewGroup mRootView;
    TextView mSizeView;
    /* access modifiers changed from: private */
    public int mToCount = 0;
    protected final View mView;

    public DuplicateHeader(Context context, ViewGroup viewGroup, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.mRootView = viewGroup;
        View createHeaderView = createHeaderView();
        this.mView = createHeaderView;
        this.mInterpolator = PathInterpolator.create(0.66f, 0.0f, 0.34f, 1.0f);
        setListener(onClickListener);
        startIconAnimation();
        setContentDescription();
        this.mLayout = (LinearLayout) createHeaderView.findViewById(R.id.duplicate_header_sub_layout);
        this.mCountView = (TextView) createHeaderView.findViewById(R.id.duplicate_count);
        this.mSizeView = (TextView) createHeaderView.findViewById(R.id.duplicate_size);
    }

    private View createHeaderView() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.duplicate_header_layout, this.mRootView, false);
        this.mIconView = (ImageView) inflate.findViewById(R.id.duplicate_icon);
        this.mDeleteViewLayout = (LinearLayout) inflate.findViewById(R.id.delete_duplicate_button_layout);
        this.mDeleteView = (TextView) inflate.findViewById(R.id.delete_duplicate_button);
        return inflate;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startIconAnimation$0(ImageView imageView) {
        final AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) imageView.getDrawable();
        animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
            public void onAnimationEnd(Drawable drawable) {
                DuplicateHeader duplicateHeader = DuplicateHeader.this;
                duplicateHeader.mIconAnimCount = duplicateHeader.mIconAnimCount + 1;
                if (DuplicateHeader.this.mIconAnimCount < 2 && !DuplicateHeader.this.mIsHeaderUpdated) {
                    animatedVectorDrawable.start();
                }
            }
        });
        animatedVectorDrawable.start();
    }

    private void setContentDescription() {
        TextView textView = this.mDeleteView;
        textView.setContentDescription(this.mDeleteView.getText() + ArcCommonLog.TAG_COMMA + this.mContext.getString(R.string.speak_button));
    }

    private void setListener(View.OnClickListener onClickListener) {
        this.mDeleteViewLayout.setOnClickListener(onClickListener);
    }

    private void startCountAnimation() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mCountView, "translationY", new float[]{0.0f, -((float) (this.mCountView.getLineHeight() * this.mRepeat))});
        ofFloat.setInterpolator(this.mInterpolator);
        ofFloat.setDuration(1000);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ViewUtils.setMaxLines(DuplicateHeader.this.mCountView, 1);
                DuplicateHeader duplicateHeader = DuplicateHeader.this;
                ViewUtils.setText(duplicateHeader.mCountView, String.valueOf(duplicateHeader.mToCount));
                ViewUtils.setTranslationY(DuplicateHeader.this.mCountView, 0.0f);
                DuplicateHeader duplicateHeader2 = DuplicateHeader.this;
                duplicateHeader2.mFromCount = duplicateHeader2.mToCount;
            }

            public void onAnimationStart(Animator animator) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(DuplicateHeader.this.mFromCount);
                for (int e = DuplicateHeader.this.mRepeat - 1; e >= 0; e--) {
                    sb2.append("\n");
                    sb2.append(DuplicateHeader.this.mToCount + e);
                }
                ViewUtils.setMaxLines(DuplicateHeader.this.mCountView, 5);
                ViewUtils.setText(DuplicateHeader.this.mCountView, (CharSequence) sb2);
            }
        });
        ofFloat.start();
    }

    private void startIconAnimation() {
        this.mIconAnimCount = 0;
        Optional.ofNullable(this.mIconView).ifPresent(new d(this));
    }

    public View getView() {
        return this.mView;
    }

    public void updateHeader(boolean z) {
        float f;
        int i2;
        int i7;
        this.mIsHeaderUpdated = true;
        LinearLayout linearLayout = this.mLayout;
        if (z) {
            f = 0.4f;
        } else {
            f = 1.0f;
        }
        linearLayout.setAlpha(f);
        TextView textView = this.mDeleteView;
        Context context = this.mContext;
        if (z) {
            i2 = R.color.delete_duplicate_button_text_color_disabled;
        } else {
            i2 = R.color.delete_duplicate_button_text_color;
        }
        textView.setTextColor(context.getColor(i2));
        TextView textView2 = this.mDeleteView;
        Context context2 = this.mContext;
        if (z) {
            i7 = R.drawable.delete_duplicate_button_bg_disabled;
        } else {
            i7 = R.drawable.delete_duplicate_button_bg;
        }
        textView2.setBackground(context2.getDrawable(i7));
        this.mDeleteViewLayout.setClickable(!z);
    }

    public void updateLayout() {
        ViewMarginUtils.setTopMargin(this.mLayout, this.mContext.getResources().getDimensionPixelSize(R.dimen.duplicate_header_sub_layout_margin_top));
        ViewMarginUtils.setMarginRelative(this.mDeleteViewLayout, (Integer) null, Integer.valueOf(this.mContext.getResources().getDimensionPixelSize(R.dimen.delete_duplicate_button_layout_margin_top)), (Integer) null, Integer.valueOf(this.mContext.getResources().getDimensionPixelSize(R.dimen.delete_duplicate_button_layout_margin_bottom)));
    }

    public void updateText(int i2, long j2) {
        this.mSizeView.setText(StringCompat.toReadableSize((double) j2));
        int i7 = this.mFromCount;
        if (i7 == 0 || i2 >= i7) {
            this.mFromCount = i2;
            this.mCountView.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)}));
            return;
        }
        this.mToCount = i2;
        int i8 = i7 - i2;
        this.mRepeat = i8;
        if (i8 > 3) {
            this.mRepeat = 3;
        }
        startCountAnimation();
    }
}
