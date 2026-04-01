package O3;

import android.graphics.Bitmap;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.FlacStreamMetadata;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.StartCropImageCmd;
import com.samsung.android.gallery.app.controller.internals.UploadToRemoteCmd;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.controller.mtp.MtpImportCmd;
import com.samsung.android.gallery.app.controller.mxalbum.AlbumCreatePopupDialogCmd;
import com.samsung.android.gallery.app.controller.mxalbum.SharedAlbumCreatePopupDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedAlbumItemCmd;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedItemFromTrashCmd;
import com.samsung.android.gallery.app.controller.sharing.EmptySharedItemFromTrashCmd;
import com.samsung.android.gallery.app.controller.sharing.LeaveSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.RemoveContentsFromSuggestionCmd;
import com.samsung.android.gallery.app.controller.sharing.RenameSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestDownloadImageCmd;
import com.samsung.android.gallery.app.controller.sharing.SharedSortByDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.ShowSharingInvitationsJoinDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.SortBySharingDialogCmd;
import com.samsung.android.gallery.app.controller.story.AddContentsToStoryCmd;
import com.samsung.android.gallery.app.controller.story.CreateStoryWithPickCmd;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingHeaderViewDelegateV2;
import com.samsung.android.gallery.app.ui.list.sharings.pinch.SharingPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterPicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineViewAdapter;
import com.samsung.android.gallery.app.ui.list.timeline.quicksearch.AbsQuickSearchFilterListViewAdapter;
import com.samsung.android.gallery.app.ui.tipcard.TipCardUpdateListener;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.list.GroupPanelVideoPreview;
import com.samsung.android.gallery.app.ui.viewer2.menu.DownloadMenuItem;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.cloud.SamsungCloudManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class y implements DataCollectionDelegate.OnDataCompletionListener, ThumbnailLoadedListener, ListViewHolder.OnItemClickListener, PreviewViewHolder.OnCompletionListener, WidthAnimator.WidthAnimationCallback, MediaScannerListener, SamsungCloudManager.OnCloudSyncStatusListener, BinarySearchSeeker.SeekTimestampConverter, TipCardUpdateListener, PopupMenu.OnMenuItemClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ y(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public boolean isWidthAnimationNeeded(View view, int i2) {
        return ((SharingPinchAnimationManager) this.e).isWidthAnimationNeeded((TextView) view, i2);
    }

    public void onCompleted() {
        ((DownloadMenuItem) this.e).lambda$saveTempFile$1();
    }

    public void onCompletion(PreviewViewHolder previewViewHolder, boolean z) {
        ((GroupPanelVideoPreview) this.e).lambda$startPreviewViewHolders$3(previewViewHolder, z);
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((StartCropImageCmd) obj).onCropResult(eventContext, arrayList);
                return;
            case 1:
                ((UploadToRemoteCmd) obj).onComplete(eventContext, arrayList);
                return;
            case 2:
                ((ViewAsCmd) obj).changeViewMode(eventContext, arrayList);
                return;
            case 4:
                ((MtpImportCmd) obj).onSelectionCompleted(eventContext, arrayList);
                return;
            case 7:
                ((AlbumCreatePopupDialogCmd) obj).onItemSelected(eventContext, arrayList);
                return;
            case 8:
                ((SharedAlbumCreatePopupDialogCmd) obj).onItemSelected(eventContext, arrayList);
                return;
            case 15:
                ((DeleteSharedAlbumCmd) obj).requestDeleteSharedAlbum(eventContext, arrayList);
                return;
            case 16:
                ((DeleteSharedAlbumItemCmd) obj).requestDeleteSharedAlbumItem(eventContext, arrayList);
                return;
            case 17:
                ((DeleteSharedItemFromTrashCmd) obj).deleteFromTrash(eventContext, arrayList);
                return;
            case 18:
                ((EmptySharedItemFromTrashCmd) obj).emptyTrash(eventContext, arrayList);
                return;
            case 19:
                ((LeaveSharedAlbumCmd) obj).requestLeaveSharedAlbum(eventContext, arrayList);
                return;
            case 20:
                ((RemoveContentsFromSuggestionCmd) obj).removeContents(eventContext, arrayList);
                return;
            case 21:
                ((RenameSharedAlbumCmd) obj).renameSharedAlbum(eventContext, arrayList);
                return;
            case 22:
                ((RequestDownloadImageCmd) obj).onDownloadConfirmed(eventContext, arrayList);
                return;
            case 23:
                ((SharedSortByDialogCmd) obj).setSortBy(eventContext, arrayList);
                return;
            case 24:
                ((ShowSharingInvitationsJoinDialogCmd) obj).requestJoinSharedAlbum(eventContext, arrayList);
                return;
            case 25:
                ((SortBySharingDialogCmd) obj).setSortBy(eventContext, arrayList);
                return;
            case 28:
                ((AddContentsToStoryCmd) obj).addToStory(eventContext, arrayList);
                return;
            default:
                ((CreateStoryWithPickCmd) obj).createStory(eventContext, arrayList);
                return;
        }
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        int i8 = this.d;
        Object obj = this.e;
        switch (i8) {
            case 5:
                ((RemasterPicturesViewAdapter) obj).onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
                return;
            default:
                ((AbsQuickSearchFilterListViewAdapter) obj).onItemClicked(listViewHolder, i2, mediaItem, i7);
                return;
        }
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 3:
                ((SharingHeaderViewDelegateV2) obj).lambda$bindImageToView$3(bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((TimelineViewAdapter) obj).setBitmapWithBind(bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return ((IStoriesView) this.e).onOptionsItemMenuSelected(menuItem);
    }

    public void onUpdateCloudSyncStatus(int i2) {
        ((SamsungCloudManager) this.e).lambda$new$0(i2);
    }

    public long timeUsToTargetTime(long j2) {
        return ((FlacStreamMetadata) this.e).getSampleNumber(j2);
    }
}
