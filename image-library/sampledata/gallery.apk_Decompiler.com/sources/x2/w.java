package X2;

import Df.n;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import v0.C0291a;
import w0.C0300a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class w {

    /* renamed from: a  reason: collision with root package name */
    public Object f943a;
    public Object b;

    public w(n nVar, InputStream inputStream) {
        this.b = nVar;
        this.f943a = inputStream;
    }

    public byte[] a(long j2) {
        int i2;
        if (j2 <= 2147483647L) {
            int i7 = (int) j2;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.abs(i7));
            if (j2 > 4096) {
                j2 = 4096;
            }
            int i8 = (int) j2;
            byte[] bArr = new byte[i8];
            while (i7 > 0) {
                try {
                    InputStream inputStream = (InputStream) this.f943a;
                    if (i7 > i8) {
                        i2 = i8;
                    } else {
                        i2 = i7;
                    }
                    int read = inputStream.read(bArr, 0, i2);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                        i7 -= read;
                    } else {
                        throw new IOException("Unexpected end of stream");
                    }
                } catch (IOException e) {
                    throw new Exception(e);
                }
            }
            return byteArrayOutputStream.toByteArray();
        }
        throw new Exception("Decoding fixed size items is limited to INTMAX");
    }

    public long b(int i2) {
        C0300a aVar;
        int[] iArr = C0291a.f1949a;
        int i7 = i2 & 31;
        switch (i7) {
            case 24:
                aVar = C0300a.ONE_BYTE;
                break;
            case 25:
                aVar = C0300a.TWO_BYTES;
                break;
            case 26:
                aVar = C0300a.FOUR_BYTES;
                break;
            case 27:
                aVar = C0300a.EIGHT_BYTES;
                break;
            case 28:
            case 29:
            case 30:
                aVar = C0300a.RESERVED;
                break;
            case 31:
                aVar = C0300a.INDEFINITE;
                break;
            default:
                aVar = C0300a.DIRECT;
                break;
        }
        switch (iArr[aVar.ordinal()]) {
            case 1:
                return (long) i7;
            case 2:
                return (long) d();
            case 3:
                byte[] e = e(2);
                return ((long) (e[1] & 255)) | ((long) ((e[0] & 255) << 8));
            case 4:
                byte[] e7 = e(4);
                long j2 = ((long) (e7[1] & 255)) << 16;
                return ((long) (e7[3] & 255)) | j2 | (((long) (e7[0] & 255)) << 24) | (((long) (e7[2] & 255)) << 8);
            case 5:
                byte[] e8 = e(8);
                long j3 = ((long) (e8[1] & 255)) << 48;
                long j8 = (((long) (e8[4] & 255)) << 24) | j3 | (((long) (e8[0] & 255)) << 56) | (((long) (e8[2] & 255)) << 40) | (((long) (e8[3] & 255)) << 32);
                return ((long) (e8[7] & 255)) | (((long) (e8[5] & 255)) << 16) | j8 | (((long) (e8[6] & 255)) << 8);
            case 6:
                return -1;
            default:
                throw new Exception("Reserved additional information");
        }
    }

    public BigInteger c(int i2) {
        C0300a aVar;
        int[] iArr = C0291a.f1949a;
        int i7 = i2 & 31;
        switch (i7) {
            case 24:
                aVar = C0300a.ONE_BYTE;
                break;
            case 25:
                aVar = C0300a.TWO_BYTES;
                break;
            case 26:
                aVar = C0300a.FOUR_BYTES;
                break;
            case 27:
                aVar = C0300a.EIGHT_BYTES;
                break;
            case 28:
            case 29:
            case 30:
                aVar = C0300a.RESERVED;
                break;
            case 31:
                aVar = C0300a.INDEFINITE;
                break;
            default:
                aVar = C0300a.DIRECT;
                break;
        }
        switch (iArr[aVar.ordinal()]) {
            case 1:
                return BigInteger.valueOf((long) i7);
            case 2:
                return BigInteger.valueOf((long) d());
            case 3:
                byte[] e = e(2);
                return BigInteger.valueOf(((long) (e[1] & 255)) | ((long) ((e[0] & 255) << 8)));
            case 4:
                byte[] e7 = e(4);
                long j2 = ((long) (e7[1] & 255)) << 16;
                return BigInteger.valueOf(((long) (e7[3] & 255)) | j2 | (((long) (e7[0] & 255)) << 24) | (((long) (e7[2] & 255)) << 8));
            case 5:
                BigInteger bigInteger = BigInteger.ZERO;
                byte[] e8 = e(8);
                return bigInteger.or(BigInteger.valueOf((long) (e8[0] & 255)).shiftLeft(56)).or(BigInteger.valueOf((long) (e8[1] & 255)).shiftLeft(48)).or(BigInteger.valueOf((long) (e8[2] & 255)).shiftLeft(40)).or(BigInteger.valueOf((long) (e8[3] & 255)).shiftLeft(32)).or(BigInteger.valueOf((long) (e8[4] & 255)).shiftLeft(24)).or(BigInteger.valueOf((long) (e8[5] & 255)).shiftLeft(16)).or(BigInteger.valueOf((long) (e8[6] & 255)).shiftLeft(8)).or(BigInteger.valueOf((long) (e8[7] & 255)).shiftLeft(0));
            case 6:
                return BigInteger.valueOf(-1);
            default:
                throw new Exception("Reserved additional information");
        }
    }

    public int d() {
        try {
            int read = ((InputStream) this.f943a).read();
            if (read != -1) {
                return read;
            }
            throw new IOException("Unexpected end of stream");
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public byte[] e(int i2) {
        InputStream inputStream = (InputStream) this.f943a;
        try {
            byte[] bArr = new byte[i2];
            int read = inputStream.read(bArr);
            if (read != i2) {
                if (read != -1) {
                    int i7 = i2 - read;
                    while (i7 > 0) {
                        int read2 = inputStream.read(bArr, i2 - i7, i7);
                        if (read2 != -1) {
                            i7 -= read2;
                        } else {
                            throw new IOException("Unexpected end of stream");
                        }
                    }
                } else {
                    throw new IOException("Unexpected end of stream");
                }
            }
            return bArr;
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}
