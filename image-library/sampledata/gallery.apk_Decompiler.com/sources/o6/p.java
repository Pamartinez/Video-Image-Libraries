package o6;

import android.graphics.Bitmap;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.SeslFadingEdgeHelper;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.picker.widget.SeslDatePicker;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabController;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.app.ui.dialog.RangeDatePickerDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureDialogPresenter;
import com.samsung.android.gallery.app.ui.dialog.tag.AddTagDialog;
import com.samsung.android.gallery.app.ui.list.search.editcreature.RegisteredCreatureAdapter;
import com.samsung.android.gallery.app.ui.list.search.floating.FloatingRecommendationPresenter;
import com.samsung.android.gallery.app.ui.list.search.keyword.stories.SearchResultStoriesViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResultViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.RecommendContactDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.GifPlayDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.EmptyTouchHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.RecyclerPageScrollHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.StoriesThemeViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesFragment;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedStoryLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DualShotOptionsViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.ExitHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.ViewerCapture;
import com.samsung.android.gallery.app.ui.viewholders.SearchPicturesDateLocationViewHolder;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.clipboard.ClipboardDataPrepareTask;
import com.samsung.android.gallery.module.creature.base.LoadFinishedListener;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.location.LocationUpdater;
import com.samsung.android.gallery.module.map.manager.AddressHelper;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.trash.helper.TrashHelper;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.widget.crop.api.OnCropImageMatrixChangedListener;
import com.samsung.android.gallery.widget.dialog.BaseDialog;
import com.samsung.android.gallery.widget.listview.InterceptTouchListener;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.popover.PopoverInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements GifAnimation.AnimationFrameStartListener, DrawerTabViewAdapter.OnDrawerItemClickListener, ClipboardDataPrepareTask.OnPrepareDoneListener, SeslDatePicker.OnDateChangedListener, LoadFinishedListener, InterceptTouchListener, RecyclerPageScrollHelper.PageScrollListener, RelatedStoryLoader.ItemProvider, AbsCacheMgr$EvictListener, OnCropImageMatrixChangedListener, AddressHelper.OnAddressUpdateListener, ThumbnailLoadedListener, MediaScannerListener, PopoverInfo.AnchorUpdateListener, SearchPicturesDateLocationViewHolder.OnExpandClickListener, FutureListener, ListViewHolder.OnItemClickListener, DataCollectionDelegate.OnDataCompletionListener, OnViewerExitGestureListener, OnApplyWindowInsetsListener, ClusterResult.OnUiUpdateListener, ViewerCapture.CaptureEndListener, InputConnectionCompat.OnCommitContentListener, Toolbar.OnMenuItemClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ p(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public void OnEvicted(Object obj, Object obj2) {
        ((ThumbnailLoader) this.e).recycle((String) obj, (Bitmap) obj2);
    }

    public void a(View view) {
        ((BaseDialog) this.e).onPopoverAnchorChanged(view);
    }

    public void b(String str) {
        ((DrawerTabController) this.e).onDrawerItemSelected(str);
    }

    public MediaItem getMediaItem(int i2) {
        return ((PageDataLoader) this.e).getStoryAlbumById(i2);
    }

    public void onAddressUpdate(Object[] objArr) {
        ((LocationUpdater) this.e).onUpdateDatabase(objArr);
    }

    public void onAnimationFrameStarted() {
        ((GifPlayDelegate) this.e).onPlayStart();
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return ((SeslFadingEdgeHelper) this.e).lambda$setOnApplyWindowInsetsListener$0(view, windowInsetsCompat);
    }

    public void onCompleted() {
        ((TrashHelper) this.e).scanDone();
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        ((DualShotOptionsViewHandler) this.e).lambda$downloadSharedDualPhoto$3(eventContext, arrayList);
    }

    public void onDateChanged(SeslDatePicker seslDatePicker, int i2, int i7, int i8) {
        ((RangeDatePickerDialog) this.e).updateDate(seslDatePicker, i2, i7, i8);
    }

    public void onExitGesture(boolean z, boolean z3) {
        ((ExitHandler) this.e).onExitGesture(z, z3);
    }

    public void onFutureDone(Future future) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 17:
                ((SearchPicturesPresenter) obj).lambda$publishLocationHeaderItem$13(future);
                return;
            default:
                ((CreaturePicturesDelegate) obj).lambda$publishCreatureHeaderItem$2(future);
                return;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 5:
                return ((EmptyTouchHandler) obj).onInterceptTouchEvent(motionEvent);
            default:
                return ((FloatingRecommendationPresenter) obj).onIntercept(motionEvent);
        }
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        int i8 = this.d;
        Object obj = this.e;
        switch (i8) {
            case 18:
                ((StoriesThemeViewAdapter) obj).onItemClicked(listViewHolder, i2, mediaItem, i7);
                return;
            default:
                ((StoryPicturesFragment) obj).onCoverClick(listViewHolder, i2, mediaItem, i7);
                return;
        }
    }

    public void onLoadFinished(ArrayList arrayList) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 4:
                ((RegisteredCreatureAdapter) obj).appendData(arrayList);
                return;
            default:
                ((RecommendContactDelegate) obj).loadFinishRecommendContact(arrayList);
                return;
        }
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 12:
                ((MergeCreatureDialogPresenter) obj).lambda$loadHeaderThumb$2(bitmap, uniqueKey, thumbKind);
                return;
            case 13:
                ((SearchResultStoriesViewAdapter) obj).setBitmapWithBind(bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((ClusterResultViewAdapter) obj).setBitmapWithBind(bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return ((AddTagDialog) this.e).onOptionsMenuSelected(menuItem);
    }
}
