package U3;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.story.SaveNotifiedContentCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f2444a;
    public final /* synthetic */ MediaItem b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ArrayList f2445c;

    public /* synthetic */ d(Context context, MediaItem mediaItem, ArrayList arrayList) {
        this.f2444a = context;
        this.b = mediaItem;
        this.f2445c = arrayList;
    }

    public final void onScanCompleted(String str, Uri uri) {
        SaveNotifiedContentCmd.lambda$saveAsFile$2(this.f2444a, this.b, this.f2445c, str, uri);
    }
}
