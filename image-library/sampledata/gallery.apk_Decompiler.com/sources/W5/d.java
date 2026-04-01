package w5;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultPresenter;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2738a;
    public final /* synthetic */ SearchClusterResultPresenter b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Toolbar f2739c;

    public /* synthetic */ d(SearchClusterResultPresenter searchClusterResultPresenter, Toolbar toolbar, int i2) {
        this.f2738a = i2;
        this.b = searchClusterResultPresenter;
        this.f2739c = toolbar;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2738a) {
            case 0:
                return this.b.lambda$updateToolbar$2(this.f2739c, jobContext);
            default:
                return this.b.lambda$updateSubTitle$3(this.f2739c, jobContext);
        }
    }
}
