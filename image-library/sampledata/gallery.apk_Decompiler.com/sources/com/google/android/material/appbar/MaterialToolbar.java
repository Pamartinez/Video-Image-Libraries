package com.google.android.material.appbar;

import P2.w;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import h2.q;
import java.util.ArrayList;
import java.util.Collections;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MaterialToolbar extends Toolbar {

    /* renamed from: i  reason: collision with root package name */
    public static final ImageView.ScaleType[] f1370i = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    public Integer d;
    public boolean e;
    public boolean f;
    public ImageView.ScaleType g;

    /* renamed from: h  reason: collision with root package name */
    public Boolean f1371h;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MaterialToolbar(android.content.Context r7, android.util.AttributeSet r8) {
        /*
            r6 = this;
            int r3 = androidx.appcompat.R$attr.toolbarStyle
            r0 = 2131953257(0x7f130669, float:1.954298E38)
            android.content.Context r7 = D2.a.a(r7, r8, r3, r0)
            r6.<init>(r7, r8, r3)
            android.content.Context r0 = r6.getContext()
            r7 = 0
            int[] r5 = new int[r7]
            int[] r2 = Q1.a.E
            r4 = 2131953257(0x7f130669, float:1.954298E38)
            r1 = r8
            android.content.res.TypedArray r8 = h2.p.d(r0, r1, r2, r3, r4, r5)
            r1 = 2
            boolean r2 = r8.hasValue(r1)
            r3 = -1
            if (r2 == 0) goto L_0x002c
            int r1 = r8.getColor(r1, r3)
            r6.setNavigationIconTint(r1)
        L_0x002c:
            r1 = 4
            boolean r1 = r8.getBoolean(r1, r7)
            r6.e = r1
            r1 = 3
            boolean r1 = r8.getBoolean(r1, r7)
            r6.f = r1
            r1 = 1
            int r1 = r8.getInt(r1, r3)
            if (r1 < 0) goto L_0x004a
            android.widget.ImageView$ScaleType[] r2 = f1370i
            int r3 = r2.length
            if (r1 >= r3) goto L_0x004a
            r1 = r2[r1]
            r6.g = r1
        L_0x004a:
            boolean r1 = r8.hasValue(r7)
            if (r1 == 0) goto L_0x005a
            boolean r1 = r8.getBoolean(r7, r7)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r6.f1371h = r1
        L_0x005a:
            r8.recycle()
            android.graphics.drawable.Drawable r8 = r6.getBackground()
            if (r8 != 0) goto L_0x0068
            android.content.res.ColorStateList r7 = android.content.res.ColorStateList.valueOf(r7)
            goto L_0x006c
        L_0x0068:
            android.content.res.ColorStateList r7 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.B(r8)
        L_0x006c:
            if (r7 == 0) goto L_0x0083
            x2.g r8 = new x2.g
            r8.<init>()
            r8.k(r7)
            r8.i(r0)
            float r7 = androidx.core.view.ViewCompat.getElevation(r6)
            r8.j(r7)
            androidx.core.view.ViewCompat.setBackground(r6, r8)
        L_0x0083:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.MaterialToolbar.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final void c(TextView textView, Pair pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = textView.getMeasuredWidth();
        int i2 = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i7 = measuredWidth2 + i2;
        int max = Math.max(Math.max(((Integer) pair.first).intValue() - i2, 0), Math.max(i7 - ((Integer) pair.second).intValue(), 0));
        if (max > 0) {
            i2 += max;
            i7 -= max;
            textView.measure(View.MeasureSpec.makeMeasureSpec(i7 - i2, 1073741824), textView.getMeasuredHeightAndState());
        }
        textView.layout(i2, textView.getTop(), i7, textView.getBottom());
    }

    public ImageView.ScaleType getLogoScaleType() {
        return this.g;
    }

    public Integer getNavigationIconTint() {
        return this.d;
    }

    public final void inflateMenu(int i2) {
        Menu menu = getMenu();
        boolean z = menu instanceof MenuBuilder;
        if (z) {
            ((MenuBuilder) menu).stopDispatchingItemsChanged();
        }
        super.inflateMenu(i2);
        if (z) {
            ((MenuBuilder) menu).startDispatchingItemsChanged();
        }
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        k.Q(this);
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        ImageView imageView;
        Drawable drawable;
        TextView textView;
        TextView textView2;
        super.onLayout(z, i2, i7, i8, i10);
        int i11 = 0;
        ImageView imageView2 = null;
        if (this.e || this.f) {
            ArrayList b = q.b(this, getTitle());
            boolean isEmpty = b.isEmpty();
            w wVar = q.f1777a;
            if (isEmpty) {
                textView = null;
            } else {
                textView = (TextView) Collections.min(b, wVar);
            }
            ArrayList b5 = q.b(this, getSubtitle());
            if (b5.isEmpty()) {
                textView2 = null;
            } else {
                textView2 = (TextView) Collections.max(b5, wVar);
            }
            if (!(textView == null && textView2 == null)) {
                int measuredWidth = getMeasuredWidth();
                int i12 = measuredWidth / 2;
                int paddingLeft = getPaddingLeft();
                int paddingRight = measuredWidth - getPaddingRight();
                for (int i13 = 0; i13 < getChildCount(); i13++) {
                    View childAt = getChildAt(i13);
                    if (!(childAt.getVisibility() == 8 || childAt == textView || childAt == textView2)) {
                        if (childAt.getRight() < i12 && childAt.getRight() > paddingLeft) {
                            paddingLeft = childAt.getRight();
                        }
                        if (childAt.getLeft() > i12 && childAt.getLeft() < paddingRight) {
                            paddingRight = childAt.getLeft();
                        }
                    }
                }
                Pair pair = new Pair(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
                if (this.e && textView != null) {
                    c(textView, pair);
                }
                if (this.f && textView2 != null) {
                    c(textView2, pair);
                }
            }
        }
        Drawable logo = getLogo();
        if (logo != null) {
            while (true) {
                if (i11 >= getChildCount()) {
                    break;
                }
                View childAt2 = getChildAt(i11);
                if ((childAt2 instanceof ImageView) && (drawable = imageView.getDrawable()) != null && drawable.getConstantState() != null && drawable.getConstantState().equals(logo.getConstantState())) {
                    imageView2 = (ImageView) childAt2;
                    break;
                }
                i11++;
            }
        }
        if (imageView2 != null) {
            Boolean bool = this.f1371h;
            if (bool != null) {
                imageView2.setAdjustViewBounds(bool.booleanValue());
            }
            ImageView.ScaleType scaleType = this.g;
            if (scaleType != null) {
                imageView2.setScaleType(scaleType);
            }
        }
    }

    public void setElevation(float f5) {
        super.setElevation(f5);
        k.O(this, f5);
    }

    public void setLogoAdjustViewBounds(boolean z) {
        Boolean bool = this.f1371h;
        if (bool == null || bool.booleanValue() != z) {
            this.f1371h = Boolean.valueOf(z);
            requestLayout();
        }
    }

    public void setLogoScaleType(ImageView.ScaleType scaleType) {
        if (this.g != scaleType) {
            this.g = scaleType;
            requestLayout();
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (!(drawable == null || this.d == null)) {
            drawable = DrawableCompat.wrap(drawable.mutate());
            DrawableCompat.setTint(drawable, this.d.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i2) {
        this.d = Integer.valueOf(i2);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z) {
        if (this.f != z) {
            this.f = z;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z) {
        if (this.e != z) {
            this.e = z;
            requestLayout();
        }
    }
}
