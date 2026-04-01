package bd;

import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnimationManager e;
    public final /* synthetic */ float f;
    public final /* synthetic */ AnimationManager.AnimationCallback g;

    public /* synthetic */ g(AnimationManager animationManager, float f5, AnimationManager.AnimationCallback animationCallback, int i2) {
        this.d = i2;
        this.e = animationManager;
        this.f = f5;
        this.g = animationCallback;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                AnimationManager.hideByLuminance$lambda$23(this.e, this.f, this.g);
                return;
            default:
                AnimationManager.createLuminanceAnimation$lambda$11(this.e, this.f, this.g);
                return;
        }
    }
}
