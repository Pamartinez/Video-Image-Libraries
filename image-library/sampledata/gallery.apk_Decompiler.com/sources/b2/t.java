package B2;

import R1.a;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.textfield.TextInputLayout;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t {

    /* renamed from: A  reason: collision with root package name */
    public ColorStateList f66A;
    public Typeface B;

    /* renamed from: a  reason: collision with root package name */
    public final int f67a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f68c;
    public final TimeInterpolator d;
    public final TimeInterpolator e;
    public final TimeInterpolator f;
    public final Context g;

    /* renamed from: h  reason: collision with root package name */
    public final TextInputLayout f69h;

    /* renamed from: i  reason: collision with root package name */
    public LinearLayout f70i;

    /* renamed from: j  reason: collision with root package name */
    public int f71j;
    public FrameLayout k;
    public AnimatorSet l;
    public final float m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public int f72o;

    /* renamed from: p  reason: collision with root package name */
    public CharSequence f73p;
    public boolean q;
    public AppCompatTextView r;
    public CharSequence s;
    public int t;
    public int u;
    public ColorStateList v;
    public CharSequence w;

    /* renamed from: x  reason: collision with root package name */
    public boolean f74x;
    public AppCompatTextView y;
    public int z;

    public t(TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.g = context;
        this.f69h = textInputLayout;
        this.m = (float) context.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
        this.f67a = k.L(context, R.attr.motionDurationShort4, 217);
        this.b = k.L(context, R.attr.motionDurationMedium4, 167);
        this.f68c = k.L(context, R.attr.motionDurationShort4, 167);
        this.d = k.M(context, R.attr.motionEasingEmphasizedDecelerateInterpolator, a.d);
        LinearInterpolator linearInterpolator = a.f640a;
        this.e = k.M(context, R.attr.motionEasingEmphasizedDecelerateInterpolator, linearInterpolator);
        this.f = k.M(context, R.attr.motionEasingLinearInterpolator, linearInterpolator);
    }

    public final void a(AppCompatTextView appCompatTextView, int i2) {
        if (this.f70i == null && this.k == null) {
            Context context = this.g;
            LinearLayout linearLayout = new LinearLayout(context);
            this.f70i = linearLayout;
            linearLayout.setOrientation(0);
            LinearLayout linearLayout2 = this.f70i;
            TextInputLayout textInputLayout = this.f69h;
            textInputLayout.addView(linearLayout2, -1, -2);
            this.k = new FrameLayout(context);
            this.f70i.addView(this.k, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (textInputLayout.getEditText() != null) {
                b();
            }
        }
        if (i2 == 0 || i2 == 1) {
            this.k.setVisibility(0);
            this.k.addView(appCompatTextView);
        } else {
            this.f70i.addView(appCompatTextView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f70i.setVisibility(0);
        this.f71j++;
    }

    public final void b() {
        if (this.f70i != null) {
            TextInputLayout textInputLayout = this.f69h;
            if (textInputLayout.getEditText() != null) {
                EditText editText = textInputLayout.getEditText();
                Context context = this.g;
                boolean E = B1.a.E(context);
                LinearLayout linearLayout = this.f70i;
                int paddingStart = ViewCompat.getPaddingStart(editText);
                if (E) {
                    paddingStart = context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_font_1_3_padding_horizontal);
                }
                int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_default_padding_top);
                if (E) {
                    dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_font_1_3_padding_top);
                }
                int paddingEnd = ViewCompat.getPaddingEnd(editText);
                if (E) {
                    paddingEnd = context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_font_1_3_padding_horizontal);
                }
                ViewCompat.setPaddingRelative(linearLayout, paddingStart, dimensionPixelSize, paddingEnd, 0);
            }
        }
    }

    public final void c() {
        AnimatorSet animatorSet = this.l;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }

    public final void d(ArrayList arrayList, boolean z3, AppCompatTextView appCompatTextView, int i2, int i7, int i8) {
        boolean z7;
        float f5;
        long j2;
        TimeInterpolator timeInterpolator;
        if (appCompatTextView != null && z3) {
            if (i2 == i8 || i2 == i7) {
                if (i8 == i2) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                if (z7) {
                    f5 = 1.0f;
                } else {
                    f5 = 0.0f;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(appCompatTextView, View.ALPHA, new float[]{f5});
                int i10 = this.f68c;
                if (z7) {
                    j2 = (long) this.b;
                } else {
                    j2 = (long) i10;
                }
                ofFloat.setDuration(j2);
                if (z7) {
                    timeInterpolator = this.e;
                } else {
                    timeInterpolator = this.f;
                }
                ofFloat.setInterpolator(timeInterpolator);
                if (i2 == i8 && i7 != 0) {
                    ofFloat.setStartDelay((long) i10);
                }
                arrayList.add(ofFloat);
                if (i8 == i2 && i7 != 0) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(appCompatTextView, View.TRANSLATION_Y, new float[]{-this.m, 0.0f});
                    ofFloat2.setDuration((long) this.f67a);
                    ofFloat2.setInterpolator(this.d);
                    ofFloat2.setStartDelay((long) i10);
                    arrayList.add(ofFloat2);
                }
            }
        }
    }

    public final TextView e(int i2) {
        if (i2 == 1) {
            return this.r;
        }
        if (i2 != 2) {
            return null;
        }
        return this.y;
    }

    public final void f() {
        this.f73p = null;
        c();
        if (this.n == 1) {
            if (!this.f74x || TextUtils.isEmpty(this.w)) {
                this.f72o = 0;
            } else {
                this.f72o = 2;
            }
        }
        i(this.n, this.f72o, h(this.r, ""));
    }

    public final void g(AppCompatTextView appCompatTextView, int i2) {
        FrameLayout frameLayout;
        LinearLayout linearLayout = this.f70i;
        if (linearLayout != null) {
            if ((i2 == 0 || i2 == 1) && (frameLayout = this.k) != null) {
                frameLayout.removeView(appCompatTextView);
            } else {
                linearLayout.removeView(appCompatTextView);
            }
            int i7 = this.f71j - 1;
            this.f71j = i7;
            LinearLayout linearLayout2 = this.f70i;
            if (i7 == 0) {
                linearLayout2.setVisibility(8);
            }
        }
    }

    public final boolean h(AppCompatTextView appCompatTextView, CharSequence charSequence) {
        TextInputLayout textInputLayout = this.f69h;
        if (!ViewCompat.isLaidOut(textInputLayout) || !textInputLayout.isEnabled()) {
            return false;
        }
        if (this.f72o != this.n || appCompatTextView == null || !TextUtils.equals(appCompatTextView.getText(), charSequence)) {
            return true;
        }
        return false;
    }

    public final void i(int i2, int i7, boolean z3) {
        TextView e7;
        TextView e8;
        t tVar = this;
        int i8 = i2;
        int i10 = i7;
        boolean z7 = z3;
        if (i8 != i10) {
            if (z7) {
                AnimatorSet animatorSet = new AnimatorSet();
                tVar.l = animatorSet;
                ArrayList arrayList = new ArrayList();
                tVar.d(arrayList, tVar.f74x, tVar.y, 2, i8, i10);
                int i11 = i7;
                tVar.d(arrayList, tVar.q, tVar.r, 1, i2, i11);
                int size = arrayList.size();
                long j2 = 0;
                for (int i12 = 0; i12 < size; i12++) {
                    Animator animator = (Animator) arrayList.get(i12);
                    j2 = Math.max(j2, animator.getDuration() + animator.getStartDelay());
                }
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 0});
                ofInt.setDuration(j2);
                arrayList.add(0, ofInt);
                animatorSet.playTogether(arrayList);
                r rVar = new r(this, i11, e(i2), i2, tVar.e(i11));
                tVar = this;
                animatorSet.addListener(rVar);
                animatorSet.start();
            } else if (i8 != i10) {
                if (!(i10 == 0 || (e8 = tVar.e(i10)) == null)) {
                    e8.setVisibility(0);
                    e8.setAlpha(1.0f);
                }
                if (!(i8 == 0 || (e7 = e(i2)) == null)) {
                    e7.setVisibility(4);
                    if (i8 == 1) {
                        e7.setText((CharSequence) null);
                    }
                }
                tVar.n = i10;
            }
            TextInputLayout textInputLayout = tVar.f69h;
            textInputLayout.r();
            textInputLayout.u(z7, false);
            textInputLayout.x();
        }
    }
}
