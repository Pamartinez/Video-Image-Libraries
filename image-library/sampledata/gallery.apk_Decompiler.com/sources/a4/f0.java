package A4;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.abstraction.YearThumbnailRequestHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f0 implements ThumbnailLoadedListener {
    public final /* synthetic */ YearThumbnailRequestHolder d;
    public final /* synthetic */ AtomicInteger e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f2246h;

    public /* synthetic */ f0(YearThumbnailRequestHolder yearThumbnailRequestHolder, AtomicInteger atomicInteger, MediaItem mediaItem, int i2, String str) {
        this.d = yearThumbnailRequestHolder;
        this.e = atomicInteger;
        this.f = mediaItem;
        this.g = i2;
        this.f2246h = str;
    }

    public final void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.lambda$loadYearThumbnailAsync$0(this.e, this.f, this.g, this.f2246h, bitmap, uniqueKey, thumbKind);
    }
}
