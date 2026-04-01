package com.samsung.android.vexfwk.sdk.common;

import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration;
import com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"com/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationFuture$SurfaceStream$Companion", "", "<init>", "()V", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$SurfaceStream;", "stream", "Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;", "direction", "Lcom/samsung/android/vexfwk/sdk/common/i;", "from", "(Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$SurfaceStream;Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;)Lcom/samsung/android/vexfwk/sdk/common/i;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHelperConfigurationFuture$SurfaceStream$Companion {
    public /* synthetic */ VexFwkHelperConfigurationFuture$SurfaceStream$Companion(e eVar) {
        this();
    }

    public final i from(VexFwkHelperConfiguration.SurfaceStream surfaceStream, VexFwkStreamInoutDirection vexFwkStreamInoutDirection) {
        j.e(surfaceStream, "stream");
        j.e(vexFwkStreamInoutDirection, "direction");
        Integer id = surfaceStream.getId();
        if (id != null) {
            int intValue = id.intValue();
            VexFwkStreamType type = surfaceStream.getType();
            if (type != null) {
                VexFwkStreamUsage usage = surfaceStream.getUsage();
                if (usage != null) {
                    Integer width = surfaceStream.getWidth();
                    if (width != null) {
                        int intValue2 = width.intValue();
                        Integer height = surfaceStream.getHeight();
                        if (height != null) {
                            int intValue3 = height.intValue();
                            Integer format = surfaceStream.getFormat();
                            if (format != null) {
                                return new i(intValue, vexFwkStreamInoutDirection, type, usage, intValue2, intValue3, format.intValue(), surfaceStream.getSurface());
                            }
                            throw new IllegalStateException(("Format is not specified for stream " + surfaceStream).toString());
                        }
                        throw new IllegalStateException(("Height is not specified for stream " + surfaceStream).toString());
                    }
                    throw new IllegalStateException(("Width is not specified for stream " + surfaceStream).toString());
                }
                throw new IllegalStateException(("Stream usage is not specified for stream " + surfaceStream).toString());
            }
            throw new IllegalStateException(("Stream type is not specified for stream " + surfaceStream).toString());
        }
        throw new IllegalStateException(("Stream ID is not specified for stream " + surfaceStream).toString());
    }

    private VexFwkHelperConfigurationFuture$SurfaceStream$Companion() {
    }
}
