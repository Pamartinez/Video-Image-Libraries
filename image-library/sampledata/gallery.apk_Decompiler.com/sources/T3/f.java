package T3;

import android.graphics.Bitmap;
import android.util.Pair;
import com.samsung.android.gallery.app.controller.sharing.request.RequestEmptyFromTrash;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurInterface;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2437a;
    public final /* synthetic */ long b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2438c;
    public final /* synthetic */ Object d;

    public /* synthetic */ f(Object obj, Object obj2, long j2, int i2) {
        this.f2437a = i2;
        this.f2438c = obj;
        this.d = obj2;
        this.b = j2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2437a) {
            case 0:
                ((RequestEmptyFromTrash) this.f2438c).lambda$request$0((Pair) this.d, this.b, (List) obj, (Integer) obj2);
                return;
            default:
                BlurUtil.blurAndBindFilteredBitmap((BlurInterface) this.f2438c, (MediaItem) this.d, (Bitmap) obj, this.b);
                return;
        }
    }
}
