package O3;

import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.internals.SavePortraitEffectCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SavePortraitEffectCmd f2417a;
    public final /* synthetic */ boolean b;

    public /* synthetic */ w(SavePortraitEffectCmd savePortraitEffectCmd, boolean z) {
        this.f2417a = savePortraitEffectCmd;
        this.b = z;
    }

    public final void onScanCompleted(String str, Uri uri) {
        this.f2417a.lambda$handleSaveAsCopy$3(this.b, str, uri);
    }
}
