package com.samsung.o3dp.lib3dphotography;

import com.samsung.o3dp.lib3dphotography.ObjectDetector;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4202a;

    public /* synthetic */ a(int i2) {
        this.f4202a = i2;
    }

    public final Object apply(Object obj) {
        ObjectDetector.ObjectInfo objectInfo = (ObjectDetector.ObjectInfo) obj;
        switch (this.f4202a) {
            case 0:
                return EfficientDetector.createBodyObject(objectInfo);
            default:
                return EfficientDetector.createFaceObject(objectInfo);
        }
    }
}
