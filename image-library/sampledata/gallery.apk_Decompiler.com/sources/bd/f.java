package bd;

import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnimationManager e;

    public /* synthetic */ f(AnimationManager animationManager, int i2) {
        this.d = i2;
        this.e = animationManager;
    }

    public final void run() {
        int i2 = this.d;
        AnimationManager animationManager = this.e;
        switch (i2) {
            case 0:
                AnimationManager.createNowBarAnimation$lambda$14(animationManager);
                return;
            default:
                AnimationManager.createSizeAnimation$lambda$3(animationManager);
                return;
        }
    }
}
