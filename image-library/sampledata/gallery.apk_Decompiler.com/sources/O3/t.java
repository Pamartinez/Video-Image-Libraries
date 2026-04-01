package O3;

import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.internals.RevertOriginalImageCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveDualPhotoCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2414a;
    public final /* synthetic */ long b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ BaseCommand f2415c;
    public final /* synthetic */ Object d;

    public /* synthetic */ t(BaseCommand baseCommand, long j2, Object obj, int i2) {
        this.f2414a = i2;
        this.f2415c = baseCommand;
        this.b = j2;
        this.d = obj;
    }

    public final void onScanCompleted(String str, Uri uri) {
        switch (this.f2414a) {
            case 0:
                String str2 = str;
                Uri uri2 = uri;
                ((RevertOriginalImageCmd) this.f2415c).lambda$executeRevertJob$2(this.b, (MediaItem) this.d, str2, uri2);
                return;
            default:
                ((SaveDualPhotoCmd) this.f2415c).lambda$saveFile$2(this.b, (Consumer) this.d, str, uri);
                return;
        }
    }
}
