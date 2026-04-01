package H7;

import android.graphics.Rect;
import android.view.View;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerFakeViewManager;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.IStoryHighlightListV2View;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.ScaleDelegate;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRectUtil;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ float e;

    public /* synthetic */ x(float f, int i2) {
        this.d = i2;
        this.e = f;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        float f = this.e;
        switch (i2) {
            case 0:
                ((IMediaPlayerView) obj).set3dEffectPosition((double) f);
                return;
            case 1:
                O3DPRectUtil.scaleRect((Rect) obj, f);
                return;
            case 2:
                ((ScaleDelegate) obj).setScaleRelative(f);
                return;
            case 3:
                ((View) obj).setAlpha(f);
                return;
            case 4:
                ((IStoryHighlightListV2View) obj).onSheetSlide(f);
                return;
            case 5:
                ((DrawerFakeViewManager) obj).onSlide(f);
                return;
            case 6:
                ((Consumer) obj).accept(Float.valueOf(f));
                return;
            default:
                ((View) obj).setAlpha(f);
                return;
        }
    }
}
