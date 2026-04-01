package com.samsung.android.gallery.module.search.recommendation;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;
import com.samsung.android.gallery.module.search.recommendation.SuggestionItem;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.type.SuggestionKeyword;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeDuration;
import com.samsung.android.gallery.support.utils.UnsafeCast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SuggestionItemLoader {
    private final Context mAppContext;
    private String mCategory;
    private int mCount = 0;
    private int mLocationType = -1;
    private String mSubCategory;

    /* renamed from: com.samsung.android.gallery.module.search.recommendation.SuggestionItemLoader$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$support$type$SuggestionKeyword;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.support.type.SuggestionKeyword[] r0 = com.samsung.android.gallery.support.type.SuggestionKeyword.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$support$type$SuggestionKeyword = r0
                com.samsung.android.gallery.support.type.SuggestionKeyword r1 = com.samsung.android.gallery.support.type.SuggestionKeyword.TIME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$support$type$SuggestionKeyword     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.support.type.SuggestionKeyword r1 = com.samsung.android.gallery.support.type.SuggestionKeyword.LOCATION     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$support$type$SuggestionKeyword     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.support.type.SuggestionKeyword r1 = com.samsung.android.gallery.support.type.SuggestionKeyword.CATEGORY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.search.recommendation.SuggestionItemLoader.AnonymousClass1.<clinit>():void");
        }
    }

    public SuggestionItemLoader(Cursor cursor, Context context) {
        extractCursor(cursor);
        this.mAppContext = context.getApplicationContext();
    }

    private void extractCursor(Cursor cursor) {
        try {
            this.mCount = cursor.getInt(cursor.getColumnIndex("__count"));
            this.mCategory = cursor.getString(cursor.getColumnIndex("__mainCategory"));
            this.mSubCategory = cursor.getString(cursor.getColumnIndex("__subCategory"));
            this.mLocationType = cursor.getInt(cursor.getColumnIndex("__tagType"));
        } catch (SQLException e) {
            Log.sw("SuggestionItemLoader", e.toString());
        }
    }

    private IRecommendationItem.IconType getIconType(int i2) {
        if (i2 == SuggestionKeyword.TIME.ordinal()) {
            return IRecommendationItem.IconType.TIME;
        }
        if (i2 == SuggestionKeyword.RECENTLY_ADDED.ordinal()) {
            return IRecommendationItem.IconType.RECENT;
        }
        if (i2 == SuggestionKeyword.VIDEOS.ordinal()) {
            return IRecommendationItem.IconType.VIDEOS;
        }
        if (i2 == SuggestionKeyword.LOCATION.ordinal()) {
            return IRecommendationItem.IconType.LOCATION;
        }
        if (i2 == SuggestionKeyword.SMILES.ordinal()) {
            return IRecommendationItem.IconType.SMILES;
        }
        if (i2 == SuggestionKeyword.CATEGORY.ordinal()) {
            return IRecommendationItem.IconType.CATEGORY;
        }
        if (i2 == SuggestionKeyword.BLURRY.ordinal()) {
            return IRecommendationItem.IconType.BLURRY;
        }
        return IRecommendationItem.IconType.ETC;
    }

    private String getSubCategory(SuggestionKeyword suggestionKeyword, String str) {
        if (suggestionKeyword.equals(SuggestionKeyword.TIME)) {
            return Long.toString(TimeDuration.calculateDuration(UnsafeCast.toLong(str, 0)).getFromTimeInfo());
        }
        return str;
    }

    private String getTagTitle() {
        return TranslationManager.getInstance().getTranslatedString("Recent", this.mSubCategory);
    }

    private String getTimeTitle() {
        return TranslationManager.getInstance().getTranslatedString("Recent", TimeDuration.calculateDuration(UnsafeCast.toLong(this.mSubCategory, 0)).toString());
    }

    private String getTitle(SuggestionKeyword suggestionKeyword) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$support$type$SuggestionKeyword[suggestionKeyword.ordinal()];
        if (i2 == 1) {
            return getTimeTitle();
        }
        if (i2 == 2 || i2 == 3) {
            return getTagTitle();
        }
        return this.mAppContext.getString(suggestionKeyword.getTitleResId());
    }

    public SuggestionItem getSuggestionItem() {
        if (this.mCount == 0) {
            return null;
        }
        SuggestionKeyword valueOf = SuggestionKeyword.valueOf(this.mCategory);
        return new SuggestionItem.Builder().setSubCategory(getSubCategory(valueOf, this.mSubCategory)).setTitle(getTitle(valueOf)).setLocationType(this.mLocationType).setOrdinary(valueOf.ordinal()).setIconType(getIconType(valueOf.ordinal())).build();
    }
}
