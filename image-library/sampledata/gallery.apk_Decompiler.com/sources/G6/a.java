package G6;

import android.content.Context;
import android.net.Uri;
import android.widget.TextView;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPager;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPanePresenter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.StoryCover;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ a(MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = mediaItem;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaItem mediaItem = this.e;
        switch (i2) {
            case 0:
                ((Context) obj).grantUriPermission(ShareProvider.SHARE_SHEET_PACKAGE, ContentUri.getUri(mediaItem), 1);
                return;
            case 1:
                AlbumPicturesPresenter.lambda$refreshAlbumSetting$2(mediaItem, (MediaItem) obj);
                return;
            case 2:
                AlbumsPanePresenter.lambda$refreshAlbumSetting$6(mediaItem, (MediaItem) obj);
                return;
            case 3:
                ((Consumer) obj).accept(mediaItem.getSubCategory());
                return;
            case 4:
                ((QueryParams) obj).addAlbumId(mediaItem.getAlbumID());
                return;
            case 5:
                ((QueryParams) obj).setFileId(mediaItem.getFileId());
                return;
            case 6:
                PresentationViewPager.lambda$updateViews$3(mediaItem, (TextView) obj);
                return;
            case 7:
                PresentationViewPager.lambda$updateViews$4(mediaItem, (TextView) obj);
                return;
            case 8:
                MediaItemStory.setStoryFavorite((MediaItem) obj, MediaItemStory.getStoryFavorite(mediaItem));
                return;
            case 9:
                ((Blackboard) obj).postEvent(EventMessage.obtain(1082, -1, 0, mediaItem));
                return;
            case 10:
                ((QueryParams) obj).setFileId(mediaItem.getFileId()).setUngroupBurstShot(true);
                return;
            case 11:
                mediaItem.setFileSize(FileUtils.length((String) obj));
                return;
            case 12:
                ((Context) obj).grantUriPermission(ShareProvider.SHARE_SHEET_PACKAGE, ContentUri.getUri(mediaItem), 1);
                return;
            case 13:
                ((Context) obj).grantUriPermission(ShareProvider.SHARE_SHEET_PACKAGE, ContentUri.getUri(mediaItem), 1);
                return;
            case 14:
                MediaItemMde.setDownloadVideoUri(mediaItem, (Uri) obj);
                return;
            case 15:
                MediaItemMde.setDownloadVideoPath(mediaItem, (String) obj);
                return;
            case 16:
                MediaItemMde.setDownloadVideoVerified(mediaItem, (Boolean) obj);
                return;
            case 17:
                ((QueryParams) obj).setFileId(mediaItem.getFileId());
                return;
            default:
                ((StoryCover) obj).updateCover(mediaItem);
                return;
        }
    }
}
