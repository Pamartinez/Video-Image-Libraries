package androidx.media3.common.util;

import B1.a;
import F2.C0010c0;
import He.F;
import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ParsableByteArray {
    private static final char[] CR_AND_LF = {13, 10};
    private static final char[] LF = {10};
    private static final C0010c0 SUPPORTED_CHARSETS_FOR_READLINE = C0010c0.x(5, StandardCharsets.US_ASCII, StandardCharsets.UTF_8, StandardCharsets.UTF_16, StandardCharsets.UTF_16BE, StandardCharsets.UTF_16LE);
    private byte[] data;
    private int limit;
    private int position;

    public ParsableByteArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    private static int decodeUtf8CodeUnit(int i2, int i7, int i8, int i10) {
        byte b = (byte) i8;
        return C0246a.V((byte) 0, a.f((long) (((i2 & 7) << 2) | ((i7 & 48) >> 4))), a.f((long) (((((byte) i7) & 15) << 4) | ((b & 60) >> 2))), a.f((long) (((b & 3) << 6) | (((byte) i10) & 63))));
    }

    private int findNextLineTerminator(Charset charset) {
        int i2;
        if (charset.equals(StandardCharsets.UTF_8) || charset.equals(StandardCharsets.US_ASCII)) {
            i2 = 1;
        } else if (charset.equals(StandardCharsets.UTF_16) || charset.equals(StandardCharsets.UTF_16LE) || charset.equals(StandardCharsets.UTF_16BE)) {
            i2 = 2;
        } else {
            throw new IllegalArgumentException("Unsupported charset: " + charset);
        }
        int i7 = this.position;
        while (true) {
            int i8 = this.limit;
            if (i7 >= i8 - (i2 - 1)) {
                return i8;
            }
            if ((charset.equals(StandardCharsets.UTF_8) || charset.equals(StandardCharsets.US_ASCII)) && Util.isLinebreak(this.data[i7])) {
                break;
            }
            if (charset.equals(StandardCharsets.UTF_16) || charset.equals(StandardCharsets.UTF_16BE)) {
                byte[] bArr = this.data;
                if (bArr[i7] == 0 && Util.isLinebreak(bArr[i7 + 1])) {
                    break;
                }
            }
            if (charset.equals(StandardCharsets.UTF_16LE)) {
                byte[] bArr2 = this.data;
                if (bArr2[i7 + 1] == 0 && Util.isLinebreak(bArr2[i7])) {
                    break;
                }
            }
            i7 += i2;
        }
        return i7;
    }

    private static int getSmallestCodeUnitSize(Charset charset) {
        boolean contains = SUPPORTED_CHARSETS_FOR_READLINE.contains(charset);
        Assertions.checkArgument(contains, "Unsupported charset: " + charset);
        if (charset.equals(StandardCharsets.UTF_8) || charset.equals(StandardCharsets.US_ASCII)) {
            return 1;
        }
        return 2;
    }

    private static boolean isUtf8ContinuationByte(byte b) {
        if ((b & 192) == 128) {
            return true;
        }
        return false;
    }

    private char peekChar(ByteOrder byteOrder, int i2) {
        byte b;
        byte b5;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.data;
            int i7 = this.position + i2;
            b = bArr[i7];
            b5 = bArr[i7 + 1];
        } else {
            byte[] bArr2 = this.data;
            int i8 = this.position + i2;
            b = bArr2[i8 + 1];
            b5 = bArr2[i8];
        }
        return (char) ((b5 & 255) | (b << 8));
    }

    private int peekCodePointAndSize(Charset charset) {
        int i2;
        ByteOrder byteOrder;
        Assertions.checkArgument(SUPPORTED_CHARSETS_FOR_READLINE.contains(charset), "Unsupported charset: " + charset);
        if (bytesLeft() >= getSmallestCodeUnitSize(charset)) {
            byte b = 1;
            if (charset.equals(StandardCharsets.US_ASCII)) {
                byte b5 = this.data[this.position];
                if ((b5 & 128) != 0) {
                    return 0;
                }
                i2 = b5 & 255;
            } else if (charset.equals(StandardCharsets.UTF_8)) {
                byte peekUtf8CodeUnitSize = peekUtf8CodeUnitSize();
                if (peekUtf8CodeUnitSize == 1) {
                    i2 = this.data[this.position] & 255;
                } else if (peekUtf8CodeUnitSize == 2) {
                    byte[] bArr = this.data;
                    int i7 = this.position;
                    i2 = decodeUtf8CodeUnit(0, 0, bArr[i7], bArr[i7 + 1]);
                } else if (peekUtf8CodeUnitSize == 3) {
                    byte[] bArr2 = this.data;
                    int i8 = this.position;
                    i2 = decodeUtf8CodeUnit(0, bArr2[i8] & 15, bArr2[i8 + 1], bArr2[i8 + 2]);
                } else if (peekUtf8CodeUnitSize != 4) {
                    return 0;
                } else {
                    byte[] bArr3 = this.data;
                    int i10 = this.position;
                    i2 = decodeUtf8CodeUnit(bArr3[i10], bArr3[i10 + 1], bArr3[i10 + 2], bArr3[i10 + 3]);
                }
                b = peekUtf8CodeUnitSize;
            } else {
                if (charset.equals(StandardCharsets.UTF_16LE)) {
                    byteOrder = ByteOrder.LITTLE_ENDIAN;
                } else {
                    byteOrder = ByteOrder.BIG_ENDIAN;
                }
                char peekChar = peekChar(byteOrder, 0);
                if (!Character.isHighSurrogate(peekChar) || bytesLeft() < 4) {
                    i2 = peekChar;
                    b = 2;
                } else {
                    i2 = Character.toCodePoint(peekChar, peekChar(byteOrder, 2));
                    b = 4;
                }
            }
            return (i2 << 8) | b;
        }
        throw new IndexOutOfBoundsException("position=" + this.position + ", limit=" + this.limit);
    }

    private byte peekUtf8CodeUnitSize() {
        byte b = this.data[this.position];
        if ((b & 128) == 0) {
            return 1;
        }
        if ((b & 224) == 192 && bytesLeft() >= 2 && isUtf8ContinuationByte(this.data[this.position + 1])) {
            return 2;
        }
        if ((this.data[this.position] & 240) == 224 && bytesLeft() >= 3 && isUtf8ContinuationByte(this.data[this.position + 1]) && isUtf8ContinuationByte(this.data[this.position + 2])) {
            return 3;
        }
        if ((this.data[this.position] & 248) != 240 || bytesLeft() < 4 || !isUtf8ContinuationByte(this.data[this.position + 1]) || !isUtf8ContinuationByte(this.data[this.position + 2]) || !isUtf8ContinuationByte(this.data[this.position + 3])) {
            return 0;
        }
        return 4;
    }

    private char readCharacterIfInList(Charset charset, char[] cArr) {
        int peekCodePointAndSize;
        boolean z;
        if (bytesLeft() < getSmallestCodeUnitSize(charset) || (peekCodePointAndSize = peekCodePointAndSize(charset)) == 0) {
            return 0;
        }
        long j2 = (long) (peekCodePointAndSize >>> 8);
        boolean z3 = true;
        if ((j2 >> 32) == 0) {
            z = true;
        } else {
            z = false;
        }
        F.k(z, "out of range: %s", j2);
        int i2 = (int) j2;
        if (Character.isSupplementaryCodePoint(i2)) {
            return 0;
        }
        long j3 = (long) i2;
        char c5 = (char) ((int) j3);
        if (((long) c5) != j3) {
            z3 = false;
        }
        F.k(z3, "Out of range: %s", j3);
        for (char c6 : cArr) {
            if (c6 == c5) {
                this.position = C0246a.N((long) (peekCodePointAndSize & ScoverState.TYPE_NFC_SMART_COVER)) + this.position;
                return c5;
            }
        }
        return 0;
    }

    private void skipLineTerminator(Charset charset) {
        if (readCharacterIfInList(charset, CR_AND_LF) == 13) {
            readCharacterIfInList(charset, LF);
        }
    }

    public int bytesLeft() {
        return Math.max(this.limit - this.position, 0);
    }

    public int capacity() {
        return this.data.length;
    }

    public void ensureCapacity(int i2) {
        if (i2 > capacity()) {
            this.data = Arrays.copyOf(this.data, i2);
        }
    }

    public byte[] getData() {
        return this.data;
    }

    public int getPosition() {
        return this.position;
    }

    public int limit() {
        return this.limit;
    }

    public int peekCodePoint(Charset charset) {
        int peekCodePointAndSize = peekCodePointAndSize(charset);
        if (peekCodePointAndSize != 0) {
            return C0246a.N((long) (peekCodePointAndSize >>> 8));
        }
        return 1114112;
    }

    public int peekUnsignedByte() {
        return this.data[this.position] & 255;
    }

    public void readBytes(ParsableBitArray parsableBitArray, int i2) {
        readBytes(parsableBitArray.data, 0, i2);
        parsableBitArray.setPosition(0);
    }

    public String readDelimiterTerminatedString(char c5) {
        if (bytesLeft() == 0) {
            return null;
        }
        int i2 = this.position;
        while (i2 < this.limit && this.data[i2] != c5) {
            i2++;
        }
        byte[] bArr = this.data;
        int i7 = this.position;
        String fromUtf8Bytes = Util.fromUtf8Bytes(bArr, i7, i2 - i7);
        this.position = i2;
        if (i2 < this.limit) {
            this.position = i2 + 1;
        }
        return fromUtf8Bytes;
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public int readInt() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        int i8 = i2 + 2;
        this.position = i8;
        byte b = ((bArr[i7] & 255) << 16) | ((bArr[i2] & 255) << 24);
        int i10 = i2 + 3;
        this.position = i10;
        this.position = i2 + 4;
        return (bArr[i10] & 255) | b | ((bArr[i8] & 255) << 8);
    }

    public int readInt24() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        int i8 = i2 + 2;
        this.position = i8;
        int i10 = (bArr[i7] & 255) << 8;
        this.position = i2 + 3;
        return (bArr[i8] & 255) | i10 | (((bArr[i2] & 255) << 24) >> 8);
    }

    public String readLine() {
        return readLine(StandardCharsets.UTF_8);
    }

    public int readLittleEndianInt() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        int i8 = i2 + 2;
        this.position = i8;
        byte b = ((bArr[i7] & 255) << 8) | (bArr[i2] & 255);
        int i10 = i2 + 3;
        this.position = i10;
        this.position = i2 + 4;
        return ((bArr[i10] & 255) << 24) | b | ((bArr[i8] & 255) << 16);
    }

    public long readLittleEndianLong() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        int i8 = i2 + 2;
        this.position = i8;
        long j2 = (((long) bArr[i2]) & 255) | ((((long) bArr[i7]) & 255) << 8);
        int i10 = i2 + 3;
        this.position = i10;
        int i11 = i2 + 4;
        this.position = i11;
        long j3 = j2 | ((((long) bArr[i8]) & 255) << 16) | ((((long) bArr[i10]) & 255) << 24);
        int i12 = i2 + 5;
        this.position = i12;
        int i13 = i2 + 6;
        this.position = i13;
        long j8 = j3 | ((((long) bArr[i11]) & 255) << 32) | ((((long) bArr[i12]) & 255) << 40);
        int i14 = i2 + 7;
        this.position = i14;
        this.position = i2 + 8;
        return ((((long) bArr[i14]) & 255) << 56) | j8 | ((((long) bArr[i13]) & 255) << 48);
    }

    public short readLittleEndianShort() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        this.position = i2 + 2;
        return (short) (((bArr[i7] & 255) << 8) | (bArr[i2] & 255));
    }

    public long readLittleEndianUnsignedInt() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        int i8 = i2 + 2;
        this.position = i8;
        int i10 = i2 + 3;
        this.position = i10;
        this.position = i2 + 4;
        return ((((long) bArr[i10]) & 255) << 24) | (((long) bArr[i2]) & 255) | ((((long) bArr[i7]) & 255) << 8) | ((((long) bArr[i8]) & 255) << 16);
    }

    public int readLittleEndianUnsignedIntToInt() {
        int readLittleEndianInt = readLittleEndianInt();
        if (readLittleEndianInt >= 0) {
            return readLittleEndianInt;
        }
        throw new IllegalStateException(C0086a.i(readLittleEndianInt, "Top bit not zero: "));
    }

    public int readLittleEndianUnsignedShort() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        this.position = i2 + 2;
        return ((bArr[i7] & 255) << 8) | (bArr[i2] & 255);
    }

    public long readLong() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        int i8 = i2 + 2;
        this.position = i8;
        int i10 = i2 + 3;
        this.position = i10;
        long j2 = ((((long) bArr[i2]) & 255) << 56) | ((((long) bArr[i7]) & 255) << 48) | ((((long) bArr[i8]) & 255) << 40);
        int i11 = i2 + 4;
        this.position = i11;
        int i12 = i2 + 5;
        this.position = i12;
        long j3 = j2 | ((((long) bArr[i10]) & 255) << 32) | ((((long) bArr[i11]) & 255) << 24);
        int i13 = i2 + 6;
        this.position = i13;
        int i14 = i2 + 7;
        this.position = i14;
        this.position = i2 + 8;
        return (((long) bArr[i14]) & 255) | j3 | ((((long) bArr[i12]) & 255) << 16) | ((((long) bArr[i13]) & 255) << 8);
    }

    public String readNullTerminatedString(int i2) {
        if (i2 == 0) {
            return "";
        }
        int i7 = this.position;
        int i8 = (i7 + i2) - 1;
        String fromUtf8Bytes = Util.fromUtf8Bytes(this.data, i7, (i8 >= this.limit || this.data[i8] != 0) ? i2 : i2 - 1);
        this.position += i2;
        return fromUtf8Bytes;
    }

    public short readShort() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        this.position = i2 + 2;
        return (short) ((bArr[i7] & 255) | ((bArr[i2] & 255) << 8));
    }

    public String readString(int i2) {
        return readString(i2, StandardCharsets.UTF_8);
    }

    public int readSynchSafeInt() {
        return readUnsignedByte() | (readUnsignedByte() << 21) | (readUnsignedByte() << 14) | (readUnsignedByte() << 7);
    }

    public int readUnsignedByte() {
        byte[] bArr = this.data;
        int i2 = this.position;
        this.position = i2 + 1;
        return bArr[i2] & 255;
    }

    public int readUnsignedFixedPoint1616() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        this.position = i2 + 2;
        byte b = (bArr[i7] & 255) | ((bArr[i2] & 255) << 8);
        this.position = i2 + 4;
        return b;
    }

    public long readUnsignedInt() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        int i8 = i2 + 2;
        this.position = i8;
        int i10 = i2 + 3;
        this.position = i10;
        this.position = i2 + 4;
        return (((long) bArr[i10]) & 255) | ((((long) bArr[i2]) & 255) << 24) | ((((long) bArr[i7]) & 255) << 16) | ((((long) bArr[i8]) & 255) << 8);
    }

    public int readUnsignedInt24() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        int i8 = i2 + 2;
        this.position = i8;
        int i10 = (bArr[i7] & 255) << 8;
        this.position = i2 + 3;
        return (bArr[i8] & 255) | i10 | ((bArr[i2] & 255) << 16);
    }

    public int readUnsignedIntToInt() {
        int readInt = readInt();
        if (readInt >= 0) {
            return readInt;
        }
        throw new IllegalStateException(C0086a.i(readInt, "Top bit not zero: "));
    }

    public int readUnsignedLeb128ToInt() {
        return C0246a.N(readUnsignedLeb128ToLong());
    }

    public long readUnsignedLeb128ToLong() {
        int i2 = 0;
        long j2 = 0;
        while (i2 < 9) {
            if (this.position != this.limit) {
                long readUnsignedByte = (long) readUnsignedByte();
                j2 |= (127 & readUnsignedByte) << (i2 * 7);
                if ((readUnsignedByte & 128) == 0) {
                    return j2;
                }
                i2++;
            } else {
                throw new IllegalStateException("Attempting to read a byte over the limit.");
            }
        }
        return j2;
    }

    public long readUnsignedLongToLong() {
        long readLong = readLong();
        if (readLong >= 0) {
            return readLong;
        }
        throw new IllegalStateException(A.a.f("Top bit not zero: ", readLong));
    }

    public int readUnsignedShort() {
        byte[] bArr = this.data;
        int i2 = this.position;
        int i7 = i2 + 1;
        this.position = i7;
        this.position = i2 + 2;
        return (bArr[i7] & 255) | ((bArr[i2] & 255) << 8);
    }

    public long readUtf8EncodedLong() {
        int i2;
        int i7;
        long j2 = (long) this.data[this.position];
        int i8 = 7;
        while (true) {
            i2 = 1;
            if (i8 < 0) {
                break;
            }
            int i10 = 1 << i8;
            if ((((long) i10) & j2) != 0) {
                i8--;
            } else if (i8 < 6) {
                j2 &= (long) (i10 - 1);
                i7 = 7 - i8;
            } else if (i8 == 7) {
                i7 = 1;
            }
        }
        i7 = 0;
        if (i7 != 0) {
            while (i2 < i7) {
                byte b = this.data[this.position + i2];
                if ((b & 192) == 128) {
                    j2 = (j2 << 6) | ((long) (b & 63));
                    i2++;
                } else {
                    throw new NumberFormatException(A.a.f("Invalid UTF-8 sequence continuation byte: ", j2));
                }
            }
            this.position += i7;
            return j2;
        }
        throw new NumberFormatException(A.a.f("Invalid UTF-8 sequence first byte: ", j2));
    }

    public Charset readUtfCharsetFromBom() {
        if (bytesLeft() >= 3) {
            byte[] bArr = this.data;
            int i2 = this.position;
            if (bArr[i2] == -17 && bArr[i2 + 1] == -69 && bArr[i2 + 2] == -65) {
                this.position = i2 + 3;
                return StandardCharsets.UTF_8;
            }
        }
        if (bytesLeft() < 2) {
            return null;
        }
        byte[] bArr2 = this.data;
        int i7 = this.position;
        byte b = bArr2[i7];
        if (b == -2 && bArr2[i7 + 1] == -1) {
            this.position = i7 + 2;
            return StandardCharsets.UTF_16BE;
        } else if (b != -1 || bArr2[i7 + 1] != -2) {
            return null;
        } else {
            this.position = i7 + 2;
            return StandardCharsets.UTF_16LE;
        }
    }

    public void reset(int i2) {
        reset(capacity() < i2 ? new byte[i2] : this.data, i2);
    }

    public void setLimit(int i2) {
        boolean z;
        if (i2 < 0 || i2 > this.data.length) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkArgument(z);
        this.limit = i2;
    }

    public void setPosition(int i2) {
        boolean z;
        if (i2 < 0 || i2 > this.limit) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkArgument(z);
        this.position = i2;
    }

    public void skipBytes(int i2) {
        setPosition(this.position + i2);
    }

    public void skipLeb128() {
        do {
        } while ((readUnsignedByte() & 128) != 0);
    }

    public String readLine(Charset charset) {
        boolean contains = SUPPORTED_CHARSETS_FOR_READLINE.contains(charset);
        Assertions.checkArgument(contains, "Unsupported charset: " + charset);
        if (bytesLeft() == 0) {
            return null;
        }
        if (!charset.equals(StandardCharsets.US_ASCII)) {
            readUtfCharsetFromBom();
        }
        String readString = readString(findNextLineTerminator(charset) - this.position, charset);
        if (this.position == this.limit) {
            return readString;
        }
        skipLineTerminator(charset);
        return readString;
    }

    public String readString(int i2, Charset charset) {
        String str = new String(this.data, this.position, i2, charset);
        this.position += i2;
        return str;
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public ParsableByteArray(int i2) {
        this.data = new byte[i2];
        this.limit = i2;
    }

    public void readBytes(byte[] bArr, int i2, int i7) {
        System.arraycopy(this.data, this.position, bArr, i2, i7);
        this.position += i7;
    }

    public void reset(byte[] bArr, int i2) {
        this.data = bArr;
        this.limit = i2;
        this.position = 0;
    }

    public ParsableByteArray(byte[] bArr) {
        this.data = bArr;
        this.limit = bArr.length;
    }

    public String readNullTerminatedString() {
        return readDelimiterTerminatedString(0);
    }

    public ParsableByteArray(byte[] bArr, int i2) {
        this.data = bArr;
        this.limit = i2;
    }
}
