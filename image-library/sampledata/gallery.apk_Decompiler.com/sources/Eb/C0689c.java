package eb;

import android.widget.ImageView;
import com.samsung.android.gallery.widget.animations.SimpleShrinkView;

/* renamed from: eb.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0689c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SimpleShrinkView e;
    public final /* synthetic */ ImageView f;

    public /* synthetic */ C0689c(SimpleShrinkView simpleShrinkView, ImageView imageView, int i2) {
        this.d = i2;
        this.e = simpleShrinkView;
        this.f = imageView;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$recycle$2(this.f);
                return;
            default:
                this.e.lambda$recycle$1(this.f);
                return;
        }
    }
}
