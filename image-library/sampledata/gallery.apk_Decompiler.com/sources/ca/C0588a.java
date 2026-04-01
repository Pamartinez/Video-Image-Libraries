package ca;

import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* renamed from: ca.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0588a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchWordCollector f2912a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ SearchWordCollector.Type f2913c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ C0588a(SearchWordCollector searchWordCollector, String str, SearchWordCollector.Type type, boolean z, boolean z3) {
        this.f2912a = searchWordCollector;
        this.b = str;
        this.f2913c = type;
        this.d = z;
        this.e = z3;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2912a.lambda$collect$0(this.b, this.f2913c, this.d, this.e, jobContext);
    }
}
