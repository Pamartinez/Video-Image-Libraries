package I3;

import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.module.search.languagepack.NeuralTranslator;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements FutureListener, OnCompleteListener {
    public final /* synthetic */ long d;
    public final /* synthetic */ Object e;

    public /* synthetic */ h(Object obj, long j2) {
        this.e = obj;
        this.d = j2;
    }

    public void onComplete(Task task) {
        ((NeuralTranslator) this.e).lambda$identifyLanguage$1(this.d, task);
    }

    public void onFutureDone(Future future) {
        ((MergeCreatureMultipleCmd) this.e).lambda$executeMerge$6(this.d, future);
    }
}
