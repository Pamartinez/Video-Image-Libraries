package D7;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.remote.v2.GalleryHighResPresentation;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.ThemeUpdater;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.AbsImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.ImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.PreviewLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.VideoThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ ThumbnailInterface f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2299h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2300i;

    public /* synthetic */ a(GalleryHighResPresentation galleryHighResPresentation, MediaItem mediaItem, Bitmap bitmap, PhotoViewMotionControl photoViewMotionControl, int i2) {
        this.d = 3;
        this.g = galleryHighResPresentation;
        this.f = mediaItem;
        this.f2299h = bitmap;
        this.f2300i = photoViewMotionControl;
        this.e = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((AbsImageLoader) this.g).lambda$requestDecode$1((Blackboard) this.f2299h, (String) this.f2300i, (MediaItem) this.f, this.e);
                return;
            case 1:
                ((ImageLoader) this.g).lambda$updateFullSizeBitmap$2((Bitmap) this.f2299h, this.e, (TimeTickLog) this.f2300i, (MediaItem) this.f);
                return;
            case 2:
                ((PreviewLoader) this.g).lambda$loadThumbnail$2((AtomicBoolean) this.f2299h, (Bitmap) this.f2300i, (MediaItem) this.f, this.e);
                return;
            case 3:
                ((GalleryHighResPresentation) this.g).lambda$updatePhotoView$1((MediaItem) this.f, (Bitmap) this.f2299h, (PhotoViewMotionControl) this.f2300i, this.e);
                return;
            case 4:
                ((VideoThumbnailLoader) this.g).lambda$load$1((List) this.f2299h, (byte[]) this.f2300i, this.f, this.e);
                return;
            default:
                ThemeUpdater.lambda$updateThemeToDb$0((MediaItem) this.f, (String) this.f2300i, (String) this.g, (String) this.f2299h, this.e);
                return;
        }
    }

    public /* synthetic */ a(ImageLoader imageLoader, Bitmap bitmap, int i2, TimeTickLog timeTickLog, MediaItem mediaItem) {
        this.d = 1;
        this.g = imageLoader;
        this.f2299h = bitmap;
        this.e = i2;
        this.f2300i = timeTickLog;
        this.f = mediaItem;
    }

    public /* synthetic */ a(MediaItem mediaItem, String str, String str2, String str3, int i2) {
        this.d = 5;
        this.f = mediaItem;
        this.f2300i = str;
        this.g = str2;
        this.f2299h = str3;
        this.e = i2;
    }

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, ThumbnailInterface thumbnailInterface, int i2, int i7) {
        this.d = i7;
        this.g = obj;
        this.f2299h = obj2;
        this.f2300i = obj3;
        this.f = thumbnailInterface;
        this.e = i2;
    }
}
