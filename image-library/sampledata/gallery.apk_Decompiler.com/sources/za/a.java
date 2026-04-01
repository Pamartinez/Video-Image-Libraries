package Za;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MirroringDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import java.util.function.BiPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2911a;

    public /* synthetic */ a(int i2) {
        this.f2911a = i2;
    }

    public final boolean test(Object obj, Object obj2) {
        switch (this.f2911a) {
            case 0:
                return ((String) obj).contains((String) obj2);
            case 1:
                return ((String) obj).equals((String) obj2);
            case 2:
                return MirroringDelegate.lambda$new$0((MediaItem) obj, (String) obj2);
            case 3:
                return ThumbnailLoader.lambda$dump$9((String) obj, (Bitmap) obj2);
            case 4:
                return ThumbnailLoader.lambda$dump$10((String) obj, (Bitmap) obj2);
            default:
                return ThumbnailLoader.lambda$dump$11((String) obj, (Bitmap) obj2);
        }
    }
}
