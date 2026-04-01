package X3;

import android.app.slice.Slice;
import android.os.Handler;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.service.MediaCaptureService;
import com.samsung.android.gallery.app.service.RemasterService;
import com.samsung.android.gallery.database.dal.mp.table.MpTagView;
import com.samsung.android.gallery.module.abstraction.VisualSearchCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remaster.RemasterHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* renamed from: X3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0413a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0413a(RemasterService remasterService, boolean z, RemasterHelper.Result result) {
        this.d = 2;
        this.f = remasterService;
        this.e = z;
        this.g = result;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((ShareProvider) this.f).lambda$onBindSlice$1((Slice.Builder) this.g, this.e, (MediaItem) obj);
                return;
            case 1:
                ((MediaCaptureService) this.f).lambda$prepareMediaCaptureWorker$0((MediaItem) this.g, this.e, (Boolean) obj);
                return;
            case 2:
                ((RemasterService) this.f).lambda$onCompleted$1(this.e, (RemasterHelper.Result) this.g, (Handler) obj);
                return;
            case 3:
                VisualSearchCategory.lambda$iterate$3(this.e, (BiConsumer) this.f, (AtomicInteger) this.g, (String) obj);
                return;
            default:
                ((MpTagView) this.f).lambda$appendEnglishKeyword$1((StringBuilder) this.g, this.e, (String) obj);
                return;
        }
    }

    public /* synthetic */ C0413a(Object obj, Object obj2, boolean z, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = z;
    }

    public /* synthetic */ C0413a(boolean z, BiConsumer biConsumer, AtomicInteger atomicInteger) {
        this.d = 3;
        this.e = z;
        this.f = biConsumer;
        this.g = atomicInteger;
    }
}
