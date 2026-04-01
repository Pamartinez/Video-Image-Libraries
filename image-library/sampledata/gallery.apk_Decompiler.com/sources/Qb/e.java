package Qb;

import com.samsung.android.gallery.app.controller.ppp.PppChecker;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateStory;
import com.samsung.android.gallery.app.controller.sharing.request.RequestStreamingVideo;
import com.samsung.android.gallery.app.controller.story.CollageVideoSaveCmd;
import com.samsung.android.gallery.app.controller.story.DeleteStoryCmd;
import com.samsung.android.gallery.app.controller.story.HighlightEncodeCmd;
import com.samsung.android.gallery.app.controller.story.UpdateStoryFavoriteCmd;
import com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.StoriesFragment;
import com.samsung.android.gallery.app.ui.list.stories.StoriesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.StoriesViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.CategoryTransition;
import com.samsung.android.gallery.app.ui.list.stories.category.TopColorEffectHandler;
import com.samsung.android.gallery.app.ui.list.timeline.ITimelineView;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineViewAdapter;
import com.samsung.android.gallery.app.ui.viewer2.remaster.DivideHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterLayoutHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.focusview.RemasterFocusViewHandler;
import com.samsung.android.gallery.module.story.transcode.HighlightEncoder;
import com.samsung.android.gallery.support.library.v0.media.GedMediaPlayerCompat;
import com.samsung.android.gallery.widget.previewable.PreviewVideo2;
import com.samsung.android.gallery.widget.progressbar.SearchProgressCircle;
import com.samsung.android.gallery.widget.remaster.TableModeRemasterLayout;
import com.samsung.android.gallery.widget.search.filter.AllFiltersView;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersAdapter;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersViewContainer;
import java.net.HttpURLConnection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ e(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((PreviewVideo2) obj).updateTransformMatrix();
                return;
            case 1:
                ((PppChecker) obj).lambda$onCompleted$0();
                return;
            case 2:
                ((AlbumTitleCountViewHolder) obj).lambda$updateNewLabelVisibility$1();
                return;
            case 3:
                ((ITimelineView) obj).invalidateOptionsMenu();
                return;
            case 4:
                ((TimelineViewAdapter) obj).lambda$onDataChanged$2();
                return;
            case 5:
                ((GedMediaPlayerCompat) obj).onTimeTickUpdated();
                return;
            case 6:
                ((SearchProgressCircle) obj).lambda$updateVisibility$0();
                return;
            case 7:
                ((ChooseSharedAlbumCmd) obj).showSharingAlbumDialog();
                return;
            case 8:
                ((StoriesFragment) obj).lambda$requestXlargeThumbnail$0();
                return;
            case 9:
                ((StoriesPresenter) obj).onViewResumeOnBg();
                return;
            case 10:
                ((StoriesViewAdapter) obj).lambda$handleDataUpdateAnimation$3();
                return;
            case 11:
                ((DivideHandler) obj).lambda$resetPosition$3();
                return;
            case 12:
                ((RemasterLayoutHandler) obj).lambda$addPhotoViewPositionChangedListener$2();
                return;
            case 13:
                ((TableModeRemasterLayout) obj).lambda$updateTableModeBG$0();
                return;
            case 14:
                ((HttpURLConnection) obj).disconnect();
                return;
            case 15:
                ((RequestCreateStory) obj).lambda$onScanCompleted$10();
                return;
            case 16:
                ((RequestStreamingVideo) obj).showProgressDialog();
                return;
            case 17:
                ((StoriesBaseFragment) obj).lambda$setEnabledItemView$0();
                return;
            case 18:
                ((RemasterFocusViewHandler) obj).lambda$loadRemasterFocusRoi$3();
                return;
            case 19:
                ((AllFiltersView) obj).dismissDialog();
                return;
            case 20:
                ((SearchFiltersAdapter) obj).dismissDialog();
                return;
            case 21:
                ((SearchFiltersViewContainer) obj).lambda$bindView$0();
                return;
            case 22:
                ((HighlightEncoder) obj).stopEncoding();
                return;
            case 23:
                ((CollageVideoSaveCmd) obj).lambda$onExecute$0();
                return;
            case 24:
                ((DeleteStoryCmd) obj).lambda$deleteStories$3();
                return;
            case 25:
                ((HighlightEncodeCmd) obj).lambda$onExecute$0();
                return;
            case 26:
                ((UpdateStoryFavoriteCmd) obj).lambda$onExecute$0();
                return;
            case 27:
                ((CategoryTransition) obj).lambda$startCategoryAutoScroll$3();
                return;
            case 28:
                ((TopColorEffectHandler) obj).onDataChanged();
                return;
            default:
                ((LaunchTrashBinCmd) obj).lambda$launchTrashBin$0();
                return;
        }
    }
}
