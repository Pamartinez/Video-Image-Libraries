package l5;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.location.LocationCategoryDummyAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.location.SearchLocationFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryDummyAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryFragment;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* renamed from: l5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0482a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2664a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ CategoryFragment f2665c;
    public final /* synthetic */ RecyclerView.Adapter d;

    public /* synthetic */ C0482a(CategoryFragment categoryFragment, int i2, RecyclerView.Adapter adapter, int i7) {
        this.f2664a = i7;
        this.f2665c = categoryFragment;
        this.b = i2;
        this.d = adapter;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2664a) {
            case 0:
                return ((SearchLocationFragment) this.f2665c).lambda$createViewPool$1(this.b, (LocationCategoryDummyAdapter) this.d, jobContext);
            default:
                return ((CreatureCategoryFragment) this.f2665c).lambda$createViewPool$0(this.b, (CreatureCategoryDummyAdapter) this.d, jobContext);
        }
    }
}
