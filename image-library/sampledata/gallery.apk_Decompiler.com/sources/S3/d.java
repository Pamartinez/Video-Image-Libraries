package S3;

import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateStory;
import com.samsung.android.gallery.app.controller.viewer.StartDirectorsViewDualEditCmd;
import com.samsung.android.gallery.app.receiver.BackupAndRestoreReceiver;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragViewHolderFinder;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.creature.FacePosRatioHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import com.samsung.android.gallery.module.debugger.LeakTracker;
import com.samsung.android.gallery.module.dynamicview.ClipInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicView;
import com.samsung.android.gallery.module.search.engine.MyQueryCreator;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.service.download.DownloadTask;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.tag.MyTagView;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2421a;

    public /* synthetic */ d(int i2) {
        this.f2421a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2421a) {
            case 0:
                return ((MediaItem) ((MediaItem) obj)).isImage();
            case 1:
                return ((MediaItem) ((MediaItem) obj)).isVideo();
            case 2:
                return ((MediaItem) ((MediaItem) obj)).isVideo();
            case 3:
                return ((MediaItem) ((MediaItem) obj)).isVideo();
            case 4:
                if (((Map.Entry) obj).getKey() != null) {
                    return true;
                }
                return false;
            case 5:
                return Objects.nonNull((FileItemInterface) obj);
            case 6:
                return RequestCreateStory.lambda$postDownloadContent$1((FileItemInterface) obj);
            case 7:
                return Objects.nonNull((ContentDownloadResult.DownloadedContent) obj);
            case 8:
                return MediaItemMde.getGroupId((FileItemInterface) obj).startsWith("UNM1");
            case 9:
                return AlbumsDragAndDropDelegate.lambda$getDragAlbums$6((MediaItem) obj);
            case 10:
                return AlbumsDragViewHolderFinder.lambda$findValidViewHolderInFolder$0((ListViewHolder) obj);
            case 11:
                return FacePosRatioHelper.lambda$preload$1((Long) obj);
            case 12:
                return MediaItemStory.isRecap((FileItemInterface) obj);
            case 13:
                return StartDirectorsViewDualEditCmd.lambda$hasCloudOnlyItem$4((MediaItem) obj);
            case 14:
                return StartDirectorsViewDualEditCmd.lambda$executeDownload$1((MediaItem) obj);
            case 15:
                return MyQueryCreator.lambda$filterCreatureEntity$2((FilterResultsEntity) obj);
            case 16:
                return MediaItemUtil.isSharableItem((MediaItem) obj, false);
            case 17:
                return ((LayoutInfo) obj).isVideo;
            case 18:
                return BackupAndRestoreReceiver.lambda$restoreGalleryPreferenceEntry$2((Blackboard) obj);
            case 19:
                return Objects.nonNull((Blackboard) obj);
            case 20:
                return LeakTracker.lambda$execMemInfo$0((String) obj);
            case 21:
                return ((ClusterResultsEntity) obj).isMediaType();
            case 22:
                return ClusterResultsEntry.lambda$hasOcrEntityOnly$0((ClusterResultsEntity) obj);
            case 23:
                return ((ClusterResultsEntity) obj).isOCRs();
            case 24:
                return ((DownloadTask) obj).isProgressing();
            case 25:
                return MyTagView.lambda$getTagList$3((MediaItem) obj);
            case 26:
                return ScreenShotFilterType.lambda$projection$4((ScreenShotFilterType) obj);
            case 27:
                return ScreenShotFilterType.lambda$projection$2((ScreenShotFilterType) obj);
            case 28:
                return ScreenShotFilterType.lambda$keySet$1((ScreenShotFilterType) obj);
            default:
                return DynamicView.lambda$getRawPlayList$0((ClipInfo) obj);
        }
    }
}
