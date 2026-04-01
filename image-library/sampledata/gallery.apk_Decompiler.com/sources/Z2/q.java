package z2;

import B1.b;
import D0.f;
import R1.a;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.LinearInterpolator;
import androidx.appcompat.R$dimen;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.samsung.android.ocr.MOCRLang;
import com.sec.android.gallery3d.R;
import h2.C0206a;
import h2.p;
import java.util.ArrayList;
import java.util.List;
import o1.C0246a;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class q {

    /* renamed from: A  reason: collision with root package name */
    public static final LinearInterpolator f2216A = a.f640a;
    public static final LinearOutSlowInInterpolator B = a.d;

    /* renamed from: C  reason: collision with root package name */
    public static final Handler f2217C = new Handler(Looper.getMainLooper(), new Object());
    public static final int[] D = {R.attr.snackbarStyle};
    public static final String E = q.class.getSimpleName();
    public static int y;
    public static final FastOutSlowInInterpolator z = a.b;

    /* renamed from: a  reason: collision with root package name */
    public final int f2218a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f2219c;
    public final TimeInterpolator d;
    public final TimeInterpolator e;
    public final TimeInterpolator f;
    public final ViewGroup g;

    /* renamed from: h  reason: collision with root package name */
    public final Context f2220h;

    /* renamed from: i  reason: collision with root package name */
    public final p f2221i;

    /* renamed from: j  reason: collision with root package name */
    public final SnackbarContentLayout f2222j;
    public int k;
    public m l;
    public final i m = new i(this, 0);
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public int f2223o;

    /* renamed from: p  reason: collision with root package name */
    public int f2224p;
    public int q;
    public int r;
    public int s;
    public boolean t;
    public ArrayList u;
    public final AccessibilityManager v;
    public final j w = new FloatPropertyCompat("scale_layout");

    /* renamed from: x  reason: collision with root package name */
    public final k f2225x = new k(this);

    /* JADX WARNING: type inference failed for: r0v1, types: [androidx.dynamicanimation.animation.FloatPropertyCompat, z2.j] */
    public q(Context context, ViewGroup viewGroup, SnackbarContentLayout snackbarContentLayout, SnackbarContentLayout snackbarContentLayout2) {
        int i2;
        this.g = viewGroup;
        this.f2222j = snackbarContentLayout2;
        this.f2220h = context;
        p.c(context, p.f1776a, "Theme.AppCompat");
        LayoutInflater from = LayoutInflater.from(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(D);
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        if (resourceId != -1) {
            i2 = R.layout.mtrl_layout_snackbar;
        } else {
            i2 = R.layout.design_layout_snackbar;
        }
        p pVar = (p) from.inflate(i2, viewGroup, false);
        this.f2221i = pVar;
        pVar.setBaseTransientBottomBar(this);
        float actionTextColorAlpha = pVar.getActionTextColorAlpha();
        if (actionTextColorAlpha != 1.0f) {
            snackbarContentLayout.e.setTextColor(C0246a.c0(actionTextColorAlpha, C0246a.W(R.attr.colorSurface, snackbarContentLayout), snackbarContentLayout.e.getCurrentTextColor()));
        }
        pVar.addView(snackbarContentLayout);
        ViewCompat.setAccessibilityLiveRegion(pVar, 1);
        ViewCompat.setImportantForAccessibility(pVar, 1);
        ViewCompat.setFitsSystemWindows(pVar, true);
        ViewCompat.setOnApplyWindowInsetsListener(pVar, new b(19, this));
        ViewCompat.setAccessibilityDelegate(pVar, new C0206a(2, this));
        this.v = (AccessibilityManager) context.getSystemService("accessibility");
        this.f2219c = k.L(context, R.attr.motionDurationLong2, 250);
        this.f2218a = k.L(context, R.attr.motionDurationLong2, MOCRLang.KHMER);
        this.b = k.L(context, R.attr.motionDurationMedium1, MOCRLang.KHMER);
        this.d = k.M(context, R.attr.motionEasingEmphasizedInterpolator, f2216A);
        this.f = k.M(context, R.attr.motionEasingEmphasizedInterpolator, B);
        this.e = k.M(context, R.attr.motionEasingEmphasizedInterpolator, z);
    }

    public static void f(SnackbarContentLayout snackbarContentLayout, int i2) {
        Drawable background;
        if (snackbarContentLayout.f1498o && snackbarContentLayout.n.intValue() == 0 && (background = snackbarContentLayout.getBackground()) != null) {
            background.setAlpha(i2);
            background.invalidateSelf();
        }
    }

    public final void a(int i2) {
        boolean z3;
        f F4 = f.F();
        k kVar = this.f2225x;
        synchronized (F4.e) {
            try {
                if (F4.H(kVar)) {
                    F4.z((v) F4.g, i2);
                } else {
                    v vVar = (v) F4.f106h;
                    if (vVar == null || kVar == null || vVar.f2233a.get() != kVar) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    if (z3) {
                        F4.z((v) F4.f106h, i2);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final View b() {
        m mVar = this.l;
        if (mVar == null) {
            return null;
        }
        return (View) mVar.e.get();
    }

    public final void c(int i2) {
        f F4 = f.F();
        k kVar = this.f2225x;
        synchronized (F4.e) {
            try {
                if (F4.H(kVar)) {
                    F4.g = null;
                    if (((v) F4.f106h) != null) {
                        F4.V();
                    }
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        ArrayList arrayList = this.u;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((n) this.u.get(size)).onDismissed(this, i2);
            }
        }
        ViewParent parent = this.f2221i.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.f2221i);
        }
    }

    public final void d() {
        f F4 = f.F();
        k kVar = this.f2225x;
        synchronized (F4.e) {
            try {
                if (F4.H(kVar)) {
                    F4.T((v) F4.g);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        ArrayList arrayList = this.u;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((n) this.u.get(size)).onShown(this);
            }
        }
    }

    public final void e(boolean z3) {
        float f5;
        SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) this.f2221i.findViewById(R.id.snackbar_content_layout);
        if (z3) {
            snackbarContentLayout.setElevation((float) this.f2220h.getResources().getDimensionPixelSize(R$dimen.sesl_figma_elevation_md));
        }
        SpringAnimation springAnimation = new SpringAnimation(snackbarContentLayout, this.w);
        springAnimation.cancel();
        springAnimation.setMinimumVisibleChange(0.002f);
        float f8 = 1.0f;
        springAnimation.setSpring(new SpringForce().setStiffness(361.0f).setDampingRatio(1.0f));
        if (z3) {
            f5 = 0.85f;
        } else {
            f5 = snackbarContentLayout.getScaleX();
        }
        springAnimation.setStartValue(f5);
        if (!z3) {
            f8 = 0.85f;
        }
        springAnimation.animateToFinalPosition(f8);
    }

    public final void g() {
        AccessibilityManager accessibilityManager;
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        p pVar = this.f2221i;
        if (pVar.f == 2 || (accessibilityManager = this.v) == null || ((enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1)) != null && enabledAccessibilityServiceList.isEmpty())) {
            pVar.post(new i(this, 2));
            return;
        }
        if (pVar.getParent() != null) {
            pVar.setVisibility(0);
        }
        d();
    }

    public final void h(int i2, int i7, View view) {
        Drawable background;
        float dimensionPixelSize = (float) this.f2220h.getResources().getDimensionPixelSize(R.dimen.sesl_design_snackbar_suggest_background_radius);
        SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) view;
        if (snackbarContentLayout.f1498o && snackbarContentLayout.n.intValue() == 0 && (background = snackbarContentLayout.getBackground()) != null) {
            Rect bounds = background.getBounds();
            int centerX = bounds.centerX();
            int centerY = bounds.centerY();
            int i8 = i2 / 2;
            int i10 = i7 / 2;
            background.setBounds(centerX - i8, centerY - i10, centerX + i8, centerY + i10);
            background.invalidateSelf();
        }
        view.setOutlineProvider(new g(i2, i7, dimensionPixelSize));
        view.setClipToOutline(true);
    }

    public final void i() {
        int i2;
        boolean z3;
        p pVar = this.f2221i;
        ViewGroup.LayoutParams layoutParams = pVar.getLayoutParams();
        boolean z7 = layoutParams instanceof ViewGroup.MarginLayoutParams;
        String str = E;
        if (!z7) {
            Log.w(str, "Unable to update margins because layout params are not MarginLayoutParams");
        } else if (pVar.m == null) {
            Log.w(str, "Unable to update margins because original view margins are not set");
        } else if (pVar.getParent() != null) {
            if (b() != null) {
                i2 = this.q;
            } else {
                i2 = this.n;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            Rect rect = pVar.m;
            int i7 = rect.bottom + i2;
            int i8 = rect.left + this.f2223o;
            int i10 = rect.right + this.f2224p;
            int i11 = rect.top;
            if (marginLayoutParams.bottomMargin == i7 && marginLayoutParams.leftMargin == i8 && marginLayoutParams.rightMargin == i10 && marginLayoutParams.topMargin == i11) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z3) {
                marginLayoutParams.bottomMargin = i7;
                marginLayoutParams.leftMargin = i8;
                marginLayoutParams.rightMargin = i10;
                marginLayoutParams.topMargin = i11;
                pVar.requestLayout();
            }
            pVar.requestLayout();
            if ((z3 || this.s != this.r) && this.r > 0) {
                ViewGroup.LayoutParams layoutParams2 = pVar.getLayoutParams();
                if ((layoutParams2 instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) layoutParams2).getBehavior() instanceof SwipeDismissBehavior)) {
                    i iVar = this.m;
                    pVar.removeCallbacks(iVar);
                    pVar.post(iVar);
                }
            }
        }
    }
}
