package D3;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.item.FloatingItemDelegate;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IPinView;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.analyticsquery.SasCount;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.module.media.InstantSlowMoPlayLogger;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import java.io.File;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2297a;

    public /* synthetic */ i(int i2) {
        this.f2297a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2297a) {
            case 0:
                return Boolean.valueOf("com.sec.android.gallery3d.app.GalleryActivity".equals((String) obj));
            case 1:
                return Boolean.valueOf(((FloatingItemDelegate) obj).isShortcutVisible());
            case 2:
                return SasCount.lambda$toString$19((Map.Entry) obj);
            case 3:
                return SasCount.lambda$summaryOfSaS$12((Map.Entry) obj);
            case 4:
                return SasCount.lambda$summaryHtmlOfSaS$16((Map.Entry) obj);
            case 5:
                return SasCount.lambda$summaryHtmlOfSaS$17((Map.Entry) obj);
            case 6:
                return SasCount.lambda$logSearchAnalysisStatus$7((Map.Entry) obj);
            case 7:
                return SasCount.lambda$logPackageStatus$8((Map.Entry) obj);
            case 8:
                return ((FileItemInterface) obj).getMediaType();
            case 9:
                return new MdeDatabase().getUserId((String) obj);
            case 10:
                return MediaItemMde.getItemId((FileItemInterface) obj);
            case 11:
                return ((Activity) obj).getReferrer();
            case 12:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 13:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getMediaId());
            case 14:
                return ((Uri) obj).toString();
            case 15:
                return AlbumType.get(((Integer) obj).intValue());
            case 16:
                return ((StorageVolumeCompat) obj).name;
            case 17:
                return ((String) obj).replaceFirst("key_", "");
            case 18:
                return Integer.valueOf(((IPinView) obj).getDataCount());
            case 19:
                return InstantSlowMoPlayLogger.lambda$getHolder$0((Long) obj);
            case 20:
                return ((AppCompatActivity) obj).getSupportActionBar();
            case 21:
                return ((Context) obj).getApplicationContext();
            case 22:
                return ((BooleanFeatures) obj).getKey();
            case 23:
                return ((PreferenceName) obj).key();
            case 24:
                return ((File) obj).getName();
            case 25:
                return Boolean.valueOf(((Preference) obj).isVisible());
            case 26:
                return Integer.valueOf(((GalleryListAdapter) obj).getStartDepth());
            case 27:
                return ((MediaItem) obj).getFolderName();
            case 28:
                return ((MediaItem) obj).getTitle();
            default:
                return ((GalleryListAdapter) obj).getAdvancedMouseFocusManager();
        }
    }
}
