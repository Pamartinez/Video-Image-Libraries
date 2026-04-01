package com.samsung.android.gallery.database.dbtype;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum DateType {
    ;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.database.dbtype.DateType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends DateType {
        public /* synthetic */ AnonymousClass1() {
            this("DAY", 0);
        }

        public String getDateFormat() {
            return "'%Y-%m-%d'";
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.database.dbtype.DateType$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends DateType {
        public /* synthetic */ AnonymousClass2() {
            this("MONTH", 1);
        }

        public String getDateFormat() {
            return "'%Y-%m'";
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public abstract String getDateFormat();
}
