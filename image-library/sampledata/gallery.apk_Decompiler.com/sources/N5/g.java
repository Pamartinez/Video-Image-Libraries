package n5;

import com.samsung.android.gallery.app.ui.list.search.category.people.SelectMePresenter;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2673a;
    public final /* synthetic */ SelectMePresenter b;

    public /* synthetic */ g(SelectMePresenter selectMePresenter, int i2) {
        this.f2673a = i2;
        this.b = selectMePresenter;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        int i2 = this.f2673a;
        SelectMePresenter selectMePresenter = this.b;
        switch (i2) {
            case 0:
                return selectMePresenter.lambda$selectMe$1(jobContext);
            default:
                return selectMePresenter.lambda$handleMergeDone$0(jobContext);
        }
    }
}
