package E5;

import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageItemPicker;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.io.File;
import java.util.function.ToLongFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ToLongFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2319a;

    public /* synthetic */ b(int i2) {
        this.f2319a = i2;
    }

    public final long applyAsLong(Object obj) {
        switch (this.f2319a) {
            case 0:
                return ((HistoryItem) obj).getCoverId();
            case 1:
                return ((Long) obj).longValue();
            case 2:
                return ((FileItemInterface) obj).getFileId();
            case 3:
                return ((File) obj).lastModified();
            case 4:
                return ((MediaItem) ((MediaItem) obj)).getDateTaken();
            case 5:
                return DbCompat.storyApi().getCmhFileId(((MediaItem) obj).getFileId());
            case 6:
                return IdentityCreatureUtil.getUnifiedIdentityId((String) obj);
            case 7:
                return Long.parseLong((String) obj);
            case 8:
                return MediaItemStory.getStoryStartTime((FileItemInterface) obj);
            case 9:
                return ((ThumbnailInterface) obj).getDateTaken();
            case 10:
                return ((MediaItem) ((MediaItem) obj)).getFileId();
            case 11:
                return ((CollageItemPicker.Content) obj).id;
            case 12:
                return ((Long) obj).longValue();
            case 13:
                return ((FileItemInterface) obj).getFileSize();
            case 14:
                return ((File) obj).length();
            case 15:
                return ((MediaItem) ((MediaItem) obj)).getDateTaken();
            default:
                return ((MediaItem) ((MediaItem) obj)).getDateTaken();
        }
    }
}
