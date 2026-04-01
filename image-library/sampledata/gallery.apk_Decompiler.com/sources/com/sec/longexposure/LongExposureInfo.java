package com.sec.longexposure;

import android.graphics.YuvImage;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.location.Location;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.MimeType;
import com.samsung.android.motionphoto.utils.v2.MotionPhotoInfo;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import com.samsung.android.sum.core.types.CodecType;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongExposureInfo {
    private String mDate = null;
    private String mDateOrigin = null;
    private Float mExifLatitude = null;
    private Float mExifLongitude = null;
    int mHeight = 0;
    private int mImageRotation = 0;
    private String mLatitude = null;
    private String mLongitude = null;
    private String mMake = null;
    private MimeType mMimeType;
    private String mModel = null;
    int mWidth = 0;

    private static ByteBuffer getByteBuffer(File file) {
        try {
            byte[] readAllBytes = Files.readAllBytes(file.toPath());
            int length = readAllBytes.length - 8;
            byte[] bArr = new byte[length];
            System.arraycopy(readAllBytes, 6, bArr, 0, length);
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(length);
            allocateDirect.put(bArr);
            if (!file.delete()) {
                Log.i("LongExposureInfo", "Failed to delete temp file");
            }
            return allocateDirect;
        } catch (IOException unused) {
            Log.e("LongExposureInfo", "MediaBuffer IO Error");
            if (file.delete()) {
                return null;
            }
            Log.i("LongExposureInfo", "Failed to delete temp file");
            return null;
        } catch (Throwable th) {
            if (!file.delete()) {
                Log.i("LongExposureInfo", "Failed to delete temp file");
            }
            throw th;
        }
    }

    private int getOrientation(YuvImage yuvImage, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(0, 1);
        hashMap.put(90, 6);
        hashMap.put(Integer.valueOf(MOCRLang.KHMER), 3);
        hashMap.put(270, 8);
        if (hashMap.get(Integer.valueOf(i2)) == null) {
            return 1;
        }
        int intValue = ((Integer) hashMap.get(Integer.valueOf(i2))).intValue();
        StringBuilder o2 = C0086a.o(i2, "rotation of video: ", ", photo: ");
        o2.append(this.mImageRotation);
        Log.i("LongExposureInfo", o2.toString());
        int i7 = this.mImageRotation;
        if (i2 != i7) {
            if ((i2 == 90 || i2 == 270) && (i7 == 0 || i7 == 180)) {
                int i8 = this.mHeight;
                this.mHeight = (yuvImage.getHeight() * i8) / yuvImage.getWidth();
                this.mWidth = i8;
            } else if ((i2 == 0 || i2 == 180) && (i7 == 90 || i7 == 270)) {
                int i10 = this.mWidth;
                this.mWidth = (yuvImage.getWidth() * i10) / yuvImage.getHeight();
                this.mHeight = i10;
                return intValue;
            }
        }
        return intValue;
    }

    public static long retrieveDuration(String str) {
        MediaMetadataRetriever mediaMetadataRetriever;
        Log.i("LongExposureInfo", "Retrieve duration info");
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
            if (extractMetadata != null) {
                Log.i("LongExposureInfo", "Retrieved duration: ".concat(extractMetadata));
                long parseLong = Long.parseLong(extractMetadata);
                mediaMetadataRetriever.close();
                return parseLong;
            }
            mediaMetadataRetriever.close();
            return 0;
        } catch (IOException | IllegalArgumentException e) {
            Log.e("LongExposureInfo", "Failed to retrieve duration", e);
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void setLocation(ExifInterface exifInterface) {
        String str;
        String str2;
        String str3;
        String str4;
        if (this.mExifLongitude != null && this.mExifLatitude != null) {
            Log.i("LongExposureInfo", "Set gps data to exif");
            String[] split = Location.convert((double) Math.abs(this.mExifLatitude.floatValue()), 2).split(NumericEnum.SEP);
            String[] split2 = split[2].split("\\.");
            if (split2.length == 0) {
                str = split[2];
            } else {
                str = split2[0];
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(split[0]);
            sb2.append("/1,");
            String q = C0212a.q(sb2, split[1], "/1,", str, "/1");
            String[] split3 = Location.convert((double) Math.abs(this.mExifLongitude.floatValue()), 2).split(NumericEnum.SEP);
            String[] split4 = split3[2].split("\\.");
            if (split4.length == 0) {
                str2 = split3[2];
            } else {
                str2 = split4[0];
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(split3[0]);
            sb3.append("/1,");
            String q10 = C0212a.q(sb3, split3[1], "/1,", str2, "/1");
            if (this.mExifLatitude.floatValue() > 0.0f) {
                str3 = "N";
            } else {
                str3 = "S";
            }
            exifInterface.setAttribute("GPSLatitudeRef", str3);
            exifInterface.setAttribute("GPSLatitude", q);
            if (this.mExifLongitude.floatValue() > 0.0f) {
                str4 = "E";
            } else {
                str4 = "W";
            }
            exifInterface.setAttribute("GPSLongitudeRef", str4);
            exifInterface.setAttribute("GPSLongitude", q10);
        }
    }

    public int extractMotionPhotoInfo(String str, String str2) {
        try {
            MotionPhotoInfo parse = MotionPhotoInfo.parse(new File(str));
            long videoDurationMs = parse.getVideoInfo().getVideoDurationMs();
            if (videoDurationMs <= 0) {
                Log.w("LongExposureInfo", "Invalid motion photo duration: " + videoDurationMs);
                return -1;
            }
            this.mWidth = parse.getImageWidth();
            this.mHeight = parse.getImageHeight();
            this.mImageRotation = parse.getExifInfo().getRotation();
            this.mMimeType = parse.getImageMimeType();
            long video = parse.getVideo(new File(str2));
            if (video <= 0) {
                Log.w("LongExposureInfo", "Invalid motion photo video size: " + video);
                return -1;
            }
            Log.i("LongExposureInfo", "Motion photo info: " + this.mWidth + "x" + this.mHeight + ", rotation: " + this.mImageRotation + ", size: " + video);
            ExifInterface exifInterface = new ExifInterface(str);
            this.mMake = exifInterface.getAttribute("Make");
            this.mModel = exifInterface.getAttribute("Model");
            this.mDateOrigin = exifInterface.getAttribute("DateTimeOriginal");
            float[] fArr = new float[2];
            if (exifInterface.getLatLong(fArr)) {
                this.mExifLatitude = Float.valueOf(fArr[0]);
                this.mExifLongitude = Float.valueOf(fArr[1]);
                Log.i("LongExposureInfo", "Gps data from exif");
            }
            return 0;
        } catch (Exception e) {
            Log.w("LongExposureInfo", "Failed to read exif info", e);
            return -1;
        }
    }

    public CodecType getCodecType() {
        CodecType codecType = CodecType.NONE;
        MimeType mimeType = this.mMimeType;
        if (mimeType == MimeType.IMAGE_JPEG) {
            return CodecType.JPEG_QURAM;
        }
        if (mimeType == MimeType.IMAGE_HEIC) {
            return CodecType.HEIF;
        }
        Log.e("LongExposureInfo", "Unsupported image mime type: " + this.mMimeType);
        return CodecType.NONE;
    }

    public ByteBuffer getExifData(YuvImage yuvImage, int i2) {
        FileOutputStream fileOutputStream;
        try {
            File createTempFile = File.createTempFile("tempJpeg", "temp");
            try {
                fileOutputStream = new FileOutputStream(createTempFile);
                byte[] bArr = new byte[4];
                System.arraycopy(new byte[]{-1, -40}, 0, bArr, 0, 2);
                System.arraycopy(new byte[]{-1, -39}, 0, bArr, 2, 2);
                fileOutputStream.write(bArr);
                if (this.mWidth == 0) {
                    this.mWidth = yuvImage.getWidth();
                }
                if (this.mHeight == 0) {
                    this.mHeight = yuvImage.getHeight();
                }
                Log.i("LongExposureInfo", "Set exif data: " + this.mWidth + "x" + this.mHeight);
                int orientation = getOrientation(yuvImage, i2);
                ExifInterface exifInterface = new ExifInterface(createTempFile);
                exifInterface.setAttribute("Make", this.mMake);
                exifInterface.setAttribute("Model", this.mModel);
                exifInterface.setAttribute("DateTimeOriginal", this.mDateOrigin);
                exifInterface.setAttribute("Orientation", String.valueOf(orientation));
                exifInterface.setAttribute("ImageWidth", String.valueOf(this.mWidth));
                exifInterface.setAttribute("ImageLength", String.valueOf(this.mHeight));
                exifInterface.setAttribute("PixelXDimension", String.valueOf(this.mWidth));
                exifInterface.setAttribute("PixelYDimension", String.valueOf(this.mHeight));
                setLocation(exifInterface);
                exifInterface.saveAttributes();
                fileOutputStream.close();
                Log.i("LongExposureInfo", "base exif data is prepared");
                ByteBuffer byteBuffer = getByteBuffer(createTempFile);
                if (byteBuffer == null) {
                    return null;
                }
                Log.i("LongExposureInfo", "exif data is prepared");
                return byteBuffer;
            } catch (IOException | IllegalArgumentException e) {
                Log.e("LongExposureInfo", "Failed to write exif data", e);
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        } catch (IOException unused) {
            return null;
        }
    }

    public int retrieveMetadata(String str) {
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            String extractMetadata = mediaMetadataRetriever.extractMetadata(5);
            if (extractMetadata != null) {
                Log.i("LongExposureInfo", "Media utc date: ".concat(extractMetadata));
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss.SSS");
                    simpleDateFormat2.setTimeZone(TimeZone.getDefault());
                    this.mDate = simpleDateFormat2.format(simpleDateFormat.parse(extractMetadata));
                    Log.i("LongExposureInfo", "Media gmt date: " + this.mDate);
                } catch (ParseException unused) {
                    Log.i("LongExposureInfo", "Metadata Date parse error");
                }
            }
            String extractMetadata2 = mediaMetadataRetriever.extractMetadata(23);
            if (extractMetadata2 != null) {
                int lastIndexOf = extractMetadata2.lastIndexOf(47);
                if (lastIndexOf == -1) {
                    Log.i("LongExposureInfo", "Last index not founded");
                    mediaMetadataRetriever.close();
                    return 0;
                }
                String substring = extractMetadata2.substring(0, lastIndexOf);
                int lastIndexOf2 = substring.lastIndexOf(45);
                int lastIndexOf3 = substring.lastIndexOf(43);
                if (lastIndexOf2 >= 0 || lastIndexOf3 >= 0) {
                    int max = Math.max(lastIndexOf2, lastIndexOf3);
                    this.mLatitude = substring.substring(0, max);
                    this.mLongitude = substring.substring(max);
                    Log.i("LongExposureInfo", "Gps info from Metadata");
                } else {
                    Log.i("LongExposureInfo", "Sign index not founded");
                    mediaMetadataRetriever.close();
                    return 0;
                }
            } else {
                Log.i("LongExposureInfo", "Metadata no gps info");
            }
            mediaMetadataRetriever.close();
            return 0;
        } catch (IOException | IllegalArgumentException e) {
            Log.e("LongExposureInfo", "Failed to retrieve metadata", e);
            return -20;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public int writeMetadata(String str) {
        Log.i("LongExposureInfo", "Write metadata");
        if (str == null) {
            Log.e("LongExposureInfo", "Wrong file path");
            return -10;
        } else if (this.mDate == null && (this.mLatitude == null || this.mLongitude == null)) {
            Log.i("LongExposureInfo", "No metadata to write");
            return 0;
        } else {
            MediaMetadataInterface mediaMetadataInterface = new MediaMetadataInterface(str);
            if (!mediaMetadataInterface.isEditable()) {
                Log.w("LongExposureInfo", "File is not editable, ".concat(str));
                return -10;
            }
            if (this.mDate != null) {
                Log.i("LongExposureInfo", "Write date: " + this.mDate);
                mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_CREATION_TIME, this.mDate);
            }
            String str2 = this.mLatitude;
            if (!(str2 == null || this.mLongitude == null)) {
                mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_LATITUDE, str2);
                mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_LONGITUDE, this.mLongitude);
            }
            mediaMetadataInterface.saveAttributes();
            return 0;
        }
    }
}
