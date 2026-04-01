package com.samsung.android.gallery.database.dbtype;

import android.content.res.Resources;
import com.samsung.android.gallery.database.R$string;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class KeywordComparator {
    private final String mKeywordBlurred;
    private final String mKeywordDislike;
    private final String mKeywordFavorite;
    private final String mKeywordHappy;
    private final String mKeywordNeutral;
    private final String mKeywordSurprise;
    private final ArrayList<String> mKeywordTime;

    public KeywordComparator(Resources resources) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.mKeywordTime = arrayList;
        arrayList.clear();
        this.mKeywordFavorite = resources.getString(R$string.favorite).toLowerCase(Locale.getDefault());
        this.mKeywordBlurred = resources.getString(R$string.blurred_pictures).toLowerCase(Locale.getDefault());
        arrayList.add(resources.getString(R$string.today).toLowerCase(Locale.getDefault()));
        arrayList.add(resources.getString(R$string.category_time_past_week).toLowerCase(Locale.getDefault()));
        arrayList.add(resources.getString(R$string.category_time_past_month).toLowerCase(Locale.getDefault()));
        arrayList.add(resources.getString(R$string.category_time_past_six_month).toLowerCase(Locale.getDefault()));
        arrayList.add(resources.getString(R$string.category_time_last_year).toLowerCase(Locale.getDefault()));
        arrayList.add(resources.getString(R$string.recently_added).toLowerCase(Locale.getDefault()));
        this.mKeywordHappy = resources.getString(R$string.expressions_happy).toLowerCase(Locale.getDefault());
        this.mKeywordDislike = resources.getString(R$string.expressions_dislike).toLowerCase(Locale.getDefault());
        this.mKeywordNeutral = resources.getString(R$string.expressions_neutral).toLowerCase(Locale.getDefault());
        this.mKeywordSurprise = resources.getString(R$string.expressions_surprise).toLowerCase(Locale.getDefault());
    }

    public boolean containDislikeKeyword(String str) {
        return this.mKeywordDislike.contains(str.toLowerCase(Locale.getDefault()));
    }

    public boolean containHappyKeyword(String str) {
        return this.mKeywordHappy.contains(str.toLowerCase(Locale.getDefault()));
    }

    public boolean containNeutralKeyword(String str) {
        return this.mKeywordNeutral.contains(str.toLowerCase(Locale.getDefault()));
    }

    public boolean containSurpriseKeyword(String str) {
        return this.mKeywordSurprise.contains(str.toLowerCase(Locale.getDefault()));
    }

    public boolean equalsBlurryKeyword(String str) {
        return this.mKeywordBlurred.equalsIgnoreCase(str);
    }

    public boolean equalsFavoriteKeyword(String str) {
        return this.mKeywordFavorite.equalsIgnoreCase(str);
    }

    public int equalsTimeKeyword(String str) {
        return this.mKeywordTime.indexOf(str.toLowerCase());
    }
}
