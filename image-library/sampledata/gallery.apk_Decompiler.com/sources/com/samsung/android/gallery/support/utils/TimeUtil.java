package com.samsung.android.gallery.support.utils;

import A.a;
import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.util.TimeZone;
import android.text.format.DateUtils;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.ExifCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimeUtil {
    private static final Pattern TIMEZONE_PATTERN = Pattern.compile("([+-](2[0-3]|[01][0-9]|[0-9]):[0-5][0-9])");
    private static String sDetailsHMS;
    static int sTimeZoneOffset;
    static String sTimeZoneOffsetTag;
    private static String sTodayStr;
    private static long sTodayValidFrom;
    private static long sTomorrowValidFrom;
    private static Long[] sYearTimeStampArray;
    private static long[] sYearValid;
    private static String sYesterdayStr;
    private static long sYesterdayValidFrom;
    private final int day;
    private final int month;
    private final int year;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DateFormatter {
        static DateFormat abbrMonthUTC;
        static DateFormat abbrWeekDayUTC;
        static Locale locale;
        static DateFormat monthDay;
        static DateFormat monthDayNoAbbr;
        static DateFormat monthUTC;
        static DateFormat year;
        static DateFormat yearMonth;
        static DateFormat yearMonthDay;

        static {
            initialize(Locale.getDefault());
        }

        public static void initialize(Locale locale2) {
            Locale locale3 = locale;
            if (locale3 == null || !locale3.equals(locale2)) {
                long currentTimeMillis = System.currentTimeMillis();
                locale = locale2;
                monthDay = DateFormat.getPatternInstance("MMMd", locale2);
                monthDayNoAbbr = DateFormat.getPatternInstance("MMMMd", locale2);
                yearMonth = DateFormat.getPatternInstance("yMMM", locale2);
                yearMonthDay = DateFormat.getPatternInstance("yMMMd", locale2);
                year = DateFormat.getPatternInstance("y", locale2);
                DateFormat patternInstance = DateFormat.getPatternInstance("MMM", locale2);
                abbrMonthUTC = patternInstance;
                TimeZone timeZone = TimeZone.GMT_ZONE;
                patternInstance.setTimeZone(timeZone);
                DateFormat patternInstance2 = DateFormat.getPatternInstance("MMMM", locale2);
                monthUTC = patternInstance2;
                patternInstance2.setTimeZone(timeZone);
                DateFormat patternInstance3 = DateFormat.getPatternInstance("E", locale2);
                abbrWeekDayUTC = patternInstance3;
                patternInstance3.setTimeZone(timeZone);
                StringBuilder sb2 = new StringBuilder("initialize {");
                sb2.append(locale2);
                sb2.append("} +");
                a.x(sb2, currentTimeMillis, "TimeUtil");
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExifDateTime {
        static volatile SimpleDateFormat FORMATTER;

        public static SimpleDateFormat getInstance() {
            if (FORMATTER == null) {
                FORMATTER = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            }
            return FORMATTER;
        }

        public static void recycle() {
            FORMATTER = null;
        }

        public static long toTimeInMillis(String str) {
            try {
                return getInstance().parse(str).getTime();
            } catch (NullPointerException | ParseException e) {
                a.s(e, new StringBuilder("toTimeInMillis failed. e="), "TimeUtil");
                return 0;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IsoUtcDateTime {
        static volatile SimpleDateFormat FORMATTER;

        public static SimpleDateFormat getInstance() {
            if (FORMATTER == null) {
                synchronized (IsoUtcDateTime.class) {
                    try {
                        if (FORMATTER == null) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            simpleDateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
                            FORMATTER = simpleDateFormat;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return FORMATTER;
        }

        public static void recycle() {
            FORMATTER = null;
        }

        public static Date toDate(String str) {
            return getInstance().parse(str, new ParsePosition(0));
        }

        public static String toDateString(long j2) {
            return toDateTimeString(j2).split(" ")[0];
        }

        public static String toDateTimeString(long j2) {
            return getInstance().format(Long.valueOf(j2));
        }

        public static long toTimeInMillis(String str) {
            try {
                return toDate(str).getTime();
            } catch (Exception e) {
                Log.e((CharSequence) "TimeUtil", "toTimeInMillis failed", (Throwable) e);
                return -1;
            }
        }
    }

    public TimeUtil() {
        ThreadUtil.assertBgThread("TimeUtil takes long time. do it background");
        Calendar instance = Calendar.getInstance();
        this.year = instance.get(1);
        this.month = instance.get(2);
        this.day = instance.get(5);
    }

    public static void buildYearTimestampArray() {
        Calendar instance = Calendar.getInstance();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 1900; i2 <= 2100; i2++) {
            instance.set(i2, 0, 1, 0, 0, 0);
            arrayList.add(Long.valueOf(floor3digits(Long.valueOf(instance.getTimeInMillis()))));
        }
        sYearTimeStampArray = (Long[]) arrayList.toArray(new Long[0]);
    }

    public static boolean compareDateTimeAsMinute(long j2, long j3) {
        if (j2 / 60000 == j3 / 60000) {
            return true;
        }
        return false;
    }

    public static String duration(long j2) {
        String str;
        Object obj;
        Object obj2;
        Object obj3;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(j2);
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        long minutes = timeUnit.toMinutes(j2 - timeUnit2.toMillis(hours));
        long seconds = timeUnit.toSeconds((j2 - timeUnit2.toMillis(hours)) - TimeUnit.MINUTES.toMillis(minutes));
        long j3 = j2 % 1000;
        StringBuilder sb2 = new StringBuilder();
        if (hours == 0) {
            str = "";
        } else if (hours < 10) {
            str = a.e(hours, "0", NumericEnum.SEP);
        } else {
            str = hours + NumericEnum.SEP;
        }
        sb2.append(str);
        if (minutes < 10) {
            obj = a.f("0", minutes);
        } else {
            obj = Long.valueOf(minutes);
        }
        sb2.append(obj);
        sb2.append(NumericEnum.SEP);
        if (seconds < 10) {
            obj2 = a.f("0", seconds);
        } else {
            obj2 = Long.valueOf(seconds);
        }
        sb2.append(obj2);
        sb2.append(".");
        if (j3 < 10) {
            obj3 = a.f("00", j3);
        } else if (j3 < 100) {
            obj3 = a.f("0", j3);
        } else {
            obj3 = Long.valueOf(j3);
        }
        sb2.append(obj3);
        return sb2.toString();
    }

    private static long floor3digits(Long l) {
        return (long) (Math.floor(((double) l.longValue()) / 1000.0d) * 1000.0d);
    }

    public static String formatDuration(Context context, int i2, int[] iArr) {
        if (context == null) {
            Log.e("TimeUtil", "null context");
            return "";
        }
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long j2 = (long) i2;
        long hours = timeUnit.toHours(j2);
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        long minutes = timeUnit.toMinutes(j2 - timeUnit2.toMillis(hours));
        long seconds = timeUnit.toSeconds((j2 - timeUnit2.toMillis(hours)) - TimeUnit.MINUTES.toMillis(minutes));
        if (hours == 0) {
            int i7 = 0;
            String string = context.getString(iArr[0]);
            if (minutes < 10) {
                string = string.replaceFirst("0", "");
            }
            String format = String.format(string, new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)});
            int length = format.length();
            while (i7 < length) {
                int codePointAt = format.codePointAt(i7);
                if (!Character.isWhitespace(codePointAt)) {
                    break;
                }
                i7 += Character.charCount(codePointAt);
            }
            while (length > i7) {
                int codePointBefore = Character.codePointBefore(format, length);
                if (!Character.isWhitespace(codePointBefore)) {
                    break;
                }
                length -= Character.charCount(codePointBefore);
            }
            return format.substring(i7, length);
        }
        if (sDetailsHMS == null) {
            sDetailsHMS = context.getString(iArr[1]);
        }
        return String.format(sDetailsHMS, new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)});
    }

    public static String formatDurationForAccessibility(Context context, int i2, int[] iArr) {
        if (context == null) {
            Log.e("TimeUtil", "null context");
            return "";
        }
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long j2 = (long) i2;
        long hours = timeUnit.toHours(j2);
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        long minutes = timeUnit.toMinutes(j2 - timeUnit2.toMillis(hours));
        long seconds = timeUnit.toSeconds((j2 - timeUnit2.toMillis(hours)) - TimeUnit.MINUTES.toMillis(minutes));
        if (sDetailsHMS == null) {
            sDetailsHMS = context.getString(iArr[1]);
        }
        return String.format(sDetailsHMS, new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)});
    }

    public static String getAbbrMonth(int i2) {
        Calendar instance = Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC"));
        instance.set(2025, i2 - 1, 1);
        return DateFormatter.abbrMonthUTC.format(instance.getTime());
    }

    public static String getBasicIsoDate(long j2) {
        return getIsoLocalDate(j2, "");
    }

    public static String getBasicIsoUtcDate(long j2) {
        return getBasicIsoUtcDate(j2, "");
    }

    private static int getBinSearchResult(long j2) {
        int binarySearch = Arrays.binarySearch(sYearTimeStampArray, Long.valueOf(j2));
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        if (binarySearch == -1) {
            return -1;
        }
        return binarySearch + 1900;
    }

    public static String getDatePeriodDays(long j2, long j3) {
        return Integer.toString((int) (Math.abs(ChronoUnit.DAYS.between(Instant.ofEpochMilli(j2).atZone(ZoneId.systemDefault()).toLocalDate(), Instant.ofEpochMilli(j3).atZone(ZoneId.systemDefault()).toLocalDate())) + 1));
    }

    public static String getDateString(String str, long j2) {
        String str2;
        if (j2 >= sTomorrowValidFrom || j2 < sYesterdayValidFrom) {
            if (str == null || str.length() >= 10) {
                str2 = "YMD";
            } else if (str.length() >= 7) {
                str2 = "YM";
            } else {
                str2 = "Y";
            }
            return toLocalDate(j2, str2);
        } else if (j2 >= sTodayValidFrom) {
            return getTodayString();
        } else {
            return getYesterdayString();
        }
    }

    public static long getDateTimeMillis(int i2, int i7, int i8, int i10, int i11, int i12) {
        Calendar instance = Calendar.getInstance();
        instance.set(i2, i7, i8, i10, i11, i12);
        return instance.getTimeInMillis();
    }

    public static long getDaysAgo(int i2) {
        return todayInMillis() - (((long) i2) * MediaApiContract.DAY_IN_MILLI);
    }

    public static String getEventDatePeriod(long j2, long j3, int i2) {
        return getEventDatePeriod(j2, j3, i2, true);
    }

    public static String getExifDateTime(long j2) {
        Date date = new Date(j2);
        return toDateString(date, NumericEnum.SEP) + " " + toTimeString(date, NumericEnum.SEP);
    }

    public static String getExifTime(long j2) {
        return toTimeString(new Date(j2), NumericEnum.SEP);
    }

    public static long getExifTimeInMillis(String str) {
        return ExifDateTime.toTimeInMillis(str);
    }

    public static String getFileTimestamp() {
        return getFileTimestamp(System.currentTimeMillis());
    }

    public static String getFullMonth(int i2) {
        Calendar instance = Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC"));
        instance.set(2025, i2 - 1, 1);
        return toLocalDate(instance.getTime().getTime(), "MMMM_UTC");
    }

    public static String getIsoLocalDate(long j2) {
        return getIsoLocalDate(j2, "-");
    }

    public static String getIsoLocalDateTime(long j2) {
        Date date = new Date(j2);
        return toDateString(date, "-") + " " + toTimeString(date, NumericEnum.SEP);
    }

    public static String getIsoLocalDateTimeWithMillis(long j2) {
        Object obj;
        String str;
        Date date = new Date(j2);
        int i2 = (int) (j2 % 1000);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(toDateString(date, "-"));
        sb2.append(" ");
        sb2.append(toTimeString(date, NumericEnum.SEP));
        sb2.append(".");
        if (i2 < 10) {
            str = "00";
        } else if (i2 < 100) {
            str = "0";
        } else {
            obj = Integer.valueOf(i2);
            sb2.append(obj);
            return sb2.toString();
        }
        obj = C0086a.i(i2, str);
        sb2.append(obj);
        return sb2.toString();
    }

    public static String getIsoLocalTime(long j2) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(j2);
        long minutes = timeUnit.toMinutes(j2) % 60;
        long seconds = timeUnit.toSeconds(j2) % 60;
        if (hours > 0) {
            return String.format(Locale.getDefault(), "%d:%02d:%02d", new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)});
        }
        return String.format(Locale.getDefault(), "%02d:%02d", new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)});
    }

    public static String getIsoLocalTimeInMin(long j2) {
        Object obj;
        Object obj2;
        Object obj3;
        Date date = new Date(j2);
        int minutes = date.getMinutes();
        int seconds = date.getSeconds();
        int i2 = (int) (j2 % 1000);
        StringBuilder sb2 = new StringBuilder("");
        if (minutes < 10) {
            obj = C0086a.i(minutes, "0");
        } else {
            obj = Integer.valueOf(minutes);
        }
        sb2.append(obj);
        sb2.append(NumericEnum.SEP);
        if (seconds < 10) {
            obj2 = C0086a.i(seconds, "0");
        } else {
            obj2 = Integer.valueOf(seconds);
        }
        sb2.append(obj2);
        sb2.append(".");
        if (i2 < 10) {
            obj3 = C0086a.i(i2, "00");
        } else if (i2 < 100) {
            obj3 = C0086a.i(i2, "0");
        } else {
            obj3 = Integer.valueOf(i2);
        }
        sb2.append(obj3);
        return sb2.toString();
    }

    public static String getIsoToday() {
        return getIsoLocalDate(sTodayValidFrom);
    }

    public static String getIsoUtcDateTime(long j2) {
        return IsoUtcDateTime.toDateTimeString(j2);
    }

    public static String getLocalizedDate(long j2) {
        return getLocalizedDate(j2, 1);
    }

    public static String getLocalizedDateTime(long j2) {
        return getLocalizedDateTime(j2, " ");
    }

    public static String getLocalizedTime(long j2) {
        int i2;
        Context appContext = AppResources.getAppContext();
        if (appContext == null) {
            return "";
        }
        if (android.text.format.DateFormat.is24HourFormat(appContext)) {
            i2 = 128;
        } else {
            i2 = 64;
        }
        return DateUtils.formatDateTime(appContext, j2, i2 | 257);
    }

    private static String getLocalizedTimeWithRTL(StringBuilder sb2, long j2, String str) {
        if (sb2.toString().equals("")) {
            return "";
        }
        if (isLocaleRTL()) {
            sb2.append(BiDirectionUnicode.LEFT_TO_RIGHT_EMBEDDING);
            sb2.append(getLocalizedTime(j2));
            sb2.append(str);
            sb2.append(BiDirectionUnicode.POP_DIRECTIONAL_FORMATTING);
            return sb2.toString();
        }
        sb2.append(str);
        sb2.append(getLocalizedTime(j2));
        return sb2.toString();
    }

    public static String getOriginalDate(long j2, int i2) {
        if (AppResources.getAppContext() == null) {
            return "";
        }
        java.text.DateFormat dateInstance = java.text.DateFormat.getDateInstance(i2);
        dateInstance.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        StringBuilder sb2 = new StringBuilder(dateInstance.format(Long.valueOf(j2)));
        if (isLocaleRTL()) {
            sb2.append(BiDirectionUnicode.RIGHT_TO_LEFT_MARK);
            sb2.append(BiDirectionUnicode.LEFT_TO_RIGHT_MARK);
        }
        return sb2.toString();
    }

    public static String getOriginalDateTime(long j2) {
        return getOriginalTimeWithRTL(new StringBuilder(getOriginalDate(j2, 1)), j2, " ");
    }

    public static String getOriginalTime(long j2) {
        int i2;
        Context appContext = AppResources.getAppContext();
        if (appContext == null) {
            return "";
        }
        if (android.text.format.DateFormat.is24HourFormat(appContext)) {
            i2 = 128;
        } else {
            i2 = 64;
        }
        return DateUtils.formatDateTime(appContext, j2, i2 | 8449);
    }

    private static String getOriginalTimeWithRTL(StringBuilder sb2, long j2, String str) {
        if (AppResources.getAppContext() == null || sb2.toString().equals("")) {
            return "";
        }
        if (isLocaleRTL()) {
            sb2.append(BiDirectionUnicode.LEFT_TO_RIGHT_EMBEDDING);
            sb2.append(getOriginalTime(j2));
            sb2.append(str);
            sb2.append(BiDirectionUnicode.POP_DIRECTIONAL_FORMATTING);
            return sb2.toString();
        }
        sb2.append(str);
        sb2.append(getOriginalTime(j2));
        return sb2.toString();
    }

    public static long getSystemTimeZoneOffset() {
        return (long) sTimeZoneOffset;
    }

    public static String getSystemTimeZoneOffsetTag() {
        if (sTimeZoneOffsetTag == null) {
            sTimeZoneOffsetTag = getTimeZoneOffset(sTimeZoneOffset);
        }
        return sTimeZoneOffsetTag;
    }

    public static String getTimeZoneOffset(int i2) {
        boolean z;
        String str;
        Object obj;
        Object obj2;
        if (i2 >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (i2 < 0) {
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

    public static long getTimeZoneOffsetInMillis(String str) {
        long j2;
        try {
            String[] split = str.split(NumericEnum.SEP);
            if (str.startsWith("-")) {
                j2 = -1;
            } else {
                j2 = 1;
            }
            long parseInt = (long) Integer.parseInt(split[0]);
            return ((Math.abs(parseInt) * 60) + ((long) Integer.parseInt(split[1]))) * j2 * 60000;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String getTimestamp() {
        return getTimestamp(System.currentTimeMillis());
    }

    public static String getTimestampMillis() {
        return getTimestampMillis(System.currentTimeMillis());
    }

    public static long getTodayInMillis() {
        return sTodayValidFrom;
    }

    public static String getTodayString() {
        return sTodayStr;
    }

    public static long[] getYearInMills(long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j2));
        int i2 = instance.get(1);
        instance.set(6, 1);
        instance.set(1, i2);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        instance.set(1, i2 + 1);
        return new long[]{timeInMillis, instance.getTimeInMillis() - 1};
    }

    public static synchronized int getYearInt(long j2) {
        int binSearchResult;
        synchronized (TimeUtil.class) {
            try {
                if (sYearTimeStampArray == null) {
                    buildYearTimestampArray();
                }
                binSearchResult = getBinSearchResult(j2);
                if (binSearchResult == -1 || binSearchResult == 2100) {
                    binSearchResult = new Date(j2).getYear() + 1900;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return binSearchResult;
    }

    public static long[] getYearMonthInMills(long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j2));
        int i2 = instance.get(1);
        int i7 = instance.get(2);
        instance.set(5, 1);
        instance.set(1, i2);
        instance.set(2, i7);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        instance.set(2, i7 + 1);
        return new long[]{timeInMillis, instance.getTimeInMillis() - 1};
    }

    public static synchronized String getYearString(long j2) {
        String format;
        synchronized (TimeUtil.class) {
            format = DateFormatter.year.format(new Date(j2));
        }
        return format;
    }

    public static long[] getYearsAgo(long j2, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j2));
        instance.set(1, instance.get(1) - i2);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        instance.set(11, 23);
        instance.set(12, 59);
        instance.set(13, 59);
        instance.set(14, 999);
        return new long[]{timeInMillis, instance.getTimeInMillis()};
    }

    public static String getYesterdayString() {
        return sYesterdayStr;
    }

    public static void initDateFormat(Locale locale, String str, String str2) {
        sTodayStr = str;
        sYesterdayStr = str2;
        sYearTimeStampArray = null;
        DateFormatter.initialize(locale);
    }

    public static boolean is24HourFormat(Context context) {
        if (context == null || !android.text.format.DateFormat.is24HourFormat(context)) {
            return false;
        }
        return true;
    }

    public static boolean isInThisYear(long j2) {
        if (sYearValid == null) {
            sYearValid = thisYearInMillis();
        }
        long[] jArr = sYearValid;
        if (j2 < jArr[0] || j2 >= jArr[1]) {
            return false;
        }
        return true;
    }

    public static boolean isInToday(long j2) {
        if (j2 < sTodayValidFrom || j2 >= sTomorrowValidFrom) {
            return false;
        }
        return true;
    }

    private static boolean isLocaleRTL() {
        String language = Locale.getDefault().getLanguage();
        if ("ar".equals(language) || "fa".equals(language) || "ur".equals(language) || "iw".equals(language)) {
            return true;
        }
        return false;
    }

    public static boolean isValidLocalTime(long j2) {
        if (j2 > 50400000 || j2 < -43200000) {
            return true;
        }
        return false;
    }

    public static boolean isValidTimeZoneFormat(String str) {
        if (!str.contains(NumericEnum.SEP)) {
            return true;
        }
        boolean matches = TIMEZONE_PATTERN.matcher(str).matches();
        if (!matches) {
            Log.w((CharSequence) "TimeUtil", "inValidTimeZoneFormat", str);
        }
        return matches;
    }

    public static Long parseTimezoneOffset(String str) {
        long j2;
        if (str == null || str.length() <= 3) {
            return null;
        }
        try {
            if (str.startsWith("-")) {
                j2 = -1;
            } else {
                j2 = 1;
            }
            return Long.valueOf(((Math.abs((long) Integer.parseInt(str.substring(0, str.length() - 2))) * 60) + ((long) Integer.parseInt(str.substring(str.length() - 2)))) * j2 * 60000);
        } catch (Exception unused) {
            return null;
        }
    }

    private static long[] thisYearInMillis() {
        Calendar instance = Calendar.getInstance();
        instance.set(5, 1);
        instance.set(2, 0);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        instance.set(1, instance.get(1) + 1);
        return new long[]{timeInMillis, instance.getTimeInMillis()};
    }

    public static String toDateString(Date date, String str) {
        Object obj;
        Object obj2;
        Object obj3;
        int year2 = date.getYear() + 1900;
        int month2 = date.getMonth() + 1;
        int date2 = date.getDate();
        StringBuilder sb2 = new StringBuilder("");
        if (year2 < 1000) {
            obj = C0086a.i(year2, "0");
        } else {
            obj = Integer.valueOf(year2);
        }
        sb2.append(obj);
        sb2.append(str);
        if (month2 < 10) {
            obj2 = C0086a.i(month2, "0");
        } else {
            obj2 = Integer.valueOf(month2);
        }
        sb2.append(obj2);
        sb2.append(str);
        if (date2 < 10) {
            obj3 = C0086a.i(date2, "0");
        } else {
            obj3 = Integer.valueOf(date2);
        }
        sb2.append(obj3);
        return sb2.toString();
    }

    public static synchronized String toLocalDate(long j2, String str) {
        DateFormat dateFormat;
        synchronized (TimeUtil.class) {
            try {
                Date date = new Date(j2);
                if ("YMD".equals(str)) {
                    if (isInThisYear(j2)) {
                        dateFormat = DateFormatter.monthDay;
                    } else {
                        dateFormat = DateFormatter.yearMonthDay;
                    }
                    String format = dateFormat.format(date);
                    return format;
                } else if ("YM".equals(str)) {
                    String format2 = DateFormatter.yearMonth.format(date);
                    return format2;
                } else if ("YYMD".equals(str)) {
                    String format3 = DateFormatter.yearMonthDay.format(date);
                    return format3;
                } else if ("MD".equals(str)) {
                    String format4 = DateFormatter.monthDay.format(date);
                    return format4;
                } else if ("MMD".equals(str)) {
                    String format5 = DateFormatter.monthDayNoAbbr.format(date);
                    return format5;
                } else if ("MMMM_UTC".equals(str)) {
                    String format6 = DateFormatter.monthUTC.format(date);
                    return format6;
                } else if ("E_UTC".equals(str)) {
                    String format7 = DateFormatter.abbrWeekDayUTC.format(date);
                    return format7;
                } else {
                    String format8 = DateFormatter.year.format(date);
                    return format8;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public static long toLocalTimeInMillis(long j2) {
        return j2 + ((long) java.util.TimeZone.getDefault().getOffset(j2));
    }

    public static String toTimeString(Date date, String str) {
        Object obj;
        Object obj2;
        Object obj3;
        int hours = date.getHours();
        int minutes = date.getMinutes();
        int seconds = date.getSeconds();
        StringBuilder sb2 = new StringBuilder("");
        if (hours < 10) {
            obj = C0086a.i(hours, "0");
        } else {
            obj = Integer.valueOf(hours);
        }
        sb2.append(obj);
        sb2.append(str);
        if (minutes < 10) {
            obj2 = C0086a.i(minutes, "0");
        } else {
            obj2 = Integer.valueOf(minutes);
        }
        sb2.append(obj2);
        sb2.append(str);
        if (seconds < 10) {
            obj3 = C0086a.i(seconds, "0");
        } else {
            obj3 = Integer.valueOf(seconds);
        }
        sb2.append(obj3);
        return sb2.toString();
    }

    public static long toUtcTimeInMillis(long j2) {
        return j2 - ((long) java.util.TimeZone.getDefault().getOffset(j2));
    }

    public static long todayInMillis() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    public static boolean updateToday() {
        return updateToday(false);
    }

    public long startOf2DaysAgo() {
        Calendar instance = Calendar.getInstance();
        instance.set(this.year, this.month, this.day, 0, 0, 0);
        instance.add(7, -2);
        return floor3digits(Long.valueOf(instance.getTimeInMillis()));
    }

    public long startOf7DaysAgo() {
        Calendar instance = Calendar.getInstance();
        instance.set(this.year, this.month, this.day, 0, 0, 0);
        instance.add(3, -1);
        return floor3digits(Long.valueOf(instance.getTimeInMillis()));
    }

    public long startOfDaysAgo(int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(this.year, this.month, this.day, 0, 0, 0);
        if (i2 > 0) {
            instance.add(6, -i2);
        }
        return floor3digits(Long.valueOf(instance.getTimeInMillis()));
    }

    public long startOfMonthsAgo(int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(this.year, this.month - i2, this.day, 0, 0, 0);
        return floor3digits(Long.valueOf(instance.getTimeInMillis()));
    }

    public long startOfPastMonths(int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(this.year, this.month - i2, 1, 0, 0, 0);
        return floor3digits(Long.valueOf(instance.getTimeInMillis()));
    }

    public long today() {
        Calendar instance = Calendar.getInstance();
        instance.set(this.year, this.month, this.day, 0, 0, 0);
        return floor3digits(Long.valueOf(instance.getTimeInMillis()));
    }

    public static String getBasicIsoUtcDate(long j2, String str) {
        return IsoUtcDateTime.toDateString(j2).replace("-", str);
    }

    public static String getEventDatePeriod(long j2, long j3, int i2, boolean z) {
        Formatter formatDateRange = DateUtils.formatDateRange((Context) null, new Formatter(new StringBuilder(i2), Locale.getDefault()), j2, j3, z ? 65556 : 65552);
        if (formatDateRange != null) {
            return formatDateRange.toString();
        }
        return null;
    }

    public static String getFileTimestamp(long j2) {
        Date date = new Date(j2);
        return toDateString(date, "") + "_" + toTimeString(date, "");
    }

    public static String getIsoLocalDate(long j2, String str) {
        return toDateString(new Date(j2), str);
    }

    public static String getLocalizedDate(long j2, int i2) {
        if (AppResources.getAppContext() == null) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder(java.text.DateFormat.getDateInstance(i2).format(Long.valueOf(j2)));
        if (isLocaleRTL()) {
            sb2.append(BiDirectionUnicode.RIGHT_TO_LEFT_MARK);
            sb2.append(BiDirectionUnicode.LEFT_TO_RIGHT_MARK);
        }
        return sb2.toString();
    }

    public static String getLocalizedDateTime(long j2, String str) {
        return getLocalizedTimeWithRTL(new StringBuilder(getLocalizedDate(j2, 1)), j2, str);
    }

    public static String getTimestamp(long j2) {
        Date date = new Date(j2);
        return toDateString(date, "-") + " " + toTimeString(date, NumericEnum.SEP);
    }

    public static String getTimestampMillis(long j2) {
        Object obj;
        String str;
        Date date = new Date(j2);
        int i2 = (int) (j2 % 1000);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(toDateString(date, "-"));
        sb2.append(" ");
        sb2.append(toTimeString(date, NumericEnum.SEP));
        sb2.append(".");
        if (i2 < 10) {
            str = "00";
        } else if (i2 < 100) {
            str = "0";
        } else {
            obj = Integer.valueOf(i2);
            sb2.append(obj);
            return sb2.toString();
        }
        obj = C0086a.i(i2, str);
        sb2.append(obj);
        return sb2.toString();
    }

    public static boolean updateToday(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            sTomorrowValidFrom = 0;
        }
        if (currentTimeMillis < sTomorrowValidFrom && currentTimeMillis >= sTodayValidFrom) {
            return false;
        }
        long j2 = todayInMillis();
        sTodayValidFrom = j2;
        sYesterdayValidFrom = j2 - MediaApiContract.DAY_IN_MILLI;
        sTomorrowValidFrom = j2 + MediaApiContract.DAY_IN_MILLI;
        sYearValid = thisYearInMillis();
        sTimeZoneOffset = java.util.TimeZone.getDefault().getOffset(currentTimeMillis);
        sTimeZoneOffsetTag = null;
        if (z) {
            ExifDateTime.recycle();
            ExifCompat.DateTimeUtc.recycle();
            IsoUtcDateTime.recycle();
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        Locale locale = DateFormatter.locale;
        Log.d("TimeUtil", "Date{" + locale + GlobalPostProcInternalPPInterface.SPLIT_REGEX + sYesterdayValidFrom + GlobalPostProcInternalPPInterface.SPLIT_REGEX + sTodayValidFrom + GlobalPostProcInternalPPInterface.SPLIT_REGEX + sTomorrowValidFrom + GlobalPostProcInternalPPInterface.SPLIT_REGEX + sYearValid[0] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + sTimeZoneOffset + "} +" + currentTimeMillis2);
        return true;
    }

    public static String getOriginalDateTime(long j2, String str, int i2) {
        return getOriginalTimeWithRTL(new StringBuilder(getOriginalDate(j2, i2)), j2, str);
    }

    public static String getLocalizedDateTime(long j2, String str, int i2) {
        return getLocalizedTimeWithRTL(new StringBuilder(getLocalizedDate(j2, i2)), j2, str);
    }

    public TimeUtil(long j2) {
        ThreadUtil.assertBgThread("TimeUtil takes long time. do it background");
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j2);
        this.year = instance.get(1);
        this.month = instance.get(2);
        this.day = instance.get(5);
    }
}
