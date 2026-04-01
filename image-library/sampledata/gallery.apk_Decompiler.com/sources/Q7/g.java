package Q7;

import com.samsung.android.gallery.app.ui.viewer2.menu.GenEditVideoMenuItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ GenEditVideoMenuItem e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ g(GenEditVideoMenuItem genEditVideoMenuItem, Runnable runnable, int i2) {
        this.d = i2;
        this.e = genEditVideoMenuItem;
        this.f = runnable;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$handleEditor$1(this.f, obj);
                return;
            default:
                this.e.lambda$handleEditor$2(this.f, obj);
                return;
        }
    }
}
