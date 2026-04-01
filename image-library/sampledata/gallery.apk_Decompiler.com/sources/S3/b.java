package S3;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.sharing.AddContentsToSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2419a;
    public final /* synthetic */ long b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f2420c;
    public final /* synthetic */ BaseCommand d;

    public /* synthetic */ b(BaseCommand baseCommand, long j2, long j3, int i2) {
        this.f2419a = i2;
        this.d = baseCommand;
        this.b = j2;
        this.f2420c = j3;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2419a) {
            case 0:
                return ((AddContentsToSharedAlbumCmd) this.d).lambda$addToSharedAlbum$1(this.b, this.f2420c, jobContext);
            default:
                return ((ChooseSharedAlbumCmd) this.d).lambda$addContentsToSharedAlbum$6(this.b, this.f2420c, jobContext);
        }
    }
}
