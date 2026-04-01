package H3;

import android.content.Context;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoExportCmd;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.scsp.common.FeatureConfigurator;
import com.samsung.scsp.error.FaultBarrier;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements DataCollectionDelegate.OnDataCompletionListener, MediaScannerListener, FaultBarrier.ThrowableSupplier, FutureListener {
    public final /* synthetic */ boolean d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ i(FaceClusterMergeDelegate faceClusterMergeDelegate, boolean z, String str) {
        this.e = faceClusterMergeDelegate;
        this.d = z;
        this.f = str;
    }

    public Object get() {
        return FeatureConfigurator.lambda$get$1((Context) this.e, (String) this.f, this.d);
    }

    public void onCompleted() {
        ((MotionPhotoExportCmd) this.e).lambda$create$3((String) this.f, this.d);
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        ((FileOpCmd) this.e).lambda$startRenameAlbumCmd$16((MediaItem) this.f, this.d, eventContext, arrayList);
    }

    public void onFutureDone(Future future) {
        ((FaceClusterMergeDelegate) this.e).lambda$executeMerge$8(this.d, (String) this.f, future);
    }

    public /* synthetic */ i(Object obj, Object obj2, boolean z) {
        this.e = obj;
        this.f = obj2;
        this.d = z;
    }
}
