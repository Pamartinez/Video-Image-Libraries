package B2;

import B1.a;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import c0.C0086a;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends LinearLayout {
    public final TextInputLayout d;
    public final FrameLayout e;
    public final CheckableImageButton f;
    public ColorStateList g;

    /* renamed from: h  reason: collision with root package name */
    public PorterDuff.Mode f56h;

    /* renamed from: i  reason: collision with root package name */
    public View.OnLongClickListener f57i;

    /* renamed from: j  reason: collision with root package name */
    public final CheckableImageButton f58j;
    public final o k;
    public int l = 0;
    public final LinkedHashSet m = new LinkedHashSet();
    public ColorStateList n;

    /* renamed from: o  reason: collision with root package name */
    public PorterDuff.Mode f59o;

    /* renamed from: p  reason: collision with root package name */
    public int f60p;
    public ImageView.ScaleType q;
    public View.OnLongClickListener r;
    public CharSequence s;
    public final AppCompatTextView t;
    public boolean u;
    public EditText v;
    public final AccessibilityManager w;

    /* renamed from: x  reason: collision with root package name */
    public AccessibilityManagerCompat.TouchExplorationStateChangeListener f61x;
    public final m y = new m(this);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p(com.google.android.material.textfield.TextInputLayout r17, androidx.appcompat.widget.TintTypedArray r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            android.content.Context r3 = r1.getContext()
            r0.<init>(r3)
            r3 = 0
            r0.l = r3
            java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
            r4.<init>()
            r0.m = r4
            B2.m r4 = new B2.m
            r4.<init>(r0)
            r0.y = r4
            B2.n r4 = new B2.n
            r4.<init>(r0)
            android.content.Context r5 = r0.getContext()
            java.lang.String r6 = "accessibility"
            java.lang.Object r5 = r5.getSystemService(r6)
            android.view.accessibility.AccessibilityManager r5 = (android.view.accessibility.AccessibilityManager) r5
            r0.w = r5
            r0.d = r1
            r5 = 8
            r0.setVisibility(r5)
            r0.setOrientation(r3)
            android.widget.FrameLayout$LayoutParams r6 = new android.widget.FrameLayout$LayoutParams
            r7 = 8388613(0x800005, float:1.175495E-38)
            r8 = -2
            r9 = -1
            r6.<init>(r8, r9, r7)
            r0.setLayoutParams(r6)
            android.widget.FrameLayout r6 = new android.widget.FrameLayout
            android.content.Context r7 = r0.getContext()
            r6.<init>(r7)
            r0.e = r6
            r6.setVisibility(r5)
            android.widget.LinearLayout$LayoutParams r7 = new android.widget.LinearLayout$LayoutParams
            r7.<init>(r8, r9)
            r6.setLayoutParams(r7)
            android.content.Context r7 = r0.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            r10 = 2131298572(0x7f09090c, float:1.821512E38)
            com.google.android.material.internal.CheckableImageButton r10 = r0.a(r0, r7, r10)
            r0.f = r10
            r11 = 2131298571(0x7f09090b, float:1.8215119E38)
            com.google.android.material.internal.CheckableImageButton r7 = r0.a(r6, r7, r11)
            r0.f58j = r7
            B2.o r11 = new B2.o
            r11.<init>((B2.p) r0, (androidx.appcompat.widget.TintTypedArray) r2)
            r0.k = r11
            androidx.appcompat.widget.AppCompatTextView r11 = new androidx.appcompat.widget.AppCompatTextView
            android.content.Context r12 = r0.getContext()
            r11.<init>(r12)
            r0.t = r11
            r12 = 38
            boolean r13 = r2.hasValue(r12)
            if (r13 == 0) goto L_0x009c
            android.content.Context r13 = r0.getContext()
            android.content.res.ColorStateList r12 = B1.a.v(r13, r2, r12)
            r0.g = r12
        L_0x009c:
            r12 = 39
            boolean r13 = r2.hasValue(r12)
            r14 = 0
            if (r13 == 0) goto L_0x00af
            int r12 = r2.getInt(r12, r9)
            android.graphics.PorterDuff$Mode r12 = h2.u.d(r12, r14)
            r0.f56h = r12
        L_0x00af:
            r12 = 37
            boolean r13 = r2.hasValue(r12)
            if (r13 == 0) goto L_0x00be
            android.graphics.drawable.Drawable r12 = r2.getDrawable(r12)
            r0.i(r12)
        L_0x00be:
            android.content.res.Resources r12 = r0.getResources()
            r13 = 2131887093(0x7f1203f5, float:1.9408783E38)
            java.lang.CharSequence r12 = r12.getText(r13)
            r10.setContentDescription(r12)
            r12 = 2
            androidx.core.view.ViewCompat.setImportantForAccessibility(r10, r12)
            r10.setClickable(r3)
            r10.setPressable(r3)
            r10.setFocusable(r3)
            r12 = 53
            boolean r13 = r2.hasValue(r12)
            if (r13 != 0) goto L_0x0105
            r13 = 32
            boolean r15 = r2.hasValue(r13)
            if (r15 == 0) goto L_0x00f3
            android.content.Context r15 = r0.getContext()
            android.content.res.ColorStateList r13 = B1.a.v(r15, r2, r13)
            r0.n = r13
        L_0x00f3:
            r13 = 33
            boolean r15 = r2.hasValue(r13)
            if (r15 == 0) goto L_0x0105
            int r13 = r2.getInt(r13, r9)
            android.graphics.PorterDuff$Mode r13 = h2.u.d(r13, r14)
            r0.f59o = r13
        L_0x0105:
            r13 = 30
            boolean r15 = r2.hasValue(r13)
            r8 = 1
            if (r15 == 0) goto L_0x0134
            int r12 = r2.getInt(r13, r3)
            r0.g(r12)
            r12 = 27
            boolean r13 = r2.hasValue(r12)
            if (r13 == 0) goto L_0x012a
            java.lang.CharSequence r12 = r2.getText(r12)
            java.lang.CharSequence r13 = r7.getContentDescription()
            if (r13 == r12) goto L_0x012a
            r7.setContentDescription(r12)
        L_0x012a:
            r12 = 26
            boolean r12 = r2.getBoolean(r12, r8)
            r7.setCheckable(r12)
            goto L_0x0174
        L_0x0134:
            boolean r13 = r2.hasValue(r12)
            if (r13 == 0) goto L_0x0174
            r13 = 54
            boolean r15 = r2.hasValue(r13)
            if (r15 == 0) goto L_0x014c
            android.content.Context r15 = r0.getContext()
            android.content.res.ColorStateList r13 = B1.a.v(r15, r2, r13)
            r0.n = r13
        L_0x014c:
            r13 = 55
            boolean r15 = r2.hasValue(r13)
            if (r15 == 0) goto L_0x015e
            int r13 = r2.getInt(r13, r9)
            android.graphics.PorterDuff$Mode r13 = h2.u.d(r13, r14)
            r0.f59o = r13
        L_0x015e:
            boolean r12 = r2.getBoolean(r12, r3)
            r0.g(r12)
            r12 = 51
            java.lang.CharSequence r12 = r2.getText(r12)
            java.lang.CharSequence r13 = r7.getContentDescription()
            if (r13 == r12) goto L_0x0174
            r7.setContentDescription(r12)
        L_0x0174:
            android.content.res.Resources r12 = r0.getResources()
            r13 = 2131166904(0x7f0706b8, float:1.7948067E38)
            int r12 = r12.getDimensionPixelSize(r13)
            r13 = 29
            int r12 = r2.getDimensionPixelSize(r13, r12)
            if (r12 < 0) goto L_0x0218
            int r13 = r0.f60p
            if (r12 == r13) goto L_0x0199
            r0.f60p = r12
            r7.setMinimumWidth(r12)
            r7.setMinimumHeight(r12)
            r10.setMinimumWidth(r12)
            r10.setMinimumHeight(r12)
        L_0x0199:
            r12 = 31
            boolean r13 = r2.hasValue(r12)
            if (r13 == 0) goto L_0x01b1
            int r9 = r2.getInt(r12, r9)
            android.widget.ImageView$ScaleType r9 = L2.a.j(r9)
            r0.q = r9
            r7.setScaleType(r9)
            r10.setScaleType(r9)
        L_0x01b1:
            r11.setVisibility(r5)
            r5 = 2131298581(0x7f090915, float:1.821514E38)
            r11.setId(r5)
            android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams
            r9 = 1117782016(0x42a00000, float:80.0)
            r12 = -2
            r5.<init>(r12, r12, r9)
            r11.setLayoutParams(r5)
            androidx.core.view.ViewCompat.setAccessibilityLiveRegion(r11, r8)
            r5 = 72
            int r3 = r2.getResourceId(r5, r3)
            androidx.core.widget.TextViewCompat.setTextAppearance(r11, r3)
            r3 = 73
            boolean r5 = r2.hasValue(r3)
            if (r5 == 0) goto L_0x01e0
            android.content.res.ColorStateList r3 = r2.getColorStateList(r3)
            r11.setTextColor(r3)
        L_0x01e0:
            r3 = 71
            java.lang.CharSequence r2 = r2.getText(r3)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L_0x01ed
            goto L_0x01ee
        L_0x01ed:
            r14 = r2
        L_0x01ee:
            r0.s = r14
            r11.setText(r2)
            r0.n()
            r6.addView(r7)
            r0.addView(r11)
            r0.addView(r6)
            r0.addView(r10)
            java.util.LinkedHashSet r2 = r1.h0
            r2.add(r4)
            android.widget.EditText r2 = r1.g
            if (r2 == 0) goto L_0x020e
            r4.a(r1)
        L_0x020e:
            q2.s r1 = new q2.s
            r2 = 2
            r1.<init>(r0, r2)
            r0.addOnAttachStateChangeListener(r1)
            return
        L_0x0218:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "endIconSize cannot be less than 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: B2.p.<init>(com.google.android.material.textfield.TextInputLayout, androidx.appcompat.widget.TintTypedArray):void");
    }

    public final CheckableImageButton a(ViewGroup viewGroup, LayoutInflater layoutInflater, int i2) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(R.layout.design_text_input_end_icon, viewGroup, false);
        checkableImageButton.setId(i2);
        if (a.E(getContext())) {
            MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams(), 0);
        }
        return checkableImageButton;
    }

    public final q b() {
        q qVar;
        int i2 = this.l;
        o oVar = this.k;
        SparseArray sparseArray = (SparseArray) oVar.f55c;
        q qVar2 = (q) sparseArray.get(i2);
        if (qVar2 != null) {
            return qVar2;
        }
        p pVar = (p) oVar.d;
        if (i2 == -1) {
            qVar = new C0004e(pVar, 0);
        } else if (i2 == 0) {
            qVar = new C0004e(pVar, 1);
        } else if (i2 == 1) {
            qVar = new x(pVar, oVar.b);
        } else if (i2 == 2) {
            qVar = new C0003d(pVar);
        } else if (i2 == 3) {
            qVar = new l(pVar);
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "Invalid end icon mode: "));
        }
        sparseArray.append(i2, qVar);
        return qVar;
    }

    public final int c() {
        int i2;
        if (d() || e()) {
            CheckableImageButton checkableImageButton = this.f58j;
            i2 = MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()) + checkableImageButton.getMeasuredWidth();
        } else {
            i2 = 0;
        }
        return ViewCompat.getPaddingEnd(this.t) + ViewCompat.getPaddingEnd(this) + i2;
    }

    public final boolean d() {
        if (this.e.getVisibility() == 0 && this.f58j.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public final boolean e() {
        if (this.f.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public final void f(boolean z) {
        boolean z3;
        boolean isActivated;
        boolean z7;
        q b = b();
        boolean j2 = b.j();
        CheckableImageButton checkableImageButton = this.f58j;
        boolean z9 = true;
        if (!j2 || (z7 = checkableImageButton.d) == b.k()) {
            z3 = false;
        } else {
            checkableImageButton.setChecked(!z7);
            z3 = true;
        }
        if (!(b instanceof l) || (isActivated = checkableImageButton.isActivated()) == ((l) b).l) {
            z9 = z3;
        } else {
            checkableImageButton.setActivated(!isActivated);
        }
        if (z || z9) {
            L2.a.u(this.d, checkableImageButton, this.n);
        }
    }

    public final void g(int i2) {
        boolean z;
        Drawable drawable;
        if (this.l != i2) {
            q b = b();
            AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener = this.f61x;
            AccessibilityManager accessibilityManager = this.w;
            if (!(touchExplorationStateChangeListener == null || accessibilityManager == null)) {
                AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(accessibilityManager, touchExplorationStateChangeListener);
            }
            CharSequence charSequence = null;
            this.f61x = null;
            b.r();
            this.l = i2;
            Iterator it = this.m.iterator();
            if (!it.hasNext()) {
                if (i2 != 0) {
                    z = true;
                } else {
                    z = false;
                }
                h(z);
                q b5 = b();
                int i7 = this.k.f54a;
                if (i7 == 0) {
                    i7 = b5.d();
                }
                if (i7 != 0) {
                    drawable = AppCompatResources.getDrawable(getContext(), i7);
                } else {
                    drawable = null;
                }
                CheckableImageButton checkableImageButton = this.f58j;
                checkableImageButton.setImageDrawable(drawable);
                TextInputLayout textInputLayout = this.d;
                if (drawable != null) {
                    L2.a.c(textInputLayout, checkableImageButton, this.n, this.f59o);
                    L2.a.u(textInputLayout, checkableImageButton, this.n);
                }
                int c5 = b5.c();
                if (c5 != 0) {
                    charSequence = getResources().getText(c5);
                }
                if (checkableImageButton.getContentDescription() != charSequence) {
                    checkableImageButton.setContentDescription(charSequence);
                }
                checkableImageButton.setCheckable(b5.j());
                if (b5.i(textInputLayout.getBoxBackgroundMode())) {
                    b5.q();
                    AccessibilityManagerCompat.TouchExplorationStateChangeListener h5 = b5.h();
                    this.f61x = h5;
                    if (!(h5 == null || accessibilityManager == null || !ViewCompat.isAttachedToWindow(this))) {
                        AccessibilityManagerCompat.addTouchExplorationStateChangeListener(accessibilityManager, this.f61x);
                    }
                    View.OnClickListener f5 = b5.f();
                    View.OnLongClickListener onLongClickListener = this.r;
                    checkableImageButton.setOnClickListener(f5);
                    L2.a.x(checkableImageButton, onLongClickListener);
                    EditText editText = this.v;
                    if (editText != null) {
                        b5.l(editText);
                        j(b5);
                    }
                    L2.a.c(textInputLayout, checkableImageButton, this.n, this.f59o);
                    f(true);
                    return;
                }
                throw new IllegalStateException("The current box background mode " + textInputLayout.getBoxBackgroundMode() + " is not supported by the end icon mode " + i2);
            }
            throw C0212a.h(it);
        }
    }

    public final void h(boolean z) {
        int i2;
        if (d() != z) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            this.f58j.setVisibility(i2);
            k();
            m();
            this.d.q();
        }
    }

    public final void i(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.f;
        checkableImageButton.setImageDrawable(drawable);
        l();
        L2.a.c(this.d, checkableImageButton, this.g, this.f56h);
    }

    public final void j(q qVar) {
        if (this.v != null) {
            if (qVar.e() != null) {
                this.v.setOnFocusChangeListener(qVar.e());
            }
            if (qVar.g() != null) {
                this.f58j.setOnFocusChangeListener(qVar.g());
            }
        }
    }

    public final void k() {
        int i2;
        boolean z;
        int i7 = 8;
        if (this.f58j.getVisibility() != 0 || e()) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        this.e.setVisibility(i2);
        if (this.s == null || this.u) {
            z = true;
        } else {
            z = false;
        }
        if (d() || e() || !z) {
            i7 = 0;
        }
        setVisibility(i7);
    }

    public final void l() {
        int i2;
        CheckableImageButton checkableImageButton = this.f;
        Drawable drawable = checkableImageButton.getDrawable();
        TextInputLayout textInputLayout = this.d;
        if (drawable == null || !textInputLayout.m.q || !textInputLayout.m()) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        checkableImageButton.setVisibility(i2);
        k();
        m();
        if (this.l == 0) {
            textInputLayout.q();
        }
    }

    public final void m() {
        int i2;
        TextInputLayout textInputLayout = this.d;
        if (textInputLayout.g != null) {
            if (d() || e()) {
                i2 = 0;
            } else {
                i2 = ViewCompat.getPaddingEnd(textInputLayout.g);
            }
            ViewCompat.setPaddingRelative(this.t, getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), textInputLayout.g.getPaddingTop(), i2, textInputLayout.g.getPaddingBottom());
        }
    }

    public final void n() {
        int i2;
        AppCompatTextView appCompatTextView = this.t;
        int visibility = appCompatTextView.getVisibility();
        boolean z = false;
        if (this.s == null || this.u) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        if (visibility != i2) {
            q b = b();
            if (i2 == 0) {
                z = true;
            }
            b.o(z);
        }
        k();
        appCompatTextView.setVisibility(i2);
        this.d.q();
    }
}
