package com.samsung.android.sum.core;

import A8.C0545b;
import android.media.ExifInterface;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MetaDataUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int JPEG_LENGTH_SIZE = 2;
    private static final int JPEG_MARKER_SIZE = 2;
    private static final String TAG = "MetaDataUtil";
    private static final String[] exifTags = {"FNumber", "ApertureValue", "Artist", "BitsPerSample", "BrightnessValue", "CFAPattern", "ColorSpace", "ComponentsConfiguration", "CompressedBitsPerPixel", "Compression", "Contrast", "Copyright", "CustomRendered", "DateTime", "DateTimeDigitized", "DateTimeOriginal", "DefaultCropSize", "DeviceSettingDescription", "DigitalZoomRatio", "DNGVersion", "ExifVersion", "ExposureBiasValue", "ExposureIndex", "ExposureMode", "ExposureProgram", "ExposureTime", "FileSource", "Flash", "FlashpixVersion", "FlashEnergy", "FocalLength", "FocalLengthIn35mmFilm", "FocalPlaneResolutionUnit", "FocalPlaneXResolution", "FocalPlaneYResolution", "FNumber", "GainControl", "GPSAltitude", "GPSAltitudeRef", "GPSAreaInformation", "GPSDateStamp", "GPSDestBearing", "GPSDestBearingRef", "GPSDestDistance", "GPSDestDistanceRef", "GPSDestLatitude", "GPSDestLatitudeRef", "GPSDestLongitude", "GPSDestLongitudeRef", "GPSDifferential", "GPSDOP", "GPSImgDirection", "GPSImgDirectionRef", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSMapDatum", "GPSMeasureMode", "GPSProcessingMethod", "GPSSatellites", "GPSSpeed", "GPSSpeedRef", "GPSStatus", "GPSTimeStamp", "GPSTrack", "GPSTrackRef", "GPSVersionID", "ImageDescription", "ImageLength", "ImageUniqueID", "ImageWidth", "InteroperabilityIndex", "ISOSpeedRatings", "ISOSpeedRatings", "JPEGInterchangeFormat", "JPEGInterchangeFormatLength", "LightSource", "Make", "MakerNote", "MaxApertureValue", "MeteringMode", "Model", "NewSubfileType", "OECF", "AspectFrame", "PreviewImageLength", "PreviewImageStart", "ThumbnailImage", "Orientation", "PhotometricInterpretation", "PixelXDimension", "PixelYDimension", "PlanarConfiguration", "PrimaryChromaticities", "ReferenceBlackWhite", "RelatedSoundFile", "ResolutionUnit", "RowsPerStrip", "ISO", "JpgFromRaw", "SensorBottomBorder", "SensorLeftBorder", "SensorRightBorder", "SensorTopBorder", "SamplesPerPixel", "Saturation", "SceneCaptureType", "SceneType", "SensingMethod", "Sharpness", "ShutterSpeedValue", "Software", "SpatialFrequencyResponse", "SpectralSensitivity", "StripByteCounts", "StripOffsets", "SubfileType", "SubjectArea", "SubjectDistance", "SubjectDistanceRange", "SubjectLocation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeDigitized", "SubSecTimeOriginal", "SubSecTimeOriginal", "ThumbnailImageLength", "ThumbnailImageWidth", "TransferFunction", "UserComment", "WhiteBalance", "WhitePoint", "XResolution", "YCbCrCoefficients", "YCbCrPositioning", "YCbCrSubSampling", "YResolution", "OffsetTimeOriginal", "OffsetTime", "OffsetTimeDigitized"};

    public static ExifInterface copyExif(FileInputStream fileInputStream, RandomAccessFile randomAccessFile) {
        SLog.d(TAG, "in: " + fileInputStream + ", out: " + randomAccessFile);
        ExifInterface exifInterface = null;
        try {
            fileInputStream.getChannel().position(0);
            randomAccessFile.getChannel().position(0);
            ExifInterface exifInterface2 = new ExifInterface(fileInputStream.getFD());
            ExifInterface exifInterface3 = new ExifInterface(randomAccessFile.getFD());
            try {
                for (String str : exifTags) {
                    if (exifInterface2.hasAttribute(str)) {
                        exifInterface3.setAttribute(str, exifInterface2.getAttribute(str));
                    }
                }
                return exifInterface3;
            } catch (IOException e) {
                e = e;
                exifInterface = exifInterface3;
                e.printStackTrace();
                return exifInterface;
            }
        } catch (IOException e7) {
            e = e7;
            e.printStackTrace();
            return exifInterface;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0080 A[SYNTHETIC, Splitter:B:39:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0095 A[SYNTHETIC, Splitter:B:49:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x009d A[SYNTHETIC, Splitter:B:53:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00a5 A[Catch:{ IOException -> 0x00a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:46:0x0090=Splitter:B:46:0x0090, B:36:0x0075=Splitter:B:36:0x0075} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean copyMetadata(java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.String r0 = "src has invalid meta: "
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "copyMetadata: src="
            r2.<init>(r3)
            r2.append(r5)
            java.lang.String r3 = ", dst="
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r1, (java.lang.String) r2)
            java.lang.String r2 = ".(jpg|jpeg)$"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.util.Locale r3 = java.util.Locale.getDefault()
            java.lang.String r3 = r5.toLowerCase(r3)
            java.util.regex.Matcher r2 = r2.matcher(r3)
            boolean r2 = r2.find()
            if (r2 == 0) goto L_0x00ad
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0072, IllegalArgumentException -> 0x0070, all -> 0x006d }
            r2.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0072, IllegalArgumentException -> 0x0070, all -> 0x006d }
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x006a, IllegalArgumentException -> 0x0068, all -> 0x0065 }
            java.lang.String r4 = "rw"
            r3.<init>(r6, r4)     // Catch:{ FileNotFoundException -> 0x006a, IllegalArgumentException -> 0x0068, all -> 0x0065 }
            java.util.ArrayList r6 = getAppNMetadata(r2)     // Catch:{ FileNotFoundException -> 0x0055, IllegalArgumentException -> 0x0053, all -> 0x0050 }
            boolean r1 = r6.isEmpty()     // Catch:{ FileNotFoundException -> 0x0055, IllegalArgumentException -> 0x0053, all -> 0x0050 }
            if (r1 != 0) goto L_0x0058
            setAppNMetadata(r6, r3)     // Catch:{ FileNotFoundException -> 0x0055, IllegalArgumentException -> 0x0053, all -> 0x0050 }
            goto L_0x0058
        L_0x0050:
            r5 = move-exception
        L_0x0051:
            r1 = r2
            goto L_0x009b
        L_0x0053:
            r1 = r2
            goto L_0x0075
        L_0x0055:
            r5 = move-exception
        L_0x0056:
            r1 = r2
            goto L_0x0090
        L_0x0058:
            r5 = 1
            r2.close()     // Catch:{ IOException -> 0x0060 }
            r3.close()     // Catch:{ IOException -> 0x0060 }
            return r5
        L_0x0060:
            r6 = move-exception
            r6.printStackTrace()
            return r5
        L_0x0065:
            r5 = move-exception
            r3 = r1
            goto L_0x0051
        L_0x0068:
            r3 = r1
            goto L_0x0053
        L_0x006a:
            r5 = move-exception
            r3 = r1
            goto L_0x0056
        L_0x006d:
            r5 = move-exception
            r3 = r1
            goto L_0x009b
        L_0x0070:
            r3 = r1
            goto L_0x0075
        L_0x0072:
            r5 = move-exception
            r3 = r1
            goto L_0x0090
        L_0x0075:
            java.lang.String r6 = TAG     // Catch:{ all -> 0x008e }
            java.lang.String r5 = r0.concat(r5)     // Catch:{ all -> 0x008e }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r6, (java.lang.String) r5)     // Catch:{ all -> 0x008e }
            if (r1 == 0) goto L_0x0083
            r1.close()     // Catch:{ IOException -> 0x0089 }
        L_0x0083:
            if (r3 == 0) goto L_0x00b6
        L_0x0085:
            r3.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x00b6
        L_0x0089:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x00b6
        L_0x008e:
            r5 = move-exception
            goto L_0x009b
        L_0x0090:
            r5.printStackTrace()     // Catch:{ all -> 0x008e }
            if (r1 == 0) goto L_0x0098
            r1.close()     // Catch:{ IOException -> 0x0089 }
        L_0x0098:
            if (r3 == 0) goto L_0x00b6
            goto L_0x0085
        L_0x009b:
            if (r1 == 0) goto L_0x00a3
            r1.close()     // Catch:{ IOException -> 0x00a1 }
            goto L_0x00a3
        L_0x00a1:
            r6 = move-exception
            goto L_0x00a9
        L_0x00a3:
            if (r3 == 0) goto L_0x00ac
            r3.close()     // Catch:{ IOException -> 0x00a1 }
            goto L_0x00ac
        L_0x00a9:
            r6.printStackTrace()
        L_0x00ac:
            throw r5
        L_0x00ad:
            java.lang.String r6 = "not supported file format: "
            java.lang.String r5 = r6.concat(r5)
            com.samsung.android.sum.core.SLog.w((java.lang.String) r1, (java.lang.String) r5)
        L_0x00b6:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.MetaDataUtil.copyMetadata(java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a4 A[SYNTHETIC, Splitter:B:44:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00be A[SYNTHETIC, Splitter:B:57:0x00be] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00cb A[SYNTHETIC, Splitter:B:63:0x00cb] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d5 A[SYNTHETIC, Splitter:B:68:0x00d5] */
    /* JADX WARNING: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:41:0x0099=Splitter:B:41:0x0099, B:54:0x00b9=Splitter:B:54:0x00b9} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean copyMetadataAndExif(java.lang.String r7, java.lang.String r8, java.util.function.Consumer<android.media.ExifInterface> r9) {
        /*
            java.lang.String r0 = "src has invalid meta: "
            java.lang.String r1 = "exif: "
            java.lang.String r2 = TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "copyMetadataAndExif: src="
            r3.<init>(r4)
            r3.append(r7)
            java.lang.String r4 = ", dst="
            r3.append(r4)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r2, (java.lang.String) r3)
            java.lang.String r3 = ".(jpg|jpeg)$"
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3)
            java.util.Locale r4 = java.util.Locale.getDefault()
            java.lang.String r4 = r7.toLowerCase(r4)
            java.util.regex.Matcher r3 = r3.matcher(r4)
            boolean r3 = r3.find()
            if (r3 == 0) goto L_0x00de
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0096, IllegalArgumentException -> 0x0094, all -> 0x0091 }
            r4.<init>(r7)     // Catch:{ IOException -> 0x0096, IllegalArgumentException -> 0x0094, all -> 0x0091 }
            java.io.RandomAccessFile r5 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x008e, IllegalArgumentException -> 0x008c, all -> 0x0089 }
            java.lang.String r6 = "rw"
            r5.<init>(r8, r6)     // Catch:{ IOException -> 0x008e, IllegalArgumentException -> 0x008c, all -> 0x0089 }
            java.util.ArrayList r8 = getAppNMetadata(r4)     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            boolean r3 = r8.isEmpty()     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            if (r3 != 0) goto L_0x005c
            setAppNMetadata(r8, r5)     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            goto L_0x005c
        L_0x0052:
            r7 = move-exception
        L_0x0053:
            r3 = r4
            goto L_0x00c9
        L_0x0056:
            r3 = r4
            goto L_0x0099
        L_0x0058:
            r7 = move-exception
        L_0x0059:
            r3 = r4
            goto L_0x00b9
        L_0x005c:
            android.media.ExifInterface r8 = copyExif(r4, r5)     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            r3.append(r8)     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r2, (java.lang.String) r1)     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            if (r9 == 0) goto L_0x0074
            r9.accept(r8)     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
        L_0x0074:
            r8.saveAttributes()     // Catch:{ IOException -> 0x0058, IllegalArgumentException -> 0x0056, all -> 0x0052 }
            r4.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r7 = move-exception
            r7.printStackTrace()
        L_0x007f:
            r5.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0087
        L_0x0083:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0087:
            r7 = 1
            return r7
        L_0x0089:
            r7 = move-exception
            r5 = r3
            goto L_0x0053
        L_0x008c:
            r5 = r3
            goto L_0x0056
        L_0x008e:
            r7 = move-exception
            r5 = r3
            goto L_0x0059
        L_0x0091:
            r7 = move-exception
            r5 = r3
            goto L_0x00c9
        L_0x0094:
            r5 = r3
            goto L_0x0099
        L_0x0096:
            r7 = move-exception
            r5 = r3
            goto L_0x00b9
        L_0x0099:
            java.lang.String r8 = TAG     // Catch:{ all -> 0x00b7 }
            java.lang.String r7 = r0.concat(r7)     // Catch:{ all -> 0x00b7 }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r8, (java.lang.String) r7)     // Catch:{ all -> 0x00b7 }
            if (r3 == 0) goto L_0x00ac
            r3.close()     // Catch:{ IOException -> 0x00a8 }
            goto L_0x00ac
        L_0x00a8:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00ac:
            if (r5 == 0) goto L_0x00e7
        L_0x00ae:
            r5.close()     // Catch:{ IOException -> 0x00b2 }
            goto L_0x00e7
        L_0x00b2:
            r7 = move-exception
            r7.printStackTrace()
            goto L_0x00e7
        L_0x00b7:
            r7 = move-exception
            goto L_0x00c9
        L_0x00b9:
            r7.printStackTrace()     // Catch:{ all -> 0x00b7 }
            if (r3 == 0) goto L_0x00c6
            r3.close()     // Catch:{ IOException -> 0x00c2 }
            goto L_0x00c6
        L_0x00c2:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00c6:
            if (r5 == 0) goto L_0x00e7
            goto L_0x00ae
        L_0x00c9:
            if (r3 == 0) goto L_0x00d3
            r3.close()     // Catch:{ IOException -> 0x00cf }
            goto L_0x00d3
        L_0x00cf:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00d3:
            if (r5 == 0) goto L_0x00dd
            r5.close()     // Catch:{ IOException -> 0x00d9 }
            goto L_0x00dd
        L_0x00d9:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00dd:
            throw r7
        L_0x00de:
            java.lang.String r8 = "not supported file format: "
            java.lang.String r7 = r8.concat(r7)
            com.samsung.android.sum.core.SLog.w((java.lang.String) r2, (java.lang.String) r7)
        L_0x00e7:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.MetaDataUtil.copyMetadataAndExif(java.lang.String, java.lang.String, java.util.function.Consumer):boolean");
    }

    public static ArrayList<ByteBuffer> getAppNMetadata(FileInputStream fileInputStream) {
        SLog.d(TAG, "getAppNMetadata E");
        ArrayList<ByteBuffer> arrayList = new ArrayList<>();
        byte[] bArr = new byte[1024];
        try {
            fileInputStream.getChannel().position(0);
            fileInputStream.read(bArr, 0, 2);
            while (true) {
                if (fileInputStream.read(bArr, 0, 2) <= 0) {
                    break;
                }
                int[] iArr = {bArr[0] & 255, bArr[1] & 255};
                String str = TAG;
                SLog.d(str, "marker: " + Integer.toHexString(iArr[0]) + Integer.toHexString(iArr[1]));
                if (iArr[0] == 255) {
                    int i2 = iArr[1];
                    if (208 > i2 || 215 < i2) {
                        fileInputStream.read(bArr, 0, 2);
                        byte b = (255 & bArr[1]) | ((bArr[0] & 255) << 8);
                        int i7 = iArr[1];
                        if (226 <= i7 && 239 >= i7) {
                            SLog.d(str, "add APP" + (iArr[1] & 15) + " meta(" + b + ')');
                            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(b + 2);
                            allocateDirect.put((byte) iArr[0]);
                            allocateDirect.put((byte) iArr[1]);
                            allocateDirect.put(bArr, 0, 2);
                            fileInputStream.getChannel().read(allocateDirect);
                            allocateDirect.rewind();
                            arrayList.add(allocateDirect);
                        } else if (i7 == 218) {
                            SLog.d(str, "EOS reached");
                            break;
                        } else {
                            fileInputStream.skip((long) (b - 2));
                        }
                    }
                } else {
                    throw new IllegalArgumentException("this is not valid markers");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        SLog.d(TAG, "getAppNMetadata X");
        return arrayList;
    }

    public static String[] getExifTags() {
        return exifTags;
    }

    public static void setAppNMetadata(ArrayList<ByteBuffer> arrayList, RandomAccessFile randomAccessFile) {
        SLog.d(TAG, "setICCProfile E");
        try {
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(((int) channel.size()) + arrayList.stream().mapToInt(new C0545b(23)).sum());
            allocateDirect.put((byte) -1);
            allocateDirect.put((byte) -40);
            Iterator<ByteBuffer> it = arrayList.iterator();
            while (it.hasNext()) {
                allocateDirect.put(it.next());
            }
            channel.position(2);
            channel.read(allocateDirect);
            channel.position(0);
            allocateDirect.rewind();
            channel.write(allocateDirect);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SLog.d(TAG, "setICCProfile X");
    }
}
