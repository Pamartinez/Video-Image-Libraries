package J0;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.Choreographer;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import x0.C0323a;
import x0.C0326d;
import x0.C0332j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends ValueAnimator implements Choreographer.FrameCallback {
    public final CopyOnWriteArraySet d = new CopyOnWriteArraySet();
    public final CopyOnWriteArraySet e = new CopyOnWriteArraySet();
    public final CopyOnWriteArraySet f = new CopyOnWriteArraySet();
    public float g = 1.0f;

    /* renamed from: h  reason: collision with root package name */
    public boolean f356h = false;

    /* renamed from: i  reason: collision with root package name */
    public long f357i = 0;

    /* renamed from: j  reason: collision with root package name */
    public float f358j = 0.0f;
    public float k = 0.0f;
    public int l = 0;
    public float m = -2.14748365E9f;
    public float n = 2.14748365E9f;

    /* renamed from: o  reason: collision with root package name */
    public C0332j f359o;

    /* renamed from: p  reason: collision with root package name */
    public boolean f360p = false;
    public boolean q = false;

    public final float a() {
        C0332j jVar = this.f359o;
        if (jVar == null) {
            return 0.0f;
        }
        float f5 = this.k;
        float f8 = jVar.l;
        return (f5 - f8) / (jVar.m - f8);
    }

    public final void addListener(Animator.AnimatorListener animatorListener) {
        this.e.add(animatorListener);
    }

    public final void addPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.f.add(animatorPauseListener);
    }

    public final void addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.d.add(animatorUpdateListener);
    }

    public final float b() {
        C0332j jVar = this.f359o;
        if (jVar == null) {
            return 0.0f;
        }
        float f5 = this.n;
        if (f5 == 2.14748365E9f) {
            return jVar.m;
        }
        return f5;
    }

    public final float c() {
        C0332j jVar = this.f359o;
        if (jVar == null) {
            return 0.0f;
        }
        float f5 = this.m;
        if (f5 == -2.14748365E9f) {
            return jVar.l;
        }
        return f5;
    }

    public final void cancel() {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationCancel(this);
        }
        e(d());
        g(true);
    }

    public final boolean d() {
        if (this.g < 0.0f) {
            return true;
        }
        return false;
    }

    public final void doFrame(long j2) {
        float f5;
        float f8;
        boolean z = false;
        if (this.f360p) {
            g(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
        C0332j jVar = this.f359o;
        if (jVar != null && this.f360p) {
            C0323a aVar = C0326d.f2049a;
            long j3 = this.f357i;
            long j8 = 0;
            if (j3 != 0) {
                j8 = j2 - j3;
            }
            float abs = ((float) j8) / ((1.0E9f / jVar.n) / Math.abs(this.g));
            float f10 = this.f358j;
            if (d()) {
                abs = -abs;
            }
            float f11 = f10 + abs;
            float c5 = c();
            float b = b();
            PointF pointF = f.f362a;
            if (f11 >= c5 && f11 <= b) {
                z = true;
            }
            float f12 = this.f358j;
            float b5 = f.b(f11, c(), b());
            this.f358j = b5;
            if (this.q) {
                b5 = (float) Math.floor((double) b5);
            }
            this.k = b5;
            this.f357i = j2;
            if (!this.q || this.f358j != f12) {
                f();
            }
            if (!z) {
                if (getRepeatCount() == -1 || this.l < getRepeatCount()) {
                    Iterator it = this.e.iterator();
                    while (it.hasNext()) {
                        ((Animator.AnimatorListener) it.next()).onAnimationRepeat(this);
                    }
                    this.l++;
                    if (getRepeatMode() == 2) {
                        this.f356h = !this.f356h;
                        this.g = -this.g;
                    } else {
                        if (d()) {
                            f5 = b();
                        } else {
                            f5 = c();
                        }
                        this.f358j = f5;
                        this.k = f5;
                    }
                    this.f357i = j2;
                } else {
                    if (this.g < 0.0f) {
                        f8 = c();
                    } else {
                        f8 = b();
                    }
                    this.f358j = f8;
                    this.k = f8;
                    g(true);
                    e(d());
                }
            }
            if (this.f359o != null) {
                float f13 = this.k;
                if (f13 < this.m || f13 > this.n) {
                    throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", new Object[]{Float.valueOf(this.m), Float.valueOf(this.n), Float.valueOf(this.k)}));
                }
            }
            C0323a aVar2 = C0326d.f2049a;
        }
    }

    public final void e(boolean z) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationEnd(this, z);
        }
    }

    public final void f() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((ValueAnimator.AnimatorUpdateListener) it.next()).onAnimationUpdate(this);
        }
    }

    public final void g(boolean z) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z) {
            this.f360p = false;
        }
    }

    public final float getAnimatedFraction() {
        float c5;
        float b;
        float c6;
        if (this.f359o == null) {
            return 0.0f;
        }
        if (d()) {
            c5 = b() - this.k;
            b = b();
            c6 = c();
        } else {
            c5 = this.k - c();
            b = b();
            c6 = c();
        }
        return c5 / (b - c6);
    }

    public final Object getAnimatedValue() {
        return Float.valueOf(a());
    }

    public final long getDuration() {
        C0332j jVar = this.f359o;
        if (jVar == null) {
            return 0;
        }
        return (long) jVar.b();
    }

    public final long getStartDelay() {
        throw new UnsupportedOperationException("LottieAnimator does not support getStartDelay.");
    }

    public final void h(float f5) {
        if (this.f358j != f5) {
            float b = f.b(f5, c(), b());
            this.f358j = b;
            if (this.q) {
                b = (float) Math.floor((double) b);
            }
            this.k = b;
            this.f357i = 0;
            f();
        }
    }

    public final boolean isRunning() {
        return this.f360p;
    }

    public final void j(float f5, float f8) {
        float f10;
        float f11;
        if (f5 <= f8) {
            C0332j jVar = this.f359o;
            if (jVar == null) {
                f10 = -3.4028235E38f;
            } else {
                f10 = jVar.l;
            }
            if (jVar == null) {
                f11 = Float.MAX_VALUE;
            } else {
                f11 = jVar.m;
            }
            float b = f.b(f5, f10, f11);
            float b5 = f.b(f8, f10, f11);
            if (b != this.m || b5 != this.n) {
                this.m = b;
                this.n = b5;
                h((float) ((int) f.b(this.k, b, b5)));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("minFrame (" + f5 + ") must be <= maxFrame (" + f8 + ")");
    }

    public final void removeAllListeners() {
        this.e.clear();
    }

    public final void removeAllUpdateListeners() {
        this.d.clear();
    }

    public final void removeListener(Animator.AnimatorListener animatorListener) {
        this.e.remove(animatorListener);
    }

    public final void removePauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.f.remove(animatorPauseListener);
    }

    public final void removeUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.d.remove(animatorUpdateListener);
    }

    public final void setInterpolator(TimeInterpolator timeInterpolator) {
        throw new UnsupportedOperationException("LottieAnimator does not support setInterpolator.");
    }

    public final void setRepeatMode(int i2) {
        super.setRepeatMode(i2);
        if (i2 != 2 && this.f356h) {
            this.f356h = false;
            this.g = -this.g;
        }
    }

    public final void setStartDelay(long j2) {
        throw new UnsupportedOperationException("LottieAnimator does not support setStartDelay.");
    }

    public final ValueAnimator setDuration(long j2) {
        throw new UnsupportedOperationException("LottieAnimator does not support setDuration.");
    }
}
