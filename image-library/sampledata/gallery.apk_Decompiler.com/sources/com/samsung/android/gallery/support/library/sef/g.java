package com.samsung.android.gallery.support.library.sef;

import java.util.function.ToIntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements ToIntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3148a;

    public /* synthetic */ g(int i2) {
        this.f3148a = i2;
    }

    public final int applyAsInt(Object obj) {
        SefData sefData = (SefData) obj;
        switch (this.f3148a) {
            case 0:
                return sefData.drdh.dataType;
            default:
                return sefData.packetSize;
        }
    }
}
