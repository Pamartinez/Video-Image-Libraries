package A2;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements ViewPager.OnPageChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f30a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f31c;

    public i(TabLayout tabLayout) {
        this.f30a = new WeakReference(tabLayout);
    }

    public final void onPageScrollStateChanged(int i2) {
        this.b = this.f31c;
        this.f31c = i2;
        TabLayout tabLayout = (TabLayout) this.f30a.get();
        if (tabLayout != null) {
            tabLayout.updateViewPagerScrollState(this.f31c);
        }
    }

    public final void onPageScrolled(int i2, float f, int i7) {
        boolean z;
        TabLayout tabLayout = (TabLayout) this.f30a.get();
        if (tabLayout != null) {
            int i8 = this.f31c;
            boolean z3 = true;
            if (i8 != 2 || this.b == 1) {
                z = true;
            } else {
                z = true;
                z3 = false;
            }
            if (i8 == 2 && this.b == 0) {
                z = false;
            }
            tabLayout.setScrollPosition(i2, f, z3, z, false);
        }
    }

    public final void onPageSelected(int i2) {
        boolean z;
        TabLayout tabLayout = (TabLayout) this.f30a.get();
        if (tabLayout != null && tabLayout.getSelectedTabPosition() != i2 && i2 < tabLayout.getTabCount()) {
            int i7 = this.f31c;
            if (i7 == 0 || (i7 == 2 && this.b == 0)) {
                z = true;
            } else {
                z = false;
            }
            tabLayout.selectTab(tabLayout.getTabAt(i2), z);
        }
    }
}
