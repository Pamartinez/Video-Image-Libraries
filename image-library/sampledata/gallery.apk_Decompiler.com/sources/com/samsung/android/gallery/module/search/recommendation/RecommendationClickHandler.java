package com.samsung.android.gallery.module.search.recommendation;

import U5.c;
import V8.a;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.type.SuggestionKeyword;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.AbstractQueue;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecommendationClickHandler {
    private final String mScreenId;
    private final boolean mShowOnNewFragment;

    public RecommendationClickHandler(String str) {
        this(str, true);
    }

    private void consumeSuggestion(Blackboard blackboard, IRecommendationItem iRecommendationItem) {
        SimpleThreadPool.getInstance().submit(new c(29, iRecommendationItem, blackboard));
    }

    private String createHistoryTargetUrl(HistoryItem historyItem) {
        return new UriBuilder(historyItem.mTarget).appendArg("title", historyItem.mTitle).appendArg("collect_keyword", historyItem.mTitle).appendArg("collect_type", SearchWordCollector.Type.RECENT_HISTORY.toString()).build();
    }

    private String createSuggestionTargetUrl(IRecommendationItem iRecommendationItem, boolean z) {
        UriBuilder uriBuilder;
        String str;
        if (iRecommendationItem.isDynamic()) {
            String collectedKeyword = SearchWordCollector.getCollectedKeyword(iRecommendationItem.getTitle(), iRecommendationItem.getSubCategory(), iRecommendationItem.getTagType());
            UriBuilder uriBuilder2 = new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Keyword", iRecommendationItem.getQueryWhereArgs()));
            if (useKeywordSearch()) {
                str = iRecommendationItem.getTitle();
            } else {
                str = iRecommendationItem.getQueryWhereArgs();
            }
            uriBuilder = uriBuilder2.appendArg("sub", str).appendArg("term", iRecommendationItem.getQueryWhereColumnName()).appendArg("collect_keyword", collectedKeyword).appendArg("collect_type", SearchWordCollector.Type.SUGGESTION.toString());
            if ("persontag".equals(iRecommendationItem.getQueryWhereColumnName())) {
                uriBuilder.appendArg("isNamed", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            }
        } else {
            uriBuilder = new UriBuilder("location://search/fileList/Recommendation/SuggestionKeyword/" + ArgumentsUtil.encode(String.valueOf(SuggestionKeyword.values()[iRecommendationItem.getOrdinary()]))).appendArg("sub", iRecommendationItem.getSubCategory());
        }
        uriBuilder.appendArg("title", iRecommendationItem.getTitle());
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_EXTENDED_SUGGESTED_KEYWORD_FEATURE)) {
            uriBuilder.appendArg("suggestedKeywordFeature", iRecommendationItem.getSuggestedKeywordFeature());
        }
        if (!z && PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD && PreferenceFeatures.OneUi7x.SUPPORT_QS_UNIFIED_ITEM) {
            uriBuilder.appendArg("disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        return uriBuilder.build();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$consumeSuggestion$0(IRecommendationItem iRecommendationItem, Blackboard blackboard) {
        AbstractQueue<IRecommendationItem> consume = QuerySuggesterFactory.create().consume(AppResources.getAppContext(), iRecommendationItem);
        if (iRecommendationItem.isDynamic()) {
            blackboard.postEvent(EventMessage.obtain(8519, (String) Optional.ofNullable(consume.peek()).map(new a(20)).orElse((Object) null)));
        }
    }

    private void moveUrl(Blackboard blackboard, String str) {
        blackboard.post("command://MoveURL", str);
    }

    private void postAnalyticsSuggestionLog(IRecommendationItem iRecommendationItem, int i2) {
        if (!iRecommendationItem.isDynamic()) {
            i2 = iRecommendationItem.getOrdinary() + 1;
        }
        AnalyticsLogger.getInstance().postLog(this.mScreenId, AnalyticsEventId.EVENT_SEARCH_SUGGEST_ITEM.toString(), String.valueOf(i2));
    }

    private void postSubmitQuery(Blackboard blackboard, String str) {
        blackboard.postEvent(EventMessage.obtain(8531, str));
    }

    private boolean useKeywordSearch() {
        return PreferenceFeatures.OneUi7x.SUPPORT_QS_UNIFIED_ITEM;
    }

    public void clearHistory() {
        postAnalyticsHistoryLog(AnalyticsEventId.EVENT_SEARCH_HISTORY_CLEAR);
        SearchHistory.getInstance().deleteAllHistory();
    }

    public void clickHistory(Blackboard blackboard, HistoryItem historyItem) {
        postAnalyticsHistoryLog(AnalyticsEventId.EVENT_SEARCH_HISTORY_ITEM);
        if (this.mShowOnNewFragment) {
            moveUrl(blackboard, createHistoryTargetUrl(historyItem));
        } else {
            postSubmitQuery(blackboard, historyItem.mTitle);
        }
    }

    public void clickSuggestion(Blackboard blackboard, IRecommendationItem iRecommendationItem, int i2) {
        postAnalyticsSuggestionLog(iRecommendationItem, i2);
        consumeSuggestion(blackboard, iRecommendationItem);
        if (this.mShowOnNewFragment || !iRecommendationItem.isDynamic()) {
            moveUrl(blackboard, createSuggestionTargetUrl(iRecommendationItem, PickerUtil.isPickerMode(blackboard)));
        } else {
            postSubmitQuery(blackboard, iRecommendationItem.getTitle());
        }
    }

    public void deleteHistory(HistoryItem historyItem) {
        postAnalyticsHistoryLog(AnalyticsEventId.EVENT_SEARCH_HISTORY_ITEM_DELETE);
        SearchHistory.getInstance().deleteHistory(historyItem.mTarget);
    }

    public void deleteRemainedHistory(HistoryItem historyItem) {
        SearchHistory.getInstance().deleteHistoryBefore(historyItem.mDateAdded);
    }

    public final void postAnalyticsHistoryLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(this.mScreenId, analyticsEventId.toString());
    }

    public RecommendationClickHandler(String str, boolean z) {
        this.mScreenId = str;
        this.mShowOnNewFragment = z;
    }
}
