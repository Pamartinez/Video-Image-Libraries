package F9;

import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloudSdk;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2801a;

    public /* synthetic */ a(int i2) {
        this.f2801a = i2;
    }

    public final void onScanCompleted(String str, Uri uri) {
        switch (this.f2801a) {
            case 0:
                Log.d("DualPhotoManipulator2", "restore scan completed", str, uri);
                return;
            default:
                SamsungCloudSdk.scanCompleted(str, uri);
                return;
        }
    }
}
