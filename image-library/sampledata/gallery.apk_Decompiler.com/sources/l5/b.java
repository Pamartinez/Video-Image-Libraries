package L5;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterView;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsViewAdapter;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.module.cache.BitmapCacheMgr;
import com.samsung.android.gallery.module.camera.PostProcessingHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.cache.CacheHelper;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2381a;

    public /* synthetic */ b(int i2) {
        this.f2381a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2381a) {
            case 0:
                return Integer.valueOf(((SharingsViewAdapter) obj).getInvitationDataCount());
            case 1:
                return BitmapCacheMgr.lambda$dumpIf$1((Map.Entry) obj);
            case 2:
                return AlbumPicturesFragment.lambda$updateFloatingToolbarLayout$44((GalleryAppBarLayout) obj);
            case 3:
                return ((MediaItem) obj).getChildAlbums().get(0);
            case 4:
                return AlbumPicturesPresenter.lambda$isToolbarTitleVisible$24((GalleryAppBarLayout) obj);
            case 5:
                return String.valueOf((Long) obj);
            case 6:
                return PostProcessingHelper.lambda$pppCompleted$1((MediaItem) obj);
            case 7:
                return Boolean.valueOf(Objects.nonNull((MediaItem) obj));
            case 8:
                return PostProcessingHelper.lambda$updateCompletedPpp$4((MediaItem) obj);
            case 9:
                return Long.valueOf(Long.parseLong((String) obj));
            case 10:
                return ((String) obj).trim();
            case 11:
                return Long.valueOf(Long.parseLong((String) obj));
            case 12:
                return QueryBuilder.escapeString((String) obj);
            case 13:
                return CacheManager.lambda$generateKey$1((String) obj);
            case 14:
                return ((CacheHelper) obj).listFiles();
            case 15:
                return ((CreateNewDialogCmd.CreateType) obj).getValue();
            case 16:
                return Integer.valueOf(((ArrayList) obj).size());
            case 17:
                return Integer.valueOf(((MediaItem) obj).getCount());
            case 18:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 19:
                return Boolean.valueOf(((ScreenShotFilterView) obj).isListViewScrolling());
            case 20:
                return ((Bundle) obj).getString("locationKey");
            case 21:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 22:
                return Integer.valueOf(AppResources.getAppContext().getResources().getIdentifier((String) obj, "dimen", "android"));
            case 23:
                return ((InputMethodManager) obj).getCurrentInputMethodSubtype();
            case 24:
                return ((InputMethodSubtype) obj).getLanguageTag();
            case 25:
                return ((List) obj).stream();
            case 26:
                return ((MediaItem) ((MediaItem) obj)).getTitle();
            case 27:
                return ((MediaItem) ((MediaItem) obj)).getTitle();
            case 28:
                return ((String) obj).toLowerCase(Locale.getDefault());
            default:
                return ExifUtils.fixDateFromFilename((String) obj);
        }
    }
}
