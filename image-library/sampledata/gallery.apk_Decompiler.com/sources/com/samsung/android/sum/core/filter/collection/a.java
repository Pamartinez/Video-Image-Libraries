package com.samsung.android.sum.core.filter.collection;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4078a;
    public final /* synthetic */ BufferedConveyorFilter b;

    public /* synthetic */ a(BufferedConveyorFilter bufferedConveyorFilter, int i2) {
        this.f4078a = i2;
        this.b = bufferedConveyorFilter;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4078a;
        BufferedConveyorFilter bufferedConveyorFilter = this.b;
        Enum enumR = (Enum) obj;
        switch (i2) {
            case 0:
                return bufferedConveyorFilter.lambda$addFilter$0(enumR);
            default:
                return bufferedConveyorFilter.lambda$addFilter$3(enumR);
        }
    }
}
