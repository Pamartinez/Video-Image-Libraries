package Ab;

import android.content.Context;
import android.view.View;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.ChangeAlbumCoverCmd;
import com.samsung.android.gallery.app.controller.delegate.PickerSelectableChecker;
import com.samsung.android.gallery.app.controller.story.CreateStoryCmd;
import com.samsung.android.gallery.app.controller.story.StoryReorderCmd;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumChoicePresenter;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPanePresenter;
import com.samsung.android.gallery.app.ui.list.search.toolbar.FloatingRecommendationBehavior;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.CategoryTransition;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterPicturesFragment;
import com.samsung.android.gallery.module.cloud.CloudLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewHolder;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewPlayer;
import com.samsung.android.gallery.settings.helper.Troubleshooting;
import com.samsung.android.gallery.settings.ui.HighlightGroupAdapter;
import com.samsung.android.gallery.settings.ui.TroubleshootingFragment;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripGroupViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripMotionPhotoViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripVideoViewHolder;
import com.samsung.android.gallery.widget.listview.handler.PenSelectionHandler;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ b(Object obj, int i2, Object obj2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
        this.g = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((FastOptionItemView) this.f).lambda$setLayoutParams$1((View) this.g, this.e);
                return;
            case 1:
                ((MotionPhotoViewHolder) this.f).lambda$onVideoRendered$3((MediaPlayerCompat) this.g, this.e);
                return;
            case 2:
                ((MotionPhotoViewPlayer) this.f).lambda$onVideoRendered$12((MediaPlayerCompat) this.g, this.e);
                return;
            case 3:
                ((FilmStripGroupViewHolder) this.f).lambda$createBitmapList$1((ObservableList) this.g, this.e);
                return;
            case 4:
                ((FilmStripMotionPhotoViewHolder) this.f).lambda$createBitmapList$3((ObservableList) this.g, this.e);
                return;
            case 5:
                ((FilmStripVideoViewHolder) this.f).lambda$createBitmapList$5(this.e, (ObservableList) this.g);
                return;
            case 6:
                ((AlbumChoicePresenter) this.f).lambda$onListItemClickInternal$0((MediaItem) this.g, this.e);
                return;
            case 7:
                ((AlbumChoicePresenter) this.f).lambda$handleItemClick$2(this.e, (String) this.g);
                return;
            case 8:
                ((MdeSharingService) this.f).lambda$connectSessionAsync$3(this.e, (ConnectListener) this.g);
                return;
            case 9:
                ((AlbumChoiceBaseFragment) this.f).lambda$refreshView$0((String) this.g, this.e);
                return;
            case 10:
                ListenerSet.lambda$queueEvent$0((CopyOnWriteArraySet) this.f, this.e, (ListenerSet.Event) this.g);
                return;
            case 11:
                ((BaseCommand) this.f).lambda$showToast$0((String) this.g, this.e);
                return;
            case 12:
                ((StoriesPinchViewPresenter) this.f).lambda$moveToHighlight$0(this.e, (MediaItem) this.g);
                return;
            case 13:
                ((StoriesPinchViewPresenter) this.f).lambda$moveToHighlightInternal$1((IStoriesPinchView) this.g, this.e);
                return;
            case 14:
                ((HighlightGroupAdapter) this.f).lambda$applyHighlight$0(this.e, (String) this.g);
                return;
            case 15:
                ((TroubleshootingFragment) this.f).lambda$resolveIssue$5((Troubleshooting.TroubleResolver) this.g, this.e);
                return;
            case 16:
                ((FolderViewFragment) this.f).lambda$refreshFolder$2((String) this.g, this.e);
                return;
            case 17:
                ((PenSelectionHandler) this.f).lambda$onBindViewHolder$0(this.e, (ListViewHolder) this.g);
                return;
            case 18:
                ((ChangeAlbumCoverCmd) this.f).lambda$changeCoverImage$0(this.e, (EventContext) this.g);
                return;
            case 19:
                ((FloatingRecommendationBehavior) this.f).lambda$setDecorViewsHeight$0(this.e, (SearchToolbar) this.g);
                return;
            case 20:
                ((DrmSessionEventListener.EventDispatcher) this.f).lambda$drmSessionAcquired$0((DrmSessionEventListener) this.g, this.e);
                return;
            case 21:
                ((SharingsViewAdapter) this.f).lambda$onBindViewHolder$0((ListViewHolder) this.g, this.e);
                return;
            case 22:
                ((PickerSelectableChecker) this.f).lambda$showToast$1((Context) this.g, this.e);
                return;
            case 23:
                ((AlbumPicturesFragment) this.f).lambda$updateAlbumsPaneBottomPadding$40((AppBarLayout) this.g, this.e);
                return;
            case 24:
                ((AlbumsPanePresenter) this.f).lambda$moveToTargetAlbum$3((MediaData) this.g, this.e);
                return;
            case 25:
                ((RemasterPicturesFragment) this.f).lambda$createSimpleAutoScroller$1((RecyclerView) this.g, this.e);
                return;
            case 26:
                CloudLog.lambda$sendLog$0((Context) this.f, (String) this.g, this.e);
                return;
            case 27:
                ((CreateStoryCmd) this.f).lambda$moveTo$1((String) this.g, this.e);
                return;
            case 28:
                ((StoryReorderCmd) this.f).lambda$updateOrder$0(this.e, (List) this.g);
                return;
            default:
                ((CategoryTransition) this.f).lambda$startCategoryAutoScroll$4((RecyclerView) this.g, this.e);
                return;
        }
    }

    public /* synthetic */ b(Object obj, Object obj2, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.g = obj2;
        this.e = i2;
    }
}
