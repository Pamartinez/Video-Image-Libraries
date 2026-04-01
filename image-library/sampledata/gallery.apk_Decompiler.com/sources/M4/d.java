package M4;

import android.app.Dialog;
import android.os.CancellationSignal;
import android.widget.TextView;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.MaskedObjectResult;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumsPaneSlideAnimationManager;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterListViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.header.AlbumPicturesHeaderView;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureFileHandler;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GallerySwipeView;
import com.samsung.android.gallery.widget.listview.noitem.GalleryListNoItemView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.Arrays;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ d(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((AlbumPicturesPresenter) obj).onDrawerSizeChanged(0);
                return;
            case 1:
                AlbumPicturesFragment.lambda$onChangeAlbum$29((GalleryListView) obj);
                return;
            case 2:
                AlbumPicturesFragment.lambda$adjustToolbarLayout$38((GalleryToolbar) obj);
                return;
            case 3:
                AlbumPicturesFragment.lambda$removeListeners$7((GallerySwipeView) obj);
                return;
            case 4:
                AlbumPicturesFragment.lambda$removeEmptyViewListener$9((GalleryListNoItemView) obj);
                return;
            case 5:
                ((Blackboard) obj).post("command://UiEventStartShrinkAnimation", (Object) null);
                return;
            case 6:
                ((GalleryListView) obj).invalidate();
                return;
            case 7:
                AlbumPicturesFragment.lambda$onSlideEnd$13((GalleryListView) obj);
                return;
            case 8:
                ((GalleryListView) obj).setGoToTopVisibility(false);
                return;
            case 9:
                ((IAlbumsPaneSlideAnimationManager) obj).cancelAnimation();
                return;
            case 10:
                ((AlbumPicturesHeaderView) obj).destroy();
                return;
            case 11:
                ((AlbumPicturesViewAdapter) obj).fixTimelineMode();
                return;
            case 12:
                ((TextView) obj).setText(R.string.create_shared_album);
                return;
            case 13:
                ((TextView) obj).setText(R.string.create_a_new_album_to_share);
                return;
            case 14:
                ((Blackboard) obj).publish("command://request_suicide", (Object) null);
                return;
            case 15:
                ((QueryParams) obj).onlyTrashed();
                return;
            case 16:
                ((CancellationSignal) obj).cancel();
                return;
            case 17:
                ((ScreenShotFilterListViewAdapter) obj).notifyDataSetChanged();
                return;
            case 18:
                ((ScreenShotFilterManager) obj).resetFilter();
                return;
            case 19:
                ((Blackboard) obj).post("command://request_suicide", (Object) null);
                return;
            case 20:
                ((Blackboard) obj).post("command://MoveURL", "location://sharing/albums");
                return;
            case 21:
                ((Blackboard) obj).postEvent(EventMessage.obtain(1003));
                return;
            case 22:
                ((Blackboard) obj).publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
                return;
            case 23:
                Arrays.stream((File[]) obj).forEach(new d(24));
                return;
            case 24:
                ObjectCaptureFileHandler.lambda$deletePreviousFiles$2((File) obj);
                return;
            case 25:
                Blackboard.getApplicationInstance().publish("data://user/CapturedFileWriting", Boolean.FALSE);
                return;
            case 26:
                ObjectCaptureFileHandler.lambda$handleSaveAsImage$7(0, (Boolean) obj);
                return;
            case 27:
                ((ObjectCaptureDrawHelper) obj).disconnectGPPMSession();
                return;
            case 28:
                ((Dialog) obj).dismiss();
                return;
            default:
                ((MaskedObjectResult) obj).release();
                return;
        }
    }
}
