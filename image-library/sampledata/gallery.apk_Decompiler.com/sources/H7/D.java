package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoSpeedControlHandler;
import com.samsung.android.gallery.widget.videoview.controller.VideoSpeedControllerCompat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ VideoSpeedControlHandler e;

    public /* synthetic */ D(VideoSpeedControlHandler videoSpeedControlHandler, int i2) {
        this.d = i2;
        this.e = videoSpeedControlHandler;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        VideoSpeedControlHandler videoSpeedControlHandler = this.e;
        VideoSpeedControllerCompat videoSpeedControllerCompat = (VideoSpeedControllerCompat) obj;
        switch (i2) {
            case 0:
                videoSpeedControlHandler.lambda$refresh$4(videoSpeedControllerCompat);
                return;
            default:
                videoSpeedControlHandler.lambda$onFinalized$5(videoSpeedControllerCompat);
                return;
        }
    }
}
