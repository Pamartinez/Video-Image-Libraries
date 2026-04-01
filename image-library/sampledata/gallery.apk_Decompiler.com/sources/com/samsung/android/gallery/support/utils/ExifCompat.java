package com.samsung.android.gallery.support.utils;

import A.a;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.File;
import java.io.FileDescriptor;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExifCompat {
    protected boolean mChanged;
    private final ExifInterface mExif;
    protected String mPath;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DateTimeExif {
        static final DateTimeExifMatcher matcher = new DateTimeExifMatcher();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class DateTimeExifMatcher extends DateTimeGeneric.DateTimeMatcher {
            private final Pattern pattern = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})$");

            public Matcher createMatcher(String str) {
                return this.pattern.matcher(str);
            }
        }

        public static String getDateTimeTag(String str) {
            return matcher.getDateTimeTag(str);
        }

        public static boolean match(String str) {
            return matcher.match(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DateTimeGeneric {
        private static final DateTimeMatcher matcher = new DateTimeMatcher();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class DateTimeMatcher {
            private final Pattern pattern = Pattern.compile("(\\d{4})\\D?(\\d{2})\\D?(\\d{2})\\D?(\\d{2})\\D?(\\d{2})\\D?(\\d{2})");

            public Matcher createMatcher(String str) {
                return this.pattern.matcher(str);
            }

            public Matcher find(String str) {
                try {
                    Matcher createMatcher = createMatcher(str);
                    if (!createMatcher.find() || !isDateValid(createMatcher) || !isTimeValid(createMatcher)) {
                        return null;
                    }
                    return createMatcher;
                } catch (Exception e) {
                    Log.e((CharSequence) "DateTimeMatcher", "find failed", (Throwable) e);
                    return null;
                }
            }

            public final String getDateTimeTag(String str) {
                Matcher find;
                if (str == null || str.length() <= 0 || (find = find(str)) == null) {
                    return null;
                }
                return find.group(1) + NumericEnum.SEP + find.group(2) + NumericEnum.SEP + find.group(3) + " " + find.group(4) + NumericEnum.SEP + find.group(5) + NumericEnum.SEP + find.group(6);
            }

            public final boolean isDateValid(Matcher matcher) {
                int parseInt = Integer.parseInt(matcher.group(1));
                int parseInt2 = Integer.parseInt(matcher.group(2));
                int parseInt3 = Integer.parseInt(matcher.group(3));
                if (parseInt < 1900 || parseInt2 < 1 || parseInt2 > 12 || parseInt3 < 1 || parseInt3 > 31) {
                    return false;
                }
                return true;
            }

            public final boolean isTimeValid(Matcher matcher) {
                int parseInt = Integer.parseInt(matcher.group(4));
                int parseInt2 = Integer.parseInt(matcher.group(5));
                int parseInt3 = Integer.parseInt(matcher.group(6));
                if (parseInt < 0 || parseInt >= 24 || parseInt2 < 0 || parseInt2 >= 60 || parseInt3 < 0 || parseInt3 >= 60) {
                    return false;
                }
                return true;
            }

            public final boolean match(String str) {
                if (str == null || find(str) == null) {
                    return false;
                }
                return true;
            }
        }

        public static String getDateTimeTag(String str) {
            return matcher.getDateTimeTag(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DateTimeUtc {
        static volatile SimpleDateFormat FORMATTER;

        private static SimpleDateFormat getInstance() {
            if (FORMATTER == null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                FORMATTER = simpleDateFormat;
            }
            return FORMATTER;
        }

        public static void recycle() {
            FORMATTER = null;
        }

        public static Date toDate(String str) {
            return getInstance().parse(str, new ParsePosition(0));
        }

        public static String toDateTimeString(long j2) {
            return getInstance().format(Long.valueOf(j2));
        }

        public static long toMillis(String str) {
            try {
                return toDate(str).getTime();
            } catch (Exception e) {
                Log.e((CharSequence) "ExifCompat", "parseDateTime failed", (Throwable) e);
                return -1;
            }
        }

        public static String toTimeString(long j2) {
            String str;
            String str2;
            String str3;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            long hours = timeUnit.toHours(j2) % 24;
            long minutes = timeUnit.toMinutes(j2) % 60;
            long seconds = timeUnit.toSeconds(j2) % 60;
            StringBuilder sb2 = new StringBuilder();
            if (hours > 9) {
                str = Long.toString(hours);
            } else {
                str = a.f("0", hours);
            }
            sb2.append(str);
            sb2.append(NumericEnum.SEP);
            if (minutes > 9) {
                str2 = Long.toString(minutes);
            } else {
                str2 = a.f("0", minutes);
            }
            sb2.append(str2);
            sb2.append(NumericEnum.SEP);
            if (seconds > 9) {
                str3 = Long.toString(seconds);
            } else {
                str3 = a.f("0", seconds);
            }
            sb2.append(str3);
            return sb2.toString();
        }

        private static long toTimeZoneOffsetInMillis(String str) {
            long j2;
            if (str != null) {
                try {
                    String[] split = str.split(NumericEnum.SEP);
                    long parseLong = Long.parseLong(split[0]);
                    long parseLong2 = Long.parseLong(split[1]);
                    if (parseLong >= 0) {
                        j2 = (parseLong * 60) + parseLong2;
                    } else {
                        j2 = -((Math.abs(parseLong) * 60) + parseLong2);
                    }
                    return j2 * 60000;
                } catch (Exception e) {
                    Log.e((CharSequence) "ExifCompat", "getTimeZoneOffset {" + str + "}", (Throwable) e);
                }
            }
            return TimeUtil.getSystemTimeZoneOffset();
        }

        public static String toUtcTime(String[] strArr) {
            try {
                return getInstance().format(Long.valueOf(toDate(strArr[0]).getTime() - toTimeZoneOffsetInMillis(strArr[1])));
            } catch (Exception e) {
                Log.e((CharSequence) "ExifCompat", "toUtcTime failed", (Throwable) e);
                return null;
            }
        }
    }

    public ExifCompat(String str) {
        this.mPath = str;
        this.mExif = createExif(str);
    }

    private String[] adjustDateTime(String str, String str2) {
        String str3 = str + ' ' + str2;
        String timeZoneOffset = getTimeZoneOffset();
        if (SdkConfig.lessThan(SdkConfig.SEM.V)) {
            long timeZoneOffsetInMillis = TimeUtil.getTimeZoneOffsetInMillis(timeZoneOffset);
            long exifTimeInMillis = TimeUtil.getExifTimeInMillis(str3);
            long offset = (long) TimeZone.getDefault().getOffset(exifTimeInMillis);
            if (timeZoneOffsetInMillis != offset) {
                str3 = TimeUtil.getExifDateTime((exifTimeInMillis + timeZoneOffsetInMillis) - offset);
            }
        }
        return new String[]{str3, timeZoneOffset};
    }

    public static boolean copy(ExifCompat exifCompat, ExifCompat exifCompat2, int i2, int i7) {
        if (!exifCompat.isValid() || !exifCompat2.isValid()) {
            return false;
        }
        for (String str : ExifTag.values) {
            String attribute = exifCompat.getAttribute(str);
            if (attribute != null) {
                exifCompat2.setAttribute(str, attribute);
            }
        }
        if (i2 > 0) {
            exifCompat2.setAttribute("ImageWidth", String.valueOf(i2));
        }
        if (i7 > 0) {
            exifCompat2.setAttribute("ImageLength", String.valueOf(i7));
        }
        return exifCompat2.saveAttributes();
    }

    private ExifInterface createExif(FileDescriptor fileDescriptor) {
        try {
            return new ExifInterface(fileDescriptor);
        } catch (Exception e) {
            Log.e((CharSequence) "ExifCompat", "createExif failed", (Throwable) e);
            return null;
        }
    }

    private String findTimeAlt() {
        if (this.mExif != null) {
            String[] strArr = {"DateTimeOriginal", "DateTime", "DateTimeDigitized"};
            int i2 = 0;
            String str = null;
            while (i2 < 3 && (str = DateTimeGeneric.getDateTimeTag(getAttribute(strArr[i2]))) == null) {
                i2++;
            }
            if (str != null) {
                return str.split(" ")[1];
            }
        }
        return null;
    }

    public static String getDateTimeInFile(String str) {
        return DateTimeGeneric.getDateTimeTag(str);
    }

    private void setSubSec(String str, String[] strArr) {
        if (strArr.length > 2 && !TextUtils.isEmpty(strArr[2])) {
            long j2 = UnsafeCast.toLong(strArr[2], -1);
            if (j2 >= 0) {
                while (j2 > 1000) {
                    j2 /= 10;
                }
                setAttribute(str, String.valueOf(j2));
            }
        }
    }

    public boolean changeDate(String str, String str2, boolean z) {
        String str3;
        if (this.mExif == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            str3 = "force";
        } else {
            String dateTimeTag = DateTimeGeneric.getDateTimeTag(FileUtils.getNameFromPath(this.mPath));
            if (dateTimeTag != null) {
                str2 = dateTimeTag.split(" ")[1];
                str3 = "filename";
            } else {
                String findTimeAlt = findTimeAlt();
                if (findTimeAlt != null) {
                    String str4 = findTimeAlt;
                    str3 = "exif";
                    str2 = str4;
                } else {
                    str3 = "preset";
                }
            }
        }
        String[] adjustDateTime = adjustDateTime(str, str2);
        setDateTime(adjustDateTime);
        boolean saveAttributes = saveAttributes();
        StringBuilder sb2 = new StringBuilder("changeDate");
        Boolean valueOf = Boolean.valueOf(saveAttributes);
        a.A(new Object[]{str3, valueOf, adjustDateTime[0] + adjustDateTime[1], Long.valueOf(currentTimeMillis)}, sb2, "ExifCompat");
        return saveAttributes;
    }

    public ExifCompat copyDateTimeLocation(ExifCompat exifCompat) {
        if (this.mExif != null) {
            try {
                setDateTimeOriginal(exifCompat.getDateTimeOriginal());
                setDateTimeTaken(exifCompat.getDateTimeTaken());
                setDateTimeGps(exifCompat.getDateTimeGps());
                setLocation(exifCompat.getLocation());
                return this;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public String[] findDateTime() {
        if (this.mExif != null) {
            String attribute = getAttribute("DateTimeOriginal");
            if (DateTimeExif.match(attribute)) {
                return new String[]{attribute, getAttribute("OffsetTimeOriginal")};
            }
            String attribute2 = getAttribute("DateTime");
            if (DateTimeExif.match(attribute2)) {
                return new String[]{attribute2, getAttribute("OffsetTime")};
            }
            String attribute3 = getAttribute("DateTimeDigitized");
            if (DateTimeExif.match(attribute3)) {
                return new String[]{attribute3, getAttribute("OffsetTimeDigitized")};
            }
        }
        String dateTimeTag = DateTimeGeneric.getDateTimeTag(FileUtils.getNameFromPath(this.mPath));
        if (dateTimeTag != null) {
            return new String[]{dateTimeTag, null};
        }
        return null;
    }

    public String[] findDateTimeAlt() {
        if (this.mExif == null) {
            return null;
        }
        String attribute = getAttribute("DateTime");
        if (DateTimeExif.match(attribute)) {
            return new String[]{attribute, getAttribute("OffsetTime"), getAttribute("SubSecTime")};
        }
        String attribute2 = getAttribute("DateTimeDigitized");
        if (DateTimeExif.match(attribute2)) {
            return new String[]{attribute2, getAttribute("OffsetTimeDigitized"), getAttribute("SubSecTimeDigitized")};
        }
        String dateTimeTag = DateTimeGeneric.getDateTimeTag(getAttribute("DateTimeOriginal"));
        if (dateTimeTag != null) {
            return new String[]{dateTimeTag, getAttribute("OffsetTimeOriginal"), getAttribute("SubSecTimeOriginal")};
        }
        return null;
    }

    public String findTimeZoneOffset() {
        if (this.mExif == null) {
            return null;
        }
        String attribute = getAttribute("OffsetTimeOriginal");
        if (attribute == null) {
            attribute = getAttribute("OffsetTime");
        }
        if (attribute == null) {
            return getAttribute("OffsetTimeDigitized");
        }
        return attribute;
    }

    public boolean fixDate() {
        return fixDate(true);
    }

    public String getAttribute(String str) {
        ExifInterface exifInterface = this.mExif;
        if (exifInterface != null) {
            return exifInterface.getAttribute(str);
        }
        return null;
    }

    public String[] getDateTimeDigitized() {
        return new String[]{getAttribute("DateTimeDigitized"), getAttribute("OffsetTimeDigitized"), getAttribute("SubSecTimeDigitized")};
    }

    public String[] getDateTimeGps() {
        return new String[]{getAttribute("GPSDateStamp"), getAttribute("GPSTimeStamp")};
    }

    public String[] getDateTimeOriginal() {
        return new String[]{getAttribute("DateTimeOriginal"), getAttribute("OffsetTimeOriginal"), getAttribute("SubSecTimeOriginal")};
    }

    public String[] getDateTimeTaken() {
        return new String[]{getAttribute("DateTime"), getAttribute("OffsetTime"), getAttribute("SubSecTime")};
    }

    public String[] getLocation() {
        return new String[]{getAttribute("GPSLatitude"), getAttribute("GPSLatitudeRef"), getAttribute("GPSLongitude"), getAttribute("GPSLongitudeRef"), getAttribute("GPSAltitude"), getAttribute("GPSAltitudeRef")};
    }

    public String getTimeZoneOffset() {
        String findTimeZoneOffset = findTimeZoneOffset();
        if (TextUtils.isEmpty(findTimeZoneOffset) || !TimeUtil.isValidTimeZoneFormat(findTimeZoneOffset)) {
            return TimeUtil.getSystemTimeZoneOffsetTag();
        }
        return findTimeZoneOffset;
    }

    public boolean hasAttribute(String str) {
        ExifInterface exifInterface = this.mExif;
        if (exifInterface == null || exifInterface.getAttribute(str) == null) {
            return false;
        }
        return true;
    }

    public boolean hasDateLocation() {
        if (!hasDateTimeOriginal() || !hasLocation()) {
            return false;
        }
        return true;
    }

    public boolean hasDateTimeOriginal() {
        return DateTimeExif.match(getAttribute("DateTimeOriginal"));
    }

    public boolean hasLocation() {
        if (!hasAttribute("GPSLatitude") || !hasAttribute("GPSLongitude")) {
            return false;
        }
        return true;
    }

    public boolean isChanged() {
        return this.mChanged;
    }

    public boolean isValid() {
        if (this.mExif != null) {
            return true;
        }
        return false;
    }

    public ExifCompat recoverDateTime(ExifCompat exifCompat) {
        if (this.mExif != null && !hasAttribute("DateTimeOriginal")) {
            String[] findDateTimeAlt = findDateTimeAlt();
            if (findDateTimeAlt == null && exifCompat != null) {
                findDateTimeAlt = exifCompat.findDateTimeAlt();
            }
            if (findDateTimeAlt != null) {
                if (findDateTimeAlt[1] == null) {
                    findDateTimeAlt[1] = TimeUtil.getSystemTimeZoneOffsetTag();
                }
                setDateTimeOriginal(findDateTimeAlt);
                if (!hasAttribute("DateTime")) {
                    setDateTimeTaken(findDateTimeAlt);
                    return this;
                }
            } else if (exifCompat != null) {
                setDateTimeGps(exifCompat.getDateTimeGps());
            }
        }
        return this;
    }

    public boolean saveAttributes() {
        if (!this.mChanged || this.mExif == null) {
            Log.d("ExifCompat", "saveAttributes skip. no changes");
            return false;
        }
        try {
            if (SdkConfig.lessThan(SdkConfig.GED.S)) {
                ExifTag.removeXmp(this.mExif);
            }
            this.mExif.saveAttributes();
            return true;
        } catch (Error | Exception e) {
            Log.e((CharSequence) "ExifCompat", "saveAttributes failed", e);
            return false;
        }
    }

    public void setAttribute(String str, String str2) {
        ExifInterface exifInterface = this.mExif;
        if (exifInterface != null) {
            this.mChanged = true;
            exifInterface.setAttribute(str, str2);
        }
    }

    public ExifCompat setDateTime(String[] strArr) {
        if (!(this.mExif == null || strArr == null || strArr.length <= 1)) {
            if (PocFeatures.RESTORE_DATETIME_LOCATION) {
                String[] dateTimeOriginal = getDateTimeOriginal();
                String[] dateTimeDigitized = getDateTimeDigitized();
                if (dateTimeDigitized[0] == null) {
                    dateTimeDigitized[0] = dateTimeOriginal[0];
                }
                if (dateTimeDigitized[1] == null) {
                    dateTimeDigitized[1] = dateTimeOriginal[1];
                }
                setDateTimeDigitized(dateTimeDigitized);
            }
            setDateTimeOriginal(strArr);
            setDateTimeTaken(strArr);
            setDateTimeGps(DateTimeUtc.toUtcTime(strArr));
        }
        return this;
    }

    public void setDateTimeDigitized(String[] strArr) {
        if (this.mExif != null && !Arrays.equals(strArr, getDateTimeDigitized())) {
            setAttribute("DateTimeDigitized", strArr[0]);
            setAttribute("OffsetTimeDigitized", strArr[1]);
            setSubSec("SubSecTimeDigitized", strArr);
        }
    }

    public void setDateTimeGps(String[] strArr) {
        if (this.mExif != null && !Arrays.equals(strArr, getDateTimeGps())) {
            setAttribute("GPSDateStamp", strArr[0]);
            setAttribute("GPSTimeStamp", strArr[1]);
        }
    }

    public void setDateTimeOriginal(String[] strArr) {
        if (this.mExif != null && !Arrays.equals(strArr, getDateTimeOriginal())) {
            setAttribute("DateTimeOriginal", strArr[0]);
            setAttribute("OffsetTimeOriginal", strArr[1]);
            setSubSec("SubSecTimeOriginal", strArr);
        }
    }

    public void setDateTimeTaken(String[] strArr) {
        if (this.mExif != null && !Arrays.equals(strArr, getDateTimeTaken())) {
            setAttribute("DateTime", strArr[0]);
            setAttribute("OffsetTime", strArr[1]);
            setSubSec("SubSecTime", strArr);
        }
    }

    public ExifCompat setLocation(String[] strArr) {
        if (this.mExif != null && !Arrays.equals(strArr, getLocation())) {
            setAttribute("GPSLatitude", strArr[0]);
            setAttribute("GPSLatitudeRef", strArr[1]);
            setAttribute("GPSLongitude", strArr[2]);
            setAttribute("GPSLongitudeRef", strArr[3]);
            setAttribute("GPSAltitude", strArr[4]);
            setAttribute("GPSAltitudeRef", strArr[5]);
        }
        return this;
    }

    public ExifCompat setModel(String str) {
        setAttribute("Model", str);
        return this;
    }

    public ExifCompat setOrientation(int i2) {
        setAttribute("Orientation", ExifTag.toOrientationTag(i2));
        return this;
    }

    public boolean fixDate(boolean z) {
        String[] strArr;
        String str;
        String str2;
        String str3;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mExif == null) {
            Log.e("ExifCompat", "fixDateTime skip {no exif}");
            return false;
        } else if (!z || !hasDateTimeOriginal()) {
            String dateTimeTag = DateTimeGeneric.getDateTimeTag(FileUtils.getNameFromPath(this.mPath));
            if (dateTimeTag != null) {
                strArr = new String[]{dateTimeTag, null};
            } else if (z) {
                strArr = findDateTimeAlt();
            } else {
                Log.d("ExifCompat", "fixDateTime skip");
                return false;
            }
            if (strArr != null) {
                if (strArr[1] == null) {
                    strArr[1] = TimeUtil.getSystemTimeZoneOffsetTag();
                }
                str2 = strArr[0].equals(dateTimeTag) ? "file" : "exif";
                str = DateTimeUtc.toUtcTime(strArr);
                setDateTimeTaken(strArr);
                setDateTimeOriginal(strArr);
                setDateTimeGps(str);
            } else {
                str = DateTimeExif.getDateTimeTag(ExifTag.getSefUtcTime(this.mPath));
                if (str != null) {
                    setDateTimeGps(str);
                    str2 = "sef";
                } else {
                    str2 = "invalid";
                }
            }
            saveAttributes();
            StringBuilder sb2 = new StringBuilder("fixDateTime {");
            sb2.append(str2);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (strArr != null) {
                str3 = strArr[0] + strArr[1];
            } else {
                str3 = "null";
            }
            sb2.append(str3);
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(str);
            sb2.append("} +");
            sb2.append(System.currentTimeMillis() - currentTimeMillis);
            Log.d("ExifCompat", sb2.toString());
            return isChanged();
        } else {
            Log.w("ExifCompat", "fixDateTime skip {valid," + getDateTimeOriginal()[0] + "}");
            return false;
        }
    }

    private ExifInterface createExif(String str) {
        try {
            return new ExifInterface((File) new SecureFile(str));
        } catch (Exception e) {
            Log.e((CharSequence) "ExifCompat", "createExif failed", (Throwable) e);
            return null;
        }
    }

    public ExifCompat(ExifInterface exifInterface) {
        this((String) null, exifInterface);
    }

    public void setDateTimeGps(String str) {
        if (this.mExif != null) {
            String[] split = str != null ? str.split(" ") : null;
            if (split == null || split.length < 2) {
                split = new String[2];
            }
            setDateTimeGps(split);
        }
    }

    public ExifCompat(String str, ExifInterface exifInterface) {
        this.mPath = str;
        this.mExif = exifInterface;
    }

    public ExifCompat(FileDescriptor fileDescriptor) {
        this(fileDescriptor, (String) null);
    }

    public ExifCompat setLocation(double d, double d2) {
        int i2 = (d > MapUtil.INVALID_LOCATION ? 1 : (d == MapUtil.INVALID_LOCATION ? 0 : -1));
        if (i2 == 0 && d2 == MapUtil.INVALID_LOCATION) {
            setLocation(new String[6]);
            return this;
        }
        int i7 = i2;
        setLocation(new String[]{ExifTag.toGeoDegree(d), i7 > 0 ? "N" : "S", ExifTag.toGeoDegree(d2), d2 > MapUtil.INVALID_LOCATION ? "E" : "W", null, null});
        return this;
    }

    public ExifCompat(FileDescriptor fileDescriptor, String str) {
        this.mPath = str;
        this.mExif = createExif(fileDescriptor);
    }
}
