package F;

import android.os.Handler;
import android.os.Message;
import androidx.media3.common.util.ListenerSet;
import com.samsung.android.gallery.app.service.FileOpService;
import com.samsung.android.gallery.app.service.abstraction.AbsProgressService;
import com.samsung.android.gallery.support.library.v0.system.SemBoosterCompat;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f177a;
    public final /* synthetic */ Object b;

    public /* synthetic */ b(int i2, Object obj) {
        this.f177a = i2;
        this.b = obj;
    }

    public final boolean handleMessage(Message message) {
        int i2 = this.f177a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                return ((ListenerSet) obj).handleMessage(message);
            case 1:
                return ((SemBoosterCompat) obj).handleMessage(message);
            case 2:
                return ((FileOpService) obj).handleMessage(message);
            case 3:
                return ((AbsProgressService) obj).handleMessage(message);
            default:
                return ((SimpleAutoScroller) obj).tryStartShrink(message);
        }
    }
}
