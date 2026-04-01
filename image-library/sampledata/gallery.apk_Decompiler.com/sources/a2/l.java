package A2;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l implements g {

    /* renamed from: a  reason: collision with root package name */
    public final ViewPager f32a;

    public l(ViewPager viewPager) {
        this.f32a = viewPager;
    }

    public final void onTabSelected(c cVar) {
        this.f32a.setCurrentItem(cVar.d);
    }

    public final void onTabReselected(c cVar) {
    }

    public final void onTabUnselected(c cVar) {
    }
}
