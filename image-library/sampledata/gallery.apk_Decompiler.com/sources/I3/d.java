package I3;

import com.samsung.android.gallery.app.controller.creature.MergeCreatureChoiceCmd;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements FutureListener {
    public final /* synthetic */ MergeCreatureChoiceCmd d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f2360h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f2361i;

    public /* synthetic */ d(MergeCreatureChoiceCmd mergeCreatureChoiceCmd, String str, String str2, String str3, String str4, String str5) {
        this.d = mergeCreatureChoiceCmd;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.f2360h = str4;
        this.f2361i = str5;
    }

    public final void onFutureDone(Future future) {
        this.d.lambda$executeMerge$2(this.e, this.f, this.g, this.f2360h, this.f2361i, future);
    }
}
