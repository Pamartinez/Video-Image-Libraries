package i4;

import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;

/* renamed from: i4.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0469b implements View.OnLayoutChangeListener {
    public final /* synthetic */ ClipboardViewAdapter d;
    public final /* synthetic */ ImageView e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ C0469b(ClipboardViewAdapter clipboardViewAdapter, ImageView imageView, MediaItem mediaItem) {
        this.d = clipboardViewAdapter;
        this.e = imageView;
        this.f = mediaItem;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        this.d.lambda$setOnLayoutChangeListener$5(this.e, this.f, view, i2, i7, i8, i10, i11, i12, i13, i14);
    }
}
