package com.samsung.android.sum.core.buffer;

import com.samsung.android.motionphoto.core.MPSurfaceReader;
import com.samsung.android.sum.core.format.MediaFormat;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Function {
    public final Object apply(Object obj) {
        return MediaFormat.newBuilder().setDataType(SharedBufferManager.dataTypeFromHalFormat(((MPSurfaceReader.MPSurfaceImage) obj).getFormat())).setColorFormat(SharedBufferManager.colorFormatFromHalFormat(((MPSurfaceReader.MPSurfaceImage) obj).getHardwareBuffer().getFormat())).setColorSpace(SharedBufferManager.colorSpaceFromDataSpace(((MPSurfaceReader.MPSurfaceImage) obj).getDataSpace())).setShape(((MPSurfaceReader.MPSurfaceImage) obj).getWidth(), ((MPSurfaceReader.MPSurfaceImage) obj).getHeight()).buildMutable();
    }
}
