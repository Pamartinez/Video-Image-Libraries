package bd;

import O3.l;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationFactory$Companion$createAnimation$1$1;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Runnable e;

    public /* synthetic */ a(Runnable runnable, int i2) {
        this.d = i2;
        this.e = runnable;
    }

    public final void run() {
        int i2 = this.d;
        Runnable runnable = this.e;
        switch (i2) {
            case 0:
                AnimationFactory$Companion$createAnimation$1$1.onAnimationStart$lambda$0(runnable);
                return;
            case 1:
                AnimationFactory$Companion$createAnimation$1$1.onAnimationEnd$lambda$1(runnable);
                return;
            case 2:
                AnimationFactory$Companion$createAnimation$1$1.onAnimationCancel$lambda$2(runnable);
                return;
            case 3:
                ThreadUtil.runOnUiThread(new a(runnable, 4));
                return;
            default:
                Optional.ofNullable(runnable).ifPresent(new l(0));
                return;
        }
    }
}
