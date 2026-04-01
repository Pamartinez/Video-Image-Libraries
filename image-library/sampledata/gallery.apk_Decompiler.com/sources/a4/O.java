package A4;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.preference.Preference;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.ConcatThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.abstraction.ListViewAdapterHandler;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreviewDelegate;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailResizer;
import com.samsung.android.gallery.app.ui.list.abstraction.YearThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFooterView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewHolderBindHelper;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumChoiceFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.SearchRelationshipPreviewFragment;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.item.FloatingItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.StoryCover;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderMapView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderPeopleViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.legacy.StoryPicturesLegacyFragment;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedStoryLoader;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.StoryRelatedView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.settings.UpgradeManager;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterrupter;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.plugins.motionphoto.Functions;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.settings.ui.AboutFragment;
import com.samsung.android.gallery.settings.ui.EditSuggestionsFragment;
import com.samsung.android.gallery.settings.ui.LabsConfigFragment;
import com.samsung.android.gallery.settings.ui.LabsUserTrialFragment;
import com.samsung.android.gallery.settings.ui.SearchSettingFragment;
import com.samsung.android.gallery.settings.ui.SharingServiceFragment;
import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listview.InterceptTouchListener;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.concurrent.atomic.AtomicBoolean;
import k2.q;
import p2.C0262c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class O implements MediaData.OnDataReadListener, ThumbnailInterrupter, q, PreviewViewHolder.OnCompletionListener, ThumbnailLoadedListener, AccessibilityManagerCompat.TouchExplorationStateChangeListener, AlbumsFooterView.OnDataChangeListener, C0262c, MapContainer.OnSnapshotReadyListener, UniqueKey, MediaScannerListener, ObservableList.OnListChangedCallback, PropertyAnimator.PropertyAnimationListener, AlbumsBaseViewHolderBindHelper.AdvancedMouseFocusManagerProvider, InterceptTouchListener, RelatedStoryLoader.ItemProvider, ListViewHolder.OnItemClickListener, UpgradeManager.OnUpdateCheckListener, Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ O(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public int getKey() {
        return this.e.hashCode();
    }

    public MediaItem getMediaItem(int i2) {
        return ((IStoryPicturesView) this.e).getStoryAlbumById(i2);
    }

    public boolean isInterrupted() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 1:
                return ((ConcatThumbnailRequestHolder) obj).lambda$loadConcatThumbnailAsync$2();
            case 5:
                return ((YearThumbnailRequestHolder) obj).lambda$loadYearThumbnailAsync$1();
            default:
                return ((AtomicBoolean) obj).get();
        }
    }

    public void onAnimationEnd(View view) {
        view.setBackground((Drawable) this.e);
    }

    public void onCompleted() {
        ((Functions) this.e).lambda$onDeleteConfirmed$6();
    }

    public void onCompletion(PreviewViewHolder previewViewHolder, boolean z) {
        ((ThumbnailPreviewDelegate) this.e).lambda$startPreviewViewHolders$0(previewViewHolder, z);
    }

    public void onDataReadCompleted(MediaItem mediaItem) {
        X x9 = (X) ((MediaItemLoader.OnMediaItemLoadingListener) this.e);
        ListViewAdapterHandler.lambda$delayLoadMediaItemStart$1((ListViewHolder) x9.f, (BaseListViewAdapter) x9.g, x9.d, x9.e, mediaItem);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return ((ForegroundViewController) this.e).onIntercept(motionEvent);
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        int i8 = this.d;
        Object obj = this.e;
        switch (i8) {
            case 21:
                ((StoryRelatedView) obj).onRelatedItemClick(listViewHolder, i2, mediaItem, i7);
                return;
            default:
                ((FloatingItemAdapter) obj).onItemClicked(listViewHolder, i2, mediaItem, i7);
                return;
        }
    }

    public void onItemRangeChanged(ObservableList observableList, int i2, int i7) {
        ((VideoCtrlView) this.e).lambda$load$6(observableList, i2, i7);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 4:
                ((ThumbnailResizer) obj).onBitmapLoaded(bitmap, uniqueKey, thumbKind);
                return;
            case 6:
                ((StoryCover) obj).lambda$onThumbnailLoadCompleted$0(bitmap, uniqueKey, thumbKind);
                return;
            case 12:
                ((StoryHeaderPeopleViewHolder) obj).lambda$bindFaceThumbnail$0(bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((StoryPicturesLegacyFragment) obj).lambda$onThumbnailLoadCompleted$0(bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 9:
                return ((SearchRelationshipPreviewFragment) obj).onMenuItemSelected(menuItem);
            default:
                return ((AlbumChoiceFragment) obj).lambda$onBindView$0(menuItem);
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return ((ListMenuUpdater) this.e).onNavigationItemSelected(menuItem);
    }

    public boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 25:
                return ((EditSuggestionsFragment) obj).lambda$initPreference$2(preference);
            case 26:
                return ((LabsConfigFragment) obj).clearPreference(preference);
            case 27:
                return ((LabsUserTrialFragment) obj).lambda$addCategorySearch$9(preference);
            case 28:
                return ((SearchSettingFragment) obj).lambda$initCustomizePreference$8(preference);
            default:
                return ((SharingServiceFragment) obj).lambda$loadPreference$5(preference);
        }
    }

    public void onSnapshotReady(Bitmap bitmap) {
        ((StoryHeaderMapView) this.e).snapshotReady(bitmap);
    }

    public void onUpdateCheckCompleted(int i2) {
        ((AboutFragment) this.e).onUpdateCheckCompleted(i2);
    }
}
