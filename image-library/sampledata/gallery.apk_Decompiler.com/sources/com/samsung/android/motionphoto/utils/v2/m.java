package com.samsung.android.motionphoto.utils.v2;

import Ae.a;
import com.samsung.android.motionphoto.utils.v2.MotionScrap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionScrap.FutureWrapper e;

    public /* synthetic */ m(MotionScrap.FutureWrapper futureWrapper, int i2) {
        this.d = i2;
        this.e = futureWrapper;
    }

    public final Object invoke() {
        int i2 = this.d;
        MotionScrap.FutureWrapper futureWrapper = this.e;
        switch (i2) {
            case 0:
                return MotionScrap.FutureWrapper.all_delegate$lambda$0(futureWrapper);
            default:
                return MotionScrap.FutureWrapper.any_delegate$lambda$1(futureWrapper);
        }
    }
}
