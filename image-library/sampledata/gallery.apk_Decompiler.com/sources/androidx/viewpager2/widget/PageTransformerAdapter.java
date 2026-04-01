package androidx.viewpager2.widget;

import A.a;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PageTransformerAdapter extends ViewPager2.OnPageChangeCallback {
    private final LinearLayoutManager mLayoutManager;
    private ViewPager2.PageTransformer mPageTransformer;

    public PageTransformerAdapter(LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
    }

    public ViewPager2.PageTransformer getPageTransformer() {
        return this.mPageTransformer;
    }

    public void onPageScrolled(int i2, float f, int i7) {
        if (this.mPageTransformer != null) {
            float f5 = -f;
            int i8 = 0;
            while (i8 < this.mLayoutManager.getChildCount()) {
                View childAt = this.mLayoutManager.getChildAt(i8);
                if (childAt != null) {
                    this.mPageTransformer.transformPage(childAt, ((float) (this.mLayoutManager.getPosition(childAt) - i2)) + f5);
                    i8++;
                } else {
                    Locale locale = Locale.US;
                    throw new IllegalStateException(a.d(i8, this.mLayoutManager.getChildCount(), "LayoutManager returned a null child at pos ", "/", " while transforming pages"));
                }
            }
        }
    }

    public void setPageTransformer(ViewPager2.PageTransformer pageTransformer) {
        this.mPageTransformer = pageTransformer;
    }

    public void onPageScrollStateChanged(int i2) {
    }

    public void onPageSelected(int i2) {
    }
}
