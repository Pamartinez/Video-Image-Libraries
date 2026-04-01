package q5;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.module.creature.base.LoadFinishedListener;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements FutureListener, ThumbnailLoadedListener, LoadFinishedListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ EditCreatureNamePresenter e;

    public /* synthetic */ e(EditCreatureNamePresenter editCreatureNamePresenter, int i2) {
        this.d = i2;
        this.e = editCreatureNamePresenter;
    }

    public void onFutureDone(Future future) {
        this.e.lambda$onPositiveClicked$12(future);
    }

    public void onLoadFinished(ArrayList arrayList) {
        this.e.lambda$onContactPicked$2(arrayList);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        int i2 = this.d;
        EditCreatureNamePresenter editCreatureNamePresenter = this.e;
        switch (i2) {
            case 1:
                editCreatureNamePresenter.lambda$loadFaceImage$18(bitmap, uniqueKey, thumbKind);
                return;
            default:
                editCreatureNamePresenter.lambda$loadFaceImage$17(bitmap, uniqueKey, thumbKind);
                return;
        }
    }
}
