package g6;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.container.picker.PickerConFragment;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.category.header.AbsCategoryHeaderView;
import com.samsung.android.gallery.app.ui.list.search.category.myquery.SearchMyQueryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryHeaderContainer;
import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.BgmItemViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.BgmListAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.StoryHighlightListV2Fragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.DragItem;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.AiProcessingViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BixbyDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.HdrContentsDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.ViewerTableModeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripSeekerDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover.FlipCoverDelegate;
import com.samsung.android.gallery.database.dal.abstraction.table.AbsSecFilesTable;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.database.helper.MyTagCopyOperation;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.story.transcode.HighlightEncoder;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.decoder.video.ImageDecoder;
import com.samsung.android.gallery.module.story.transcode.decoder.video.processor.FilterProcessor;
import com.samsung.android.gallery.module.story.transcode.decoder.video.processor.NonFilterProcessor;
import com.samsung.android.gallery.module.story.transcode.transcoder.ITranscoder;
import com.samsung.android.gallery.module.story.transcode.util.ThumbProvider;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimListView;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimView;
import com.samsung.android.gallery.widget.awesome.AwesomeIntelligenceDialog;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.videoview.ScaleEndListener;
import com.samsung.android.gallery.widget.videoview.mediaplayer.DebugInfoDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.ScaleDelegate;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((StoryHighlightPresenter) obj2).onHeaderUpdated((MediaItem) obj);
                return;
            case 1:
                ((AiProcessingViewHandler) obj2).lambda$setPhotoViewPositionChangedListener$7((PointF) obj);
                return;
            case 2:
                ((MyTagCopyOperation) obj2).lambda$apply$1((Map) obj);
                return;
            case 3:
                ((TextExpandAnimListView) obj2).lambda$updateViewList$1((TextExpandAnimView) obj);
                return;
            case 4:
                ((HighlightEncoder) obj2).lambda$startMuxer$2((ITranscoder) obj);
                return;
            case 5:
                ((MediaPlayerDelegate) obj2).lambda$destroyMediaPlayer$9((Boolean) obj);
                return;
            case 6:
                ((ScaleDelegate) obj).setScaleEndListener((ScaleEndListener) obj2);
                return;
            case 7:
                ((DebugInfoDelegate) obj).setVideoOrientation(((MediaHelper.VideoInfo) obj2).orientation);
                return;
            case 8:
                ((ScaleDelegate) obj).setTranslationListener((OnTranslationListener) obj2);
                return;
            case 9:
                ((ScaleDelegate) obj).setPinchShrinkSupport((OnViewerExitGestureListener) obj2);
                return;
            case 10:
                CategoryHeaderContainer categoryHeaderContainer = (CategoryHeaderContainer) obj2;
                if (obj == null) {
                    categoryHeaderContainer.lambda$initView$0((AbsCategoryHeaderView) null);
                    return;
                }
                throw new ClassCastException();
            case 11:
                ((BgmListAdapter) obj2).onSelect((BgmItemViewHolder) obj);
                return;
            case 12:
                ((StoryHighlightListV2Fragment) obj2).lambda$createLayoutManager$1((View) obj);
                return;
            case 13:
                ((BixbyDelegate) obj2).handleAddTagDone((Void) obj);
                return;
            case 14:
                ((HdrContentsDelegate) obj2).lambda$postChangeColorMode$3((Handler) obj);
                return;
            case 15:
                ((ViewerTableModeDelegate) obj2).lambda$onCreate$0((FoldStateManager) obj);
                return;
            case 16:
                ((ImageDecoder) obj).draw(new FrameProperty.Builder((FrameProperty) obj2).setAlpha(Math.min(((FrameProperty) obj2).getProgress() * 2.0f, 1.0f)).build());
                return;
            case 17:
                ((ThumbProvider) obj2).recycleBitmap((Bitmap) obj);
                return;
            case 18:
                ((FragmentTransaction) obj2).hide((MvpBaseFragment) obj);
                return;
            case 19:
                ((FilmStripSeekerDelegate) obj2).updateSeeker((FilmStripViewHolder) obj);
                return;
            case 20:
                ((FilterProcessor) obj2).lambda$setInputBufferQueue$0((Boolean) obj);
                return;
            case 21:
                ((NonFilterProcessor) obj2).lambda$setInputBufferQueue$0((Boolean) obj);
                return;
            case 22:
                ((AwesomeIntelligenceDialog) obj2).lambda$init$0((Void) obj);
                return;
            case 23:
                ((PickerConFragment) obj2).finishChildFragment((IBaseFragment) obj);
                return;
            case 24:
                ((SearchMyQueryFragment) obj2).lambda$updateSaveMenu$1((MenuItem) obj);
                return;
            case 25:
                ((FlipCoverDelegate) obj2).lambda$onCreate$0((FoldStateManager) obj);
                return;
            case 26:
                ((AbsSecFilesTable) obj2).lambda$filterAlbumIDs$0((int[]) obj);
                return;
            case 27:
                ((CreatureCategoryHeaderContainer) obj2).lambda$initAnalysisTipHeader$0((Boolean) obj);
                return;
            case 28:
                ((DragItem) obj2).lambda$enableDragView$0((ListViewHolder) obj);
                return;
            default:
                ((Menu) obj2).add(0, ((MenuItem) obj).getItemId(), ((MenuItem) obj).getOrder(), ((MenuItem) obj).getTitle());
                return;
        }
    }
}
