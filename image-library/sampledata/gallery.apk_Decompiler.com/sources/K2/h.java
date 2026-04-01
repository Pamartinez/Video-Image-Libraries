package k2;

import Q2.a;
import X1.c;
import android.animation.AnimatorInflater;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$string;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SeslMenuItem;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import c0.C0086a;
import com.google.android.material.datepicker.m;
import com.sec.android.gallery3d.R;
import java.util.HashSet;
import kotlin.jvm.internal.j;
import l2.C0231d;
import x2.C0340g;
import x2.C0344k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class h extends ViewGroup implements MenuView {

    /* renamed from: c0  reason: collision with root package name */
    public static final int[] f1811c0 = {16842912};
    public static final int[] d0 = {-16842910};

    /* renamed from: A  reason: collision with root package name */
    public int f1812A;
    public C0344k B;

    /* renamed from: C  reason: collision with root package name */
    public ColorStateList f1813C;
    public int D;
    public m E;

    /* renamed from: F  reason: collision with root package name */
    public MenuBuilder f1814F;

    /* renamed from: G  reason: collision with root package name */
    public int f1815G;

    /* renamed from: H  reason: collision with root package name */
    public MenuBuilder.Callback f1816H;

    /* renamed from: I  reason: collision with root package name */
    public int f1817I;

    /* renamed from: J  reason: collision with root package name */
    public a f1818J;

    /* renamed from: K  reason: collision with root package name */
    public a f1819K;
    public d L;

    /* renamed from: M  reason: collision with root package name */
    public boolean f1820M;

    /* renamed from: N  reason: collision with root package name */
    public MenuBuilder f1821N;

    /* renamed from: O  reason: collision with root package name */
    public int f1822O;

    /* renamed from: P  reason: collision with root package name */
    public int f1823P;
    public int Q;
    public MenuBuilder R;
    public boolean S;
    public boolean T;
    public boolean U;
    public boolean V;

    /* renamed from: W  reason: collision with root package name */
    public C0231d f1824W;
    public final ContentResolver a0;
    public ColorDrawable b0;
    public final AutoTransition d;
    public final m e;
    public final Pools$SynchronizedPool f = new Pools$SynchronizedPool(5);
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public d[] f1825h;

    /* renamed from: i  reason: collision with root package name */
    public int f1826i;

    /* renamed from: j  reason: collision with root package name */
    public int f1827j;
    public ColorStateList k;
    public int l;
    public ColorStateList m;
    public final ColorStateList n;

    /* renamed from: o  reason: collision with root package name */
    public int f1828o;

    /* renamed from: p  reason: collision with root package name */
    public int f1829p;
    public Drawable q;
    public ColorStateList r;
    public int s;
    public final SparseArray t;
    public int u;
    public int v;
    public int w;

    /* renamed from: x  reason: collision with root package name */
    public boolean f1830x;
    public int y;
    public int z;

    public h(Context context) {
        super(context);
        new SparseArray(5);
        this.f1826i = 0;
        this.f1827j = 0;
        this.t = new SparseArray(5);
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.D = 0;
        this.f1817I = 1;
        this.f1818J = null;
        this.f1819K = null;
        this.L = null;
        this.f1820M = false;
        this.f1821N = null;
        this.f1822O = 0;
        this.f1823P = 0;
        this.Q = 0;
        this.T = true;
        this.U = true;
        this.V = false;
        this.n = c();
        if (isInEditMode()) {
            this.d = null;
        } else {
            AutoTransition autoTransition = new AutoTransition();
            this.d = autoTransition;
            autoTransition.setOrdering(0);
            autoTransition.setDuration(0);
            autoTransition.addTransition(new Transition());
        }
        this.e = new m(1, (c) this);
        this.a0 = context.getContentResolver();
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    private d getNewItem() {
        d dVar = (d) this.f.acquire();
        if (dVar == null) {
            return new d(getContext(), 1);
        }
        return dVar;
    }

    private void setBadgeIfNeeded(d dVar) {
        U1.a aVar;
        int id = dVar.getId();
        if (id != -1 && (aVar = (U1.a) this.t.get(id)) != null) {
            dVar.setBadge(aVar);
        }
    }

    private void setShowButtonShape(d dVar) {
        int i2;
        MenuItemImpl itemData;
        int i7;
        if (dVar != null) {
            ColorStateList itemTextColor = getItemTextColor();
            if (Settings.System.getInt(this.a0, "show_button_background", 0) == 1) {
                ColorDrawable colorDrawable = this.b0;
                if (colorDrawable != null) {
                    i2 = colorDrawable.getColor();
                } else {
                    i2 = 0;
                }
                if (i2 == 0) {
                    Resources resources = getResources();
                    if (SeslMisc.isLightTheme(getContext())) {
                        i7 = R.color.sesl_bottom_navigation_background_light;
                    } else {
                        i7 = R.color.sesl_bottom_navigation_background_dark;
                    }
                    i2 = resources.getColor(i7, (Resources.Theme) null);
                }
                Drawable drawable = dVar.getResources().getDrawable(R.drawable.sesl_bottom_nav_show_button_shapes_background);
                TextView textView = dVar.u;
                textView.setTextColor(i2);
                TextView textView2 = dVar.v;
                textView2.setTextColor(i2);
                textView.setBackground(drawable);
                textView2.setBackground(drawable);
                textView.setBackgroundTintList(itemTextColor);
                textView2.setBackgroundTintList(itemTextColor);
                if (this.L != null && (itemData = dVar.getItemData()) != null && this.R != null && itemData.getItemId() == this.R.getItem(0).getItemId()) {
                    h(i2, false);
                }
            }
        }
    }

    public final d a(MenuItemImpl menuItemImpl, boolean z3) {
        MenuItemImpl menuItemImpl2;
        h hVar;
        d dVar = (d) this.f.acquire();
        if (dVar == null) {
            int viewType = getViewType();
            hVar = this;
            menuItemImpl2 = menuItemImpl;
            dVar = new f(hVar, getContext(), viewType, menuItemImpl2, viewType);
        } else {
            hVar = this;
            menuItemImpl2 = menuItemImpl;
        }
        dVar.setIconTintList(hVar.k);
        dVar.setIconSize(hVar.l);
        dVar.setTextColor(hVar.n);
        int i2 = hVar.f1815G;
        dVar.f1802P = i2;
        dVar.Q = i2;
        TextView textView = dVar.u;
        TextViewCompat.setTextAppearance(textView, i2);
        float textSize = textView.getTextSize();
        TextView textView2 = dVar.v;
        dVar.a(textSize, textView2.getTextSize());
        dVar.e(textView2, dVar.f1802P);
        dVar.e(textView, dVar.Q);
        dVar.setTextAppearanceInactive(hVar.f1828o);
        dVar.setTextAppearanceActive(hVar.f1829p);
        dVar.setTextColor(hVar.m);
        Drawable drawable = hVar.q;
        if (drawable != null) {
            dVar.setItemBackground(drawable);
        } else {
            dVar.setItemBackground(hVar.s);
        }
        C0231d dVar2 = hVar.f1824W;
        if (dVar2 != null) {
            Resources resources = hVar.getResources();
            j.e(resources, "resources");
            dVar.setSelectedSidePadding(C0231d.b(dVar2.h(), resources));
        }
        hVar.e(dVar);
        dVar.setShifting(z3);
        dVar.setLabelVisibilityMode(hVar.g);
        dVar.initialize(menuItemImpl2, 0);
        dVar.setItemPosition(hVar.f1822O);
        return dVar;
    }

    public final void b() {
        boolean z3;
        boolean z7;
        int i2;
        CharSequence charSequence;
        int i7;
        removeAllViews();
        TransitionManager.beginDelayedTransition(this, this.d);
        d[] dVarArr = this.f1825h;
        d dVar = null;
        int i8 = 0;
        if (dVarArr != null && this.T) {
            for (d dVar2 : dVarArr) {
                if (dVar2 != null) {
                    g(dVar2.getId());
                    this.f.release(dVar2);
                    ImageView imageView = dVar2.s;
                    if (dVar2.L != null) {
                        if (imageView != null) {
                            dVar2.setClipChildren(true);
                            dVar2.setClipToPadding(true);
                            U1.a aVar = dVar2.L;
                            if (aVar != null) {
                                if (aVar.d() != null) {
                                    aVar.d().setForeground((Drawable) null);
                                } else {
                                    imageView.getOverlay().remove(aVar);
                                }
                            }
                        }
                        dVar2.L = null;
                    }
                    dVar2.y = null;
                    dVar2.E = 0.0f;
                    dVar2.e = false;
                }
            }
        }
        if (this.L != null) {
            g(R.id.bottom_overflow);
        }
        int size = this.f1814F.size();
        if (size == 0) {
            this.f1826i = 0;
            this.f1827j = 0;
            this.f1825h = null;
            this.f1822O = 0;
            this.L = null;
            this.f1821N = null;
            this.f1818J = null;
            this.f1819K = null;
            return;
        }
        HashSet hashSet = new HashSet();
        for (int i10 = 0; i10 < this.f1814F.size(); i10++) {
            hashSet.add(Integer.valueOf(this.f1814F.getItem(i10).getItemId()));
        }
        int i11 = 0;
        while (true) {
            SparseArray sparseArray = this.t;
            if (i11 >= sparseArray.size()) {
                break;
            }
            int keyAt = sparseArray.keyAt(i11);
            if (!hashSet.contains(Integer.valueOf(keyAt))) {
                sparseArray.delete(keyAt);
            }
            i11++;
        }
        int i12 = this.g;
        this.f1814F.getVisibleItems().size();
        if (i12 == 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.f1825h = new d[this.f1814F.size()];
        this.f1818J = new a(size);
        this.f1819K = new a(size);
        this.f1821N = new MenuBuilder(getContext());
        this.f1818J.f634a = 0;
        this.f1819K.f634a = 0;
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < size; i15++) {
            this.E.f = true;
            this.f1814F.getItem(i15).setCheckable(true);
            this.E.f = false;
            if (((MenuItemImpl) this.f1814F.getItem(i15)).requiresOverflow()) {
                a aVar2 = this.f1819K;
                int i16 = aVar2.f634a;
                aVar2.f634a = i16 + 1;
                ((int[]) aVar2.b)[i16] = i15;
                if (!this.f1814F.getItem(i15).isVisible()) {
                    i13++;
                }
            } else {
                a aVar3 = this.f1818J;
                int i17 = aVar3.f634a;
                aVar3.f634a = i17 + 1;
                ((int[]) aVar3.b)[i17] = i15;
                if (this.f1814F.getItem(i15).isVisible()) {
                    i14++;
                }
            }
        }
        if (this.f1819K.f634a - i13 > 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        this.f1820M = z7;
        int i18 = i14 + (z7 ? 1 : 0);
        int i19 = this.Q;
        if (i18 > i19) {
            int i20 = i18 - (i19 - 1);
            if (z7) {
                i20--;
            }
            for (int i21 = this.f1818J.f634a - 1; i21 >= 0; i21--) {
                if (!this.f1814F.getItem(((int[]) this.f1818J.b)[i21]).isVisible()) {
                    a aVar4 = this.f1819K;
                    int i22 = aVar4.f634a;
                    aVar4.f634a = i22 + 1;
                    a aVar5 = this.f1818J;
                    ((int[]) aVar4.b)[i22] = ((int[]) aVar5.b)[i21];
                    aVar5.f634a--;
                } else {
                    a aVar6 = this.f1819K;
                    int i23 = aVar6.f634a;
                    aVar6.f634a = i23 + 1;
                    a aVar7 = this.f1818J;
                    ((int[]) aVar6.b)[i23] = ((int[]) aVar7.b)[i21];
                    aVar7.f634a--;
                    i20--;
                    if (i20 == 0) {
                        break;
                    }
                }
            }
        }
        this.f1822O = 0;
        this.f1823P = 0;
        int i24 = 0;
        while (true) {
            a aVar8 = this.f1818J;
            if (i24 >= aVar8.f634a) {
                break;
            }
            int i25 = ((int[]) aVar8.b)[i24];
            if (this.f1825h != null) {
                if (i25 < 0 || i25 > this.f1814F.size() || !(this.f1814F.getItem(i25) instanceof MenuItemImpl)) {
                    StringBuilder o2 = C0086a.o(i25, "position is out of index (pos=", "/size=");
                    o2.append(this.f1814F.size());
                    o2.append(") or not instance of MenuItemImpl");
                    Log.e("h", o2.toString());
                } else {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) this.f1814F.getItem(i25);
                    d a7 = a(menuItemImpl, z3);
                    this.f1825h[this.f1822O] = a7;
                    if (this.f1814F.getItem(i25).isVisible()) {
                        i7 = 0;
                    } else {
                        i7 = 8;
                    }
                    a7.setVisibility(i7);
                    a7.setOnClickListener(this.e);
                    if (this.f1826i != 0 && this.f1814F.getItem(i25).getItemId() == this.f1826i) {
                        this.f1827j = this.f1822O;
                    }
                    String badgeText = menuItemImpl.getBadgeText();
                    if (badgeText != null) {
                        f(menuItemImpl.getItemId(), badgeText);
                    } else {
                        g(menuItemImpl.getItemId());
                    }
                    setBadgeIfNeeded(a7);
                    if (a7.getParent() instanceof ViewGroup) {
                        ((ViewGroup) a7.getParent()).removeView(a7);
                    }
                    addView(a7);
                    this.f1822O++;
                    if (a7.getVisibility() == 0) {
                        this.f1823P++;
                    }
                }
            }
            i24++;
        }
        if (this.f1819K.f634a > 0) {
            int i26 = 0;
            int i27 = 0;
            while (true) {
                a aVar9 = this.f1819K;
                i2 = aVar9.f634a;
                if (i26 >= i2) {
                    break;
                }
                MenuItemImpl menuItemImpl2 = (MenuItemImpl) this.f1814F.getItem(((int[]) aVar9.b)[i26]);
                if (menuItemImpl2 != null) {
                    if (menuItemImpl2.getTitle() == null) {
                        charSequence = menuItemImpl2.getContentDescription();
                    } else {
                        charSequence = menuItemImpl2.getTitle();
                    }
                    this.f1821N.add(menuItemImpl2.getGroupId(), menuItemImpl2.getItemId(), menuItemImpl2.getOrder(), charSequence).setVisible(menuItemImpl2.isVisible()).setEnabled(menuItemImpl2.isEnabled());
                    this.f1821N.setGroupDividerEnabled(this.S);
                    menuItemImpl2.setBadgeText(menuItemImpl2.getBadgeText());
                    if (!menuItemImpl2.isVisible()) {
                        i27++;
                    }
                }
                i26++;
            }
            if (i2 - i27 > 0) {
                this.f1820M = true;
                this.R = new MenuBuilder(getContext());
                new MenuInflater(getContext()).inflate(R.menu.nv_dummy_overflow_menu_icon, this.R);
                if (this.R.size() > 0 && (this.R.getItem(0) instanceof MenuItemImpl)) {
                    MenuItemImpl menuItemImpl3 = (MenuItemImpl) this.R.getItem(0);
                    if (getViewType() == 1) {
                        menuItemImpl3.setTooltipText((CharSequence) null);
                    } else {
                        menuItemImpl3.setTooltipText((CharSequence) getResources().getString(R$string.sesl_more_item_label));
                    }
                    dVar = a(menuItemImpl3, z3);
                    e(dVar);
                    dVar.setBadgeType(0);
                    dVar.setOnClickListener(new g(this));
                    dVar.setContentDescription(getResources().getString(R$string.sesl_action_menu_overflow_description));
                    if (getViewType() == 3) {
                        Drawable drawable = getContext().getDrawable(R$drawable.sesl_ic_menu_overflow_dark);
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(" ");
                        ImageSpan imageSpan = new ImageSpan(drawable);
                        drawable.setState(new int[]{16842910, -16842910});
                        drawable.setTintList(this.m);
                        drawable.setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_size), getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_size));
                        spannableStringBuilder.setSpan(imageSpan, 0, 1, 18);
                        dVar.setLabelImageSpan(spannableStringBuilder);
                    }
                    if (dVar.getParent() instanceof ViewGroup) {
                        ((ViewGroup) dVar.getParent()).removeView(dVar);
                    }
                    addView(dVar);
                }
                this.L = dVar;
                this.f1825h[this.f1818J.f634a] = dVar;
                this.f1822O++;
                this.f1823P++;
                dVar.setVisibility(0);
            }
        }
        if (this.f1823P > this.Q) {
            Log.i("h", "Maximum number of visible items supported by BottomNavigationView is " + this.Q + ". Current visible count is " + this.f1823P);
            int i28 = this.Q;
            this.f1822O = i28;
            this.f1823P = i28;
        }
        while (true) {
            d[] dVarArr2 = this.f1825h;
            if (i8 < dVarArr2.length) {
                setShowButtonShape(dVarArr2[i8]);
                i8++;
            } else {
                int min = Math.min(this.Q - 1, this.f1827j);
                this.f1827j = min;
                this.f1814F.getItem(min).setChecked(true);
                return;
            }
        }
    }

    public final ColorStateList c() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(16842808, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        int[] iArr = f1811c0;
        int[] iArr2 = ViewGroup.EMPTY_STATE_SET;
        int[] iArr3 = d0;
        return new ColorStateList(new int[][]{iArr3, iArr, iArr2}, new int[]{colorStateList.getColorForState(iArr3, defaultColor), i2, defaultColor});
    }

    public final d d(int i2) {
        if (i2 != -1) {
            d[] dVarArr = this.f1825h;
            if (dVarArr == null) {
                return null;
            }
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    return null;
                }
                if (dVar.getId() == i2) {
                    return dVar;
                }
            }
            return null;
        }
        throw new IllegalArgumentException(i2 + " is not a valid view id");
    }

    public final void e(d dVar) {
        if (this.D != 0) {
            dVar.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getContext(), this.D));
        }
    }

    public final void f(int i2, String str) {
        TextView textView;
        d d2 = d(i2);
        if (d2 != null) {
            View findViewById = d2.findViewById(R.id.notifications_badge_container);
            if (findViewById != null) {
                textView = (TextView) findViewById.findViewById(R.id.notifications_badge);
            } else {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sesl_navigation_bar_badge_layout, this, false);
                d2.addView(inflate);
                textView = (TextView) inflate.findViewById(R.id.notifications_badge);
            }
            if (str != null) {
                try {
                    Integer.parseInt(str);
                    if (Integer.parseInt(str) > 999) {
                        d2.setBadgeNumberless(true);
                        str = "999+";
                    } else {
                        d2.setBadgeNumberless(false);
                    }
                } catch (NumberFormatException unused) {
                }
            }
            d2.setBadgeNumberless(false);
        } else {
            textView = null;
        }
        if (textView != null) {
            textView.setText(str);
        }
        i(d2);
    }

    public final void g(int i2) {
        View findViewById;
        d d2 = d(i2);
        if (d2 != null && (findViewById = d2.findViewById(R.id.notifications_badge_container)) != null) {
            d2.removeView(findViewById);
        }
    }

    public int getActiveIndicatorLabelPadding() {
        return this.w;
    }

    public ColorDrawable getBackgroundColorDrawable() {
        return this.b0;
    }

    public SparseArray<U1.a> getBadgeDrawables() {
        return this.t;
    }

    public ColorStateList getIconTintList() {
        return this.k;
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.f1813C;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.f1830x;
    }

    public int getItemActiveIndicatorHeight() {
        return this.z;
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.f1812A;
    }

    public C0344k getItemActiveIndicatorShapeAppearance() {
        return this.B;
    }

    public int getItemActiveIndicatorWidth() {
        return this.y;
    }

    public Drawable getItemBackground() {
        d[] dVarArr = this.f1825h;
        if (dVarArr == null || dVarArr.length <= 0) {
            return this.q;
        }
        return dVarArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.s;
    }

    public int getItemIconSize() {
        return this.l;
    }

    public int getItemPaddingBottom() {
        return this.v;
    }

    public int getItemPaddingTop() {
        return this.u;
    }

    public ColorStateList getItemRippleColor() {
        return this.r;
    }

    public int getItemTextAppearanceActive() {
        return this.f1829p;
    }

    public int getItemTextAppearanceInactive() {
        return this.f1828o;
    }

    public ColorStateList getItemTextColor() {
        return this.m;
    }

    public int getLabelVisibilityMode() {
        return this.g;
    }

    public MenuBuilder getMenu() {
        return this.f1814F;
    }

    public MenuBuilder getOverflowMenu() {
        return this.f1821N;
    }

    public int getSelectedItemId() {
        return this.f1826i;
    }

    public int getSelectedItemPosition() {
        return this.f1827j;
    }

    public int getViewType() {
        return this.f1817I;
    }

    public int getViewVisibleItemCount() {
        return this.f1823P;
    }

    public int getVisibleItemCount() {
        return this.f1822O;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final void h(int i2, boolean z3) {
        SpannableStringBuilder labelImageSpan;
        d dVar = this.L;
        if (dVar != null && (labelImageSpan = dVar.getLabelImageSpan()) != null) {
            Drawable drawable = getContext().getDrawable(R$drawable.sesl_ic_menu_overflow_dark);
            ImageSpan[] imageSpanArr = (ImageSpan[]) labelImageSpan.getSpans(0, labelImageSpan.length(), ImageSpan.class);
            if (imageSpanArr != null) {
                for (ImageSpan removeSpan : imageSpanArr) {
                    labelImageSpan.removeSpan(removeSpan);
                }
            }
            ImageSpan imageSpan = new ImageSpan(drawable);
            drawable.setState(new int[]{16842910, -16842910});
            if (z3) {
                drawable.setTintList(this.m);
            } else {
                drawable.setTint(i2);
            }
            drawable.setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_size), getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_size));
            labelImageSpan.setSpan(imageSpan, 0, 1, 18);
            this.L.setLabelImageSpan(labelImageSpan);
        }
    }

    public final void i(d dVar) {
        TextView textView;
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        if (dVar != null && (textView = (TextView) dVar.findViewById(R.id.notifications_badge)) != null) {
            Resources resources = getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sesl_navigation_bar_num_badge_size);
            float f5 = getResources().getConfiguration().fontScale;
            if (f5 > 1.2f) {
                textView.setTextSize(0, (((float) dimensionPixelSize) / f5) * 1.2f);
            }
            int badgeType = dVar.getBadgeType();
            int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.sesl_bottom_navigation_dot_badge_size);
            if (this.f1822O == this.Q) {
                i2 = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_mode_min_padding_horizontal);
            } else {
                i2 = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_mode_padding_horizontal);
            }
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_N_badge_top_margin);
            int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_N_badge_start_margin);
            TextView label = dVar.getLabel();
            if (label == null) {
                i7 = 1;
            } else {
                i7 = label.getWidth();
            }
            if (label == null) {
                i8 = 1;
            } else {
                i8 = label.getHeight();
            }
            if (badgeType == 1 || badgeType == 0) {
                ViewCompat.setBackground(textView, resources.getDrawable(R.drawable.sesl_dot_badge));
                i11 = dimensionPixelOffset;
                i10 = i11;
            } else {
                ViewCompat.setBackground(textView, resources.getDrawable(R.drawable.sesl_tab_n_badge));
                textView.measure(0, 0);
                i11 = textView.getMeasuredWidth();
                i10 = textView.getMeasuredHeight();
            }
            if (getViewType() != 3) {
                if (badgeType == 1) {
                    i12 = getItemIconSize() / 2;
                } else {
                    i12 = (textView.getMeasuredWidth() / 2) - i2;
                    dimensionPixelOffset /= 2;
                }
            } else if (badgeType == 1) {
                i12 = (textView.getMeasuredWidth() + i7) / 2;
                dimensionPixelOffset = (dVar.getHeight() - i8) / 2;
            } else if (badgeType == 0) {
                i12 = ((i7 - textView.getMeasuredWidth()) - dimensionPixelSize3) / 2;
                dimensionPixelOffset = ((dVar.getHeight() - i8) / 2) - dimensionPixelSize2;
            } else {
                i12 = (textView.getMeasuredWidth() + i7) / 2;
                dimensionPixelOffset = ((dVar.getHeight() - i8) / 2) - dimensionPixelSize2;
                if ((textView.getMeasuredWidth() / 2) + (dVar.getWidth() / 2) + i12 > dVar.getWidth()) {
                    i12 += dVar.getWidth() - ((textView.getMeasuredWidth() / 2) + ((dVar.getWidth() / 2) + i12));
                }
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textView.getLayoutParams();
            int i13 = layoutParams.width;
            int i14 = layoutParams.leftMargin;
            if (i13 != i11 || i14 != i12) {
                layoutParams.width = i11;
                layoutParams.height = i10;
                layoutParams.topMargin = dimensionPixelOffset;
                layoutParams.setMarginStart(i12);
                textView.setLayoutParams(layoutParams);
            }
        }
    }

    public final void initialize(MenuBuilder menuBuilder) {
        this.f1814F = menuBuilder;
    }

    public final void j() {
        boolean z3;
        MenuBuilder menuBuilder;
        boolean z7;
        AutoTransition autoTransition;
        m mVar;
        j jVar;
        MenuBuilder menuBuilder2 = this.f1814F;
        if (menuBuilder2 != null && this.f1825h != null && this.f1818J != null && this.f1819K != null) {
            int size = menuBuilder2.size();
            if (this.f1820M && (mVar = this.E) != null && (jVar = mVar.k) != null && jVar.isShowing()) {
                this.E.hideOverflowMenu();
            }
            if (size != this.f1818J.f634a + this.f1819K.f634a) {
                b();
                return;
            }
            int i2 = this.f1826i;
            int i7 = 0;
            while (true) {
                a aVar = this.f1818J;
                if (i7 >= aVar.f634a) {
                    break;
                }
                MenuItem item = this.f1814F.getItem(((int[]) aVar.b)[i7]);
                if (item.isChecked()) {
                    this.f1826i = item.getItemId();
                    this.f1827j = i7;
                }
                if (item instanceof SeslMenuItem) {
                    SeslMenuItem seslMenuItem = (SeslMenuItem) item;
                    g(item.getItemId());
                    if (seslMenuItem.getBadgeText() != null) {
                        f(item.getItemId(), seslMenuItem.getBadgeText());
                    }
                }
                i7++;
            }
            if (!(i2 == this.f1826i || (autoTransition = this.d) == null)) {
                TransitionManager.beginDelayedTransition(this, autoTransition);
            }
            int i8 = this.g;
            this.f1814F.getVisibleItems().size();
            if (i8 == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            for (int i10 = 0; i10 < this.f1818J.f634a; i10++) {
                this.E.f = true;
                this.f1825h[i10].setLabelVisibilityMode(this.g);
                this.f1825h[i10].setShifting(z3);
                this.f1825h[i10].initialize((MenuItemImpl) this.f1814F.getItem(((int[]) this.f1818J.b)[i10]), 0);
                this.E.f = false;
            }
            int i11 = 0;
            boolean z9 = false;
            while (true) {
                a aVar2 = this.f1819K;
                if (i11 >= aVar2.f634a) {
                    break;
                }
                MenuItem item2 = this.f1814F.getItem(((int[]) aVar2.b)[i11]);
                if ((item2 instanceof SeslMenuItem) && (menuBuilder = this.f1821N) != null) {
                    SeslMenuItem seslMenuItem2 = (SeslMenuItem) item2;
                    MenuItem findItem = menuBuilder.findItem(item2.getItemId());
                    if (findItem instanceof SeslMenuItem) {
                        findItem.setTitle(item2.getTitle());
                        ((SeslMenuItem) findItem).setBadgeText(seslMenuItem2.getBadgeText());
                    }
                    if (seslMenuItem2.getBadgeText() != null) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    z9 |= z7;
                }
                i11++;
            }
            if (z9) {
                f(R.id.bottom_overflow, "");
            } else {
                g(R.id.bottom_overflow);
            }
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        m mVar;
        j jVar;
        super.onConfigurationChanged(configuration);
        if (getViewType() != 3) {
            setItemIconSize(getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_size));
            d[] dVarArr = this.f1825h;
            if (dVarArr != null) {
                for (d dVar : dVarArr) {
                    if (dVar == null) {
                        break;
                    }
                    int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_size);
                    ViewGroup viewGroup = dVar.t;
                    if (viewGroup != null) {
                        dVar.k = dVar.getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_inset);
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
                        if (marginLayoutParams != null) {
                            marginLayoutParams.topMargin = dimensionPixelSize + dVar.k;
                            viewGroup.setLayoutParams(marginLayoutParams);
                        }
                    }
                }
            }
        }
        if (this.f1820M && (mVar = this.E) != null && (jVar = mVar.k) != null && jVar.isShowing()) {
            this.E.hideOverflowMenu();
        }
    }

    public void setActiveIndicatorLabelPadding(int i2) {
        this.w = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d activeIndicatorLabelPadding : dVarArr) {
                activeIndicatorLabelPadding.setActiveIndicatorLabelPadding(i2);
            }
        }
    }

    public void setBackgroundColorDrawable(ColorDrawable colorDrawable) {
        this.b0 = colorDrawable;
    }

    public void setExclusiveCheckable(boolean z3) {
        this.U = z3;
    }

    public void setGroupDividerEnabled(boolean z3) {
        this.S = z3;
        MenuBuilder menuBuilder = this.f1821N;
        if (menuBuilder != null) {
            menuBuilder.setGroupDividerEnabled(z3);
        } else {
            j();
        }
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.k = colorStateList;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    break;
                }
                dVar.setIconTintList(colorStateList);
            }
        }
        d dVar2 = this.L;
        if (dVar2 != null) {
            dVar2.setIconTintList(colorStateList);
        }
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        C0340g gVar;
        this.f1813C = colorStateList;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (this.B == null || this.f1813C == null) {
                    gVar = null;
                } else {
                    gVar = new C0340g(this.B);
                    gVar.k(this.f1813C);
                }
                dVar.setActiveIndicatorDrawable(gVar);
            }
        }
    }

    public void setItemActiveIndicatorEnabled(boolean z3) {
        this.f1830x = z3;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d activeIndicatorEnabled : dVarArr) {
                activeIndicatorEnabled.setActiveIndicatorEnabled(z3);
            }
        }
    }

    public void setItemActiveIndicatorHeight(int i2) {
        this.z = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d activeIndicatorHeight : dVarArr) {
                activeIndicatorHeight.setActiveIndicatorHeight(i2);
            }
        }
    }

    public void setItemActiveIndicatorMarginHorizontal(int i2) {
        this.f1812A = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d activeIndicatorMarginHorizontal : dVarArr) {
                activeIndicatorMarginHorizontal.setActiveIndicatorMarginHorizontal(i2);
            }
        }
    }

    public void setItemActiveIndicatorResizeable(boolean z3) {
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d activeIndicatorResizeable : dVarArr) {
                activeIndicatorResizeable.setActiveIndicatorResizeable(z3);
            }
        }
    }

    public void setItemActiveIndicatorShapeAppearance(C0344k kVar) {
        C0340g gVar;
        this.B = kVar;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (this.B == null || this.f1813C == null) {
                    gVar = null;
                } else {
                    gVar = new C0340g(this.B);
                    gVar.k(this.f1813C);
                }
                dVar.setActiveIndicatorDrawable(gVar);
            }
        }
    }

    public void setItemActiveIndicatorWidth(int i2) {
        this.y = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d activeIndicatorWidth : dVarArr) {
                activeIndicatorWidth.setActiveIndicatorWidth(i2);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.q = drawable;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    break;
                }
                dVar.setItemBackground(drawable);
            }
        }
        d dVar2 = this.L;
        if (dVar2 != null) {
            dVar2.setItemBackground(drawable);
        }
    }

    public void setItemBackgroundRes(int i2) {
        this.s = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    break;
                }
                dVar.setItemBackground(i2);
            }
        }
        d dVar2 = this.L;
        if (dVar2 != null) {
            dVar2.setItemBackground(i2);
        }
    }

    public void setItemIconSize(int i2) {
        this.l = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    break;
                }
                dVar.setIconSize(i2);
            }
        }
        d dVar2 = this.L;
        if (dVar2 != null) {
            dVar2.setIconSize(i2);
        }
    }

    public void setItemPaddingBottom(int i2) {
        this.v = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d itemPaddingBottom : dVarArr) {
                itemPaddingBottom.setItemPaddingBottom(i2);
            }
        }
    }

    public void setItemPaddingTop(int i2) {
        this.u = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d itemPaddingTop : dVarArr) {
                itemPaddingTop.setItemPaddingTop(i2);
            }
        }
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.r = colorStateList;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d itemRippleColor : dVarArr) {
                itemRippleColor.setItemRippleColor(colorStateList);
            }
        }
    }

    public void setItemStateListAnimator(int i2) {
        this.D = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    break;
                }
                e(dVar);
            }
        }
        d dVar2 = this.L;
        if (dVar2 != null) {
            e(dVar2);
        }
    }

    public void setItemTextAppearanceActive(int i2) {
        this.f1829p = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    break;
                }
                dVar.setTextAppearanceActive(i2);
                ColorStateList colorStateList = this.m;
                if (colorStateList != null) {
                    dVar.setTextColor(colorStateList);
                }
            }
        }
        d dVar2 = this.L;
        if (dVar2 != null && this.m != null) {
            dVar2.setTextAppearanceActive(i2);
            this.L.setTextColor(this.m);
        }
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z3) {
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d textAppearanceActiveBoldEnabled : dVarArr) {
                textAppearanceActiveBoldEnabled.setTextAppearanceActiveBoldEnabled(z3);
            }
        }
    }

    public void setItemTextAppearanceInactive(int i2) {
        this.f1828o = i2;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    break;
                }
                dVar.setTextAppearanceInactive(i2);
                ColorStateList colorStateList = this.m;
                if (colorStateList != null) {
                    dVar.setTextColor(colorStateList);
                }
            }
        }
        d dVar2 = this.L;
        if (dVar2 != null) {
            dVar2.setTextAppearanceInactive(i2);
            ColorStateList colorStateList2 = this.m;
            if (colorStateList2 != null) {
                this.L.setTextColor(colorStateList2);
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.m = colorStateList;
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            for (d dVar : dVarArr) {
                if (dVar == null) {
                    break;
                }
                dVar.setTextColor(colorStateList);
            }
        }
        d dVar2 = this.L;
        if (dVar2 != null) {
            dVar2.setTextColor(colorStateList);
            h(0, true);
        }
        if (Settings.System.getInt(this.a0, "show_button_background", 0) == 1) {
            this.E.updateMenuView(true);
        }
    }

    public void setLabelVisibilityMode(int i2) {
        this.g = i2;
    }

    public void setMaxItemCount(int i2) {
        this.Q = i2;
    }

    public void setOverflowSelectedCallback(MenuBuilder.Callback callback) {
        this.f1816H = callback;
    }

    public void setPresenter(m mVar) {
        this.E = mVar;
    }

    public void setViewType(int i2) {
        this.f1817I = i2;
    }
}
