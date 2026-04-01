package com.samsung.android.gallery.module.search.recommendation;

import V8.a;
import android.content.Context;
import com.samsung.android.gallery.module.search.recommendation.SuggestionItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsSCSQuerySuggester implements IQuerySuggester {
    protected final Object LOCK = new Object();
    protected final String TAG = getClass().getSimpleName();
    protected final Queue<IRecommendationItem> mCandidateSuggestion = new LinkedList();
    protected final ConcurrentLinkedQueue<IRecommendationItem> mSuggestionQueue = new ConcurrentLinkedQueue<>();

    private boolean loadCandidateSuggestions(Context context, int i2) {
        boolean z = false;
        if (context == null) {
            Log.sw(this.TAG, "Context is null");
            return false;
        } else if (!isSuggestionFeatureEnabled(context)) {
            Log.sw(this.TAG, "SCS suggestion feature is not available");
            return false;
        } else {
            if (this.mCandidateSuggestion.isEmpty() || this.mCandidateSuggestion.size() < i2) {
                z = true;
            }
            if (z) {
                loadCandidateSuggestionsInternal(context, i2);
            }
            return z;
        }
    }

    public IRecommendationItem buildSuggestionItem(String str, String str2) {
        return buildSuggestionItem(str, str2, (String) null, (String) null);
    }

    public void clear() {
        this.mCandidateSuggestion.clear();
        this.mSuggestionQueue.clear();
    }

    public AbstractQueue<IRecommendationItem> consume(Context context, IRecommendationItem iRecommendationItem) {
        ConcurrentLinkedQueue<IRecommendationItem> concurrentLinkedQueue;
        synchronized (this.LOCK) {
            this.mSuggestionQueue.remove(iRecommendationItem);
            loadSuggestion(context);
            concurrentLinkedQueue = this.mSuggestionQueue;
        }
        return concurrentLinkedQueue;
    }

    public void fillSuggestionQueue(int i2) {
        int i7 = 0;
        while (true) {
            if (i7 >= i2) {
                break;
            }
            IRecommendationItem poll = this.mCandidateSuggestion.poll();
            if (poll == null) {
                Log.s(this.TAG, "Candidate suggestion list is empty");
                break;
            }
            if (!this.mSuggestionQueue.contains(poll)) {
                this.mSuggestionQueue.add(poll);
            } else {
                String str = this.TAG;
                Log.sw(str, "Unsupported suggestion ->  [Name]: *** [Type]: " + poll.getTagType());
            }
            i7++;
        }
        String str2 = this.TAG;
        Log.s(str2, "suggestions count=" + this.mSuggestionQueue.size());
    }

    public AbstractQueue<IRecommendationItem> getSuggestion(Context context, Blackboard blackboard, long j2) {
        ConcurrentLinkedQueue<IRecommendationItem> concurrentLinkedQueue;
        synchronized (this.LOCK) {
            try {
                Log.s(this.TAG, "Request query suggestion");
                if (this.mSuggestionQueue.size() < 3) {
                    loadSuggestion(context);
                }
                blackboard.postEvent(EventMessage.obtain(8519, (String) Optional.ofNullable(this.mSuggestionQueue.peek()).map(new a(20)).orElse((Object) null)));
                concurrentLinkedQueue = this.mSuggestionQueue;
            } catch (Throwable th) {
                throw th;
            }
        }
        return concurrentLinkedQueue;
    }

    public boolean isSuggestionFeatureEnabled(Context context) {
        return true;
    }

    public abstract void loadCandidateSuggestionsInternal(Context context, int i2);

    public void loadSuggestion(Context context) {
        int size = 3 - this.mSuggestionQueue.size();
        if (!loadCandidateSuggestions(context, size)) {
            fillSuggestionQueue(size);
        }
    }

    public void preloadSuggestion(Context context, Blackboard blackboard) {
        getSuggestion(context, blackboard, -1);
    }

    public IRecommendationItem buildSuggestionItem(String str, String str2, String str3, String str4) {
        SuggestionItem.Builder dynamicState = new SuggestionItem.Builder().setSubCategory(str).setTagType(str2).setQueryString(str3).setTitle(TranslationManager.getInstance().getTranslatedString(str2, str)).setDynamicState();
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_EXTENDED_SUGGESTED_KEYWORD_FEATURE)) {
            dynamicState.setSuggestedKeywordFeature(str4);
        }
        return dynamicState.build();
    }
}
