package C4;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AlbumsBaseFragment f2284a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f2285c;
    public final /* synthetic */ AlbumsViewDummyAdapter d;
    public final /* synthetic */ int e;

    public /* synthetic */ e(AlbumsBaseFragment albumsBaseFragment, String str, int i2, AlbumsViewDummyAdapter albumsViewDummyAdapter, int i7) {
        this.f2284a = albumsBaseFragment;
        this.b = str;
        this.f2285c = i2;
        this.d = albumsViewDummyAdapter;
        this.e = i7;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2284a.lambda$createViewPool$0(this.b, this.f2285c, this.d, this.e, jobContext);
    }
}
