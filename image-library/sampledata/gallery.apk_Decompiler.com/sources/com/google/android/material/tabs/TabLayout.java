package com.google.android.material.tabs;

import A2.c;
import A2.d;
import A2.e;
import A2.f;
import A2.g;
import A2.h;
import A2.i;
import A2.l;
import D2.a;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.oneui.common.BlurSupportable;
import androidx.appcompat.oneui.common.internal.policy.BlurInfoState;
import androidx.appcompat.oneui.common.internal.semblurinfo.SemBlurInfoStateBuilder;
import androidx.appcompat.oneui.common.internal.util.ContextHelperKt;
import androidx.appcompat.util.SeslMisc;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.oneui.common.internal.semblurinfo.SemBlurInfoState;
import androidx.core.util.Pair;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.reflect.widget.SeslHorizontalScrollViewReflector;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import h2.p;
import h2.u;
import java.util.ArrayList;
import java.util.Iterator;
import og.k;
import x2.C0340g;

@ViewPager.DecorView
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TabLayout extends HorizontalScrollView implements BlurSupportable {
    private static final int DEF_STYLE_RES = 2131952948;
    public static final /* synthetic */ int d = 0;
    private static final Pools$Pool<c> tabPool = new Pools$SynchronizedPool(16);
    private a adapterChangeListener;
    private int contentInsetStart;
    private f currentVpSelectedListener;
    /* access modifiers changed from: private */
    public final int defaultTabTextAppearance;
    int indicatorPosition = -1;
    boolean inlineLabel;
    private ColorDrawable mBackgroundColorDrawable;
    private Drawable mBackgroundDrawable;
    private int mBadgeColor = -1;
    private int mBadgeTextColor = -1;
    private SemBlurInfoState mBlurInfo;
    private int mBlurMode = 2;
    /* access modifiers changed from: private */
    public Typeface mBoldTypeface;
    private ContentResolver mContentResolver;
    private int mCurrentTouchSlop;
    private final int mDefaultTouchSlop;
    /* access modifiers changed from: private */
    public int mDepthStyle;
    /* access modifiers changed from: private */
    public int mFirstTabGravity;
    /* access modifiers changed from: private */
    public int mIconTextGap = -1;
    /* access modifiers changed from: private */
    public boolean mIsOverScreen = false;
    private boolean mIsScaledTextSizeType = false;
    /* access modifiers changed from: private */
    public int mMainTabSelectedSideMargin;
    /* access modifiers changed from: private */
    public int mMainTabSeparatorMargin;
    private int mMaxTouchSlop;
    /* access modifiers changed from: private */
    public Typeface mNormalTypeface;
    /* access modifiers changed from: private */
    public int mOverScreenMaxWidth = -1;
    /* access modifiers changed from: private */
    public int mRequestedTabWidth = -1;
    /* access modifiers changed from: private */
    public int mSubTabIndicator2ndHeight = 1;
    /* access modifiers changed from: private */
    public int mSubTabIndicatorHeight = 1;
    /* access modifiers changed from: private */
    public int mSubTabSelectedIndicatorColor = -1;
    int mSubTabSubTextAppearance;
    ColorStateList mSubTabSubTextColors;
    int mSubTabTextSize;
    /* access modifiers changed from: private */
    public int mTabMinSideSpace;
    /* access modifiers changed from: private */
    public int mTabSelectedIndicatorColor;
    @ViewDebug.ExportedProperty(category = "tablayout")
    int mode;
    private i pageChangeListener;
    private PagerAdapter pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private final int requestedTabMaxWidth;
    private final int requestedTabMinWidth;
    private ValueAnimator scrollAnimator;
    private final int scrollableTabMinWidth;
    private f selectedListener;
    private final ArrayList<f> selectedListeners = new ArrayList<>();
    private c selectedTab;
    /* access modifiers changed from: private */
    public int selectedTabTextAppearance = -1;
    float selectedTabTextSize;
    private boolean setupViewPagerImplicitly;
    final b slidingTabIndicator;
    int tabBackgroundResId;
    int tabGravity;
    ColorStateList tabIconTint;
    PorterDuff.Mode tabIconTintMode;
    int tabIndicatorAnimationDuration;
    int tabIndicatorAnimationMode;
    boolean tabIndicatorFullWidth;
    int tabIndicatorGravity;
    int tabIndicatorHeight = -1;
    /* access modifiers changed from: private */
    public c tabIndicatorInterpolator;
    private final TimeInterpolator tabIndicatorTimeInterpolator;
    int tabMaxWidth = Integer.MAX_VALUE;
    int tabPaddingBottom;
    int tabPaddingEnd;
    int tabPaddingStart;
    int tabPaddingTop;
    ColorStateList tabRippleColorStateList;
    Drawable tabSelectedIndicator;
    private int tabSelectedIndicatorColor = 0;
    /* access modifiers changed from: private */
    public final int tabTextAppearance;
    ColorStateList tabTextColors;
    float tabTextMultiLineSize;
    float tabTextSize;
    private final Pools$Pool<d> tabViewPool = new Pools$SimplePool(12);
    private final ArrayList<c> tabs = new ArrayList<>();
    boolean unboundedRipple;
    ViewPager viewPager;
    private int viewPagerScrollState;

    /* JADX INFO: finally extract failed */
    public TabLayout(Context context, AttributeSet attributeSet) {
        super(a.a(context, attributeSet, R.attr.tabStyle, DEF_STYLE_RES), attributeSet, R.attr.tabStyle);
        int i2;
        int i7;
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        b bVar = new b(this, context2);
        this.slidingTabIndicator = bVar;
        super.addView(bVar, 0, new FrameLayout.LayoutParams(-2, -1));
        if (SeslMisc.isLightTheme(context2)) {
            i2 = 2131952949;
        } else {
            i2 = 2131952948;
        }
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, Q1.a.f622M, R.attr.tabStyle, i2);
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        int i8 = obtainStyledAttributes.getInt(4, 1);
        this.mDepthStyle = i8;
        if (z && i8 == 1 && !ContextHelperKt.isDefaultTheme(context2)) {
            setBackground(context2.getDrawable(R.drawable.sesl_tablayout_maintab_background_for_theme));
        }
        ColorStateList B = com.samsung.context.sdk.samsunganalytics.internal.sender.c.B(getBackground());
        if (B != null) {
            C0340g gVar = new C0340g();
            gVar.k(B);
            gVar.i(context2);
            gVar.j(ViewCompat.getElevation(this));
            ViewCompat.setBackground(this, gVar);
        }
        setSelectedTabIndicator(B1.a.w(context2, obtainStyledAttributes, 12));
        setSelectedTabIndicatorColor(obtainStyledAttributes.getColor(15, 0));
        bVar.a(obtainStyledAttributes.getDimensionPixelSize(18, -1));
        this.mTabSelectedIndicatorColor = obtainStyledAttributes.getColor(15, 0);
        setSelectedTabIndicatorGravity(obtainStyledAttributes.getInt(17, 0));
        setTabIndicatorAnimationMode(obtainStyledAttributes.getInt(14, 0));
        setTabIndicatorFullWidth(obtainStyledAttributes.getBoolean(16, true));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(23, 0);
        this.tabPaddingBottom = dimensionPixelSize;
        this.tabPaddingEnd = dimensionPixelSize;
        this.tabPaddingTop = dimensionPixelSize;
        this.tabPaddingStart = dimensionPixelSize;
        this.tabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(26, dimensionPixelSize);
        this.tabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(27, this.tabPaddingTop);
        this.tabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(25, this.tabPaddingEnd);
        this.tabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(24, this.tabPaddingBottom);
        int[] iArr = p.f1776a;
        if (k.K(context2, R.attr.isMaterial3Theme, false)) {
            this.defaultTabTextAppearance = R.attr.textAppearanceTitleSmall;
        } else {
            this.defaultTabTextAppearance = R.attr.textAppearanceButton;
        }
        int resourceId = obtainStyledAttributes.getResourceId(31, com.samsung.android.photoremaster.engine.R.style.TextAppearance_Design_Tab);
        this.tabTextAppearance = resourceId;
        int[] iArr2 = R$styleable.TextAppearance;
        TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(resourceId, iArr2);
        int i10 = R$styleable.TextAppearance_android_textSize;
        this.tabTextSize = (float) obtainStyledAttributes2.getDimensionPixelSize(i10, 0);
        this.mIsScaledTextSizeType = obtainStyledAttributes2.getText(i10).toString().contains("sp");
        int i11 = R$styleable.TextAppearance_android_textColor;
        this.tabTextColors = B1.a.u(context2, obtainStyledAttributes2, i11);
        Resources resources = getResources();
        this.mMaxTouchSlop = resources.getDisplayMetrics().widthPixels;
        int scaledTouchSlop = ViewConfiguration.get(context2).getScaledTouchSlop();
        this.mDefaultTouchSlop = scaledTouchSlop;
        this.mCurrentTouchSlop = scaledTouchSlop;
        if (Build.VERSION.SDK_INT >= 34) {
            Typeface create = Typeface.create("sec", 0);
            this.mBoldTypeface = Typeface.create(create, 600, false);
            this.mNormalTypeface = Typeface.create(create, 400, false);
        } else {
            String string = resources.getString(R$string.sesl_font_family_regular);
            this.mBoldTypeface = Typeface.create(string, 1);
            this.mNormalTypeface = Typeface.create(string, 0);
        }
        this.mSubTabIndicatorHeight = resources.getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_indicator_height);
        this.mSubTabIndicator2ndHeight = resources.getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_indicator_2nd_height);
        this.mTabMinSideSpace = resources.getDimensionPixelSize(R.dimen.sesl_tab_min_side_space);
        int resourceId2 = obtainStyledAttributes.getResourceId(5, 2131952433);
        this.mSubTabSubTextAppearance = resourceId2;
        TypedArray obtainStyledAttributes3 = context2.obtainStyledAttributes(resourceId2, iArr2);
        try {
            this.mSubTabSubTextColors = B1.a.u(context2, obtainStyledAttributes3, i11);
            this.mSubTabTextSize = obtainStyledAttributes3.getDimensionPixelSize(i10, 0);
            obtainStyledAttributes2.recycle();
            obtainStyledAttributes3.recycle();
            if (obtainStyledAttributes.hasValue(6)) {
                this.mSubTabSubTextColors = B1.a.u(context2, obtainStyledAttributes, 6);
            }
            if (obtainStyledAttributes.hasValue(2)) {
                this.mSubTabSubTextColors = e(this.mSubTabSubTextColors.getDefaultColor(), obtainStyledAttributes.getColor(2, 0));
            }
            this.mMainTabSeparatorMargin = obtainStyledAttributes.getDimensionPixelSize(3, 0);
            this.mMainTabSelectedSideMargin = obtainStyledAttributes.getDimensionPixelSize(1, 0);
            if (obtainStyledAttributes.hasValue(29)) {
                this.selectedTabTextAppearance = obtainStyledAttributes.getResourceId(29, resourceId);
            }
            int i12 = this.selectedTabTextAppearance;
            if (i12 != -1) {
                TypedArray obtainStyledAttributes4 = context2.obtainStyledAttributes(i12, iArr2);
                try {
                    this.selectedTabTextSize = (float) obtainStyledAttributes4.getDimensionPixelSize(i10, (int) this.tabTextSize);
                    ColorStateList u = B1.a.u(context2, obtainStyledAttributes4, i11);
                    if (u != null) {
                        this.tabTextColors = e(this.tabTextColors.getDefaultColor(), u.getColorForState(new int[]{16842913}, u.getDefaultColor()));
                    }
                } finally {
                    obtainStyledAttributes4.recycle();
                }
            }
            if (obtainStyledAttributes.hasValue(32)) {
                this.tabTextColors = B1.a.u(context2, obtainStyledAttributes, 32);
            }
            if (obtainStyledAttributes.hasValue(30)) {
                this.tabTextColors = e(this.tabTextColors.getDefaultColor(), obtainStyledAttributes.getColor(30, 0));
            }
            this.tabIconTint = B1.a.u(context2, obtainStyledAttributes, 10);
            this.tabIconTintMode = u.d(obtainStyledAttributes.getInt(11, -1), (PorterDuff.Mode) null);
            this.tabRippleColorStateList = B1.a.u(context2, obtainStyledAttributes, 28);
            this.tabIndicatorAnimationDuration = obtainStyledAttributes.getInt(13, StatusCodes.INPUT_MISSING);
            this.tabIndicatorTimeInterpolator = k.M(context2, R.attr.motionEasingEmphasizedInterpolator, R1.a.b);
            this.requestedTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(21, -1);
            this.requestedTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(20, -1);
            this.tabBackgroundResId = obtainStyledAttributes.getResourceId(7, 0);
            this.contentInsetStart = obtainStyledAttributes.getDimensionPixelSize(8, 0);
            this.mode = obtainStyledAttributes.getInt(22, 1);
            int i13 = obtainStyledAttributes.getInt(9, 0);
            this.tabGravity = i13;
            this.mFirstTabGravity = i13;
            this.inlineLabel = obtainStyledAttributes.getBoolean(19, false);
            this.unboundedRipple = obtainStyledAttributes.getBoolean(33, false);
            obtainStyledAttributes.recycle();
            this.tabTextMultiLineSize = (float) resources.getDimensionPixelSize(R.dimen.sesl_tab_text_size_2line);
            this.scrollableTabMinWidth = resources.getDimensionPixelSize(R.dimen.sesl_tab_scrollable_min_width);
            b();
            if (!ContextHelperKt.isDefaultTheme(context2) && this.mode == 13) {
                this.tabTextColors = ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), R.color.sesl_tablayout_text_color_default_selector_for_theme, getContext().getTheme()));
                this.tabTextColors = e(this.tabTextColors.getDefaultColor(), ResourcesCompat.getColor(getResources(), R.color.sesl_tablayout_text_color_selected_for_theme, getContext().getTheme()));
                this.tabBackgroundResId = R.drawable.sesl_tabview_maintab_ripple_background_for_theme;
            }
            Drawable background = getBackground();
            this.mContentResolver = context2.getContentResolver();
            if (background instanceof ColorDrawable) {
                this.mBackgroundColorDrawable = (ColorDrawable) background;
            }
            if (this.mDepthStyle == 2) {
                Resources resources2 = getResources();
                if (SeslMisc.isLightTheme(getContext())) {
                    i7 = R.color.sesl_tablayout_subtab_text_color_light;
                } else {
                    i7 = R.color.sesl_tablayout_subtab_text_color_dark;
                }
                this.tabTextColors = resources2.getColorStateList(i7);
            }
            if (z) {
                applyBlurInfo(context2);
            }
        } catch (Throwable th) {
            obtainStyledAttributes2.recycle();
            obtainStyledAttributes3.recycle();
            throw th;
        }
    }

    public static void access$2300(TabLayout tabLayout, TextView textView, int i2) {
        float f = tabLayout.getResources().getConfiguration().fontScale;
        if (textView != null && tabLayout.mIsScaledTextSizeType && f > 1.3f) {
            textView.setTextSize(0, (((float) i2) / f) * 1.3f);
        }
    }

    public static boolean access$3500(TabLayout tabLayout) {
        if (tabLayout.getTabMode() == 0 || tabLayout.getTabMode() == 2) {
            return true;
        }
        return false;
    }

    public static boolean access$3700(TabLayout tabLayout, View view, int i2, int i7) {
        tabLayout.getClass();
        Rect rect = new Rect();
        int[] iArr = new int[2];
        view.getDrawingRect(rect);
        view.getLocationOnScreen(iArr);
        rect.offset(iArr[0], iArr[1]);
        return !rect.contains(i2, i7);
    }

    public static void access$3800(TabLayout tabLayout, d dVar) {
        tabLayout.getClass();
        SeslTabRoundRectIndicator seslTabRoundRectIndicator = dVar.q;
        dVar.setSelected(false);
        if (seslTabRoundRectIndicator != null && !seslTabRoundRectIndicator.isSelected()) {
            seslTabRoundRectIndicator.a();
        }
        c tabAt = tabLayout.getTabAt(tabLayout.getSelectedTabPosition());
        if (tabAt != null) {
            tabAt.g.setSelected(true);
            SeslTabRoundRectIndicator seslTabRoundRectIndicator2 = tabAt.g.q;
            if (seslTabRoundRectIndicator2 != null) {
                seslTabRoundRectIndicator2.d();
            }
        }
        if (tabLayout.mDepthStyle != 1 && seslTabRoundRectIndicator != null && seslTabRoundRectIndicator.isSelected()) {
            seslTabRoundRectIndicator.c();
        }
    }

    public static ColorStateList e(int i2, int i7) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i7, i2});
    }

    private int getDefaultHeight() {
        int size = this.tabs.size();
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = this.tabs.get(i2);
            if (cVar != null && cVar.b != null && !TextUtils.isEmpty(cVar.f1502c)) {
                return 56;
            }
        }
        return 56;
    }

    private int getSelectedTabTextColor() {
        ColorStateList colorStateList = this.tabTextColors;
        if (colorStateList != null) {
            return colorStateList.getColorForState(new int[]{16842913, 16842910}, colorStateList.getDefaultColor());
        }
        return -1;
    }

    private int getTabMinWidth() {
        int i2 = this.requestedTabMinWidth;
        if (i2 != -1) {
            return i2;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void setShowButtonShape(d dVar) {
        int i2;
        ColorStateList tabTextColors2 = getTabTextColors();
        if (this.mDepthStyle == 1) {
            int i7 = 0;
            if (Settings.System.getInt(this.mContentResolver, "show_button_background", 0) == 1) {
                ColorDrawable colorDrawable = this.mBackgroundColorDrawable;
                if (colorDrawable != null) {
                    i7 = colorDrawable.getColor();
                }
                if (i7 == 0) {
                    Resources resources = getResources();
                    if (SeslMisc.isLightTheme(getContext())) {
                        i2 = R.color.sesl_bottom_navigation_background_light;
                    } else {
                        i2 = R.color.sesl_bottom_navigation_background_dark;
                    }
                    i7 = resources.getColor(i2, (Resources.Theme) null);
                }
                Drawable drawable = dVar.getResources().getDrawable(R.drawable.sesl_bottom_nav_show_button_shapes_background);
                TextView textView = dVar.e;
                if (textView != null) {
                    textView.setTextColor(i7);
                    dVar.e.setBackground(drawable);
                    dVar.e.setBackgroundTintList(tabTextColors2);
                }
                TextView textView2 = dVar.u;
                if (textView2 != null) {
                    textView2.setTextColor(i7);
                    dVar.u.setBackground(drawable);
                    dVar.u.setBackgroundTintList(tabTextColors2);
                }
            }
        }
    }

    public final void a(int i2) {
        if (i2 != -1) {
            if (getWindowToken() != null && ViewCompat.isLaidOut(this)) {
                b bVar = this.slidingTabIndicator;
                int childCount = bVar.getChildCount();
                int i7 = 0;
                while (i7 < childCount) {
                    if (bVar.getChildAt(i7).getWidth() > 0) {
                        i7++;
                    }
                }
                int scrollX = getScrollX();
                int c5 = c(0.0f, i2);
                if (scrollX != c5) {
                    f();
                    this.scrollAnimator.setIntValues(new int[]{scrollX, c5});
                    this.scrollAnimator.start();
                }
                this.slidingTabIndicator.getClass();
                return;
            }
            setScrollPosition(i2, 0.0f, true);
        }
    }

    public void addOnTabSelectedListener(g gVar) {
        addOnTabSelectedListener((f) gVar);
    }

    public void addTab(c cVar, boolean z) {
        addTab(cVar, this.tabs.size(), z);
    }

    public void addView(View view) {
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    public boolean applyBlurInfo(Context context) {
        if (Build.VERSION.SDK_INT < 35) {
            return false;
        }
        clearBlurInfo(context);
        float dimension = context.getResources().getDimension(R.dimen.sesl_tablayout_item_radius);
        SemBlurInfoStateBuilder generateFloatingComponentBlurInfoStateBuilder = BlurInfoState.INSTANCE.generateFloatingComponentBlurInfoStateBuilder(context, this.mBlurMode);
        Drawable drawable = this.mBackgroundDrawable;
        if (drawable != null) {
            generateFloatingComponentBlurInfoStateBuilder.nonBlurBackground(drawable);
        }
        SemBlurInfoState build = generateFloatingComponentBlurInfoStateBuilder.cornerRadius(dimension).build();
        if (this.mDepthStyle == 1 && this.mode == 13 && build.applyBlurInfo(this)) {
            this.mBlurInfo = build;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        if (r0 != 2) goto L_0x0058;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r4 = this;
            int r0 = r4.mode
            r1 = 13
            if (r0 != r1) goto L_0x0017
            android.content.res.Resources r0 = r4.getResources()
            r1 = 2131168508(0x7f070cfc, float:1.795132E38)
            int r0 = r0.getDimensionPixelOffset(r1)
            com.google.android.material.tabs.b r1 = r4.slidingTabIndicator
            androidx.core.view.ViewCompat.setPaddingRelative(r1, r0, r0, r0, r0)
            goto L_0x001d
        L_0x0017:
            com.google.android.material.tabs.b r0 = r4.slidingTabIndicator
            r1 = 0
            androidx.core.view.ViewCompat.setPaddingRelative(r0, r1, r1, r1, r1)
        L_0x001d:
            int r0 = r4.mode
            java.lang.String r1 = "TabLayout"
            r2 = 2
            r3 = 1
            if (r0 == 0) goto L_0x003c
            if (r0 == r3) goto L_0x002d
            if (r0 == r2) goto L_0x002d
            switch(r0) {
                case 11: goto L_0x003c;
                case 12: goto L_0x003c;
                case 13: goto L_0x002d;
                default: goto L_0x002c;
            }
        L_0x002c:
            goto L_0x0058
        L_0x002d:
            int r0 = r4.tabGravity
            if (r0 != r2) goto L_0x0036
            java.lang.String r0 = "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead"
            android.util.Log.w(r1, r0)
        L_0x0036:
            com.google.android.material.tabs.b r0 = r4.slidingTabIndicator
            r0.setGravity(r3)
            goto L_0x0058
        L_0x003c:
            int r0 = r4.tabGravity
            if (r0 == 0) goto L_0x004b
            if (r0 == r3) goto L_0x0045
            if (r0 == r2) goto L_0x0050
            goto L_0x0058
        L_0x0045:
            com.google.android.material.tabs.b r0 = r4.slidingTabIndicator
            r0.setGravity(r3)
            goto L_0x0058
        L_0x004b:
            java.lang.String r0 = "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead"
            android.util.Log.w(r1, r0)
        L_0x0050:
            com.google.android.material.tabs.b r0 = r4.slidingTabIndicator
            r1 = 8388611(0x800003, float:1.1754948E-38)
            r0.setGravity(r1)
        L_0x0058:
            r4.updateTabViews(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.b():void");
    }

    public final int c(float f, int i2) {
        View childAt;
        View view;
        int i7 = this.mode;
        int i8 = 0;
        if ((i7 != 0 && i7 != 2 && i7 != 11 && i7 != 12) || (childAt = this.slidingTabIndicator.getChildAt(i2)) == null) {
            return 0;
        }
        int i10 = i2 + 1;
        if (i10 < this.slidingTabIndicator.getChildCount()) {
            view = this.slidingTabIndicator.getChildAt(i10);
        } else {
            view = null;
        }
        int width = childAt.getWidth();
        if (view != null) {
            i8 = view.getWidth();
        }
        int left = ((width / 2) + childAt.getLeft()) - (getWidth() / 2);
        int i11 = (int) (((float) (width + i8)) * 0.5f * f);
        if (ViewCompat.getLayoutDirection(this) == 0) {
            return left + i11;
        }
        return left - i11;
    }

    public void clearBlurInfo(Context context) {
        SemBlurInfoState semBlurInfoState = this.mBlurInfo;
        if (semBlurInfoState != null) {
            semBlurInfoState.clearBlurInfo(this);
        }
        this.mBlurInfo = null;
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [com.google.android.material.tabs.c, java.lang.Object] */
    public c createTabFromPool() {
        c acquire = tabPool.acquire();
        if (acquire != null) {
            return acquire;
        }
        ? obj = new Object();
        obj.d = -1;
        return obj;
    }

    public final void d() {
        int measuredWidth = getMeasuredWidth();
        int integer = getResources().getInteger(R.integer.sesl_tablayout_over_screen_width_dp);
        if (measuredWidth > ((int) ((((float) getContext().getResources().getDisplayMetrics().densityDpi) / 160.0f) * ((float) integer)))) {
            this.mIsOverScreen = true;
            this.mOverScreenMaxWidth = (int) (getResources().getFloat(R.dimen.sesl_tablayout_over_screen_max_width_rate) * ((float) measuredWidth));
            return;
        }
        this.mIsOverScreen = false;
    }

    public final void f() {
        if (this.scrollAnimator == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.scrollAnimator = valueAnimator;
            valueAnimator.setInterpolator(this.tabIndicatorTimeInterpolator);
            this.scrollAnimator.setDuration((long) this.tabIndicatorAnimationDuration);
            this.scrollAnimator.addUpdateListener(new e(0, this));
        }
    }

    public final Pair g(boolean z) {
        Resources resources = getResources();
        int i2 = R.dimen.sesl_tablayout_tab_n_badge_offset;
        int dimensionPixelSize = resources.getDimensionPixelSize(z ? R.dimen.sesl_tablayout_tab_n_badge_offset : R.dimen.sesl_tablayout_subtab_dot_badge_offset_x);
        Resources resources2 = getResources();
        if (!z) {
            i2 = R.dimen.sesl_tablayout_subtab_dot_badge_offset_y;
        }
        return new Pair(Integer.valueOf(dimensionPixelSize), Integer.valueOf(resources2.getDimensionPixelSize(i2)));
    }

    public int getSelectedTabPosition() {
        c cVar = this.selectedTab;
        if (cVar != null) {
            return cVar.d;
        }
        return -1;
    }

    public c getTabAt(int i2) {
        if (i2 < 0 || i2 >= getTabCount()) {
            return null;
        }
        return this.tabs.get(i2);
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    public int getTabIndicatorAnimationMode() {
        return this.tabIndicatorAnimationMode;
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    public int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    public int getTabMode() {
        return this.mode;
    }

    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public final Pair h(boolean z, boolean z3) {
        int i2;
        int i7;
        if (z) {
            int i8 = this.mDepthStyle;
            i7 = R.dimen.sesl_tablayout_tab_n_badge_offset;
            if (i8 == 1) {
                int i10 = z3 ? R.dimen.sesl_tablayout_maintab_n_badge_with_icon_offset_x_icon_label : R.dimen.sesl_tablayout_tab_n_badge_offset;
                if (z3) {
                    i7 = R.dimen.sesl_tablayout_maintab_n_badge_with_icon_offset_y_icon_label;
                }
                int i11 = i7;
                i7 = i10;
                i2 = i11;
            } else {
                i2 = R.dimen.sesl_tablayout_tab_n_badge_offset;
            }
        } else if (this.mDepthStyle == 1) {
            i2 = R.dimen.sesl_tablayout_subtab_dot_badge_with_icon_offset;
            i7 = z3 ? R.dimen.sesl_tablayout_maintab_dot_badge_with_icon_offset_x_icon_label : R.dimen.sesl_tablayout_subtab_dot_badge_with_icon_offset;
            if (z3) {
                i2 = R.dimen.sesl_tablayout_maintab_dot_badge_with_icon_offset_y_icon_label;
            }
        } else {
            i7 = R.dimen.sesl_tablayout_subtab_dot_badge_offset_x;
            i2 = R.dimen.sesl_tablayout_subtab_dot_badge_offset_y;
        }
        return new Pair(Integer.valueOf(getResources().getDimensionPixelSize(i7)), Integer.valueOf(getResources().getDimensionPixelSize(i2)));
    }

    public final void i(int i2) {
        d dVar = (d) this.slidingTabIndicator.getChildAt(i2);
        this.slidingTabIndicator.removeViewAt(i2);
        if (dVar != null) {
            dVar.setTab((c) null);
            dVar.setSelected(false);
            this.tabViewPool.release(dVar);
        }
        requestLayout();
    }

    public boolean isTabIndicatorFullWidth() {
        return this.tabIndicatorFullWidth;
    }

    public final void j(c cVar, boolean z) {
        int i2;
        ViewPager viewPager2;
        if (cVar == null || cVar.g.isEnabled() || (viewPager2 = this.viewPager) == null) {
            c cVar2 = this.selectedTab;
            if (cVar2 != cVar) {
                if (cVar != null) {
                    i2 = cVar.d;
                } else {
                    i2 = -1;
                }
                if (z) {
                    if ((cVar2 == null || cVar2.d == -1) && i2 != -1) {
                        setScrollPosition(i2, 0.0f, true);
                    } else {
                        a(i2);
                    }
                    if (i2 != -1) {
                        k(i2);
                    }
                }
                this.selectedTab = cVar;
                if (!(cVar2 == null || cVar2.f == null)) {
                    for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
                        this.selectedListeners.get(size).onTabUnselected(cVar2);
                    }
                }
                if (cVar != null) {
                    for (int size2 = this.selectedListeners.size() - 1; size2 >= 0; size2--) {
                        this.selectedListeners.get(size2).onTabSelected(cVar);
                    }
                }
            } else if (cVar2 != null) {
                for (int size3 = this.selectedListeners.size() - 1; size3 >= 0; size3--) {
                    this.selectedListeners.get(size3).onTabReselected(cVar);
                }
                a(cVar.d);
            }
        } else {
            viewPager2.setCurrentItem(getSelectedTabPosition());
        }
    }

    public final void k(int i2) {
        SeslTabRoundRectIndicator seslTabRoundRectIndicator;
        boolean z;
        boolean z3;
        int childCount = this.slidingTabIndicator.getChildCount();
        if (i2 < childCount) {
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = this.slidingTabIndicator.getChildAt(i7);
                boolean z7 = true;
                if ((i7 != i2 || childAt.isSelected()) && (i7 == i2 || !childAt.isSelected())) {
                    if (i7 == i2) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    childAt.setSelected(z3);
                    if (i7 != i2) {
                        z7 = false;
                    }
                    childAt.setActivated(z7);
                } else {
                    if (i7 == i2) {
                        z = true;
                    } else {
                        z = false;
                    }
                    childAt.setSelected(z);
                    if (i7 != i2) {
                        z7 = false;
                    }
                    childAt.setActivated(z7);
                    if (childAt instanceof d) {
                        ((d) childAt).e();
                    }
                }
            }
            for (int i8 = 0; i8 < getTabCount(); i8++) {
                c cVar = this.tabs.get(i8);
                if (!(cVar == null || (seslTabRoundRectIndicator = cVar.g.q) == null)) {
                    if (i8 != i2) {
                        seslTabRoundRectIndicator.a();
                    } else if (seslTabRoundRectIndicator.getAlpha() != 1.0f) {
                        seslTabRoundRectIndicator.d();
                    }
                }
            }
        }
    }

    public final void l(ViewPager viewPager2, boolean z, boolean z3) {
        ViewPager viewPager3 = this.viewPager;
        if (viewPager3 != null) {
            i iVar = this.pageChangeListener;
            if (iVar != null) {
                viewPager3.removeOnPageChangeListener(iVar);
            }
            a aVar = this.adapterChangeListener;
            if (aVar != null) {
                this.viewPager.removeOnAdapterChangeListener(aVar);
            }
        }
        f fVar = this.currentVpSelectedListener;
        if (fVar != null) {
            removeOnTabSelectedListener(fVar);
            this.currentVpSelectedListener = null;
        }
        if (viewPager2 != null) {
            this.viewPager = viewPager2;
            if (this.pageChangeListener == null) {
                this.pageChangeListener = new i(this);
            }
            i iVar2 = this.pageChangeListener;
            iVar2.f31c = 0;
            iVar2.b = 0;
            viewPager2.addOnPageChangeListener(iVar2);
            l lVar = new l(viewPager2);
            this.currentVpSelectedListener = lVar;
            addOnTabSelectedListener((f) lVar);
            PagerAdapter adapter = viewPager2.getAdapter();
            if (adapter != null) {
                setPagerAdapter(adapter, z);
            }
            if (this.adapterChangeListener == null) {
                this.adapterChangeListener = new a(this);
            }
            a aVar2 = this.adapterChangeListener;
            aVar2.f1500a = z;
            viewPager2.addOnAdapterChangeListener(aVar2);
            setScrollPosition(viewPager2.getCurrentItem(), 0.0f, true);
        } else {
            this.viewPager = null;
            setPagerAdapter((PagerAdapter) null, false);
        }
        this.setupViewPagerImplicitly = z3;
    }

    public final void m(LinearLayout.LayoutParams layoutParams, boolean z, boolean z3) {
        int i2;
        int i7 = this.mode;
        if (i7 == 13) {
            int i8 = this.mMainTabSelectedSideMargin;
            if (z || z3) {
                i2 = i8;
            } else {
                i2 = this.mMainTabSeparatorMargin - i8;
            }
            layoutParams.setMarginStart(i8);
            layoutParams.setMarginEnd(i2);
            if (this.tabGravity == 0) {
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
                return;
            }
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        } else if (i7 == 1 && this.tabGravity == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else if (i7 == 11 || i7 == 12) {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    public final void n() {
        char c5;
        TextView textView;
        int i2;
        boolean z;
        int i7;
        int i8;
        int i10;
        if (!this.tabs.isEmpty()) {
            for (int i11 = 0; i11 < this.tabs.size(); i11++) {
                d dVar = this.tabs.get(i11).g;
                View view = dVar.e;
                View view2 = dVar.f;
                if (dVar.getWidth() > 0) {
                    TextView textView2 = dVar.s;
                    boolean z3 = true;
                    if (textView2 == null || textView2.getVisibility() != 0) {
                        TextView textView3 = dVar.t;
                        if (textView3 == null || textView3.getVisibility() != 0) {
                            textView = null;
                            c5 = 65535;
                        } else {
                            textView = dVar.t;
                            c5 = 2;
                        }
                    } else {
                        textView = dVar.s;
                        c5 = 1;
                    }
                    if (textView != null && textView.getVisibility() == 0) {
                        textView.measure(0, 0);
                        if (c5 == 1) {
                            i2 = textView.getMeasuredWidth();
                        } else {
                            i2 = getResources().getDimensionPixelSize(R.dimen.sesl_tab_badge_dot_size);
                        }
                        if (c5 == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (view2 == null || view2.getVisibility() != 0) {
                            if (view != null) {
                                i10 = view.getPaddingRight();
                            } else {
                                i10 = 0;
                            }
                            Pair g = g(z);
                            i7 = ((Integer) g.first).intValue();
                            i8 = ((Integer) g.second).intValue();
                        } else {
                            if (view == null || view.getVisibility() != 0) {
                                z3 = false;
                            }
                            Pair h5 = h(z, z3);
                            i7 = ((Integer) h5.first).intValue();
                            i8 = ((Integer) h5.second).intValue();
                            view = view2;
                            i10 = 0;
                        }
                        if (view != null) {
                            int width = dVar.getWidth();
                            int i12 = i7 - i10;
                            if (view.getRight() + i7 + i2 > width) {
                                i12 = -((view.getRight() + i2) - width);
                            }
                            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textView.getLayoutParams();
                            int i13 = layoutParams.width;
                            if (layoutParams.getMarginStart() != i12 || i13 != i2 || layoutParams.topMargin != i8) {
                                dVar.setClipChildren(false);
                                layoutParams.setMargins(i12, i8, layoutParams.getMarginEnd(), layoutParams.bottomMargin);
                                layoutParams.width = i2;
                                textView.setLayoutParams(layoutParams);
                            }
                        }
                    }
                }
                setShowButtonShape(dVar);
            }
        }
    }

    public c newTab() {
        d dVar;
        c createTabFromPool = createTabFromPool();
        createTabFromPool.f = this;
        Pools$Pool<d> pools$Pool = this.tabViewPool;
        if (pools$Pool != null) {
            dVar = pools$Pool.acquire();
        } else {
            dVar = null;
        }
        if (dVar == null) {
            dVar = new d(this, getContext());
        }
        View view = dVar.r;
        if (view != null) {
            view.setAlpha(0.0f);
        }
        ConstraintLayout constraintLayout = dVar.f1507p;
        if (constraintLayout != null) {
            constraintLayout.removeView(dVar.t);
            dVar.f1507p.removeView(dVar.s);
            dVar.t = null;
            dVar.s = null;
        }
        dVar.setTab(createTabFromPool);
        dVar.setFocusable(true);
        dVar.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty((CharSequence) null)) {
            dVar.setContentDescription(createTabFromPool.f1502c);
        } else {
            dVar.setContentDescription((CharSequence) null);
        }
        createTabFromPool.g = dVar;
        return createTabFromPool;
    }

    public void onAttachedToWindow() {
        d dVar;
        super.onAttachedToWindow();
        for (int i2 = 0; i2 < getTabCount(); i2++) {
            c tabAt = getTabAt(i2);
            if (!(tabAt == null || (dVar = tabAt.g) == null)) {
                View view = dVar.r;
                if (view != null) {
                    view.setAlpha(0.0f);
                }
                if (tabAt.g.q != null) {
                    if (getSelectedTabPosition() == i2) {
                        tabAt.g.q.d();
                    } else {
                        tabAt.g.q.a();
                    }
                }
            }
        }
        k.Q(this);
        if (this.viewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                l((ViewPager) parent, true, true);
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        d dVar;
        View view;
        super.onConfigurationChanged(configuration);
        for (int i2 = 0; i2 < getTabCount(); i2++) {
            c tabAt = getTabAt(i2);
            if (!(tabAt == null || (dVar = tabAt.g) == null || (view = dVar.r) == null)) {
                view.setAlpha(0.0f);
            }
        }
        n();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly) {
            setupWithViewPager((ViewPager) null);
            this.setupViewPagerImplicitly = false;
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getTabCount(), false, 1));
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if ((getTabMode() == 0 || getTabMode() == 2) && super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int i11;
        super.onLayout(z, i2, i7, i8, i10);
        n();
        if (z) {
            this.mMaxTouchSlop = Math.max(this.mMaxTouchSlop, i8 - i2);
        }
        if (this.mode == 1 || (!canScrollHorizontally(1) && !canScrollHorizontally(-1))) {
            i11 = this.mMaxTouchSlop;
        } else {
            i11 = this.mDefaultTouchSlop;
        }
        if (this.mCurrentTouchSlop != i11) {
            SeslHorizontalScrollViewReflector.setTouchSlop(this, i11);
            this.mCurrentTouchSlop = i11;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0081, code lost:
        if (r0.getMeasuredWidth() != getMeasuredWidth()) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008c, code lost:
        if (r0.getMeasuredWidth() < getMeasuredWidth()) goto L_0x008e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = h2.u.b(r0, r1)
            int r0 = java.lang.Math.round(r0)
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 1
            r5 = 0
            if (r1 == r2) goto L_0x002e
            if (r1 == 0) goto L_0x001f
            goto L_0x0041
        L_0x001f:
            int r8 = r6.getPaddingTop()
            int r8 = r8 + r0
            int r0 = r6.getPaddingBottom()
            int r0 = r0 + r8
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            goto L_0x0041
        L_0x002e:
            int r1 = r6.getChildCount()
            if (r1 != r4) goto L_0x0041
            int r1 = android.view.View.MeasureSpec.getSize(r8)
            if (r1 < r0) goto L_0x0041
            android.view.View r1 = r6.getChildAt(r5)
            r1.setMinimumHeight(r0)
        L_0x0041:
            int r0 = android.view.View.MeasureSpec.getSize(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            if (r1 == 0) goto L_0x005f
            int r1 = r6.requestedTabMaxWidth
            if (r1 <= 0) goto L_0x0050
            goto L_0x005d
        L_0x0050:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = h2.u.b(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L_0x005d:
            r6.tabMaxWidth = r1
        L_0x005f:
            super.onMeasure(r7, r8)
            int r0 = r6.getChildCount()
            if (r0 != r4) goto L_0x00e1
            android.view.View r0 = r6.getChildAt(r5)
            int r1 = r6.mode
            r2 = 2
            if (r1 == 0) goto L_0x0084
            if (r1 == r4) goto L_0x0079
            if (r1 == r2) goto L_0x0084
            switch(r1) {
                case 11: goto L_0x008e;
                case 12: goto L_0x008e;
                case 13: goto L_0x008e;
                default: goto L_0x0078;
            }
        L_0x0078:
            goto L_0x00b7
        L_0x0079:
            int r1 = r0.getMeasuredWidth()
            int r4 = r6.getMeasuredWidth()
            if (r1 == r4) goto L_0x00b7
            goto L_0x008e
        L_0x0084:
            int r1 = r0.getMeasuredWidth()
            int r4 = r6.getMeasuredWidth()
            if (r1 >= r4) goto L_0x00b7
        L_0x008e:
            int r1 = r6.getPaddingTop()
            int r4 = r6.getPaddingBottom()
            int r4 = r4 + r1
            android.view.ViewGroup$LayoutParams r1 = r0.getLayoutParams()
            int r1 = r1.height
            int r8 = android.view.ViewGroup.getChildMeasureSpec(r8, r4, r1)
            int r1 = r6.mode
            r4 = 13
            if (r1 != r4) goto L_0x00ac
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            goto L_0x00b0
        L_0x00ac:
            int r7 = r6.getMeasuredWidth()
        L_0x00b0:
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r3)
            r0.measure(r7, r8)
        L_0x00b7:
            r6.d()
            boolean r7 = r6.mIsOverScreen
            if (r7 == 0) goto L_0x00de
            android.view.View r7 = r6.getChildAt(r5)
            int r7 = r7.getMeasuredWidth()
            int r8 = r6.getMeasuredWidth()
            if (r7 >= r8) goto L_0x00de
            int r7 = r6.getMeasuredWidth()
            android.view.View r8 = r6.getChildAt(r5)
            int r8 = r8.getMeasuredWidth()
            int r7 = r7 - r8
            int r7 = r7 / r2
            r6.setPaddingRelative(r7, r5, r5, r5)
            return
        L_0x00de:
            r6.setPaddingRelative(r5, r5, r5, r5)
        L_0x00e1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 8 || getTabMode() == 0 || getTabMode() == 2) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void onVisibilityChanged(View view, int i2) {
        d dVar;
        View view2;
        super.onVisibilityChanged(view, i2);
        for (int i7 = 0; i7 < getTabCount(); i7++) {
            c tabAt = getTabAt(i7);
            if (!(tabAt == null || (dVar = tabAt.g) == null || (view2 = dVar.r) == null)) {
                view2.setAlpha(0.0f);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0049, code lost:
        if (r7 == r6.d) goto L_0x0055;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populateFromPagerAdapter() {
        /*
            r9 = this;
            r9.removeAllTabs()
            androidx.viewpager.widget.PagerAdapter r0 = r9.pagerAdapter
            if (r0 == 0) goto L_0x007b
            int r0 = r0.getCount()
            r1 = 0
            r2 = r1
        L_0x000d:
            r3 = 1
            if (r2 >= r0) goto L_0x005e
            com.google.android.material.tabs.c r4 = r9.newTab()
            androidx.viewpager.widget.PagerAdapter r5 = r9.pagerAdapter
            java.lang.CharSequence r5 = r5.getPageTitle(r2)
            r4.getClass()
            r6 = 0
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L_0x002f
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x002f
            com.google.android.material.tabs.d r6 = r4.g
            r6.setContentDescription(r5)
        L_0x002f:
            r4.f1502c = r5
            com.google.android.material.tabs.d r5 = r4.g
            if (r5 == 0) goto L_0x0058
            r5.e()
            com.google.android.material.tabs.c r6 = r5.d
            if (r6 == 0) goto L_0x0054
            com.google.android.material.tabs.TabLayout r7 = r6.f
            if (r7 == 0) goto L_0x004c
            int r7 = r7.getSelectedTabPosition()
            r8 = -1
            if (r7 == r8) goto L_0x0054
            int r6 = r6.d
            if (r7 != r6) goto L_0x0054
            goto L_0x0055
        L_0x004c:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Tab not attached to a TabLayout"
            r9.<init>(r0)
            throw r9
        L_0x0054:
            r3 = r1
        L_0x0055:
            r5.setSelected(r3)
        L_0x0058:
            r9.addTab(r4, r1)
            int r2 = r2 + 1
            goto L_0x000d
        L_0x005e:
            androidx.viewpager.widget.ViewPager r1 = r9.viewPager
            if (r1 == 0) goto L_0x007b
            if (r0 <= 0) goto L_0x007b
            int r0 = r1.getCurrentItem()
            int r1 = r9.getSelectedTabPosition()
            if (r0 == r1) goto L_0x007b
            int r1 = r9.getTabCount()
            if (r0 >= r1) goto L_0x007b
            com.google.android.material.tabs.c r0 = r9.getTabAt(r0)
            r9.j(r0, r3)
        L_0x007b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.populateFromPagerAdapter():void");
    }

    public boolean releaseFromTabPool(c cVar) {
        return tabPool.release(cVar);
    }

    public void removeAllTabs() {
        for (int childCount = this.slidingTabIndicator.getChildCount() - 1; childCount >= 0; childCount--) {
            i(childCount);
        }
        Iterator<c> it = this.tabs.iterator();
        while (it.hasNext()) {
            c next = it.next();
            it.remove();
            next.f = null;
            next.g = null;
            next.f1501a = null;
            next.b = null;
            next.f1502c = null;
            next.d = -1;
            next.e = null;
            releaseFromTabPool(next);
        }
        this.selectedTab = null;
    }

    public void removeOnTabSelectedListener(g gVar) {
        removeOnTabSelectedListener((f) gVar);
    }

    public void removeTab(c cVar) {
        if (cVar.f == this) {
            removeTabAt(cVar.d);
            return;
        }
        throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
    }

    public void removeTabAt(int i2) {
        int i7;
        c cVar = this.selectedTab;
        if (cVar != null) {
            i7 = cVar.d;
        } else {
            i7 = 0;
        }
        i(i2);
        c remove = this.tabs.remove(i2);
        int i8 = -1;
        c cVar2 = null;
        if (remove != null) {
            remove.f = null;
            remove.g = null;
            remove.f1501a = null;
            remove.b = null;
            remove.f1502c = null;
            remove.d = -1;
            remove.e = null;
            releaseFromTabPool(remove);
        }
        int size = this.tabs.size();
        for (int i10 = i2; i10 < size; i10++) {
            if (this.tabs.get(i10).d == this.indicatorPosition) {
                i8 = i10;
            }
            this.tabs.get(i10).d = i10;
        }
        this.indicatorPosition = i8;
        if (i7 == i2) {
            if (!this.tabs.isEmpty()) {
                cVar2 = this.tabs.get(Math.max(0, i2 - 1));
            }
            selectTab(cVar2);
        }
    }

    public void selectTab(c cVar) {
        selectTab(cVar, true);
    }

    public void seslShowDotBadge(int i2, boolean z) {
        seslShowDotBadge(i2, z, (String) null);
    }

    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
        this.mBackgroundDrawable = getBackground();
    }

    public void setBlurMode(int i2) {
        this.mBlurMode = i2;
        applyBlurInfo(getContext());
    }

    public void setElevation(float f) {
        super.setElevation(f);
        k.O(this, f);
    }

    public void setInlineLabel(boolean z) {
        if (this.inlineLabel != z) {
            this.inlineLabel = z;
            for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                if (childAt instanceof d) {
                    d dVar = (d) childAt;
                    dVar.setOrientation(dVar.v.inlineLabel ^ true ? 1 : 0);
                    TextView textView = dVar.f1505j;
                    if (textView == null && dVar.k == null) {
                        dVar.f(dVar.e, dVar.f, true);
                    } else {
                        dVar.f(textView, dVar.k, false);
                    }
                }
            }
            b();
        }
    }

    public void setInlineLabelResource(int i2) {
        setInlineLabel(getResources().getBoolean(i2));
    }

    @Deprecated
    public void setOnTabSelectedListener(g gVar) {
        setOnTabSelectedListener((f) gVar);
    }

    public void setPagerAdapter(PagerAdapter pagerAdapter2, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter3 = this.pagerAdapter;
        if (!(pagerAdapter3 == null || (dataSetObserver = this.pagerAdapterObserver) == null)) {
            pagerAdapter3.unregisterDataSetObserver(dataSetObserver);
        }
        this.pagerAdapter = pagerAdapter2;
        if (z && pagerAdapter2 != null) {
            if (this.pagerAdapterObserver == null) {
                this.pagerAdapterObserver = new h(this);
            }
            pagerAdapter2.registerDataSetObserver(this.pagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        f();
        this.scrollAnimator.addListener(animatorListener);
    }

    public void setScrollPosition(int i2, float f, boolean z) {
        setScrollPosition(i2, f, z, true);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (drawable == null) {
            drawable = new GradientDrawable();
        }
        Drawable mutate = DrawableCompat.wrap(drawable).mutate();
        this.tabSelectedIndicator = mutate;
        int i2 = this.tabSelectedIndicatorColor;
        if (i2 != 0) {
            DrawableCompat.setTint(mutate, i2);
        } else {
            DrawableCompat.setTintList(mutate, (ColorStateList) null);
        }
        int i7 = this.tabIndicatorHeight;
        if (i7 == -1) {
            i7 = this.tabSelectedIndicator.getIntrinsicHeight();
        }
        this.slidingTabIndicator.a(i7);
    }

    public void setSelectedTabIndicatorColor(int i2) {
        int i7;
        updateTabViews(false);
        this.mTabSelectedIndicatorColor = i2;
        Iterator<c> it = this.tabs.iterator();
        while (it.hasNext()) {
            SeslTabRoundRectIndicator seslTabRoundRectIndicator = it.next().g.q;
            if (seslTabRoundRectIndicator != null) {
                if (this.mDepthStyle != 2 || (i7 = this.mSubTabSelectedIndicatorColor) == -1) {
                    seslTabRoundRectIndicator.setSelectedIndicatorColor(i2);
                } else {
                    seslTabRoundRectIndicator.setSelectedIndicatorColor(i7);
                }
                seslTabRoundRectIndicator.invalidate();
            }
        }
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.tabIndicatorGravity != i2) {
            this.tabIndicatorGravity = i2;
            ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.tabIndicatorHeight = i2;
        this.slidingTabIndicator.a(i2);
    }

    public void setTabGravity(int i2) {
        if (this.tabGravity != i2) {
            this.tabGravity = i2;
            b();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        boolean z;
        if (this.tabIconTint != colorStateList) {
            this.tabIconTint = colorStateList;
            int size = this.tabs.size();
            for (int i2 = 0; i2 < size; i2++) {
                d dVar = this.tabs.get(i2).g;
                if (dVar != null) {
                    dVar.e();
                    c cVar = dVar.d;
                    if (cVar != null) {
                        TabLayout tabLayout = cVar.f;
                        if (tabLayout != null) {
                            int selectedTabPosition = tabLayout.getSelectedTabPosition();
                            if (selectedTabPosition != -1 && selectedTabPosition == cVar.d) {
                                z = true;
                                dVar.setSelected(z);
                            }
                        } else {
                            throw new IllegalArgumentException("Tab not attached to a TabLayout");
                        }
                    }
                    z = false;
                    dVar.setSelected(z);
                }
            }
        }
    }

    public void setTabIconTintResource(int i2) {
        setTabIconTint(AppCompatResources.getColorStateList(getContext(), i2));
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [A2.c, java.lang.Object] */
    public void setTabIndicatorAnimationMode(int i2) {
        this.tabIndicatorAnimationMode = i2;
        if (i2 == 0) {
            this.tabIndicatorInterpolator = new Object();
        } else if (i2 == 1) {
            this.tabIndicatorInterpolator = new A2.a(0);
        } else if (i2 == 2) {
            this.tabIndicatorInterpolator = new A2.a(1);
        } else {
            throw new IllegalArgumentException(i2 + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.tabIndicatorFullWidth = z;
        b bVar = this.slidingTabIndicator;
        int i2 = b.e;
        bVar.getClass();
        ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
    }

    public void setTabMode(int i2) {
        if (i2 != this.mode) {
            this.mode = i2;
            b();
            n();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.tabRippleColorStateList != colorStateList) {
            this.tabRippleColorStateList = colorStateList;
            for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                if (childAt instanceof d) {
                    Context context = getContext();
                    int i7 = d.w;
                    ((d) childAt).d(context);
                }
            }
        }
    }

    public void setTabRippleColorResource(int i2) {
        setTabRippleColor(AppCompatResources.getColorStateList(getContext(), i2));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        boolean z;
        if (this.tabTextColors != colorStateList) {
            this.tabTextColors = colorStateList;
            int size = this.tabs.size();
            for (int i2 = 0; i2 < size; i2++) {
                d dVar = this.tabs.get(i2).g;
                if (dVar != null) {
                    dVar.e();
                    c cVar = dVar.d;
                    if (cVar != null) {
                        TabLayout tabLayout = cVar.f;
                        if (tabLayout != null) {
                            int selectedTabPosition = tabLayout.getSelectedTabPosition();
                            if (selectedTabPosition != -1 && selectedTabPosition == cVar.d) {
                                z = true;
                                dVar.setSelected(z);
                            }
                        } else {
                            throw new IllegalArgumentException("Tab not attached to a TabLayout");
                        }
                    }
                    z = false;
                    dVar.setSelected(z);
                }
            }
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(PagerAdapter pagerAdapter2) {
        setPagerAdapter(pagerAdapter2, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.unboundedRipple != z) {
            this.unboundedRipple = z;
            for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                if (childAt instanceof d) {
                    Context context = getContext();
                    int i7 = d.w;
                    ((d) childAt).d(context);
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i2) {
        setUnboundedRipple(getResources().getBoolean(i2));
    }

    public void setupWithViewPager(ViewPager viewPager2) {
        setupWithViewPager(viewPager2, true);
    }

    public boolean shouldDelayChildPressedState() {
        if (getTabScrollRange() > 0) {
            return true;
        }
        return false;
    }

    public void updateTabViews(boolean z) {
        boolean z3;
        int childCount = this.slidingTabIndicator.getChildCount();
        for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
            View childAt = this.slidingTabIndicator.getChildAt(i2);
            childAt.setMinimumWidth(getTabMinWidth());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            boolean z7 = true;
            if (childCount == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (i2 != childCount - 1) {
                z7 = false;
            }
            m(layoutParams, z3, z7);
            if (z) {
                childAt.requestLayout();
            }
        }
        n();
    }

    public void updateViewPagerScrollState(int i2) {
        this.viewPagerScrollState = i2;
    }

    @Deprecated
    public void addOnTabSelectedListener(f fVar) {
        if (!this.selectedListeners.contains(fVar)) {
            this.selectedListeners.add(fVar);
        }
    }

    public void addTab(c cVar, int i2, boolean z) {
        if (cVar.f == this) {
            cVar.d = i2;
            this.tabs.add(i2, cVar);
            int size = this.tabs.size();
            int i7 = -1;
            for (int i8 = i2 + 1; i8 < size; i8++) {
                if (this.tabs.get(i8).d == this.indicatorPosition) {
                    i7 = i8;
                }
                this.tabs.get(i8).d = i8;
            }
            this.indicatorPosition = i7;
            d dVar = cVar.g;
            dVar.setSelected(false);
            dVar.setActivated(false);
            b bVar = this.slidingTabIndicator;
            int i10 = cVar.d;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            m(layoutParams, false, false);
            bVar.addView(dVar, i10, layoutParams);
            if (this.mode == 13) {
                updateTabViews(true);
            }
            dVar.post(new d(0, this, dVar));
            if (z) {
                cVar.a();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    public void addView(View view, int i2) {
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void removeOnTabSelectedListener(f fVar) {
        this.selectedListeners.remove(fVar);
    }

    public void selectTab(c cVar, boolean z) {
        j(cVar, z);
    }

    public void seslShowDotBadge(int i2, boolean z, String str) {
        int i7;
        int i8;
        int i10;
        if (this.tabs.get(i2) != null) {
            d dVar = this.tabs.get(i2).g;
            if (dVar.t == null && dVar.f1507p != null) {
                TextView textView = new TextView(getContext());
                Resources resources = getResources();
                TextView textView2 = dVar.e;
                boolean z3 = textView2 != null && textView2.getVisibility() == 0;
                if (dVar.t == null) {
                    textView.setVisibility(8);
                    ViewCompat.setBackground(textView, resources.getDrawable(R.drawable.sesl_dot_badge));
                    textView.setId(R.id.sesl_badge_dot);
                    int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sesl_tab_badge_dot_size);
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
                    ImageView imageView = dVar.f;
                    if (imageView == null || imageView.getVisibility() != 0) {
                        layoutParams.topToTop = R.id.title;
                        layoutParams.startToEnd = R.id.title;
                        i10 = textView2 != null ? textView2.getPaddingRight() : 0;
                        Pair g = g(false);
                        i7 = ((Integer) g.first).intValue();
                        i8 = ((Integer) g.second).intValue();
                    } else {
                        layoutParams.topToTop = R.id.icon;
                        layoutParams.startToEnd = R.id.icon;
                        Pair h5 = h(false, z3);
                        i7 = ((Integer) h5.first).intValue();
                        i8 = ((Integer) h5.second).intValue();
                        i10 = 0;
                    }
                    layoutParams.setMargins(i7 - i10, i8, 0, 0);
                    dVar.f1507p.addView(textView, layoutParams);
                    dVar.t = textView;
                }
            }
            TextView textView3 = dVar.t;
            if (textView3 == null) {
                return;
            }
            if (z) {
                textView3.setVisibility(0);
                if (this.mBadgeColor != -1) {
                    DrawableCompat.setTint(textView3.getBackground(), this.mBadgeColor);
                }
                if (str != null) {
                    textView3.setContentDescription(str);
                }
                n();
                return;
            }
            textView3.setVisibility(8);
        }
    }

    @Deprecated
    public void setOnTabSelectedListener(f fVar) {
        f fVar2 = this.selectedListener;
        if (fVar2 != null) {
            removeOnTabSelectedListener(fVar2);
        }
        this.selectedListener = fVar;
        if (fVar != null) {
            addOnTabSelectedListener(fVar);
        }
    }

    public void setScrollPosition(int i2, float f, boolean z, boolean z3) {
        setScrollPosition(i2, f, z, z3, true);
    }

    public void setupWithViewPager(ViewPager viewPager2, boolean z) {
        l(viewPager2, z, false);
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    public void setScrollPosition(int i2, float f, boolean z, boolean z3, boolean z7) {
        float f5;
        float f8 = ((float) i2) + f;
        int round = Math.round(f8);
        if (round >= 0 && round < this.slidingTabIndicator.getChildCount()) {
            if (z3) {
                b bVar = this.slidingTabIndicator;
                TabLayout tabLayout = bVar.d;
                tabLayout.indicatorPosition = Math.round(f8);
                View childAt = bVar.getChildAt(i2);
                View childAt2 = bVar.getChildAt(i2 + 1);
                if (childAt == null || childAt.getWidth() <= 0) {
                    f5 = f;
                    Drawable drawable = tabLayout.tabSelectedIndicator;
                    drawable.setBounds(-1, drawable.getBounds().top, -1, tabLayout.tabSelectedIndicator.getBounds().bottom);
                } else {
                    f5 = f;
                    tabLayout.tabIndicatorInterpolator.b(tabLayout, childAt, childAt2, f5, tabLayout.tabSelectedIndicator);
                }
                ViewCompat.postInvalidateOnAnimation(bVar);
            } else {
                f5 = f;
            }
            ValueAnimator valueAnimator = this.scrollAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.scrollAnimator.cancel();
            }
            int c5 = c(f5, i2);
            int scrollX = getScrollX();
            boolean z9 = (i2 < getSelectedTabPosition() && c5 >= scrollX) || (i2 > getSelectedTabPosition() && c5 <= scrollX) || i2 == getSelectedTabPosition();
            if (ViewCompat.getLayoutDirection(this) == 1) {
                z9 = (i2 < getSelectedTabPosition() && c5 <= scrollX) || (i2 > getSelectedTabPosition() && c5 >= scrollX) || i2 == getSelectedTabPosition();
            }
            if (z9 || this.viewPagerScrollState == 1 || z7) {
                if (i2 < 0) {
                    c5 = 0;
                }
                scrollTo(c5, 0);
            }
            if (z) {
                k(round);
            }
        }
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    public void setSelectedTabIndicator(int i2) {
        if (i2 != 0) {
            setSelectedTabIndicator(AppCompatResources.getDrawable(getContext(), i2));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    public void onDraw(Canvas canvas) {
    }
}
