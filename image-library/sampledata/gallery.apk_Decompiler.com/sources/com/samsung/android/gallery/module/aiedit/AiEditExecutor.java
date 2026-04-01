package com.samsung.android.gallery.module.aiedit;

import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum AiEditExecutor {
    ;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.aiedit.AiEditExecutor$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends AiEditExecutor {
        public /* synthetic */ AnonymousClass1() {
            this("EditSuggestion", 0);
        }

        public String getScreenId(boolean z) {
            return AnalyticsScreenId.SCREEN_DETAILS_NORMAL.toString();
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.aiedit.AiEditExecutor$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends AiEditExecutor {
        public /* synthetic */ AnonymousClass2() {
            this("AwesomeIntelligence", 1);
        }

        public String getScreenId(boolean z) {
            if (z) {
                return AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICTURE.toString();
            }
            return AnalyticsScreenId.SCREEN_DETAIL_VIEW_VIDEO.toString();
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public abstract String getScreenId(boolean z);
}
