package K4;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import androidx.media3.exoplayer.source.ProgressiveMediaExtractor;
import androidx.print.PrintHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd;
import com.samsung.android.gallery.app.controller.externals.PasteClipboardCmd;
import com.samsung.android.gallery.app.controller.externals.SetAsWallpaperChooserDialogCmd;
import com.samsung.android.gallery.app.controller.externals.ShareWithWebLinkCmd;
import com.samsung.android.gallery.app.controller.externals.StartPrintCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd2;
import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd2;
import com.samsung.android.gallery.app.controller.internals.CreateMyQueryCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteGroupShotCmd;
import com.samsung.android.gallery.app.controller.internals.EditLocationCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoExportCmd;
import com.samsung.android.gallery.app.controller.internals.RangeDatePickerCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveLiveEffectCmd;
import com.samsung.android.gallery.app.controller.internals.SetAsChooserDialogCmd;
import com.samsung.android.gallery.app.controller.internals.ShowLocationGdprDialogCmd;
import com.samsung.android.gallery.app.controller.internals.SortByLocationDialogCmd;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderView;
import com.samsung.android.gallery.app.ui.list.albums.mx.manage.MxManageAlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterListViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.spotify.SpotifySlideshowFragment;
import com.samsung.android.gallery.app.ui.list.stories.videopreview.VideoPreviewHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.cache.BitmapCacheMgr;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsDataLoadCallback;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.fileio.redact.OnProgress;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.widget.listview.InterceptDispatchTouchListener;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.gallery.widget.photoview.ScaleAnimation;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThumbnailLoadedListener, RecyclerView.SeslOnGoToTopClickListener, EventContext.OnSelectionListener, PreviewViewHolder.OnCompletionListener, DetailsDataLoadCallback, AbsCacheMgr$EvictListener, InterceptDispatchTouchListener, ProgressiveMediaExtractor.Factory, FutureListener, DataCollectionDelegate.OnDataCompletionListener, PrintHelper.OnPrintFinishCallback, ListViewHolder.OnItemClickListener, ScaleAnimation.ScaleAnimationListener, OnProgress {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public void OnEvicted(Object obj, Object obj2) {
        ((BitmapCacheMgr) this.e).OnBitmapEvicted(obj, (Bitmap) obj2);
    }

    public void a() {
        ((StartPrintCmd) this.e).lambda$onExecute$1();
    }

    public void onAnimationUpdate() {
        ((PhotoViewMotionControl) this.e).lambda$zoom$1();
    }

    public void onCompleted(int i2, int i7, int i8) {
        int i10 = this.d;
        Object obj = this.e;
        switch (i10) {
            case 17:
                ((ChangeBestItemCmd2) obj).lambda$onExecute$2(i2, i7, i8);
                return;
            default:
                ((RemoveLiveEffectCmd) obj).lambda$executeInternal$1(i2, i7, i8);
                return;
        }
    }

    public void onCompletion(PreviewViewHolder previewViewHolder, boolean z) {
        ((VideoPreviewHandler) this.e).lambda$handleRequestPreview$1(previewViewHolder, z);
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 10:
                ((CreateNewDialogCmd) obj).onItemSelected(eventContext, arrayList);
                return;
            case 11:
                ((PasteClipboardCmd) obj).onCompleted(eventContext, arrayList);
                return;
            case 12:
                ((SetAsWallpaperChooserDialogCmd) obj).onItemSelected(eventContext, arrayList);
                return;
            case 13:
                ((ShareWithWebLinkCmd) obj).onDataCompleted(eventContext, arrayList);
                return;
            case 18:
                ((ChangeLocationCmd) obj).lambda$onExecute$2(eventContext, arrayList);
                return;
            case 19:
                ((ChangeLocationCmd2) obj).lambda$onExecute$2(eventContext, arrayList);
                return;
            case 20:
                ((CreateMyQueryCmd) obj).addMyQuery(eventContext, arrayList);
                return;
            case 21:
                ((DeleteCmd) obj).onConfirmed(eventContext, arrayList);
                return;
            case 22:
                ((DeleteGroupShotCmd) obj).onConfirmed(eventContext, arrayList);
                return;
            case 23:
                ((EditLocationCmd) obj).onDataReady(eventContext, arrayList);
                return;
            case 24:
                ((MotionPhotoExportCmd) obj).onItemSelected(eventContext, arrayList);
                return;
            case 25:
                ((RangeDatePickerCmd) obj).onConfirmed(eventContext, arrayList);
                return;
            case 27:
                ((SetAsChooserDialogCmd) obj).onItemSelected(eventContext, arrayList);
                return;
            case 28:
                ((ShowLocationGdprDialogCmd) obj).onConfirmed(eventContext, arrayList);
                return;
            default:
                ((SortByLocationDialogCmd) obj).updateSortBy(eventContext, arrayList);
                return;
        }
    }

    public void onFutureDone(Future future) {
        ((AlbumPicturesPresenter) this.e).lambda$onTipCardCloseClicked$12(future);
    }

    public boolean onInterceptDispatchTouchEvent(MotionEvent motionEvent) {
        return ((LiveMotionViewPager) this.e).lambda$setDispatchTouchInterceptor$2(motionEvent);
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ((ScreenShotFilterListViewAdapter) this.e).onItemClicked(listViewHolder, i2, mediaItem, i7);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((MxAlbumsHeaderView) obj).lambda$setThumbnailOnHostIcon$7(bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((SpotifySlideshowFragment) obj).lambda$applyBlur$3(bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        return ((MxManageAlbumsPresenter) this.e).onSelectionCompleted(eventContext, mediaItemArr);
    }

    public void onLoaded(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        ((DetailsLoadHandler) this.e).onDetailsDataUpdated(mediaItem, detailsLoadResult);
    }
}
