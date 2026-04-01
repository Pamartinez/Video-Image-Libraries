package com.samsung.android.vexfwk.sdk.common;

import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration;
import com.samsung.android.vexfwk.session.VexFwkStreamInoutDirection;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"com/samsung/android/vexfwk/sdk/common/VexFwkHelperConfigurationFuture$BufferStream$Companion", "", "<init>", "()V", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$BufferStream;", "stream", "Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;", "direction", "Lcom/samsung/android/vexfwk/sdk/common/g;", "from", "(Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$BufferStream;Lcom/samsung/android/vexfwk/session/VexFwkStreamInoutDirection;)Lcom/samsung/android/vexfwk/sdk/common/g;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHelperConfigurationFuture$BufferStream$Companion {
    public /* synthetic */ VexFwkHelperConfigurationFuture$BufferStream$Companion(e eVar) {
        this();
    }

    public final g from(VexFwkHelperConfiguration.BufferStream bufferStream, VexFwkStreamInoutDirection vexFwkStreamInoutDirection) {
        j.e(bufferStream, "stream");
        j.e(vexFwkStreamInoutDirection, "direction");
        Integer id = bufferStream.getId();
        if (id != null) {
            int intValue = id.intValue();
            VexFwkStreamType type = bufferStream.getType();
            if (type != null) {
                VexFwkStreamUsage usage = bufferStream.getUsage();
                if (usage != null) {
                    return new g(intValue, vexFwkStreamInoutDirection, type, usage);
                }
                throw new IllegalStateException(("Stream usage is not specified for stream " + bufferStream).toString());
            }
            throw new IllegalStateException(("Stream type is not specified for stream " + bufferStream).toString());
        }
        throw new IllegalStateException(("Stream ID is not specified for stream " + bufferStream).toString());
    }

    private VexFwkHelperConfigurationFuture$BufferStream$Companion() {
    }
}
