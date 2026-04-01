package com.samsung.android.gallery.module.remote.v2;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3077a;

    public /* synthetic */ a(int i2) {
        this.f3077a = i2;
    }

    public final boolean test(Object obj) {
        MirroringConnectionParam mirroringConnectionParam = (MirroringConnectionParam) obj;
        switch (this.f3077a) {
            case 0:
                return mirroringConnectionParam.isBackgroundPlaying();
            case 1:
                return mirroringConnectionParam.isHidingParam();
            case 2:
                return MirroringState.lambda$supportHighRes$2(mirroringConnectionParam);
            case 3:
                return MirroringState.lambda$isDMRConnected$1(mirroringConnectionParam);
            default:
                return MirroringState.lambda$isPaused$0(mirroringConnectionParam);
        }
    }
}
