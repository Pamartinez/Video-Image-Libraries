package com.samsung.o3dp.lib3dphotography.animation.animations;

import com.samsung.o3dp.lib3dphotography.animation.Animation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Animation e;

    public /* synthetic */ a(Animation animation, int i2) {
        this.d = i2;
        this.e = animation;
    }

    public final void run() {
        int i2 = this.d;
        Animation animation = this.e;
        switch (i2) {
            case 0:
                ((PanoramaAnimation) animation).lambda$onTouchEvent$0();
                return;
            default:
                ((TouchGestureAnimation) animation).lambda$new$2();
                return;
        }
    }
}
