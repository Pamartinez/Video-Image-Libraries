package D7;

import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.controller.album.UpdateAlbumSyncStatusCmd;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV2;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.RemasterGifImageLoader;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements GifAnimation.AnimationFrameUpdateListener, FutureListener, PropertyAnimator.PropertyAnimationListener, ThumbnailLoadedListener {
    public final /* synthetic */ boolean d;
    public final /* synthetic */ Object e;

    public /* synthetic */ l(Object obj, boolean z) {
        this.e = obj;
        this.d = z;
    }

    public void onAnimationEnd(View view) {
        ((PicturesPinchAnimationManagerV2) this.e).lambda$addDataAnimator$2(this.d, view);
    }

    public void onAnimationFrameUpdated(Bitmap bitmap) {
        ((RemasterGifImageLoader) this.e).lambda$createGifAnimation$1(this.d, bitmap);
    }

    public void onFutureDone(Future future) {
        ((UpdateAlbumSyncStatusCmd) this.e).lambda$onExecute$1(this.d, future);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ((FaceClusterMergeDelegate) this.e).lambda$executeLastMerge$13(this.d, bitmap, uniqueKey, thumbKind);
    }
}
