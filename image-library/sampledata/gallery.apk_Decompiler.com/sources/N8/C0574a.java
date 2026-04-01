package N8;

import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureFileHandler;
import com.samsung.android.gallery.module.data.MediaItem;

/* renamed from: N8.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0574a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ObjectCaptureFileHandler e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Bitmap g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Context f2847h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f2848i;

    public /* synthetic */ C0574a(int i2, Context context, Bitmap bitmap, ObjectCaptureFileHandler objectCaptureFileHandler, MediaItem mediaItem) {
        this.d = 1;
        this.e = objectCaptureFileHandler;
        this.f = mediaItem;
        this.g = bitmap;
        this.f2847h = context;
        this.f2848i = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$handleCopy$5(this.f2847h, this.f, this.g, this.f2848i);
                return;
            case 1:
                this.e.lambda$handleSaveAsImage$9(this.f, this.g, this.f2847h, this.f2848i);
                return;
            case 2:
                this.e.lambda$handleEdit$6(this.f2847h, this.f, this.g, this.f2848i);
                return;
            default:
                this.e.lambda$handleShare$11(this.f2847h, this.f, this.g, this.f2848i);
                return;
        }
    }

    public /* synthetic */ C0574a(ObjectCaptureFileHandler objectCaptureFileHandler, Context context, MediaItem mediaItem, Bitmap bitmap, int i2, int i7) {
        this.d = i7;
        this.e = objectCaptureFileHandler;
        this.f2847h = context;
        this.f = mediaItem;
        this.g = bitmap;
        this.f2848i = i2;
    }
}
