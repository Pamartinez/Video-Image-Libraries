package vb;

import android.content.Context;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataCreatorDexImpl;
import java.util.function.Consumer;

/* renamed from: vb.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0712a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ClipDataCreatorDexImpl e;
    public final /* synthetic */ Context f;

    public /* synthetic */ C0712a(ClipDataCreatorDexImpl clipDataCreatorDexImpl, Context context, int i2) {
        this.d = i2;
        this.e = clipDataCreatorDexImpl;
        this.f = context;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$addIntentsForAlbums$1(this.f, (MediaItem) obj);
                return;
            default:
                this.e.lambda$addIntentsForItems$2(this.f, (MediaItem) obj);
                return;
        }
    }
}
