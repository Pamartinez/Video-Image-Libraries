package com.samsung.android.gallery.module.search.recommendation;

import android.content.Context;
import android.database.Cursor;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SCSQuerySuggesterWithDirect extends AbsSCSQuerySuggester {
    private static volatile SCSQuerySuggesterWithDirect sInstance;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class QuerySuggesterItemComposer {
        private AbsSCSQuerySuggester mSuggester;
        final HashSet<String> titleKeySet = new HashSet<>();

        public QuerySuggesterItemComposer(AbsSCSQuerySuggester absSCSQuerySuggester) {
            this.mSuggester = absSCSQuerySuggester;
        }

        private IRecommendationItem buildItem(Cursor cursor, String str, String str2) {
            String str3;
            String str4 = null;
            if (Features.isEnabled(Features.SUPPORT_SPECIAL_DAY_SUGGESTION)) {
                str3 = cursor.getString(cursor.getColumnIndex("query_string"));
            } else {
                str3 = null;
            }
            if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_EXTENDED_SUGGESTED_KEYWORD_FEATURE)) {
                str4 = cursor.getString(cursor.getColumnIndex("featureName"));
            }
            return this.mSuggester.buildSuggestionItem(str, str2, str3, str4);
        }

        public IRecommendationItem read(Cursor cursor) {
            String string = cursor.getString(cursor.getColumnIndex("hint"));
            String string2 = cursor.getString(cursor.getColumnIndex("hint_type"));
            if (!PreferenceFeatures.OneUi7x.SUPPORT_QS_UNIFIED_ITEM) {
                return buildItem(cursor, string, string2);
            }
            String translatedString = TranslationManager.getInstance().getTranslatedString(string2, string);
            if (this.titleKeySet.contains(translatedString)) {
                return null;
            }
            this.titleKeySet.add(translatedString);
            return buildItem(cursor, string, string2);
        }

        public ArrayList<IRecommendationItem> readAll(Cursor cursor) {
            ArrayList<IRecommendationItem> arrayList = new ArrayList<>();
            if (cursor == null || !cursor.moveToFirst()) {
                return arrayList;
            }
            do {
                IRecommendationItem read = read(cursor);
                if (read != null) {
                    arrayList.add(read);
                }
            } while (cursor.moveToNext());
            return arrayList;
        }
    }

    public static SCSQuerySuggesterWithDirect getInstance() {
        if (sInstance == null) {
            synchronized (SCSQuerySuggesterWithDirect.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SCSQuerySuggesterWithDirect();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public void loadCandidateSuggestionsInternal(Context context, int i2) {
        Cursor queryHintKeywords;
        try {
            queryHintKeywords = IntelligentSearch.getInstance().queryHintKeywords(i2);
            if (queryHintKeywords != null) {
                if (queryHintKeywords.moveToFirst()) {
                    String str = this.TAG;
                    Log.d(str, "loadCandidateSuggestionsInternal cursor count [" + queryHintKeywords.getCount() + "]");
                    this.mCandidateSuggestion.addAll(new QuerySuggesterItemComposer(this).readAll(queryHintKeywords));
                    fillSuggestionQueue(i2);
                }
            }
            if (queryHintKeywords != null) {
                queryHintKeywords.close();
                return;
            }
            return;
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.se(str2, "loadCandidateSuggestionsInternal() e=" + e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
