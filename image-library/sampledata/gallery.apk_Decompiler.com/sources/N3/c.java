package N3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Size;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayGenericVideoCmd;
import com.samsung.android.gallery.app.controller.externals.SetAsWallpaperChooserDialogCmd;
import com.samsung.android.gallery.app.controller.externals.StoryHighlightShareViaCmd;
import com.samsung.android.gallery.app.controller.internals.AddTagToListCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeLocationCmd2;
import com.samsung.android.gallery.app.controller.internals.DownloadCmd;
import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.app.controller.internals.SetAsChooserDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.ShowSharingInvitationsJoinDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestAddContent;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateStory;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteContent;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteFamilySpace;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteSpace;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDownloadContent;
import com.samsung.android.gallery.app.controller.sharing.request.RequestUpdateSpace;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.storage.SharingStorageUseFragment;
import com.samsung.android.gallery.app.ui.list.timeline.quicksearch.AbsQuickSearchFilterListViewAdapter;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureGPPMSession;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.quicksearch.QuickSearchManager;
import com.samsung.android.gallery.widget.photoview.ImageProcessor;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.photoview.PhotoPreViewDebugDelegate;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import com.samsung.android.sdk.mobileservice.social.share.result.ItemListResult;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((SetAsWallpaperChooserDialogCmd) this.e).lambda$onPreExecute$1((EventContext) this.f, (MediaItem[]) obj);
                return;
            case 1:
                ((StoryHighlightShareViaCmd) this.e).lambda$loadAdditionalExtraData$0((Intent) this.f, (EventContext) obj);
                return;
            case 2:
                ((ObjectCaptureGPPMSession) this.e).lambda$onTaskCompleted$0((Uri) this.f, (MediaItem) obj);
                return;
            case 3:
                ((ImageProcessor) this.e).lambda$tryRegionDecoding$0((View) this.f, (BitmapOptions) obj);
                return;
            case 4:
                PhotoPreView photoPreView = (PhotoPreView) this.e;
                Size size = (Size) this.f;
                if (obj == null) {
                    photoPreView.lambda$calculateParentViewSize$1(size, (PhotoPreViewDebugDelegate) null);
                    return;
                }
                throw new ClassCastException();
            case 5:
                ((AddTagToListCmd) this.e).lambda$onPreExecute$0((EventContext) this.f, (MediaItem[]) obj);
                return;
            case 6:
                ((ChangeLocationCmd) this.e).lambda$onPreExecute$1((EventContext) this.f, (MediaItem[]) obj);
                return;
            case 7:
                ((ChangeLocationCmd2) this.e).lambda$onPreExecute$1((EventContext) this.f, (MediaItem[]) obj);
                return;
            case 8:
                ((DownloadCmd) this.e).lambda$onPreExecute$0((EventContext) this.f, (MediaItem[]) obj);
                return;
            case 9:
                ((EditDateAndTimeCmd) this.e).lambda$onPreExecute$1((EventContext) this.f, (MediaItem[]) obj);
                return;
            case 10:
                ((FixDateTimeCmd) this.e).lambda$updateGroupItems$14((FixDateTimeCmd.Updater) this.f, (MediaItem) obj);
                return;
            case 11:
                ((FixDateTimeCmd) this.e).lambda$updateDateTimeAsync$4((Consumer) this.f, (MediaItem) obj);
                return;
            case 12:
                ((FixDateTimeCmd) this.e).lambda$fixDateTime$8((Function) this.f, (MediaItem) obj);
                return;
            case 13:
                ((SetAsChooserDialogCmd) this.e).lambda$onPreExecute$1((EventContext) this.f, (MediaItem[]) obj);
                return;
            case 14:
                ((TextExtractionHelper) this.e).lambda$barcodeScan$6((Bitmap) this.f, (Context) obj);
                return;
            case 15:
                ((QuickSearchManager) this.e).lambda$getFilteredMediaIds$1((ArrayList) this.f, (String) obj);
                return;
            case 16:
                ((SharingStorageUseFragment) this.e).lambda$bindFamilyItem$5((MediaItem) this.f, (BaseListViewAdapter) obj);
                return;
            case 17:
                ((ChooseSharedAlbumCmd) this.e).lambda$onPreExecute$1((EventContext) this.f, (MediaItem[]) obj);
                return;
            case 18:
                ((ShowSharingInvitationsJoinDialogCmd) this.e).lambda$requestJoinSharedAlbum$0((EventContext) this.f, (RequestCmdType) obj);
                return;
            case 19:
                ((AbsQuickSearchFilterListViewAdapter) this.e).lambda$onBindViewHolder$0((ImageTitleViewHolder) this.f, (MediaItem) obj);
                return;
            case 20:
                ((RequestAddContent) this.e).lambda$requestAddContents$0((List) this.f, (SharedItemListResult) obj);
                return;
            case 21:
                ((RequestCreateStory) this.e).lambda$onScanCompleted$8((HashMap) this.f, (MediaItem) obj);
                return;
            case 22:
                ((RequestCreateStory) this.e).lambda$postDownloadContent$2((List) this.f, (FileItemInterface) obj);
                return;
            case 23:
                ((RequestDeleteContent) this.e).lambda$getUploadingItems$2((ArrayList) this.f, (ItemListResult.SharedItemListFailureResult) obj);
                return;
            case 24:
                ((RequestDeleteFamilySpace) this.e).lambda$requestDeleteFamilySpace$1((String) this.f, (Integer) obj);
                return;
            case 25:
                ((RequestDeleteSpace) this.e).lambda$requestDeleteSpace$3((List) this.f, (Integer) obj);
                return;
            case 26:
                ((RequestDeleteSpace) this.e).lambda$requestDeleteSpace$2((Integer) this.f, (FileItemInterface) obj);
                return;
            case 27:
                ((RequestDownloadContent) this.e).lambda$showDownloadWarningAsPartialUploading$4((ArrayList) this.f, (ContentDownloadResult.DownloadedContent) obj);
                return;
            case 28:
                new PlayGenericVideoCmd().execute((EventContext) obj, (FileItemInterface) this.e, (Uri) this.f);
                return;
            default:
                ((RequestUpdateSpace) this.e).lambda$requestUpdateSpace$0((String) this.f, (Integer) obj);
                return;
        }
    }
}
