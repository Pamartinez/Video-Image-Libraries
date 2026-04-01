package com.google.android.material.tabs;

import A2.k;
import U1.a;
import U1.b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.widget.TooltipCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.cover.ScoverState;
import com.sec.android.gallery3d.R;
import h2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends LinearLayout {
    public static final /* synthetic */ int w = 0;
    public c d;
    public TextView e;
    public ImageView f;
    public View g;

    /* renamed from: h  reason: collision with root package name */
    public a f1503h;

    /* renamed from: i  reason: collision with root package name */
    public View f1504i;

    /* renamed from: j  reason: collision with root package name */
    public TextView f1505j;
    public ImageView k;
    public Drawable l;
    public int m = 2;
    public boolean n;

    /* renamed from: o  reason: collision with root package name */
    public int f1506o;

    /* renamed from: p  reason: collision with root package name */
    public ConstraintLayout f1507p;
    public SeslTabRoundRectIndicator q;
    public View r;
    public TextView s;
    public TextView t;
    public TextView u;
    public final /* synthetic */ TabLayout v;

    /* JADX WARNING: type inference failed for: r0v1, types: [android.view.View$OnKeyListener, java.lang.Object] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(TabLayout tabLayout, Context context) {
        super(context);
        this.v = tabLayout;
        ? obj = new Object();
        d(context);
        setGravity(17);
        setOrientation(tabLayout.inlineLabel ^ true ? 1 : 0);
        setClickable(true);
        setOnKeyListener(obj);
        if (tabLayout.mDepthStyle == 1) {
            ViewCompat.setPaddingRelative(this, 0, tabLayout.tabPaddingTop, 0, tabLayout.tabPaddingBottom);
        }
        this.f1506o = getResources().getDimensionPixelOffset(R.dimen.sesl_tab_icon_size);
    }

    private a getBadge() {
        return this.f1503h;
    }

    private a getOrCreateBadge() {
        if (this.f1503h == null) {
            this.f1503h = new a(getContext(), (b) null);
        }
        b();
        a aVar = this.f1503h;
        if (aVar != null) {
            return aVar;
        }
        throw new IllegalStateException("Unable to create badge");
    }

    public final void a() {
        if (this.f1503h != null) {
            setClipChildren(true);
            setClipToPadding(true);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(true);
                viewGroup.setClipToPadding(true);
            }
            View view = this.g;
            if (view != null) {
                a aVar = this.f1503h;
                if (aVar != null) {
                    if (aVar.d() != null) {
                        aVar.d().setForeground((Drawable) null);
                    } else {
                        view.getOverlay().remove(aVar);
                    }
                }
                this.g = null;
            }
        }
    }

    public final void b() {
        c cVar;
        if (this.f1503h == null) {
            return;
        }
        if (this.f1504i != null) {
            a();
            return;
        }
        ImageView imageView = this.f;
        if (imageView == null || (cVar = this.d) == null || cVar.b == null) {
            TextView textView = this.e;
            if (textView == null || this.d == null) {
                a();
            } else if (this.g != textView) {
                a();
                TextView textView2 = this.e;
                if (this.f1503h != null && textView2 != null) {
                    setClipChildren(false);
                    setClipToPadding(false);
                    ViewGroup viewGroup = (ViewGroup) getParent();
                    if (viewGroup != null) {
                        viewGroup.setClipChildren(false);
                        viewGroup.setClipToPadding(false);
                    }
                    a aVar = this.f1503h;
                    Rect rect = new Rect();
                    textView2.getDrawingRect(rect);
                    aVar.setBounds(rect);
                    aVar.i(textView2, (FrameLayout) null);
                    if (aVar.d() != null) {
                        aVar.d().setForeground(aVar);
                    } else {
                        textView2.getOverlay().add(aVar);
                    }
                    this.g = textView2;
                }
            } else {
                c(textView);
            }
        } else if (this.g != imageView) {
            a();
            ImageView imageView2 = this.f;
            if (this.f1503h != null && imageView2 != null) {
                setClipChildren(false);
                setClipToPadding(false);
                ViewGroup viewGroup2 = (ViewGroup) getParent();
                if (viewGroup2 != null) {
                    viewGroup2.setClipChildren(false);
                    viewGroup2.setClipToPadding(false);
                }
                a aVar2 = this.f1503h;
                Rect rect2 = new Rect();
                imageView2.getDrawingRect(rect2);
                aVar2.setBounds(rect2);
                aVar2.i(imageView2, (FrameLayout) null);
                if (aVar2.d() != null) {
                    aVar2.d().setForeground(aVar2);
                } else {
                    imageView2.getOverlay().add(aVar2);
                }
                this.g = imageView2;
            }
        } else {
            c(imageView);
        }
    }

    public final void c(View view) {
        a aVar = this.f1503h;
        if (aVar != null && view == this.g) {
            Rect rect = new Rect();
            view.getDrawingRect(rect);
            aVar.setBounds(rect);
            aVar.i(view, (FrameLayout) null);
        }
    }

    public final void d(Context context) {
        TabLayout tabLayout = this.v;
        if (tabLayout.tabBackgroundResId == 0 || tabLayout.mDepthStyle == 2) {
            this.l = null;
            return;
        }
        Drawable drawable = AppCompatResources.getDrawable(context, tabLayout.tabBackgroundResId);
        this.l = drawable;
        if (drawable != null && drawable.isStateful()) {
            this.l.setState(getDrawableState());
        }
        ViewCompat.setBackground(this, this.l);
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
    }

    public final void e() {
        View view;
        int i2;
        int i7;
        int i8;
        int i10;
        ConstraintLayout constraintLayout;
        int i11;
        ViewParent parent;
        c cVar = this.d;
        if (cVar != null) {
            view = cVar.e;
        } else {
            view = null;
        }
        if (view != null) {
            ViewParent parent2 = view.getParent();
            if (parent2 != this) {
                if (parent2 != null) {
                    ((ViewGroup) parent2).removeView(view);
                }
                View view2 = this.f1504i;
                if (!(view2 == null || (parent = view2.getParent()) == null)) {
                    ((ViewGroup) parent).removeView(this.f1504i);
                }
                addView(view);
            }
            this.f1504i = view;
            TextView textView = this.e;
            if (textView != null) {
                textView.setVisibility(8);
            }
            ImageView imageView = this.f;
            if (imageView != null) {
                imageView.setVisibility(8);
                this.f.setImageDrawable((Drawable) null);
            }
            TextView textView2 = this.u;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            TextView textView3 = (TextView) view.findViewById(16908308);
            this.f1505j = textView3;
            if (textView3 != null) {
                this.m = TextViewCompat.getMaxLines(textView3);
            }
            this.k = (ImageView) view.findViewById(16908294);
        } else {
            View view3 = this.f1504i;
            if (view3 != null) {
                removeView(view3);
                this.f1504i = null;
            }
            this.f1505j = null;
            this.k = null;
        }
        boolean z = false;
        if (this.f1504i != null || this.d == null) {
            TextView textView4 = this.f1505j;
            if (!(textView4 == null && this.k == null)) {
                f(textView4, this.k, false);
            }
        } else {
            ConstraintLayout constraintLayout2 = this.f1507p;
            TabLayout tabLayout = this.v;
            if (constraintLayout2 == null) {
                if (tabLayout.mDepthStyle == 2) {
                    this.f1507p = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.sesl_tabs_sub_tab_layout, this, false);
                } else {
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.sesl_tabs_main_tab_layout, this, false);
                    this.f1507p = constraintLayout3;
                    View findViewById = constraintLayout3.findViewById(R.id.main_tab_touch_background);
                    this.r = findViewById;
                    if (findViewById != null && this.d.b == null) {
                        Context context = getContext();
                        if (SeslMisc.isLightTheme(getContext())) {
                            i11 = R.drawable.sesl_tablayout_maintab_touch_background_light;
                        } else {
                            i11 = R.drawable.sesl_tablayout_maintab_touch_background_dark;
                        }
                        ViewCompat.setBackground(findViewById, ContextCompat.getDrawable(context, i11));
                        this.r.setAlpha(0.0f);
                    }
                }
            }
            if (this.q == null) {
                this.q = (SeslTabRoundRectIndicator) this.f1507p.findViewById(R.id.indicator);
            }
            int i12 = -1;
            if (tabLayout.mDepthStyle != 2) {
                SeslTabRoundRectIndicator seslTabRoundRectIndicator = this.q;
                if (seslTabRoundRectIndicator != null) {
                    seslTabRoundRectIndicator.setSelectedIndicatorColor(tabLayout.mTabSelectedIndicatorColor);
                }
            } else if (!(this.q == null || tabLayout.mSubTabSelectedIndicatorColor == -1)) {
                this.q.setSelectedIndicatorColor(tabLayout.mSubTabSelectedIndicatorColor);
            }
            if (this.e == null) {
                this.e = (TextView) this.f1507p.findViewById(R.id.title);
            }
            this.m = TextViewCompat.getMaxLines(this.e);
            TextViewCompat.setTextAppearance(this.e, tabLayout.defaultTabTextAppearance);
            if (!isSelected() || tabLayout.selectedTabTextAppearance == -1) {
                TextViewCompat.setTextAppearance(this.e, tabLayout.tabTextAppearance);
            } else {
                TextViewCompat.setTextAppearance(this.e, tabLayout.selectedTabTextAppearance);
            }
            if (isSelected()) {
                this.e.setTypeface(tabLayout.mBoldTypeface);
            } else {
                this.e.setTypeface(tabLayout.mNormalTypeface);
            }
            TabLayout.access$2300(tabLayout, this.e, (int) tabLayout.tabTextSize);
            this.e.setTextColor(tabLayout.tabTextColors);
            if (tabLayout.mDepthStyle == 2) {
                if (this.u == null) {
                    this.u = (TextView) this.f1507p.findViewById(R.id.sub_title);
                }
                TextView textView5 = this.u;
                if (textView5 != null) {
                    TextViewCompat.setTextAppearance(textView5, tabLayout.mSubTabSubTextAppearance);
                    this.u.setTextColor(tabLayout.mSubTabSubTextColors);
                }
                TextView textView6 = this.u;
                if (textView6 != null) {
                    TabLayout.access$2300(tabLayout, textView6, tabLayout.mSubTabTextSize);
                }
            }
            if (this.f == null && (constraintLayout = this.f1507p) != null) {
                this.f = (ImageView) constraintLayout.findViewById(R.id.icon);
            }
            TextView textView7 = this.e;
            TextView textView8 = this.u;
            f(textView7, this.f, true);
            if (textView8 != null) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textView7.getLayoutParams();
                boolean isEmpty = TextUtils.isEmpty((CharSequence) null);
                if (!isEmpty) {
                    i7 = -1;
                } else {
                    i7 = 0;
                }
                layoutParams.topToTop = i7;
                if (!isEmpty) {
                    i8 = -1;
                } else {
                    i8 = 0;
                }
                layoutParams.bottomToBottom = i8;
                if (!isEmpty) {
                    i10 = R.id.center_anchor;
                } else {
                    i10 = -1;
                }
                layoutParams.bottomToTop = i10;
                textView8.setText((CharSequence) null);
                if (!isEmpty) {
                    this.d.getClass();
                    textView8.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView8.setVisibility(8);
                }
            }
            int i13 = -2;
            if (tabLayout.mDepthStyle == 2) {
                if (tabLayout.mode == 0) {
                    i12 = -2;
                }
                if (!TextUtils.isEmpty((CharSequence) null)) {
                    i2 = tabLayout.mSubTabIndicator2ndHeight;
                } else {
                    i2 = tabLayout.mSubTabIndicatorHeight;
                }
                ConstraintLayout constraintLayout4 = this.f1507p;
                if (!(constraintLayout4 == null || constraintLayout4.getHeight() == i2)) {
                    z = true;
                }
                i13 = i12;
                i12 = i2;
            }
            ConstraintLayout constraintLayout5 = this.f1507p;
            if (constraintLayout5 != null && constraintLayout5.getParent() == null) {
                addView(this.f1507p, i13, i12);
            } else if (z) {
                removeView(this.f1507p);
                addView(this.f1507p, i13, i12);
            }
            b();
            ImageView imageView2 = this.f;
            if (imageView2 != null) {
                imageView2.addOnLayoutChangeListener(new k(this, imageView2));
            }
            TextView textView9 = this.e;
            if (textView9 != null) {
                textView9.addOnLayoutChangeListener(new k(this, textView9));
            }
        }
        if (cVar != null && !TextUtils.isEmpty((CharSequence) null)) {
            setContentDescription((CharSequence) null);
        }
    }

    public final void f(TextView textView, ImageView imageView, boolean z) {
        Drawable drawable;
        CharSequence charSequence;
        boolean z3;
        int i2;
        Drawable drawable2;
        c cVar = this.d;
        if (cVar == null || (drawable2 = cVar.b) == null) {
            drawable = null;
        } else {
            drawable = DrawableCompat.wrap(drawable2).mutate();
        }
        TabLayout tabLayout = this.v;
        if (drawable != null) {
            ColorStateList colorStateList = tabLayout.tabIconTint;
            if (colorStateList == null) {
                DrawableCompat.setTintList(drawable, tabLayout.tabTextColors);
            } else {
                DrawableCompat.setTintList(drawable, colorStateList);
            }
            PorterDuff.Mode mode = tabLayout.tabIconTintMode;
            if (mode != null) {
                DrawableCompat.setTintMode(drawable, mode);
            }
        }
        c cVar2 = this.d;
        if (cVar2 != null) {
            charSequence = cVar2.f1502c;
        } else {
            charSequence = null;
        }
        boolean z7 = false;
        if (imageView != null) {
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
                imageView.setVisibility(0);
                setVisibility(0);
            } else {
                imageView.setVisibility(8);
                imageView.setImageDrawable((Drawable) null);
            }
        }
        boolean isEmpty = TextUtils.isEmpty(charSequence);
        if (textView != null) {
            if (!isEmpty) {
                this.d.getClass();
                z3 = true;
            } else {
                z3 = false;
            }
            if (isEmpty) {
                charSequence = null;
            }
            textView.setText(charSequence);
            if (z3) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            textView.setVisibility(i2);
            if (!isEmpty) {
                setVisibility(0);
            }
            z7 = z3;
        }
        if (z && imageView != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            if (z7 && imageView.getVisibility() == 0) {
                if (tabLayout.mIconTextGap != -1) {
                    int unused = tabLayout.mIconTextGap;
                } else {
                    u.b(getContext(), 8);
                }
            }
        }
        TooltipCompat.setTooltipText(this, (CharSequence) null);
    }

    public int getContentHeight() {
        View[] viewArr = {this.e, this.f, this.f1504i};
        int i2 = 0;
        int i7 = 0;
        boolean z = false;
        for (int i8 = 0; i8 < 3; i8++) {
            View view = viewArr[i8];
            if (view != null && view.getVisibility() == 0) {
                if (z) {
                    i7 = Math.min(i7, view.getTop());
                } else {
                    i7 = view.getTop();
                }
                if (z) {
                    i2 = Math.max(i2, view.getBottom());
                } else {
                    i2 = view.getBottom();
                }
                z = true;
            }
        }
        return i2 - i7;
    }

    public int getContentWidth() {
        View[] viewArr = {this.e, this.f, this.f1504i};
        int i2 = 0;
        int i7 = 0;
        boolean z = false;
        for (int i8 = 0; i8 < 3; i8++) {
            View view = viewArr[i8];
            if (view != null && view.getVisibility() == 0) {
                if (z) {
                    i7 = Math.min(i7, view.getLeft());
                } else {
                    i7 = view.getLeft();
                }
                if (z) {
                    i2 = Math.max(i2, view.getRight());
                } else {
                    i2 = view.getRight();
                }
                z = true;
            }
        }
        return i2 - i7;
    }

    public c getTab() {
        return this.d;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f1506o = getResources().getDimensionPixelOffset(R.dimen.sesl_tab_icon_size);
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = this.l;
        if (drawable != null) {
            TabLayout tabLayout = this.v;
            drawable.setBounds(-tabLayout.mMainTabSelectedSideMargin, 0, tabLayout.mMainTabSelectedSideMargin + getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
        a aVar = this.f1503h;
        if (aVar != null && aVar.isVisible()) {
            wrap.setContentDescription(this.f1503h.c());
        }
        wrap.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, this.d.d, 1, false, isSelected()));
        if (isSelected()) {
            wrap.setClickable(false);
            wrap.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }
        wrap.setRoleDescription(getResources().getString(R.string.item_view_role_description));
        TextView textView = this.t;
        if (textView == null || textView.getVisibility() != 0 || this.t.getContentDescription() == null) {
            TextView textView2 = this.s;
            if (textView2 != null && textView2.getVisibility() == 0 && this.s.getContentDescription() != null) {
                accessibilityNodeInfo.setContentDescription(getContentDescription() + ArcCommonLog.TAG_COMMA + this.s.getContentDescription());
                return;
            }
            return;
        }
        accessibilityNodeInfo.setContentDescription(getContentDescription() + ArcCommonLog.TAG_COMMA + this.t.getContentDescription());
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        TextView textView;
        super.onLayout(z, i2, i7, i8, i10);
        View view = this.r;
        if (!(view == null || view.getAnimation() == null || !this.r.getAnimation().hasEnded())) {
            this.r.setAlpha(0.0f);
        }
        if (this.f != null && this.d.b != null && (textView = this.e) != null && this.q != null && this.f1507p != null) {
            int measuredWidth = textView.getMeasuredWidth() + this.f1506o;
            TabLayout tabLayout = this.v;
            if (tabLayout.mIconTextGap != -1) {
                measuredWidth += tabLayout.mIconTextGap;
            }
            int abs = Math.abs((getWidth() - measuredWidth) / 2);
            if (u.c(this)) {
                int i11 = -abs;
                if (this.f.getRight() == this.f1507p.getRight()) {
                    this.e.offsetLeftAndRight(i11);
                    this.f.offsetLeftAndRight(i11);
                    this.q.offsetLeftAndRight(i11);
                }
            } else if (this.f.getLeft() == this.f1507p.getLeft()) {
                this.e.offsetLeftAndRight(abs);
                this.f.offsetLeftAndRight(abs);
                this.q.offsetLeftAndRight(abs);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d3, code lost:
        if (((r4 / r5.getPaint().getTextSize()) * r5.getLineWidth(0)) > ((float) ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()))) goto L_0x00f8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r12, int r13) {
        /*
            r11 = this;
            int r0 = android.view.View.MeasureSpec.getSize(r12)
            int r1 = android.view.View.MeasureSpec.getMode(r12)
            com.google.android.material.tabs.TabLayout r2 = r11.v
            int r3 = r2.getTabMaxWidth()
            int r4 = r2.mode
            r5 = 11
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = 1073741824(0x40000000, float:2.0)
            r8 = 0
            if (r4 == r5) goto L_0x003b
            r5 = 12
            if (r4 != r5) goto L_0x001e
            goto L_0x003b
        L_0x001e:
            int r4 = r2.mRequestedTabWidth
            r5 = -1
            if (r4 == r5) goto L_0x002e
            int r12 = r2.mRequestedTabWidth
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r7)
            goto L_0x004a
        L_0x002e:
            if (r3 <= 0) goto L_0x004a
            if (r1 == 0) goto L_0x0034
            if (r0 <= r3) goto L_0x004a
        L_0x0034:
            int r12 = r2.tabMaxWidth
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r6)
            goto L_0x004a
        L_0x003b:
            if (r1 != 0) goto L_0x0044
            int r12 = r2.tabMaxWidth
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r8)
            goto L_0x004a
        L_0x0044:
            if (r1 != r7) goto L_0x004a
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r7)
        L_0x004a:
            super.onMeasure(r12, r13)
            android.widget.TextView r0 = r11.e
            r1 = 2
            if (r0 == 0) goto L_0x00f8
            android.view.View r4 = r11.f1504i
            if (r4 != 0) goto L_0x00f8
            float r4 = r2.tabTextSize
            int r5 = (int) r4
            com.google.android.material.tabs.TabLayout.access$2300(r2, r0, r5)
            int r0 = r2.mDepthStyle
            if (r0 != r1) goto L_0x006b
            android.widget.TextView r0 = r11.u
            if (r0 == 0) goto L_0x006b
            int r5 = r2.mSubTabTextSize
            com.google.android.material.tabs.TabLayout.access$2300(r2, r0, r5)
        L_0x006b:
            int r0 = r11.m
            android.widget.ImageView r5 = r11.f
            r7 = 1
            if (r5 == 0) goto L_0x007d
            int r5 = r5.getVisibility()
            if (r5 != 0) goto L_0x007d
            int r0 = r2.mSubTabTextSize
            float r4 = (float) r0
            r0 = r7
            goto L_0x0089
        L_0x007d:
            android.widget.TextView r5 = r11.e
            if (r5 == 0) goto L_0x0089
            int r5 = r5.getLineCount()
            if (r5 <= r7) goto L_0x0089
            float r4 = r2.tabTextMultiLineSize
        L_0x0089:
            android.widget.TextView r5 = r11.e
            float r5 = r5.getTextSize()
            android.widget.TextView r9 = r11.e
            int r9 = r9.getLineCount()
            android.widget.TextView r10 = r11.e
            int r10 = androidx.core.widget.TextViewCompat.getMaxLines(r10)
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x00a3
            if (r10 < 0) goto L_0x00f8
            if (r0 == r10) goto L_0x00f8
        L_0x00a3:
            int r10 = r2.mode
            if (r10 != r7) goto L_0x00d6
            if (r5 <= 0) goto L_0x00d6
            if (r9 != r7) goto L_0x00d6
            android.widget.TextView r5 = r11.e
            android.text.Layout r5 = r5.getLayout()
            if (r5 == 0) goto L_0x00f8
            float r7 = r5.getLineWidth(r8)
            android.text.TextPaint r5 = r5.getPaint()
            float r5 = r5.getTextSize()
            float r5 = r4 / r5
            float r5 = r5 * r7
            int r7 = r11.getMeasuredWidth()
            int r9 = r11.getPaddingLeft()
            int r7 = r7 - r9
            int r9 = r11.getPaddingRight()
            int r7 = r7 - r9
            float r7 = (float) r7
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x00d6
            goto L_0x00f8
        L_0x00d6:
            android.widget.TextView r5 = r11.e
            r5.setTextSize(r8, r4)
            android.widget.TextView r5 = r11.e
            int r4 = (int) r4
            com.google.android.material.tabs.TabLayout.access$2300(r2, r5, r4)
            int r4 = r2.mDepthStyle
            if (r4 != r1) goto L_0x00f0
            android.widget.TextView r4 = r11.u
            if (r4 == 0) goto L_0x00f0
            int r5 = r2.mSubTabTextSize
            com.google.android.material.tabs.TabLayout.access$2300(r2, r4, r5)
        L_0x00f0:
            android.widget.TextView r4 = r11.e
            r4.setMaxLines(r0)
            super.onMeasure(r12, r13)
        L_0x00f8:
            android.widget.TextView r12 = r11.f1505j
            if (r12 != 0) goto L_0x014c
            androidx.constraintlayout.widget.ConstraintLayout r12 = r11.f1507p
            if (r12 == 0) goto L_0x014c
            android.widget.TextView r12 = r11.e
            if (r12 == 0) goto L_0x014c
            com.google.android.material.tabs.c r12 = r11.d
            if (r12 == 0) goto L_0x014c
            int r12 = r2.mode
            if (r12 != 0) goto L_0x014c
            int r12 = r2.mDepthStyle
            if (r12 != r1) goto L_0x014c
            if (r3 <= 0) goto L_0x011a
            android.widget.TextView r12 = r11.e
            r12.measure(r3, r8)
            goto L_0x011f
        L_0x011a:
            android.widget.TextView r12 = r11.e
            r12.measure(r8, r8)
        L_0x011f:
            android.widget.TextView r12 = r11.e
            int r12 = r12.getMeasuredWidth()
            androidx.constraintlayout.widget.ConstraintLayout r0 = r11.f1507p
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            android.content.Context r2 = r11.getContext()
            android.content.res.Resources r2 = r2.getResources()
            r3 = 2131168523(0x7f070d0b, float:1.795135E38)
            int r2 = r2.getDimensionPixelSize(r3)
            int r2 = r2 * r1
            int r2 = r2 + r12
            r0.width = r2
            androidx.constraintlayout.widget.ConstraintLayout r12 = r11.f1507p
            r12.setLayoutParams(r0)
            int r12 = r0.width
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r6)
            super.onMeasure(r12, r13)
        L_0x014c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.d.onMeasure(int, int):void");
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        SeslTabRoundRectIndicator seslTabRoundRectIndicator;
        if (isEnabled()) {
            TabLayout tabLayout = this.v;
            if (!TabLayout.access$3500(tabLayout)) {
                View view = this.d.e;
                if (view != null) {
                    return super.onTouchEvent(motionEvent);
                }
                if (motionEvent == null || view != null) {
                    return false;
                }
                int action = motionEvent.getAction() & ScoverState.TYPE_NFC_SMART_COVER;
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                if (action == 0) {
                    this.n = false;
                    if (this.d.d != tabLayout.getSelectedTabPosition()) {
                        setSelected(true);
                        SeslTabRoundRectIndicator seslTabRoundRectIndicator2 = this.q;
                        if (seslTabRoundRectIndicator2 != null) {
                            seslTabRoundRectIndicator2.b();
                        }
                        c tabAt = tabLayout.getTabAt(tabLayout.getSelectedTabPosition());
                        if (tabAt != null) {
                            tabAt.g.setSelected(false);
                            SeslTabRoundRectIndicator seslTabRoundRectIndicator3 = tabAt.g.q;
                            if (seslTabRoundRectIndicator3 != null) {
                                seslTabRoundRectIndicator3.a();
                            }
                        }
                    } else if (this.d.d == tabLayout.getSelectedTabPosition() && (seslTabRoundRectIndicator = this.q) != null) {
                        seslTabRoundRectIndicator.b();
                    }
                } else if (action != 1) {
                    if (action != 2) {
                        if (action == 3) {
                            TabLayout.access$3800(tabLayout, this);
                        }
                    } else if (TabLayout.access$3700(tabLayout, this, (int) rawX, (int) rawY)) {
                        TabLayout.access$3800(tabLayout, this);
                    }
                } else if (!TabLayout.access$3700(tabLayout, this, (int) rawX, (int) rawY)) {
                    SeslTabRoundRectIndicator seslTabRoundRectIndicator4 = this.q;
                    if (seslTabRoundRectIndicator4 != null) {
                        seslTabRoundRectIndicator4.c();
                        this.q.onTouchEvent(motionEvent);
                    }
                    performClick();
                    this.n = true;
                }
                return super.onTouchEvent(motionEvent);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public final boolean performClick() {
        if (this.n) {
            this.n = false;
            return true;
        }
        boolean performClick = super.performClick();
        if (this.d == null) {
            return performClick;
        }
        if (!performClick) {
            playSoundEffect(0);
        }
        this.d.a();
        return true;
    }

    public void setEnabled(boolean z) {
        int i2;
        super.setEnabled(z);
        View view = this.r;
        if (view != null) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
    }

    public void setSelected(boolean z) {
        int i2;
        Typeface typeface;
        if (isEnabled()) {
            isSelected();
            super.setSelected(z);
            TextView textView = this.e;
            if (textView != null) {
                textView.setSelected(z);
                TextView textView2 = this.e;
                boolean isSelected = isSelected();
                TabLayout tabLayout = this.v;
                if (isSelected) {
                    typeface = tabLayout.mBoldTypeface;
                } else {
                    typeface = tabLayout.mNormalTypeface;
                }
                textView2.setTypeface(typeface);
            }
            ImageView imageView = this.f;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.f1504i;
            if (view != null) {
                view.setSelected(z);
            }
            SeslTabRoundRectIndicator seslTabRoundRectIndicator = this.q;
            if (seslTabRoundRectIndicator != null) {
                seslTabRoundRectIndicator.setSelected(z);
                if (!TextUtils.isEmpty((CharSequence) null)) {
                    SeslTabRoundRectIndicator seslTabRoundRectIndicator2 = this.q;
                    Context context = getContext();
                    if (SeslMisc.isLightTheme(getContext())) {
                        i2 = R.drawable.sesl_tablayout_subtab_subtext_indicator_background_light;
                    } else {
                        i2 = R.drawable.sesl_tablayout_subtab_subtext_indicator_background_dark;
                    }
                    ViewCompat.setBackground(seslTabRoundRectIndicator2, ContextCompat.getDrawable(context, i2));
                }
            }
            TextView textView3 = this.u;
            if (textView3 != null) {
                textView3.setSelected(z);
            }
        }
    }

    public void setTab(c cVar) {
        boolean z;
        if (cVar != this.d) {
            this.d = cVar;
            e();
            c cVar2 = this.d;
            if (cVar2 != null) {
                TabLayout tabLayout = cVar2.f;
                if (tabLayout != null) {
                    int selectedTabPosition = tabLayout.getSelectedTabPosition();
                    if (selectedTabPosition != -1 && selectedTabPosition == cVar2.d) {
                        z = true;
                        setSelected(z);
                    }
                } else {
                    throw new IllegalArgumentException("Tab not attached to a TabLayout");
                }
            }
            z = false;
            setSelected(z);
        }
    }
}
