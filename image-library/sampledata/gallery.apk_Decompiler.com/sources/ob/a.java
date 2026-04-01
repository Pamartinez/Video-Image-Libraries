package Ob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.mxalbum.SharedAlbumCreatePopupDialogCmd;
import com.samsung.android.gallery.app.controller.ppp.PppChecker;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.EmptySharedItemFromTrashCmd;
import com.samsung.android.gallery.app.controller.sharing.request.AbsRequest;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateStory;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDownloadContent;
import com.samsung.android.gallery.app.controller.story.AddContentsToStoryCmd;
import com.samsung.android.gallery.app.controller.story.CollageVideoSaveCmd;
import com.samsung.android.gallery.app.controller.story.CreateStoryWithPickCmd;
import com.samsung.android.gallery.app.controller.story.RemoveHideDateCmd;
import com.samsung.android.gallery.app.controller.story.SaveRecapVideoCmd;
import com.samsung.android.gallery.app.controller.story.SaveTransitoryToOthersCmd;
import com.samsung.android.gallery.app.controller.story.StartHighlightPlayerEditCmd;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AutoAlbumSettingFragment;
import com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate;
import com.samsung.android.gallery.app.ui.list.sharings.pinch.SharingItemPinchViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.suggestions.revitalized.RevitalizedPicturesViewAdapter;
import com.samsung.android.gallery.app.ui.viewer2.menu.DownloadFromRemoteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.remaster.focusview.FocusItemViewHolder;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.album.AutoAlbumSettingData;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.story.ExportOptions;
import com.samsung.android.gallery.module.story.transcode.HighlightEncoder;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.popover.PopoverInfo;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;
import com.samsung.android.gallery.widget.search.filter.AbsFiltersAdapter;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((PopoverInfo) this.e).lambda$notifyAnchorChanged$0((View) this.f);
                return;
            case 1:
                ((AutoAlbumSettingFragment) this.e).lambda$updateCreatureCount$1((AutoAlbumSettingData) this.f);
                return;
            case 2:
                ((SharedAlbumCreatePopupDialogCmd) this.e).lambda$checkSupportFamilyAlbumCreation$0((Consumer) this.f);
                return;
            case 3:
                ((SharingItemPinchViewHolder) this.e).lambda$bindImage$0((Bitmap) this.f);
                return;
            case 4:
                ((RevitalizedPicturesViewAdapter) this.e).lambda$onBindViewHolder$0((ListViewHolder) this.f);
                return;
            case 5:
                ((DownloadFromRemoteMenuItem) this.e).lambda$onMenuSelectInternal$2((MediaItem) this.f);
                return;
            case 6:
                ((EditMenuItem) this.e).lambda$onMenuSelectInternal$1((MediaItem) this.f);
                return;
            case 7:
                ((PreviewHdrVideo) this.e).lambda$loadVideoInfo$3((FileItemInterface) this.f);
                return;
            case 8:
                ((PreviewHdrVideo) this.e).lambda$loadVideoInfo$1((MediaHelper.VideoInfo) this.f);
                return;
            case 9:
                ((PppChecker) this.e).lambda$showDialog$1((EventContext) this.f);
                return;
            case 10:
                ((ChooseSharedAlbumCmd) this.e).lambda$showSharingAlbumDialogAfterLoadingItem$4(this.f);
                return;
            case 11:
                ((EmptySharedItemFromTrashCmd) this.e).lambda$onExecute$0((EventContext) this.f);
                return;
            case 12:
                try {
                    ((Consumer) this.f).accept(((Intent) this.e).getExtras());
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            case 13:
                ((AbsRequest) this.e).lambda$showToast$0((String) this.f);
                return;
            case 14:
                ((RequestCreateStory) this.e).lambda$onScanCompleted$9((HashMap) this.f);
                return;
            case 15:
                ((RequestCreateStory) this.e).lambda$postDownloadContent$3((List) this.f);
                return;
            case 16:
                ((RequestDownloadContent) this.e).lambda$postDownloadContent$1((ContentDownloadResult) this.f);
                return;
            case 17:
                ((StoriesBaseViewAdapter) this.e).lambda$calculateItemHeight$0((ListViewHolder) this.f);
                return;
            case 18:
                ((FocusItemViewHolder) this.e).lambda$setCropRectRatio$0((RectF) this.f);
                return;
            case 19:
                ((AbsFiltersAdapter) this.e).lambda$bindThumbnailTypeViewHolder$0((FilterResultsEntity) this.f);
                return;
            case 20:
                ((AddContentsToStoryCmd) this.e).lambda$onExecute$0((EventContext) this.f);
                return;
            case 21:
                ((CollageVideoSaveCmd) this.e).lambda$onExecute$1((ExportOptions) this.f);
                return;
            case 22:
                ((CollageVideoSaveCmd) this.e).lambda$startSaveVideo$2((HighlightEncoder) this.f);
                return;
            case 23:
                ((CreateStoryWithPickCmd) this.e).lambda$onExecute$1((EventContext) this.f);
                return;
            case 24:
                ((RemoveHideDateCmd) this.e).lambda$onExecute$0((Object[]) this.f);
                return;
            case 25:
                ((SaveRecapVideoCmd) this.e).lambda$onExecute$0((EventContext) this.f);
                return;
            case 26:
                ((SaveRecapVideoCmd) this.e).lambda$saveRecap$2((MediaItem) this.f);
                return;
            case 27:
                ((SaveTransitoryToOthersCmd) this.e).lambda$saveToOthers$0((MediaItem) this.f);
                return;
            case 28:
                ((StartHighlightPlayerEditCmd) this.e).lambda$setBgmUrisIfEmpty$0((BgmExtraInfo) this.f);
                return;
            default:
                ((ListPopupMenuDelegate) this.e).lambda$setPopupWindowProperty$8((View) this.f);
                return;
        }
    }
}
