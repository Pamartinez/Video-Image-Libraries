package bd;

import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnimationManager e;
    public final /* synthetic */ float f;
    public final /* synthetic */ float g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ AnimationManager.AnimationCallback f1051h;

    public /* synthetic */ c(AnimationManager animationManager, float f5, float f8, AnimationManager.AnimationCallback animationCallback, int i2) {
        this.d = i2;
        this.e = animationManager;
        this.f = f5;
        this.g = f8;
        this.f1051h = animationCallback;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                AnimationManager.createSizeAnimation$lambda$6(this.e, this.f, this.g, this.f1051h);
                return;
            case 1:
                AnimationManager.createNowBarAnimation$lambda$15(this.e, this.f, this.g, this.f1051h);
                return;
            case 2:
                AnimationManager.createNowBarAnimation$lambda$17(this.e, this.f, this.g, this.f1051h);
                return;
            case 3:
                AnimationManager.createNowBarAnimation$lambda$18(this.e, this.f, this.g, this.f1051h);
                return;
            case 4:
                AnimationManager.createNowBarAnimation$lambda$20(this.e, this.f, this.g, this.f1051h);
                return;
            default:
                AnimationManager.createSizeAnimation$lambda$4(this.e, this.f, this.g, this.f1051h);
                return;
        }
    }
}
