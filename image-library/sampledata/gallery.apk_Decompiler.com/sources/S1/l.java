package S1;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.search.SearchBar$ScrollingViewBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class l extends w {
    public final Rect f = new Rect();
    public final Rect g = new Rect();

    /* renamed from: h  reason: collision with root package name */
    public int f786h = 0;

    /* renamed from: i  reason: collision with root package name */
    public int f787i;

    public l() {
    }

    public final void b(CoordinatorLayout coordinatorLayout, View view, int i2) {
        AppBarLayout e = AppBarLayout.ScrollingViewBehavior.e(coordinatorLayout.getDependencies(view));
        if (e != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
            int paddingLeft = coordinatorLayout.getPaddingLeft() + layoutParams.leftMargin;
            int bottom = e.getBottom() + layoutParams.topMargin;
            int width = (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - layoutParams.rightMargin;
            int height = coordinatorLayout.getHeight();
            Rect rect = this.f;
            rect.set(paddingLeft, bottom, width, ((e.getBottom() + height) - coordinatorLayout.getPaddingBottom()) - layoutParams.bottomMargin);
            WindowInsetsCompat lastWindowInsets = coordinatorLayout.getLastWindowInsets();
            if (lastWindowInsets != null && ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(view)) {
                rect.left = lastWindowInsets.getSystemWindowInsetLeft() + rect.left;
                rect.right -= lastWindowInsets.getSystemWindowInsetRight();
            }
            int i7 = layoutParams.gravity;
            if (i7 == 0) {
                i7 = 8388659;
            }
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            Rect rect2 = this.g;
            GravityCompat.apply(i7, measuredWidth, measuredHeight, rect, rect2, i2);
            int d = d(e);
            view.layout(rect2.left, rect2.top - d, rect2.right, rect2.bottom - d);
            this.f786h = rect2.top - e.getBottom();
            return;
        }
        coordinatorLayout.onLayoutChild(view, i2);
        this.f786h = 0;
    }

    public final int d(View view) {
        int i2;
        int i7;
        if (this.f787i == 0) {
            return 0;
        }
        float f5 = 0.0f;
        if (view instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
            if (behavior instanceof AppBarLayout.BaseBehavior) {
                i2 = ((AppBarLayout.BaseBehavior) behavior).d();
            } else {
                i2 = 0;
            }
            if ((downNestedPreScrollRange == 0 || totalScrollRange + i2 > downNestedPreScrollRange) && (i7 = totalScrollRange - downNestedPreScrollRange) != 0) {
                f5 = (((float) i2) / ((float) i7)) + 1.0f;
            }
        }
        int i8 = this.f787i;
        return MathUtils.clamp((int) (f5 * ((float) i8)), 0, i8);
    }

    public final boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i2, int i7, int i8, int i10) {
        AppBarLayout e;
        int i11;
        WindowInsetsCompat lastWindowInsets;
        int i12 = view.getLayoutParams().height;
        int i13 = 0;
        if ((i12 != -1 && i12 != -2) || (e = AppBarLayout.ScrollingViewBehavior.e(coordinatorLayout.getDependencies(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i8);
        if (size <= 0) {
            size = coordinatorLayout.getHeight();
        } else if (ViewCompat.getFitsSystemWindows(e) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
            size += lastWindowInsets.getSystemWindowInsetBottom() + lastWindowInsets.getSystemWindowInsetTop();
        }
        int totalScrollRange = e.getTotalScrollRange() + size;
        int measuredHeight = e.getMeasuredHeight();
        if (this instanceof SearchBar$ScrollingViewBehavior) {
            view.setTranslationY((float) (-measuredHeight));
            size = totalScrollRange;
        } else if (e.useFloatingToolbar()) {
            view.setTranslationY(0.0f);
        } else {
            view.setTranslationY(0.0f);
            size = totalScrollRange - measuredHeight;
        }
        if (size >= 0) {
            i13 = size;
        }
        if (i12 == -1) {
            i11 = 1073741824;
        } else {
            i11 = Integer.MIN_VALUE;
        }
        coordinatorLayout.onMeasureChild(view, i2, i7, View.MeasureSpec.makeMeasureSpec(i13, i11), i10);
        return true;
    }

    public l(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
