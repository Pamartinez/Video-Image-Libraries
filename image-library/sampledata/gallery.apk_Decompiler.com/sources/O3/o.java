package O3;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.quicksearch.QuickSearchManager;
import com.samsung.android.gallery.module.receiver.GlobalActionReceiver;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterManager;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2413a;

    public /* synthetic */ o(int i2) {
        this.f2413a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2413a) {
            case 0:
                return ExifUtils.fixDate((String) obj);
            case 1:
                return String.valueOf(((MediaItem) obj).getFileId());
            case 2:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 3:
                return Boolean.valueOf(((MediaItem) obj).isCloudOnly());
            case 4:
                return ((String) obj).toLowerCase();
            case 5:
                return QuickSearchManager.lambda$getInstance$0((String) obj);
            case 6:
                return Integer.valueOf(((Byte) obj).intValue());
            case 7:
                return ((Bundle) obj).getString("locationKey");
            case 8:
                return ((MediaItem) ((MediaItem) obj)).getMimeType();
            case 9:
                return Clipboard.a((String) obj);
            case 10:
                return ((CharSequence) obj).toString();
            case 11:
                return GlobalActionReceiver.lambda$processAdjustPopOverOptions$17((Parcelable) obj);
            case 12:
                return new File((String) obj);
            case 13:
                return ((File) obj).getPath();
            case 14:
                return (List) ((Map.Entry) obj).getValue();
            case 15:
                return MediaItemMde.getSpaceId((FileItemInterface) obj);
            case 16:
                return String.valueOf(((MediaItem) obj).getFileId());
            case 17:
                return ((MvpBaseFragment) obj).getToolbar();
            case 18:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 19:
                return Settings.Secure.getString(((Context) obj).getContentResolver(), "android_id");
            case 20:
                return Integer.valueOf(MediaItemStory.getStoryId((FileItemInterface) obj));
            case 21:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 22:
                return CreatureData.of((MediaItem) obj).faceGroupUuid;
            case 23:
                return ((StorageInfo) obj).gif.replaceFirst(((StorageInfo) obj).root, "");
            case 24:
                return ((StorageInfo) obj).videoCollage.replaceFirst(((StorageInfo) obj).root, "");
            case 25:
                return Integer.valueOf(MediaItemStory.getStoryId((MediaItem) obj));
            case 26:
                return Integer.valueOf(((ListViewHolder) obj).getItemViewType());
            case 27:
                return ScreenShotFilterManager.lambda$getInstance$0((String) obj);
            case 28:
                return ((ViewGroup) obj).getContext();
            default:
                return Integer.valueOf(MediaItemStory.getStoryId(((PreviewViewHolder) obj).getMediaItem()));
        }
    }
}
