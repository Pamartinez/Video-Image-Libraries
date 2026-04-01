package e5;

import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* renamed from: e5.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0452b implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PicturesFragment f2629a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2630c;
    public final /* synthetic */ PicturesViewDummyAdapter d;
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;

    public /* synthetic */ C0452b(PicturesFragment picturesFragment, String str, int i2, PicturesViewDummyAdapter picturesViewDummyAdapter, int i7, long j2) {
        this.f2629a = picturesFragment;
        this.b = str;
        this.f2630c = i2;
        this.d = picturesViewDummyAdapter;
        this.e = i7;
        this.f = j2;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2629a.lambda$createViewPool$5(this.b, this.f2630c, this.d, this.e, this.f, jobContext);
    }
}
