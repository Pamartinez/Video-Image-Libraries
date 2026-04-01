package q8;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.dialog.switchable.LayoutType;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesMultipleHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.database.dal.mp.impl.CategoriesImpl;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataCreatorImpl;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2700a;

    public /* synthetic */ a(int i2) {
        this.f2700a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2700a) {
            case 0:
                return C0212a.l("\n", (String) obj);
            case 1:
                return YearThumbnailLoader.lambda$getPieceBitmapCache$0((String) obj);
            case 2:
                return QueryBuilder.escapeString(((FilterResultsEntity) obj).getName());
            case 3:
                return TagEditApi.lambda$queryTagListFromIds$0((Long) obj);
            case 4:
                return FileUtils.getVolumeName(((ContentValues) obj).getAsString("_data"));
            case 5:
                return MediaItemBuilder.cloneCreatureItem((MediaItem) obj);
            case 6:
                return CategoriesImpl.lambda$getScreenShotCursor$11((String) obj);
            case 7:
                return CategoriesImpl.lambda$getScreenShotCursor$13((String) obj);
            case 8:
                return ((FileItemInterface) obj).getSubCategory();
            case 9:
                return TrashDeleteHelper.lambda$reloadCompletedMediaItem$4((MediaItem) obj);
            case 10:
                return ((File) obj).listFiles();
            case 11:
                return Arrays.stream((File[]) obj);
            case 12:
                return ((MediaItem) ((MediaItem) obj)).getSubCategory();
            case 13:
                return C0212a.m("'", (String) obj, "'");
            case 14:
                return Integer.valueOf(((byte[]) obj).hashCode());
            case 15:
                return SearchPicturesMultipleHeaderContainer.lambda$isFilterHeaderVisible$0((ViewGroup) obj);
            case 16:
                return Boolean.valueOf(((SearchHeaderView) obj).onBackPressed());
            case 17:
                return ((String) obj).toLowerCase(Locale.getDefault());
            case 18:
                return ((MediaItem) ((MediaItem) obj)).getMimeType();
            case 19:
                return ClipDataCreatorImpl.lambda$get$2((MediaItem) obj);
            case 20:
                return ((ClusterResult) obj).getListView();
            case 21:
                return QueryBuilder.escapeString((String) obj);
            case 22:
                return QueryBuilder.escapeString((String) obj);
            case 23:
                return Integer.valueOf(((Bitmap) obj).getHeight() * ((Bitmap) obj).getWidth());
            case 24:
                return Long.valueOf(Long.parseLong(((String) obj).trim()));
            case 25:
                return Boolean.valueOf(ViewUtils.isTranslucent(((ViewPagerHolder) obj).getTransformParentLayout()));
            case 26:
                return (Bitmap) ((WeakReference) obj).get();
            case 27:
                return Long.valueOf(MotionPhotoUtils.getXmpTimeStamp(((MediaItem) obj).getPath()));
            case 28:
                return LayoutType.createLayoutType(((Integer) obj).intValue());
            default:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getDateModified());
        }
    }
}
