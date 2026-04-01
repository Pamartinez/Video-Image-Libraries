package z5;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DataCollectionDelegate.OnDataCompletionListener, FutureListener, ThumbnailLoadedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FaceClusterMergeDelegate e;

    public /* synthetic */ a(FaceClusterMergeDelegate faceClusterMergeDelegate, int i2) {
        this.d = i2;
        this.e = faceClusterMergeDelegate;
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        this.e.lambda$showMergePeopleChoiceDialog$6(eventContext, arrayList);
    }

    public void onFutureDone(Future future) {
        int i2 = this.d;
        FaceClusterMergeDelegate faceClusterMergeDelegate = this.e;
        switch (i2) {
            case 1:
                faceClusterMergeDelegate.lambda$handleMergeCancel$5(future);
                return;
            default:
                faceClusterMergeDelegate.lambda$reloadLastMergedItem$11(future);
                return;
        }
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.e.lambda$onBackPressed$15(bitmap, uniqueKey, thumbKind);
    }
}
