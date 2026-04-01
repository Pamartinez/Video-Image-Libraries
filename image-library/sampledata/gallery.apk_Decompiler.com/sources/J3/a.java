package J3;

import android.app.job.JobParameters;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.abstraction.CommitCreatureCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHidePresenter;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataStoriesBase;
import com.samsung.android.gallery.module.groupshot.GroupShotFormat;
import com.samsung.android.gallery.module.idleworker.IdleWorker;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2374a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2375c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i2) {
        this.f2374a = i2;
        this.b = obj;
        this.f2375c = obj2;
        this.d = obj3;
        this.e = obj4;
        this.f = obj5;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2374a) {
            case 0:
                return ((CommitCreatureCmd) this.b).lambda$addTag$0((String) this.f2375c, (CreatureNameData) this.d, (EventContext) this.e, (Bitmap) this.f, jobContext);
            case 1:
                return ((ChangeBestItemCmd) this.b).lambda$onExecute$1((GroupShotFormat) this.f2375c, (MediaItem[]) this.d, (MediaItem) this.e, (double[]) this.f, jobContext);
            case 2:
                return ((MediaDataStoriesBase) this.b).lambda$swap$2((ConcurrentHashMap) this.f2375c, (HashMap) this.d, (Cursor) this.e, (CountDownLatch) this.f, jobContext);
            case 3:
                return ((CreatureHidePresenter) this.b).lambda$saveChangedCreature$0((ArrayList) this.f2375c, (ArrayList) this.d, (ArrayList) this.e, (ArrayList) this.f, jobContext);
            default:
                return ((IdleWorker) this.b).lambda$execJob$3((IdleWorkerJob) this.f2375c, (Context) this.d, (AtomicInteger) this.e, (JobParameters) this.f, jobContext);
        }
    }
}
