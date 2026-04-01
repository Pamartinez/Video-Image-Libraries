package k2;

import U1.a;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.google.android.material.datepicker.g;
import com.sec.android.gallery3d.R;
import og.k;
import t1.i;
import v2.C0299a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class d extends FrameLayout implements MenuView.ItemView {
    public static final int[] T = {16842912};
    public static final i U = new Object();
    public static final c V = new Object();

    /* renamed from: A  reason: collision with root package name */
    public Drawable f1791A;
    public Drawable B;

    /* renamed from: C  reason: collision with root package name */
    public ValueAnimator f1792C;
    public i D = U;
    public float E = 0.0f;

    /* renamed from: F  reason: collision with root package name */
    public boolean f1793F = false;

    /* renamed from: G  reason: collision with root package name */
    public int f1794G = 0;

    /* renamed from: H  reason: collision with root package name */
    public int f1795H = 0;

    /* renamed from: I  reason: collision with root package name */
    public boolean f1796I = false;

    /* renamed from: J  reason: collision with root package name */
    public int f1797J = 0;

    /* renamed from: K  reason: collision with root package name */
    public int f1798K = 0;
    public a L;

    /* renamed from: M  reason: collision with root package name */
    public int f1799M = 1;

    /* renamed from: N  reason: collision with root package name */
    public SpannableStringBuilder f1800N;

    /* renamed from: O  reason: collision with root package name */
    public final int f1801O;

    /* renamed from: P  reason: collision with root package name */
    public int f1802P;
    public int Q;
    public boolean R;
    public boolean S;
    public final String d = d.class.getSimpleName();
    public boolean e = false;
    public ColorStateList f;
    public Drawable g;

    /* renamed from: h  reason: collision with root package name */
    public int f1803h;

    /* renamed from: i  reason: collision with root package name */
    public int f1804i;

    /* renamed from: j  reason: collision with root package name */
    public int f1805j;
    public int k;
    public float l;
    public float m;
    public float n;

    /* renamed from: o  reason: collision with root package name */
    public int f1806o;

    /* renamed from: p  reason: collision with root package name */
    public boolean f1807p;
    public final FrameLayout q;
    public final View r;
    public final ImageView s;
    public final ViewGroup t;
    public final TextView u;
    public final TextView v;
    public int w = -1;

    /* renamed from: x  reason: collision with root package name */
    public int f1808x = 0;
    public MenuItemImpl y;
    public ColorStateList z;

    public d(Context context, int i2) {
        super(context, (AttributeSet) null, 0);
        this.f1801O = i2;
        LayoutInflater.from(context).inflate(getItemLayoutResId(), this, true);
        this.q = (FrameLayout) findViewById(R.id.navigation_bar_item_icon_container);
        this.r = findViewById(R.id.navigation_bar_item_active_indicator_view);
        ImageView imageView = (ImageView) findViewById(R.id.navigation_bar_item_icon_view);
        this.s = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.navigation_bar_item_labels_group);
        this.t = viewGroup;
        TextView textView = (TextView) findViewById(R.id.navigation_bar_item_small_label_view);
        this.u = textView;
        TextView textView2 = (TextView) findViewById(R.id.navigation_bar_item_large_label_view);
        this.v = textView2;
        CheckBox checkBox = (CheckBox) findViewById(R.id.navigation_bar_item_checkbox_view);
        setBackgroundResource(getItemBackgroundResId());
        this.f1803h = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.f1804i = viewGroup.getPaddingBottom();
        this.f1805j = getResources().getDimensionPixelSize(R.dimen.m3_navigation_item_active_indicator_label_padding);
        ViewCompat.setImportantForAccessibility(textView, 2);
        ViewCompat.setImportantForAccessibility(textView2, 2);
        setFocusable(true);
        a(textView.getTextSize(), textView2.getTextSize());
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new C0223a(this));
        }
        ViewCompat.setAccessibilityDelegate(this, (AccessibilityDelegateCompat) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void f(android.widget.TextView r4, int r5) {
        /*
            androidx.core.widget.TextViewCompat.setTextAppearance(r4, r5)
            android.content.Context r0 = r4.getContext()
            r1 = 0
            if (r5 != 0) goto L_0x000b
            goto L_0x0021
        L_0x000b:
            int[] r2 = androidx.appcompat.R$styleable.TextAppearance
            android.content.res.TypedArray r5 = r0.obtainStyledAttributes(r5, r2)
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            int r3 = androidx.appcompat.R$styleable.TextAppearance_android_textSize
            boolean r3 = r5.getValue(r3, r2)
            r5.recycle()
            if (r3 != 0) goto L_0x0023
        L_0x0021:
            r5 = r1
            goto L_0x004e
        L_0x0023:
            int r5 = r2.getComplexUnit()
            r3 = 2
            if (r5 != r3) goto L_0x0040
            int r5 = r2.data
            float r5 = android.util.TypedValue.complexToFloat(r5)
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            float r0 = r0.density
            float r5 = r5 * r0
            int r5 = java.lang.Math.round(r5)
            goto L_0x004e
        L_0x0040:
            int r5 = r2.data
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            int r5 = android.util.TypedValue.complexToDimensionPixelSize(r5, r0)
        L_0x004e:
            if (r5 == 0) goto L_0x0054
            float r5 = (float) r5
            r4.setTextSize(r1, r5)
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: k2.d.f(android.widget.TextView, int):void");
    }

    public static void g(View view, float f5, float f8, int i2) {
        view.setScaleX(f5);
        view.setScaleY(f8);
        view.setVisibility(i2);
    }

    private View getIconOrContainer() {
        FrameLayout frameLayout = this.q;
        if (frameLayout != null) {
            return frameLayout;
        }
        return this.s;
    }

    private int getItemVisiblePosition() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        int indexOfChild = viewGroup.indexOfChild(this);
        int i2 = 0;
        for (int i7 = 0; i7 < indexOfChild; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            if ((childAt instanceof d) && childAt.getVisibility() == 0) {
                i2++;
            }
        }
        return i2;
    }

    private int getSuggestedIconHeight() {
        return getIconOrContainer().getMeasuredHeight() + ((FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams()).topMargin;
    }

    private int getSuggestedIconWidth() {
        int i2;
        a aVar = this.L;
        if (aVar == null) {
            i2 = 0;
        } else {
            i2 = aVar.getMinimumWidth() - this.L.f840h.b.z.intValue();
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        int max = Math.max(i2, layoutParams.leftMargin);
        return Math.max(i2, layoutParams.rightMargin) + this.s.getMeasuredWidth() + max;
    }

    public static void h(int i2, int i7, View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i2;
        layoutParams.bottomMargin = i2;
        layoutParams.gravity = i7;
        view.setLayoutParams(layoutParams);
    }

    public static void j(int i2, View view) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i2);
    }

    public final void a(float f5, float f8) {
        int i2 = (f8 > 0.0f ? 1 : (f8 == 0.0f ? 0 : -1));
        String str = this.d;
        if (i2 == 0 || f5 == 0.0f) {
            Log.e(str, "LabelSize is invalid");
            this.m = 1.0f;
            this.n = 1.0f;
            this.l = 0.0f;
            return;
        }
        this.l = f5 - f8;
        float f10 = (f8 * 1.0f) / f5;
        this.m = f10;
        this.n = (f5 * 1.0f) / f8;
        if (f10 >= Float.MAX_VALUE || f10 <= -3.4028235E38f) {
            Log.e(str, "scaleUpFactor is invalid");
            this.m = 1.0f;
            this.l = 0.0f;
        }
        float f11 = this.n;
        if (f11 >= Float.MAX_VALUE || f11 <= -3.4028235E38f) {
            Log.e(str, "scaleDownFactor is invalid");
            this.n = 1.0f;
            this.l = 0.0f;
        }
    }

    public final void b() {
        MenuItemImpl menuItemImpl = this.y;
        if (menuItemImpl != null) {
            setChecked(menuItemImpl.isChecked());
        }
    }

    public final void c() {
        Drawable drawable = this.g;
        ColorStateList colorStateList = this.f;
        FrameLayout frameLayout = this.q;
        RippleDrawable rippleDrawable = null;
        boolean z3 = true;
        if (colorStateList != null) {
            Drawable activeIndicatorDrawable = getActiveIndicatorDrawable();
            if (this.f1793F && getActiveIndicatorDrawable() != null && frameLayout != null && activeIndicatorDrawable != null) {
                rippleDrawable = new RippleDrawable(C0299a.b(this.f), (Drawable) null, activeIndicatorDrawable);
                z3 = false;
            } else if (drawable == null) {
                ColorStateList colorStateList2 = this.f;
                int a7 = C0299a.a(colorStateList2, C0299a.f1981c);
                int[] iArr = C0299a.b;
                drawable = new RippleDrawable(new ColorStateList(new int[][]{C0299a.d, iArr, StateSet.NOTHING}, new int[]{a7, C0299a.a(colorStateList2, iArr), C0299a.a(colorStateList2, C0299a.f1980a)}), (Drawable) null, (Drawable) null);
            }
        }
        if (frameLayout != null) {
            frameLayout.setPadding(0, 0, 0, 0);
            frameLayout.setForeground(rippleDrawable);
        }
        ViewCompat.setBackground(this, drawable);
        setDefaultFocusHighlightEnabled(z3);
    }

    public final void d(float f5, float f8) {
        float f10;
        float f11;
        View view = this.r;
        if (view != null) {
            i iVar = this.D;
            iVar.getClass();
            view.setScaleX(R1.a.a(0.4f, 1.0f, f5));
            view.setScaleY(iVar.c(f5, f8));
            int i2 = (f8 > 0.0f ? 1 : (f8 == 0.0f ? 0 : -1));
            if (i2 == 0) {
                f10 = 0.8f;
            } else {
                f10 = 0.0f;
            }
            if (i2 == 0) {
                f11 = 1.0f;
            } else {
                f11 = 0.2f;
            }
            view.setAlpha(R1.a.b(0.0f, 1.0f, f10, f11, f5));
        }
        this.E = f5;
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        FrameLayout frameLayout = this.q;
        if (frameLayout != null && this.f1793F) {
            frameLayout.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void e(TextView textView, int i2) {
        if (textView != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(i2, R$styleable.TextAppearance);
            TypedValue peekValue = obtainStyledAttributes.peekValue(R$styleable.TextAppearance_android_textSize);
            obtainStyledAttributes.recycle();
            textView.setTextSize(1, Math.min(getResources().getConfiguration().fontScale, 1.3f) * TypedValue.complexToFloat(peekValue.data));
        }
    }

    public Drawable getActiveIndicatorDrawable() {
        View view = this.r;
        if (view == null) {
            return null;
        }
        return view.getBackground();
    }

    public a getBadge() {
        return this.L;
    }

    public int getBadgeType() {
        return this.f1799M;
    }

    public int getItemBackgroundResId() {
        return R.drawable.mtrl_navigation_bar_item_background;
    }

    public MenuItemImpl getItemData() {
        return this.y;
    }

    public int getItemDefaultMarginResId() {
        return R.dimen.mtrl_navigation_bar_item_default_margin;
    }

    public abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.w;
    }

    public TextView getLabel() {
        TextView textView = this.u;
        if (textView != null) {
            return textView;
        }
        return this.v;
    }

    public SpannableStringBuilder getLabelImageSpan() {
        return this.f1800N;
    }

    public int getSuggestedMinimumHeight() {
        int i2;
        ViewGroup viewGroup = this.t;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        int suggestedIconHeight = getSuggestedIconHeight();
        if (viewGroup.getVisibility() == 0) {
            i2 = this.f1805j;
        } else {
            i2 = 0;
        }
        return viewGroup.getMeasuredHeight() + suggestedIconHeight + i2 + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public int getSuggestedMinimumWidth() {
        ViewGroup viewGroup = this.t;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        int measuredWidth = viewGroup.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
        int minimumWidth = getMinimumWidth();
        if (minimumWidth != 0) {
            return minimumWidth;
        }
        return Math.max(getSuggestedIconWidth(), measuredWidth);
    }

    public int getViewType() {
        return this.f1801O;
    }

    public final void i(int i2) {
        int i7;
        View view = this.r;
        if (view != null && i2 > 0) {
            int min = Math.min(this.f1794G, i2 - (this.f1797J * 2));
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            if (!this.f1796I || this.f1806o != 2) {
                i7 = this.f1795H;
            } else {
                i7 = min;
            }
            layoutParams.height = i7;
            layoutParams.width = min;
            view.setLayoutParams(layoutParams);
        }
    }

    public void initialize(MenuItemImpl menuItemImpl, int i2) {
        int i7;
        this.y = menuItemImpl;
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitle());
        setId(menuItemImpl.getItemId());
        if (!TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(menuItemImpl.getContentDescription());
        }
        TooltipCompat.setTooltipText(this, menuItemImpl.getTooltipText());
        String badgeText = menuItemImpl.getBadgeText();
        if (badgeText == null || badgeText.equals("") || badgeText.isEmpty()) {
            i7 = 1;
        } else if (menuItemImpl.getItemId() == R.id.bottom_overflow) {
            i7 = 0;
        } else {
            i7 = 2;
        }
        setBadgeType(i7);
        this.e = true;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getBackground() instanceof SeslRecoilDrawable) {
            this.S = true;
        }
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        e(this.v, this.f1802P);
        e(this.u, this.Q);
    }

    public final int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        MenuItemImpl menuItemImpl = this.y;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.y.isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, T);
        }
        return onCreateDrawableState;
    }

    public final void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        Drawable drawable = this.g;
        if (drawable != null && (i2 = this.f1798K) != 0) {
            drawable.setBounds(-i2, 0, getMeasuredWidth() + this.f1798K, getMeasuredHeight());
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        String str;
        a aVar;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (!(this.y == null || (aVar = this.L) == null || !aVar.isVisible())) {
            CharSequence title = this.y.getTitle();
            if (!TextUtils.isEmpty(this.y.getContentDescription())) {
                title = this.y.getContentDescription();
            }
            accessibilityNodeInfo.setContentDescription(title + ArcCommonLog.TAG_COMMA + this.L.c());
        }
        TextView textView = (TextView) findViewById(R.id.notifications_badge);
        if (this.y != null && textView != null && textView.getVisibility() == 0 && textView.getWidth() > 0) {
            CharSequence title2 = this.y.getTitle();
            String charSequence = title2.toString();
            if (TextUtils.isEmpty(this.y.getContentDescription())) {
                int i2 = this.f1799M;
                if (i2 == 0) {
                    charSequence = title2 + " , " + getResources().getString(R.string.sesl_material_badge_description);
                } else if (i2 == 1) {
                    charSequence = title2 + " , " + getResources().getString(R.string.mtrl_badge_numberless_content_description);
                } else if (i2 == 2) {
                    String charSequence2 = textView.getText().toString();
                    if (charSequence2 != null) {
                        try {
                            Integer.parseInt(charSequence2);
                            int parseInt = Integer.parseInt(charSequence2);
                            charSequence = title2 + " , " + getResources().getQuantityString(R.plurals.mtrl_badge_content_description, parseInt, new Object[]{Integer.valueOf(parseInt)});
                        } catch (NumberFormatException unused) {
                        }
                    }
                    if (this.R) {
                        str = title2 + " , " + getResources().getString(R.string.mtrl_exceed_max_badge_number_content_description, new Object[]{999});
                    } else {
                        str = title2 + " , " + getResources().getString(R.string.sesl_material_badge_description);
                    }
                    charSequence = str;
                }
            } else {
                charSequence = this.y.getContentDescription().toString();
            }
            accessibilityNodeInfo.setContentDescription(charSequence);
        }
        AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
        wrap.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, getItemVisiblePosition(), 1, false, isSelected()));
        if (isSelected()) {
            wrap.setClickable(false);
            wrap.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    public final void onSizeChanged(int i2, int i7, int i8, int i10) {
        super.onSizeChanged(i2, i7, i8, i10);
        post(new g(this, i2, 2));
    }

    public final boolean prefersCondensedTitle() {
        return false;
    }

    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.S && getStateListAnimator() != null) {
            getStateListAnimator().jumpToCurrentState();
            this.S = false;
        }
    }

    public void setActiveIndicatorDrawable(Drawable drawable) {
        View view = this.r;
        if (view != null) {
            view.setBackgroundDrawable(drawable);
            c();
        }
    }

    public void setActiveIndicatorEnabled(boolean z3) {
        int i2;
        this.f1793F = z3;
        c();
        View view = this.r;
        if (view != null) {
            if (z3) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
            requestLayout();
        }
    }

    public void setActiveIndicatorHeight(int i2) {
        this.f1795H = i2;
        i(getWidth());
    }

    public void setActiveIndicatorLabelPadding(int i2) {
        if (this.f1805j != i2) {
            this.f1805j = i2;
            b();
        }
    }

    public void setActiveIndicatorMarginHorizontal(int i2) {
        this.f1797J = i2;
        i(getWidth());
    }

    public void setActiveIndicatorResizeable(boolean z3) {
        this.f1796I = z3;
    }

    public void setActiveIndicatorWidth(int i2) {
        this.f1794G = i2;
        i(getWidth());
    }

    public void setBadge(a aVar) {
        a aVar2 = this.L;
        if (aVar2 != aVar) {
            ImageView imageView = this.s;
            if (!(aVar2 == null || imageView == null)) {
                Log.w("NavigationBar", "Multiple badges shouldn't be attached to one item.");
                if (this.L != null) {
                    setClipChildren(true);
                    setClipToPadding(true);
                    a aVar3 = this.L;
                    if (aVar3 != null) {
                        if (aVar3.d() != null) {
                            aVar3.d().setForeground((Drawable) null);
                        } else {
                            imageView.getOverlay().remove(aVar3);
                        }
                    }
                    this.L = null;
                }
            }
            this.L = aVar;
            if (imageView != null && aVar != null) {
                setClipChildren(false);
                setClipToPadding(false);
                a aVar4 = this.L;
                Rect rect = new Rect();
                imageView.getDrawingRect(rect);
                aVar4.setBounds(rect);
                aVar4.i(imageView, (FrameLayout) null);
                if (aVar4.d() != null) {
                    aVar4.d().setForeground(aVar4);
                } else {
                    imageView.getOverlay().add(aVar4);
                }
            }
        }
    }

    public void setBadgeNumberless(boolean z3) {
        this.R = z3;
    }

    public void setBadgeType(int i2) {
        this.f1799M = i2;
    }

    public void setCheckable(boolean z3) {
        refreshDrawableState();
    }

    public void setChecked(boolean z3) {
        float f5;
        TextView textView = this.v;
        textView.setPivotX((float) (textView.getWidth() / 2));
        textView.setPivotY((float) textView.getBaseline());
        TextView textView2 = this.u;
        textView2.setPivotX((float) (textView2.getWidth() / 2));
        textView2.setPivotY((float) textView2.getBaseline());
        if (getViewType() == 2) {
            this.k = getResources().getDimensionPixelSize(R.dimen.sesl_navigation_bar_icon_only_inset);
        } else if (getViewType() != 3) {
            this.k = getResources().getDimensionPixelSize(R.dimen.sesl_navigation_bar_icon_inset);
        }
        this.f1803h = this.k;
        if (z3) {
            f5 = 1.0f;
        } else {
            f5 = 0.0f;
        }
        if (!this.f1793F || !this.e || !ViewCompat.isAttachedToWindow(this)) {
            d(f5, f5);
        } else {
            ValueAnimator valueAnimator = this.f1792C;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                this.f1792C = null;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.E, f5});
            this.f1792C = ofFloat;
            ofFloat.addUpdateListener(new b(this, f5));
            this.f1792C.setInterpolator(k.M(getContext(), R.attr.motionEasingEmphasizedInterpolator, R1.a.b));
            this.f1792C.setDuration((long) k.L(getContext(), R.attr.motionDurationLong2, getResources().getInteger(R.integer.material_motion_duration_long_1)));
            this.f1792C.start();
        }
        int i2 = this.f1806o;
        ViewGroup viewGroup = this.t;
        if (i2 != -1) {
            if (i2 == 0) {
                if (z3) {
                    h(this.f1803h, 49, getIconOrContainer());
                    j(this.f1804i, viewGroup);
                    textView.setVisibility(0);
                    g(textView, 1.0f, 1.0f, 0);
                } else {
                    h(this.f1803h, 17, getIconOrContainer());
                    j(0, viewGroup);
                    textView.setVisibility(4);
                    g(textView, 0.5f, 0.5f, 4);
                }
                textView2.setVisibility(4);
            } else if (i2 == 1) {
                j(this.f1804i, viewGroup);
                if (z3) {
                    h((int) (((float) this.f1803h) + this.l), 49, getIconOrContainer());
                    g(textView, 1.0f, 1.0f, 0);
                    float f8 = this.m;
                    g(textView2, f8, f8, 4);
                } else {
                    h(this.f1803h, 49, getIconOrContainer());
                    float f10 = this.n;
                    g(textView, f10, f10, 4);
                    g(textView2, 1.0f, 1.0f, 0);
                }
            } else if (i2 == 2) {
                h(this.f1803h, 17, getIconOrContainer());
                textView.setVisibility(8);
                textView2.setVisibility(8);
            }
        } else if (this.f1807p) {
            if (z3) {
                h(this.f1803h, 49, getIconOrContainer());
                j(this.f1804i, viewGroup);
                textView.setVisibility(0);
                g(textView, 1.0f, 1.0f, 0);
            } else {
                h(this.f1803h, 17, getIconOrContainer());
                j(0, viewGroup);
                textView.setVisibility(4);
                g(textView, 0.5f, 0.5f, 4);
            }
            textView2.setVisibility(4);
        } else {
            j(this.f1804i, viewGroup);
            if (z3) {
                h((int) (((float) this.f1803h) + this.l), 49, getIconOrContainer());
                g(textView, 1.0f, 1.0f, 4);
                float f11 = this.m;
                g(textView2, f11, f11, 0);
            } else {
                h(this.f1803h, 49, getIconOrContainer());
                float f12 = this.n;
                g(textView, f12, f12, 4);
                g(textView2, 1.0f, 1.0f, 0);
            }
        }
        refreshDrawableState();
    }

    public void setEnabled(boolean z3) {
        super.setEnabled(z3);
        this.u.setEnabled(z3);
        this.v.setEnabled(z3);
        this.s.setEnabled(z3);
        if (z3) {
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        } else {
            ViewCompat.setPointerIcon(this, (PointerIconCompat) null);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable != this.f1791A) {
            this.f1791A = drawable;
            if (drawable != null) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = DrawableCompat.wrap(drawable).mutate();
                this.B = drawable;
                ColorStateList colorStateList = this.z;
                if (colorStateList != null) {
                    DrawableCompat.setTintList(drawable, colorStateList);
                }
            }
            this.s.setImageDrawable(drawable);
        }
    }

    public void setIconSize(int i2) {
        ImageView imageView = this.s;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        imageView.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.z = colorStateList;
        MenuItemImpl menuItemImpl = this.y;
        if ((menuItemImpl != null || this.B != null) && menuItemImpl != null && (drawable = this.B) != null) {
            DrawableCompat.setTintList(drawable, colorStateList);
            this.B.invalidateSelf();
        }
    }

    public void setItemBackground(int i2) {
        setItemBackground(i2 == 0 ? null : ContextCompat.getDrawable(getContext(), i2));
    }

    public void setItemPaddingBottom(int i2) {
        if (this.f1804i != i2) {
            this.f1804i = i2;
            b();
        }
    }

    public void setItemPaddingTop(int i2) {
        if (this.f1803h != i2) {
            this.f1803h = i2;
            b();
        }
    }

    public void setItemPosition(int i2) {
        this.w = i2;
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.f = colorStateList;
        c();
    }

    public void setLabelImageSpan(SpannableStringBuilder spannableStringBuilder) {
        this.f1800N = spannableStringBuilder;
        this.u.setText(spannableStringBuilder);
        this.v.setText(spannableStringBuilder);
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.f1806o != i2) {
            this.f1806o = i2;
            if (!this.f1796I || i2 != 2) {
                this.D = U;
            } else {
                this.D = V;
            }
            i(getWidth());
            b();
        }
    }

    public void setSelectedSidePadding(int i2) {
        this.f1798K = i2;
    }

    public void setShifting(boolean z3) {
        if (this.f1807p != z3) {
            this.f1807p = z3;
            b();
        }
    }

    public void setTextAppearanceActive(int i2) {
        this.f1808x = i2;
        TextView textView = this.v;
        f(textView, i2);
        a(this.u.getTextSize(), textView.getTextSize());
    }

    public void setTextAppearanceActiveBoldEnabled(boolean z3) {
        setTextAppearanceActive(this.f1808x);
        TextView textView = this.v;
        textView.setTypeface(textView.getTypeface(), z3 ? 1 : 0);
    }

    public void setTextAppearanceInactive(int i2) {
        TextView textView = this.u;
        f(textView, i2);
        a(textView.getTextSize(), this.v.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.u.setTextColor(colorStateList);
            this.v.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        CharSequence charSequence2;
        TextView textView = this.u;
        textView.setText(charSequence);
        TextView textView2 = this.v;
        textView2.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
            textView2.setVisibility(8);
        }
        MenuItemImpl menuItemImpl = this.y;
        if (menuItemImpl == null || TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(charSequence);
        }
        MenuItemImpl menuItemImpl2 = this.y;
        if (menuItemImpl2 != null) {
            charSequence2 = menuItemImpl2.getTooltipText();
        } else {
            charSequence2 = null;
        }
        TooltipCompat.setTooltipText(this, charSequence2);
    }

    public void setItemBackground(Drawable drawable) {
        if (!(drawable == null || drawable.getConstantState() == null)) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        this.g = drawable;
        c();
    }
}
