package D7;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.MotionPhotoImageLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2303a;
    public final /* synthetic */ MotionPhotoImageLoader b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaItem f2304c;

    public /* synthetic */ h(MotionPhotoImageLoader motionPhotoImageLoader, MediaItem mediaItem, int i2) {
        this.f2303a = i2;
        this.b = motionPhotoImageLoader;
        this.f2304c = mediaItem;
    }

    public final void accept(Object obj, Object obj2) {
        Integer num = (Integer) obj;
        Bitmap bitmap = (Bitmap) obj2;
        switch (this.f2303a) {
            case 0:
                this.b.lambda$onViewAttached$3(this.f2304c, num, bitmap);
                return;
            default:
                this.b.lambda$onBind$1(this.f2304c, num, bitmap);
                return;
        }
    }
}
