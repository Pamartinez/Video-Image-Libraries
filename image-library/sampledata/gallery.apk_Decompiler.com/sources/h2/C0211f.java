package h2;

import Q1.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.LinearLayoutCompat;

/* renamed from: h2.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0211f extends LinearLayoutCompat {
    public Drawable d;
    public final Rect e = new Rect();
    public final Rect f = new Rect();
    public int g = 119;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f1767h = true;

    /* renamed from: i  reason: collision with root package name */
    public boolean f1768i = false;

    public C0211f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        TypedArray d2 = p.d(context2, attributeSet2, a.t, 0, 0, new int[0]);
        this.g = d2.getInt(1, this.g);
        Drawable drawable = d2.getDrawable(0);
        if (drawable != null) {
            setForeground(drawable);
        }
        this.f1767h = d2.getBoolean(2, true);
        d2.recycle();
    }

    public final void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.d;
        if (drawable != null) {
            if (this.f1768i) {
                this.f1768i = false;
                int right = getRight() - getLeft();
                int bottom = getBottom() - getTop();
                boolean z = this.f1767h;
                Rect rect = this.e;
                if (z) {
                    rect.set(0, 0, right, bottom);
                } else {
                    rect.set(getPaddingLeft(), getPaddingTop(), right - getPaddingRight(), bottom - getPaddingBottom());
                }
                int i2 = this.g;
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                Rect rect2 = this.f;
                Gravity.apply(i2, intrinsicWidth, intrinsicHeight, rect, rect2);
                drawable.setBounds(rect2);
            }
            drawable.draw(canvas);
        }
    }

    public final void drawableHotspotChanged(float f5, float f8) {
        super.drawableHotspotChanged(f5, f8);
        Drawable drawable = this.d;
        if (drawable != null) {
            drawable.setHotspot(f5, f8);
        }
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.d;
        if (drawable != null && drawable.isStateful()) {
            this.d.setState(getDrawableState());
        }
    }

    public Drawable getForeground() {
        return this.d;
    }

    public int getForegroundGravity() {
        return this.g;
    }

    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.d;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        this.f1768i = z | this.f1768i;
    }

    public final void onSizeChanged(int i2, int i7, int i8, int i10) {
        super.onSizeChanged(i2, i7, i8, i10);
        this.f1768i = true;
    }

    public void setForeground(Drawable drawable) {
        Drawable drawable2 = this.d;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
                unscheduleDrawable(this.d);
            }
            this.d = drawable;
            this.f1768i = true;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.g == 119) {
                    drawable.getPadding(new Rect());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    public void setForegroundGravity(int i2) {
        if (this.g != i2) {
            if ((8388615 & i2) == 0) {
                i2 |= 8388611;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.g = i2;
            if (i2 == 119 && this.d != null) {
                this.d.getPadding(new Rect());
            }
            requestLayout();
        }
    }

    public final boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.d) {
            return true;
        }
        return false;
    }
}
