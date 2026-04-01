package D7;

import Da.f;
import android.webkit.WebView;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.MultipleInputVideoGraph;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.abstraction.ViewerDownloadTask;
import com.samsung.android.gallery.app.controller.album.AutoGroupCmd;
import com.samsung.android.gallery.app.controller.album.ChangeAlbumLevelCmd;
import com.samsung.android.gallery.app.controller.album.CreateNamedAlbumCmd;
import com.samsung.android.gallery.app.controller.album.CreateNamedFolderCmd;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.recap.RecapPresenter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.MotionPhotoImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.liveeffectvideo.LiveEffectVideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionVideoController;
import com.samsung.android.gallery.module.analyticsquery.SasCount;
import com.samsung.android.gallery.module.bgm.BgmUriProviderManager;
import com.samsung.android.gallery.module.mde.executor.CreateFamilyGroup;
import com.samsung.android.gallery.module.mde.executor.CreateSpace;
import com.samsung.android.gallery.module.media.quramsoft.AgifMovie;
import com.samsung.android.gallery.settings.ui.AboutFragment;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.settings.ui.SettingPresenter;
import com.samsung.android.gallery.settings.ui.TroubleshootingFragment;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.ListScrollBuilder;
import com.samsung.android.gallery.widget.listview.handler.AbsPinchAnimationHandler;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.o3dp.lib3dphotography.O3DPhotoPipeline;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((MotionPhotoImageLoader) obj).lambda$onViewAttached$2();
                return;
            case 1:
                ((O3DPhotoPipeline) obj).abortRecordingVideo();
                return;
            case 2:
                ((LiveEffectVideoController) obj).onShotModeClicked();
                return;
            case 3:
                ((CreateFamilyGroup) obj).lambda$onFailure$1();
                return;
            case 4:
                ((CreateSpace) obj).lambda$onFailure$1();
                return;
            case 5:
                ((HoverPreviewViewHolder) obj).lambda$onVideoStop$0();
                return;
            case 6:
                ((BaseCommand) obj).lambda$hideProgress$2();
                return;
            case 7:
                ((GalleryAppBarLayout) obj).setExpanded(false, false);
                return;
            case 8:
                ((MotionVideoController) obj).onShotModeClicked();
                return;
            case 9:
                ((BgmUriProviderManager) obj).onLoadedBgm();
                return;
            case 10:
                ((AgifMovie) obj).finish();
                return;
            case 11:
                ((AboutFragment) obj).lambda$initLayout$3();
                return;
            case 12:
                ((GalleryPreference) obj).clear();
                return;
            case 13:
                LabsDevManageFragment.lambda$addCategoryStatus$49((File) obj);
                return;
            case 14:
                ((ProgressDialogCompat) obj).dismiss();
                return;
            case 15:
                ThreadUtil.postOnUiThread(new f(22, (WebView) obj, new SasCount().summaryHtmlOfSaS()));
                return;
            case 16:
                ((SettingPresenter) obj).lambda$removeStoryAndRecapData$3();
                return;
            case 17:
                ((TroubleshootingFragment) obj).lambda$executeDiagnose$1();
                return;
            case 18:
                ((GalleryListAdapter) obj).notifyAdvancedMouseFocusedItemChanged();
                return;
            case 19:
                ((ListScrollBuilder) obj).lambda$apply$1();
                return;
            case 20:
                ((ViewerDownloadTask) obj).lambda$onPostExecute$0();
                return;
            case 21:
                ((FolderViewAdapter) obj).notifyDataSetChanged();
                return;
            case 22:
                ((RecapPresenter) obj).onDataChangedOnUi();
                return;
            case 23:
                ((AbsPinchAnimationHandler) obj).completePinchAnimation();
                return;
            case 24:
                ((VideoFrameProcessor.Listener) obj).onEnded();
                return;
            case 25:
                ((MultipleInputVideoGraph) obj).lambda$release$1();
                return;
            case 26:
                ((AutoGroupCmd) obj).create();
                return;
            case 27:
                ((ChangeAlbumLevelCmd) obj).changeAlbumLevel();
                return;
            case 28:
                ((CreateNamedAlbumCmd) obj).decideExecution();
                return;
            default:
                ((CreateNamedFolderCmd) obj).decideExecution();
                return;
        }
    }
}
