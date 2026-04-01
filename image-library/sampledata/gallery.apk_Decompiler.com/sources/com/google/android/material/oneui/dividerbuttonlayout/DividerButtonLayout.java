package com.google.android.material.oneui.dividerbuttonlayout;

import Ad.f;
import Ba.g;
import Sf.k;
import Sf.n;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.oneui.common.BlurSupportable;
import androidx.appcompat.oneui.common.internal.policy.BlurInfoState;
import androidx.appcompat.oneui.common.internal.resource.OpenThemeResourceColorRes;
import androidx.appcompat.oneui.common.internal.resource.ThemeResourceColorRes;
import androidx.appcompat.oneui.common.internal.semblurinfo.SemBlurInfoStateBuilder;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import androidx.core.oneui.common.internal.semblurinfo.SemBlurInfoState;
import androidx.core.view.ViewGroupKt;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import m2.a;
import me.m;
import ne.C1195m;
import p2.C0260a;
import p2.C0261b;
import p2.C0262c;
import p2.C0264e;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0003\u0006\f\u000eJ\u0015\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\u0004\b\r\u0010\bJ\u0015\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0018\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u001b\u0010\"\u001a\u00020\u001d8BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u001b\u0010'\u001a\u00020#8BX\u0002¢\u0006\f\n\u0004\b$\u0010\u001f\u001a\u0004\b%\u0010&R\u0014\u0010+\u001a\u00020(8VX\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006,"}, d2 = {"Lcom/google/android/material/oneui/dividerbuttonlayout/DividerButtonLayout;", "Landroid/widget/LinearLayout;", "Landroidx/appcompat/view/menu/MenuView;", "Landroidx/appcompat/oneui/common/BlurSupportable;", "Lm2/a;", "", "Lp2/a;", "getDividers", "()Ljava/util/List;", "Landroid/view/Menu;", "getMenu", "()Landroid/view/Menu;", "Lp2/b;", "getDividerButtons", "Lp2/c;", "listener", "Lme/x;", "setOnMenuItemClickListener", "(Lp2/c;)V", "", "getWindowAnimations", "()I", "Landroid/graphics/drawable/Drawable;", "background", "setBackground", "(Landroid/graphics/drawable/Drawable;)V", "semBlurInfoMode", "setBlurMode", "(I)V", "Landroidx/appcompat/view/SupportMenuInflater;", "h", "Lme/f;", "getMenuInflater", "()Landroidx/appcompat/view/SupportMenuInflater;", "menuInflater", "Lp2/e;", "i", "getPresenter", "()Lp2/e;", "presenter", "", "getLogTag", "()Ljava/lang/String;", "logTag", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DividerButtonLayout extends LinearLayout implements MenuView, BlurSupportable, a {
    public static final /* synthetic */ int l = 0;
    public final boolean d;
    public int e;
    public SemBlurInfoState f;
    public Drawable g;

    /* renamed from: h  reason: collision with root package name */
    public final m f1477h;

    /* renamed from: i  reason: collision with root package name */
    public final m f1478i;

    /* renamed from: j  reason: collision with root package name */
    public final MenuBuilder f1479j;
    public C0262c k;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DividerButtonLayout(android.content.Context r8, android.util.AttributeSet r9) {
        /*
            r7 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.j.e(r8, r0)
            boolean r0 = androidx.appcompat.util.SeslMisc.isLightTheme(r8)
            if (r0 == 0) goto L_0x000f
            r0 = 2131952929(0x7f130521, float:1.9542315E38)
            goto L_0x0012
        L_0x000f:
            r0 = 2131952926(0x7f13051e, float:1.9542309E38)
        L_0x0012:
            r4 = 0
            r7.<init>(r8, r9, r4, r0)
            r0 = 2
            r7.e = r0
            gg.d r0 = new gg.d
            r1 = 3
            r0.<init>(r1, r8)
            me.m r0 = L1.d.q(r0)
            r7.f1477h = r0
            p2.d r0 = p2.C0263d.d
            me.m r0 = L1.d.q(r0)
            r7.f1478i = r0
            androidx.appcompat.view.menu.MenuBuilder r0 = new androidx.appcompat.view.menu.MenuBuilder
            r0.<init>(r8)
            p2.e r1 = r7.getPresenter()
            r1.getClass()
            r1.d = r7
            p2.e r1 = r7.getPresenter()
            r0.addMenuPresenter(r1)
            r7.f1479j = r0
            r0 = 0
            int[] r6 = new int[r0]
            r5 = 0
            h2.p.a(r8, r9, r4, r5)
            int[] r3 = Q1.a.n
            r1 = r8
            r2 = r9
            h2.p.b(r1, r2, r3, r4, r5, r6)
            android.content.res.TypedArray r8 = r1.obtainStyledAttributes(r2, r3, r4, r5)
            java.lang.String r9 = "obtainStyledAttributes(\n…tyleAttr, 0\n            )"
            kotlin.jvm.internal.j.d(r8, r9)
            boolean r9 = r8.hasValue(r0)
            if (r9 == 0) goto L_0x0068
            r9 = 1
            boolean r9 = r8.getBoolean(r0, r9)
            r7.d = r9
        L_0x0068:
            boolean r9 = androidx.appcompat.util.SeslMisc.isDefaultTheme(r1)
            if (r9 != 0) goto L_0x0074
            r9 = 2131232441(0x7f0806b9, float:1.8080991E38)
            r7.setBackgroundResource(r9)
        L_0x0074:
            boolean r9 = r7.d
            if (r9 == 0) goto L_0x007b
            r7.a(r1)
        L_0x007b:
            r8.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private final List<C0260a> getDividers() {
        k children = ViewGroupKt.getChildren(this);
        j.e(children, "<this>");
        return n.v0(n.p0(children, new f(2, (Object) C0260a.class)));
    }

    private final SupportMenuInflater getMenuInflater() {
        return (SupportMenuInflater) this.f1477h.getValue();
    }

    private final C0264e getPresenter() {
        return (C0264e) this.f1478i.getValue();
    }

    public final boolean a(Context context) {
        if (Build.VERSION.SDK_INT < 35) {
            return false;
        }
        clearBlurInfo(context);
        float dimension = context.getResources().getDimension(R.dimen.sesl_divider_button_layout_background_radius);
        SemBlurInfoStateBuilder generateFloatingComponentBlurInfoStateBuilder = BlurInfoState.INSTANCE.generateFloatingComponentBlurInfoStateBuilder(context, this.e);
        Drawable drawable = this.g;
        if (drawable != null) {
            generateFloatingComponentBlurInfoStateBuilder.nonBlurBackground(drawable);
        }
        generateFloatingComponentBlurInfoStateBuilder.cornerRadius(dimension);
        SemBlurInfoState build = generateFloatingComponentBlurInfoStateBuilder.build();
        this.f = build;
        return build.applyBlurInfo(this);
    }

    public final void b() {
        removeAllViews();
        ArrayList<MenuItemImpl> visibleItems = this.f1479j.getVisibleItems();
        j.d(visibleItems, "menuBuilder.visibleItems");
        for (MenuItemImpl menuItemImpl : visibleItems) {
            Context context = getContext();
            j.d(context, "context");
            j.d(menuItemImpl, "menuItem");
            C0261b bVar = new C0261b(context);
            bVar.a(menuItemImpl);
            bVar.setOnClickListener(new g(25, this, menuItemImpl));
            if (getChildCount() > 0) {
                Context context2 = getContext();
                j.d(context2, "context");
                View view = new View(context2, (AttributeSet) null, 0);
                view.setBackgroundResource(new OpenThemeResourceColorRes(new ThemeResourceColorRes(R.color.sesl_divider_button_layout_divider_light, R.color.sesl_divider_button_layout_divider_dark), new ThemeResourceColorRes(R.color.sesl_divider_button_layout_divider_for_theme, 0, 2, (e) null)).getResource(context2).intValue());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                layoutParams.width = view.getResources().getDimensionPixelSize(R.dimen.sesl_divider_button_layout_divider_width);
                layoutParams.height = view.getResources().getDimensionPixelSize(R.dimen.sesl_divider_button_layout_divider_height);
                layoutParams.gravity = 17;
                view.setLayoutParams(layoutParams);
                addView(view);
            }
            addView(bVar, new LinearLayout.LayoutParams(-2, -1));
        }
    }

    public final void c(int i2) {
        getPresenter().e = true;
        getMenuInflater().inflate(i2, this.f1479j);
        getPresenter().e = false;
        getPresenter().updateMenuView(true);
    }

    public final void clearBlurInfo(Context context) {
        j.e(context, "context");
        SemBlurInfoState semBlurInfoState = this.f;
        if (semBlurInfoState != null) {
            semBlurInfoState.clearBlurInfo(this);
        }
        this.f = null;
    }

    public final void d() {
        getPresenter().e = true;
        List<C0261b> dividerButtons = getDividerButtons();
        MenuBuilder menuBuilder = this.f1479j;
        if (menuBuilder.getVisibleItems().size() != dividerButtons.size()) {
            LogTagHelperKt.debug(this, "updateMenuView size changed(" + menuBuilder.getVisibleItems().size() + " -> " + dividerButtons.size() + ')');
            b();
            return;
        }
        ArrayList<MenuItemImpl> visibleItems = menuBuilder.getVisibleItems();
        j.d(visibleItems, "menuBuilder.visibleItems");
        int i2 = 0;
        for (T next : visibleItems) {
            int i7 = i2 + 1;
            if (i2 >= 0) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) next;
                j.d(menuItemImpl, "menuItem");
                dividerButtons.get(i2).a(menuItemImpl);
                i2 = i7;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        getPresenter().e = false;
    }

    public final List<C0261b> getDividerButtons() {
        k children = ViewGroupKt.getChildren(this);
        j.e(children, "<this>");
        return n.v0(n.p0(children, new f(2, (Object) C0261b.class)));
    }

    public String getLogTag() {
        return "DividerButtonLayout";
    }

    public final Menu getMenu() {
        return this.f1479j;
    }

    public /* bridge */ /* synthetic */ String getPrefix() {
        return "";
    }

    public /* bridge */ /* synthetic */ String getVersion() {
        return "[sesl8-material:2.0.67]";
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final void initialize(MenuBuilder menuBuilder) {
        j.e(menuBuilder, "menu");
    }

    public final void onMeasure(int i2, int i7) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_divider_button_layout_button_height);
        int mode = View.MeasureSpec.getMode(i7);
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                i7 = View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + getPaddingTop() + dimensionPixelSize, 1073741824);
            }
        } else if (View.MeasureSpec.getSize(i7) >= dimensionPixelSize) {
            setMinimumHeight(dimensionPixelSize);
        }
        super.onMeasure(i2, i7);
    }

    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
        this.g = drawable;
    }

    public void setBlurMode(int i2) {
        this.e = i2;
        Context context = getContext();
        j.d(context, "context");
        a(context);
    }

    public final void setOnMenuItemClickListener(C0262c cVar) {
        j.e(cVar, "listener");
        this.k = cVar;
    }
}
