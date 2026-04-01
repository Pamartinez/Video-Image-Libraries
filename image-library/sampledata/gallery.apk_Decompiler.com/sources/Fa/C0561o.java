package Fa;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterCustomDataManager;
import com.samsung.android.gallery.settings.ui.LabsConfigFragment;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import java.util.Map;
import java.util.function.Predicate;

/* renamed from: Fa.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0561o implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2811a;
    public final /* synthetic */ Map b;

    public /* synthetic */ C0561o(int i2, Map map) {
        this.f2811a = i2;
        this.b = map;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2811a;
        Map map = this.b;
        switch (i2) {
            case 0:
                return map.containsKey(((BooleanFeatures) obj).getKey());
            case 1:
                return LabsConfigFragment.lambda$clearPreference$2(map, (BooleanFeatures) obj);
            default:
                return ScreenShotFilterCustomDataManager.lambda$reorder$1(map, (MediaItem) obj);
        }
    }
}
