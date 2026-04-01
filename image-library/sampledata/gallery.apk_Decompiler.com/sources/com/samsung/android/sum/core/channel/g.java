package com.samsung.android.sum.core.channel;

import android.hardware.HardwareBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4067a;
    public final /* synthetic */ HardwareBuffer b;

    public /* synthetic */ g(HardwareBuffer hardwareBuffer, int i2) {
        this.f4067a = i2;
        this.b = hardwareBuffer;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4067a;
        HardwareBuffer hardwareBuffer = this.b;
        Map.Entry entry = (Map.Entry) obj;
        switch (i2) {
            case 0:
                return Arrays.stream((int[]) entry.getValue()).anyMatch(new d(hardwareBuffer, 0));
            default:
                return Arrays.stream((int[]) entry.getValue()).anyMatch(new d(hardwareBuffer, 1));
        }
    }
}
