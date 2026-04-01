package b4;

import android.graphics.Bitmap;
import android.graphics.Point;
import com.samsung.android.gallery.app.remote.v2.GalleryHighResPresentation;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;

/* renamed from: b4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0421a implements Runnable {
    public final /* synthetic */ GalleryHighResPresentation d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Point g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Bitmap f2484h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ PhotoViewMotionControl f2485i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ int f2486j;

    public /* synthetic */ C0421a(GalleryHighResPresentation galleryHighResPresentation, MediaItem mediaItem, int i2, Point point, Bitmap bitmap, PhotoViewMotionControl photoViewMotionControl, int i7) {
        this.d = galleryHighResPresentation;
        this.e = mediaItem;
        this.f = i2;
        this.g = point;
        this.f2484h = bitmap;
        this.f2485i = photoViewMotionControl;
        this.f2486j = i7;
    }

    public final void run() {
        this.d.lambda$updatePhotoView$2(this.e, this.f, this.g, this.f2484h, this.f2485i, this.f2486j);
    }
}
