package com.samsung.android.sdk.sgpl.media.iso;

import A.a;
import android.icu.text.SimpleDateFormat;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.media.SemExtendedFormat;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ISOEditor {
    private static final int BOX_HEADER_SIZE = 8;
    private static final int DEFAULT_SOCT_BOX_SIZE = 16;
    private static final int DEFAULT_UDTA_BOX_SIZE = 8;
    private static final int DEFAULT_XYZ_BOX_SIZE = 30;
    private static final int FULL_BOX_HEADER_SIZE = 12;
    private static final String FUNC_REMOVE = "Remove";
    private static final String FUNC_RESTORE = "Restore";
    private static final int LANGUAGE_CODE_SIZE = 2;
    private static final int MAX_ATOM_SIZE = 67108864;
    private static final int MAX_INT_SIZE = Integer.MAX_VALUE;
    private static final long MAX_UINT_SIZE = 4294967295L;
    private static final int MEDIA_TYPE_HEIF = 2;
    private static final int MEDIA_TYPE_MP4 = 1;
    private static final int MEDIA_TYPE_UNKNOWN = 0;
    public static final int OPTION_EDIT = 1;
    private static final int OPTION_FIRST = 1;
    private static final int OPTION_LAST = 3;
    public static final int OPTION_REMOVE = 3;
    public static final int OPTION_RESTORE = 2;
    private static final int STRING_TERMINATOR_SIZE = 1;
    private static final String TAG = "ISOEditor";
    private static final int TAG_BYTE_FIRST = 100;
    private static final int TAG_BYTE_LAST = 100;
    public static final int TAG_DATE = 1;
    public static final int TAG_DEVICE_NAME = 4;
    public static final int TAG_LATITUDE = 2;
    public static final int TAG_LONGITUDE = 3;
    public static final int TAG_SPHERICAL_VIDEO_XML = 100;
    private static final int TAG_STRING_FIRST = 1;
    private static final int TAG_STRING_LAST = 4;
    private static final int[] TIME_BOXES = {IsoInterface.BOX_MVHD, IsoInterface.BOX_TKHD, IsoInterface.BOX_MDHD};
    private static final int UUID_EXTENDED_TYPE_SIZE = 16;
    private Vector mAttribute;
    private String mFileName;
    private boolean mHasExif;
    private int mMimeType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MetaTag {
        public final int keyCode;
        public final byte[] valueBytes;
        public final String valueStr;

        public /* synthetic */ MetaTag(int i2, int i7, byte[] bArr) {
            this(i2, bArr);
        }

        public /* synthetic */ MetaTag(int i2, String str, int i7) {
            this(i2, str);
        }

        private MetaTag(int i2, String str) {
            this.keyCode = i2;
            this.valueStr = str;
            this.valueBytes = null;
        }

        private MetaTag(int i2, byte[] bArr) {
            this.keyCode = i2;
            this.valueStr = null;
            this.valueBytes = bArr;
        }
    }

    public ISOEditor(String str) {
        if (str != null) {
            this.mFileName = str;
            this.mMimeType = 0;
            this.mHasExif = false;
            this.mAttribute = new Vector();
            MediaMetadataRetriever mediaMetadataRetriever = null;
            try {
                MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever2.setDataSource(str);
                    String extractMetadata = mediaMetadataRetriever2.extractMetadata(12);
                    if (!Encode.ContentType.VIDEO_MP4.equalsIgnoreCase(extractMetadata)) {
                        if (!"audio/mp4".equalsIgnoreCase(extractMetadata)) {
                            if (!"image/heif".equalsIgnoreCase(extractMetadata)) {
                                if ("image/avif".equalsIgnoreCase(extractMetadata)) {
                                }
                                release(mediaMetadataRetriever2);
                            }
                            this.mMimeType = 2;
                            if (mediaMetadataRetriever2.extractMetadata(33) != null) {
                                this.mHasExif = true;
                            }
                            release(mediaMetadataRetriever2);
                        }
                    }
                    this.mMimeType = 1;
                    release(mediaMetadataRetriever2);
                } catch (Throwable th) {
                    th = th;
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                    release(mediaMetadataRetriever);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                release(mediaMetadataRetriever);
                throw th;
            }
        } else {
            throw new NullPointerException("filename cannot be null");
        }
    }

    private static byte[] addnewExifData(byte[] bArr, String str, String str2, String str3) {
        String str4;
        String str5;
        String str6;
        byte[] bArr2 = bArr;
        String str7 = str;
        String str8 = str2;
        String str9 = str3;
        byte[] bArr3 = new byte[(bArr2.length + 8)];
        Log.i(TAG, "addnewExifData() current exif size : " + bArr2.length);
        System.arraycopy(new byte[]{-1, -40}, 0, bArr3, 0, 2);
        System.arraycopy(new byte[]{-1, -31}, 0, bArr3, 2, 2);
        bArr3[4] = (byte) (((bArr2.length + 2) >>> 8) & ScoverState.TYPE_NFC_SMART_COVER);
        bArr3[5] = (byte) ((bArr2.length + 2) & ScoverState.TYPE_NFC_SMART_COVER);
        System.arraycopy(bArr2, 0, bArr3, 6, bArr2.length);
        System.arraycopy(new byte[]{-1, -39}, 0, bArr3, bArr2.length + 6, 2);
        FileOutputStream fileOutputStream = null;
        try {
            File createTempFile = File.createTempFile("tempJpeg", "tmp");
            FileOutputStream fileOutputStream2 = new FileOutputStream(createTempFile);
            try {
                fileOutputStream2.write(bArr3);
                closeQuietly(fileOutputStream2);
                ExifInterface exifInterface = new ExifInterface(createTempFile);
                if (!(str7 == null || str8 == null)) {
                    if (!str7.equals(FUNC_REMOVE) || !str8.equals(FUNC_REMOVE)) {
                        Double valueOf = Double.valueOf(str7);
                        Double valueOf2 = Double.valueOf(str8);
                        String geoDegree = toGeoDegree(valueOf.doubleValue());
                        String geoDegree2 = toGeoDegree(valueOf2.doubleValue());
                        Log.i(TAG, "change exif. latitude : " + geoDegree + ", longitude" + geoDegree2);
                        exifInterface.setAttribute("GPSLatitude", geoDegree);
                        if (valueOf.doubleValue() > MapUtil.INVALID_LOCATION) {
                            str5 = "N";
                        } else {
                            str5 = "S";
                        }
                        exifInterface.setAttribute("GPSLatitudeRef", str5);
                        exifInterface.setAttribute("GPSLongitude", geoDegree2);
                        if (valueOf2.doubleValue() > MapUtil.INVALID_LOCATION) {
                            str6 = "E";
                        } else {
                            str6 = "W";
                        }
                        exifInterface.setAttribute("GPSLongitudeRef", str6);
                    } else {
                        exifInterface.setAttribute("GPSLatitude", (String) null);
                        exifInterface.setAttribute("GPSLatitudeRef", (String) null);
                        exifInterface.setAttribute("GPSLongitude", (String) null);
                        exifInterface.setAttribute("GPSLongitudeRef", (String) null);
                    }
                }
                if (str9 != null) {
                    if (str9.equals(FUNC_RESTORE)) {
                        str4 = exifInterface.getAttribute("DateTimeDigitized");
                    } else {
                        str4 = str9;
                    }
                    Log.i(TAG, "change exif, time : " + str4);
                    exifInterface.setAttribute("DateTime", str4);
                    exifInterface.setAttribute("DateTimeOriginal", str4);
                    String timeZoneString = getTimeZoneString(TimeZone.getDefault().getOffset(System.currentTimeMillis()));
                    Log.i(TAG, "change exif, time offset : " + timeZoneString);
                    exifInterface.setAttribute("OffsetTime", timeZoneString);
                    exifInterface.setAttribute("OffsetTimeOriginal", timeZoneString);
                }
                exifInterface.saveAttributes();
                byte[] readAllBytes = Files.readAllBytes(createTempFile.toPath());
                int length = readAllBytes.length - 8;
                byte[] bArr4 = new byte[length];
                System.arraycopy(readAllBytes, 6, bArr4, 0, length);
                createTempFile.delete();
                return bArr4;
            } catch (Exception e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    throw new IOException("Failed to write temp jpeg file", e);
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (Exception e7) {
            e = e7;
            throw new IOException("Failed to write temp jpeg file", e);
        }
    }

    private static void changeMotionPhotoInfo(String str, int i2) {
        File file = new File(str);
        byte[] data = SemExtendedFormat.getData(file, "MotionPhoto_Data");
        if (data != null && data.length >= 12 && data[0] == 109 && data[1] == 112 && data[2] == 118 && data[3] == 50) {
            long j2 = (((((((long) (data[4] & 255)) << 8) + ((long) (data[5] & 255))) << 8) + ((long) (data[6] & 255))) << 8) + ((long) (data[7] & 255)) + ((long) i2);
            data[4] = (byte) ((int) (j2 >> 24));
            data[5] = (byte) ((int) ((j2 >> 16) & 255));
            data[6] = (byte) ((int) ((j2 >> 8) & 255));
            data[7] = (byte) ((int) (j2 & 255));
            if (SemExtendedFormat.addData(file, "MotionPhoto_Data", data, 2608, 5) == 0) {
                throw new IOException("Fail to change Motion Photo SEF Data");
            }
        }
    }

    private static void changeOffsetInfo(RandomAccessFile randomAccessFile, long j2, long j3, int i2, long j8) {
        int i7;
        int i8;
        long j10;
        int i10;
        int i11;
        long j11;
        byte b;
        long j12;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        int i12 = i2;
        randomAccessFile.seek(j2);
        int readInt = randomAccessFile2.readInt() >> 24;
        int i13 = 2;
        if (readInt <= 2) {
            byte readByte = randomAccessFile2.readByte();
            byte b5 = readByte & 15;
            byte b8 = 4;
            int i14 = readByte >> 4;
            byte readByte2 = randomAccessFile2.readByte();
            short s = 1;
            if (readInt == 0 || readInt == 1) {
                byte b10 = readByte2 & 15;
                int i15 = readByte2 >> 4;
                if (readInt < 2) {
                    i7 = 2;
                } else {
                    i7 = 4;
                }
                if (i7 == 2) {
                    i8 = randomAccessFile2.readShort();
                } else {
                    i8 = randomAccessFile2.readInt();
                }
                int i16 = 0;
                long j13 = 0;
                while (i16 < i8) {
                    if (i7 == i13) {
                        randomAccessFile2.readShort();
                    } else {
                        randomAccessFile2.readInt();
                    }
                    if (readInt == s || readInt == i13) {
                        randomAccessFile2.readShort();
                    }
                    if (randomAccessFile2.readShort() == 0) {
                        if (i15 <= 0) {
                            j10 = 0;
                        } else if (i15 == b8) {
                            j10 = 0;
                            j13 = (long) randomAccessFile2.readInt();
                        } else {
                            j10 = 0;
                            j13 = randomAccessFile2.readLong();
                        }
                        short readShort = randomAccessFile2.readShort();
                        if (readShort > s) {
                            Log.w(TAG, "cannot support multi extent_count");
                        }
                        int i17 = 0;
                        while (i17 < readShort) {
                            if ((readInt == s || readInt == i13) && b10 > 0) {
                                if (b10 == b8) {
                                    randomAccessFile2.readInt();
                                } else {
                                    randomAccessFile2.readLong();
                                }
                            }
                            if (i14 > 0) {
                                i11 = i7;
                                long filePointer = randomAccessFile2.getFilePointer();
                                if (i14 == b8) {
                                    i10 = i15;
                                    j11 = (long) randomAccessFile2.readInt();
                                } else {
                                    i10 = i15;
                                    j11 = randomAccessFile2.readLong();
                                }
                                if (j13 + j11 > j8) {
                                    randomAccessFile2.seek(filePointer);
                                    if (i14 == 4) {
                                        randomAccessFile2.writeInt(((int) j11) + i12);
                                    } else {
                                        randomAccessFile2.writeLong(((long) i12) + j11);
                                    }
                                }
                            } else {
                                i10 = i15;
                                i11 = i7;
                                j11 = j10;
                            }
                            if (b5 > 0) {
                                if (j11 + 4 == j8) {
                                    j12 = randomAccessFile2.getFilePointer();
                                } else {
                                    j12 = j10;
                                }
                                b = 4;
                                if (b5 == 4) {
                                    long readInt2 = (long) randomAccessFile2.readInt();
                                    if (j12 != j10) {
                                        randomAccessFile2.seek(j12);
                                        randomAccessFile2.writeInt(((int) readInt2) + i12);
                                    }
                                } else {
                                    long readLong = randomAccessFile2.readLong();
                                    if (j12 != j10) {
                                        randomAccessFile2.seek(j12);
                                        randomAccessFile2.writeLong(readLong + ((long) i12));
                                    }
                                }
                            } else {
                                b = 4;
                            }
                            i17++;
                            b8 = b;
                            i7 = i11;
                            i15 = i10;
                            i13 = 2;
                            s = 1;
                        }
                        byte b11 = b8;
                        int i18 = i15;
                        int i19 = i7;
                        i16++;
                        i13 = 2;
                        s = 1;
                    } else {
                        throw new IOException("cannot support data_reference_index");
                    }
                }
                return;
            }
            throw new IOException("unsupported iloc box version");
        }
        throw new IOException("unsupported iloc box version");
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    private static long convertDateToTime(String str) {
        return (new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(str).getTime() / 1000) + 2082844800;
    }

    private static int copy(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[SerializeOptions.SORT];
        int i2 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return i2;
            }
            i2 += read;
            outputStream.write(bArr, 0, read);
        }
    }

    private static void deleteItunesLocationInfo(RandomAccessFile randomAccessFile, long j2, long j3) {
        randomAccessFile.seek(j2 + 4);
        randomAccessFile.writeInt(0);
        randomAccessFile.writeInt(0);
        int readInt = (int) ((long) randomAccessFile.readInt());
        byte[] bArr = new byte[readInt];
        Arrays.fill(bArr, 0, readInt, (byte) 0);
        randomAccessFile.write(bArr, 0, readInt);
    }

    private static int doubleToIntx10000(double d) {
        double d2;
        double d3 = d * 10000.0d;
        if (d3 < MapUtil.INVALID_LOCATION) {
            d2 = d3 - 0.5d;
        } else {
            d2 = d3 + 0.5d;
        }
        return (int) d2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:125:0x022b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void editCreationTimeForMP4(java.lang.String r34, java.lang.String r35) {
        /*
            r0 = r34
            r1 = r35
            java.lang.String r2 = "Restore"
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x021f, all -> 0x021c }
            r4.<init>(r0)     // Catch:{ Exception -> 0x021f, all -> 0x021c }
            java.io.FileDescriptor r5 = r4.getFD()     // Catch:{ all -> 0x01f3 }
            com.samsung.android.sdk.sgpl.media.iso.IsoInterface r5 = com.samsung.android.sdk.sgpl.media.iso.IsoInterface.fromFileDescriptor(r5)     // Catch:{ all -> 0x01f3 }
            r6 = 1969517665(0x75647461, float:2.8960062E32)
            long[] r6 = r5.getPathRangesWithHeaderSize(r6)     // Catch:{ all -> 0x01f3 }
            r7 = 1835295092(0x6d646174, float:4.4175247E27)
            long[] r7 = r5.getBoxRangesWithHeaderSize(r7)     // Catch:{ all -> 0x01f3 }
            r8 = 1836019574(0x6d6f6f76, float:4.631354E27)
            long[] r8 = r5.getBoxRangesWithHeaderSize(r8)     // Catch:{ all -> 0x01f3 }
            r9 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            long[] r9 = r5.getBoxRangesWithHeaderSize(r9)     // Catch:{ all -> 0x01f3 }
            r10 = 1836476516(0x6d766864, float:4.7662196E27)
            long[] r10 = r5.getBoxRangesWithHeaderSize(r10)     // Catch:{ all -> 0x01f3 }
            r11 = 1936679796(0x736f6374, float:1.896631E31)
            long[] r5 = r5.getBoxRangesWithHeaderSize(r11)     // Catch:{ all -> 0x01f3 }
            int r9 = r9.length     // Catch:{ all -> 0x01f3 }
            if (r9 > 0) goto L_0x0205
            int r9 = r8.length     // Catch:{ all -> 0x01f3 }
            if (r9 < 0) goto L_0x01f9
            int r9 = r8.length     // Catch:{ all -> 0x01f3 }
            r11 = 2
            if (r9 > r11) goto L_0x01f9
            int r9 = r6.length     // Catch:{ all -> 0x01f3 }
            r11 = 1
            r12 = 0
            if (r9 <= 0) goto L_0x004f
            r16 = r11
            goto L_0x0051
        L_0x004f:
            r16 = r12
        L_0x0051:
            java.io.RandomAccessFile r13 = new java.io.RandomAccessFile     // Catch:{ all -> 0x01f3 }
            java.lang.String r9 = "rw"
            r13.<init>(r0, r9)     // Catch:{ all -> 0x01f3 }
            boolean r0 = r1.equals(r2)     // Catch:{ all -> 0x0076 }
            r14 = 8
            if (r0 == 0) goto L_0x0084
            int r0 = r5.length     // Catch:{ all -> 0x0076 }
            if (r0 == 0) goto L_0x007a
            r17 = r5[r12]     // Catch:{ all -> 0x0076 }
            r19 = r4
            long r3 = r17 + r14
            r13.seek(r3)     // Catch:{ all -> 0x0071 }
            long r3 = r13.readLong()     // Catch:{ all -> 0x0071 }
            goto L_0x008a
        L_0x0071:
            r0 = move-exception
        L_0x0072:
            r1 = r0
            r3 = r13
            goto L_0x020f
        L_0x0076:
            r0 = move-exception
            r19 = r4
            goto L_0x0072
        L_0x007a:
            r19 = r4
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0071 }
            java.lang.String r1 = "cannot support. This file has no original creation time"
            r0.<init>(r1)     // Catch:{ all -> 0x0071 }
            throw r0     // Catch:{ all -> 0x0071 }
        L_0x0084:
            r19 = r4
            long r3 = convertDateToTime(r1)     // Catch:{ all -> 0x0071 }
        L_0x008a:
            int r0 = r5.length     // Catch:{ all -> 0x0071 }
            java.lang.String r9 = "ISOEditor"
            r17 = r14
            r20 = 4
            if (r0 != 0) goto L_0x0176
            r0 = r12
            r1 = r0
        L_0x0095:
            int r2 = r7.length     // Catch:{ all -> 0x0071 }
            if (r0 >= r2) goto L_0x00a4
            r22 = r7[r0]     // Catch:{ all -> 0x0071 }
            r24 = r8[r12]     // Catch:{ all -> 0x0071 }
            int r2 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r2 <= 0) goto L_0x00a1
            r1 = r11
        L_0x00a1:
            int r0 = r0 + 2
            goto L_0x0095
        L_0x00a4:
            if (r1 == 0) goto L_0x0135
            int r0 = r7.length     // Catch:{ all -> 0x0071 }
            int r0 = r0 - r11
            r0 = r7[r0]     // Catch:{ all -> 0x0071 }
            long r22 = r13.length()     // Catch:{ all -> 0x0071 }
            r24 = 0
            long r14 = r22 - r0
            r22 = r8[r11]     // Catch:{ all -> 0x0071 }
            r26 = r8[r12]     // Catch:{ all -> 0x0071 }
            r28 = r11
            r29 = r12
            long r11 = r22 - r26
            long r22 = r0 - r26
            long r26 = r14 + r11
            r30 = 67108864(0x4000000, double:3.31561842E-316)
            int r2 = (r26 > r30 ? 1 : (r26 == r30 ? 0 : -1))
            if (r2 > 0) goto L_0x0111
            r26 = r6
            r5 = r7[r29]     // Catch:{ all -> 0x0071 }
            r27 = r8
            r7 = r7[r28]     // Catch:{ all -> 0x0071 }
            updateMdatBox(r13, r5, r7)     // Catch:{ all -> 0x0071 }
            int r2 = (r14 > r24 ? 1 : (r14 == r24 ? 0 : -1))
            if (r2 <= 0) goto L_0x00e2
            int r5 = (int) r14     // Catch:{ all -> 0x0071 }
            byte[] r6 = new byte[r5]     // Catch:{ all -> 0x0071 }
            r13.seek(r0)     // Catch:{ all -> 0x0071 }
            r7 = r29
            r13.read(r6, r7, r5)     // Catch:{ all -> 0x0071 }
            goto L_0x00e3
        L_0x00e2:
            r6 = 0
        L_0x00e3:
            int r5 = (int) r11     // Catch:{ all -> 0x0071 }
            byte[] r7 = new byte[r5]     // Catch:{ all -> 0x0071 }
            r8 = 0
            r11 = r27[r8]     // Catch:{ all -> 0x0071 }
            r13.seek(r11)     // Catch:{ all -> 0x0071 }
            r13.read(r7, r8, r5)     // Catch:{ all -> 0x0071 }
            r13.seek(r0)     // Catch:{ all -> 0x0071 }
            r13.write(r7, r8, r5)     // Catch:{ all -> 0x0071 }
            if (r2 <= 0) goto L_0x00fb
            int r0 = (int) r14     // Catch:{ all -> 0x0071 }
            r13.write(r6, r8, r0)     // Catch:{ all -> 0x0071 }
        L_0x00fb:
            r0 = r27[r8]     // Catch:{ all -> 0x0071 }
            long r0 = r0 + r20
            r13.seek(r0)     // Catch:{ all -> 0x0071 }
            r0 = 1718773093(0x66726565, float:2.8617077E23)
            r13.writeInt(r0)     // Catch:{ all -> 0x0071 }
            java.util.Arrays.fill(r7, r8, r5, r8)     // Catch:{ all -> 0x0071 }
            int r5 = r5 + -8
            r13.write(r7, r8, r5)     // Catch:{ all -> 0x0071 }
            goto L_0x013f
        L_0x0111:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0071 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r1.<init>()     // Catch:{ all -> 0x0071 }
            java.lang.String r2 = "Movie header or extra data size is too big. moov("
            r1.append(r2)     // Catch:{ all -> 0x0071 }
            r1.append(r11)     // Catch:{ all -> 0x0071 }
            java.lang.String r2 = "), extra data("
            r1.append(r2)     // Catch:{ all -> 0x0071 }
            r1.append(r14)     // Catch:{ all -> 0x0071 }
            java.lang.String r2 = ")"
            r1.append(r2)     // Catch:{ all -> 0x0071 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0071 }
            r0.<init>(r1)     // Catch:{ all -> 0x0071 }
            throw r0     // Catch:{ all -> 0x0071 }
        L_0x0135:
            r26 = r6
            r27 = r8
            r28 = r11
            r24 = 0
            r22 = r24
        L_0x013f:
            if (r16 == 0) goto L_0x0147
            r0 = 16
            r1 = r0
            r0 = r26
            goto L_0x014c
        L_0x0147:
            r0 = 24
            r1 = r0
            r0 = r27
        L_0x014c:
            int r2 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
            if (r2 <= 0) goto L_0x015d
            r2 = 0
        L_0x0151:
            int r5 = r0.length     // Catch:{ all -> 0x0071 }
            if (r2 >= r5) goto L_0x015d
            r5 = r0[r2]     // Catch:{ all -> 0x0071 }
            long r5 = r5 + r22
            r0[r2] = r5     // Catch:{ all -> 0x0071 }
            int r2 = r2 + 1
            goto L_0x0151
        L_0x015d:
            int r2 = r0.length     // Catch:{ all -> 0x0071 }
            r5 = r28
            if (r2 <= r5) goto L_0x0164
            r2 = 1
            goto L_0x016a
        L_0x0164:
            java.lang.String r2 = "The file is abnormal. no moov box"
            android.util.Log.w(r9, r2)     // Catch:{ all -> 0x0071 }
            r2 = 0
        L_0x016a:
            r7 = r2
            r14 = r22
            r32 = r17
            r18 = r1
            r1 = r32
        L_0x0173:
            r29 = 0
            goto L_0x019d
        L_0x0176:
            r24 = 0
            boolean r0 = r1.equals(r2)     // Catch:{ all -> 0x0071 }
            if (r0 == 0) goto L_0x0193
            r29 = 0
            r0 = r5[r29]     // Catch:{ all -> 0x0071 }
            long r0 = r0 + r20
            r13.seek(r0)     // Catch:{ all -> 0x0071 }
            r0 = 1718773093(0x66726565, float:2.8617077E23)
            r13.writeInt(r0)     // Catch:{ all -> 0x0071 }
            r0 = r24
            r13.writeLong(r0)     // Catch:{ all -> 0x0071 }
            goto L_0x0195
        L_0x0193:
            r0 = r24
        L_0x0195:
            r14 = r0
            r1 = r17
            r0 = 0
            r7 = 0
            r18 = 0
            goto L_0x0173
        L_0x019d:
            r5 = r10[r29]     // Catch:{ all -> 0x0071 }
            long r5 = r5 + r20
            long r5 = r5 + r14
            long r10 = r5 + r20
            r13.seek(r10)     // Catch:{ all -> 0x0071 }
            int r8 = r13.read()     // Catch:{ all -> 0x0071 }
            long r5 = r5 + r1
            r13.seek(r5)     // Catch:{ all -> 0x0071 }
            r1 = 1
            if (r8 != r1) goto L_0x01c1
            long r1 = r13.readLong()     // Catch:{ all -> 0x0071 }
            r13.seek(r5)     // Catch:{ all -> 0x0071 }
            r13.writeLong(r3)     // Catch:{ all -> 0x0071 }
            r13.writeLong(r3)     // Catch:{ all -> 0x0071 }
        L_0x01bf:
            r14 = r1
            goto L_0x01d7
        L_0x01c1:
            int r1 = r13.readInt()     // Catch:{ all -> 0x0071 }
            long r1 = (long) r1     // Catch:{ all -> 0x0071 }
            r10 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r1 = r1 & r10
            r13.seek(r5)     // Catch:{ all -> 0x0071 }
            int r3 = (int) r3     // Catch:{ all -> 0x0071 }
            r13.writeInt(r3)     // Catch:{ all -> 0x0071 }
            r13.writeInt(r3)     // Catch:{ all -> 0x0071 }
            goto L_0x01bf
        L_0x01d7:
            if (r7 == 0) goto L_0x01e6
            if (r0 == 0) goto L_0x01e6
            r17 = r0
            updateSoctBox(r13, r14, r16, r17, r18)     // Catch:{ Exception -> 0x01e1 }
            goto L_0x01e6
        L_0x01e1:
            java.lang.String r0 = "failed to update soct box"
            android.util.Log.w(r9, r0)     // Catch:{ all -> 0x0071 }
        L_0x01e6:
            r19.close()     // Catch:{ Exception -> 0x01f0, all -> 0x01ed }
            r13.close()
            return
        L_0x01ed:
            r0 = move-exception
            r3 = r13
            goto L_0x0229
        L_0x01f0:
            r0 = move-exception
            r3 = r13
            goto L_0x0221
        L_0x01f3:
            r0 = move-exception
            r19 = r4
        L_0x01f6:
            r1 = r0
            r3 = 0
            goto L_0x020f
        L_0x01f9:
            r19 = r4
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0203 }
            java.lang.String r1 = "moov must be 1. the file is abnormal"
            r0.<init>(r1)     // Catch:{ all -> 0x0203 }
            throw r0     // Catch:{ all -> 0x0203 }
        L_0x0203:
            r0 = move-exception
            goto L_0x01f6
        L_0x0205:
            r19 = r4
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0203 }
            java.lang.String r1 = "cannot support. The file has moof box"
            r0.<init>(r1)     // Catch:{ all -> 0x0203 }
            throw r0     // Catch:{ all -> 0x0203 }
        L_0x020f:
            r19.close()     // Catch:{ all -> 0x0213 }
            goto L_0x0217
        L_0x0213:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ Exception -> 0x021a }
        L_0x0217:
            throw r1     // Catch:{ Exception -> 0x021a }
        L_0x0218:
            r0 = move-exception
            goto L_0x0229
        L_0x021a:
            r0 = move-exception
            goto L_0x0221
        L_0x021c:
            r0 = move-exception
            r3 = 0
            goto L_0x0229
        L_0x021f:
            r0 = move-exception
            r3 = 0
        L_0x0221:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x0218 }
            java.lang.String r2 = "failed to edit creation time"
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0218 }
            throw r1     // Catch:{ all -> 0x0218 }
        L_0x0229:
            if (r3 == 0) goto L_0x022e
            r3.close()
        L_0x022e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.editCreationTimeForMP4(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:127:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x018f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void editDeivceNameForMP4(java.lang.String r27, java.lang.String r28) {
        /*
            r0 = r27
            java.lang.String r1 = "ISOEditor"
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x01c3, all -> 0x01c0 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x01c3, all -> 0x01c0 }
            java.io.FileDescriptor r4 = r3.getFD()     // Catch:{ all -> 0x019b }
            com.samsung.android.sdk.sgpl.media.iso.IsoInterface r4 = com.samsung.android.sdk.sgpl.media.iso.IsoInterface.fromFileDescriptor(r4)     // Catch:{ all -> 0x019b }
            r5 = 1635087464(0x61757468, float:2.8299002E20)
            long[] r5 = r4.getPathRangesWithHeaderSize(r5)     // Catch:{ all -> 0x019b }
            r6 = 1969517665(0x75647461, float:2.8960062E32)
            long[] r6 = r4.getPathRangesWithHeaderSize(r6)     // Catch:{ all -> 0x019b }
            r7 = 1835295092(0x6d646174, float:4.4175247E27)
            long[] r7 = r4.getBoxRangesWithHeaderSize(r7)     // Catch:{ all -> 0x019b }
            r8 = 1836019574(0x6d6f6f76, float:4.631354E27)
            long[] r8 = r4.getBoxRangesWithHeaderSize(r8)     // Catch:{ all -> 0x019b }
            r9 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            long[] r4 = r4.getBoxRangesWithHeaderSize(r9)     // Catch:{ all -> 0x019b }
            int r4 = r4.length     // Catch:{ all -> 0x019b }
            if (r4 > 0) goto L_0x01ad
            int r4 = r8.length     // Catch:{ all -> 0x019b }
            if (r4 < 0) goto L_0x01a1
            int r4 = r8.length     // Catch:{ all -> 0x019b }
            r9 = 2
            if (r4 > r9) goto L_0x01a1
            int r4 = r5.length     // Catch:{ all -> 0x019b }
            r9 = 1
            r10 = 0
            if (r4 <= 0) goto L_0x0045
            r13 = r9
            goto L_0x0046
        L_0x0045:
            r13 = r10
        L_0x0046:
            int r4 = r6.length     // Catch:{ all -> 0x019b }
            if (r4 <= 0) goto L_0x004b
            r14 = r9
            goto L_0x004c
        L_0x004b:
            r14 = r10
        L_0x004c:
            r4 = r10
            r11 = r4
        L_0x004e:
            int r12 = r7.length     // Catch:{ all -> 0x019b }
            if (r4 >= r12) goto L_0x0064
            r15 = r7[r4]     // Catch:{ all -> 0x005d }
            r17 = r8[r10]     // Catch:{ all -> 0x005d }
            int r12 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r12 <= 0) goto L_0x005a
            r11 = r9
        L_0x005a:
            int r4 = r4 + 2
            goto L_0x004e
        L_0x005d:
            r0 = move-exception
            r2 = r0
            r22 = r3
        L_0x0061:
            r15 = 0
            goto L_0x01b7
        L_0x0064:
            int r4 = r28.length()     // Catch:{ all -> 0x019b }
            int r12 = r4 + 15
            java.io.RandomAccessFile r15 = new java.io.RandomAccessFile     // Catch:{ all -> 0x019b }
            java.lang.String r2 = "rw"
            r15.<init>(r0, r2)     // Catch:{ all -> 0x019b }
            r17 = 0
            if (r11 == 0) goto L_0x0115
            int r0 = r7.length     // Catch:{ all -> 0x0111 }
            int r0 = r0 - r9
            r2 = r9
            r11 = r10
            r9 = r7[r0]     // Catch:{ all -> 0x0111 }
            long r19 = r15.length()     // Catch:{ all -> 0x0111 }
            r27 = r2
            r22 = r3
            long r2 = r19 - r9
            r19 = r8[r11]     // Catch:{ all -> 0x00b6 }
            long r23 = r9 - r19
            r25 = r8[r27]     // Catch:{ all -> 0x00b6 }
            r21 = r11
            r0 = r12
            long r11 = r25 - r19
            long r19 = r2 + r11
            r25 = 67108864(0x4000000, double:3.31561842E-316)
            int r19 = (r19 > r25 ? 1 : (r19 == r25 ? 0 : -1))
            if (r19 > 0) goto L_0x00ed
            r20 = r4
            r19 = r5
            r4 = r7[r21]     // Catch:{ all -> 0x00b6 }
            r25 = r6
            r6 = r7[r27]     // Catch:{ all -> 0x00b6 }
            updateMdatBox(r15, r4, r6)     // Catch:{ all -> 0x00b6 }
            int r4 = (r2 > r17 ? 1 : (r2 == r17 ? 0 : -1))
            if (r4 <= 0) goto L_0x00ba
            int r5 = (int) r2     // Catch:{ all -> 0x00b6 }
            byte[] r6 = new byte[r5]     // Catch:{ all -> 0x00b6 }
            r15.seek(r9)     // Catch:{ all -> 0x00b6 }
            r7 = r21
            r15.read(r6, r7, r5)     // Catch:{ all -> 0x00b6 }
            goto L_0x00bb
        L_0x00b6:
            r0 = move-exception
        L_0x00b7:
            r2 = r0
            goto L_0x01b7
        L_0x00ba:
            r6 = 0
        L_0x00bb:
            int r5 = (int) r11     // Catch:{ all -> 0x00b6 }
            byte[] r7 = new byte[r5]     // Catch:{ all -> 0x00b6 }
            r21 = 0
            r11 = r8[r21]     // Catch:{ all -> 0x00b6 }
            r15.seek(r11)     // Catch:{ all -> 0x00b6 }
            r11 = r21
            r15.read(r7, r11, r5)     // Catch:{ all -> 0x00b6 }
            r15.seek(r9)     // Catch:{ all -> 0x00b6 }
            r15.write(r7, r11, r5)     // Catch:{ all -> 0x00b6 }
            if (r4 <= 0) goto L_0x00d6
            int r2 = (int) r2     // Catch:{ all -> 0x00b6 }
            r15.write(r6, r11, r2)     // Catch:{ all -> 0x00b6 }
        L_0x00d6:
            r2 = r8[r11]     // Catch:{ all -> 0x00b6 }
            r9 = 4
            long r2 = r2 + r9
            r15.seek(r2)     // Catch:{ all -> 0x00b6 }
            r2 = 1718773093(0x66726565, float:2.8617077E23)
            r15.writeInt(r2)     // Catch:{ all -> 0x00b6 }
            java.util.Arrays.fill(r7, r11, r5, r11)     // Catch:{ all -> 0x00b6 }
            int r5 = r5 + -8
            r15.write(r7, r11, r5)     // Catch:{ all -> 0x00b6 }
            goto L_0x0122
        L_0x00ed:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00b6 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b6 }
            r4.<init>()     // Catch:{ all -> 0x00b6 }
            java.lang.String r5 = "Movie header or extra data size is too big. moov("
            r4.append(r5)     // Catch:{ all -> 0x00b6 }
            r4.append(r11)     // Catch:{ all -> 0x00b6 }
            java.lang.String r5 = "), extra data("
            r4.append(r5)     // Catch:{ all -> 0x00b6 }
            r4.append(r2)     // Catch:{ all -> 0x00b6 }
            java.lang.String r2 = ")"
            r4.append(r2)     // Catch:{ all -> 0x00b6 }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x00b6 }
            r0.<init>(r2)     // Catch:{ all -> 0x00b6 }
            throw r0     // Catch:{ all -> 0x00b6 }
        L_0x0111:
            r0 = move-exception
            r22 = r3
            goto L_0x00b7
        L_0x0115:
            r22 = r3
            r20 = r4
            r19 = r5
            r25 = r6
            r27 = r9
            r0 = r12
            r23 = r17
        L_0x0122:
            if (r13 == 0) goto L_0x0153
            r2 = r17
            r21 = 0
            r17 = r19[r21]     // Catch:{ all -> 0x00b6 }
            r4 = r19
            r19 = r4[r27]     // Catch:{ all -> 0x00b6 }
            long r5 = (long) r0     // Catch:{ all -> 0x00b6 }
            long r9 = r19 - r17
            int r0 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x014e
            r21 = 0
            r16 = r28
            writeAuthBox(r15, r16, r17, r19, r21)     // Catch:{ all -> 0x00b6 }
            r15.close()     // Catch:{ all -> 0x00b6 }
            r22.close()     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r15.close()
            return
        L_0x0146:
            r0 = move-exception
            r2 = r15
            goto L_0x01d5
        L_0x014a:
            r0 = move-exception
            r2 = r15
            goto L_0x01c5
        L_0x014e:
            long r5 = r5 - r9
            int r12 = (int) r5
        L_0x0150:
            r16 = r12
            goto L_0x0161
        L_0x0153:
            r2 = r17
            r4 = r19
            r21 = 0
            if (r14 == 0) goto L_0x015e
            r16 = r0
            goto L_0x0161
        L_0x015e:
            int r12 = r20 + 23
            goto L_0x0150
        L_0x0161:
            if (r13 == 0) goto L_0x0165
            r5 = r4
            goto L_0x016b
        L_0x0165:
            if (r14 == 0) goto L_0x016a
            r5 = r25
            goto L_0x016b
        L_0x016a:
            r5 = r8
        L_0x016b:
            int r0 = (r23 > r2 ? 1 : (r23 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x017d
            r10 = r21
        L_0x0171:
            int r0 = r5.length     // Catch:{ all -> 0x00b6 }
            if (r10 >= r0) goto L_0x017d
            r2 = r5[r10]     // Catch:{ all -> 0x00b6 }
            long r2 = r2 + r23
            r5[r10] = r2     // Catch:{ all -> 0x00b6 }
            int r10 = r10 + 1
            goto L_0x0171
        L_0x017d:
            int r0 = r5.length     // Catch:{ all -> 0x00b6 }
            r2 = r27
            if (r0 <= r2) goto L_0x018f
            r12 = r28
            r11 = r15
            r15 = r5
            updateAuthBox(r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x018b }
            r15 = r11
            goto L_0x0194
        L_0x018b:
            r0 = move-exception
            r15 = r11
            goto L_0x00b7
        L_0x018f:
            java.lang.String r0 = "The file has no moov Box"
            android.util.Log.w(r1, r0)     // Catch:{ all -> 0x00b6 }
        L_0x0194:
            r22.close()     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
            r15.close()
            return
        L_0x019b:
            r0 = move-exception
            r22 = r3
        L_0x019e:
            r2 = r0
            goto L_0x0061
        L_0x01a1:
            r22 = r3
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01ab }
            java.lang.String r2 = "abnormal file. the number of moov box must be one"
            r0.<init>(r2)     // Catch:{ all -> 0x01ab }
            throw r0     // Catch:{ all -> 0x01ab }
        L_0x01ab:
            r0 = move-exception
            goto L_0x019e
        L_0x01ad:
            r22 = r3
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01ab }
            java.lang.String r2 = "cannot support. The file has moof box"
            r0.<init>(r2)     // Catch:{ all -> 0x01ab }
            throw r0     // Catch:{ all -> 0x01ab }
        L_0x01b7:
            r22.close()     // Catch:{ all -> 0x01bb }
            goto L_0x01bf
        L_0x01bb:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
        L_0x01bf:
            throw r2     // Catch:{ Exception -> 0x014a, all -> 0x0146 }
        L_0x01c0:
            r0 = move-exception
            r2 = 0
            goto L_0x01d5
        L_0x01c3:
            r0 = move-exception
            r2 = 0
        L_0x01c5:
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x01d4 }
            android.util.Log.e(r1, r3)     // Catch:{ all -> 0x01d4 }
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x01d4 }
            java.lang.String r3 = "failed to edit device name"
            r1.<init>(r3, r0)     // Catch:{ all -> 0x01d4 }
            throw r1     // Catch:{ all -> 0x01d4 }
        L_0x01d4:
            r0 = move-exception
        L_0x01d5:
            if (r2 == 0) goto L_0x01da
            r2.close()
        L_0x01da:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.editDeivceNameForMP4(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void editExifForHEIF(java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            android.media.MediaMetadataRetriever r0 = new android.media.MediaMetadataRetriever
            r0.<init>()
            r0.setDataSource(r6)
            r1 = 33
            java.lang.String r1 = r0.extractMetadata(r1)
            r2 = 34
            java.lang.String r0 = r0.extractMetadata(r2)
            if (r1 == 0) goto L_0x0081
            if (r0 == 0) goto L_0x0081
            int r1 = java.lang.Integer.parseInt(r1)
            int r0 = java.lang.Integer.parseInt(r0)
            byte[] r2 = new byte[r0]
            java.io.FileInputStream r3 = new java.io.FileInputStream
            r3.<init>(r6)
            long r4 = (long) r1
            r3.skip(r4)     // Catch:{ all -> 0x0077 }
            r1 = 0
            r3.read(r2, r1, r0)     // Catch:{ all -> 0x0077 }
            r3.close()
            byte[] r7 = addnewExifData(r2, r8, r9, r7)
            if (r7 == 0) goto L_0x006f
            int r8 = r7.length
            if (r8 >= r0) goto L_0x006b
            java.lang.String r8 = "ISOEditor"
            java.lang.String r9 = "edited Exif data size is smaller than original Exif data size"
            android.util.Log.i(r8, r9)
            r8 = 0
            java.io.RandomAccessFile r9 = new java.io.RandomAccessFile     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = "rw"
            r9.<init>(r6, r2)     // Catch:{ all -> 0x0064 }
            r9.seek(r4)     // Catch:{ all -> 0x0061 }
            r9.write(r7)     // Catch:{ all -> 0x0061 }
            int r6 = r7.length     // Catch:{ all -> 0x0061 }
            int r0 = r0 - r6
            byte[] r6 = new byte[r0]     // Catch:{ all -> 0x0061 }
            java.util.Arrays.fill(r6, r1, r0, r1)     // Catch:{ all -> 0x0061 }
            r9.write(r6)     // Catch:{ all -> 0x0061 }
            r9.close()     // Catch:{ all -> 0x0061 }
            r9.close()
            return
        L_0x0061:
            r6 = move-exception
            r8 = r9
            goto L_0x0065
        L_0x0064:
            r6 = move-exception
        L_0x0065:
            if (r8 == 0) goto L_0x006a
            r8.close()
        L_0x006a:
            throw r6
        L_0x006b:
            writeNewExifDataAndAdjustOffset(r6, r7, r4, r0)
            return
        L_0x006f:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r7 = "Failed to add Exif data"
            r6.<init>(r7)
            throw r6
        L_0x0077:
            r6 = move-exception
            r3.close()     // Catch:{ all -> 0x007c }
            goto L_0x0080
        L_0x007c:
            r7 = move-exception
            r6.addSuppressed(r7)
        L_0x0080:
            throw r6
        L_0x0081:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r7 = "cannot support this image file"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.editExifForHEIF(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:156:0x0230  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void editLocationForMP4(java.lang.String r31, java.lang.String r32, java.lang.String r33) {
        /*
            r0 = r31
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0223, all -> 0x0220 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0223, all -> 0x0220 }
            java.io.FileDescriptor r3 = r2.getFD()     // Catch:{ all -> 0x01f5 }
            com.samsung.android.sdk.sgpl.media.iso.IsoInterface r3 = com.samsung.android.sdk.sgpl.media.iso.IsoInterface.fromFileDescriptor(r3)     // Catch:{ all -> 0x01f5 }
            r4 = -1451722374(0xffffffffa978797a, float:-5.5172426E-14)
            long[] r4 = r3.getPathRangesWithHeaderSize(r4)     // Catch:{ all -> 0x01f5 }
            r5 = 1969517665(0x75647461, float:2.8960062E32)
            long[] r5 = r3.getPathRangesWithHeaderSize(r5)     // Catch:{ all -> 0x01f5 }
            r6 = 1768715124(0x696c7374, float:1.7865732E25)
            long[] r6 = r3.getBoxRangesWithHeaderSize(r6)     // Catch:{ all -> 0x01f5 }
            r7 = 1835295092(0x6d646174, float:4.4175247E27)
            long[] r7 = r3.getBoxRangesWithHeaderSize(r7)     // Catch:{ all -> 0x01f5 }
            r8 = 1836019574(0x6d6f6f76, float:4.631354E27)
            long[] r8 = r3.getBoxRangesWithHeaderSize(r8)     // Catch:{ all -> 0x01f5 }
            r9 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            long[] r3 = r3.getBoxRangesWithHeaderSize(r9)     // Catch:{ all -> 0x01f5 }
            int r3 = r3.length     // Catch:{ all -> 0x01f5 }
            if (r3 > 0) goto L_0x0207
            int r3 = r8.length     // Catch:{ all -> 0x01f5 }
            if (r3 < 0) goto L_0x01fb
            int r3 = r8.length     // Catch:{ all -> 0x01f5 }
            r9 = 2
            if (r3 > r9) goto L_0x01fb
            int r3 = r4.length     // Catch:{ all -> 0x01f5 }
            r9 = 1
            r10 = 0
            if (r3 <= 0) goto L_0x004a
            r3 = r9
            goto L_0x004b
        L_0x004a:
            r3 = r10
        L_0x004b:
            int r11 = r5.length     // Catch:{ all -> 0x01f5 }
            if (r11 <= 0) goto L_0x0050
            r15 = r9
            goto L_0x0051
        L_0x0050:
            r15 = r10
        L_0x0051:
            if (r3 == 0) goto L_0x007c
            r13 = r4[r10]     // Catch:{ all -> 0x0073 }
            r16 = r4[r9]     // Catch:{ all -> 0x0073 }
            r1 = r10
            r18 = 0
        L_0x005a:
            int r11 = r6.length     // Catch:{ all -> 0x0073 }
            if (r1 >= r11) goto L_0x007a
            r11 = r6[r1]     // Catch:{ all -> 0x0073 }
            int r20 = r1 + 1
            r20 = r6[r20]     // Catch:{ all -> 0x0073 }
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L_0x0070
            int r11 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r11 < 0) goto L_0x0070
            r1 = r9
            r3 = r10
        L_0x006d:
            r11 = r16
            goto L_0x0082
        L_0x0070:
            int r1 = r1 + 2
            goto L_0x005a
        L_0x0073:
            r0 = move-exception
            r1 = r0
            r28 = r2
        L_0x0077:
            r6 = 0
            goto L_0x0211
        L_0x007a:
            r1 = r10
            goto L_0x006d
        L_0x007c:
            r18 = 0
            r1 = r10
            r11 = r18
            r13 = r11
        L_0x0082:
            r17 = r9
            r6 = r10
            r16 = r6
        L_0x0087:
            int r9 = r7.length     // Catch:{ all -> 0x01f5 }
            if (r6 >= r9) goto L_0x0097
            r20 = r7[r6]     // Catch:{ all -> 0x0073 }
            r22 = r8[r10]     // Catch:{ all -> 0x0073 }
            int r9 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
            if (r9 <= 0) goto L_0x0094
            r16 = r17
        L_0x0094:
            int r6 = r6 + 2
            goto L_0x0087
        L_0x0097:
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch:{ all -> 0x01f5 }
            java.lang.String r9 = "rw"
            r6.<init>(r0, r9)     // Catch:{ all -> 0x01f5 }
            if (r1 == 0) goto L_0x00aa
            deleteItunesLocationInfo(r6, r13, r11)     // Catch:{ all -> 0x00a4 }
            goto L_0x00aa
        L_0x00a4:
            r0 = move-exception
            r1 = r0
            r28 = r2
            goto L_0x0211
        L_0x00aa:
            if (r16 == 0) goto L_0x0156
            int r0 = r7.length     // Catch:{ all -> 0x012a }
            int r0 = r0 + -1
            r0 = r7[r0]     // Catch:{ all -> 0x012a }
            long r20 = r6.length()     // Catch:{ all -> 0x012a }
            r9 = r10
            r25 = r11
            long r10 = r20 - r0
            r20 = r8[r9]     // Catch:{ all -> 0x012a }
            long r22 = r0 - r20
            r27 = r8[r17]     // Catch:{ all -> 0x012a }
            r31 = r9
            r29 = r10
            long r9 = r27 - r20
            long r11 = r29 + r9
            r20 = 67108864(0x4000000, double:3.31561842E-316)
            int r11 = (r11 > r20 ? 1 : (r11 == r20 ? 0 : -1))
            if (r11 > 0) goto L_0x012e
            r11 = r7[r31]     // Catch:{ all -> 0x012a }
            r28 = r2
            r16 = r3
            r2 = r7[r17]     // Catch:{ all -> 0x00ee }
            updateMdatBox(r6, r11, r2)     // Catch:{ all -> 0x00ee }
            int r2 = (r29 > r18 ? 1 : (r29 == r18 ? 0 : -1))
            if (r2 <= 0) goto L_0x00f2
            r11 = r29
            int r3 = (int) r11     // Catch:{ all -> 0x00ee }
            byte[] r7 = new byte[r3]     // Catch:{ all -> 0x00ee }
            r6.seek(r0)     // Catch:{ all -> 0x00ee }
            r20 = r2
            r2 = r31
            r6.read(r7, r2, r3)     // Catch:{ all -> 0x00ee }
            goto L_0x00f7
        L_0x00ee:
            r0 = move-exception
        L_0x00ef:
            r1 = r0
            goto L_0x0211
        L_0x00f2:
            r20 = r2
            r11 = r29
            r7 = 0
        L_0x00f7:
            int r3 = (int) r9     // Catch:{ all -> 0x00ee }
            byte[] r9 = new byte[r3]     // Catch:{ all -> 0x00ee }
            r10 = r3
            r31 = 0
            r2 = r8[r31]     // Catch:{ all -> 0x00ee }
            r6.seek(r2)     // Catch:{ all -> 0x00ee }
            r2 = r31
            r6.read(r9, r2, r10)     // Catch:{ all -> 0x00ee }
            r6.seek(r0)     // Catch:{ all -> 0x00ee }
            r6.write(r9, r2, r10)     // Catch:{ all -> 0x00ee }
            if (r20 <= 0) goto L_0x0113
            int r0 = (int) r11     // Catch:{ all -> 0x00ee }
            r6.write(r7, r2, r0)     // Catch:{ all -> 0x00ee }
        L_0x0113:
            r0 = r8[r2]     // Catch:{ all -> 0x00ee }
            r11 = 4
            long r0 = r0 + r11
            r6.seek(r0)     // Catch:{ all -> 0x00ee }
            r0 = 1718773093(0x66726565, float:2.8617077E23)
            r6.writeInt(r0)     // Catch:{ all -> 0x00ee }
            java.util.Arrays.fill(r9, r2, r10, r2)     // Catch:{ all -> 0x00ee }
            int r3 = r10 + -8
            r6.write(r9, r2, r3)     // Catch:{ all -> 0x00ee }
            goto L_0x015f
        L_0x012a:
            r0 = move-exception
            r28 = r2
            goto L_0x00ef
        L_0x012e:
            r28 = r2
            r11 = r29
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00ee }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ee }
            r1.<init>()     // Catch:{ all -> 0x00ee }
            java.lang.String r2 = "Movie header or extra data size is too big. moov("
            r1.append(r2)     // Catch:{ all -> 0x00ee }
            r1.append(r9)     // Catch:{ all -> 0x00ee }
            java.lang.String r2 = "), extra data("
            r1.append(r2)     // Catch:{ all -> 0x00ee }
            r1.append(r11)     // Catch:{ all -> 0x00ee }
            java.lang.String r2 = ")"
            r1.append(r2)     // Catch:{ all -> 0x00ee }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ee }
            r0.<init>(r1)     // Catch:{ all -> 0x00ee }
            throw r0     // Catch:{ all -> 0x00ee }
        L_0x0156:
            r28 = r2
            r16 = r3
            r2 = r10
            r25 = r11
            r22 = r18
        L_0x015f:
            if (r16 == 0) goto L_0x01ae
            long r11 = r25 - r13
            r0 = 30
            int r3 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r3 > 0) goto L_0x01a9
            java.lang.Double r0 = java.lang.Double.valueOf(r32)     // Catch:{ all -> 0x01a4 }
            double r0 = r0.doubleValue()     // Catch:{ all -> 0x01a4 }
            int r21 = doubleToIntx10000(r0)     // Catch:{ all -> 0x01a4 }
            java.lang.Double r0 = java.lang.Double.valueOf(r33)     // Catch:{ all -> 0x01a4 }
            double r0 = r0.doubleValue()     // Catch:{ all -> 0x01a4 }
            int r22 = doubleToIntx10000(r0)     // Catch:{ all -> 0x01a4 }
            r27 = 0
            r20 = r6
            r23 = r13
            writeXyzBox(r20, r21, r22, r23, r25, r27)     // Catch:{ all -> 0x019e }
            r20.close()     // Catch:{ all -> 0x019e }
            r28.close()     // Catch:{ Exception -> 0x0199, all -> 0x0194 }
            r20.close()
            return
        L_0x0194:
            r0 = move-exception
            r1 = r20
            goto L_0x022e
        L_0x0199:
            r0 = move-exception
            r1 = r20
            goto L_0x0225
        L_0x019e:
            r0 = move-exception
        L_0x019f:
            r1 = r0
            r6 = r20
            goto L_0x0211
        L_0x01a4:
            r0 = move-exception
            r20 = r6
            goto L_0x00ef
        L_0x01a9:
            r20 = r6
            long r0 = r0 - r11
            int r0 = (int) r0
            goto L_0x01b7
        L_0x01ae:
            r20 = r6
            if (r15 == 0) goto L_0x01b5
            r0 = 30
            goto L_0x01b7
        L_0x01b5:
            r0 = 38
        L_0x01b7:
            if (r16 == 0) goto L_0x01ba
            goto L_0x01bf
        L_0x01ba:
            if (r15 == 0) goto L_0x01be
            r4 = r5
            goto L_0x01bf
        L_0x01be:
            r4 = r8
        L_0x01bf:
            int r1 = (r22 > r18 ? 1 : (r22 == r18 ? 0 : -1))
            if (r1 <= 0) goto L_0x01d0
            r10 = r2
        L_0x01c4:
            int r1 = r4.length     // Catch:{ all -> 0x019e }
            if (r10 >= r1) goto L_0x01d0
            r1 = r4[r10]     // Catch:{ all -> 0x019e }
            long r1 = r1 + r22
            r4[r10] = r1     // Catch:{ all -> 0x019e }
            int r10 = r10 + 1
            goto L_0x01c4
        L_0x01d0:
            int r1 = r4.length     // Catch:{ all -> 0x019e }
            r2 = r17
            if (r1 <= r2) goto L_0x01e7
            r13 = r32
            r14 = r33
            r17 = r0
            r16 = r4
            r12 = r20
            updateLocationBox(r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x01e3 }
            goto L_0x01ee
        L_0x01e3:
            r0 = move-exception
            r20 = r12
            goto L_0x019f
        L_0x01e7:
            java.lang.String r0 = "ISOEditor"
            java.lang.String r1 = "The file has no moov Box"
            android.util.Log.w(r0, r1)     // Catch:{ all -> 0x019e }
        L_0x01ee:
            r28.close()     // Catch:{ Exception -> 0x0199, all -> 0x0194 }
            r20.close()
            return
        L_0x01f5:
            r0 = move-exception
            r28 = r2
        L_0x01f8:
            r1 = r0
            goto L_0x0077
        L_0x01fb:
            r28 = r2
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0205 }
            java.lang.String r1 = "abnormal file. the number of moov box must be one"
            r0.<init>(r1)     // Catch:{ all -> 0x0205 }
            throw r0     // Catch:{ all -> 0x0205 }
        L_0x0205:
            r0 = move-exception
            goto L_0x01f8
        L_0x0207:
            r28 = r2
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0205 }
            java.lang.String r1 = "cannot support. The file has moof box"
            r0.<init>(r1)     // Catch:{ all -> 0x0205 }
            throw r0     // Catch:{ all -> 0x0205 }
        L_0x0211:
            r28.close()     // Catch:{ all -> 0x0215 }
            goto L_0x0219
        L_0x0215:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ Exception -> 0x021d, all -> 0x021a }
        L_0x0219:
            throw r1     // Catch:{ Exception -> 0x021d, all -> 0x021a }
        L_0x021a:
            r0 = move-exception
            r1 = r6
            goto L_0x022e
        L_0x021d:
            r0 = move-exception
            r1 = r6
            goto L_0x0225
        L_0x0220:
            r0 = move-exception
            r1 = 0
            goto L_0x022e
        L_0x0223:
            r0 = move-exception
            r1 = 0
        L_0x0225:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x022d }
            java.lang.String r3 = "failed to edit location"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x022d }
            throw r2     // Catch:{ all -> 0x022d }
        L_0x022d:
            r0 = move-exception
        L_0x022e:
            if (r1 == 0) goto L_0x0233
            r1.close()
        L_0x0233:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.editLocationForMP4(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x01eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void editSphericalVideoXMLForMP4(java.lang.String r27, byte[] r28) {
        /*
            r0 = r27
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x01df, all -> 0x01dc }
            r3.<init>(r0)     // Catch:{ Exception -> 0x01df, all -> 0x01dc }
            java.io.FileDescriptor r4 = r3.getFD()     // Catch:{ all -> 0x01a8 }
            com.samsung.android.sdk.sgpl.media.iso.IsoInterface r4 = com.samsung.android.sdk.sgpl.media.iso.IsoInterface.fromFileDescriptor(r4)     // Catch:{ all -> 0x01a8 }
            r5 = 1986618469(0x76696465, float:1.1834389E33)
            long[] r5 = r4.getTrackBoxRangesWithHeaderSize(r5)     // Catch:{ all -> 0x01a8 }
            int r6 = r5.length     // Catch:{ all -> 0x01a8 }
            r7 = 1
            if (r6 < r7) goto L_0x01c4
            r6 = 1835295092(0x6d646174, float:4.4175247E27)
            long[] r6 = r4.getBoxRangesWithHeaderSize(r6)     // Catch:{ all -> 0x01a8 }
            r8 = 1836019574(0x6d6f6f76, float:4.631354E27)
            long[] r8 = r4.getBoxRangesWithHeaderSize(r8)     // Catch:{ all -> 0x01a8 }
            r9 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            long[] r4 = r4.getBoxRangesWithHeaderSize(r9)     // Catch:{ all -> 0x01a8 }
            int r4 = r4.length     // Catch:{ all -> 0x01a8 }
            if (r4 > 0) goto L_0x01ba
            int r4 = r8.length     // Catch:{ all -> 0x01a8 }
            if (r4 < 0) goto L_0x01ae
            int r4 = r8.length     // Catch:{ all -> 0x01a8 }
            r9 = 2
            if (r4 > r9) goto L_0x01ae
            r4 = 0
            r10 = r4
            r11 = r10
        L_0x003c:
            int r12 = r6.length     // Catch:{ all -> 0x01a8 }
            if (r10 >= r12) goto L_0x0052
            r12 = r6[r10]     // Catch:{ all -> 0x004b }
            r14 = r8[r4]     // Catch:{ all -> 0x004b }
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 <= 0) goto L_0x0048
            r11 = r7
        L_0x0048:
            int r10 = r10 + 2
            goto L_0x003c
        L_0x004b:
            r0 = move-exception
            r1 = r0
            r16 = r3
        L_0x004f:
            r2 = 0
            goto L_0x01cf
        L_0x0052:
            java.io.RandomAccessFile r10 = new java.io.RandomAccessFile     // Catch:{ all -> 0x01a8 }
            java.lang.String r12 = "rw"
            r10.<init>(r0, r12)     // Catch:{ all -> 0x01a8 }
            if (r11 == 0) goto L_0x00ef
            int r0 = r6.length     // Catch:{ all -> 0x00eb }
            int r0 = r0 - r7
            r16 = r3
            r2 = r6[r0]     // Catch:{ all -> 0x0093 }
            long r17 = r10.length()     // Catch:{ all -> 0x0093 }
            r19 = 67108864(0x4000000, double:3.31561842E-316)
            long r11 = r17 - r2
            r17 = r8[r4]     // Catch:{ all -> 0x0093 }
            long r21 = r2 - r17
            r23 = r8[r7]     // Catch:{ all -> 0x0093 }
            r25 = 0
            long r14 = r23 - r17
            long r17 = r11 + r14
            int r0 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r0 > 0) goto L_0x00c7
            r0 = r7
            r13 = r8
            r7 = r6[r4]     // Catch:{ all -> 0x0093 }
            r17 = r0
            r0 = r6[r17]     // Catch:{ all -> 0x0093 }
            updateMdatBox(r10, r7, r0)     // Catch:{ all -> 0x0093 }
            int r0 = (r11 > r25 ? 1 : (r11 == r25 ? 0 : -1))
            if (r0 <= 0) goto L_0x0098
            int r1 = (int) r11     // Catch:{ all -> 0x0093 }
            byte[] r6 = new byte[r1]     // Catch:{ all -> 0x0093 }
            r10.seek(r2)     // Catch:{ all -> 0x0093 }
            r10.read(r6, r4, r1)     // Catch:{ all -> 0x0093 }
            goto L_0x0099
        L_0x0093:
            r0 = move-exception
        L_0x0094:
            r1 = r0
            r2 = r10
            goto L_0x01cf
        L_0x0098:
            r6 = 0
        L_0x0099:
            int r1 = (int) r14     // Catch:{ all -> 0x0093 }
            byte[] r7 = new byte[r1]     // Catch:{ all -> 0x0093 }
            r14 = r13[r4]     // Catch:{ all -> 0x0093 }
            r10.seek(r14)     // Catch:{ all -> 0x0093 }
            r10.read(r7, r4, r1)     // Catch:{ all -> 0x0093 }
            r10.seek(r2)     // Catch:{ all -> 0x0093 }
            r10.write(r7, r4, r1)     // Catch:{ all -> 0x0093 }
            if (r0 <= 0) goto L_0x00b0
            int r0 = (int) r11     // Catch:{ all -> 0x0093 }
            r10.write(r6, r4, r0)     // Catch:{ all -> 0x0093 }
        L_0x00b0:
            r2 = r13[r4]     // Catch:{ all -> 0x0093 }
            r11 = 4
            long r2 = r2 + r11
            r10.seek(r2)     // Catch:{ all -> 0x0093 }
            r0 = 1718773093(0x66726565, float:2.8617077E23)
            r10.writeInt(r0)     // Catch:{ all -> 0x0093 }
            java.util.Arrays.fill(r7, r4, r1, r4)     // Catch:{ all -> 0x0093 }
            int r1 = r1 + -8
            r10.write(r7, r4, r1)     // Catch:{ all -> 0x0093 }
            goto L_0x00fb
        L_0x00c7:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r1.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "Movie header or extra data size is too big. moov("
            r1.append(r2)     // Catch:{ all -> 0x0093 }
            r1.append(r14)     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "), extra data("
            r1.append(r2)     // Catch:{ all -> 0x0093 }
            r1.append(r11)     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = ")"
            r1.append(r2)     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0093 }
            r0.<init>(r1)     // Catch:{ all -> 0x0093 }
            throw r0     // Catch:{ all -> 0x0093 }
        L_0x00eb:
            r0 = move-exception
            r16 = r3
            goto L_0x0094
        L_0x00ef:
            r16 = r3
            r17 = r7
            r13 = r8
            r19 = 67108864(0x4000000, double:3.31561842E-316)
            r25 = 0
            r21 = r25
        L_0x00fb:
            int r0 = r5.length     // Catch:{ all -> 0x0093 }
            int r1 = r13.length     // Catch:{ all -> 0x0093 }
            int r0 = r0 + r1
            long[] r1 = new long[r0]     // Catch:{ all -> 0x0093 }
            r2 = r5[r4]     // Catch:{ all -> 0x0093 }
            r1[r4] = r2     // Catch:{ all -> 0x0093 }
            r2 = r5[r17]     // Catch:{ all -> 0x0093 }
            r1[r17] = r2     // Catch:{ all -> 0x0093 }
            r2 = r13[r4]     // Catch:{ all -> 0x0093 }
            r1[r9] = r2     // Catch:{ all -> 0x0093 }
            r2 = r13[r17]     // Catch:{ all -> 0x0093 }
            r5 = 3
            r1[r5] = r2     // Catch:{ all -> 0x0093 }
            int r2 = (r21 > r25 ? 1 : (r21 == r25 ? 0 : -1))
            if (r2 <= 0) goto L_0x0121
            r2 = r4
        L_0x0116:
            if (r2 >= r0) goto L_0x0121
            r5 = r1[r2]     // Catch:{ all -> 0x0093 }
            long r5 = r5 + r21
            r1[r2] = r5     // Catch:{ all -> 0x0093 }
            int r2 = r2 + 1
            goto L_0x0116
        L_0x0121:
            r2 = r1[r17]     // Catch:{ all -> 0x0093 }
            long r5 = r10.length()     // Catch:{ all -> 0x0093 }
            long r5 = r5 - r2
            int r7 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r7 > 0) goto L_0x0191
            int r7 = (r5 > r25 ? 1 : (r5 == r25 ? 0 : -1))
            if (r7 <= 0) goto L_0x013c
            int r7 = (int) r5     // Catch:{ all -> 0x0093 }
            byte[] r8 = new byte[r7]     // Catch:{ all -> 0x0093 }
            r10.seek(r2)     // Catch:{ all -> 0x0093 }
            r10.read(r8, r4, r7)     // Catch:{ all -> 0x0093 }
        L_0x0139:
            r7 = r28
            goto L_0x013e
        L_0x013c:
            r8 = 0
            goto L_0x0139
        L_0x013e:
            int r9 = r7.length     // Catch:{ all -> 0x0093 }
            int r9 = r9 + 24
            writeUuidBox(r10, r7, r2)     // Catch:{ all -> 0x0093 }
            r7 = r4
        L_0x0145:
            if (r7 >= r0) goto L_0x0179
            r11 = r1[r7]     // Catch:{ all -> 0x0093 }
            r10.seek(r11)     // Catch:{ all -> 0x0093 }
            int r11 = r7 + 1
            r11 = r1[r11]     // Catch:{ all -> 0x0093 }
            r13 = r1[r7]     // Catch:{ all -> 0x0093 }
            long r11 = r11 - r13
            r13 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r13 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r13 > 0) goto L_0x0162
            int r11 = (int) r11     // Catch:{ all -> 0x0093 }
            int r11 = r11 + r9
            r10.writeInt(r11)     // Catch:{ all -> 0x0093 }
            int r7 = r7 + 2
            goto L_0x0145
        L_0x0162:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r1.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "file has too big udta box. size : "
            r1.append(r2)     // Catch:{ all -> 0x0093 }
            r1.append(r5)     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0093 }
            r0.<init>(r1)     // Catch:{ all -> 0x0093 }
            throw r0     // Catch:{ all -> 0x0093 }
        L_0x0179:
            if (r8 == 0) goto L_0x0184
            long r0 = (long) r9     // Catch:{ all -> 0x0093 }
            long r2 = r2 + r0
            r10.seek(r2)     // Catch:{ all -> 0x0093 }
            int r0 = (int) r5     // Catch:{ all -> 0x0093 }
            r10.write(r8, r4, r0)     // Catch:{ all -> 0x0093 }
        L_0x0184:
            r16.close()     // Catch:{ Exception -> 0x018e, all -> 0x018b }
            r10.close()
            return
        L_0x018b:
            r0 = move-exception
            r2 = r10
            goto L_0x01e9
        L_0x018e:
            r0 = move-exception
            r2 = r10
            goto L_0x01e1
        L_0x0191:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r1.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "file has too big data except mdat. size : "
            r1.append(r2)     // Catch:{ all -> 0x0093 }
            r1.append(r5)     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0093 }
            r0.<init>(r1)     // Catch:{ all -> 0x0093 }
            throw r0     // Catch:{ all -> 0x0093 }
        L_0x01a8:
            r0 = move-exception
            r16 = r3
        L_0x01ab:
            r1 = r0
            goto L_0x004f
        L_0x01ae:
            r16 = r3
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = "abnormal file. the number of moov box must be one"
            r0.<init>(r1)     // Catch:{ all -> 0x01b8 }
            throw r0     // Catch:{ all -> 0x01b8 }
        L_0x01b8:
            r0 = move-exception
            goto L_0x01ab
        L_0x01ba:
            r16 = r3
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = "cannot support. The file has moof box"
            r0.<init>(r1)     // Catch:{ all -> 0x01b8 }
            throw r0     // Catch:{ all -> 0x01b8 }
        L_0x01c4:
            r16 = r3
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = "video track not found"
            r0.<init>(r1)     // Catch:{ all -> 0x01b8 }
            throw r0     // Catch:{ all -> 0x01b8 }
        L_0x01cf:
            r16.close()     // Catch:{ all -> 0x01d3 }
            goto L_0x01d7
        L_0x01d3:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ Exception -> 0x01da }
        L_0x01d7:
            throw r1     // Catch:{ Exception -> 0x01da }
        L_0x01d8:
            r0 = move-exception
            goto L_0x01e9
        L_0x01da:
            r0 = move-exception
            goto L_0x01e1
        L_0x01dc:
            r0 = move-exception
            r2 = 0
            goto L_0x01e9
        L_0x01df:
            r0 = move-exception
            r2 = 0
        L_0x01e1:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x01d8 }
            java.lang.String r3 = "failed to edit location"
            r1.<init>(r3, r0)     // Catch:{ all -> 0x01d8 }
            throw r1     // Catch:{ all -> 0x01d8 }
        L_0x01e9:
            if (r2 == 0) goto L_0x01ee
            r2.close()
        L_0x01ee:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.editSphericalVideoXMLForMP4(java.lang.String, byte[]):void");
    }

    private static String getTimeZoneString(int i2) {
        boolean z;
        String str;
        Object obj;
        Object obj2;
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            i2 = -i2;
        }
        int i7 = i2 / 1000;
        int i8 = i7 / 3600;
        int i10 = (i7 - (i8 * 3600)) / 60;
        StringBuilder sb2 = new StringBuilder();
        if (z) {
            str = "+";
        } else {
            str = "-";
        }
        sb2.append(str);
        if (i8 > 9) {
            obj = Integer.valueOf(i8);
        } else {
            obj = C0086a.i(i8, "0");
        }
        sb2.append(obj);
        sb2.append(NumericEnum.SEP);
        if (i10 > 9) {
            obj2 = Integer.valueOf(i10);
        } else {
            obj2 = C0086a.i(i10, "0");
        }
        sb2.append(obj2);
        return sb2.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean hasVideoTrack(java.lang.String r5) {
        /*
            java.lang.String r0 = "ISOEditor"
            java.lang.String r1 = "video track range : "
            r2 = 0
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0044 }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0044 }
            java.io.FileDescriptor r5 = r4.getFD()     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            com.samsung.android.sdk.sgpl.media.iso.IsoInterface r5 = com.samsung.android.sdk.sgpl.media.iso.IsoInterface.fromFileDescriptor(r5)     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            r3 = 1986618469(0x76696465, float:1.1834389E33)
            long[] r5 = r5.getTrackBoxRangesWithHeaderSize(r3)     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            r3.<init>(r1)     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            int r1 = r5.length     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            r3.append(r1)     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            java.lang.String r1 = r3.toString()     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            android.util.Log.w(r0, r1)     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            int r5 = r5.length     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            r1 = 1
            if (r5 >= r1) goto L_0x003e
            java.lang.String r5 = "video track not found"
            android.util.Log.w(r0, r5)     // Catch:{ FileNotFoundException -> 0x003c, all -> 0x0039 }
            r4.close()
            return r2
        L_0x0039:
            r5 = move-exception
            r3 = r4
            goto L_0x004f
        L_0x003c:
            r3 = r4
            goto L_0x0044
        L_0x003e:
            r4.close()
            return r1
        L_0x0042:
            r5 = move-exception
            goto L_0x004f
        L_0x0044:
            java.lang.String r5 = "file not found"
            android.util.Log.w(r0, r5)     // Catch:{ all -> 0x0042 }
            if (r3 == 0) goto L_0x004e
            r3.close()
        L_0x004e:
            return r2
        L_0x004f:
            if (r3 == 0) goto L_0x0054
            r3.close()
        L_0x0054:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.hasVideoTrack(java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isEditableMP4(java.lang.String r5) {
        /*
            java.lang.String r0 = "ISOEditor"
            r1 = 0
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x003f }
            r3.<init>(r5)     // Catch:{ FileNotFoundException -> 0x003f }
            java.io.FileDescriptor r5 = r3.getFD()     // Catch:{ FileNotFoundException -> 0x002f, all -> 0x002c }
            com.samsung.android.sdk.sgpl.media.iso.IsoInterface r5 = com.samsung.android.sdk.sgpl.media.iso.IsoInterface.fromFileDescriptor(r5)     // Catch:{ FileNotFoundException -> 0x002f, all -> 0x002c }
            r2 = 1836019558(0x6d6f6f66, float:4.6313494E27)
            long[] r2 = r5.getBoxRanges((int) r2)     // Catch:{ FileNotFoundException -> 0x002f, all -> 0x002c }
            r4 = 1836019574(0x6d6f6f76, float:4.631354E27)
            long[] r5 = r5.getBoxRanges((int) r4)     // Catch:{ FileNotFoundException -> 0x002f, all -> 0x002c }
            int r5 = r5.length     // Catch:{ FileNotFoundException -> 0x002f, all -> 0x002c }
            r4 = 2
            if (r5 == r4) goto L_0x0031
            java.lang.String r5 = "moov is not one"
            android.util.Log.w(r0, r5)     // Catch:{ FileNotFoundException -> 0x002f, all -> 0x002c }
            r3.close()
            return r1
        L_0x002c:
            r5 = move-exception
            r2 = r3
            goto L_0x004a
        L_0x002f:
            r2 = r3
            goto L_0x003f
        L_0x0031:
            int r5 = r2.length     // Catch:{ FileNotFoundException -> 0x002f, all -> 0x002c }
            if (r5 != 0) goto L_0x0039
            r3.close()
            r5 = 1
            return r5
        L_0x0039:
            r3.close()
            goto L_0x0049
        L_0x003d:
            r5 = move-exception
            goto L_0x004a
        L_0x003f:
            java.lang.String r5 = "file not found"
            android.util.Log.w(r0, r5)     // Catch:{ all -> 0x003d }
            if (r2 == 0) goto L_0x0049
            r2.close()
        L_0x0049:
            return r1
        L_0x004a:
            if (r2 == 0) goto L_0x004f
            r2.close()
        L_0x004f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.isEditableMP4(java.lang.String):boolean");
    }

    private boolean isRemovableAtrribute(int i2) {
        if (this.mMimeType == 2 && ((i2 == 1 || i2 == 2 || i2 == 3) && this.mHasExif)) {
            return true;
        }
        StringBuilder o2 = C0086a.o(i2, "remove cannot support. keyCode : ", ", mMimeType : ");
        o2.append(this.mMimeType);
        Log.i(TAG, o2.toString());
        return false;
    }

    private boolean isRestorableAttribute(int i2) {
        int i7 = this.mMimeType;
        if (i7 == 1) {
            return isRestorableMP4(i2);
        }
        if (i7 == 2) {
            return isRestorableHEIF(i2);
        }
        return false;
    }

    private boolean isRestorableHEIF(int i2) {
        if (i2 != 1) {
            return false;
        }
        ExifInterface exifInterface = new ExifInterface(this.mFileName);
        if (exifInterface.getAttribute("DateTimeOriginal").equals(exifInterface.getAttribute("DateTimeDigitized"))) {
            return false;
        }
        Log.i(TAG, "creation time info of this file is restorable");
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isRestorableMP4(int r3) {
        /*
            r2 = this;
            java.lang.String r3 = "ISOEditor"
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0031 }
            java.lang.String r2 = r2.mFileName     // Catch:{ FileNotFoundException -> 0x0031 }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0031 }
            java.io.FileDescriptor r2 = r1.getFD()     // Catch:{ FileNotFoundException -> 0x0029, all -> 0x0026 }
            com.samsung.android.sdk.sgpl.media.iso.IsoInterface r2 = com.samsung.android.sdk.sgpl.media.iso.IsoInterface.fromFileDescriptor(r2)     // Catch:{ FileNotFoundException -> 0x0029, all -> 0x0026 }
            r0 = 1936679796(0x736f6374, float:1.896631E31)
            long[] r2 = r2.getBoxRanges((int) r0)     // Catch:{ FileNotFoundException -> 0x0029, all -> 0x0026 }
            int r2 = r2.length     // Catch:{ FileNotFoundException -> 0x0029, all -> 0x0026 }
            if (r2 <= 0) goto L_0x002b
            java.lang.String r2 = "creation time info of this file is restorable"
            android.util.Log.i(r3, r2)     // Catch:{ FileNotFoundException -> 0x0029, all -> 0x0026 }
            r1.close()
            r2 = 1
            return r2
        L_0x0026:
            r2 = move-exception
            r0 = r1
            goto L_0x003d
        L_0x0029:
            r0 = r1
            goto L_0x0031
        L_0x002b:
            r1.close()
            goto L_0x003b
        L_0x002f:
            r2 = move-exception
            goto L_0x003d
        L_0x0031:
            java.lang.String r2 = "file not found"
            android.util.Log.w(r3, r2)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x003b
            r0.close()
        L_0x003b:
            r2 = 0
            return r2
        L_0x003d:
            if (r0 == 0) goto L_0x0042
            r0.close()
        L_0x0042:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.isRestorableMP4(int):boolean");
    }

    private void release(MediaMetadataRetriever mediaMetadataRetriever) {
        if (mediaMetadataRetriever != null) {
            try {
                mediaMetadataRetriever.release();
            } catch (Exception unused) {
            }
        }
    }

    private static String toGeoDegree(double d) {
        double abs = Math.abs(d);
        long j2 = (long) abs;
        double d2 = abs - ((double) j2);
        long j3 = (long) (d2 * 60.0d);
        long round = Math.round((d2 - (((double) j3) / 60.0d)) * 3600.0d * 1.0E7d);
        return j2 + "/1," + j3 + "/1," + round + "/10000000";
    }

    private static void updateAuthBox(RandomAccessFile randomAccessFile, String str, boolean z, boolean z3, long[] jArr, int i2) {
        byte[] bArr;
        String str2;
        RandomAccessFile randomAccessFile2;
        RandomAccessFile randomAccessFile3 = randomAccessFile;
        long[] jArr2 = jArr;
        int i7 = i2;
        int length = str.length() + 15;
        long j2 = jArr2[0];
        long j3 = jArr2[1];
        long length2 = randomAccessFile3.length() - j3;
        if (length2 <= 67108864) {
            if (length2 > 0) {
                int i8 = (int) length2;
                bArr = new byte[i8];
                randomAccessFile3.seek(j3);
                randomAccessFile3.read(bArr, 0, i8);
            } else {
                bArr = null;
            }
            byte[] bArr2 = bArr;
            if (z3) {
                if (z) {
                    str2 = "file has too big udta box. size : ";
                    writeAuthBox(randomAccessFile3, str, j2, ((long) length) + j2, true);
                    randomAccessFile2 = randomAccessFile;
                } else {
                    str2 = "file has too big udta box. size : ";
                    randomAccessFile2 = randomAccessFile;
                    writeAuthBox(randomAccessFile2, str, j3, j3 + ((long) length), true);
                }
                int i10 = 0;
                while (i10 < jArr2.length) {
                    randomAccessFile2.seek(jArr2[i10]);
                    long j8 = jArr2[i10 + 1] - jArr2[i10];
                    if (j8 <= 2147483647L) {
                        randomAccessFile2.writeInt(((int) j8) + i7);
                        i10 += 2;
                    } else {
                        throw new RuntimeException(a.f(str2, length2));
                    }
                }
                if (bArr2 != null) {
                    randomAccessFile2.seek(j3 + ((long) i7));
                    randomAccessFile2.write(bArr2, 0, (int) length2);
                    return;
                }
                return;
            }
            writeUdtaBox(randomAccessFile3, j3, length);
            long j10 = 8 + j3;
            String str3 = "file has too big udta box. size : ";
            writeAuthBox(randomAccessFile3, str, j10, ((long) length) + j10, true);
            long j11 = jArr2[1];
            long j12 = jArr2[0];
            long j13 = j11 - j12;
            randomAccessFile3.seek(j12);
            if (j13 <= 2147483647L) {
                randomAccessFile3.writeInt(((int) j13) + i7);
                if (bArr2 != null) {
                    randomAccessFile3.seek(j3 + ((long) i7));
                    randomAccessFile3.write(bArr2, 0, (int) length2);
                    return;
                }
                return;
            }
            throw new RuntimeException(a.f(str3, j13));
        }
        throw new RuntimeException(a.f("file has too big data except mdat. size : ", length2));
    }

    private static void updateLocationBox(RandomAccessFile randomAccessFile, String str, String str2, boolean z, long[] jArr, int i2) {
        byte[] bArr;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        long[] jArr2 = jArr;
        int i7 = i2;
        int doubleToIntx10000 = doubleToIntx10000(Double.valueOf(str).doubleValue());
        int doubleToIntx100002 = doubleToIntx10000(Double.valueOf(str2).doubleValue());
        long j2 = jArr2[1];
        long length = randomAccessFile2.length() - j2;
        if (length <= 67108864) {
            if (length > 0) {
                int i8 = (int) length;
                bArr = new byte[i8];
                randomAccessFile2.seek(j2);
                randomAccessFile2.read(bArr, 0, i8);
            } else {
                bArr = null;
            }
            byte[] bArr2 = bArr;
            if (z) {
                writeXyzBox(randomAccessFile2, doubleToIntx10000, doubleToIntx100002, j2, 30 + j2, true);
                long j3 = j2;
                int i10 = 0;
                while (i10 < jArr2.length) {
                    randomAccessFile2.seek(jArr2[i10]);
                    long j8 = jArr2[i10 + 1] - jArr2[i10];
                    if (j8 <= 2147483647L) {
                        randomAccessFile2.writeInt(((int) j8) + i7);
                        i10 += 2;
                    } else {
                        throw new RuntimeException(a.f("file has too big udta box. size : ", length));
                    }
                }
                if (bArr2 != null) {
                    randomAccessFile2.seek(((long) i7) + j3);
                    randomAccessFile2.write(bArr2, 0, (int) length);
                    return;
                }
                return;
            }
            long j10 = j2;
            int i11 = doubleToIntx10000;
            long j11 = j10;
            writeUdtaBox(randomAccessFile2, j11, 30);
            long j12 = j10;
            writeXyzBox(randomAccessFile2, i11, doubleToIntx100002, 8 + j11, j11 + 38, true);
            long j13 = jArr2[1];
            long j14 = jArr2[0];
            long j15 = j13 - j14;
            randomAccessFile2.seek(j14);
            if (j15 <= 2147483647L) {
                randomAccessFile2.writeInt(((int) j15) + i7);
                if (bArr2 != null) {
                    randomAccessFile2.seek(j12 + ((long) i7));
                    randomAccessFile2.write(bArr2, 0, (int) length);
                    return;
                }
                return;
            }
            throw new RuntimeException(a.f("file has too big udta box. size : ", j15));
        }
        throw new RuntimeException(a.f("file has too big data except mdat. size : ", length));
    }

    private static void updateMdatBox(RandomAccessFile randomAccessFile, long j2, long j3) {
        randomAccessFile.seek(j2);
        if (((long) randomAccessFile.readInt()) == 0) {
            long j8 = j3 - j2;
            if (j8 <= MAX_UINT_SIZE) {
                randomAccessFile.seek(j2);
                randomAccessFile.writeInt((int) j8);
                return;
            }
            throw new IOException(a.e(j8, "mdat box size is ", ". This file cannot be supported"));
        }
    }

    private static void updateSoctBox(RandomAccessFile randomAccessFile, long j2, boolean z, long[] jArr, int i2) {
        byte[] bArr;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        long j3 = j2;
        long[] jArr2 = jArr;
        int i7 = i2;
        long j8 = jArr2[1];
        long length = randomAccessFile2.length() - j8;
        if (length <= 67108864) {
            if (length > 0) {
                int i8 = (int) length;
                bArr = new byte[i8];
                randomAccessFile2.seek(j8);
                randomAccessFile2.read(bArr, 0, i8);
            } else {
                bArr = null;
            }
            if (z) {
                writeSoctBox(randomAccessFile2, j3, j8);
                int i10 = 0;
                while (i10 < jArr2.length) {
                    randomAccessFile2.seek(jArr2[i10]);
                    long j10 = jArr2[i10 + 1] - jArr2[i10];
                    if (j10 <= 2147483647L) {
                        randomAccessFile2.writeInt(((int) j10) + i7);
                        i10 += 2;
                    } else {
                        throw new IOException(a.f("file has too big udta box. size : ", length));
                    }
                }
                if (bArr != null) {
                    randomAccessFile2.seek(j8 + ((long) i7));
                    randomAccessFile2.write(bArr, 0, (int) length);
                    return;
                }
                return;
            }
            writeUdtaBox(randomAccessFile2, j8, 16);
            writeSoctBox(randomAccessFile2, j3, 8 + j8);
            long j11 = jArr2[1];
            long j12 = jArr2[0];
            long j13 = j11 - j12;
            randomAccessFile2.seek(j12);
            if (j13 <= 2147483647L) {
                randomAccessFile2.writeInt(((int) j13) + i7);
                if (bArr != null) {
                    randomAccessFile2.seek(j8 + ((long) i7));
                    randomAccessFile2.write(bArr, 0, (int) length);
                    return;
                }
                return;
            }
            throw new IOException(a.f("file has too big udta box. size : ", j13));
        }
        throw new IOException(a.f("file has too big data except mdat. size : ", length));
    }

    private static void writeAuthBox(RandomAccessFile randomAccessFile, String str, long j2, long j3, boolean z) {
        long j8 = j3 - j2;
        if (j8 <= 67108864) {
            int length = str.length() + 15;
            if (z) {
                randomAccessFile.seek(j2);
                randomAccessFile.writeInt(length);
                randomAccessFile.writeInt(IsoInterface.BOX_AUTH);
                randomAccessFile.writeInt(0);
            } else {
                randomAccessFile.seek(j2 + 12);
            }
            randomAccessFile.writeShort(5575);
            Locale locale = Locale.ENGLISH;
            randomAccessFile.write(str.concat("\u0000").getBytes());
            long j10 = j8 - ((long) length);
            if (j10 > 0) {
                int i2 = (int) j10;
                byte[] bArr = new byte[i2];
                Arrays.fill(bArr, 0, i2, (byte) 0);
                randomAccessFile.write(bArr);
                return;
            }
            return;
        }
        throw new RuntimeException(a.f("authBoxSize is too large. size : ", j8));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0209, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x020e, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a4, code lost:
        r2 = r6;
        r10 = r7;
        r19 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0169, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x016a, code lost:
        r2 = r6;
        r10 = r7;
        r19 = r15;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0209 A[Catch:{ all -> 0x0160 }] */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x020e A[Catch:{ all -> 0x0160 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a3 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:38:0x0087] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:162:0x0242=Splitter:B:162:0x0242, B:135:0x01fe=Splitter:B:135:0x01fe} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void writeNewExifDataAndAdjustOffset(java.lang.String r28, byte[] r29, long r30, int r32) {
        /*
            r1 = r28
            r0 = r29
            r8 = r30
            r2 = r32
            java.lang.String r3 = "ISOEditor"
            java.lang.String r4 = "mdat size changed. before : "
            java.lang.String r5 = "exifOffset : "
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0266 }
            r10.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0266 }
            java.io.FileDescriptor r6 = r10.getFD()     // Catch:{ all -> 0x0212 }
            com.samsung.android.sdk.sgpl.media.iso.IsoInterface r6 = com.samsung.android.sdk.sgpl.media.iso.IsoInterface.fromFileDescriptor(r6)     // Catch:{ all -> 0x0212 }
            r7 = 1835295092(0x6d646174, float:4.4175247E27)
            long[] r11 = r6.getBoxRanges((int) r7)     // Catch:{ all -> 0x0212 }
            int r12 = r11.length     // Catch:{ all -> 0x0212 }
            r13 = 2
            if (r12 > r13) goto L_0x0253
            int r7 = r6.getBoxHeaderSize(r7)     // Catch:{ all -> 0x0212 }
            r12 = 16
            r15 = 0
            if (r7 < r12) goto L_0x0031
            r7 = 1
            goto L_0x0032
        L_0x0031:
            r7 = r15
        L_0x0032:
            r11 = r11[r15]     // Catch:{ all -> 0x0212 }
            r16 = 8
            long r11 = r11 - r16
            r16 = 1
            r14 = 1768714083(0x696c6f63, float:1.7864531E25)
            long[] r6 = r6.getBoxRanges((int) r14)     // Catch:{ all -> 0x0212 }
            int r14 = r6.length     // Catch:{ all -> 0x0212 }
            if (r14 > r13) goto L_0x0249
            r13 = r6[r15]     // Catch:{ all -> 0x0212 }
            r16 = r6[r16]     // Catch:{ all -> 0x0212 }
            long r16 = r16 - r13
            java.lang.String r6 = "temp"
            java.lang.String r15 = "tmp"
            java.io.File r15 = java.io.File.createTempFile(r6, r15)     // Catch:{ Exception -> 0x0234, all -> 0x022f }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0234, all -> 0x022f }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0234, all -> 0x022f }
            r20 = r7
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0229, all -> 0x0223 }
            r7.<init>(r15)     // Catch:{ Exception -> 0x0229, all -> 0x0223 }
            copy(r6, r7)     // Catch:{ Exception -> 0x021d, all -> 0x0217 }
            closeQuietly(r6)     // Catch:{ all -> 0x0212 }
            closeQuietly(r7)     // Catch:{ all -> 0x0212 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x01cd, all -> 0x01c3 }
            r6.<init>(r15)     // Catch:{ Exception -> 0x01cd, all -> 0x01c3 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x01b7, all -> 0x01a9 }
            r7.<init>(r1)     // Catch:{ Exception -> 0x01b7, all -> 0x01a9 }
            r21 = r6
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x01a5, all -> 0x01a1 }
            r22 = r7
            java.lang.String r7 = "rw"
            r6.<init>(r1, r7)     // Catch:{ Exception -> 0x0198, all -> 0x018d }
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0188, all -> 0x0183 }
            r23 = r10
            java.lang.String r10 = "r"
            r7.<init>(r15, r10)     // Catch:{ Exception -> 0x017a, all -> 0x016f }
            int r10 = r0.length     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            int r10 = r10 - r2
            long r24 = r7.length()     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            r26 = r13
            int r13 = r0.length     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            long r13 = (long) r13
            int r13 = (r24 > r13 ? 1 : (r24 == r13 ? 0 : -1))
            if (r13 <= 0) goto L_0x00ac
            long r13 = r7.length()     // Catch:{ Exception -> 0x00a3, all -> 0x009b }
            int r13 = (int) r13
            goto L_0x00ad
        L_0x009b:
            r0 = move-exception
            r2 = r6
            r10 = r7
            r4 = r15
        L_0x009f:
            r6 = r21
            goto L_0x01fe
        L_0x00a3:
            r0 = move-exception
            r2 = r6
            r10 = r7
            r19 = r15
        L_0x00a8:
            r6 = r21
            goto L_0x01d5
        L_0x00ac:
            int r13 = r0.length     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
        L_0x00ad:
            byte[] r13 = new byte[r13]     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            r14.<init>(r5)     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            r14.append(r8)     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            java.lang.String r5 = ", new exif data size : "
            r14.append(r5)     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            int r5 = r0.length     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            r14.append(r5)     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            java.lang.String r5 = r14.toString()     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            android.util.Log.i(r3, r5)     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            int r5 = (int) r8     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            r14 = 0
            r7.read(r13, r14, r5)     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            r6.write(r13, r14, r5)     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            r18 = r5
            int r5 = r0.length     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            r6.write(r0, r14, r5)     // Catch:{ Exception -> 0x00a3, all -> 0x0169 }
            r19 = r15
            long r14 = (long) r2
            long r14 = r14 + r8
            r7.seek(r14)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            long r14 = r7.length()     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            int r5 = (int) r14     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            int r5 = r5 - r18
            int r5 = r5 - r2
            r14 = 0
            r7.read(r13, r14, r5)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            long r14 = r7.length()     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            int r5 = (int) r14     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            int r5 = r5 - r18
            int r5 = r5 - r2
            r14 = 0
            r6.write(r13, r14, r5)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            r7.seek(r11)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            if (r20 == 0) goto L_0x0108
            long r13 = r7.readLong()     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            goto L_0x010d
        L_0x00fe:
            r0 = move-exception
            r2 = r6
            r10 = r7
        L_0x0101:
            r4 = r19
            goto L_0x009f
        L_0x0104:
            r0 = move-exception
            r2 = r6
            r10 = r7
            goto L_0x00a8
        L_0x0108:
            int r0 = r7.readInt()     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            long r13 = (long) r0     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
        L_0x010d:
            r6.seek(r11)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            if (r20 == 0) goto L_0x0118
            long r11 = (long) r10     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            long r11 = r11 + r13
            r6.writeLong(r11)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            goto L_0x011d
        L_0x0118:
            int r0 = (int) r13     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            int r0 = r0 + r10
            r6.writeInt(r0)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
        L_0x011d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            r0.<init>(r4)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            r0.append(r13)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            java.lang.String r2 = ", after : "
            r0.append(r2)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            long r4 = (long) r10     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            long r13 = r13 + r4
            r0.append(r13)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            android.util.Log.i(r3, r0)     // Catch:{ Exception -> 0x0104, all -> 0x00fe }
            int r0 = (r26 > r8 ? 1 : (r26 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x0144
            long r13 = r26 + r4
            r3 = r13
        L_0x013d:
            r2 = r10
            r10 = r7
            r7 = r2
            r2 = r6
            r5 = r16
            goto L_0x0147
        L_0x0144:
            r3 = r26
            goto L_0x013d
        L_0x0147:
            changeOffsetInfo(r2, r3, r5, r7, r8)     // Catch:{ Exception -> 0x0166, all -> 0x0164 }
            changeMotionPhotoInfo(r1, r7)     // Catch:{ Exception -> 0x0166, all -> 0x0164 }
            closeQuietly(r21)     // Catch:{ all -> 0x0160 }
            closeQuietly(r22)     // Catch:{ all -> 0x0160 }
            r19.delete()     // Catch:{ all -> 0x0160 }
            r10.close()     // Catch:{ all -> 0x0160 }
            r2.close()     // Catch:{ all -> 0x0160 }
            r23.close()     // Catch:{ FileNotFoundException -> 0x0266 }
            return
        L_0x0160:
            r0 = move-exception
        L_0x0161:
            r1 = r0
            goto L_0x025d
        L_0x0164:
            r0 = move-exception
            goto L_0x0101
        L_0x0166:
            r0 = move-exception
            goto L_0x00a8
        L_0x0169:
            r0 = move-exception
            r2 = r6
            r10 = r7
            r19 = r15
            goto L_0x0101
        L_0x016f:
            r0 = move-exception
            r2 = r6
        L_0x0171:
            r19 = r15
            r4 = r19
            r6 = r21
        L_0x0177:
            r10 = 0
            goto L_0x01fe
        L_0x017a:
            r0 = move-exception
            r2 = r6
        L_0x017c:
            r19 = r15
            r6 = r21
        L_0x0180:
            r10 = 0
            goto L_0x01d5
        L_0x0183:
            r0 = move-exception
            r2 = r6
            r23 = r10
            goto L_0x0171
        L_0x0188:
            r0 = move-exception
            r2 = r6
            r23 = r10
            goto L_0x017c
        L_0x018d:
            r0 = move-exception
        L_0x018e:
            r23 = r10
            r19 = r15
            r4 = r19
            r6 = r21
            r2 = 0
            goto L_0x0177
        L_0x0198:
            r0 = move-exception
        L_0x0199:
            r23 = r10
            r19 = r15
            r6 = r21
            r2 = 0
            goto L_0x0180
        L_0x01a1:
            r0 = move-exception
            r22 = r7
            goto L_0x018e
        L_0x01a5:
            r0 = move-exception
            r22 = r7
            goto L_0x0199
        L_0x01a9:
            r0 = move-exception
            r21 = r6
            r23 = r10
            r19 = r15
            r4 = r19
            r2 = 0
        L_0x01b3:
            r10 = 0
            r22 = 0
            goto L_0x01fe
        L_0x01b7:
            r0 = move-exception
            r21 = r6
            r23 = r10
            r19 = r15
            r2 = 0
        L_0x01bf:
            r10 = 0
            r22 = 0
            goto L_0x01d5
        L_0x01c3:
            r0 = move-exception
            r23 = r10
            r19 = r15
            r4 = r19
            r2 = 0
            r6 = 0
            goto L_0x01b3
        L_0x01cd:
            r0 = move-exception
            r23 = r10
            r19 = r15
            r2 = 0
            r6 = 0
            goto L_0x01bf
        L_0x01d5:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x01fb }
            r4 = r19
            r3.<init>(r4)     // Catch:{ all -> 0x01f9 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ all -> 0x01f7 }
            r6.<init>(r1)     // Catch:{ all -> 0x01f7 }
            copy(r3, r6)     // Catch:{ all -> 0x01f2 }
            closeQuietly(r3)     // Catch:{ all -> 0x01f2 }
            closeQuietly(r6)     // Catch:{ all -> 0x01f2 }
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x01f2 }
            java.lang.String r5 = "Failed to write new Exif"
            r1.<init>(r5, r0)     // Catch:{ all -> 0x01f2 }
            throw r1     // Catch:{ all -> 0x01f2 }
        L_0x01f2:
            r0 = move-exception
            r22 = r6
        L_0x01f5:
            r6 = r3
            goto L_0x01fe
        L_0x01f7:
            r0 = move-exception
            goto L_0x01f5
        L_0x01f9:
            r0 = move-exception
            goto L_0x01fe
        L_0x01fb:
            r0 = move-exception
            r4 = r19
        L_0x01fe:
            closeQuietly(r6)     // Catch:{ all -> 0x0160 }
            closeQuietly(r22)     // Catch:{ all -> 0x0160 }
            r4.delete()     // Catch:{ all -> 0x0160 }
            if (r10 == 0) goto L_0x020c
            r10.close()     // Catch:{ all -> 0x0160 }
        L_0x020c:
            if (r2 == 0) goto L_0x0211
            r2.close()     // Catch:{ all -> 0x0160 }
        L_0x0211:
            throw r0     // Catch:{ all -> 0x0160 }
        L_0x0212:
            r0 = move-exception
            r23 = r10
            goto L_0x0161
        L_0x0217:
            r0 = move-exception
            r23 = r10
            r18 = r7
            goto L_0x0242
        L_0x021d:
            r0 = move-exception
            r23 = r10
            r18 = r7
            goto L_0x0239
        L_0x0223:
            r0 = move-exception
            r23 = r10
        L_0x0226:
            r18 = 0
            goto L_0x0242
        L_0x0229:
            r0 = move-exception
            r23 = r10
        L_0x022c:
            r18 = 0
            goto L_0x0239
        L_0x022f:
            r0 = move-exception
            r23 = r10
            r6 = 0
            goto L_0x0226
        L_0x0234:
            r0 = move-exception
            r23 = r10
            r6 = 0
            goto L_0x022c
        L_0x0239:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x0241 }
            java.lang.String r2 = "Failed to copy original file to temp file"
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0241 }
            throw r1     // Catch:{ all -> 0x0241 }
        L_0x0241:
            r0 = move-exception
        L_0x0242:
            closeQuietly(r6)     // Catch:{ all -> 0x0160 }
            closeQuietly(r18)     // Catch:{ all -> 0x0160 }
            throw r0     // Catch:{ all -> 0x0160 }
        L_0x0249:
            r23 = r10
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0160 }
            java.lang.String r1 = "cannot support multi iloc box"
            r0.<init>(r1)     // Catch:{ all -> 0x0160 }
            throw r0     // Catch:{ all -> 0x0160 }
        L_0x0253:
            r23 = r10
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0160 }
            java.lang.String r1 = "cannot support multi mdat box"
            r0.<init>(r1)     // Catch:{ all -> 0x0160 }
            throw r0     // Catch:{ all -> 0x0160 }
        L_0x025d:
            r23.close()     // Catch:{ all -> 0x0261 }
            goto L_0x0265
        L_0x0261:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ FileNotFoundException -> 0x0266 }
        L_0x0265:
            throw r1     // Catch:{ FileNotFoundException -> 0x0266 }
        L_0x0266:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "file not found"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.writeNewExifDataAndAdjustOffset(java.lang.String, byte[], long, int):void");
    }

    private static void writeSoctBox(RandomAccessFile randomAccessFile, long j2, long j3) {
        randomAccessFile.seek(j3);
        randomAccessFile.writeInt(16);
        randomAccessFile.writeInt(IsoInterface.BOX_SOCT);
        randomAccessFile.writeLong(j2);
    }

    private static void writeUdtaBox(RandomAccessFile randomAccessFile, long j2, int i2) {
        randomAccessFile.seek(j2);
        randomAccessFile.writeInt(i2 + 8);
        randomAccessFile.writeInt(IsoInterface.BOX_UDTA);
    }

    private static void writeUuidBox(RandomAccessFile randomAccessFile, byte[] bArr, long j2) {
        randomAccessFile.seek(j2);
        randomAccessFile.writeInt(bArr.length + 24);
        randomAccessFile.writeInt(IsoInterface.BOX_UUID);
        randomAccessFile.write(new byte[]{-1, -52, -126, 99, -8, 85, 74, -109, -120, 20, 88, 122, 2, 82, 31, -35});
        randomAccessFile.write(bArr);
    }

    private static void writeXyzBox(RandomAccessFile randomAccessFile, int i2, int i7, long j2, long j3, boolean z) {
        boolean z3;
        if (z) {
            randomAccessFile.seek(j2);
            randomAccessFile.writeInt(30);
            randomAccessFile.writeInt(IsoInterface.BOX_XYZ);
        } else {
            randomAccessFile.seek(8 + j2);
        }
        randomAccessFile.writeInt(1185223);
        int i8 = i2 / 10000;
        boolean z7 = true;
        if (i2 < 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        int i10 = i2 - (i8 * 10000);
        if (i10 < 0) {
            i10 = -i10;
        }
        if (i8 < 0) {
            i8 = -i8;
        }
        if (z3) {
            randomAccessFile.writeByte(45);
        } else {
            randomAccessFile.writeByte(43);
        }
        Locale locale = Locale.ENGLISH;
        randomAccessFile.write(String.format(locale, "%02d.", new Object[]{Integer.valueOf(i8)}).getBytes());
        randomAccessFile.write(String.format(locale, "%04d", new Object[]{Integer.valueOf(i10)}).getBytes());
        if (i7 >= 0) {
            z7 = false;
        }
        int i11 = i7 / 10000;
        int i12 = i7 - (i11 * 10000);
        if (i12 < 0) {
            i12 = -i12;
        }
        if (i11 < 0) {
            i11 = -i11;
        }
        if (z7) {
            randomAccessFile.writeByte(45);
        } else {
            randomAccessFile.writeByte(43);
        }
        randomAccessFile.write(String.format(locale, "%03d.", new Object[]{Integer.valueOf(i11)}).getBytes());
        randomAccessFile.write(String.format(locale, "%04d", new Object[]{Integer.valueOf(i12)}).getBytes());
        randomAccessFile.writeByte(47);
        int i13 = (((int) j3) - ((int) j2)) - 30;
        if (i13 > 0) {
            byte[] bArr = new byte[i13];
            Arrays.fill(bArr, 0, i13, (byte) 0);
            randomAccessFile.write(bArr);
        }
    }

    public boolean isEditableAttribute(int i2, int i7) {
        if (i7 == 1) {
            return isEditableAttribute(i2);
        }
        if (i7 == 2) {
            return isRestorableAttribute(i2);
        }
        if (i7 != 3) {
            return false;
        }
        try {
            return isRemovableAtrribute(i2);
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean isEditableFile() {
        int i2 = this.mMimeType;
        if (i2 == 1) {
            try {
                if (isEditableMP4(this.mFileName)) {
                    return true;
                }
            } catch (IOException unused) {
                Log.i(TAG, "unsupported file : " + this.mMimeType);
                return false;
            }
        } else if (i2 == 2 && this.mHasExif) {
            return true;
        }
        Log.i(TAG, "unsupported file : " + this.mMimeType);
        return false;
    }

    public void restoreAttribute(int i2) {
        if (i2 == 1) {
            this.mAttribute.add(new MetaTag(i2, FUNC_RESTORE, 0));
            return;
        }
        throw new IllegalArgumentException(C0086a.i(i2, "invalid keyCode : "));
    }

    public void saveAttributes() {
        if (this.mMimeType != 0) {
            String str = null;
            String str2 = null;
            String str3 = null;
            byte[] bArr = null;
            int i2 = 0;
            String str4 = null;
            while (i2 < this.mAttribute.size()) {
                try {
                    MetaTag metaTag = (MetaTag) this.mAttribute.get(i2);
                    if (metaTag != null) {
                        int i7 = metaTag.keyCode;
                        if (i7 == 1) {
                            str = metaTag.valueStr;
                        } else if (i7 == 2) {
                            str4 = metaTag.valueStr;
                        } else if (i7 == 3) {
                            str2 = metaTag.valueStr;
                        } else if (i7 == 4) {
                            str3 = metaTag.valueStr;
                        } else if (i7 == 100) {
                            bArr = metaTag.valueBytes;
                        }
                    }
                    i2++;
                } catch (Exception e) {
                    throw new IOException("Failed to saveAttributes", e);
                }
            }
            int i8 = this.mMimeType;
            if (i8 == 1) {
                if (str != null) {
                    editCreationTimeForMP4(this.mFileName, str);
                }
                if (!(str4 == null || str2 == null)) {
                    editLocationForMP4(this.mFileName, str4, str2);
                }
                if (bArr != null) {
                    editSphericalVideoXMLForMP4(this.mFileName, bArr);
                }
                if (str3 != null) {
                    editDeivceNameForMP4(this.mFileName, str3);
                }
            } else if (i8 == 2) {
                editExifForHEIF(this.mFileName, str, str4, str2);
            }
        } else {
            throw new IllegalArgumentException("this file is unsupported file format");
        }
    }

    public void setAttribute(int i2, String str) {
        int i7 = this.mMimeType;
        if (i7 == 0) {
            throw new IllegalArgumentException("this file is unsupported file format");
        } else if (i2 < 1 || i2 > 4) {
            throw new IllegalArgumentException(C0086a.i(i2, "invalid keyCode : "));
        } else {
            if (str == null) {
                if (i7 == 2 && (i2 == 3 || i2 == 2)) {
                    str = FUNC_REMOVE;
                } else {
                    throw new IllegalArgumentException("remove cannot support");
                }
            }
            this.mAttribute.add(new MetaTag(i2, str, 0));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        if (r5 != 100) goto L_0x003d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isEditableAttribute(int r5) {
        /*
            r4 = this;
            int r0 = r4.mMimeType
            r1 = 3
            r2 = 2
            r3 = 1
            if (r0 != r3) goto L_0x002f
            if (r5 == r3) goto L_0x0015
            if (r5 == r2) goto L_0x0015
            if (r5 == r1) goto L_0x0015
            r0 = 4
            if (r5 == r0) goto L_0x0015
            r0 = 100
            if (r5 == r0) goto L_0x001e
            goto L_0x003d
        L_0x0015:
            java.lang.String r0 = r4.mFileName
            boolean r0 = isEditableMP4(r0)
            if (r0 == 0) goto L_0x001e
            return r3
        L_0x001e:
            java.lang.String r0 = r4.mFileName
            boolean r0 = isEditableMP4(r0)
            if (r0 == 0) goto L_0x003d
            java.lang.String r0 = r4.mFileName
            boolean r0 = hasVideoTrack(r0)
            if (r0 == 0) goto L_0x003d
            return r3
        L_0x002f:
            if (r0 != r2) goto L_0x003d
            if (r5 == r3) goto L_0x0038
            if (r5 == r2) goto L_0x0038
            if (r5 == r1) goto L_0x0038
            goto L_0x003d
        L_0x0038:
            boolean r0 = r4.mHasExif
            if (r0 == 0) goto L_0x003d
            return r3
        L_0x003d:
            java.lang.String r0 = "edit cannot support. keyCode : "
            java.lang.String r1 = ", mMimeType : "
            java.lang.StringBuilder r5 = c0.C0086a.o(r5, r0, r1)
            int r4 = r4.mMimeType
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.String r5 = "ISOEditor"
            android.util.Log.i(r5, r4)
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.sgpl.media.iso.ISOEditor.isEditableAttribute(int):boolean");
    }

    public void setAttribute(int i2, String str, boolean z) {
        int i7 = this.mMimeType;
        if (i7 == 0) {
            throw new IllegalArgumentException("this file is unsupported file format");
        } else if (i2 < 1 || i2 > 4) {
            throw new IllegalArgumentException(C0086a.i(i2, "invalid keyCode : "));
        } else {
            if (z) {
                if (i7 == 2 && (i2 == 3 || i2 == 2)) {
                    str = FUNC_REMOVE;
                } else {
                    throw new IllegalArgumentException(C0086a.i(i2, "removal(String) is not supported for this keyCode : "));
                }
            }
            this.mAttribute.add(new MetaTag(i2, str, 0));
        }
    }

    public void setAttribute(int i2, byte[] bArr, boolean z) {
        if (this.mMimeType != 1) {
            throw new IllegalArgumentException("this file is unsupported file format");
        } else if (i2 < 100 || i2 > 100) {
            throw new IllegalArgumentException(C0086a.i(i2, "invalid keyCode : "));
        } else if (!z) {
            this.mAttribute.add(new MetaTag(i2, 0, bArr));
        } else {
            throw new IllegalArgumentException(C0086a.i(i2, "removal(byte[]) is not supported for this keyCode : "));
        }
    }
}
