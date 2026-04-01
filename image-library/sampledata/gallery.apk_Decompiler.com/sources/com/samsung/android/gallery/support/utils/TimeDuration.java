package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum TimeDuration {
    ;
    
    final TimeUtil mTimeUtil;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.TimeDuration$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends TimeDuration {
        public /* synthetic */ AnonymousClass1() {
            this("TODAY", 0);
        }

        public long getFromTimeInfo() {
            return this.mTimeUtil.today();
        }

        public String toString() {
            return "Today";
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.TimeDuration$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends TimeDuration {
        public /* synthetic */ AnonymousClass2() {
            this("LAST_7DAY", 1);
        }

        public long getFromTimeInfo() {
            return this.mTimeUtil.startOf7DaysAgo();
        }

        public String toString() {
            return "Last 7 days";
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.TimeDuration$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends TimeDuration {
        public /* synthetic */ AnonymousClass3() {
            this("LAST_30DAY", 2);
        }

        public long getFromTimeInfo() {
            return this.mTimeUtil.startOfMonthsAgo(1);
        }

        public String toString() {
            return "Last 30 days";
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.TimeDuration$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends TimeDuration {
        public /* synthetic */ AnonymousClass4() {
            this("LAST_6MONTH", 3);
        }

        public long getFromTimeInfo() {
            return this.mTimeUtil.startOfPastMonths(6);
        }

        public String toString() {
            return "Last 6 months";
        }

        private AnonymousClass4(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.utils.TimeDuration$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends TimeDuration {
        public /* synthetic */ AnonymousClass5() {
            this("LAST_12MONTH", 4);
        }

        public long getFromTimeInfo() {
            return this.mTimeUtil.startOfPastMonths(12);
        }

        public String toString() {
            return "Last 12 months";
        }

        private AnonymousClass5(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public static TimeDuration calculateDuration(long j2) {
        TimeUtil timeUtil = new TimeUtil();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - j2 < 0 || TimeUtil.getBasicIsoDate(currentTimeMillis).equals(TimeUtil.getBasicIsoDate(j2))) {
            return TODAY;
        }
        if (j2 >= timeUtil.startOf7DaysAgo()) {
            return LAST_7DAY;
        }
        if (j2 >= timeUtil.startOfMonthsAgo(1)) {
            return LAST_30DAY;
        }
        if (j2 >= timeUtil.startOfPastMonths(6)) {
            return LAST_6MONTH;
        }
        return LAST_12MONTH;
    }

    public abstract long getFromTimeInfo();
}
