package com.samsung.android.sum.core.format;

import com.samsung.android.sum.core.types.DataType;
import java.util.ArrayList;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4099a;
    public final /* synthetic */ ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ DataType f4100c;
    public final /* synthetic */ Shape d;

    public /* synthetic */ d(ArrayList arrayList, DataType dataType, Shape shape, int i2) {
        this.f4099a = i2;
        this.b = arrayList;
        this.f4100c = dataType;
        this.d = shape;
    }

    public final void accept(int i2) {
        switch (this.f4099a) {
            case 0:
                this.b.add(MediaFormat.newImageBuilder().setDataType(this.f4100c).setShape(this.d).buildMutable());
                return;
            default:
                this.b.add(MediaFormat.newImageBuilder().setDataType(this.f4100c).setShape(this.d).buildMutable());
                return;
        }
    }
}
