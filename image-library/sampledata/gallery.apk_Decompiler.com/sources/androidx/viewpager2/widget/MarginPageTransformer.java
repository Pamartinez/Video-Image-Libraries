package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewParent;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MarginPageTransformer implements ViewPager2.PageTransformer {
    private final int mMarginPx;

    public MarginPageTransformer(int i2) {
        Preconditions.checkArgumentNonnegative(i2, "Margin must be non-negative");
        this.mMarginPx = i2;
    }

    private ViewPager2 requireViewPager(View view) {
        ViewParent parent = view.getParent();
        ViewParent parent2 = parent.getParent();
        if ((parent instanceof RecyclerView) && (parent2 instanceof ViewPager2)) {
            return (ViewPager2) parent2;
        }
        throw new IllegalStateException("Expected the page view to be managed by a ViewPager2 instance.");
    }

    public void transformPage(View view, float f) {
        ViewPager2 requireViewPager = requireViewPager(view);
        float f5 = ((float) this.mMarginPx) * f;
        if (requireViewPager.getOrientation() == 0) {
            if (requireViewPager.isRtl()) {
                f5 = -f5;
            }
            view.setTranslationX(f5);
            return;
        }
        view.setTranslationY(f5);
    }
}
