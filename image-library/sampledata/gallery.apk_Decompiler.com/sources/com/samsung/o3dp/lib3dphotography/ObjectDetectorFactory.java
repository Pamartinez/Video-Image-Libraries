package com.samsung.o3dp.lib3dphotography;

import com.samsung.o3dp.lib3dphotography.utils.RemasterUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ObjectDetectorFactory {
    public static ObjectDetectable create() {
        if (RemasterUtil.isAtLeastOneUi8_0()) {
            return new EfficientDetector();
        }
        return new ObjectDetectorLegacy();
    }
}
