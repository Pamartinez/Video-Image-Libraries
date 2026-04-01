package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsActionBarView extends ViewGroup {
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    private boolean mEatingHover;
    protected ActionMenuView mMenuView;
    protected final Context mPopupContext;
    protected final VisibilityAnimListener mVisAnimListener = new VisibilityAnimListener();
    protected ViewPropertyAnimatorCompat mVisibilityAnim;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class VisibilityAnimListener implements ViewPropertyAnimatorListener {
        private boolean mCanceled = false;
        int mFinalVisibility;

        public VisibilityAnimListener() {
        }

        public void onAnimationCancel(View view) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(View view) {
            if (!this.mCanceled) {
                AbsActionBarView absActionBarView = AbsActionBarView.this;
                absActionBarView.mVisibilityAnim = null;
                AbsActionBarView.super.setVisibility(this.mFinalVisibility);
            }
        }

        public void onAnimationStart(View view) {
            AbsActionBarView.super.setVisibility(0);
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, int i2) {
            AbsActionBarView.this.mVisibilityAnim = viewPropertyAnimatorCompat;
            this.mFinalVisibility = i2;
            return this;
        }
    }

    public AbsActionBarView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.mPopupContext = context;
        } else {
            this.mPopupContext = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    public static int next(int i2, int i7, boolean z) {
        if (z) {
            return i2 - i7;
        }
        return i2 + i7;
    }

    public int getAnimatedVisibility() {
        if (this.mVisibilityAnim != null) {
            return this.mVisAnimListener.mFinalVisibility;
        }
        return getVisibility();
    }

    public int getContentHeight() {
        return this.mContentHeight;
    }

    public int measureChildView(View view, int i2, int i7, int i8) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE), i7);
        return Math.max(0, (i2 - view.getMeasuredWidth()) - i8);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.onConfigurationChanged(configuration);
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    public int positionChild(View view, int i2, int i7, int i8, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int D = C0086a.D(i8, measuredHeight, 2, i7);
        if (z) {
            view.layout(i2 - measuredWidth, D, i2, measuredHeight + D);
        } else {
            view.layout(i2, D, i2 + measuredWidth, measuredHeight + D);
        }
        if (z) {
            return -measuredWidth;
        }
        return measuredWidth;
    }

    public abstract void setContentHeight(int i2);

    public void setVisibility(int i2) {
        if (i2 != getVisibility()) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVisibilityAnim;
            if (viewPropertyAnimatorCompat != null) {
                viewPropertyAnimatorCompat.cancel();
            }
            super.setVisibility(i2);
        }
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i2, long j2) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVisibilityAnim;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.cancel();
        }
        if (i2 == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            ViewPropertyAnimatorCompat alpha = ViewCompat.animate(this).alpha(1.0f);
            alpha.setDuration(j2);
            alpha.setListener(this.mVisAnimListener.withFinalVisibility(alpha, i2));
            return alpha;
        }
        ViewPropertyAnimatorCompat alpha2 = ViewCompat.animate(this).alpha(0.0f);
        alpha2.setDuration(j2);
        alpha2.setListener(this.mVisAnimListener.withFinalVisibility(alpha2, i2));
        return alpha2;
    }
}
