package com.samsung.android.gallery.support.utils;

import A.a;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.ExifCompat;
import com.samsung.android.ocr.MOCRLang;
import i.C0212a;
import java.io.File;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExifTag {
    private static final String[] ORIENTATION_TAG = {"Undefined", "Normal", "Mirror horizontal", "Rotate 180", "Mirror vertical", "Transpose", "Rotate 90 CW", "Transverse", "Rotate 270 CW"};
    static final String[] values = {"Orientation", "ImageWidth", "ImageLength", "DateTime", "OffsetTime", "SubSecTime", "DateTimeOriginal", "OffsetTimeOriginal", "SubSecTimeOriginal", "DateTimeDigitized", "OffsetTimeDigitized", "SubSecTimeDigitized", "GPSDateStamp", "GPSTimeStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSAltitude", "GPSAltitudeRef", "GPSProcessingMethod", "Flash", "FNumber", "PhotographicSensitivity", "ExposureTime", "Make", "Model", "FocalLength", "WhiteBalance", "ShutterSpeedValue", "Software"};
    public String aperture;
    public String colorSpace;
    public String digitizedTime;
    public String digitizedTimeOffset;
    public String digitizedTimeSubSec;
    public String exposureBias;
    public String exposureTime;
    public Boolean flash;
    public Double focalLength;
    public Integer focalLength35mm;
    public String gpsTime;
    public String iso;
    public String lensInfo;
    public double[] location;
    public String make;
    public byte[] makerNote;
    public String model;
    public String modifiedTime;
    public String orientation;
    public String originalTime;
    public String originalTimeOffset;
    public String originalTimeSubSec;
    public String shutterSpeed;
    public String software;
    public String takenTime;
    public String takenTimeOffset;
    public String takenTimeSubSec;
    public String utcTime;
    public Boolean whiteBalance;

    public ExifTag(ExifInterface exifInterface) {
        this(exifInterface, false);
    }

    public static String getAperture(ExifInterface exifInterface) {
        String attribute = exifInterface.getAttribute("FNumber");
        if (attribute != null) {
            return String.format(Locale.getDefault(), "F%.1f", new Object[]{Float.valueOf(UnsafeCast.toFloat(attribute, 4.8f))});
        }
        return null;
    }

    public static String getColorSpace(ExifInterface exifInterface) {
        int attributeInt = exifInterface.getAttributeInt("ColorSpace", 1);
        if (attributeInt == 1) {
            return "sRGB";
        }
        if (attributeInt == 2) {
            return "Adobe RGB";
        }
        switch (attributeInt) {
            case 65533:
                return "Wide Gamut RGB";
            case 65534:
                return "ICC Profile";
            case 65535:
                return "Uncalibrated";
            default:
                return "Undefined (" + Integer.toHexString(attributeInt) + ")";
        }
    }

    public static String getDateTime(ExifInterface exifInterface) {
        return exifInterface.getAttribute("DateTime");
    }

    public static String getDateTimeDigitized(ExifInterface exifInterface) {
        return exifInterface.getAttribute("DateTimeDigitized");
    }

    public static String getDateTimeGps(ExifInterface exifInterface) {
        String str;
        String attribute = exifInterface.getAttribute("GPSDateStamp");
        if (TextUtils.isEmpty(attribute)) {
            return null;
        }
        String attribute2 = exifInterface.getAttribute("GPSTimeStamp");
        StringBuilder s = C0212a.s(attribute);
        if (attribute2 != null) {
            str = " ".concat(attribute2);
        } else {
            str = "";
        }
        s.append(str);
        return s.toString();
    }

    public static String getDateTimeOriginal(ExifInterface exifInterface) {
        return exifInterface.getAttribute("DateTimeOriginal");
    }

    public static String getExposureBias(ExifInterface exifInterface) {
        if (!exifInterface.hasAttribute("ExposureBiasValue")) {
            return null;
        }
        return String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(exifInterface.getAttributeDouble("ExposureBiasValue", MapUtil.INVALID_LOCATION))});
    }

    public static String getExposureTime(ExifInterface exifInterface) {
        return toReciprocal(exifInterface.getAttribute("ExposureTime"));
    }

    public static Boolean getFlash(ExifInterface exifInterface) {
        String attribute = exifInterface.getAttribute("Flash");
        if (attribute != null) {
            return Boolean.valueOf(isFlashFired(UnsafeCast.toInt(attribute, 0)));
        }
        return null;
    }

    public static Double getFocalLength(ExifInterface exifInterface) {
        double attributeDouble = exifInterface.getAttributeDouble("FocalLength", MapUtil.INVALID_LOCATION);
        if (attributeDouble != MapUtil.INVALID_LOCATION) {
            return Double.valueOf(attributeDouble);
        }
        return null;
    }

    public static Integer getFocalLength35mm(ExifInterface exifInterface) {
        int attributeInt = exifInterface.getAttributeInt("FocalLengthIn35mmFilm", 0);
        if (attributeInt != 0) {
            return Integer.valueOf(attributeInt);
        }
        return null;
    }

    public static String getIso(ExifInterface exifInterface) {
        String attribute = exifInterface.getAttribute("PhotographicSensitivity");
        if (attribute != null) {
            return String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(UnsafeCast.toInt(attribute, 200))});
        }
        return null;
    }

    public static double[] getLocation(ExifInterface exifInterface) {
        if (exifInterface == null) {
            return null;
        }
        try {
            return exifInterface.getLatLong();
        } catch (Exception e) {
            a.s(e, new StringBuilder("getLatLong failed e="), "ExifTag");
            return null;
        }
    }

    public static String getMake(ExifInterface exifInterface) {
        String attribute = exifInterface.getAttribute("Make");
        if (attribute != null) {
            return attribute.trim();
        }
        return null;
    }

    public static byte[] getMakerNote(ExifInterface exifInterface) {
        return exifInterface.getAttributeBytes("MakerNote");
    }

    public static Boolean getManualWhiteBalance(ExifInterface exifInterface) {
        String attribute = exifInterface.getAttribute("WhiteBalance");
        if (attribute != null) {
            return Boolean.valueOf("1".equals(attribute));
        }
        return null;
    }

    public static String getModel(ExifInterface exifInterface) {
        String attribute = exifInterface.getAttribute("Model");
        if (attribute != null) {
            return attribute.trim();
        }
        return null;
    }

    public static String getOrientationReadable(ExifInterface exifInterface) {
        int attributeInt = exifInterface.getAttributeInt("Orientation", 1);
        if (attributeInt >= 0) {
            String[] strArr = ORIENTATION_TAG;
            if (attributeInt < strArr.length) {
                return strArr[attributeInt] + " [" + toOrientation(attributeInt) + "]";
            }
        }
        return C0212a.j(attributeInt, "#", " [0]");
    }

    public static String getSefUtcTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] data = SeApiCompat.getSefFileCompat().getData(new File(str), SefInfo.IMAGE_UTC_DATA.keyName);
            if (data == null || data.length <= 0) {
                return null;
            }
            return ExifCompat.DateTimeUtc.toDateTimeString(UnsafeCast.toLong(new String(data)));
        } catch (Exception e) {
            a.s(e, new StringBuilder("getSefUtcTime failed e="), "ExifTag");
            return null;
        }
    }

    public static String getShutterSpeed(ExifInterface exifInterface) {
        return toReciprocal(exifInterface.getAttribute("ShutterSpeedValue"));
    }

    public static String getSoftware(ExifInterface exifInterface) {
        return exifInterface.getAttribute("Software");
    }

    public static String getTimeDigitizedOffset(ExifInterface exifInterface) {
        return exifInterface.getAttribute("OffsetTimeDigitized");
    }

    public static String getTimeDigitizedSubSec(ExifInterface exifInterface) {
        return exifInterface.getAttribute("SubSecTimeDigitized");
    }

    public static String getTimeOffset(ExifInterface exifInterface) {
        return exifInterface.getAttribute("OffsetTime");
    }

    public static String getTimeOriginalOffset(ExifInterface exifInterface) {
        return exifInterface.getAttribute("OffsetTimeOriginal");
    }

    public static String getTimeOriginalSubSec(ExifInterface exifInterface) {
        return exifInterface.getAttribute("SubSecTimeOriginal");
    }

    public static String getTimeSubSec(ExifInterface exifInterface) {
        return exifInterface.getAttribute("SubSecTime");
    }

    private static boolean isFlashFired(int i2) {
        if ((i2 & 1) != 0) {
            return true;
        }
        return false;
    }

    public static void removeXmp(ExifInterface exifInterface) {
        if (exifInterface.getAttribute("Xmp") != null) {
            exifInterface.setAttribute("Xmp", "");
        }
    }

    public static String toGeoDegree(double d) {
        double abs = Math.abs(d);
        long j2 = (long) abs;
        double d2 = abs - ((double) j2);
        long j3 = (long) (d2 * 60.0d);
        long round = Math.round((d2 - (((double) j3) / 60.0d)) * 3600.0d * 1.0E7d);
        return j2 + "/1," + j3 + "/1," + round + "/10000000";
    }

    public static int toOrientation(int i2) {
        switch (i2) {
            case 3:
            case 4:
                return MOCRLang.KHMER;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public static String toOrientationTag(int i2) {
        if (i2 == 90) {
            return String.valueOf(6);
        }
        if (i2 == 180) {
            return String.valueOf(3);
        }
        if (i2 != 270) {
            return String.valueOf(1);
        }
        return String.valueOf(8);
    }

    public static String toReciprocal(String str) {
        double d;
        long j2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (str.contains("/")) {
                String[] split = str.split("/", 2);
                d = Double.parseDouble(split[0]) / Double.parseDouble(split[1]);
            } else {
                d = Double.parseDouble(str);
            }
            int i2 = (d > 1.0d ? 1 : (d == 1.0d ? 0 : -1));
            double d2 = MapUtil.INVALID_LOCATION;
            if (i2 < 0) {
                if (d == MapUtil.INVALID_LOCATION) {
                    j2 = 0;
                } else {
                    j2 = (long) ((1.0d / d) + 0.5d);
                }
                if (j2 == 59) {
                    j2 = 60;
                } else if (j2 == 91) {
                    j2 = 90;
                } else if (j2 == 179) {
                    j2 = 180;
                } else if (j2 == 345) {
                    j2 = 350;
                } else if (j2 == 769) {
                    j2 = 750;
                } else if (j2 == 1429) {
                    j2 = 1500;
                }
                if (j2 == 0) {
                    return "";
                }
                return String.format(Locale.getDefault(), "%d/%d s", new Object[]{1, Long.valueOf(j2)});
            }
            long j3 = (long) d;
            double d3 = d - ((double) j3);
            long j8 = (long) ((1.0d / d3) + 0.5d);
            if (j8 == 1) {
                j3++;
            } else {
                d2 = d3;
            }
            String str2 = j3 + "''";
            if (d2 <= 1.0E-4d) {
                return str2;
            }
            return str2 + String.format(Locale.getDefault(), " %d/%d s", new Object[]{1, Long.valueOf(j8)});
        } catch (NumberFormatException unused) {
            Log.e((CharSequence) "ExifTag", "toReciprocal failed", str);
            return null;
        }
    }

    public long getDateTimeOriginalMilli() {
        if (TextUtils.isEmpty(this.originalTime) || !ExifCompat.DateTimeExif.match(this.originalTime)) {
            return -1;
        }
        return ExifCompat.DateTimeUtc.toMillis(this.originalTime);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ExifTag");
        sb2.append(Logger.v("flash=" + this.flash, "make=" + this.make, "model=" + this.model, "aperture=" + this.aperture, "iso=" + this.iso, "wb=" + this.whiteBalance, "exposerTime=" + this.exposureTime, "exposureBias=" + this.exposureBias, "shutterSpeed=" + this.shutterSpeed, "focalLength=" + this.focalLength, "makerNote=" + StringCompat.valueOf(this.makerNote, 14), "focalLength35mm=" + this.focalLength35mm + ""));
        return sb2.toString();
    }

    public ExifTag(ExifInterface exifInterface, boolean z) {
        if (exifInterface != null) {
            this.flash = getFlash(exifInterface);
            this.make = getMake(exifInterface);
            this.model = getModel(exifInterface);
            this.aperture = getAperture(exifInterface);
            this.iso = getIso(exifInterface);
            this.whiteBalance = getManualWhiteBalance(exifInterface);
            this.exposureTime = getExposureTime(exifInterface);
            this.exposureBias = getExposureBias(exifInterface);
            this.shutterSpeed = getShutterSpeed(exifInterface);
            this.focalLength = getFocalLength(exifInterface);
            this.focalLength35mm = getFocalLength35mm(exifInterface);
            this.location = getLocation(exifInterface);
            this.makerNote = getMakerNote(exifInterface);
            this.originalTime = getDateTimeOriginal(exifInterface);
            this.originalTimeSubSec = getTimeOriginalSubSec(exifInterface);
            this.originalTimeOffset = getTimeOriginalOffset(exifInterface);
            if (z) {
                this.orientation = getOrientationReadable(exifInterface);
                this.colorSpace = getColorSpace(exifInterface);
                this.takenTime = getDateTime(exifInterface);
                this.takenTimeSubSec = getTimeSubSec(exifInterface);
                this.takenTimeOffset = getTimeOffset(exifInterface);
                this.gpsTime = getDateTimeGps(exifInterface);
                this.digitizedTime = getDateTimeDigitized(exifInterface);
                this.digitizedTimeSubSec = getTimeDigitizedSubSec(exifInterface);
                this.digitizedTimeOffset = getTimeDigitizedOffset(exifInterface);
                this.software = getSoftware(exifInterface);
            }
        }
    }
}
