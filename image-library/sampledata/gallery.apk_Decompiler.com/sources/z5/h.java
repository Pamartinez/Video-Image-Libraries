package z5;

import android.graphics.Bitmap;
import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.animation.FaceClusterAnimationHelper;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import k2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements q, FaceClusterAnimationHelper.EnteringCompletedAnimationListener, ThumbnailLoadedListener, FutureListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchCreatureHeaderView e;

    public /* synthetic */ h(SearchCreatureHeaderView searchCreatureHeaderView, int i2) {
        this.d = i2;
        this.e = searchCreatureHeaderView;
    }

    public void onFutureDone(Future future) {
        this.e.lambda$updateFaceClusterHeader$8(future);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        int i2 = this.d;
        SearchCreatureHeaderView searchCreatureHeaderView = this.e;
        switch (i2) {
            case 2:
                searchCreatureHeaderView.bindMainImage(bitmap, uniqueKey, thumbKind);
                return;
            default:
                searchCreatureHeaderView.bindImage(bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return this.e.lambda$initBottomNavigation$20(menuItem);
    }
}
