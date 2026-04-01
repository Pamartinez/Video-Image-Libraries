package M4;

import android.net.Uri;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.gallery.app.controller.externals.CreateMeituPostCmd;
import com.samsung.android.gallery.app.controller.internals.AddTagBaseCmd;
import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.app.controller.internals.ExportVideoClipsCmd;
import com.samsung.android.gallery.app.controller.internals.ViewLiveEffectCmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.fastoption.SingleTakeSelectionMenuHandler;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.cloud.scpm.GotoLink;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.media.CreateMediaHelper;
import com.samsung.android.gallery.support.cache.CacheHelper;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.BucketUtils;
import java.io.File;
import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2395a;

    public /* synthetic */ j(int i2) {
        this.f2395a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2395a) {
            case 0:
                return AlbumPicturesPresenter.lambda$turnOffMergedAlbum$19((MediaItem) obj);
            case 1:
                return Objects.nonNull((Integer) obj);
            case 2:
                return Objects.nonNull((CacheHelper) obj);
            case 3:
                return CreateMeituPostCmd.lambda$showCombinationErrorToast$3((CreateMediaHelper.SupportType) obj);
            case 4:
                return ((MediaItem) ((MediaItem) obj)).isVideo();
            case 5:
                return ((MediaItem) ((MediaItem) obj)).isVideo();
            case 6:
                return ((MediaItem) ((MediaItem) obj)).isVideo();
            case 7:
                return ((MediaItem) ((MediaItem) obj)).isVideo();
            case 8:
                return BucketUtils.isRecent(((MediaItem) obj).getAlbumID());
            case 9:
                return ((Uri) obj).toString().startsWith("content://com.sec.android.gallery3d.provider2");
            case 10:
                return AddTagBaseCmd.lambda$contains$0((MediaItem) obj);
            case 11:
                return AddTagBaseCmd.lambda$getPrevList$2((MediaItem) obj);
            case 12:
                return AddTagBaseCmd.lambda$onTagSelected$4((MediaItem) obj);
            case 13:
                return AddTagBaseCmd.lambda$onTagSelected$6((String) obj);
            case 14:
                return EditDateAndTimeCmd.lambda$onExecute$2((MediaItem) obj);
            case 15:
                return ExportVideoClipsCmd.lambda$getFilteredList$2((MediaItem) obj);
            case 16:
                return ViewLiveEffectCmd.lambda$startLiveEffectActivity$3((View) obj);
            case 17:
                return BucketUtils.isScreenshot(((MediaItem) obj).getAlbumID());
            case 18:
                return MediaItemMde.isOwnedByMe((MediaItem) obj);
            case 19:
                return SingleTakeSelectionMenuHandler.lambda$removeUnsupportedMenu$0((ViewerMenuItem) obj);
            case 20:
                return SingleTakeSelectionMenuHandler.lambda$removeUnsupportedMenu$1((ViewerMenuItem) obj);
            case 21:
                return SingleTakeSelectionMenuHandler.lambda$removeUnsupportedMenu$2((ViewerMenuItem) obj);
            case 22:
                return TextExtractionHelper.lambda$getFilteredTextData$8((String) obj);
            case 23:
                return TextUtils.isEmpty(((OcrData.BlockInfo) obj).toString());
            case 24:
                return Objects.nonNull((StorageVolume) obj);
            case 25:
                return ((MediaItem) ((MediaItem) obj)).isPostProcessing();
            case 26:
                return GotoLink.lambda$update$0((File) obj);
            case 27:
                return ((String) obj).startsWith(SefInfo.CAMERA_STICKER_BGM_D.keyName);
            case 28:
                return ((MediaItem) ((MediaItem) obj)).isImage();
            default:
                return ChooseSharedAlbumCmd.lambda$checkMediaItem$2((MediaItem) obj);
        }
    }
}
