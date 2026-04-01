package U9;

import android.util.Pair;
import com.samsung.android.gallery.app.ui.dialog.CreateSharedAlbumDialog;
import com.samsung.android.gallery.app.ui.map.search.SearchMarkerManagerDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.sum.core.filter.MediaParserFilter;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ToIntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2883a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(int i2, Object obj) {
        this.f2883a = i2;
        this.b = obj;
    }

    public final int applyAsInt(Object obj) {
        int i2 = this.f2883a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((Integer) ((Pair) ((Map) obj2).getOrDefault(((MediaItem) obj).getSubCategory(), new Pair(Boolean.TRUE, Integer.MAX_VALUE))).second).intValue();
            case 1:
                return ((SearchMarkerManagerDelegate) obj2).lambda$getClusterCount$2((MapItem) obj);
            case 2:
                return MediaParserFilter.lambda$run$3((List) obj2, (Pair) obj);
            default:
                return CreateSharedAlbumDialog.lambda$getDefaultName$1((String) obj2, (MediaItem) obj);
        }
    }
}
