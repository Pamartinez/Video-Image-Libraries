package q9;

import android.content.Context;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.knox.KnoxOperationTask;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ KnoxOperationTask e;

    public /* synthetic */ b(KnoxOperationTask knoxOperationTask, int i2) {
        this.d = i2;
        this.e = knoxOperationTask;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        KnoxOperationTask knoxOperationTask = this.e;
        switch (i2) {
            case 0:
                knoxOperationTask.operateInternal((Context) obj);
                return;
            case 1:
                knoxOperationTask.lambda$extractItemPath$1((MediaItem) obj);
                return;
            default:
                knoxOperationTask.lambda$extractItemPath$0((MediaItem) obj);
                return;
        }
    }
}
