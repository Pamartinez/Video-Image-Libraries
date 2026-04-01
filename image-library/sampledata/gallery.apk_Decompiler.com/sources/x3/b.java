package X3;

import android.content.Context;
import com.samsung.android.gallery.app.provider.SharedItemUploader;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ SharedItemUploader e;
    public final /* synthetic */ Context f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f2466h;

    public /* synthetic */ b(SharedItemUploader sharedItemUploader, Context context, String str, String str2) {
        this.e = sharedItemUploader;
        this.f = context;
        this.g = str;
        this.f2466h = str2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$run$2(this.g, this.f2466h, this.f, (MediaItem) obj);
                return;
            default:
                this.e.lambda$run$1(this.f, this.g, this.f2466h, (Integer) obj);
                return;
        }
    }

    public /* synthetic */ b(SharedItemUploader sharedItemUploader, String str, String str2, Context context) {
        this.e = sharedItemUploader;
        this.g = str;
        this.f2466h = str2;
        this.f = context;
    }
}
