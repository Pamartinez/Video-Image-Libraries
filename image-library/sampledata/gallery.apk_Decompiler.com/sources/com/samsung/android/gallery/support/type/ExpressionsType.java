package com.samsung.android.gallery.support.type;

import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ExpressionsType {
    ;
    
    private final String mSubCategory;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.type.ExpressionsType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends ExpressionsType {
        public /* synthetic */ AnonymousClass1() {
            this("EXPRESSION_HAPPY", 0, "Happy");
        }

        public String getExpressionColumn() {
            return "expression_like";
        }

        private AnonymousClass1(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.type.ExpressionsType$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends ExpressionsType {
        public /* synthetic */ AnonymousClass2() {
            this("EXPRESSION_NEUTRAL", 1, "Neutral");
        }

        public String getExpressionColumn() {
            return "expression_neutral";
        }

        private AnonymousClass2(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.type.ExpressionsType$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends ExpressionsType {
        public /* synthetic */ AnonymousClass3() {
            this("EXPRESSION_DISLIKE", 2, "Dislike");
        }

        public String getExpressionColumn() {
            return "expression_dislike";
        }

        private AnonymousClass3(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.type.ExpressionsType$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends ExpressionsType {
        public /* synthetic */ AnonymousClass4() {
            this("EXPRESSION_SURPRISE", 3, "Surprise");
        }

        public String getExpressionColumn() {
            return "expression_surprise";
        }

        private AnonymousClass4(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.support.type.ExpressionsType$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends ExpressionsType {
        public /* synthetic */ AnonymousClass5() {
            this("EXPRESSION_UNKNOWN", 4, C2paManifestList.UNKNOWN_VALUE);
        }

        public String getExpressionColumn() {
            return "";
        }

        private AnonymousClass5(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    public abstract String getExpressionColumn();

    public String getSubCategory() {
        return this.mSubCategory;
    }

    private ExpressionsType(String str) {
        this.mSubCategory = str;
    }
}
