package B2;

import A4.C0372g;
import Ab.a;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.EditText;
import com.sec.android.gallery3d.R;
import og.k;

/* renamed from: B2.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0003d extends q {
    public final int e;
    public final int f;
    public final TimeInterpolator g;

    /* renamed from: h  reason: collision with root package name */
    public final TimeInterpolator f43h;

    /* renamed from: i  reason: collision with root package name */
    public EditText f44i;

    /* renamed from: j  reason: collision with root package name */
    public final a f45j = new a(1, this);
    public final C0000a k = new C0000a(0, this);
    public AnimatorSet l;
    public ValueAnimator m;

    public C0003d(p pVar) {
        super(pVar);
        this.e = k.L(pVar.getContext(), R.attr.motionDurationShort3, 100);
        this.f = k.L(pVar.getContext(), R.attr.motionDurationShort3, 150);
        this.g = k.M(pVar.getContext(), R.attr.motionEasingLinearInterpolator, R1.a.f640a);
        this.f43h = k.M(pVar.getContext(), R.attr.motionEasingEmphasizedInterpolator, R1.a.d);
    }

    public final void a() {
        if (this.b.s == null) {
            s(t());
        }
    }

    public final int c() {
        return R.string.clear_text_end_icon_content_description;
    }

    public final int d() {
        return R.drawable.mtrl_ic_cancel;
    }

    public final View.OnFocusChangeListener e() {
        return this.k;
    }

    public final View.OnClickListener f() {
        return this.f45j;
    }

    public final View.OnFocusChangeListener g() {
        return this.k;
    }

    public final void l(EditText editText) {
        this.f44i = editText;
        this.f62a.setEndIconVisible(t());
    }

    public final void o(boolean z) {
        if (this.b.s != null) {
            s(z);
        }
    }

    public final void q() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.8f, 1.0f});
        ofFloat.setInterpolator(this.f43h);
        ofFloat.setDuration((long) this.f);
        ofFloat.addUpdateListener(new C0001b(this, 1));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        TimeInterpolator timeInterpolator = this.g;
        ofFloat2.setInterpolator(timeInterpolator);
        int i2 = this.e;
        ofFloat2.setDuration((long) i2);
        ofFloat2.addUpdateListener(new C0001b(this, 0));
        AnimatorSet animatorSet = new AnimatorSet();
        this.l = animatorSet;
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        this.l.addListener(new C0002c(this, 0));
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofFloat3.setInterpolator(timeInterpolator);
        ofFloat3.setDuration((long) i2);
        ofFloat3.addUpdateListener(new C0001b(this, 0));
        this.m = ofFloat3;
        ofFloat3.addListener(new C0002c(this, 1));
    }

    public final void r() {
        EditText editText = this.f44i;
        if (editText != null) {
            editText.post(new C0372g(3, this));
        }
    }

    public final void s(boolean z) {
        boolean z3;
        if (this.b.d() == z) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z && !this.l.isRunning()) {
            this.m.cancel();
            this.l.start();
            if (z3) {
                this.l.end();
            }
        } else if (!z) {
            this.l.cancel();
            this.m.start();
            if (z3) {
                this.m.end();
            }
        }
    }

    public final boolean t() {
        EditText editText = this.f44i;
        if (editText == null) {
            return false;
        }
        if ((editText.hasFocus() || this.d.hasFocus()) && this.f44i.getText().length() > 0) {
            return true;
        }
        return false;
    }
}
