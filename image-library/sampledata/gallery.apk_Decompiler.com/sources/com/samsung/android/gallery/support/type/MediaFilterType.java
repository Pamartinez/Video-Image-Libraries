package com.samsung.android.gallery.support.type;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MediaFilterType {
    ;
    
    private static final String INTENT_DIR_TYPE_IMAGE = "vnd.android.cursor.dir/image";
    private static final String INTENT_DIR_TYPE_VIDEO = "vnd.android.cursor.dir/video";

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.type.MediaFilterType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends MediaFilterType {
        public /* synthetic */ AnonymousClass1() {
            this("IMAGE_ONLY", 0);
        }

        public boolean match(String str) {
            if (str == null) {
                return false;
            }
            if (MediaFilterType.INTENT_DIR_TYPE_IMAGE.equals(str)) {
                return true;
            }
            if (!str.contains("image/") || str.contains("video/")) {
                return false;
            }
            return true;
        }

        public String toString() {
            return "image/*";
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.type.MediaFilterType$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends MediaFilterType {
        public /* synthetic */ AnonymousClass2() {
            this("VIDEO_ONLY", 1);
        }

        public boolean match(String str) {
            if (str == null) {
                return false;
            }
            if (MediaFilterType.INTENT_DIR_TYPE_VIDEO.equals(str)) {
                return true;
            }
            if (!str.contains("video/") || str.contains("image/")) {
                return false;
            }
            return true;
        }

        public String toString() {
            return "video/*";
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.type.MediaFilterType$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends MediaFilterType {
        public /* synthetic */ AnonymousClass3() {
            this("ALL", 2);
        }

        public boolean match(String str) {
            if (str == null) {
                return false;
            }
            if (str.equals("*/*")) {
                return true;
            }
            if (!str.contains("image/") || !str.contains("video/")) {
                return false;
            }
            return true;
        }

        public String toString() {
            return "*/*";
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public static boolean match(MediaFilterType mediaFilterType, String str) {
        return str != null && mediaFilterType.match(str);
    }

    public abstract boolean match(String str);
}
