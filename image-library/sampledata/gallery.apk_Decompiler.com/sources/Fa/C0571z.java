package Fa;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import com.samsung.android.gallery.app.controller.abstraction.ViewerDownloadTask;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.app.controller.album.ReorderAlbumCmd;
import com.samsung.android.gallery.app.controller.album.UpdateOrderCmd;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmItem;
import com.samsung.android.gallery.module.c2pa.C2paExif;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItem;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItemBuilder;
import java.util.Objects;
import java.util.function.Predicate;

/* renamed from: Fa.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0571z implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2817a;

    public /* synthetic */ C0571z(int i2) {
        this.f2817a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2817a) {
            case 0:
                return LabsDevManageFragment.lambda$addCategoryPreferenceManager$7((String) obj);
            case 1:
                return ((String) obj).contains("#last");
            case 2:
                return LabsFragment.lambda$onHeapDumpClicked$24((String) obj);
            case 3:
                return ViewerDownloadTask.lambda$loadAndCheckResult$2((MediaItem) obj);
            case 4:
                return Objects.nonNull((MediaItem) obj);
            case 5:
                return ((MediaItem) obj).isCloudOnly();
            case 6:
                return ((MediaItem) obj).isAutoAlbum();
            case 7:
                return Objects.isNull((MediaItem) obj);
            case 8:
                return CreateFolderCmd.lambda$containsDefaultOrder$1((MediaItem) obj);
            case 9:
                return CreateFolderCmd.lambda$getAllItems$6((MediaItem) obj);
            case 10:
                return ((MediaItem) ((MediaItem) obj)).isVideo();
            case 11:
                return ((MediaItem) ((MediaItem) obj)).isImage();
            case 12:
                return ((MediaItem) obj).isFolder();
            case 13:
                return ReorderAlbumCmd.lambda$containsDefaultOrder$1((MediaItem) obj);
            case 14:
                return UpdateOrderCmd.lambda$getCandidateFolder$3((MediaItem) obj);
            case 15:
                return UpdateOrderCmd.lambda$getAlbumsData$1((MediaItem) obj);
            case 16:
                return ((MediaItem) obj).isAlbumLvFirst();
            case 17:
                return MergeCreatureMultipleCmd.lambda$onExecute$1((MediaItem) obj);
            case 18:
                return ((MediaItem) obj).isPeople();
            case 19:
                return ((MediaItem) obj).isPet();
            case 20:
                return ((BgmItem) obj).isDownloaded();
            case 21:
                return Objects.isNull((PropertyAnimator) obj);
            case 22:
                return PinchItemBuilder.lambda$createDividerItems$4((PinchItem) obj);
            case 23:
                return ((PinchItem) obj).isHeader();
            case 24:
                return C2paExif.lambda$getInfo$0((String) obj);
            case 25:
                return C2paExif.lambda$getInfo$1((String) obj);
            case 26:
                return Objects.nonNull((Context) obj);
            case 27:
                return Blackboard.lambda$getContext$3((Context) obj);
            case 28:
                return ((AppCompatActivity) ((Context) obj)).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
            default:
                return ((MediaItem) ((MediaItem) obj)).isImage();
        }
    }
}
