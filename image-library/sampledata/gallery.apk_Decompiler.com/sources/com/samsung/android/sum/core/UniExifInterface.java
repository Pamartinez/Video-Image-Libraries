package com.samsung.android.sum.core;

import android.media.ExifInterface;
import android.util.Pair;
import com.samsung.android.media.SemBitmapFactory;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UniExifInterface extends ExifInterface implements AutoCloseable {
    static final byte[] EXIF_PREFIX = {-1, -31};
    static final byte[] JPEG_POSTFIX = {-1, -39};
    static final byte[] JPEG_PREFIX = {-1, -40};
    private static final String TAG = Def.tagOf((Class<?>) UniExifInterface.class);
    ByteBuffer originExifBuffer;
    File tempFile;

    private UniExifInterface(ByteBuffer byteBuffer, File file) {
        super(file);
        this.tempFile = file;
        this.originExifBuffer = byteBuffer;
        file.deleteOnExit();
    }

    public static UniExifInterface emptyOf() {
        return of(ByteBuffer.allocate(0));
    }

    private static boolean isJpegPrefix(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        if (byteBuffer.limit() < 2) {
            return false;
        }
        return Arrays.equals(new byte[]{byteBuffer.get(), byteBuffer.get()}, JPEG_PREFIX);
    }

    public static UniExifInterface of(ByteBuffer byteBuffer) {
        try {
            return new UniExifInterface(byteBuffer, toJpegExifFile(byteBuffer));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ByteBuffer parseExif(File file) {
        ByteBuffer parseJpegExif = parseJpegExif(file);
        if (parseJpegExif == null) {
            return parseHeifExif(file);
        }
        return parseJpegExif;
    }

    private static ByteBuffer parseHeifExif(File file) {
        return (ByteBuffer) Optional.ofNullable(SemBitmapFactory.getExifDataFile(file.getPath())).map(new a(8)).orElse((Object) null);
    }

    private static ByteBuffer parseJpegExif(File file) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[2];
            fileInputStream.getChannel().position(0);
            fileInputStream.read(bArr, 0, 2);
            long j2 = 2;
            while (fileInputStream.read(bArr, 0, 2) > 0) {
                long j3 = j2 + 2;
                Pair pair = new Pair(Integer.valueOf(bArr[0] & 255), Integer.valueOf(bArr[1] & 255));
                if (((Number) pair.first).intValue() != 255) {
                    SLog.d(TAG, "this is not valid markers");
                } else if ((208 > ((Number) pair.second).intValue() || 215 < ((Number) pair.second).intValue()) && ((Number) pair.second).intValue() == 225) {
                    int read = (fileInputStream.read() << 8) | fileInputStream.read();
                    byte[] bArr2 = new byte[4];
                    if (fileInputStream.read(bArr2) < 4) {
                        SLog.e(TAG, "Fail to read exif Tag");
                    } else {
                        long j8 = 8 + j2;
                        if (new String(bArr2, "UTF-8").equals(MediaDefs.Image.HEIF.HEIF_EXIF_BOX)) {
                            int i2 = read - 2;
                            byte[] bArr3 = new byte[i2];
                            fileInputStream.getChannel().position(j2 + 4);
                            fileInputStream.read(bArr3, 0, i2);
                            ByteBuffer wrap = ByteBuffer.wrap(bArr3);
                            fileInputStream.close();
                            return wrap;
                        }
                        SLog.d(TAG, "Not exif " + read);
                        long j10 = (long) (read + -6);
                        fileInputStream.skip(j10);
                        j2 = j8 + j10;
                    }
                } else {
                    j2 = j3;
                }
                fileInputStream.close();
                return null;
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return null;
        throw th;
    }

    private void reset() {
        this.originExifBuffer.clear();
        if (this.tempFile.exists()) {
            this.tempFile.delete();
        }
    }

    private static File toJpegExifFile(ByteBuffer byteBuffer) {
        byte[] bArr;
        if (isJpegPrefix(byteBuffer)) {
            byteBuffer.rewind();
            bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
        } else {
            byteBuffer.rewind();
            int remaining = byteBuffer.remaining();
            byte[] bArr2 = new byte[remaining];
            byte[] bArr3 = new byte[(byteBuffer.limit() + 8)];
            byteBuffer.get(bArr2);
            System.arraycopy(JPEG_PREFIX, 0, bArr3, 0, 2);
            System.arraycopy(EXIF_PREFIX, 0, bArr3, 2, 2);
            int i2 = remaining + 2;
            bArr3[4] = (byte) ((i2 >>> 8) & ScoverState.TYPE_NFC_SMART_COVER);
            bArr3[5] = (byte) (i2 & ScoverState.TYPE_NFC_SMART_COVER);
            System.arraycopy(bArr2, 0, bArr3, 6, remaining);
            System.arraycopy(JPEG_POSTFIX, 0, bArr3, remaining + 6, 2);
            bArr = bArr3;
        }
        File createTempFile = File.createTempFile("temp.jpg", "tmp");
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        fileOutputStream.write(bArr);
        fileOutputStream.close();
        return createTempFile;
    }

    public void close() {
        this.originExifBuffer.clear();
        if (this.tempFile.exists()) {
            this.tempFile.delete();
        }
    }

    public void finalize() {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public ByteBuffer getOriginExifBuffer() {
        return this.originExifBuffer;
    }

    public File getTempFile() {
        return this.tempFile;
    }

    public ByteBuffer toExifByteBuffer() {
        byte[] bArr = new byte[0];
        try {
            bArr = Files.readAllBytes(this.tempFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = bArr.length - 8;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 6, bArr2, 0, length);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(length);
        allocateDirect.put(bArr2);
        allocateDirect.rewind();
        reset();
        return allocateDirect;
    }

    public static UniExifInterface of(File file) {
        return (UniExifInterface) Optional.ofNullable(parseExif(file)).map(new a(7)).orElse((Object) null);
    }
}
