package com.samsung.android.vexfwk.sdk.common.runtime;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.vexfwk.hardware.VexFwkHardwareBufferNative;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1200r;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a,\u0010\u0006\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0007¨\u0006\f"}, d2 = {"getOutputBuffer", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkBuffer;", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "streamId", "", "getOutputBufferOrThrow", "toBitmapCropped", "Landroid/graphics/Bitmap;", "left", "top", "right", "bottom", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkSessionExtensionsKt {
    public static final VexFwkBuffer getOutputBuffer(VexFwkSessionTotalResult vexFwkSessionTotalResult, int i2) {
        j.e(vexFwkSessionTotalResult, "<this>");
        ArrayList arrayList = new ArrayList();
        for (VexFwkSessionPartialResult outputBuffers : vexFwkSessionTotalResult.getPartialResults()) {
            C1200r.A0(outputBuffers.getOutputBuffers(), arrayList);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            VexFwkBuffer vexFwkBuffer = (VexFwkBuffer) it.next();
            if (vexFwkBuffer.getStreamId() == i2) {
                return vexFwkBuffer;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static final VexFwkBuffer getOutputBufferOrThrow(VexFwkSessionTotalResult vexFwkSessionTotalResult, int i2) {
        j.e(vexFwkSessionTotalResult, "<this>");
        VexFwkBuffer outputBuffer = getOutputBuffer(vexFwkSessionTotalResult, i2);
        if (outputBuffer != null) {
            return outputBuffer;
        }
        throw new IllegalStateException(C0212a.j(i2, "No output buffer found for stream id ", ".").toString());
    }

    public static final Bitmap toBitmapCropped(VexFwkBuffer vexFwkBuffer, int i2, int i7, int i8, int i10) {
        j.e(vexFwkBuffer, "<this>");
        if (new Rect(i2, i7, i8, i10).isEmpty()) {
            StringBuilder h5 = a.h(i2, i7, "Crop rect cannot be empty or negative. Given: (", ArcCommonLog.TAG_COMMA, ArcCommonLog.TAG_COMMA);
            h5.append(i8);
            h5.append(ArcCommonLog.TAG_COMMA);
            h5.append(i10);
            h5.append(")");
            throw new IllegalStateException(h5.toString().toString());
        } else if (i2 < 0 || i2 >= vexFwkBuffer.getHardwareBuffer().getWidth()) {
            throw new IllegalArgumentException(N2.j.b(vexFwkBuffer.getHardwareBuffer().getWidth(), i2, "left is out of bounds, expected to be in range [0, ", ") but was ").toString());
        } else if (i7 < 0 || i7 >= vexFwkBuffer.getHardwareBuffer().getHeight()) {
            throw new IllegalArgumentException(N2.j.b(vexFwkBuffer.getHardwareBuffer().getHeight(), i7, "top is out of bounds, expected to be in range [0, ", ") but was ").toString());
        } else if (i8 < 0 || i8 >= vexFwkBuffer.getHardwareBuffer().getWidth()) {
            throw new IllegalArgumentException(N2.j.b(vexFwkBuffer.getHardwareBuffer().getWidth(), i8, "right is out of bounds, expected to be in range [0, ", ") but was ").toString());
        } else if (i10 >= 0 && i10 < vexFwkBuffer.getHardwareBuffer().getHeight()) {
            return VexFwkHardwareBufferNative.Companion.convertToBitmap(vexFwkBuffer.getHardwareBuffer(), i2, i7, i8, i10);
        } else {
            throw new IllegalArgumentException(N2.j.b(vexFwkBuffer.getHardwareBuffer().getHeight(), i10, "bottom is out of bounds, expected to be in range [0, ", ") but was ").toString());
        }
    }
}
