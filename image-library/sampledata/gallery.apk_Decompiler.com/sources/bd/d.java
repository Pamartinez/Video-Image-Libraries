package bd;

import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnimationManager.AnimationCallback e;

    public /* synthetic */ d(AnimationManager.AnimationCallback animationCallback, int i2) {
        this.d = i2;
        this.e = animationCallback;
    }

    public final void run() {
        int i2 = this.d;
        AnimationManager.AnimationCallback animationCallback = this.e;
        switch (i2) {
            case 0:
                AnimationManager.createSizeAnimation$lambda$7(animationCallback);
                return;
            case 1:
                AnimationManager.createNowBarAnimation$lambda$21(animationCallback);
                return;
            case 2:
                AnimationManager.hideByLuminance$lambda$24(animationCallback);
                return;
            default:
                AnimationManager.createLuminanceAnimation$lambda$12(animationCallback);
                return;
        }
    }
}
