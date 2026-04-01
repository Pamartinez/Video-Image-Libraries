package g6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler;
import java.util.function.Consumer;

/* renamed from: g6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0457a implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ ExportHandler e;
    public final /* synthetic */ String f;
    public final /* synthetic */ Consumer g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2639h;

    public /* synthetic */ C0457a(ExportHandler exportHandler, String str, int i2, Consumer consumer) {
        this.e = exportHandler;
        this.f = str;
        this.f2639h = i2;
        this.g = consumer;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$findExportedItem$7(this.f, this.f2639h, this.g);
                return;
            default:
                Consumer consumer = this.g;
                this.e.lambda$findExportedItem$8(this.f, consumer, this.f2639h);
                return;
        }
    }

    public /* synthetic */ C0457a(ExportHandler exportHandler, String str, Consumer consumer, int i2) {
        this.e = exportHandler;
        this.f = str;
        this.g = consumer;
        this.f2639h = i2;
    }
}
