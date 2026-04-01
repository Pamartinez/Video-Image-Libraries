package A8;

import com.samsung.android.gallery.app.controller.abstraction.WallpaperPackage;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AbsRemasterAiEdit;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.ClipInfo;
import com.samsung.android.gallery.module.knox.KnoxAlbumOperator;
import com.samsung.android.gallery.module.search.root.FilterResultsGroupType;
import com.samsung.android.gallery.plugins.portrait.VideoWallpaperPackage;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listview.pinch.v3.IFocused;
import com.samsung.android.gallery.widget.listview.pinch.v3.RelativeRange;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ToIntFunction;

/* renamed from: A8.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0545b implements ToIntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2765a;

    public /* synthetic */ C0545b(int i2) {
        this.f2765a = i2;
    }

    public final int applyAsInt(Object obj) {
        switch (this.f2765a) {
            case 0:
                return ((Integer) ((Map.Entry) obj).getValue()).intValue();
            case 1:
                return ((PinchItem) obj).getFromViewPosition();
            case 2:
                return ((MediaItem) obj).getAlbumID();
            case 3:
                return Integer.parseInt((String) obj);
            case 4:
                return ((Long) obj).intValue();
            case 5:
                return ((MediaType) obj).toInt();
            case 6:
                return ((VideoWallpaperPackage) obj).getOrder();
            case 7:
                return ((Integer) obj).intValue();
            case 8:
                return ((Integer) obj).intValue();
            case 9:
                return ((Integer) obj).intValue();
            case 10:
                return ((IFocused) obj).getGridSize();
            case 11:
                return RelativeRange.lambda$updateColumns$0((int[]) obj);
            case 12:
                return RelativeRange.lambda$updateColumns$1((int[]) obj);
            case 13:
                return ((WallpaperPackage) obj).getOrder();
            case 14:
                return ((ArrayList) obj).size();
            case 15:
                return ((Integer) obj).intValue();
            case 16:
                return ((Integer) obj).intValue();
            case 17:
                return ((Integer) obj).intValue();
            case 18:
                return ((MediaItem) obj).getCount();
            case 19:
                return ((FilterResultsGroupType) obj).ordinal();
            case 20:
                return ((Integer) obj).intValue();
            case 21:
                return ((ClipInfo) obj).clipStartMs;
            case 22:
                return ((MotionPhotoVideoUtils.SEFInfo) ((Map.Entry) obj).getKey()).sefDataType.intValue();
            case 23:
                return ((ByteBuffer) obj).limit();
            case 24:
                return ((AtomicInteger) obj).get();
            case 25:
                return ((Integer) obj).intValue();
            case 26:
                return ((AbsRemasterAiEdit) obj).getDetectionType();
            case 27:
                return ((Integer) obj).intValue();
            case 28:
                return ((Integer) obj).intValue();
            default:
                return KnoxAlbumOperator.lambda$fetchMediaItemsFromAlbums$0((int[]) obj);
        }
    }
}
