package M9;

import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import com.samsung.android.gallery.app.controller.externals.LaunchAlbumViewCmd;
import com.samsung.android.gallery.app.controller.internals.AddSingleTagCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteFromCleanOutCmd;
import com.samsung.android.gallery.app.controller.internals.EachExportVideoClipsCmd;
import com.samsung.android.gallery.app.controller.internals.ExportVideoClipsCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoDeleteVideoCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveRemasteredImageCmd;
import com.samsung.android.gallery.app.controller.mxalbum.AbsAlbumCreatePopupDialogCmd;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterDelegate;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AlbumSettingPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AutoAlbumSettingFragment;
import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySuggestedPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.cleanout.CleanOutPicturesFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.cleanout.ICleanOutPicturesView;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterPicturesFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.revitalized.RevitalizedPicturesPinchAnimationManager;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.list.GroupPanelVideoPreview;
import com.samsung.android.gallery.app.ui.viewer2.menu.CopyEffectMenu;
import com.samsung.android.gallery.app.ui.viewer2.menu.DownloadFromRemoteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.OpenInVideoPlayerMenuItem;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureGPPMSession;
import com.samsung.android.gallery.module.clipboard.ClipboardDataPrepareTask;
import com.samsung.android.gallery.module.longexposure.LongExposureHelper;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisher;
import com.samsung.android.gallery.module.receiver.GlobalActionReceiver;
import com.samsung.android.gallery.module.remaster.RemasterSaveController;
import com.samsung.android.gallery.widget.photoview.ImageProcessor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ o(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((StoriesDataPublisher) obj).lambda$publishStoriesCacheData$0();
                return;
            case 1:
                ((LaunchAlbumViewCmd) obj).lambda$onExecute$0();
                return;
            case 2:
                ((ScreenShotFilterDelegate) obj).onDataChangedOnUi();
                return;
            case 3:
                ((FamilySuggestedPicturesPresenter) obj).setFamilySuggestedTipCardCondition();
                return;
            case 4:
                ((SuggestionsFragment) obj).lambda$onConfigurationChanged$0();
                return;
            case 5:
                ((LottieAnimationView) obj).c();
                return;
            case 6:
                ((ObjectCaptureGPPMSession) obj).disconnect();
                return;
            case 7:
                ((ImageProcessor) obj).lambda$close$9();
                return;
            case 8:
                ((AddSingleTagCmd) obj).operate();
                return;
            case 9:
                ((DeleteFromCleanOutCmd) obj).lambda$operateDelete$0();
                return;
            case 10:
                ((EachExportVideoClipsCmd) obj).lambda$onExecute$0();
                return;
            case 11:
                ((ExportVideoClipsCmd) obj).lambda$onExecute$0();
                return;
            case 12:
                ((LongExposureHelper) obj).cancel();
                return;
            case 13:
                ((MotionPhotoDeleteVideoCmd) obj).lambda$onSelected$0();
                return;
            case 14:
                ((RemoveRemasteredImageCmd) obj).lambda$remove$1();
                return;
            case 15:
                ((ICleanOutPicturesView) obj).finish();
                return;
            case 16:
                ((CleanOutPicturesFragment) obj).updateHeaderText();
                return;
            case 17:
                ((TextExtractionDrawHelper.ProgressBarCallback) obj).finishProgressBar();
                return;
            case 18:
                ((AlbumSettingPresenter) obj).lambda$announceScreenLabel$1();
                return;
            case 19:
                ((AutoAlbumSettingFragment) obj).lambda$updateCreatureCount$2();
                return;
            case 20:
                ((RemasterPicturesFragment) obj).startShrinkAnimation();
                return;
            case 21:
                ((GroupPanelVideoPreview) obj).startPreviewViewHolders();
                return;
            case 22:
                ((ClipboardDataPrepareTask) obj).lambda$run$0();
                return;
            case 23:
                ((GlobalActionReceiver) obj).lambda$processDateTimeChanged$16();
                return;
            case 24:
                ((AbsAlbumCreatePopupDialogCmd) obj).createPopupByType();
                return;
            case 25:
                ((RevitalizedPicturesPinchAnimationManager) obj).lambda$setFakeViewLayoutAlpha$0();
                return;
            case 26:
                ((CopyEffectMenu) obj).lambda$updateMenu$3();
                return;
            case 27:
                ((DownloadFromRemoteMenuItem) obj).lambda$onMenuSelectInternal$1();
                return;
            case 28:
                ((OpenInVideoPlayerMenuItem) obj).lambda$onMenuSelectInternal$2();
                return;
            default:
                ((RemasterSaveController) obj).lambda$postSaveFromOnDemand$0();
                return;
        }
    }
}
