package ic;

import com.samsung.android.gallery.widget.capture.ClipViewDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.AudioManagerDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.CaptureDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.DebugInfoDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaSessionDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.RoundedCornerDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ g(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((DebugInfoDelegate) obj).setLogTag(obj2);
                return;
            case 1:
                ((AudioManagerDelegate) obj).setLogTag(obj2);
                return;
            case 2:
                ((CaptureDelegate) obj).setLogTag(obj2);
                return;
            case 3:
                ((ClipViewDelegate) obj).setLogTag(obj2);
                return;
            case 4:
                ((MediaSessionDelegate) obj).setLogTag(obj2);
                return;
            default:
                ((RoundedCornerDelegate) obj).setLogTag(obj2);
                return;
        }
    }
}
