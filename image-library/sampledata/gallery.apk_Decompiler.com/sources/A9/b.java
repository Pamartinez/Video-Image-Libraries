package A9;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import com.samsung.android.gallery.app.activity.external.StoriesViewNavigatorDelegate;
import com.samsung.android.gallery.app.activity.external.launcher.MirroringViewLauncher;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.ChangeCoverCmd;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.app.controller.album.RenameFolderCmd;
import com.samsung.android.gallery.app.controller.album.SaveObjectCaptureToAlbumCmd;
import com.samsung.android.gallery.app.controller.creature.EditCreatureDialogDelegate;
import com.samsung.android.gallery.app.controller.creature.PersonUnlinkCmd;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.RelationshipView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchSelectedFiltersDelegate;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.StoryRelatedAdapter;
import com.samsung.android.gallery.app.ui.list.stories.recap.lastpage.RecapPageDataLoader;
import com.samsung.android.gallery.app.ui.viewer2.common.shotmode.AbsShotModeHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.SubItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.PreviewLoader;
import com.samsung.android.gallery.module.album.hide.AlbumsHideManager;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.service.RecapVideoMaker;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewPlayer;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.plugins.portrait.SetAsWallpaperDialog;
import com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.settings.ui.TwoStepVerificationFragment;
import com.samsung.android.gallery.settings.widget.SummaryPreference;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripVideoViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((RecapVideoMaker) this.e).lambda$createVideo$0((w) this.f, (Consumer) this.g);
                return;
            case 1:
                ((MotionPhotoViewPlayer) this.e).lambda$stopPlayback$5((MediaPlayerCompat) this.f, (Runnable) this.g);
                return;
            case 2:
                ((MotionPhotoViewPlayer) this.e).lambda$computeVideo$2((MediaItem) this.f, (Consumer) this.g);
                return;
            case 3:
                ((VideoCtrlView) this.e).lambda$load$7((MediaItem) this.f, (Bitmap) this.g);
                return;
            case 4:
                ((FilmStripVideoViewHolder) this.e).lambda$bindSearchView$2((ArrayList) this.f, (MediaItem) this.g);
                return;
            case 5:
                ((FilmStripViewHolder) this.e).lambda$tryViewMatrix$4((ImageView) this.f, (MediaItem) this.g);
                return;
            case 6:
                ((SubItemLoader) this.e).lambda$setGroupItemImage$1((Bitmap) this.f, (MediaItem) this.g);
                return;
            case 7:
                ((AlbumsHideManager) this.e).lambda$setHideAlbum$0((AlbumsHideManager.OnAlbumHideListener) this.f, (MediaItem) this.g);
                return;
            case 8:
                ((StoriesViewNavigatorDelegate) this.e).lambda$onHandle$2((AtomicReference) this.f, (AtomicInteger) this.g);
                return;
            case 9:
                ((StoryRelatedAdapter) this.e).lambda$bindThumbnail$1((ListViewHolder) this.f, (Bitmap) this.g);
                return;
            case 10:
                ((PreviewLoader) this.e).lambda$setPreviewBmp$4((Bitmap) this.f, (MediaItem) this.g);
                return;
            case 11:
                MdeGroupHelper.lambda$shareGroupInvitationLink$7((Context) this.e, (String) this.f, (String) this.g);
                return;
            case 12:
                ((SetAsWallpaperDialog) this.e).lambda$show$1((Context) this.f, (String) this.g);
                return;
            case 13:
                ((MirroringViewLauncher) this.e).lambda$execute$0((MediaItem[]) this.f, (CountDownLatch) this.g);
                return;
            case 14:
                ((BaseCommand) this.e).lambda$loadAndExecute$3((EventContext) this.f, (Consumer) this.g);
                return;
            case 15:
                ((LabsAlbumBnRFragment) this.e).lambda$onRestoreAlbumDbClicked$4((ArrayList) this.f, (Context) this.g);
                return;
            case 16:
                ((LabsDevManageFragment) this.e).lambda$loadCacheInfo$63((String) this.f, (String) this.g);
                return;
            case 17:
                ((SummaryPreference) this.e).setSummary((CharSequence) ((String) this.f) + "\n" + ((String) this.g));
                return;
            case 18:
                ((TwoStepVerificationFragment) this.e).lambda$loadWebView$0((String) this.f, (Map) this.g);
                return;
            case 19:
                ((ChangeCoverCmd) this.e).lambda$changeCover$1((MediaItem) this.f, (String) this.g);
                return;
            case 20:
                ((AbsShotModeHandler) this.e).execute((EventContext) this.f, (MediaItem) this.g);
                return;
            case 21:
                ((CreateFolderCmd) this.e).lambda$createEmptyFolder$2((EventContext) this.f, (ArrayList) this.g);
                return;
            case 22:
                ((CreateFolderCmd) this.e).lambda$createFolder$4((EventContext) this.f, (String) this.g);
                return;
            case 23:
                ((RenameFolderCmd) this.e).lambda$renameFolder$0((EventContext) this.f, (String) this.g);
                return;
            case 24:
                ((SaveObjectCaptureToAlbumCmd) this.e).lambda$saveFile$0((String) this.f, (Uri) this.g);
                return;
            case 25:
                ((EditCreatureDialogDelegate) this.e).lambda$mergeFaceGroup$5((CreatureNameData) this.f, (Consumer) this.g);
                return;
            case 26:
                ((PersonUnlinkCmd) this.e).lambda$onExecute$1((ArrayList) this.f, (ArrayList) this.g);
                return;
            case 27:
                ((RelationshipView) this.e).lambda$bind$1((Future) this.f, (String) this.g);
                return;
            case 28:
                ((RecapPageDataLoader) this.e).lambda$loadData$1((List) this.f, (Consumer) this.g);
                return;
            default:
                ((SearchSelectedFiltersDelegate) this.e).lambda$initMainKeywordEntity$0((String) this.f, (String) this.g);
                return;
        }
    }
}
