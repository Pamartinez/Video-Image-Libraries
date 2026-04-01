package androidx.profileinstaller;

import c0.C0086a;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class Encoding {
    public static int bitsToBytes(int i2) {
        return ((i2 + 7) & -8) / 8;
    }

    public static byte[] compress(byte[] bArr) {
        DeflaterOutputStream deflaterOutputStream;
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            deflaterOutputStream.write(bArr);
            deflaterOutputStream.close();
            deflater.end();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
        throw th;
    }

    public static RuntimeException error(String str) {
        return new IllegalStateException(str);
    }

    public static byte[] read(InputStream inputStream, int i2) {
        byte[] bArr = new byte[i2];
        int i7 = 0;
        while (i7 < i2) {
            int read = inputStream.read(bArr, i7, i2 - i7);
            if (read >= 0) {
                i7 += read;
            } else {
                throw error(C0086a.i(i2, "Not enough bytes to read: "));
            }
        }
        return bArr;
    }

    public static byte[] readCompressed(InputStream inputStream, int i2, int i7) {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i7];
            byte[] bArr2 = new byte[2048];
            int i8 = 0;
            int i10 = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i8 < i2) {
                int read = inputStream.read(bArr2);
                if (read >= 0) {
                    inflater.setInput(bArr2, 0, read);
                    i10 += inflater.inflate(bArr, i10, i7 - i10);
                    i8 += read;
                } else {
                    throw error("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i2 + " bytes");
                }
            }
            if (i8 != i2) {
                throw error("Didn't read enough bytes during decompression. expected=" + i2 + " actual=" + i8);
            } else if (inflater.finished()) {
                inflater.end();
                return bArr;
            } else {
                throw error("Inflater did not finish");
            }
        } catch (DataFormatException e) {
            throw error(e.getMessage());
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }

    public static String readString(InputStream inputStream, int i2) {
        return new String(read(inputStream, i2), StandardCharsets.UTF_8);
    }

    public static long readUInt(InputStream inputStream, int i2) {
        byte[] read = read(inputStream, i2);
        long j2 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            j2 += ((long) (read[i7] & 255)) << (i7 * 8);
        }
        return j2;
    }

    public static int readUInt16(InputStream inputStream) {
        return (int) readUInt(inputStream, 2);
    }

    public static long readUInt32(InputStream inputStream) {
        return readUInt(inputStream, 4);
    }

    public static int readUInt8(InputStream inputStream) {
        return (int) readUInt(inputStream, 1);
    }

    public static int utf8Length(String str) {
        return str.getBytes(StandardCharsets.UTF_8).length;
    }

    public static void writeAll(InputStream inputStream, OutputStream outputStream, FileLock fileLock) {
        if (fileLock == null || !fileLock.isValid()) {
            throw new IOException("Unable to acquire a lock on the underlying file channel.");
        }
        byte[] bArr = new byte[512];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void writeCompressed(OutputStream outputStream, byte[] bArr) {
        writeUInt32(outputStream, (long) bArr.length);
        byte[] compress = compress(bArr);
        writeUInt32(outputStream, (long) compress.length);
        outputStream.write(compress);
    }

    public static void writeString(OutputStream outputStream, String str) {
        outputStream.write(str.getBytes(StandardCharsets.UTF_8));
    }

    public static void writeUInt(OutputStream outputStream, long j2, int i2) {
        byte[] bArr = new byte[i2];
        for (int i7 = 0; i7 < i2; i7++) {
            bArr[i7] = (byte) ((int) ((j2 >> (i7 * 8)) & 255));
        }
        outputStream.write(bArr);
    }

    public static void writeUInt16(OutputStream outputStream, int i2) {
        writeUInt(outputStream, (long) i2, 2);
    }

    public static void writeUInt32(OutputStream outputStream, long j2) {
        writeUInt(outputStream, j2, 4);
    }

    public static void writeUInt8(OutputStream outputStream, int i2) {
        writeUInt(outputStream, (long) i2, 1);
    }
}
