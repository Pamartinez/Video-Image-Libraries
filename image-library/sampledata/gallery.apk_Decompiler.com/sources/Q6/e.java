package q6;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.SeslTouchTargetDelegate;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureDialogPresenter;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResultViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageRecyclerAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedDataHolder;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ForceSwipeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.TransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ContentViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.CloudDownloader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MotionPhotoPlayViewerStateHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.XmpTypeLoader;
import com.samsung.android.gallery.image360.activity.options.PlayBackOptionsFragment;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.location.LocationUpdater;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.details.DetailsListVideoHelper;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ e(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((PageDataLoader) this.e).lambda$loadData$2((Consumer) this.f);
                return;
            case 1:
                ((PageDataLoader) this.e).lambda$swapRelatedData$3((RelatedDataHolder) this.f);
                return;
            case 2:
                ((PageRecyclerAdapter) this.e).lambda$setData$0((ArrayList) this.f);
                return;
            case 3:
                ((ForceSwipeDelegate) this.e).lambda$finish$1((String) this.f);
                return;
            case 4:
                ((ThumbnailLoader) this.e).lambda$postponeRequest$1((ReqInfo) this.f);
                return;
            case 5:
                ConstraintTracker._set_state_$lambda$4$lambda$3((List) this.e, (ConstraintTracker) this.f);
                return;
            case 6:
                ((TransitionDelegate) this.e).lambda$prepareRemasterReturnTransition$7((PhotoViewCompat) this.f);
                return;
            case 7:
                ((TransitionDelegate) this.e).lambda$startTransition$3((SharedTransition.TransitionListener) this.f);
                return;
            case 8:
                ((LocationUpdater) this.e).lambda$onUpdateDatabase$2((String) this.f);
                return;
            case 9:
                ((DetailsListVideoHelper) this.e).lambda$postPlay$0((MediaItem) this.f);
                return;
            case 10:
                ((MergeCreatureDialogPresenter) this.e).lambda$loadHeaderThumb$1((Bitmap) this.f);
                return;
            case 11:
                ((ProgressDialogCompat) this.e).lambda$updateMessage$0((CharSequence) this.f);
                return;
            case 12:
                ((PdcSearchDelegate) this.e).lambda$updateListData$5((ArrayList) this.f);
                return;
            case 13:
                ((PdcSearchDelegate) this.e).lambda$addRelationship$1((String) this.f);
                return;
            case 14:
                ((AbsViewerHolder) this.e).lambda$invalidate$1((MediaItem) this.f);
                return;
            case 15:
                ((PhotoViewCompat) this.e).setImage((Bitmap) this.f, ((Bitmap) this.f).getWidth(), ((Bitmap) this.f).getHeight());
                return;
            case 16:
                ((ContentViewerHolder) this.e).lambda$onUpdateBitmapAnim$13((Object[]) this.f);
                return;
            case 17:
                ((ContentViewerHolder) this.e).lambda$onUpdatedRemasterStatus$2((String) this.f);
                return;
            case 18:
                ((ResourcesCompat.FontCallback) this.e).lambda$callbackSuccessAsync$0((Typeface) this.f);
                return;
            case 19:
                PeopleDataManager.updateCustomRelationship((String) this.e, (String) this.f);
                return;
            case 20:
                ((SearchPicturesFragment) this.e).lambda$updateBackgroundIfNeeded$4((GalleryListView) this.f);
                return;
            case 21:
                ((SearchPicturesFragment) this.e).lambda$bindHeader$6((MediaItem) this.f);
                return;
            case 22:
                ((CloudDownloader) this.e).lambda$afterConfirm$0((DownloadSyncMgr) this.f);
                return;
            case 23:
                ((MotionPhotoPlayViewerStateHandler) this.e).lambda$invalidate$3((MediaItem) this.f);
                return;
            case 24:
                ((XmpTypeLoader) this.e).lambda$loadOnBgThread$0((MediaItem) this.f);
                return;
            case 25:
                ((PlayBackOptionsFragment) this.e).lambda$setBitmaps$1((Resources) this.f);
                return;
            case 26:
                ((SeslTouchTargetDelegate.Builder) this.e).lambda$create$1((androidx.core.util.Consumer) this.f);
                return;
            case 27:
                ((ClusterPicturesPresenter) this.e).lambda$updateToolbarCount$0((int[]) this.f);
                return;
            case 28:
                ((ClusterResultViewAdapter) this.e).lambda$bindImageOnScrollIdle$1((ListViewHolder) this.f);
                return;
            default:
                ((ViewPagerHolder) this.e).lambda$layoutTransformView$0((Drawable) this.f);
                return;
        }
    }
}
