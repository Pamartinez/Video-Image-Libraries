package F9;

import android.graphics.drawable.AnimatedImageDrawable;
import com.samsung.android.gallery.module.media.GifAnimationDrawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnimatedImageDrawable e;

    public /* synthetic */ c(AnimatedImageDrawable animatedImageDrawable, int i2) {
        this.d = i2;
        this.e = animatedImageDrawable;
    }

    public final void run() {
        int i2 = this.d;
        AnimatedImageDrawable animatedImageDrawable = this.e;
        switch (i2) {
            case 0:
                animatedImageDrawable.invalidateSelf();
                return;
            default:
                GifAnimationDrawable.lambda$stop$0(animatedImageDrawable);
                return;
        }
    }
}
