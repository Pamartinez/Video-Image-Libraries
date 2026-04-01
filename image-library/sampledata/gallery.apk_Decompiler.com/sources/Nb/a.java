package Nb;

import android.graphics.Canvas;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.photoview.PhotoPreViewDebugDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.DebugInfoDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Canvas e;

    public /* synthetic */ a(Canvas canvas, int i2) {
        this.d = i2;
        this.e = canvas;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                if (obj == null) {
                    PhotoPreView.lambda$onDraw$3(this.e, (PhotoPreViewDebugDelegate) null);
                    return;
                }
                throw new ClassCastException();
            default:
                ((DebugInfoDelegate) obj).onDrawForeground(this.e);
                return;
        }
    }
}
