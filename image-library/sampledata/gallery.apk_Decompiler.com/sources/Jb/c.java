package Jb;

import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchRectMap;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2833a;
    public final /* synthetic */ StringBuilder b;

    public /* synthetic */ c(StringBuilder sb2, int i2) {
        this.f2833a = i2;
        this.b = sb2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2833a) {
            case 0:
                PinchRectMap.lambda$getLog$0(this.b, (Integer) obj, (ArrayList) obj2);
                return;
            default:
                LruCache.lambda$dump$0(this.b, obj, obj2);
                return;
        }
    }
}
