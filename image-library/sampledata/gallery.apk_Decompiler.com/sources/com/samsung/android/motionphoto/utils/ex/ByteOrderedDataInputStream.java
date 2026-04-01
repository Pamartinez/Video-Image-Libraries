package com.samsung.android.motionphoto.utils.ex;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\b\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u0000 D2\u00020\u00012\u00020\u0002:\u0001DB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005B\u0013\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0004\u0010\bJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0016\u0010\u0014J\u000f\u0010\u0017\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0017\u0010\u0014J\u0011\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0018H\u0016¢\u0006\u0004\b!\u0010\u001aJ'\u0010%\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0012H\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010%\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u0006H\u0016¢\u0006\u0004\b%\u0010\bJ\u000f\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)J\u000f\u0010+\u001a\u00020*H\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020\u0012H\u0016¢\u0006\u0004\b-\u0010\u0014J\u0017\u0010.\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0012H\u0016¢\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020\u0012H\u0016¢\u0006\u0004\b0\u0010\u0014J\r\u00101\u001a\u00020\u000e¢\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020\u000eH\u0016¢\u0006\u0004\b3\u00102J\u000f\u00105\u001a\u000204H\u0016¢\u0006\u0004\b5\u00106J\u000f\u00108\u001a\u000207H\u0016¢\u0006\u0004\b8\u00109R\u0014\u0010\u0003\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u001e\u0010?\u001a\n >*\u0004\u0018\u00010\t0\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b?\u0010@R\u0017\u0010$\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b$\u0010A\u001a\u0004\bB\u0010\u0014R\u0016\u0010C\u001a\u00020\u00128\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bC\u0010A¨\u0006E"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/ByteOrderedDataInputStream;", "Ljava/io/InputStream;", "Ljava/io/DataInput;", "mInputStream", "<init>", "(Ljava/io/InputStream;)V", "", "bytes", "([B)V", "Ljava/nio/ByteOrder;", "byteOrder", "Lme/x;", "setByteOrder", "(Ljava/nio/ByteOrder;)V", "", "byteCount", "seek", "(J)V", "", "peek", "()I", "available", "read", "readUnsignedByte", "", "readLine", "()Ljava/lang/String;", "", "readBoolean", "()Z", "", "readChar", "()C", "readUTF", "buffer", "offset", "length", "readFully", "([BII)V", "", "readByte", "()B", "", "readShort", "()S", "readInt", "skipBytes", "(I)I", "readUnsignedShort", "readUnsignedInt", "()J", "readLong", "", "readFloat", "()F", "", "readDouble", "()D", "Ljava/io/InputStream;", "Ljava/io/DataInputStream;", "mDataInputStream", "Ljava/io/DataInputStream;", "kotlin.jvm.PlatformType", "mByteOrder", "Ljava/nio/ByteOrder;", "I", "getLength", "mPosition", "Companion", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ByteOrderedDataInputStream extends InputStream implements DataInput {
    private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
    public static final Companion Companion = new Companion((e) null);
    private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
    public static final String TAG = "ByteOrderedDataInputStream";
    private final int length;
    private ByteOrder mByteOrder;
    private final DataInputStream mDataInputStream;
    private final InputStream mInputStream;
    private int mPosition;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0018\u0010\n\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/motionphoto/utils/ex/ByteOrderedDataInputStream$Companion;", "", "<init>", "()V", "TAG", "", "LITTLE_ENDIAN", "Ljava/nio/ByteOrder;", "kotlin.jvm.PlatformType", "Ljava/nio/ByteOrder;", "BIG_ENDIAN", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public ByteOrderedDataInputStream(InputStream inputStream) {
        j.e(inputStream, "mInputStream");
        this.mInputStream = inputStream;
        this.mByteOrder = ByteOrder.BIG_ENDIAN;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.mDataInputStream = dataInputStream;
        int available = dataInputStream.available();
        this.length = available;
        this.mPosition = 0;
        dataInputStream.mark(available);
    }

    public int available() {
        return this.mDataInputStream.available();
    }

    public final int getLength() {
        return this.length;
    }

    public final int peek() {
        return this.mPosition;
    }

    public int read() {
        this.mPosition++;
        return this.mDataInputStream.read();
    }

    public boolean readBoolean() {
        this.mPosition++;
        return this.mDataInputStream.readBoolean();
    }

    public byte readByte() {
        int i2 = this.mPosition + 1;
        this.mPosition = i2;
        if (i2 <= this.length) {
            int read = this.mDataInputStream.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    public char readChar() {
        this.mPosition += 2;
        return this.mDataInputStream.readChar();
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public void readFully(byte[] bArr, int i2, int i7) {
        j.e(bArr, "buffer");
        int i8 = this.mPosition + i7;
        this.mPosition = i8;
        if (i8 > this.length) {
            throw new EOFException();
        } else if (this.mDataInputStream.read(bArr, i2, i7) != i7) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }

    public int readInt() {
        int i2 = this.mPosition + 4;
        this.mPosition = i2;
        if (i2 <= this.length) {
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4) < 0) {
                throw new EOFException();
            } else if (j.a(this.mByteOrder, LITTLE_ENDIAN)) {
                return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
            } else {
                if (j.a(this.mByteOrder, BIG_ENDIAN)) {
                    return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                }
                ByteOrder byteOrder = this.mByteOrder;
                throw new IOException("Invalid byte order: " + byteOrder);
            }
        } else {
            throw new EOFException();
        }
    }

    public String readLine() {
        Log.d(TAG, "Currently unsupported");
        return null;
    }

    public long readLong() {
        int i2 = this.mPosition + 8;
        this.mPosition = i2;
        if (i2 <= this.length) {
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            int read5 = this.mDataInputStream.read();
            int read6 = this.mDataInputStream.read();
            int read7 = this.mDataInputStream.read();
            int read8 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) < 0) {
                throw new EOFException();
            } else if (j.a(this.mByteOrder, LITTLE_ENDIAN)) {
                return (((long) read8) << 56) + (((long) read7) << 48) + (((long) read6) << 40) + (((long) read5) << 32) + (((long) read4) << 24) + (((long) read3) << 16) + (((long) read2) << 8) + ((long) read);
            } else {
                if (j.a(this.mByteOrder, BIG_ENDIAN)) {
                    return (((long) read) << 56) + (((long) read2) << 48) + (((long) read3) << 40) + (((long) read4) << 32) + (((long) read5) << 24) + (((long) read6) << 16) + (((long) read7) << 8) + ((long) read8);
                }
                ByteOrder byteOrder = this.mByteOrder;
                throw new IOException("Invalid byte order: " + byteOrder);
            }
        } else {
            throw new EOFException();
        }
    }

    public short readShort() {
        int i2;
        int i7 = this.mPosition + 2;
        this.mPosition = i7;
        if (i7 <= this.length) {
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) >= 0) {
                if (j.a(this.mByteOrder, LITTLE_ENDIAN)) {
                    i2 = (read2 << 8) + read;
                } else if (j.a(this.mByteOrder, BIG_ENDIAN)) {
                    i2 = (read << 8) + read2;
                } else {
                    ByteOrder byteOrder = this.mByteOrder;
                    throw new IOException("Invalid byte order: " + byteOrder);
                }
                return (short) i2;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    public String readUTF() {
        this.mPosition += 2;
        String readUTF = this.mDataInputStream.readUTF();
        j.d(readUTF, "readUTF(...)");
        return readUTF;
    }

    public int readUnsignedByte() {
        this.mPosition++;
        return this.mDataInputStream.readUnsignedByte();
    }

    public final long readUnsignedInt() {
        return (long) readInt();
    }

    public int readUnsignedShort() {
        int i2 = this.mPosition + 2;
        this.mPosition = i2;
        if (i2 <= this.length) {
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            } else if (j.a(this.mByteOrder, LITTLE_ENDIAN)) {
                return (read2 << 8) + read;
            } else {
                if (j.a(this.mByteOrder, BIG_ENDIAN)) {
                    return (read << 8) + read2;
                }
                ByteOrder byteOrder = this.mByteOrder;
                throw new IOException("Invalid byte order: " + byteOrder);
            }
        } else {
            throw new EOFException();
        }
    }

    public final void seek(long j2) {
        int i2 = this.mPosition;
        if (((long) i2) > j2) {
            this.mPosition = 0;
            this.mDataInputStream.reset();
            this.mDataInputStream.mark(this.length);
        } else {
            j2 -= (long) i2;
        }
        int i7 = (int) j2;
        if (skipBytes(i7) != i7) {
            throw new IOException("Couldn't seek up to the byteCount");
        }
    }

    public final void setByteOrder(ByteOrder byteOrder) {
        j.e(byteOrder, "byteOrder");
        this.mByteOrder = byteOrder;
    }

    public int skipBytes(int i2) {
        int min = Math.min(i2, this.length - this.mPosition);
        int i7 = 0;
        while (i7 < min) {
            i7 += this.mDataInputStream.skipBytes(min - i7);
        }
        this.mPosition += i7;
        return i7;
    }

    public void readFully(byte[] bArr) {
        j.e(bArr, "buffer");
        int length2 = this.mPosition + bArr.length;
        this.mPosition = length2;
        if (length2 > this.length) {
            throw new EOFException();
        } else if (this.mDataInputStream.read(bArr, 0, bArr.length) != bArr.length) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }

    public ByteOrderedDataInputStream(byte[] bArr) {
        this((InputStream) new ByteArrayInputStream(bArr));
    }
}
