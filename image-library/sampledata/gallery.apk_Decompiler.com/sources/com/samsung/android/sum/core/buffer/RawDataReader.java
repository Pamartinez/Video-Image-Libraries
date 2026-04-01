package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.types.DataType;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RawDataReader implements MediaBufferReader<Number> {
    private static final String TAG = Def.tagOf((Class<?>) RawDataReader.class);
    private final Supplier<?> pixelRead;
    private final Function<Integer, ?> pixelReadByIndex;

    public RawDataReader(MediaBuffer mediaBuffer) {
        MediaFormat format = mediaBuffer.getFormat();
        Class cls = ByteBuffer.class;
        this.pixelRead = (Supplier) Optional.ofNullable((ByteBuffer) mediaBuffer.getTypedData(cls)).map(new F(this, format, 0)).orElseThrow(new u(4));
        this.pixelReadByIndex = (Function) Optional.ofNullable((ByteBuffer) mediaBuffer.getTypedData(cls)).map(new F(this, format, 1)).orElseThrow(new u(4));
    }

    private Supplier<?> getRawDataRead(DataType dataType, ByteBuffer byteBuffer) {
        if (dataType.isByte()) {
            Objects.requireNonNull(byteBuffer);
            return new D(byteBuffer, 0);
        } else if (dataType.isShort()) {
            return new D(byteBuffer, 1);
        } else {
            if (dataType.isInt()) {
                return new D(byteBuffer, 2);
            }
            if (dataType.isLong()) {
                return new D(byteBuffer, 3);
            }
            if (dataType.isFloat()) {
                return new D(byteBuffer, 4);
            }
            throw new UnsupportedOperationException("not supported");
        }
    }

    private Function<Integer, ?> getRawDataReadByIndex(DataType dataType, ByteBuffer byteBuffer) {
        if (dataType.isByte()) {
            Objects.requireNonNull(byteBuffer);
            return new E(byteBuffer, 2);
        } else if (dataType.isShort()) {
            return new E(byteBuffer, 3);
        } else {
            if (dataType.isInt()) {
                return new E(byteBuffer, 4);
            }
            if (dataType.isLong()) {
                return new E(byteBuffer, 0);
            }
            if (dataType.isFloat()) {
                return new E(byteBuffer, 1);
            }
            throw new UnsupportedOperationException("not supported");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Supplier lambda$new$8(MediaFormat mediaFormat, ByteBuffer byteBuffer) {
        return getRawDataRead(mediaFormat.getDataType(), byteBuffer);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Function lambda$new$9(MediaFormat mediaFormat, ByteBuffer byteBuffer) {
        return getRawDataReadByIndex(mediaFormat.getDataType(), byteBuffer);
    }

    public Number get() {
        return (Number) this.pixelRead.get();
    }

    public Number get(int i2) {
        return (Number) this.pixelReadByIndex.apply(Integer.valueOf(i2));
    }
}
