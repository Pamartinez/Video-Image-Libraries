package com.samsung.android.vexfwk.param;

import com.samsung.android.vexfwk.metadata.IVexFwkMetadataConverter;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bJ\u0015\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH&¢\u0006\u0002\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0004¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "T", "Lcom/samsung/android/vexfwk/metadata/IVexFwkMetadataConverter;", "<init>", "()V", "from", "data", "", "([B)Ljava/lang/Object;", "to", "value", "(Ljava/lang/Object;)[B", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)Ljava/lang/Object;", "toBuffer", "(Ljava/lang/Object;)Ljava/nio/ByteBuffer;", "wrapBuffer", "allocateBuffer", "size", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VexFwkParamBaseBuffer<T> implements IVexFwkMetadataConverter<T> {
    private final ByteBuffer wrapBuffer(byte[] bArr) {
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder());
        j.d(order, "order(...)");
        return order;
    }

    public final ByteBuffer allocateBuffer(int i2) {
        ByteBuffer order = ByteBuffer.wrap(new byte[i2]).order(ByteOrder.nativeOrder());
        j.d(order, "order(...)");
        return order;
    }

    public T from(byte[] bArr) {
        j.e(bArr, "data");
        return fromBuffer(wrapBuffer(bArr));
    }

    public abstract T fromBuffer(ByteBuffer byteBuffer);

    public byte[] to(T t) {
        Buffer rewind = toBuffer(t).rewind();
        j.c(rewind, "null cannot be cast to non-null type java.nio.ByteBuffer");
        byte[] array = ((ByteBuffer) rewind).array();
        j.d(array, "array(...)");
        return array;
    }

    public abstract ByteBuffer toBuffer(T t);
}
