package T1;

import androidx.appcompat.widget.SeslIndicator;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.model.view.BasicViewPagerAppBarView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends ViewPager2.OnPageChangeCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BasicViewPagerAppBarView f795a;

    public b(BasicViewPagerAppBarView basicViewPagerAppBarView) {
        this.f795a = basicViewPagerAppBarView;
    }

    public final void onPageSelected(int i2) {
        SeslIndicator indicator;
        BasicViewPagerAppBarView basicViewPagerAppBarView = this.f795a;
        if (!basicViewPagerAppBarView.isDeleteAnimatorRunning && (indicator = basicViewPagerAppBarView.getIndicator()) != null) {
            indicator.setSelectedPosition(i2);
        }
    }
}
