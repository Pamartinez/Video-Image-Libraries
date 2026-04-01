package ic;

import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.widget.videoview.mediaplayer.AudioManagerDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;
import com.samsung.android.gallery.widget.videoview.mediaplayer.RoundedCornerDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerViewImp e;

    public /* synthetic */ k(MediaPlayerViewImp mediaPlayerViewImp, int i2) {
        this.d = i2;
        this.e = mediaPlayerViewImp;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MediaPlayerViewImp mediaPlayerViewImp = this.e;
        switch (i2) {
            case 0:
                mediaPlayerViewImp.lambda$onMeasure$19((RoundedCornerDelegate) obj);
                return;
            case 1:
                mediaPlayerViewImp.lambda$resume$6((AudioManagerDelegate) obj);
                return;
            case 2:
                mediaPlayerViewImp.lambda$open$1((AudioManagerDelegate) obj);
                return;
            default:
                mediaPlayerViewImp.onComputeVideo((MediaHelper.VideoInfo) obj);
                return;
        }
    }
}
