package com.samsung.android.sum.core.channel;

import android.media.ImageWriter;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4068a;
    public final /* synthetic */ StapleSurfaceWriteChannel b;

    public /* synthetic */ m(StapleSurfaceWriteChannel stapleSurfaceWriteChannel, int i2) {
        this.f4068a = i2;
        this.b = stapleSurfaceWriteChannel;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4068a;
        StapleSurfaceWriteChannel stapleSurfaceWriteChannel = this.b;
        ImageWriter imageWriter = (ImageWriter) obj;
        switch (i2) {
            case 0:
                return stapleSurfaceWriteChannel.lambda$getWidth$0(imageWriter);
            case 1:
                return stapleSurfaceWriteChannel.lambda$getUsage$3(imageWriter);
            case 2:
                return stapleSurfaceWriteChannel.lambda$getHeight$1(imageWriter);
            default:
                return stapleSurfaceWriteChannel.lambda$getFormat$2(imageWriter);
        }
    }
}
