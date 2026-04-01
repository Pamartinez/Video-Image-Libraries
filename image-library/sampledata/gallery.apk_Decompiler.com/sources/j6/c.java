package J6;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.net.Uri;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.people.RelationshipMultiplePickerCmd;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.app.controller.internals.ImageTranscodeCmd;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoDeleteVideoCmd;
import com.samsung.android.gallery.app.controller.internals.ShowSnackBarForViewerCmd;
import com.samsung.android.gallery.app.controller.internals.ViewLiveEffectCmd;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderView;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPanePresenter;
import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySuggestedPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.Order;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.SlideshowV2Presenter;
import com.samsung.android.gallery.app.ui.list.stories.spotify.SpotifySlideshowFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterItemListAdapter;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsListHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.app.ui.viewer2.menu.GenEditMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.GenEditVideoMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.TrashRestorePrivateMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.c2pa.C2paDummyImp;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.publisher.SharingsDataPublisher;
import com.samsung.android.gallery.module.receiver.UsbDetachReceiver;
import com.samsung.android.gallery.module.share.ShareComponent;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((SlideshowV2Presenter) this.e).lambda$changeOrder$1((MediaData) this.f, (Order) this.g);
                return;
            case 1:
                ((DrmSessionEventListener.EventDispatcher) this.e).lambda$drmSessionManagerError$2((DrmSessionEventListener) this.f, (Exception) this.g);
                return;
            case 2:
                ((RelationshipMultiplePickerCmd) this.e).lambda$onExecute$0((Object[]) this.f, (EventContext) this.g);
                return;
            case 3:
                ((MxAlbumsHeaderView) this.e).lambda$updateViews$0((MxAlbumsHeaderView) this.f, (MxAlbumsHeaderViewState) this.g);
                return;
            case 4:
                ((SpotifySlideshowFragment) this.e).lambda$blurAndBindBitmap$4((ImageView) this.f, (Bitmap) this.g);
                return;
            case 5:
                C2paDummyImp.lambda$update$1((Function) this.e, (FileItemInterface) this.f, (com.samsung.android.visual.ai.sdkcommon.c) this.g);
                return;
            case 6:
                ((DetailsListHandler) this.e).lambda$onLoadDone$4((Object[]) this.f, (DetailsLoadResult) this.g);
                return;
            case 7:
                ((DetailsLoadHandler) this.e).lambda$handleBlackboardEvent$10((EventMessage) this.f, (MediaItem) this.g);
                return;
            case 8:
                ((AlbumPicturesFragment) this.e).lambda$updateHeaderView$31((AtomicBoolean) this.f, (MediaItem) this.g);
                return;
            case 9:
                ((AlbumPicturesPresenter) this.e).lambda$updateSubTitle$3((Toolbar) this.f, (String) this.g);
                return;
            case 10:
                ((AlbumPicturesPresenter) this.e).lambda$findMatchedAlbumAndUpdate$18((MediaItem) this.f, (ArrayList) this.g);
                return;
            case 11:
                ((AlbumsPanePresenter) this.e).lambda$moveToTargetAlbum$1((MediaData) this.f, (MediaItem) this.g);
                return;
            case 12:
                ((AlbumsPanePresenter) this.e).lambda$moveToNewAlbum$4((MediaData) this.f, (String) this.g);
                return;
            case 13:
                ((SharingsDataPublisher) this.e).lambda$publishSharingsData$5((Cursor[]) this.f, (TimeTickLog) this.g);
                return;
            case 14:
                ((BiConsumer) this.e).accept((String) this.f, (Uri) this.g);
                return;
            case 15:
                ((ShareViaCmd) this.e).lambda$onExecute$0((MediaItem[]) this.f, (ShareComponent) this.g);
                return;
            case 16:
                ((FamilySuggestedPicturesPresenter) this.e).lambda$hasPeopleList$1((AtomicBoolean) this.f, (CountDownLatch) this.g);
                return;
            case 17:
                ((EditDateAndTimeCmd) this.e).lambda$runDateUpdater$4((MediaItem[]) this.f, (Object[]) this.g);
                return;
            case 18:
                ((ImageTranscodeCmd) this.e).lambda$execute$0((String) this.f, (String) this.g);
                return;
            case 19:
                ((MotionPhotoDeleteVideoCmd) this.e).lambda$onConfirmed$2((String) this.f, (MediaItem) this.g);
                return;
            case 20:
                ((ShowSnackBarForViewerCmd) this.e).lambda$onExecute$0(this.f, (String) this.g);
                return;
            case 21:
                ((ViewLiveEffectCmd) this.e).lambda$loadBitmap$2((MediaItem) this.f, (Consumer) this.g);
                return;
            case 22:
                ((SharingPicturesFragment) this.e).lambda$needToAddFamilyMember$6((AtomicInteger) this.f, (CountDownLatch) this.g);
                return;
            case 23:
                ((TextExtractionHelper) this.e).lambda$extract$2((Bitmap) this.f, (Bitmap) this.g);
                return;
            case 24:
                ((RemasterItemListAdapter) this.e).lambda$bindThumbnail$1((ListViewHolder) this.f, (Bitmap) this.g);
                return;
            case 25:
                ((UsbDetachReceiver) this.e).lambda$onDetached$0((Context) this.f, (UsbDevice) this.g);
                return;
            case 26:
                ((GenEditMenuItem) this.e).lambda$handleEditor$3((MediaItem) this.f, (Runnable) this.g);
                return;
            case 27:
                ((GenEditVideoMenuItem) this.e).lambda$handleEditor$3((MediaItem) this.f, (Runnable) this.g);
                return;
            case 28:
                ((TrashRestorePrivateMenuItem) this.e).lambda$onMenuSelectInternal$2((MediaItem) this.f, (Context) this.g);
                return;
            default:
                ((ViewerMenuItem) this.e).lambda$executeMediaCaptureCmd$27((MediaItem) this.f, (ConvertingUsageType) this.g);
                return;
        }
    }
}
