package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.format.MediaFormat;
import java.nio.ByteBuffer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class F implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4040a;
    public final /* synthetic */ RawDataReader b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaFormat f4041c;

    public /* synthetic */ F(RawDataReader rawDataReader, MediaFormat mediaFormat, int i2) {
        this.f4040a = i2;
        this.b = rawDataReader;
        this.f4041c = mediaFormat;
    }

    public final Object apply(Object obj) {
        switch (this.f4040a) {
            case 0:
                return this.b.lambda$new$8(this.f4041c, (ByteBuffer) obj);
            default:
                return this.b.lambda$new$9(this.f4041c, (ByteBuffer) obj);
        }
    }
}
