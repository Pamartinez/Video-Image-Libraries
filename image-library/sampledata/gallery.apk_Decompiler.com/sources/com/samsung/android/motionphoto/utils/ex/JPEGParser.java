package com.samsung.android.motionphoto.utils.ex;

import android.util.Log;
import android.util.Pair;
import com.samsung.android.motionphoto.utils.ex.MotionPhotoVideoUtils;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JPEGParser {
    private static final String TAG = "JPEGParser";

    public static byte[] getExifData(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[2];
        try {
            fileInputStream.getChannel().position(0);
            fileInputStream.read(bArr, 0, 2);
            long j2 = 2;
            while (fileInputStream.read(bArr, 0, 2) > 0) {
                long j3 = j2 + 2;
                Pair pair = new Pair(Integer.valueOf(bArr[0] & 255), Integer.valueOf(bArr[1] & 255));
                if (((Number) pair.first).intValue() == 255) {
                    if (208 > ((Number) pair.second).intValue() || 215 < ((Number) pair.second).intValue()) {
                        if (((Number) pair.second).intValue() == 225) {
                            int read = (fileInputStream.read() << 8) | fileInputStream.read();
                            byte[] bArr2 = new byte[4];
                            if (fileInputStream.read(bArr2) < 4) {
                                Log.e(TAG, "Fail to read exif Tag");
                                fileInputStream.close();
                                return null;
                            }
                            long j8 = 8 + j2;
                            if (new String(bArr2, "UTF-8").equals(MediaDefs.Image.HEIF.HEIF_EXIF_BOX)) {
                                int i2 = read - 2;
                                byte[] bArr3 = new byte[i2];
                                fileInputStream.getChannel().position(j2 + 4);
                                fileInputStream.read(bArr3, 0, i2);
                                fileInputStream.close();
                                return bArr3;
                            }
                            Log.i(TAG, "Not exif " + read);
                            long j10 = (long) (read + -6);
                            fileInputStream.skip(j10);
                            j2 = j10 + j8;
                        }
                    }
                    j2 = j3;
                } else {
                    Log.i(TAG, "this is not valid markers");
                    fileInputStream.close();
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileInputStream.close();
        return null;
    }

    public MotionPhotoVideoUtils.XMPInformation getCoverImageXMPOffsetAndSize(FileInputStream fileInputStream) {
        int read;
        FileInputStream fileInputStream2 = fileInputStream;
        int i2 = 2;
        byte[] bArr = new byte[2];
        long size = fileInputStream2.getChannel().size();
        try {
            fileInputStream2.getChannel().position(0);
            fileInputStream2.read(bArr, 0, 2);
            while (true) {
                if (fileInputStream2.read(bArr, 0, i2) <= 0) {
                    break;
                }
                Pair pair = new Pair(Integer.valueOf(bArr[0] & 255), Integer.valueOf(bArr[1] & 255));
                if (((Number) pair.first).intValue() != 255) {
                    Log.i(TAG, "this is not valid markers");
                    return null;
                } else if (208 > ((Number) pair.second).intValue() || 215 < ((Number) pair.second).intValue()) {
                    if (((Number) pair.second).intValue() != 225) {
                        if (((Number) pair.second).intValue() == 226 || ((Number) pair.second).intValue() == 227 || ((Number) pair.second).intValue() == 228) {
                            Log.d(TAG, "Found data:" + pair.second);
                            read = (fileInputStream2.read() << 8) | fileInputStream2.read();
                            long position = fileInputStream2.getChannel().position();
                            long j2 = (long) read;
                            if (j2 > size) {
                                break;
                            }
                            long j3 = (position - 2) + j2;
                            if (j3 > size) {
                                break;
                            }
                            fileInputStream2.getChannel().position(j3);
                        }
                    } else {
                        int read2 = (fileInputStream2.read() << 8) | fileInputStream2.read();
                        long position2 = fileInputStream2.getChannel().position();
                        byte[] bArr2 = new byte[29];
                        fileInputStream2.read(bArr2, 0, 29);
                        try {
                            byte[] bArr3 = new byte[29];
                            System.arraycopy(bArr2, 0, bArr3, 0, 29);
                            if (new String(bArr3, "UTF-8").equals(MediaDefs.Meta.XMP.XMP_SIGNATURE)) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("xmp offset: ");
                                long j8 = position2 - 4;
                                sb2.append(j8);
                                sb2.append(" size: ");
                                int i7 = read2 + 2;
                                sb2.append(i7);
                                Log.i(TAG, sb2.toString());
                                fileInputStream2.getChannel().position(j8);
                                byte[] bArr4 = new byte[i7];
                                fileInputStream2.read(bArr4, 0, i7);
                                return new MotionPhotoVideoUtils.XMPInformation(j8, (long) i7, bArr4);
                            }
                            byte[] bArr5 = new byte[5];
                            System.arraycopy(bArr2, 0, bArr5, 0, 5);
                            if (new String(bArr5, "UTF-8").equals("Exif\u0000")) {
                                fileInputStream2.getChannel().position((position2 - 2) + ((long) read2));
                            } else {
                                Log.i(TAG, "Not exif, not xmp");
                            }
                        } catch (UnsupportedEncodingException e) {
                            Log.e(TAG, "hasXMPHeader:\n" + e.getMessage());
                        }
                    }
                    i2 = 2;
                }
            }
            Log.w(TAG, "marker len:" + read + " is larger than fsize:" + size);
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        return null;
    }
}
