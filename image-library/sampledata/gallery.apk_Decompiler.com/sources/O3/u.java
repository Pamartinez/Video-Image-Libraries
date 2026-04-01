package O3;

import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.internals.SaveAsCopyCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateStory;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;
import com.samsung.android.gallery.plugins.filebrowser.FileListFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2416a;
    public final /* synthetic */ Object b;

    public /* synthetic */ u(int i2, Object obj) {
        this.f2416a = i2;
        this.b = obj;
    }

    public final void onScanCompleted(String str, Uri uri) {
        int i2 = this.f2416a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                ((SaveAsCopyCmd) obj).lambda$moveFile$1(str, uri);
                return;
            case 1:
                ((RequestCreateStory) obj).lambda$createStoryAfterScan$7(str, uri);
                return;
            case 2:
                ((SaveCaptureCmd) obj).lambda$scanFile$2(str, uri);
                return;
            default:
                ((FileListFragment) obj).lambda$onClick$19(str, uri);
                return;
        }
    }
}
