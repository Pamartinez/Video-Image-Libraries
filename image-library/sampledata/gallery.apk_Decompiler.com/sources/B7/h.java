package B7;

import android.graphics.Rect;
import android.view.View;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Rect e;

    public /* synthetic */ h(Rect rect, int i2) {
        this.d = i2;
        this.e = rect;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Rect rect = this.e;
        switch (i2) {
            case 0:
                ViewMarginUtils.setPadding((View) ((IMediaPlayerView) obj), rect.left, rect.top, rect.right, rect.bottom);
                return;
            case 1:
                ViewMarginUtils.setMarginRelative((View) obj, Integer.valueOf(rect.left), Integer.valueOf(rect.top), Integer.valueOf(rect.right), Integer.valueOf(rect.bottom));
                return;
            default:
                ViewMarginUtils.setMarginRelative((View) obj, Integer.valueOf(rect.left), Integer.valueOf(rect.top), Integer.valueOf(rect.right), Integer.valueOf(rect.bottom));
                return;
        }
    }
}
