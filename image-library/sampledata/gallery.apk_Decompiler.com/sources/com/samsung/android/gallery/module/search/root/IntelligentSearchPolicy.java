package com.samsung.android.gallery.module.search.root;

import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum IntelligentSearchPolicy {
    ;
    
    private final String mPolicyName;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.search.root.IntelligentSearchPolicy$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends IntelligentSearchPolicy {
        public /* synthetic */ AnonymousClass1() {
            this("FUZZY_QUERY", 0, "isFuzzyQueryEnabled");
        }

        public boolean isFeatureEnabled() {
            return SdkConfig.atLeast(SdkConfig.SEM.R_MR1);
        }

        private AnonymousClass1(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.search.root.IntelligentSearchPolicy$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends IntelligentSearchPolicy {
        public /* synthetic */ AnonymousClass2() {
            this("PREFIX_QUERY", 1, "isPrefixQueryEnabled");
        }

        public boolean isFeatureEnabled() {
            return SdkConfig.atLeast(SdkConfig.SEM.R_MR5);
        }

        private AnonymousClass2(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.search.root.IntelligentSearchPolicy$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends IntelligentSearchPolicy {
        public /* synthetic */ AnonymousClass3() {
            this("PHONETIC", 2, "isPhoneticEnabled");
        }

        public boolean isFeatureEnabled() {
            return SdkConfig.atLeast(SdkConfig.SEM.R_MR5);
        }

        private AnonymousClass3(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.search.root.IntelligentSearchPolicy$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends IntelligentSearchPolicy {
        public /* synthetic */ AnonymousClass4() {
            this("TRANSLITERATION", 3, "isTransliterationEnabled");
        }

        public boolean isActive(String str) {
            if (!isFeatureEnabled() || !str.equals("ko")) {
                return false;
            }
            return true;
        }

        public boolean isFeatureEnabled() {
            return SdkConfig.atLeast(SdkConfig.SEM.R_MR5);
        }

        private AnonymousClass4(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.search.root.IntelligentSearchPolicy$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends IntelligentSearchPolicy {
        public /* synthetic */ AnonymousClass5() {
            this("EXTREME_FUZZY", 4, "isExtremeFuzzyEnabled");
        }

        public boolean isFeatureEnabled() {
            return false;
        }

        private AnonymousClass5(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.search.root.IntelligentSearchPolicy$6  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass6 extends IntelligentSearchPolicy {
        public /* synthetic */ AnonymousClass6() {
            this("KOREAN_QUERY_CONVERSION_ENABLED", 5, "isKoreanQwertyConversionEnabled");
        }

        public boolean isActive(String str) {
            if (!isFeatureEnabled() || !str.startsWith("en") || !Locale.getDefault().getLanguage().equals("ko")) {
                return false;
            }
            return true;
        }

        public boolean isFeatureEnabled() {
            return SdkConfig.atLeast(SdkConfig.SEM.S);
        }

        private AnonymousClass6(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.search.root.IntelligentSearchPolicy$7  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass7 extends IntelligentSearchPolicy {
        public /* synthetic */ AnonymousClass7() {
            this("SEMANTIC_QUERY_ENABLED", 6, "isSemanticQueryEnabled");
        }

        public boolean isActive(String str) {
            return isFeatureEnabled();
        }

        public boolean isFeatureEnabled() {
            return Features.isEnabled(Features.SUPPORT_SEMANTIC_SEARCH);
        }

        private AnonymousClass7(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    public String getPolicyName() {
        return this.mPolicyName;
    }

    public boolean isActive(String str) {
        return isFeatureEnabled();
    }

    public abstract boolean isFeatureEnabled();

    private IntelligentSearchPolicy(String str) {
        this.mPolicyName = str;
    }
}
