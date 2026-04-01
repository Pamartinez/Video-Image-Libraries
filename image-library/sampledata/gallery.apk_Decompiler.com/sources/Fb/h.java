package Fb;

import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.GlShaderProgram;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.ChangeAlbumCoverCmd;
import com.samsung.android.gallery.app.controller.album.CreateAutoUpdatingAlbumCmd;
import com.samsung.android.gallery.app.controller.album.CreateNamedFolderCmd;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.FolderGroupCmd;
import com.samsung.android.gallery.app.controller.album.MoveFilesOnPrivateCmd;
import com.samsung.android.gallery.app.controller.album.RemoveAutoUpdatedItemsCmd;
import com.samsung.android.gallery.app.controller.album.RenameAutoAlbumCmd;
import com.samsung.android.gallery.app.controller.album.RenameFolderCmd;
import com.samsung.android.gallery.app.controller.album.SortByAlbumDialogCmd;
import com.samsung.android.gallery.app.controller.album.SortByDialogCmd;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureChoiceCmd;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.app.controller.creature.RemoveBackgroundEffectInfoCmd;
import com.samsung.android.gallery.app.controller.creature.UnmergeCreatureCmd;
import com.samsung.android.gallery.app.controller.creature.abstraction.CommitCreatureCmd;
import com.samsung.android.gallery.app.controller.creature.abstraction.CreatureHideCmd;
import com.samsung.android.gallery.app.controller.creature.people.RemoveSubscribedCreatureCmd;
import com.samsung.android.gallery.app.controller.creature.people.UpdateContactPhotoCmd;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.IAlbumsHeaderView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.FaceClusterMergeView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.LogVideoTipHandler;
import com.samsung.android.gallery.app.ui.viewer2.crop.CropViewPresenter;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.gallery.module.media.AudioPlayer;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManagerLegacy;
import com.samsung.android.gallery.widget.listview.pinch.v3.HeaderItem;
import com.samsung.android.gallery.widget.tip.PopupTipBuilder;
import com.samsung.android.gallery.widget.videoview.controller.VideoSpeedControllerCompat;
import java.util.ArrayList;
import k2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements SpecProvider, AudioPlayer.OnAudioPlayerChangedListener, GlShaderProgram.ErrorListener, DataCollectionDelegate.OnDataCompletionListener, PopupTipBuilder.OnDismissListener, VideoSpeedControllerCompat.ChangeSpeedListener, SearchView.OnCloseListener, IAlbumsHeaderView.OnDataChangeListener, q, FutureListener, PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ h(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public int[] getSpec(int i2, int i7) {
        return ((GalleryRecyclerView) this.e).getRealRatioSpec(i2, i7);
    }

    public void onAnimationEnd(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 24:
                ((PinchAnimationManagerLegacy) obj).resetTranslate(view);
                return;
            default:
                ((HeaderItem) obj).resetAlpha(view);
                return;
        }
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 3:
                ((ChangeAlbumCoverCmd) obj).changeCoverImage(eventContext, arrayList);
                return;
            case 4:
                ((CreateAutoUpdatingAlbumCmd) obj).launchCreatureSelect(eventContext, arrayList);
                return;
            case 5:
                ((CreateNamedFolderCmd) obj).onConfirmed(eventContext, arrayList);
                return;
            case 6:
                ((FileOpCmd) obj).lambda$startChoiceAlbumCmd$3(eventContext, arrayList);
                return;
            case 7:
                ((FolderGroupCmd) obj).addAlbumToFolder(eventContext, arrayList);
                return;
            case 8:
                ((MoveFilesOnPrivateCmd) obj).onConfirmed(eventContext, arrayList);
                return;
            case 9:
                ((RemoveAutoUpdatedItemsCmd) obj).removeAutoUpdatedItems(eventContext, arrayList);
                return;
            case 10:
                ((RenameAutoAlbumCmd) obj).renameAutoAlbum(eventContext, arrayList);
                return;
            case 11:
                ((RenameFolderCmd) obj).renameFolder(eventContext, arrayList);
                return;
            case 12:
                ((SortByAlbumDialogCmd) obj).setSortBy(eventContext, arrayList);
                return;
            case 13:
                ((SortByDialogCmd) obj).setSortBy(eventContext, arrayList);
                return;
            case 17:
                ((MergeCreatureChoiceCmd) obj).lambda$onExecute$0(eventContext, arrayList);
                return;
            case 18:
                ((MergeCreatureMultipleCmd) obj).lambda$showMergeConfirmDialog$4(eventContext, arrayList);
                return;
            case 19:
                ((RemoveBackgroundEffectInfoCmd) obj).onConfirmed(eventContext, arrayList);
                return;
            case 20:
                ((UnmergeCreatureCmd) obj).undoMerge(eventContext, arrayList);
                return;
            case 28:
                ((RemoveSubscribedCreatureCmd) obj).onDataComplete(eventContext, arrayList);
                return;
            default:
                ((UpdateContactPhotoCmd) obj).updatePhoto(eventContext, arrayList);
                return;
        }
    }

    public void onDismiss() {
        ((LogVideoTipHandler) this.e).lambda$showLogVideoTip$0();
    }

    public void onError(VideoFrameProcessingException videoFrameProcessingException) {
        ((VideoFrameProcessor.Listener) this.e).onError(videoFrameProcessingException);
    }

    public void onFutureDone(Future future) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 23:
                ((CropViewPresenter) obj).lambda$saveCropImageFile$3(future);
                return;
            case 25:
                ((CommitCreatureCmd) obj).lambda$addTag$1(future);
                return;
            default:
                ((CreatureHideCmd) obj).lambda$hide$3(future);
                return;
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return ((FaceClusterMergeView) this.e).lambda$new$7(menuItem);
    }
}
