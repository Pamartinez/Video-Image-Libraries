package Y9;

import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchHistory f2907a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f2908c;

    public /* synthetic */ a(SearchHistory searchHistory, String str, long j2) {
        this.f2907a = searchHistory;
        this.b = str;
        this.f2908c = j2;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2907a.lambda$insert$0(this.b, this.f2908c, jobContext);
    }
}
