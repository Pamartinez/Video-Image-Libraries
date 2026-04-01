package b4;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.samsung.android.gallery.app.remote.v2.GalleryHighResPresentation;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataStoriesBase;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: b4.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0422b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2487h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2488i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Object f2489j;

    public /* synthetic */ C0422b(IrregularView irregularView, Drawable drawable, int i2, Bitmap bitmap, Rect rect, ImageView imageView) {
        this.d = 2;
        this.f = irregularView;
        this.g = drawable;
        this.e = i2;
        this.f2487h = bitmap;
        this.f2488i = rect;
        this.f2489j = imageView;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((GalleryHighResPresentation) this.f).lambda$updatePhotoView$0((MediaItem) this.g, (Bitmap) this.f2487h, (Bitmap) this.f2488i, (PhotoViewMotionControl) this.f2489j, this.e);
                return;
            case 1:
                ((MediaDataStoriesBase) this.f).lambda$swap$3((Cursor[]) this.g, (Cursor[]) this.f2487h, (ConcurrentHashMap) this.f2488i, (HashMap) this.f2489j, this.e);
                return;
            default:
                ((IrregularView) this.f).lambda$bindIrregularShapeBitmap$5((Drawable) this.g, this.e, (Bitmap) this.f2487h, (Rect) this.f2488i, (ImageView) this.f2489j);
                return;
        }
    }

    public /* synthetic */ C0422b(AutoCloseable autoCloseable, Cloneable cloneable, Object obj, Object obj2, Object obj3, int i2, int i7) {
        this.d = i7;
        this.f = autoCloseable;
        this.g = cloneable;
        this.f2487h = obj;
        this.f2488i = obj2;
        this.f2489j = obj3;
        this.e = i2;
    }
}
