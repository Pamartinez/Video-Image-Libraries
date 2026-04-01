package com.google.android.material.timepicker;

import Q1.a;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.ViewCompat;
import com.sec.android.gallery3d.R;
import h2.u;
import java.util.ArrayList;
import java.util.Iterator;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ClockHandView extends View {
    public final ValueAnimator d = new ValueAnimator();
    public boolean e;
    public final ArrayList f = new ArrayList();
    public final int g;

    /* renamed from: h  reason: collision with root package name */
    public final float f1553h;

    /* renamed from: i  reason: collision with root package name */
    public final Paint f1554i;

    /* renamed from: j  reason: collision with root package name */
    public final RectF f1555j;
    public final int k;
    public float l;
    public boolean m;
    public double n;

    /* renamed from: o  reason: collision with root package name */
    public int f1556o;

    /* renamed from: p  reason: collision with root package name */
    public int f1557p;

    public ClockHandView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.materialClockStyle);
        Paint paint = new Paint();
        this.f1554i = paint;
        this.f1555j = new RectF();
        this.f1557p = 1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f630j, R.attr.materialClockStyle, 2131953249);
        k.L(context, R.attr.motionDurationLong2, 200);
        k.M(context, R.attr.motionEasingEmphasizedInterpolator, R1.a.b);
        this.f1556o = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.g = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        Resources resources = getResources();
        this.k = resources.getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width);
        this.f1553h = (float) resources.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius);
        int color = obtainStyledAttributes.getColor(0, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        b(0.0f);
        ViewConfiguration.get(context).getScaledTouchSlop();
        ViewCompat.setImportantForAccessibility(this, 2);
        obtainStyledAttributes.recycle();
    }

    public final int a(int i2) {
        int i7 = this.f1556o;
        if (i2 == 2) {
            return Math.round(((float) i7) * 0.66f);
        }
        return i7;
    }

    public final void b(float f5) {
        ValueAnimator valueAnimator = this.d;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        float f8 = f5 % 360.0f;
        this.l = f8;
        this.n = Math.toRadians((double) (f8 - 90.0f));
        float a7 = (float) a(this.f1557p);
        float cos = (((float) Math.cos(this.n)) * a7) + ((float) (getWidth() / 2));
        float sin = (a7 * ((float) Math.sin(this.n))) + ((float) (getHeight() / 2));
        float f10 = (float) this.g;
        this.f1555j.set(cos - f10, sin - f10, cos + f10, sin + f10);
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ClockFaceView clockFaceView = (ClockFaceView) ((d) it.next());
            if (Math.abs(clockFaceView.t - f8) > 0.001f) {
                clockFaceView.t = f8;
                clockFaceView.b();
            }
        }
        invalidate();
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        int a7 = a(this.f1557p);
        float f5 = (float) width;
        float f8 = (float) a7;
        float cos = (((float) Math.cos(this.n)) * f8) + f5;
        float f10 = (float) height;
        Paint paint = this.f1554i;
        paint.setStrokeWidth(0.0f);
        int i2 = this.g;
        canvas.drawCircle(cos, (f8 * ((float) Math.sin(this.n))) + f10, (float) i2, paint);
        double sin = Math.sin(this.n);
        double d2 = (double) ((float) (a7 - i2));
        paint.setStrokeWidth((float) this.k);
        Canvas canvas2 = canvas;
        canvas2.drawLine(f5, f10, (float) (width + ((int) (Math.cos(this.n) * d2))), (float) (height + ((int) (d2 * sin))), paint);
        canvas2.drawCircle(f5, f10, this.f1553h, paint);
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        if (!this.d.isRunning()) {
            b(this.l);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z3;
        boolean z7;
        int i2;
        int actionMasked = motionEvent.getActionMasked();
        float x9 = motionEvent.getX();
        float y = motionEvent.getY();
        boolean z9 = false;
        if (actionMasked == 0) {
            this.m = false;
            z = true;
            z3 = false;
        } else if (actionMasked == 1 || actionMasked == 2) {
            z3 = this.m;
            if (this.e) {
                if (((float) Math.hypot((double) (x9 - ((float) (getWidth() / 2))), (double) (y - ((float) (getHeight() / 2))))) <= ((float) a(2)) + u.b(getContext(), 12)) {
                    i2 = 2;
                } else {
                    i2 = 1;
                }
                this.f1557p = i2;
            }
            z = false;
        } else {
            z3 = false;
            z = false;
        }
        boolean z10 = this.m;
        int degrees = (int) Math.toDegrees(Math.atan2((double) (y - ((float) (getHeight() / 2))), (double) (x9 - ((float) (getWidth() / 2)))));
        int i7 = degrees + 90;
        if (i7 < 0) {
            i7 = degrees + 450;
        }
        float f5 = (float) i7;
        if (this.l != f5) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (!z || !z7) {
            if (z7 || z3) {
                b(f5);
            }
            this.m = z10 | z9;
            return true;
        }
        z9 = true;
        this.m = z10 | z9;
        return true;
    }
}
