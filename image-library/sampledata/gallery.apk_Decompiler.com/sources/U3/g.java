package U3;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.story.StorySaveCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.effectfilter.ImageFilterApplier;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2447h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2448i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Object f2449j;

    public /* synthetic */ g(StorySaveCmd storySaveCmd, boolean z, MediaItem mediaItem, List list, int i2, String str) {
        this.g = storySaveCmd;
        this.f = z;
        this.f2447h = mediaItem;
        this.f2448i = list;
        this.e = i2;
        this.f2449j = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((StorySaveCmd) this.g).lambda$onExecute$0(this.f, (MediaItem) this.f2447h, (List) this.f2448i, this.e, (String) this.f2449j);
                return;
            default:
                ((ImageFilterApplier) this.g).lambda$apply$0((Bitmap) this.f2447h, (Filter) this.f2448i, this.e, this.f, (BiConsumer) this.f2449j);
                return;
        }
    }

    public /* synthetic */ g(ImageFilterApplier imageFilterApplier, Bitmap bitmap, Filter filter, int i2, boolean z, BiConsumer biConsumer) {
        this.g = imageFilterApplier;
        this.f2447h = bitmap;
        this.f2448i = filter;
        this.e = i2;
        this.f = z;
        this.f2449j = biConsumer;
    }
}
