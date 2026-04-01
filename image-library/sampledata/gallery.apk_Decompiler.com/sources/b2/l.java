package B2;

import A4.C0372g;
import A4.O;
import Ab.a;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.textfield.TextInputLayout;
import com.sec.android.gallery3d.R;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends q {
    public final int e;
    public final int f;
    public final TimeInterpolator g;

    /* renamed from: h  reason: collision with root package name */
    public AutoCompleteTextView f48h;

    /* renamed from: i  reason: collision with root package name */
    public final a f49i = new a(2, this);

    /* renamed from: j  reason: collision with root package name */
    public final C0000a f50j = new C0000a(1, this);
    public final O k = new O(7, this);
    public boolean l;
    public boolean m;
    public boolean n;

    /* renamed from: o  reason: collision with root package name */
    public long f51o = Long.MAX_VALUE;

    /* renamed from: p  reason: collision with root package name */
    public AccessibilityManager f52p;
    public ValueAnimator q;
    public ValueAnimator r;

    public l(p pVar) {
        super(pVar);
        this.f = k.L(pVar.getContext(), R.attr.motionDurationShort3, 67);
        this.e = k.L(pVar.getContext(), R.attr.motionDurationShort3, 50);
        this.g = k.M(pVar.getContext(), R.attr.motionEasingLinearInterpolator, R1.a.f640a);
    }

    public final void a() {
        if (this.f52p.isTouchExplorationEnabled() && this.f48h.getInputType() != 0 && !this.d.hasFocus()) {
            this.f48h.dismissDropDown();
        }
        this.f48h.post(new C0372g(4, this));
    }

    public final int c() {
        return R.string.exposed_dropdown_menu_content_description;
    }

    public final int d() {
        return R.drawable.mtrl_dropdown_arrow;
    }

    public final View.OnFocusChangeListener e() {
        return this.f50j;
    }

    public final View.OnClickListener f() {
        return this.f49i;
    }

    public final AccessibilityManagerCompat.TouchExplorationStateChangeListener h() {
        return this.k;
    }

    public final boolean i(int i2) {
        if (i2 != 0) {
            return true;
        }
        return false;
    }

    public final boolean k() {
        return this.n;
    }

    public final void l(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
            this.f48h = autoCompleteTextView;
            autoCompleteTextView.setOnTouchListener(new i(0, this));
            this.f48h.setOnDismissListener(new j(this));
            this.f48h.setThreshold(0);
            TextInputLayout textInputLayout = this.f62a;
            textInputLayout.setErrorIconDrawable((Drawable) null);
            if (editText.getInputType() == 0 && this.f52p.isTouchExplorationEnabled()) {
                ViewCompat.setImportantForAccessibility(this.d, 2);
            }
            textInputLayout.setEndIconVisible(true);
            return;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    public final void m(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (this.f48h.getInputType() == 0) {
            accessibilityNodeInfoCompat.setClassName(Spinner.class.getName());
        }
        if (accessibilityNodeInfoCompat.isShowingHintText()) {
            accessibilityNodeInfoCompat.setHintText((CharSequence) null);
        }
    }

    public final void n(AccessibilityEvent accessibilityEvent) {
        boolean z;
        if (this.f52p.isEnabled() && this.f48h.getInputType() == 0) {
            if ((accessibilityEvent.getEventType() == 32768 || accessibilityEvent.getEventType() == 8) && this.n && !this.f48h.isPopupShowing()) {
                z = true;
            } else {
                z = false;
            }
            if (accessibilityEvent.getEventType() == 1 || z) {
                t();
                this.m = true;
                this.f51o = System.currentTimeMillis();
            }
        }
    }

    public final void q() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        TimeInterpolator timeInterpolator = this.g;
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.setDuration((long) this.f);
        ofFloat.addUpdateListener(new h(0, this));
        this.r = ofFloat;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofFloat2.setInterpolator(timeInterpolator);
        ofFloat2.setDuration((long) this.e);
        ofFloat2.addUpdateListener(new h(0, this));
        this.q = ofFloat2;
        ofFloat2.addListener(new k(0, this));
        this.f52p = (AccessibilityManager) this.f63c.getSystemService("accessibility");
    }

    public final void r() {
        AutoCompleteTextView autoCompleteTextView = this.f48h;
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setOnTouchListener((View.OnTouchListener) null);
            this.f48h.setOnDismissListener((AutoCompleteTextView.OnDismissListener) null);
        }
    }

    public final void s(boolean z) {
        if (this.n != z) {
            this.n = z;
            this.r.cancel();
            this.q.start();
        }
    }

    public final void t() {
        if (this.f48h != null) {
            long currentTimeMillis = System.currentTimeMillis() - this.f51o;
            if (currentTimeMillis < 0 || currentTimeMillis > 300) {
                this.m = false;
            }
            if (!this.m) {
                s(!this.n);
                if (this.n) {
                    this.f48h.requestFocus();
                    this.f48h.showDropDown();
                    return;
                }
                this.f48h.dismissDropDown();
                return;
            }
            this.m = false;
        }
    }
}
