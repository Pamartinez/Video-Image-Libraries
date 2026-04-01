package com.samsung.android.gallery.module.search.root;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntelligentSearchIndexFieldIcon {
    private final Integer mDrawableResId;
    private final Integer mTintColorResId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        FILTER_TYPE,
        LINE_TYPE,
        SEMANTIC_SEARCH_TYPE;

        public boolean isLineType() {
            if (this == LINE_TYPE) {
                return true;
            }
            return false;
        }

        public boolean isSemanticSearchType() {
            if (this == SEMANTIC_SEARCH_TYPE) {
                return true;
            }
            return false;
        }
    }

    private IntelligentSearchIndexFieldIcon(String str, String str2, Type type) {
        this.mDrawableResId = IntelligentSearchIndexFieldIconUtil.findDrawableResource(str, str2, type);
        this.mTintColorResId = IntelligentSearchIndexFieldIconUtil.findColorTintResource(str, str2);
    }

    public static IntelligentSearchIndexFieldIcon create(String str, String str2) {
        return new IntelligentSearchIndexFieldIcon(str, str2, Type.FILTER_TYPE);
    }

    public static IntelligentSearchIndexFieldIcon createForAutoComplete(String str, String str2) {
        return new IntelligentSearchIndexFieldIcon(str, str2, Type.LINE_TYPE);
    }

    public static IntelligentSearchIndexFieldIcon createForSuggestion(String str, String str2) {
        return new IntelligentSearchIndexFieldIcon(str, str2, Type.LINE_TYPE);
    }

    public Integer getDrawableResId() {
        return this.mDrawableResId;
    }

    public Integer getTintColorResId() {
        return this.mTintColorResId;
    }
}
