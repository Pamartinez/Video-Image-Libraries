package Fb;

import android.view.Choreographer;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Choreographer.FrameCallback {
    public final void doFrame(long j2) {
        ThreadUtil.startPostponedHandler();
    }
}
