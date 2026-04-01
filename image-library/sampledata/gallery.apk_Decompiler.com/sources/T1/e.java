package T1;

import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.model.view.ViewPagerAppBarView;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends AccessibilityDelegateCompat {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewPagerAppBarView f797a;

    public e(ViewPagerAppBarView viewPagerAppBarView) {
        this.f797a = viewPagerAppBarView;
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        RecyclerView.Adapter adapter;
        ViewPager2 viewpager;
        j.e(view, "host");
        j.e(accessibilityNodeInfoCompat, "info");
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        ViewPagerAppBarView viewPagerAppBarView = this.f797a;
        ViewPager2 viewpager2 = viewPagerAppBarView.getViewpager();
        if (viewpager2 != null && (adapter = viewpager2.getAdapter()) != null && adapter.getItemCount() == 1 && (viewpager = viewPagerAppBarView.getViewpager()) != null && viewpager.getCurrentItem() == 0) {
            accessibilityNodeInfoCompat.setClassName("");
        }
    }
}
