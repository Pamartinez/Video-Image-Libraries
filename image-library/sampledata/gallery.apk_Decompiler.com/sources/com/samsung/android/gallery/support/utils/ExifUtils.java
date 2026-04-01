package com.samsung.android.gallery.support.utils;

import A.a;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.support.utils.ExifApiImpl;
import com.samsung.android.gallery.support.utils.ExifCompat;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ExifUtils {
    static final boolean SUPPORT_ORIENTATION_TAG = Features.isEnabled(Features.SUPPORT_SEC_MP_ORIENTATION_TAG);

    public static String[] changeDate(String str, String str2, String str3, boolean z) {
        boolean z3;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long dateModified = FileUtils.getDateModified(str);
            ExifApiImpl.ExifApi exifApi = ExifApiImpl.instance;
            String str4 = str3;
            boolean z7 = z;
            String[] changeDate = exifApi.changeDate(str, str2, str4, z7);
            if (changeDate != null) {
                FileUtils.adjustDateModified(str, dateModified);
            }
            StringBuilder sb2 = new StringBuilder("changeDate");
            String tag = exifApi.tag();
            if (changeDate != null) {
                z3 = true;
            } else {
                z3 = false;
            }
            String str5 = tag;
            sb2.append(Logger.vt(str5, Boolean.valueOf(z3), str2, str4, Boolean.valueOf(z7), "", Long.valueOf(currentTimeMillis)));
            Log.d("ExifUtils", sb2.toString());
            return changeDate;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean changeDateLocation(String str, String[] strArr, double d, double d2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long dateModified = FileUtils.getDateModified(str);
            ExifApiImpl.ExifApi exifApi = ExifApiImpl.instance;
            boolean changeDateLocation = exifApi.changeDateLocation(str, strArr, d, d2);
            if (changeDateLocation) {
                FileUtils.adjustDateModified(str, dateModified);
            }
            Log.d("ExifUtils", "changeDateLocation" + Logger.vt(exifApi.tag(), Boolean.valueOf(changeDateLocation), "", Long.valueOf(currentTimeMillis)));
            return changeDateLocation;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeDateTime(String str, long j2) {
        return changeDateTime(str, new String[]{TimeUtil.getExifDateTime(j2), TimeUtil.getSystemTimeZoneOffsetTag(), String.valueOf(j2 % 1000)});
    }

    public static boolean changeLocation(String str, double d, double d2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long dateModified = FileUtils.getDateModified(str);
            ExifApiImpl.ExifApi exifApi = ExifApiImpl.instance;
            String str2 = str;
            boolean changeLocation = exifApi.changeLocation(str2, d, d2);
            if (changeLocation) {
                FileUtils.adjustDateModified(str2, dateModified);
            }
            Log.d("ExifUtils", "changeLocation" + Logger.vt(exifApi.tag(), Boolean.valueOf(changeLocation), "", Long.valueOf(currentTimeMillis)));
            return changeLocation;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeModel(String str, String str2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long dateModified = FileUtils.getDateModified(str);
            ExifApiImpl.ExifApi exifApi = ExifApiImpl.instance;
            boolean changeModel = exifApi.changeModel(str, str2);
            if (changeModel) {
                FileUtils.adjustDateModified(str, dateModified);
            }
            Log.d("ExifUtils", "changeModel" + Logger.vt(exifApi.tag(), Boolean.valueOf(changeModel), "", Long.valueOf(currentTimeMillis)));
            return changeModel;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeOrientation(String str, int i2) {
        RandomAccessFile randomAccessFile;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            randomAccessFile = new RandomAccessFile(new SecureFile(str), "rw");
            boolean saveAttributes = new ExifCompat(randomAccessFile.getFD()).setOrientation(i2).saveAttributes();
            if (saveAttributes) {
                FileUtils.truncateFile(randomAccessFile);
            }
            Log.d("ExifUtils", "changeOrientation" + Logger.vt("fd", Boolean.valueOf(saveAttributes), Integer.valueOf(i2), Long.valueOf(currentTimeMillis)));
            randomAccessFile.close();
            return saveAttributes;
        } catch (Error | Exception e) {
            Log.e((CharSequence) "ExifUtils", "changeOrientation failed", e);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static boolean copy(String str, String str2, int i2, int i7) {
        if (str == null || str2 == null) {
            return false;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ExifApiImpl.ExifApi exifApi = ExifApiImpl.instance;
            boolean copy = exifApi.copy(str, str2, i2, i7);
            Log.d("ExifUtils", "copy" + Logger.vt(exifApi.tag(), Boolean.valueOf(copy), "", Long.valueOf(currentTimeMillis)));
            return copy;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyDateLocation(ExifInterface exifInterface, String str) {
        if (exifInterface == null || str == null) {
            return false;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ExifApiImpl.ExifApi exifApi = ExifApiImpl.instance;
            boolean copyDateLocation = exifApi.copyDateLocation(exifInterface, str);
            Log.d("ExifUtils", "copyDateLocation" + Logger.vt(exifApi.tag(), Boolean.valueOf(copyDateLocation), "", Long.valueOf(currentTimeMillis)));
            return copyDateLocation;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyDateLocationIfAbsent(String str, String str2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ExifApiImpl.ExifApi exifApi = ExifApiImpl.instance;
            boolean copyDateLocationIfAbsent = exifApi.copyDateLocationIfAbsent(str, str2);
            Log.d("ExifUtils", "copyDateLocationIfEmpty" + Logger.vt(exifApi.tag(), Boolean.valueOf(copyDateLocationIfAbsent), "", Long.valueOf(currentTimeMillis)));
            return copyDateLocationIfAbsent;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String[] fixDate(String str) {
        boolean z;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long dateModified = FileUtils.getDateModified(str);
            ExifApiImpl.ExifApi exifApi = ExifApiImpl.instance;
            String[] fixDate = exifApi.fixDate(str);
            if (fixDate != null) {
                FileUtils.adjustDateModified(str, dateModified);
            }
            StringBuilder sb2 = new StringBuilder("fixDate");
            String tag = exifApi.tag();
            if (fixDate != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(Logger.vt(tag, Boolean.valueOf(z), "", Long.valueOf(currentTimeMillis)));
            Log.d("ExifUtils", sb2.toString());
            return fixDate;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] fixDateFromFilename(String str) {
        try {
            ExifCompat exifCompat = new ExifCompat(str);
            if (exifCompat.fixDate(false)) {
                return exifCompat.getDateTimeOriginal();
            }
            return null;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getDateTimeOriginal(ExifInterface exifInterface) {
        String attribute = exifInterface.getAttribute("DateTimeOriginal");
        if (!ExifCompat.DateTimeExif.match(attribute)) {
            return -1;
        }
        long millis = ExifCompat.DateTimeUtc.toMillis(attribute);
        if (millis > 0) {
            return millis + getTimeSubSecOriginal(exifInterface);
        }
        return millis;
    }

    public static ExifInterface getExif(String str, boolean z) {
        if (z) {
            str = SecureDigitalPolicy.getWritablePath(str);
        }
        try {
            return new ExifInterface(str);
        } catch (Exception | OutOfMemoryError | StackOverflowError e) {
            a.z(e, new StringBuilder("getExif failed  e="), "ExifUtils");
            return null;
        }
    }

    public static int getHeight(ExifInterface exifInterface) {
        if (exifInterface != null) {
            return exifInterface.getAttributeInt("ImageLength", 0);
        }
        return 0;
    }

    public static int getOrientation(ExifInterface exifInterface) {
        if (exifInterface != null) {
            return ExifTag.toOrientation(exifInterface.getAttributeInt("Orientation", 1));
        }
        return 0;
    }

    public static int getOrientationTag(ExifInterface exifInterface) {
        if (exifInterface != null) {
            return exifInterface.getAttributeInt("Orientation", 1);
        }
        return 0;
    }

    public static byte[] getThumbnailBytes(String str) {
        ExifInterface exif = getExif(str);
        if (exif != null) {
            return exif.getThumbnailBytes();
        }
        return null;
    }

    private static long getTimeSubSecOriginal(ExifInterface exifInterface) {
        if (exifInterface == null) {
            return 0;
        }
        return toSubSecTime(exifInterface.getAttribute("SubSecTimeOriginal"));
    }

    public static int getWidth(ExifInterface exifInterface) {
        if (exifInterface != null) {
            return exifInterface.getAttributeInt("ImageWidth", 0);
        }
        return 0;
    }

    public static boolean isHorizontalMirror(int i2) {
        if (!SUPPORT_ORIENTATION_TAG) {
            return false;
        }
        if (i2 == 2 || i2 == 4 || i2 == 5 || i2 == 7) {
            return true;
        }
        return false;
    }

    public static long toSubSecTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            long parseLong = Long.parseLong(str);
            while (parseLong > 1000) {
                parseLong /= 10;
            }
            return parseLong;
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static int getOrientation(byte[] bArr) {
        if (bArr != null) {
            return getOrientation(getExif(bArr));
        }
        return 0;
    }

    public static boolean changeDateTime(String str, String[] strArr) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long dateModified = FileUtils.getDateModified(str);
            ExifApiImpl.ExifApi exifApi = ExifApiImpl.instance;
            boolean changeDateTime = exifApi.changeDateTime(str, strArr);
            if (changeDateTime) {
                FileUtils.adjustDateModified(str, dateModified);
            }
            Log.d("ExifUtils", "changeDateTime" + Logger.vt(exifApi.tag(), Boolean.valueOf(changeDateTime), "", Long.valueOf(currentTimeMillis)));
            return changeDateTime;
        } catch (Error | Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ExifInterface getExif(String str) {
        return getExif(str, false);
    }

    public static ExifInterface getExif(InputStream inputStream) {
        try {
            return new ExifInterface(inputStream);
        } catch (Exception | OutOfMemoryError | StackOverflowError e) {
            a.z(e, new StringBuilder("getExif(InputStream) failed e="), "ExifUtils");
            return null;
        }
    }

    public static ExifInterface getExif(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            ExifInterface exifInterface = new ExifInterface((InputStream) byteArrayInputStream);
            byteArrayInputStream.close();
            return exifInterface;
        } catch (Exception | OutOfMemoryError | StackOverflowError e) {
            Log.e("ExifUtils", "getExif(byte[]) failed e=" + e);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
