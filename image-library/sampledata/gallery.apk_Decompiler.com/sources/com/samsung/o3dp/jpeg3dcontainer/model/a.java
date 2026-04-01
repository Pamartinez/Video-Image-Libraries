package com.samsung.o3dp.jpeg3dcontainer.model;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4200a;
    public final /* synthetic */ Jpeg3d b;

    public /* synthetic */ a(Jpeg3d jpeg3d, int i2) {
        this.f4200a = i2;
        this.b = jpeg3d;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4200a;
        Jpeg3d jpeg3d = this.b;
        Segment segment = (Segment) obj;
        switch (i2) {
            case 0:
                return jpeg3d.isMpfSegment(segment);
            case 1:
                return jpeg3d.isGContainerSegment(segment);
            case 2:
                return jpeg3d.lambda$new$0(segment);
            default:
                return jpeg3d.isXmpSegment(segment);
        }
    }
}
