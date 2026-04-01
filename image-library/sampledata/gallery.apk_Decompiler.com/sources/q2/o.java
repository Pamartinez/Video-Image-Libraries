package q2;

import S1.v;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.appcompat.oneui.common.internal.resource.OpenThemeResourceDrawableRes;
import androidx.appcompat.oneui.common.internal.resource.ThemeResourceDrawableRes;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import m2.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends FrameLayout implements a {
    public static final /* synthetic */ int n = 0;
    public final ArrayList d = new ArrayList();
    public final k e;
    public final k f;
    public final k g;

    /* renamed from: h  reason: collision with root package name */
    public final int f1884h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f1885i;

    /* renamed from: j  reason: collision with root package name */
    public final Rect f1886j;
    public final ObjectAnimator k;
    public float l;
    public float m;

    public o(Context context) {
        super(context);
        k kVar = new k(context);
        this.e = kVar;
        k kVar2 = new k(context);
        this.f = kVar2;
        k kVar3 = new k(context);
        this.g = kVar3;
        int intValue = new OpenThemeResourceDrawableRes(new ThemeResourceDrawableRes(R.drawable.sesl_floating_appbar_round_background_light, R.drawable.sesl_floating_appbar_round_background_dark), new ThemeResourceDrawableRes(R.drawable.sesl_floating_appbar_round_background_for_theme, 0, 2, (e) null)).getResource(context).intValue();
        this.f1884h = intValue;
        this.f1886j = new Rect();
        this.k = new ObjectAnimator();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, new i("Prj_Bg_Alpha_Anim", 1), new float[]{getAlpha()});
        j.d(ofFloat, "ofFloat(this, mPrjAlphaAnimProperty, alpha)");
        this.k = ofFloat;
        ofFloat.setDuration(150);
        ofFloat.setInterpolator(e.f1877a);
        setAlpha(0.0f);
        ofFloat.addListener(new v(1, this));
        kVar.setTag("end_first");
        kVar2.setTag("start_first");
        kVar3.setTag("start_second");
        kVar2.setId(R.id.floating_toolbar_item_background_start_first);
        kVar3.setId(R.id.floating_toolbar_item_background_start_second);
        kVar.setId(R.id.floating_toolbar_item_background_end_first);
        kVar2.setBackground(getResources().getDrawable(intValue));
        kVar3.setBackground(getResources().getDrawable(intValue));
        kVar.setBackground(getResources().getDrawable(intValue));
        addView(kVar, 0, -1);
        addView(kVar2, 0, -1);
        addView(kVar3, 0, -1);
        float dimension = getResources().getDimension(R.dimen.sesl_floating_toolbar_projection_background_elevation);
        kVar.setElevation(dimension);
        kVar2.setElevation(dimension);
        kVar3.setElevation(dimension);
    }

    /* access modifiers changed from: private */
    public final u getParentFloatingLayout() {
        u uVar;
        ViewParent parent = getParent();
        String str = null;
        if (parent instanceof u) {
            uVar = (u) parent;
        } else {
            uVar = null;
        }
        if (uVar != null) {
            return uVar;
        }
        StringBuilder sb2 = new StringBuilder("SeslProjectionView must have a FloatingGroupLayout as its parent, but found: ");
        ViewParent parent2 = getParent();
        if (parent2 != null) {
            str = parent2.getClass().getSimpleName();
        }
        sb2.append(str);
        throw new IllegalStateException(sb2.toString());
    }

    /* access modifiers changed from: private */
    public final u getSafeParentFloatingLayout() {
        String str;
        if (getParent() instanceof u) {
            ViewParent parent = getParent();
            j.c(parent, "null cannot be cast to non-null type com.google.android.material.oneui.floatingactioncontainer.FloatingGroupLayout");
            return (u) parent;
        }
        StringBuilder sb2 = new StringBuilder("SeslProjectionView must have a FloatingGroupLayout as its parent, but found: ");
        ViewParent parent2 = getParent();
        if (parent2 != null) {
            str = parent2.getClass().getSimpleName();
        } else {
            str = null;
        }
        sb2.append(str);
        LogTagHelperKt.warn(this, sb2.toString());
        return null;
    }

    public final Rect c(k kVar, List list, Rect rect) {
        int i2;
        int i7;
        int[] iArr = new int[2];
        Iterator it = list.iterator();
        int i8 = Integer.MAX_VALUE;
        int i10 = Integer.MAX_VALUE;
        int i11 = Integer.MIN_VALUE;
        int i12 = Integer.MIN_VALUE;
        int i13 = Integer.MAX_VALUE;
        while (it.hasNext()) {
            View view = (View) it.next();
            view.getLocationOnScreen(iArr);
            i13 = Math.min(i13, iArr[0]);
            i12 = Math.max(i12, view.getWidth() + iArr[0]);
            i11 = Math.max(i11, view.getHeight() + iArr[1]);
            i10 = Math.min(i10, iArr[0]);
            i8 = Math.min(i8, iArr[1]);
        }
        int[] iArr2 = {i10, i8};
        int[] iArr3 = new int[2];
        u parentFloatingLayout = getParentFloatingLayout();
        parentFloatingLayout.getLocationOnScreen(iArr3);
        j.e(kVar, "<this>");
        Rect rect2 = this.f1886j;
        j.e(rect2, "rect");
        rect2.set(kVar.getLeft(), kVar.getTop(), kVar.getRight(), kVar.getBottom());
        int top = (iArr2[1] - iArr3[1]) - getTop();
        int i14 = (i11 - i8) + top;
        int i15 = top + rect.top;
        int i16 = i14 - rect.bottom;
        int paddingLeft = (i13 - iArr3[0]) - parentFloatingLayout.getPaddingLeft();
        if (getLayoutDirection() == 1) {
            i2 = rect.right;
        } else {
            i2 = rect.left;
        }
        int i17 = paddingLeft + i2;
        int paddingLeft2 = (i12 - iArr3[0]) - parentFloatingLayout.getPaddingLeft();
        if (getLayoutDirection() == 1) {
            i7 = rect.left;
        } else {
            i7 = rect.right;
        }
        return new Rect(i17, i15, paddingLeft2 + (-i7), i16);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void d(q2.C0267a r12, boolean r13) {
        /*
            r11 = this;
            q2.u r0 = r11.getParentFloatingLayout()
            q2.b r0 = r0.getFloatingAware$material_release()
            android.view.View r1 = r0.getReferenceView(r12)
            int[] r2 = q2.l.f1883a
            int r3 = r12.ordinal()
            r2 = r2[r3]
            r3 = 1
            if (r2 == r3) goto L_0x002a
            r4 = 2
            if (r2 == r4) goto L_0x0027
            r4 = 3
            if (r2 != r4) goto L_0x0021
            q2.k r2 = r11.e
        L_0x001f:
            r6 = r2
            goto L_0x002d
        L_0x0021:
            Dd.a r11 = new Dd.a
            r11.<init>()
            throw r11
        L_0x0027:
            q2.k r2 = r11.g
            goto L_0x001f
        L_0x002a:
            q2.k r2 = r11.f
            goto L_0x001f
        L_0x002d:
            java.util.List r2 = r0.getReferenceViews(r12)
            r10 = 0
            if (r2 == 0) goto L_0x0037
        L_0x0034:
            r7 = r2
            r1 = r10
            goto L_0x0041
        L_0x0037:
            if (r1 == 0) goto L_0x003e
            java.util.List r2 = o1.C0246a.e0(r1)
            goto L_0x0034
        L_0x003e:
            r2 = 0
            r7 = r2
            r1 = r3
        L_0x0041:
            java.util.ArrayList r2 = r11.d
            boolean r2 = r2.contains(r12)
            if (r2 != 0) goto L_0x010d
            if (r1 != 0) goto L_0x010d
            if (r7 == 0) goto L_0x010d
            android.graphics.Rect r9 = r0.getReferenceViewInset(r12)
            q2.u r12 = r11.getParentFloatingLayout()
            float r12 = r12.getScaleX()
            r0 = 1065353216(0x3f800000, float:1.0)
            int r12 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r12 != 0) goto L_0x0104
            android.graphics.Rect r12 = r11.f1886j
            boolean r0 = r12.isEmpty()
            if (r0 != 0) goto L_0x0073
            float r0 = r6.getAlpha()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x0071
            goto L_0x0073
        L_0x0071:
            r0 = r13
            goto L_0x0074
        L_0x0073:
            r0 = r10
        L_0x0074:
            android.graphics.Rect r2 = r11.c(r6, r7, r9)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "[FloatingItemBG Animation: anim:"
            r4.<init>(r5)
            r4.append(r13)
            java.lang.String r5 = " should:"
            r4.append(r5)
            r4.append(r0)
            java.lang.String r5 = " tag["
            r4.append(r5)
            java.lang.Object r5 = r6.getTag()
            r4.append(r5)
            java.lang.String r5 = "] hashCode{"
            r4.append(r5)
            int r5 = r6.hashCode()
            r4.append(r5)
            java.lang.String r5 = "} visible:"
            r4.append(r5)
            int r5 = r6.getVisibility()
            r4.append(r5)
            java.lang.String r5 = " alpha:"
            r4.append(r5)
            float r5 = r6.getAlpha()
            r4.append(r5)
            r5 = 32
            r4.append(r5)
            r4.append(r12)
            java.lang.String r12 = " -> "
            r4.append(r12)
            r4.append(r2)
            java.lang.String r12 = ", paddingRect:"
            r4.append(r12)
            java.lang.String r12 = r9.toShortString()
            r4.append(r12)
            r4.append(r5)
            android.view.ViewParent r12 = r6.getParent()
            android.view.ViewParent r12 = r12.getParent()
            r4.append(r12)
            java.lang.String r12 = r4.toString()
            androidx.core.oneui.common.internal.log.LogTagHelperKt.info(r11, r12)
            if (r0 == 0) goto L_0x00fc
            r6.a(r2)
            q2.m r4 = new q2.m
            r5 = r11
            r8 = r13
            r4.<init>(r5, r6, r7, r8, r9)
            r6.setOnResizeUpdate(r4)
            goto L_0x010e
        L_0x00fc:
            r8 = r13
            r6.setFinalPosition(r2)
            L1.d.v(r2, r6)
            goto L_0x010e
        L_0x0104:
            r5 = r11
            r8 = r13
            q2.u r11 = r5.getParentFloatingLayout()
            r11.f1898h = r3
            goto L_0x010e
        L_0x010d:
            r8 = r13
        L_0x010e:
            if (r1 == 0) goto L_0x0114
            r6.c(r10, r8)
            return
        L_0x0114:
            r6.c(r3, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q2.o.d(q2.a, boolean):void");
    }

    public final void e(float f5, boolean z) {
        int i2;
        int i7;
        long j2;
        ObjectAnimator objectAnimator = this.k;
        Object target = objectAnimator.getTarget();
        j.c(target, "null cannot be cast to non-null type android.view.View");
        if (((View) target).getAlpha() != f5 && f5 != this.m) {
            C0267a aVar = C0267a.START_FIRST;
            ArrayList arrayList = this.d;
            int i8 = 8;
            if (arrayList.contains(aVar)) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            this.f.setVisibility(i2);
            if (arrayList.contains(C0267a.START_SECOND)) {
                i7 = 8;
            } else {
                i7 = 0;
            }
            this.g.setVisibility(i7);
            if (!arrayList.contains(C0267a.END_FIRST)) {
                i8 = 0;
            }
            this.e.setVisibility(i8);
            if (z) {
                j2 = 0;
            } else {
                j2 = 150;
            }
            objectAnimator.setDuration(j2);
            Log.d("ProjectionView", "ProjectionBackgroundAnimation: to=" + f5 + ", duration=" + objectAnimator.getDuration() + ", isRunning=" + objectAnimator.isRunning());
            if (!objectAnimator.isRunning()) {
                this.m = f5;
                objectAnimator.setFloatValues(new float[]{getAlpha(), f5});
                objectAnimator.start();
            } else if (this.m != f5) {
                this.m = f5;
                objectAnimator.end();
                objectAnimator.setFloatValues(new float[]{getAlpha(), f5});
                objectAnimator.start();
            }
        }
    }

    public final void f(boolean z) {
        u parentFloatingLayout = getParentFloatingLayout();
        parentFloatingLayout.getViewTreeObserver().addOnPreDrawListener(new n(this, z, parentFloatingLayout));
    }

    public final int getDefaultBgId() {
        return this.f1884h;
    }

    public String getLogTag() {
        return "SeslProjectionView";
    }

    public /* bridge */ /* synthetic */ String getPrefix() {
        return "";
    }

    public final float getPrjBGAlphaFrom$material_release() {
        return this.l;
    }

    public final k getPrjBgEndFirstView() {
        return this.e;
    }

    public final k getPrjBgStartFirstView() {
        return this.f;
    }

    public final k getPrjBgStartSecondView() {
        return this.g;
    }

    public /* bridge */ /* synthetic */ String getVersion() {
        return "[sesl8-material:2.0.67]";
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f1885i = true;
        LogTagHelperKt.debug(this, "onConfigurationChanged " + this);
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        if (z && getAlpha() == 1.0f && getSafeParentFloatingLayout() != null) {
            f(true);
        }
    }

    public void setAlpha(float f5) {
        this.m = f5;
        super.setAlpha(f5);
    }

    public final void setElevation(Float f5) {
        float f8;
        if (f5 != null) {
            f8 = f5.floatValue();
        } else {
            f8 = getResources().getDimension(R.dimen.sesl_floating_toolbar_projection_background_elevation);
        }
        this.e.setElevation(f8);
        this.f.setElevation(f8);
        this.g.setElevation(f8);
    }

    public final void setPrjBGAlphaFrom$material_release(float f5) {
        this.l = f5;
    }
}
