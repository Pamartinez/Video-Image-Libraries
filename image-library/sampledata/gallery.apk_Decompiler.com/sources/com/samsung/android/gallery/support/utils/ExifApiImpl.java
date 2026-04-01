package com.samsung.android.gallery.support.utils;

import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.support.config.SdkConfig;
import java.io.RandomAccessFile;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ExifApiImpl {
    static final ExifApi instance;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ExifApi {
        String[] changeDate(String str, String str2, String str3, boolean z);

        boolean changeDateLocation(String str, String[] strArr, double d, double d2);

        boolean changeDateTime(String str, String[] strArr);

        boolean changeLocation(String str, double d, double d2);

        boolean changeModel(String str, String str2);

        boolean copy(String str, String str2, int i2, int i7);

        boolean copyDateLocation(ExifInterface exifInterface, String str);

        boolean copyDateLocationIfAbsent(String str, String str2);

        String[] fixDate(String str);

        String tag() {
            return "file";
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExifApiFd implements ExifApi {
        public String[] changeDate(String str, String str2, String str3, boolean z) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new SecureFile(str), "rw");
            try {
                ExifCompat exifCompat = new ExifCompat(randomAccessFile.getFD(), str);
                if (exifCompat.changeDate(str2, str3, z)) {
                    FileUtils.truncateFile(randomAccessFile);
                    String[] dateTimeOriginal = exifCompat.getDateTimeOriginal();
                    randomAccessFile.close();
                    return dateTimeOriginal;
                }
                randomAccessFile.close();
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public boolean changeDateLocation(String str, String[] strArr, double d, double d2) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new SecureFile(str), "rw");
            try {
                if (new ExifCompat(randomAccessFile.getFD()).setDateTime(strArr).setLocation(d, d2).saveAttributes()) {
                    FileUtils.truncateFile(randomAccessFile);
                    randomAccessFile.close();
                    return true;
                }
                randomAccessFile.close();
                return false;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public boolean changeDateTime(String str, String[] strArr) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new SecureFile(str), "rw");
            try {
                if (new ExifCompat(randomAccessFile.getFD(), str).setDateTime(strArr).saveAttributes()) {
                    FileUtils.truncateFile(randomAccessFile);
                    randomAccessFile.close();
                    return true;
                }
                randomAccessFile.close();
                return false;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public boolean changeLocation(String str, double d, double d2) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new SecureFile(str), "rw");
            try {
                if (new ExifCompat(randomAccessFile.getFD()).setLocation(d, d2).saveAttributes()) {
                    FileUtils.truncateFile(randomAccessFile);
                    randomAccessFile.close();
                    return true;
                }
                randomAccessFile.close();
                return false;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public boolean changeModel(String str, String str2) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new SecureFile(str), "rw");
            try {
                if (new ExifCompat(randomAccessFile.getFD()).setModel(str2).saveAttributes()) {
                    FileUtils.truncateFile(randomAccessFile);
                    randomAccessFile.close();
                    return true;
                }
                randomAccessFile.close();
                return false;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public boolean copy(String str, String str2, int i2, int i7) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new SecureFile(str2), "rw");
            try {
                if (ExifCompat.copy(new ExifCompat(str), new ExifCompat(randomAccessFile.getFD(), str2), i2, i7)) {
                    FileUtils.truncateFile(randomAccessFile);
                    randomAccessFile.close();
                    return true;
                }
                randomAccessFile.close();
                return false;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public boolean copyDateLocation(ExifInterface exifInterface, String str) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new SecureFile(str), "rw");
            try {
                if (new ExifCompat(randomAccessFile.getFD()).copyDateTimeLocation(new ExifCompat(exifInterface)).saveAttributes()) {
                    FileUtils.truncateFile(randomAccessFile);
                    randomAccessFile.close();
                    return true;
                }
                randomAccessFile.close();
                return false;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public boolean copyDateLocationIfAbsent(String str, String str2) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new SecureFile(str2), "rw");
            try {
                ExifCompat exifCompat = new ExifCompat(randomAccessFile.getFD());
                if (exifCompat.isValid() && !exifCompat.hasDateLocation()) {
                    ExifCompat exifCompat2 = new ExifCompat(str);
                    exifCompat.recoverDateTime(exifCompat2);
                    exifCompat.setLocation(exifCompat2.getLocation());
                    if (!exifCompat.hasAttribute("DateTimeOriginal")) {
                        exifCompat.setDateTimeGps(ExifTag.getSefUtcTime(str));
                    }
                    if (exifCompat.saveAttributes()) {
                        FileUtils.truncateFile(randomAccessFile);
                        randomAccessFile.close();
                        return true;
                    }
                }
                randomAccessFile.close();
                return false;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public String[] fixDate(String str) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new SecureFile(str), "rw");
            try {
                ExifCompat exifCompat = new ExifCompat(randomAccessFile.getFD(), str);
                if (exifCompat.fixDate()) {
                    FileUtils.truncateFile(randomAccessFile);
                    String[] dateTimeOriginal = exifCompat.getDateTimeOriginal();
                    randomAccessFile.close();
                    return dateTimeOriginal;
                }
                randomAccessFile.close();
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public String tag() {
            return "fd";
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExifApiFile implements ExifApi {
        public String[] changeDate(String str, String str2, String str3, boolean z) {
            ExifCompat exifCompat = new ExifCompat(str);
            if (exifCompat.changeDate(str2, str3, z)) {
                return exifCompat.getDateTimeOriginal();
            }
            return null;
        }

        public boolean changeDateLocation(String str, String[] strArr, double d, double d2) {
            return new ExifCompat(str).setDateTime(strArr).setLocation(d, d2).saveAttributes();
        }

        public boolean changeDateTime(String str, String[] strArr) {
            return new ExifCompat(str).setDateTime(strArr).saveAttributes();
        }

        public boolean changeLocation(String str, double d, double d2) {
            return new ExifCompat(str).setLocation(d, d2).saveAttributes();
        }

        public boolean changeModel(String str, String str2) {
            return new ExifCompat(str).setModel(str2).saveAttributes();
        }

        public boolean copy(String str, String str2, int i2, int i7) {
            return ExifCompat.copy(new ExifCompat(str), new ExifCompat(str2), i2, i7);
        }

        public boolean copyDateLocation(ExifInterface exifInterface, String str) {
            return new ExifCompat(str).copyDateTimeLocation(new ExifCompat(exifInterface)).saveAttributes();
        }

        public boolean copyDateLocationIfAbsent(String str, String str2) {
            ExifCompat exifCompat = new ExifCompat(str2);
            if (!exifCompat.isValid() || exifCompat.hasDateLocation()) {
                return false;
            }
            ExifCompat exifCompat2 = new ExifCompat(str);
            exifCompat.recoverDateTime(exifCompat2);
            exifCompat.setLocation(exifCompat2.getLocation());
            if (!exifCompat.hasAttribute("DateTimeOriginal")) {
                exifCompat.setDateTimeGps(ExifTag.getSefUtcTime(str));
            }
            return exifCompat.saveAttributes();
        }

        public String[] fixDate(String str) {
            ExifCompat exifCompat = new ExifCompat(str);
            if (exifCompat.fixDate()) {
                return exifCompat.getDateTimeOriginal();
            }
            return null;
        }
    }

    static {
        ExifApi exifApi;
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            exifApi = new ExifApiFd();
        } else {
            exifApi = new ExifApiFile();
        }
        instance = exifApi;
    }
}
