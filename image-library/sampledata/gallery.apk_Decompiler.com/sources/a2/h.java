package A2;

import android.database.DataSetObserver;
import com.google.android.material.tabs.TabLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends DataSetObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TabLayout f29a;

    public h(TabLayout tabLayout) {
        this.f29a = tabLayout;
    }

    public final void onChanged() {
        this.f29a.populateFromPagerAdapter();
    }

    public final void onInvalidated() {
        this.f29a.populateFromPagerAdapter();
    }
}
