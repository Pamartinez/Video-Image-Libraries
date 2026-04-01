package I3;

import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MergeCreatureMultipleCmd f2367a;
    public final /* synthetic */ String[] b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2368c;
    public final /* synthetic */ MediaItem[] d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ String f;

    public /* synthetic */ g(MergeCreatureMultipleCmd mergeCreatureMultipleCmd, String[] strArr, String str, MediaItem[] mediaItemArr, MediaItem mediaItem, String str2) {
        this.f2367a = mergeCreatureMultipleCmd;
        this.b = strArr;
        this.f2368c = str;
        this.d = mediaItemArr;
        this.e = mediaItem;
        this.f = str2;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        return this.f2367a.lambda$executeMerge$5(this.b, this.f2368c, this.d, this.e, this.f, jobContext);
    }
}
