package qa;

import android.content.ClipData;
import android.content.pm.ApplicationInfo;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MyTagHandler;
import com.samsung.android.gallery.database.dal.mp.table.MpAnalyzeInfoTable;
import com.samsung.android.gallery.database.dal.mp.table.MpTagView;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.recap.data.RecapImageCandidate;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader;
import com.samsung.android.gallery.module.trash.helper.TrashMpRecoverTask;
import com.samsung.android.gallery.widget.dialog.MissingPackage;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataCreatorDexImpl;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadowCreator;
import java.io.File;
import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3288a;

    public /* synthetic */ e(int i2) {
        this.f3288a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f3288a) {
            case 0:
                return YearThumbnailLoader.lambda$getCacheSize$1((File) obj);
            case 1:
                return ((FilterResultsEntity) obj).isStory();
            case 2:
                return CreatureData.hasName((MediaItem) obj);
            case 3:
                return Objects.nonNull((File) obj);
            case 4:
                return TrashMpRecoverTask.lambda$getTrashFileList$3((String) obj);
            case 5:
                return ((String) obj).endsWith(".nomedia");
            case 6:
                return Objects.nonNull((File[]) obj);
            case 7:
                return TrashMpRecoverTask.lambda$getTrashFileList$2((File) obj);
            case 8:
                return MissingPackage.supportPackageDisabling((ApplicationInfo) obj);
            case 9:
                return MpAnalyzeInfoTable.lambda$unpack$0((String) obj);
            case 10:
                return MpTagView.lambda$appendEnglishKeyword$0((String) obj);
            case 11:
                return ((MediaItem) ((MediaItem) obj)).isImage();
            case 12:
                return ((String) obj).contains("GalleryActivity");
            case 13:
                return MyTagHandler.lambda$onTagDeleteClickListener$3((String) obj);
            case 14:
                return ((RecapImageCandidate) obj).isVertical;
            case 15:
                return Objects.nonNull((ClipData.Item) obj);
            case 16:
                return ClipDataCreatorDexImpl.lambda$isValidIntentClipData$3((ClipData.Item) obj);
            case 17:
                return ClipDataManager.lambda$getStartCount$0((ClipData.Item) obj);
            case 18:
                return ((ClusterResult) obj).supportItemSelection();
            case 19:
                return ((ClusterResult) obj).isItemClicked();
            case 20:
                return DragShadowCreator.lambda$getUpdatedBitmap$2((ClipData.Item) obj);
            default:
                return ((MediaItem) ((MediaItem) obj)).isImage();
        }
    }
}
