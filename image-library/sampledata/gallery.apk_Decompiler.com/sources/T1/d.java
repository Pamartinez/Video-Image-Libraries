package T1;

import android.view.View;
import androidx.appcompat.widget.SeslIndicator;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.model.view.ViewPagerAppBarView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements SeslIndicator.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewPagerAppBarView f796a;

    public d(ViewPagerAppBarView viewPagerAppBarView) {
        this.f796a = viewPagerAppBarView;
    }

    public final void a(int i2, View view) {
        ViewPager2 viewpager = this.f796a.getViewpager();
        if (viewpager != null) {
            viewpager.setCurrentItem(i2, true);
        }
    }
}
