package com.google.android.material.chip;

import L7.l;
import a6.C0419b;
import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.text.TextUtilsCompat;
import c2.q;
import c2.r;
import c2.t;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslExpandableContainer extends FrameLayout {

    /* renamed from: o  reason: collision with root package name */
    public static final /* synthetic */ int f1445o = 0;
    public final HorizontalScrollView d;
    public final LinearLayout e;
    public final t f;
    public final View g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f1446h = false;

    /* renamed from: i  reason: collision with root package name */
    public final int f1447i;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f1448j = true;
    public int k = 0;
    public boolean l;
    public final boolean m = true;
    public final boolean n;

    public SeslExpandableContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, -1, -1);
        boolean z;
        if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            z = true;
        } else {
            z = false;
        }
        this.n = z;
        View inflate = LayoutInflater.from(context).inflate(R.layout.sesl_expandable_container, (ViewGroup) null);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) inflate.findViewById(R.id.sesl_scroll_view);
        this.d = horizontalScrollView;
        View findViewById = inflate.findViewById(R.id.sesl_padding_view);
        this.g = findViewById;
        horizontalScrollView.setPaddingRelative(0, 0, context.getResources().getDimensionPixelSize(R.dimen.padding_view_width), 0);
        findViewById.setVisibility(8);
        horizontalScrollView.setOnScrollChangeListener(new l(1, this));
        this.e = (LinearLayout) inflate.findViewById(R.id.sesl_scrolling_chips_container);
        addView(inflate);
        int generateViewId = View.generateViewId();
        this.f1447i = generateViewId;
        t tVar = new t(context);
        this.f = tVar;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, context.getResources().getDimensionPixelSize(R.dimen.expansion_button_margin_top), 0, 0);
        tVar.setLayoutParams(layoutParams);
        tVar.setBackground(context.getDrawable(R.drawable.sesl_expansion_button_background));
        tVar.setImageDrawable(context.getDrawable(R.drawable.sesl_expansion_button_foreground));
        tVar.setAutomaticDisappear(true);
        tVar.setExpanded(false);
        tVar.setFloated(true);
        tVar.setVisibility(8);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        relativeLayout.setId(generateViewId);
        if (z) {
            relativeLayout.setGravity(3);
        } else {
            relativeLayout.setGravity(5);
        }
        addView(relativeLayout);
        relativeLayout.addView(tVar);
    }

    public final void a() {
        setLayoutTransition((LayoutTransition) null);
        boolean z = this.f1446h;
        View view = this.g;
        boolean z3 = this.n;
        t tVar = this.f;
        HorizontalScrollView horizontalScrollView = this.d;
        LinearLayout linearLayout = this.e;
        int i2 = 1;
        if (z) {
            if (linearLayout.getChildCount() > 0) {
                tVar.setAutomaticDisappear(false);
                this.k = horizontalScrollView.getScrollX();
                int childCount = linearLayout.getChildCount();
                View[] viewArr = new View[childCount];
                for (int i7 = 0; i7 < linearLayout.getChildCount(); i7++) {
                    viewArr[i7] = linearLayout.getChildAt(i7);
                }
                if (z3) {
                    Collections.reverse(Arrays.asList(viewArr));
                }
                int i8 = 0;
                for (int i10 = 0; i10 < childCount; i10++) {
                    View view2 = viewArr[i10];
                    if (!this.f1448j || view2.getId() != view.getId()) {
                        linearLayout.removeView(view2);
                        addView(view2, i2);
                        i8 += view2.getHeight();
                        i2++;
                    }
                }
                horizontalScrollView.setVisibility(8);
                if (tVar.getVisibility() != 0 && i8 > 0) {
                    tVar.setVisibility(0);
                }
            }
        } else if (getChildCount() > 2) {
            tVar.setAutomaticDisappear(true);
            horizontalScrollView.setVisibility(0);
            int childCount2 = getChildCount();
            View[] viewArr2 = new View[childCount2];
            for (int i11 = 0; i11 < getChildCount(); i11++) {
                viewArr2[i11] = getChildAt(i11);
            }
            if (z3) {
                Collections.reverse(Arrays.asList(viewArr2));
            }
            int i12 = 0;
            for (int i13 = 0; i13 < childCount2; i13++) {
                View view3 = viewArr2[i13];
                if (!this.l && (view3 instanceof SeslChipGroup)) {
                    ((SeslChipGroup) view3).setMaxChipWidth(getWidth());
                    this.l = true;
                }
                int id = view3.getId();
                if (!(id == horizontalScrollView.getId() || id == this.f1447i || id == view.getId())) {
                    removeView(view3);
                    linearLayout.addView(view3, i12);
                    i12++;
                }
            }
            horizontalScrollView.scrollTo(this.k, 0);
            b();
        }
    }

    public final void b() {
        boolean z;
        int scrollContentsWidth = getScrollContentsWidth();
        int width = getWidth() + 10;
        View view = this.g;
        int width2 = view.getWidth();
        t tVar = this.f;
        boolean z3 = this.f1448j;
        if (z3) {
            if ((view.getVisibility() == 0 && scrollContentsWidth - width2 > width) || (view.getVisibility() == 8 && scrollContentsWidth > width)) {
                if (tVar.getVisibility() != 0) {
                    tVar.setVisibility(0);
                }
                tVar.setOnClickListener(new C0419b(4, this));
            } else if (tVar.getVisibility() == 0) {
                tVar.setVisibility(4);
            }
        } else if (scrollContentsWidth > width) {
            if (tVar.getVisibility() != 0) {
                tVar.setVisibility(0);
            }
            tVar.setOnClickListener(new C0419b(4, this));
        } else if (tVar.getVisibility() == 0) {
            tVar.setVisibility(4);
        }
        if (this.m) {
            HorizontalScrollView horizontalScrollView = this.d;
            if (horizontalScrollView.getVisibility() != 0) {
                return;
            }
            if (!z3 || ((z && horizontalScrollView.getScrollX() > getPaddingView().getWidth() / 2) || (!(z = this.n) && horizontalScrollView.getScrollX() < getScrollContentsWidth() - getWidth()))) {
                tVar.setFloated(true);
            } else {
                tVar.setFloated(false);
            }
        }
    }

    public t getExpansionButton() {
        return this.f;
    }

    public View getPaddingView() {
        return this.g;
    }

    public int getScrollContentsWidth() {
        int width;
        int i2 = 0;
        if (this.f1446h) {
            return 0;
        }
        int i7 = 0;
        while (true) {
            LinearLayout linearLayout = this.e;
            if (i2 >= linearLayout.getChildCount()) {
                return i7;
            }
            View childAt = linearLayout.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                if (childAt instanceof SeslChipGroup) {
                    width = ((SeslChipGroup) childAt).getTotalWidth();
                } else {
                    width = childAt.getWidth();
                }
                i7 = width + i7;
            }
            i2++;
        }
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        a();
    }

    public void setExpanded(boolean z) {
        this.f1446h = z;
        a();
        post(new q(this, 0));
        Log.i("SeslExpandableContainer", "expansion state: " + z);
    }

    public void setExpansionBackGroundImage(Drawable drawable) {
        this.f.setBackground(drawable);
        Log.i("SeslExpandableContainer", "expansion button background changed");
    }

    public void setExpansionImageDrawable(Drawable drawable) {
        this.f.setImageDrawable(drawable);
        Log.i("SeslExpandableContainer", "expansion button image changed");
    }

    public void setOnExpansionButtonClickedListener(r rVar) {
    }
}
