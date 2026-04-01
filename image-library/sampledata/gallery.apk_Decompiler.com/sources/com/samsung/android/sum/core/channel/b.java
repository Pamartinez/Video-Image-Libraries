package com.samsung.android.sum.core.channel;

import android.media.ImageReader;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.evaluate.Evaluator;
import com.samsung.android.sum.core.functional.BufferSupplier;
import com.samsung.android.sum.core.graph.GraphEdge;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4064a;

    public /* synthetic */ b(int i2) {
        this.f4064a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4064a) {
            case 0:
                return ChannelRouterBase.lambda$new$1((List) obj);
            case 1:
                return ChannelRouterBase.lambda$new$3((List) obj);
            case 2:
                return (MediaBuffer) ((BufferChannel) obj).receive();
            case 3:
                return Boolean.valueOf(((BufferChannel) obj).isClosedForSend());
            case 4:
                return Boolean.valueOf(((BufferChannel) obj).isClosedForReceive());
            case 5:
                return ((BufferSupplier) obj).getBuffer();
            case 6:
                return (BufferChannel) ((Map.Entry) obj).getValue();
            case 7:
                return (Evaluator) ((Map.Entry) obj).getKey();
            case 8:
                return ((GraphEdge) obj).getEvaluator();
            case 9:
                return ((GraphEdge) obj).getBufferChannel();
            case 10:
                return Integer.valueOf(((ImageReader) obj).getHeight());
            case 11:
                return Integer.valueOf(((ImageReader) obj).getHardwareBufferFormat());
            case 12:
                return Long.valueOf(((ImageReader) obj).getUsage());
            case 13:
                return Integer.valueOf(((ImageReader) obj).getWidth());
            case 14:
                return Integer.valueOf(((ImageReader) obj).getHardwareBufferFormat());
            case 15:
                return Long.valueOf(((ImageReader) obj).getUsage());
            case 16:
                return Long.valueOf(((SurfaceReadChannel) obj).getUsage());
            case 17:
                return Integer.valueOf(((SurfaceReadChannel) obj).getWidth());
            case 18:
                return Integer.valueOf(((SurfaceReadChannel) obj).getHeight());
            default:
                return Integer.valueOf(((SurfaceReadChannel) obj).getFormat());
        }
    }
}
