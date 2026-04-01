package H3;

import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.album.SaveObjectCaptureToAlbumCmd;
import com.samsung.android.gallery.app.controller.externals.DocumentScanCmd;
import com.samsung.android.gallery.app.controller.internals.NondestructiveSaveRemasterCmd;
import com.samsung.android.gallery.app.service.MediaCaptureService;
import com.samsung.android.gallery.module.extendedformat.SefFormat;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2337a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2338c;

    public /* synthetic */ n(int i2, Object obj, Object obj2) {
        this.f2337a = i2;
        this.b = obj;
        this.f2338c = obj2;
    }

    public final void onScanCompleted(String str, Uri uri) {
        switch (this.f2337a) {
            case 0:
                ((SaveObjectCaptureToAlbumCmd) this.b).lambda$saveFile$1((String) this.f2338c, str, uri);
                return;
            case 1:
                DocumentScanCmd.DocumentScanSaveTask.lambda$saveFile$5((TimeTickLog) this.b, (BiConsumer) this.f2338c, str, uri);
                return;
            case 2:
                ((NondestructiveSaveRemasterCmd) this.b).lambda$moveAndScan$2((String) this.f2338c, str, uri);
                return;
            case 3:
                ((MediaCaptureService) this.b).lambda$scanAndNotifyResult$15((Blackboard) this.f2338c, str, uri);
                return;
            default:
                ((SefFormat) this.b).lambda$scanFile$0((CountDownLatch) this.f2338c, str, uri);
                return;
        }
    }
}
